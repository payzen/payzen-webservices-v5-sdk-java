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

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import com.lyra.vads.ws.v5.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.vads.ws.v5.CancelSubscriptionResponse.CancelSubscriptionResult;
import com.lyra.vads.ws.v5.CreateSubscriptionResponse.CreateSubscriptionResult;
import com.lyra.vads.ws.v5.CreateTokenFromTransactionResponse.CreateTokenFromTransactionResult;
import com.lyra.vads.ws.v5.GetSubscriptionDetailsResponse.GetSubscriptionDetailsResult;
import com.lyra.vads.ws.v5.RefundPaymentResponse.RefundPaymentResult;
import com.lyra.vads.ws.v5.ValidatePaymentResponse.ValidatePaymentResult;

import eu.payzen.webservices.sdk.client.ClientV5;
import eu.payzen.webservices.sdk.util.BuilderUtils;
import eu.payzen.webservices.sdk.util.RequestUtils;
import eu.payzen.webservices.sdk.util.SessionUtils;

/**
 * Implementation of methods used by Payment class to create web service
 * requests related with payments.
 *
 * @see Payment
 *
 * @author Javier Garcia Alonso
 */
final class PaymentInstance {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(PaymentInstance.class);

	/**
	 * Creates a payment request using the common parameters in a simple way.
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameters.
	 *
	 * @param orderId
	 *            Optional, null is none. The order Id.
	 * @param amount
	 *            Amount of the payment in cents
	 * @param currency
	 *            used currency
	 * @param cardNumber
	 *            card number
	 * @param expMonth
	 *            expiration month
	 * @param expYear
	 *            expiration year
	 * @param cvvCode
	 *            card verification code
	 *
	 * @return result with all the response objects
	 *
	 * @see Payment#create(java.lang.String, long, int, java.lang.String, int,
	 *      int, java.lang.String)
	 */
	ServiceResult createSimple(Map<String, String> config, String orderId, long amount, int currency, String cardNumber,
			int expMonth, int expYear, String cvvCode) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderId(orderId);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(amount);
		paymentRequest.setCurrency(currency);

		CardRequest cardRequest = new CardRequest();
		cardRequest.setNumber(cardNumber);
		cardRequest.setScheme("VISA"); // It does not matter. It will be
										// recalified
		cardRequest.setExpiryMonth(expMonth);
		cardRequest.setExpiryYear(expYear);
		cardRequest.setCardSecurityCode(cvvCode);

		CreatePaymentResponse.CreatePaymentResult paymentResponse = api.createPayment(new CommonRequest(), null,
				paymentRequest, orderRequest, cardRequest, null, null, null);

		ServiceResult serviceResult = new ServiceResult(paymentResponse,
				SessionUtils.getHttpSessionId((BindingProvider) api));

