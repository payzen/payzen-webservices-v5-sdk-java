package eu.payzen.webservices.sdk.builder.request;

import com.lyra.vads.ws.v5.QueryRequest;

/**
 * Builder class to help in the construction of a PaymentRequest
 *
 * @author Javier Garcia Alonso
 */
public class QueryRequestBuilder {
    private String uuid;
    private String orderId;
    private String subscriptionId;
    private String paymentToken;

    private QueryRequestBuilder() {
    }

    public void uuid(String uuid) {
        this.uuid = uuid;
    }

    public void orderId(String orderId) {
        this.orderId = orderId;
    }

    public void subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void paymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public static QueryRequestBuilder create() {
        return new QueryRequestBuilder();
    }

    public QueryRequest build() {
        QueryRequest queryRequest = new QueryRequest();

        queryRequest.setUuid(uuid);
        queryRequest.setOrderId(orderId);
        queryRequest.setSubscriptionId(subscriptionId);
        queryRequest.setPaymentToken(paymentToken);

        return queryRequest;
    }
}
