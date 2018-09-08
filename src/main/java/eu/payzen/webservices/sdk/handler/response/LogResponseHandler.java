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

import eu.payzen.webservices.sdk.ServiceResult;
import org.apache.commons.lang.builder.ToStringBuilder;
import eu.payzen.webservices.sdk.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Response handler (callback) that writes all the response in the logs
 * 
 * @author Javier Garcia Alonso
 */
public class LogResponseHandler implements ResponseHandler{
    private static final Logger logger = LoggerFactory.getLogger(LogResponseHandler.class);

    public void handle(ServiceResult response) throws Exception {
        logger.info("Response Code: " + response.getCommonResponse().getResponseCode());
        logger.info("Response Code Detail: " + response.getCommonResponse().getResponseCodeDetail());
        
        logger.info("Common Response: " + ToStringBuilder.reflectionToString(response.getCommonResponse()));
        logger.info("Authorization Response: " + ToStringBuilder.reflectionToString(response.getAuthorizationResponse()));
        logger.info("Capture Response: " + ToStringBuilder.reflectionToString(response.getCaptureResponse()));
        logger.info("Card Response: " + ToStringBuilder.reflectionToString(response.getCardResponse()));
        logger.info("Customer Response: " + ToStringBuilder.reflectionToString(response.getCustomerResponse()));
        logger.info("Extra Response: " + ToStringBuilder.reflectionToString(response.getExtraResponse()));
        logger.info("Fraud Management Response: " + ToStringBuilder.reflectionToString(response.getFraudManagementResponse()));
        logger.info("Mark Response: " + ToStringBuilder.reflectionToString(response.getMarkResponse()));
        logger.info("Order Response: " + ToStringBuilder.reflectionToString(response.getOrderResponse()));
        logger.info("Payment Response: " + ToStringBuilder.reflectionToString(response.getPaymentResponse()));
        logger.info("Shopping cart Response: " + ToStringBuilder.reflectionToString(response.getShoppingCartResponse()));
        logger.info("Subscription Response: " + ToStringBuilder.reflectionToString(response.getSubscriptionResponse()));
        logger.info("3DS Response: " + ToStringBuilder.reflectionToString(response.getThreeDSResponse()));
        logger.info("Token Response: " + ToStringBuilder.reflectionToString(response.getTokenResponse()));
    }
    
}
