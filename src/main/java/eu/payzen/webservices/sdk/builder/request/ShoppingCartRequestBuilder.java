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

import com.lyra.vads.ws.v5.CartItemInfo;
import com.lyra.vads.ws.v5.ShoppingCartRequest;
import java.util.List;

/**
 * Builder class to help in the construction of a ShoppingCartRequest
 * 
 * @author Javier Garcia Alonso
 */
public class ShoppingCartRequestBuilder {
    private Long insuranceAmount;
    private Long shippingAmount;
    private Long taxAmount;
    private List<CartItemInfo> cartItemInfo;

    private ShoppingCartRequestBuilder() {
    }

    public static ShoppingCartRequestBuilder create() {
        return new ShoppingCartRequestBuilder();
    }

    public void setInsuranceAmount(Long insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public void setShippingAmount(Long shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public void setTaxAmount(Long taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setCartItemInfo(List<CartItemInfo> cartItemInfo) {
        this.cartItemInfo = cartItemInfo;
    }
    
    public ShoppingCartRequest build() {
        ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest();
        
        shoppingCartRequest.setInsuranceAmount(insuranceAmount);
        shoppingCartRequest.setShippingAmount(shippingAmount);
        shoppingCartRequest.setTaxAmount(taxAmount);
        shoppingCartRequest.getCartItemInfo().addAll(cartItemInfo);
        
        return shoppingCartRequest;
    }
}
