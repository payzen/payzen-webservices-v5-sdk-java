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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 * Class used to add a handler into the web service handler chain 
 * 
 * @author Javier Garcia Alonso
 */
public class HeaderHandlerResolver implements HandlerResolver {

    private final String shopId;
    private final String shopKey;
    private final String mode;
    private final String wsUser;
    private final String returnUrl;
    private final String ecsPaymentId;
    private final String remoteId;
    
    private final Map<String, String> dynamicHeaders;
    
    public HeaderHandlerResolver(String shopId, String shopKey, String mode, String wsUser, String returnUrl, String ecsPaymentId, String remoteId, Map<String, String> dynamicHeaders) {
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
     * Adds HeaderHandler into the handler chain
     * 
     * @param portInfo used to query information about the port
     * @return list of handlers
     */
    @SuppressWarnings("rawtypes")
	public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlerChain = new ArrayList<>();

        HeaderHandler hh = new HeaderHandler(shopId, shopKey, mode, wsUser, returnUrl, ecsPaymentId, remoteId, dynamicHeaders);

        handlerChain.add(hh);

        return handlerChain;
    }
}