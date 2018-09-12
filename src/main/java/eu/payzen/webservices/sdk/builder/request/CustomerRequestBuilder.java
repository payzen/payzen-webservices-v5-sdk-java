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

import com.lyra.vads.ws.v5.BillingDetailsRequest;
import com.lyra.vads.ws.v5.CustStatus;
import com.lyra.vads.ws.v5.CustomerRequest;
import com.lyra.vads.ws.v5.DeliverySpeed;
import com.lyra.vads.ws.v5.DeliveryType;
import com.lyra.vads.ws.v5.ExtraDetailsRequest;
import com.lyra.vads.ws.v5.ShippingDetailsRequest;

/**
 * Builder class to help in the construction of a CustomerRequest
 * 
 * @author Javier Garcia Alonso
 */
public class CustomerRequestBuilder {
    private BillingDetailsRequest billingDetailsRequest;
    private ShippingDetailsRequest shippingDetailsRequest;
    private ExtraDetailsRequest extraDetailsRequest;

    public static CustomerRequestBuilder create() { return new CustomerRequestBuilder(); }

    public CustomerRequestBuilder billingDetailsRequest(BillingDetailsRequest billingDetailsRequest) {
        this.billingDetailsRequest = billingDetailsRequest;
        return this;
    }

    public CustomerRequestBuilder shippingDetailsRequest(ShippingDetailsRequest shippingDetailsRequest) {
        this.shippingDetailsRequest = shippingDetailsRequest;
        return this;
    }

    public CustomerRequestBuilder extraDetailsRequest(ExtraDetailsRequest extraDetailsRequest) {
        this.extraDetailsRequest = extraDetailsRequest;
        return this;
    }
    
    public CustomerRequest build() {
        CustomerRequest customerRequest = new CustomerRequest();
        
        customerRequest.setBillingDetails(billingDetailsRequest);
        customerRequest.setShippingDetails(shippingDetailsRequest);
        customerRequest.setExtraDetails(extraDetailsRequest);
        
        return customerRequest;
    }

    public static class BillingDetailsRequestBuilder {

        private String reference;
        private String title;
        private CustStatus type;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String streetNumber;
        private String address;
        private String district;
        private String zipCode;
        private String city;
        private String state;
        private String country;
        private String language;
        private String cellPhoneNumber;
        private String legalName;
        private String identityCode;

        public static BillingDetailsRequestBuilder create() { return new BillingDetailsRequestBuilder(); }

        public BillingDetailsRequestBuilder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public BillingDetailsRequestBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BillingDetailsRequestBuilder type(CustStatus type) {
            this.type = type;
            return this;
        }

        public BillingDetailsRequestBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BillingDetailsRequestBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BillingDetailsRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public BillingDetailsRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BillingDetailsRequestBuilder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public BillingDetailsRequestBuilder address(String address) {
            this.address = address;
            return this;
        }

        public BillingDetailsRequestBuilder district(String district) {
            this.district = district;
            return this;
        }

        public BillingDetailsRequestBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public BillingDetailsRequestBuilder city(String city) {
            this.city = city;
            return this;
        }

        public BillingDetailsRequestBuilder state(String state) {
            this.state = state;
            return this;
        }

        public BillingDetailsRequestBuilder country(String country) {
            this.country = country;
            return this;
        }

        public BillingDetailsRequestBuilder language(String language) {
            this.language = language;
            return this;
        }

        public BillingDetailsRequestBuilder cellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
            return this;
        }

        public BillingDetailsRequestBuilder legalName(String legalName) {
            this.legalName = legalName;
            return this;
        }

        public BillingDetailsRequestBuilder identityCode(String identityCode) {
            this.identityCode = identityCode;
            return this;
        }

