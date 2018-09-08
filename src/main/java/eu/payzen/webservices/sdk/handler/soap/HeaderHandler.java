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
package eu.payzen.webservices.sdk.handler.soap;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This SOAP handler class generates the header of the message with all the 
 * authentication token information
 * 
 * @author Javier Garcia Alonso
 */
public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger logger = LoggerFactory.getLogger(HeaderHandler.class);
    
    private final String shopId;
    private final String shopKey;
    private final String mode;
    private final String wsUser;
    private final String returnUrl;
    private final String ecsPaymentId;
    private final String remoteId;
    
    private final Map<String, String> dynamicHeaders;

    private static final String NAMESPACE = "http://v5.ws.vads.lyra.com/Header/";

    public HeaderHandler(String shopId, String shopKey, String mode, String wsUser, String returnUrl, String ecsPaymentId, String remoteId, Map<String, String> dynamicHeaders) {
        this.shopId = shopId;
        this.shopKey = shopKey;
        this.mode = mode;
        this.wsUser = wsUser;
        this.returnUrl = returnUrl;
        this.ecsPaymentId = ecsPaymentId;
        this.remoteId = remoteId;
        
        this.dynamicHeaders = dynamicHeaders;
    }
    

    /**
     * Takes the outgoing SOAP message and modifies it adding the header 
     * information
     * 
     * @param smc SOAP message context
     * @return boolean indicating outbound property
     */
    public boolean handleMessage(SOAPMessageContext smc) {

        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (Boolean.TRUE.equals(outboundProperty)) {

            SOAPMessage message = smc.getMessage();

            try {
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                
                //Creates header into SOAP envelope
                SOAPHeader header = envelope.getHeader();
                if (header == null) {
                    header = envelope.addHeader();
                }

                // Add shopId
                addHeaderField(header, "shopId", this.shopId);
                
                // Add User name
                if (wsUser != null) {                	
                	addHeaderField(header, "wsUser", this.wsUser);
                }
                
                // Add return url
                if (returnUrl != null) {                	
                	addHeaderField(header, "returnUrl", this.returnUrl);
                }
                
                // Add ecsPaymentId
                if (ecsPaymentId != null) {                	
                	addHeaderField(header, "ecsPaymentId", this.ecsPaymentId);
                }
                
                // Add remoteId
                if (remoteId != null) {                	
                	addHeaderField(header, "remoteId", this.remoteId);
                }
                
                //DynamicHeaders
                if (dynamicHeaders != null) {
                	for (String key : dynamicHeaders.keySet()) {
                		String value = dynamicHeaders.get(key);
						if (value != null) {
							addHeaderField(header, key, value);
						}	
					}
                }

                // Timestamp
                TimeZone tz = TimeZone.getTimeZone("UTC");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                df.setTimeZone(tz);
                String nowAsISO = df.format(new Date());
                addHeaderField(header, "timestamp", nowAsISO);

                // Mode
                addHeaderField(header, "mode", this.mode);

                // Add requestId
                String requestId = UUID.randomUUID().toString();
                addHeaderField(header, "requestId", requestId);

                // Authentication token
                String tokenString = requestId + nowAsISO;
                addHeaderField(header, "authToken", sign(tokenString, shopKey));

            } catch (SOAPException e) {
                logger.error("Error sending header", e);
            }
        }

        return outboundProperty;

    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
    }

    private void addHeaderField(SOAPHeader header, String fieldName, String value) throws SOAPException {
        QName qname = new QName(NAMESPACE, fieldName);
        SOAPElement element = header.addChildElement(qname);
        element.addTextNode(value);
    }

    private String sign(String stringToSign, String key) {
        try {
            byte[] bytes = encode256(key.getBytes("UTF-8"), stringToSign.getBytes("UTF-8"));

            return Base64.encodeBase64String(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] encode256(byte[] keyBytes, byte[] text) throws NoSuchAlgorithmException, InvalidKeyException {

        Mac hmacSha1;
        try {
            hmacSha1 = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException nsae) {
            hmacSha1 = Mac.getInstance("HMAC-SHA-256");
        }
        SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
        try {
            hmacSha1.init(macKey);
        } catch (java.security.InvalidKeyException ex) {
            logger.error("Error encoding auth hash", ex);
        }

        return hmacSha1.doFinal(text);
    }

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}