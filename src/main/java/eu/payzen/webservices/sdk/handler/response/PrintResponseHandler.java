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
package eu.payzen.webservices.sdk.handler.response;

import eu.payzen.webservices.sdk.ResponseHandler;
import eu.payzen.webservices.sdk.ServiceResult;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Response handler (callback) that writes all the response in the standard output
 * 
 * @author Javier Garcia Alonso
 */
public class PrintResponseHandler implements ResponseHandler {

    public void handle(ServiceResult response) throws Exception {
        System.out.println("Response Code: " + response.getCommonResponse().getResponseCode());
        System.out.println("Response Code Detail: " + response.getCommonResponse().getResponseCodeDetail());
        
        System.out.println("Common Response: " + ToStringBuilder.reflectionToString(response.getCommonResponse()));
        System.out.println("Authorization Response: " + ToStringBuilder.reflectionToString(response.getAuthorizationResponse()));
        System.out.println("Capture Response: " + ToStringBuilder.reflectionToString(response.getCaptureResponse()));
        System.out.println("Card Response: " + ToStringBuilder.reflectionToString(response.getCardResponse()));
        System.out.println("Customer Response: " + ToStringBuilder.reflectionToString(response.getCustomerResponse()));
        System.out.println("Extra Response: " + ToStringBuilder.reflectionToString(response.getExtraResponse()));
        System.out.println("Fraud Management Response: " + ToStringBuilder.reflectionToString(response.getFraudManagementResponse()));
        System.out.println("Mark Response: " + ToStringBuilder.reflectionToString(response.getMarkResponse()));
        System.out.println("Order Response: " + ToStringBuilder.reflectionToString(response.getOrderResponse()));
        System.out.println("Payment Response: " + ToStringBuilder.reflectionToString(response.getPaymentResponse()));
        System.out.println("Shopping cart Response: " + ToStringBuilder.reflectionToString(response.getShoppingCartResponse()));
        System.out.println("Subscription Response: " + ToStringBuilder.reflectionToString(response.getSubscriptionResponse()));
        System.out.println("3DS Response: " + ToStringBuilder.reflectionToString(response.getThreeDSResponse()));
        System.out.println("Token Response: " + ToStringBuilder.reflectionToString(response.getTokenResponse()));
    }
    
}
