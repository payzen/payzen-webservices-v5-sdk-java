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
package eu.payzen.webservices.sdk.builder.request;

import com.lyra.vads.ws.v5.PaymentRequest;
import java.util.Date;
import eu.payzen.webservices.sdk.util.BuilderUtils;

/**
 * Builder class to help in the construction of a PaymentRequest
 * 
 * @author Javier Garcia Alonso
 */
public class PaymentRequestBuilder {
    private String transactionId;
    private Long amount;
    private Integer currency;
    private Date expectedCaptureDate;
    private Integer manualValidation;
    private String paymentOptionCode;

    private PaymentRequestBuilder() {
    }
  
    public static PaymentRequestBuilder create() {
        return new PaymentRequestBuilder();
    }

    public PaymentRequestBuilder transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public PaymentRequestBuilder amount(long amount) {
        this.amount = amount;
        return this;
    }

    public PaymentRequestBuilder currency(int currency) {
        this.currency = currency;
        return this;
    }

    public PaymentRequestBuilder expectedCaptureDate(Date expectedCaptureDate) {
        this.expectedCaptureDate = expectedCaptureDate;
        return this;
    }

    public PaymentRequestBuilder manualValidation(int manualValidation) {
        this.manualValidation = manualValidation;
        return this;
    }

    public PaymentRequestBuilder paymentOptionCode(String paymentOptionCode) {
        this.paymentOptionCode = paymentOptionCode;
        return this;
    }

    public PaymentRequest build() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setTransactionId(transactionId);
        paymentRequest.setAmount(amount);
        paymentRequest.setCurrency(currency);
        paymentRequest.setExpectedCaptureDate(BuilderUtils.date2XMLGregorianCalendar(expectedCaptureDate));
        paymentRequest.setManualValidation(manualValidation);
        paymentRequest.setPaymentOptionCode(paymentOptionCode);

        return paymentRequest;
    }
}
