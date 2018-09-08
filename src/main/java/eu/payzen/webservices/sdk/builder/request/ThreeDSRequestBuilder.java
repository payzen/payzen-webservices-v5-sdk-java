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

import com.lyra.vads.ws.v5.ThreeDSMode;
import com.lyra.vads.ws.v5.ThreeDSRequest;

/**
 * Builder class to help in the construction of a ThreeDSRequest
 * 
 * @author Javier Garcia Alonso
 */
public class ThreeDSRequestBuilder {

    private ThreeDSMode mode;
    private String requestId;
    private String pares;
    private String brand;
    private String enrolled;
    private String status;
    private String eci;
    private String xid;
    private String cavv;
    private String algorithm;
    
    private ThreeDSRequestBuilder() {
    }
  
    public static ThreeDSRequestBuilder create() {
        return new ThreeDSRequestBuilder();
    }

    public ThreeDSRequestBuilder mode(ThreeDSMode mode) {
        this.mode = mode;
        return this;
    }

    public ThreeDSRequestBuilder requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ThreeDSRequestBuilder pares(String pares) {
        this.pares = pares;
        return this;
    }
    
    public ThreeDSRequestBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public ThreeDSRequestBuilder enrolled(String enrolled) {
        this.enrolled = enrolled;
        return this;
    }

    public ThreeDSRequestBuilder status(String status) {
        this.status = status;
        return this;
    }

    public ThreeDSRequestBuilder eci(String eci) {
        this.eci = eci;
        return this;
    }

    public ThreeDSRequestBuilder xid(String xid) {
        this.xid = xid;
        return this;
    }

    public ThreeDSRequestBuilder cavv(String cavv) {
        this.cavv = cavv;
        return this;
    }

    public ThreeDSRequestBuilder algorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public ThreeDSRequest build() {
        ThreeDSRequest threeDSRequest = new ThreeDSRequest();

        threeDSRequest.setAlgorithm(algorithm);
        threeDSRequest.setBrand(brand);
        threeDSRequest.setCavv(cavv);
        threeDSRequest.setEci(eci);
        threeDSRequest.setEnrolled(enrolled);
        threeDSRequest.setMode(mode);
        threeDSRequest.setPares(pares);
        threeDSRequest.setRequestId(requestId);
        threeDSRequest.setStatus(status);
        threeDSRequest.setXid(xid);

        return threeDSRequest;
    }
}