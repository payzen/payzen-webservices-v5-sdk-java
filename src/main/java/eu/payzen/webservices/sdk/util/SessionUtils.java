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

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

/**
 * Utils to manage the session between different Web Service calls (case of 
 * 3D Secure Payment)
 * 
 * @author Javier Garcia Alonso
 */
public class SessionUtils {
    public static String SET_COOKIE_HEADER = "Set-Cookie";
    public static String SEARCH_SESSIONID_REGEX = "JSESSIONID=(.*?);";
    
    /**
     * Get the JSESSIONID from the cookie sent by Web Service
     * 
     * @param port web service port
     * @return value of session in cookie
     */
    public static String getHttpSessionId(BindingProvider port) {
        
        @SuppressWarnings("unchecked")
		Map<String, List<String>> headers = (Map<String, List<String>>) port.getResponseContext().get(MessageContext.HTTP_RESPONSE_HEADERS);
        
        List<String> cookie = headers.get(SET_COOKIE_HEADER);
        String cookieValue = cookie.get(0);        
        
        Pattern p = Pattern.compile(SEARCH_SESSIONID_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(cookieValue);
        
        if (m.find()) {
            return m.group(0);
        }        
        
        return "UNKNOWN";
    }
    
}
