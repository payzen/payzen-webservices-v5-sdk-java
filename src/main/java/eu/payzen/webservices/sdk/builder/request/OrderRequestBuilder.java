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

import com.lyra.vads.ws.v5.OrderRequest;
import com.lyra.vads.ws.v5.ExtInfo;
import java.util.List;

/**
 * Builder class to help in the construction of a OrderRequest
 * 
 * @author Javier Garcia Alonso
 */
public class OrderRequestBuilder {

    protected String orderId;
    protected List<ExtInfo> extInfo;

    private OrderRequestBuilder() {
    }

    public static OrderRequestBuilder create() {
        return new OrderRequestBuilder();
    }

    public OrderRequestBuilder orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderRequestBuilder extInfo(List<ExtInfo> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public OrderRequest build() {
        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setOrderId(orderId);
        if (extInfo != null) {
            orderRequest.getExtInfo().addAll(extInfo);
        }

        return orderRequest;
    }
}