        public BillingDetailsRequest build() {
            BillingDetailsRequest billingDetailsRequest = new BillingDetailsRequest();

            billingDetailsRequest.setAddress(address);
            billingDetailsRequest.setCellPhoneNumber(cellPhoneNumber);
            billingDetailsRequest.setCity(city);
            billingDetailsRequest.setCountry(country);
            billingDetailsRequest.setDistrict(district);
            billingDetailsRequest.setEmail(email);
            billingDetailsRequest.setFirstName(firstName);
            billingDetailsRequest.setIdentityCode(identityCode);
            billingDetailsRequest.setLanguage(language);
            billingDetailsRequest.setLastName(lastName);
            billingDetailsRequest.setLegalName(legalName);
            billingDetailsRequest.setPhoneNumber(phoneNumber);
            billingDetailsRequest.setReference(reference);
            billingDetailsRequest.setState(state);
            billingDetailsRequest.setStreetNumber(streetNumber);
            billingDetailsRequest.setTitle(title);
            billingDetailsRequest.setType(type);
            billingDetailsRequest.setZipCode(zipCode);
            
            return billingDetailsRequest;
        }
    }

    public static class ShippingDetailsRequestBuilder {

        private CustStatus type;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String streetNumber;
        private String address;
        private String address2;
        private String district;
        private String zipCode;
        private String city;
        private String state;
        private String country;
        private String deliveryCompanyName;
        private DeliverySpeed shippingSpeed;
        private DeliveryType shippingMethod;
        private String legalName;
        private String identityCode;

        public static ShippingDetailsRequestBuilder create() { return new ShippingDetailsRequestBuilder(); }

        public ShippingDetailsRequestBuilder type(CustStatus type) {
            this.type = type;
            return this;
        }

        public ShippingDetailsRequestBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ShippingDetailsRequestBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ShippingDetailsRequestBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ShippingDetailsRequestBuilder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public ShippingDetailsRequestBuilder address(String address) {
            this.address = address;
            return this;
        }

        public ShippingDetailsRequestBuilder address2(String address2) {
            this.address2 = address2;
            return this;
        }

        public ShippingDetailsRequestBuilder district(String district) {
            this.district = district;
            return this;
        }

        public ShippingDetailsRequestBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ShippingDetailsRequestBuilder city(String city) {
            this.city = city;
            return this;
        }

        public ShippingDetailsRequestBuilder state(String state) {
            this.state = state;
            return this;
        }

        public ShippingDetailsRequestBuilder country(String country) {
            this.country = country;
            return this;
        }

        public ShippingDetailsRequestBuilder deliveryCompanyName(String deliveryCompanyName) {
            this.deliveryCompanyName = deliveryCompanyName;
            return this;
        }

        public ShippingDetailsRequestBuilder shippingSpeed(DeliverySpeed shippingSpeed) {
            this.shippingSpeed = shippingSpeed;
            return this;
        }

        public ShippingDetailsRequestBuilder shippingMethod(DeliveryType shippingMethod) {
            this.shippingMethod = shippingMethod;
            return this;
        }

        public ShippingDetailsRequestBuilder legalName(String legalName) {
            this.legalName = legalName;
            return this;
        }

        public ShippingDetailsRequestBuilder identityCode(String identityCode) {
            this.identityCode = identityCode;
            return this;
        }

        public ShippingDetailsRequest build() {
            ShippingDetailsRequest shippingDetailsRequest = new ShippingDetailsRequest();

            shippingDetailsRequest.setAddress(address);
            shippingDetailsRequest.setAddress2(address2);
            shippingDetailsRequest.setCity(city);
            shippingDetailsRequest.setCountry(country);
            shippingDetailsRequest.setDeliveryCompanyName(deliveryCompanyName);
            shippingDetailsRequest.setDistrict(district);
            shippingDetailsRequest.setFirstName(firstName);
            shippingDetailsRequest.setIdentityCode(identityCode);
            shippingDetailsRequest.setLastName(lastName);
            shippingDetailsRequest.setLegalName(legalName);
            shippingDetailsRequest.setPhoneNumber(phoneNumber);
            shippingDetailsRequest.setShippingMethod(shippingMethod);
            shippingDetailsRequest.setShippingSpeed(shippingSpeed);
            shippingDetailsRequest.setState(state);
            shippingDetailsRequest.setStreetNumber(streetNumber);
            shippingDetailsRequest.setType(type);
            shippingDetailsRequest.setZipCode(zipCode);

            return shippingDetailsRequest;
        }
    }

    public static class ExtraDetailsRequestBuilder {

        private String ipAddress;
        private String fingerPrintId;

        public static ExtraDetailsRequestBuilder create() { return new ExtraDetailsRequestBuilder(); }

        public ExtraDetailsRequestBuilder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public ExtraDetailsRequestBuilder fingerPrintId(String fingerPrintId) {
            this.fingerPrintId = fingerPrintId;
            return this;
        }

        public ExtraDetailsRequest build() {
            ExtraDetailsRequest extraDetailsRequest = new ExtraDetailsRequest();

            extraDetailsRequest.setIpAddress(ipAddress);
            extraDetailsRequest.setFingerPrintId(fingerPrintId);

            return extraDetailsRequest;
        }
    }
}