		return serviceResult;
	}

	/**
	 * Creates a payment request using the common parameters in a simple way.
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameters.
	 *
	 * @param orderId
	 *            Optional, null is none. The order Id.
	 * @param amount
	 *            Amount of the payment in cents
	 * @param currency
	 *            used currency
	 * @param cardNumber
	 *            card number
	 * @param expMonth
	 *            expiration month
	 * @param expYear
	 *            expiration year
	 * @param cvvCode
	 *            card verification code
	 * @param response
	 *            callback handler to work with the response
	 *
	 * @return result with all the response objects
	 *
	 * @see Payment#create(java.lang.String, long, int, java.lang.String, int,
	 *      int, java.lang.String,
	 *      ResponseHandler)
	 */
	ServiceResult createSimple(Map<String, String> config, String orderId, long amount, int currency, String cardNumber,
			int expMonth, int expYear, String cvvCode, ResponseHandler response) {
		ServiceResult serviceResult = createSimple(config, orderId, amount, currency, cardNumber, expMonth, expYear,
				cvvCode);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Creates a payment request using the createPayment object
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param createPaymentRequest
	 *            complex object with all the parameters for service call
	 * @return result with all the response objects
	 *
	 * @see Payment#create(com.lyra.vads.ws.v5.CreatePayment)
	 */
	ServiceResult create(Map<String, String> config, CreatePayment createPaymentRequest) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		CreatePaymentResponse.CreatePaymentResult paymentResponse = api.createPayment(
				createPaymentRequest.getCommonRequest(), createPaymentRequest.getThreeDSRequest(),
				createPaymentRequest.getPaymentRequest(), createPaymentRequest.getOrderRequest(),
				createPaymentRequest.getCardRequest(), createPaymentRequest.getCustomerRequest(),
				createPaymentRequest.getTechRequest(), createPaymentRequest.getShoppingCartRequest());

		ServiceResult serviceResult = new ServiceResult(paymentResponse,
				SessionUtils.getHttpSessionId((BindingProvider) api));

		return serviceResult;
	}

	/**
	 * Creates a payment request using the createPayment object
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param createPaymentRequest
	 *            complex object with all the parameters for service call
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#create(com.lyra.vads.ws.v5.CreatePayment,
	 *      ResponseHandler)
	 */
	ServiceResult create(Map<String, String> config, CreatePayment createPaymentRequest, ResponseHandler response) {
		ServiceResult serviceResult = create(config, createPaymentRequest);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Creates a payment in the system after returning from ACS (payment 3DS) .
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameters.
	 *
	 * @param paRes
	 *            Response from ACS
	 * @param MD
	 *            Payment session information
	 * @return result with all the response objects
	 *
	 * @see Payment#create(java.lang.String, java.lang.String)
	 */
	ServiceResult create3DS(Map<String, String> config, String paREs, String MD) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		ThreeDSRequest threeDSRequest = new ThreeDSRequest();
		threeDSRequest.setMode(ThreeDSMode.ENABLED_FINALIZE);
		threeDSRequest.setPares(paREs);
		threeDSRequest.setRequestId(RequestUtils.getRequestIdFromMD(MD));

		// Maintain Session! (this payment context will not be found if we do
		// not provide the good session)
		((BindingProvider) api).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS,
				Collections.singletonMap("Cookie", Collections.singletonList(RequestUtils.getSessionCookieFromMD(MD))));
		logger.info("Setting session Cookie: " + RequestUtils.getSessionCookieFromMD(MD));

		CreatePaymentResponse.CreatePaymentResult paymentResponse = api.createPayment(new CommonRequest(),
				threeDSRequest, null, null, null, null, null, null);

		ServiceResult serviceResult = new ServiceResult(paymentResponse, RequestUtils.getSessionCookieFromMD(MD));

		return serviceResult;
	}

	/**
	 * Creates a payment in the system after returning from ACS (payment 3DS) .
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameters.
	 *
	 * @param paRes
	 *            Response from ACS
	 * @param MD
	 *            Payment session information
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#create(java.lang.String, java.lang.String,
	 *      ResponseHandler)
	 */
	ServiceResult create3DS(Map<String, String> config, String paREs, String MD, ResponseHandler response) {
		ServiceResult serviceResult = create3DS(config, paREs, MD);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Get all the details of an existing transaction using the UUID of the
	 * transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult detailsSimple(Map<String, String> config, String uuidTransaction) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		GetPaymentDetailsResponse.GetPaymentDetailsResult detailsResponse = api.getPaymentDetails(queryRequest, new ExtendedResponseRequest());

		ServiceResult serviceResult = new ServiceResult(detailsResponse);

		return serviceResult;
	}

	/**
	 * Get all the details of an existing transaction using the UUID of the
	 * transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String,
	 *      ResponseHandler)
	 */
	ServiceResult detailsSimple(Map<String, String> config, String uuidTransaction, ResponseHandler response) {
		ServiceResult serviceResult = detailsSimple(config, uuidTransaction);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Get all the details of an existing transaction using the three key field
	 * that identify a transaction uniquely
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param transactionId
	 *            the transaction id number
	 * @param creationDate
	 *            the creation date. It only takes the day into account
	 * @param sequenceNumber
	 *            the sequence number in case o multiple payment. Always 1 in
	 *            case of simple payment
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String, java.util.Date, int)
	 */
	ServiceResult detailsByFind(Map<String, String> config, String transactionId, Date creationDate,
			int sequenceNumber) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		LegacyTransactionKeyRequest transactionKey = new LegacyTransactionKeyRequest();
		transactionKey.setTransactionId(transactionId);
		transactionKey.setCreationDate(BuilderUtils.date2XMLGregorianCalendar(creationDate));
		transactionKey.setSequenceNumber(sequenceNumber);

		GetPaymentUuidResponse.LegacyTransactionKeyResult keyResult = api.getPaymentUuid(transactionKey);

		ServiceResult serviceResult;
		if (keyResult.getPaymentResponse() != null && keyResult.getPaymentResponse().getTransactionUuid() != null) {
			serviceResult = detailsSimple(config, keyResult.getPaymentResponse().getTransactionUuid());
		} else {
			serviceResult = new ServiceResult(keyResult);
		}

		return serviceResult;
	}

	/**
	 * Get all the details of an existing transaction using the three key field
	 * that identify a transaction uniquely
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param transactionId
	 *            the transaction id number
	 * @param creationDate
	 *            the creation date. It only takes the day into account
	 * @param sequenceNumber
	 *            the sequence number in case o multiple payment. Always 1 in
	 *            case of simple payment
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String, java.util.Date, int,
	 *      ResponseHandler)
	 */
	ServiceResult detailsByFind(Map<String, String> config, String transactionId, Date creationDate, int sequenceNumber,
			ResponseHandler response) {
		ServiceResult serviceResult = detailsByFind(config, transactionId, creationDate, sequenceNumber);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Cancel an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult cancelSimple(Map<String, String> config, String uuidTransaction) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		CancelPaymentResponse.CancelPaymentResult cancelResponse = api.cancelPayment(new CommonRequest(), queryRequest);

		ServiceResult serviceResult = new ServiceResult(cancelResponse);

		return serviceResult;
	}

	/**
	 * Cancel an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String,
	 *      ResponseHandler)
	 */
	ServiceResult cancelSimple(Map<String, String> config, String uuidTransaction, ResponseHandler response) {
		ServiceResult serviceResult = cancelSimple(config, uuidTransaction);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Cancel an existing transaction using the three key field that identify a
	 * transaction uniquely
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param transactionId
	 *            the transaction id number
	 * @param creationDate
	 *            the creation date. It only takes the day into account
	 * @param sequenceNumber
	 *            the sequence number in case o multiple payment. Always 1 in
	 *            case of simple payment
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String, java.util.Date, int)
	 */
	ServiceResult cancelByFind(Map<String, String> config, String transactionId, Date creationDate,
			int sequenceNumber) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		LegacyTransactionKeyRequest transactionKey = new LegacyTransactionKeyRequest();
		transactionKey.setTransactionId(transactionId);
		transactionKey.setCreationDate(BuilderUtils.date2XMLGregorianCalendar(creationDate));
		transactionKey.setSequenceNumber(sequenceNumber);

		GetPaymentUuidResponse.LegacyTransactionKeyResult keyResult = api.getPaymentUuid(transactionKey);

		ServiceResult serviceResult;
		if (keyResult.getPaymentResponse() != null && keyResult.getPaymentResponse().getTransactionUuid() != null) {
			serviceResult = cancelSimple(config, keyResult.getPaymentResponse().getTransactionUuid());
		} else {
			serviceResult = new ServiceResult(keyResult);
		}

		return serviceResult;
	}

	/**
	 * Cancel an existing transaction using the three key field that identify a
	 * transaction uniquely
	 * <p>
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param transactionId
	 *            the transaction id number
	 * @param creationDate
	 *            the creation date. It only takes the day into account
	 * @param sequenceNumber
	 *            the sequence number in case o multiple payment. Always 1 in
	 *            case of simple payment
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String, java.util.Date, int,
	 *      ResponseHandler)
	 */
	ServiceResult cancelByFind(Map<String, String> config, String transactionId, Date creationDate, int sequenceNumber,
			ResponseHandler response) {
		ServiceResult serviceResult = cancelByFind(config, transactionId, creationDate, sequenceNumber);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	/**
	 * Updates an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param amount
	 *            the new amount of the transaction
	 * 
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult updateSimple(Map<String, String> config, String uuidTransaction, long amount, int currency) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(amount);
		paymentRequest.setCurrency(currency);

		UpdatePaymentResponse.UpdatePaymentResult updateResponse = api.updatePayment(new CommonRequest(), queryRequest,
				paymentRequest);

		ServiceResult serviceResult = new ServiceResult(updateResponse);

		return serviceResult;
	}

	/**
	 * Updates an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param captureDate
	 *            expected capture date
	 * 
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult updateSimple(Map<String, String> config, String uuidTransaction, Date captureDate) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setExpectedCaptureDate(BuilderUtils.date2XMLGregorianCalendar(captureDate));

		UpdatePaymentResponse.UpdatePaymentResult updateResponse = api.updatePayment(new CommonRequest(), queryRequest,
				paymentRequest);

		ServiceResult serviceResult = new ServiceResult(updateResponse);

		return serviceResult;
	}

	/**
	 * Updates an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param paymentRequest
	 *            payment request parameters to update
	 * 
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult updateSimple(Map<String, String> config, String uuidTransaction, PaymentRequest paymentRequest) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		UpdatePaymentResponse.UpdatePaymentResult updateResponse = api.updatePayment(new CommonRequest(), queryRequest,
				paymentRequest);

		ServiceResult serviceResult = new ServiceResult(updateResponse);

		return serviceResult;
	}

	/**
	 * Updates an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param amount
	 *            the new amount of the transaction
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult updateSimple(Map<String, String> config, String uuidTransaction, long amount, int currency,
			ResponseHandler response) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(amount);
		paymentRequest.setCurrency(currency);

		UpdatePaymentResponse.UpdatePaymentResult updateResponse = api.updatePayment(new CommonRequest(), queryRequest,
				paymentRequest);

		ServiceResult serviceResult = new ServiceResult(updateResponse);

		return serviceResult;
	}

	/**
	 * Updates an existing transaction using the UUID of the transaction
	 * <p>
	 *
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 *
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param captureDate
	 *            expected capture date
	 * @param response
	 *            callback handler to work with the response
	 * @return result with all the response objects
	 *
	 * @see Payment#details(java.lang.String)
	 */
	ServiceResult updateSimple(Map<String, String> config, String uuidTransaction, Date captureDate,
			ResponseHandler response) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setExpectedCaptureDate(BuilderUtils.date2XMLGregorianCalendar(captureDate));

		UpdatePaymentResponse.UpdatePaymentResult updateResponse = api.updatePayment(new CommonRequest(), queryRequest,
				paymentRequest);

		ServiceResult serviceResult = new ServiceResult(updateResponse);

		return serviceResult;
	}

	/**
	 * Validate an existing transaction using the UUID of the transaction
	 * <p>
	 * 
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 * 
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @return result with all the response objects
	 */
	ServiceResult validatePayment(Map<String, String> config, String uuidTransaction, String comment) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		CommonRequest commonRequest = new CommonRequest();
		commonRequest.setComment(comment);

		ValidatePaymentResult validatePayment = api.validatePayment(commonRequest, queryRequest);
		ServiceResult serviceResult = new ServiceResult(validatePayment);

		return serviceResult;
	}

	/**
	 * Refund a payment by its transaction UUID <p>
     *
	 * @param config
	 * @param uuidTransaction unique identifier of the transaction
	 * @param amount Amount to refund in cents
     * @param currency used currency in ISO 4217
     * @param comment commentary to add to history
	 * @return result with all the response objects
	 */
	ServiceResult refund(Map<String, String> config, String uuidTransaction, long amount, int currency, String comment) {

		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();

		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);

		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(amount);
		paymentRequest.setCurrency(currency);

		CommonRequest commonRequest = new CommonRequest();
		commonRequest.setComment(comment);

		RefundPaymentResult refundPayment = api.refundPayment(commonRequest, paymentRequest, queryRequest);
		ServiceResult serviceResult = new ServiceResult(refundPayment);
		return serviceResult;
	}

	/**
	 * Create a token using the card data an existing transaction using the UUID of the transaction<p>
	 * 
	 * Please read official documentation for more detailed information about
	 * parameter content.
	 * 
	 * @param uuidTransaction
	 *            unique identifier of the transaction
	 * @param comment
	 *            commonRequest parameters
	 * @return result with all the response objects
	 */
	ServiceResult createTokenFromTransaction(Map<String, String> config, String uuidTransaction,
			String comment) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setUuid(uuidTransaction);
		CommonRequest commonRequest = new CommonRequest();
		commonRequest.setComment(comment);

		CreateTokenFromTransactionResult createTokenFromTransaction = api.createTokenFromTransaction(commonRequest,
				new CardRequest(), queryRequest);
        ServiceResult serviceResult = new ServiceResult(createTokenFromTransaction);
        return serviceResult;
	}

	/**
	 * 
	 * @param config
	 * @param commonRequest
	 * @param orderRequest
	 * @param subscriptionRequest
	 * @param cardRequest
	 * @return
	 */
	CreateSubscriptionResult createSubscription(Map<String, String> config, CommonRequest commonRequest,
			OrderRequest orderRequest, SubscriptionRequest subscriptionRequest, CardRequest cardRequest) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		CreateSubscriptionResult createSubscription = api.createSubscription(commonRequest, orderRequest,
				subscriptionRequest, cardRequest);
		return createSubscription;
	}

	/**
	 * 
	 * @param config
	 * @param queryRequest
	 * @return
	 */
	GetSubscriptionDetailsResult getSubscriptionDetails(Map<String, String> config, QueryRequest queryRequest) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		GetSubscriptionDetailsResult subscriptionDetails = api.getSubscriptionDetails(queryRequest);
		return subscriptionDetails;
	}

	/**
	 * 
	 * @param config
	 * @param commonRequest
	 * @param queryRequest
	 * @return
	 */
	CancelSubscriptionResult cancelSubscription(Map<String, String> config, CommonRequest commonRequest,
			QueryRequest queryRequest) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		CancelSubscriptionResult cancelSubscription = api.cancelSubscription(commonRequest, queryRequest);
		return cancelSubscription;
	}

	/**
	 * 
	 * @param config
	 * @param orderId
	 * @return
	 */
	// TODO: not used for the moment
	ServiceResult details(Map<String, String> config, String orderId) {
		PaymentAPI api = new ClientV5(config).getPaymentAPIImplPort();
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setOrderId(orderId);

		GetPaymentDetailsResponse.GetPaymentDetailsResult detailsResponse = api.getPaymentDetails(queryRequest, new ExtendedResponseRequest());

		ServiceResult serviceResult = new ServiceResult(detailsResponse);

		return serviceResult;
	}

	/**
	 * 
	 * @param config
	 * @param orderId
	 * @param response
	 * @return
	 */
	// TODO: not used for the moment
	ServiceResult details(Map<String, String> config, String orderId, ResponseHandler response) {
		ServiceResult serviceResult = details(config, orderId);

		handleResponse(response, serviceResult);

		return serviceResult;
	}

	// Handle response in callback way
	private void handleResponse(ResponseHandler response, ServiceResult serviceResult) {
		try {
			response.handle(serviceResult);
		} catch (Exception ex) {
			logger.error("Cannot handle response.", ex);
		}
	}
}
