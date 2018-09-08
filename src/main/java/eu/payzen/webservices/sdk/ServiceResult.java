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
package eu.payzen.webservices.sdk;

import com.lyra.vads.ws.v5.*;
import com.lyra.vads.ws.v5.RefundPaymentResponse.RefundPaymentResult;
import eu.payzen.webservices.sdk.util.Config;

/**
 * Result object that encapsulates the response of a Payzen Web Service.
 * 
 * @author Javier Garcia Alonso
 */
public final class ServiceResult {

    private CommonResponse commonResponse = null;
    private OrderResponse orderResponse = null;
    private PaymentResponse paymentResponse = null;
    private CardResponse cardResponse = null;
    private AuthorizationResponse authorizationResponse = null;
    private CaptureResponse captureResponse = null;
    private CustomerResponse customerResponse = null;
    private ExtraResponse extraResponse = null;
    private ThreeDSResponse threeDSResponse = null;
    private TokenResponse tokenResponse = null;
    private ShoppingCartResponse shoppingCartResponse = null;
    private FraudManagementResponse fraudManagementResponse = null;
    private MarkResponse markResponse = null;
    private SubscriptionResponse subscriptionResponse = null;
    private String serviceSessionId = null;

    public ServiceResult(CreatePaymentResponse.CreatePaymentResult createPaymentResult, String httpSessionId) {
        commonResponse = createPaymentResult.getCommonResponse();
        paymentResponse = createPaymentResult.getPaymentResponse();
        orderResponse = createPaymentResult.getOrderResponse();
        cardResponse = createPaymentResult.getCardResponse();
        authorizationResponse = createPaymentResult.getAuthorizationResponse();
        captureResponse = createPaymentResult.getCaptureResponse();
        customerResponse = createPaymentResult.getCustomerResponse();
        extraResponse = createPaymentResult.getExtraResponse();
        fraudManagementResponse = createPaymentResult.getFraudManagementResponse();
        markResponse = createPaymentResult.getMarkResponse();
        shoppingCartResponse = createPaymentResult.getShoppingCartResponse();
        subscriptionResponse = createPaymentResult.getSubscriptionResponse();
        threeDSResponse = createPaymentResult.getThreeDSResponse();
        serviceSessionId = httpSessionId;
    }

    public ServiceResult(GetPaymentDetailsResponse.GetPaymentDetailsResult detailsPaymentResult) {
        commonResponse = detailsPaymentResult.getCommonResponse();
        paymentResponse = detailsPaymentResult.getPaymentResponse();
        orderResponse = detailsPaymentResult.getOrderResponse();
        cardResponse = detailsPaymentResult.getCardResponse();
        authorizationResponse = detailsPaymentResult.getAuthorizationResponse();
        captureResponse = detailsPaymentResult.getCaptureResponse();
        customerResponse = detailsPaymentResult.getCustomerResponse();
        extraResponse = detailsPaymentResult.getExtraResponse();
        fraudManagementResponse = detailsPaymentResult.getFraudManagementResponse();
        markResponse = detailsPaymentResult.getMarkResponse();
        //shoppingCartResponse = detailsPaymentResult.getShoppingCartResponse();
        subscriptionResponse = detailsPaymentResult.getSubscriptionResponse();
        threeDSResponse = detailsPaymentResult.getThreeDSResponse();
    }
    
    public ServiceResult(CancelPaymentResponse.CancelPaymentResult cancelResult) {
        commonResponse = cancelResult.getCommonResponse();
    }
    
    public ServiceResult(UpdatePaymentResponse.UpdatePaymentResult updateResult) {
        commonResponse = updateResult.getCommonResponse();
        paymentResponse = updateResult.getPaymentResponse();
        orderResponse = updateResult.getOrderResponse();
        cardResponse = updateResult.getCardResponse();
        authorizationResponse = updateResult.getAuthorizationResponse();
        captureResponse = updateResult.getCaptureResponse();
        customerResponse = updateResult.getCustomerResponse();
        extraResponse = updateResult.getExtraResponse();
        fraudManagementResponse = updateResult.getFraudManagementResponse();
        markResponse = updateResult.getMarkResponse();
        //shoppingCartResponse = detailsPaymentResult.getShoppingCartResponse();
        subscriptionResponse = updateResult.getSubscriptionResponse();
        threeDSResponse = updateResult.getThreeDSResponse();
    }

