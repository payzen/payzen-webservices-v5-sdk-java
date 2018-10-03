/*
 * Copyright 2015-2018 Javier Garcia Alonso.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.payzen.webservices.sdk.client;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.lyra.vads.ws.v5.PaymentAPI;

import com.sun.xml.ws.client.BindingProviderProperties;
import eu.payzen.webservices.sdk.handler.soap.HeaderHandlerResolver;
import eu.payzen.webservices.sdk.util.Config;
import eu.payzen.webservices.sdk.util.NullHostnameVerifier;
import eu.payzen.webservices.sdk.util.PayzenHostnameVerifier;
import org.apache.commons.lang.StringUtils;

/**
 * Encapsulates the client WS to call Payment operations
 *
 * @author Javier Garcia Alonso
 */
public class ClientV5 {

	private final PaymentAPI port;

	public ClientV5(Map<String, String> config) {
		// Read client properties - ws-sdk-config.properties
		String shopId = getProperty("shopId", config);
		String shopKey = getProperty("shopKey", config);
		String mode = getProperty("mode", config);
		String endpointHost = getProperty("endpointHost", config);
		String secureConnection = getProperty("secureConnection", config);
		String disableHostnameVerifier = getProperty("disableHostnameVerifier", config);
		String wsUser = getProperty("wsUser", config);
		String returnUrl = getProperty("returnUrl", config);
		String ecsPaymentId = getProperty("ecsPaymentId", config);
		String remoteId = getProperty("remoteId", config);
		String connectionTimeout = getProperty("connectionTimeout", config);
		String requestTimeout = getProperty("requestTimeout", config);

		String protocol = "https://";
		if (!("true".equalsIgnoreCase(secureConnection))) {
			protocol = "http://";
		}

		// Adds hostnameverifier to check domain/certificate
		HostnameVerifier verifier = ("true".equalsIgnoreCase(disableHostnameVerifier)) ? new NullHostnameVerifier()
				: new PayzenHostnameVerifier();
		HttpsURLConnection.setDefaultHostnameVerifier(verifier);

		// Initialises port
		StringBuilder wsdlURLStr = new StringBuilder(protocol);
		wsdlURLStr.append(endpointHost).append("/vads-ws/v5?wsdl");
		URL wsdlURL;
		try {
			wsdlURL = new URL(wsdlURLStr.toString());
			QName qname = new QName("http://v5.ws.vads.lyra.com/", "v5");
			Service service = Service.create(wsdlURL, qname);
			service.setHandlerResolver(new HeaderHandlerResolver(shopId, shopKey, mode, wsUser, returnUrl, ecsPaymentId, remoteId, config));
			port = service.getPort(PaymentAPI.class);

			//Set timeout values if necessary
			if (StringUtils.isNotBlank(connectionTimeout) || StringUtils.isNotBlank(requestTimeout)) {
				setTimeoutValues(port, connectionTimeout, requestTimeout);
			}

		}  catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Returns Web Service port to Payzen payment API
	 *
	 * @return port to Payzen Payment API
	 */
	public PaymentAPI getPaymentAPIImplPort() {
		return port;
	}
	
	private String getProperty(String key, Map<String, String> config) {
		String value = (config != null && config.get(key) != null) ? config.remove(key)
				:Config.getConfig().getProperty(key);
		return value;
	}

	private void setTimeoutValues(PaymentAPI port, String connectionTimeout, String requestTimeout) {
		BindingProvider bindingProvider = (BindingProvider)port;

		if (StringUtils.isNotBlank(connectionTimeout)) {
			bindingProvider.getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT,
					Integer.valueOf(connectionTimeout).intValue() * 1000);
		}
		if (StringUtils.isNotBlank(requestTimeout)) {
			bindingProvider.getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT,
					Integer.valueOf(requestTimeout).intValue() * 1000);
		}
	}
}
