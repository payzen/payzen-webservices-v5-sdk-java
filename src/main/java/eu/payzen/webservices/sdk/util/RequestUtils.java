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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils used to handle the requests in the Web Service
 * 
 * @author Javier Garcia Alonso
 */
public class RequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);
    
    /**
     * Retrieves the requestId information from the MD field
     * 
     * @param MD MD information sent to ACS
     * @return the extracted requestId
     */
    public static String getRequestIdFromMD(String MD) {
        String requestId = "";
        String data[] = getMDTokens(MD);
        
        if (data != null) {
            requestId += data[1];
        }
        
        return requestId;
    }
    
    /**
     * Retrieves the sessioncookie (JSESSIONID) form the MD field
     * 
     * @param MD MD information sent to ACS
     * @return the extracted cookie
     */
    public static String getSessionCookieFromMD(String MD) {
        String jsessionId = "";
        String data[] = getMDTokens(MD);
        
        if (data != null) {
            jsessionId += data[0];
        }
        
        return jsessionId;
    }
    
    private static String[] getMDTokens(String MD) {
         if (MD == null) {
            logger.error("No MD data found. Payment will fail!");
            return null;
        }
        
        String[] tokens = MD.split("\\+");
        
        if (tokens == null || tokens.length < 2) {
            logger.error("Incomplete MD data: " + MD + ". Payment will fail!");
            return null;
        }
        
        return tokens;
    }    
}