    public ServiceResult(GetPaymentUuidResponse.LegacyTransactionKeyResult getPaymentUuidResult) {
        commonResponse = getPaymentUuidResult.getCommonResponse();
        paymentResponse = getPaymentUuidResult.getPaymentResponse();
    }
    
    public ServiceResult(RefundPaymentResult refundPaymentResult) {
    	 commonResponse = refundPaymentResult.getCommonResponse();
         paymentResponse = refundPaymentResult.getPaymentResponse();
         orderResponse = refundPaymentResult.getOrderResponse();
         cardResponse = refundPaymentResult.getCardResponse();
         authorizationResponse = refundPaymentResult.getAuthorizationResponse();
         captureResponse = refundPaymentResult.getCaptureResponse();
         customerResponse = refundPaymentResult.getCustomerResponse();
         markResponse = refundPaymentResult.getMarkResponse();
         threeDSResponse = refundPaymentResult.getThreeDSResponse();
         extraResponse = refundPaymentResult.getExtraResponse();
         fraudManagementResponse = refundPaymentResult.getFraudManagementResponse();
    }

    public ServiceResult(ValidatePaymentResponse.ValidatePaymentResult validatePaymentResult) {
        commonResponse = validatePaymentResult.getCommonResponse();
    }

    public ServiceResult(CreateTokenFromTransactionResponse.CreateTokenFromTransactionResult createTokenFromTransactionResult) {
        commonResponse = createTokenFromTransactionResult.getCommonResponse();
        paymentResponse = createTokenFromTransactionResult.getPaymentResponse();
        orderResponse = createTokenFromTransactionResult.getOrderResponse();
        cardResponse = createTokenFromTransactionResult.getCardResponse();
        authorizationResponse = createTokenFromTransactionResult.getAuthorizationResponse();
        captureResponse = createTokenFromTransactionResult.getCaptureResponse();
        customerResponse = createTokenFromTransactionResult.getCustomerResponse();
        markResponse = createTokenFromTransactionResult.getMarkResponse();
        threeDSResponse = createTokenFromTransactionResult.getThreeDSResponse();
        extraResponse = createTokenFromTransactionResult.getExtraResponse();
        fraudManagementResponse = createTokenFromTransactionResult.getFraudManagementResponse();
    }

    public CommonResponse getCommonResponse() {
        return commonResponse;
    }

    public OrderResponse getOrderResponse() {
        return orderResponse;
    }

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public CardResponse getCardResponse() {
        return cardResponse;
    }

    public AuthorizationResponse getAuthorizationResponse() {
        return authorizationResponse;
    }

    public CaptureResponse getCaptureResponse() {
        return captureResponse;
    }

    public CustomerResponse getCustomerResponse() {
        return customerResponse;
    }

    public ExtraResponse getExtraResponse() {
        return extraResponse;
    }

    public ThreeDSResponse getThreeDSResponse() {
        return threeDSResponse;
    }

    public TokenResponse getTokenResponse() {
        return tokenResponse;
    }

    public ShoppingCartResponse getShoppingCartResponse() {
        return shoppingCartResponse;
    }

    public FraudManagementResponse getFraudManagementResponse() {
        return fraudManagementResponse;
    }

    public MarkResponse getMarkResponse() {
        return markResponse;
    }

    public SubscriptionResponse getSubscriptionResponse() {
        return subscriptionResponse;
    }

    public String getWebServiceSession() {
        return serviceSessionId;
    }

    public String getRedirectAcsMD() {
        if (getWebServiceSession() != null && threeDSResponse != null
                && threeDSResponse.getAuthenticationRequestData() != null
                && threeDSResponse.getAuthenticationRequestData().getThreeDSRequestId() != null) {
            return getWebServiceSession() + "+" + threeDSResponse.getAuthenticationRequestData().getThreeDSRequestId();
        }
        //TODO: handle error
        return null;
    }

    public String getMode() {
        return Config.getConfig().getProperty("mode");
    }
}
