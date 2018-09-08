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
package eu.payzen.webservices.sdk.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Verifier that checks that the hostName matchs with the server's auhentication 
 * scheme
 * 
 * @author Javier Garcia Alonso
 */
public class PayzenHostnameVerifier implements HostnameVerifier {

    public boolean verify(String hostname, SSLSession session) {
        HostnameVerifier hv =
            HttpsURLConnection.getDefaultHostnameVerifier();
        String endpointToVerify = Config.getConfig().getProperty("endpointHost");
        return hv.verify(endpointToVerify, session);
    }
}
