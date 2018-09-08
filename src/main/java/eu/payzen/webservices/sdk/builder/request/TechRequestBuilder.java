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

import com.lyra.vads.ws.v5.TechRequest;

/**
 * Builder class to help in the construction of a TechRequest
 * 
 * @author Javier Garcia Alonso
 */
public class TechRequestBuilder {
    private String browserUserAgent;
    private String browserAccept;
    private String integrationType;

    public TechRequestBuilder browserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
        return this;
    }

    public TechRequestBuilder browserAccept(String browserAccept) {
        this.browserAccept = browserAccept;
        return this;
    }

    public TechRequestBuilder integrationType(String integrationType) {
        this.integrationType = integrationType;
        return this;
    }
    
    public TechRequest build() {
        TechRequest techRequest = new TechRequest();
        
        techRequest.setBrowserAccept(browserAccept);
        techRequest.setBrowserUserAgent(browserUserAgent);
        techRequest.setIntegrationType(integrationType);
        
        return techRequest;
    }
}
