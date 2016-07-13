
package com.lyra.vads.ws.v5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createToken complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createToken">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commonRequest" type="{http://v5.ws.vads.lyra.com/}commonRequest" minOccurs="0"/>
 *         &lt;element name="cardRequest" type="{http://v5.ws.vads.lyra.com/}cardRequest" minOccurs="0"/>
 *         &lt;element name="customerRequest" type="{http://v5.ws.vads.lyra.com/}customerRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createToken", propOrder = {
    "commonRequest",
    "cardRequest",
    "customerRequest"
})
public class CreateToken {

    protected CommonRequest commonRequest;
    protected CardRequest cardRequest;
    protected CustomerRequest customerRequest;

    /**
     * Gets the value of the commonRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CommonRequest }
     *     
     */
    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    /**
     * Sets the value of the commonRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonRequest }
     *     
     */
    public void setCommonRequest(CommonRequest value) {
        this.commonRequest = value;
    }

    /**
     * Gets the value of the cardRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CardRequest }
     *     
     */
    public CardRequest getCardRequest() {
        return cardRequest;
    }

    /**
     * Sets the value of the cardRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardRequest }
     *     
     */
    public void setCardRequest(CardRequest value) {
        this.cardRequest = value;
    }

    /**
     * Gets the value of the customerRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerRequest }
     *     
     */
    public CustomerRequest getCustomerRequest() {
        return customerRequest;
    }

    /**
     * Sets the value of the customerRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerRequest }
     *     
     */
    public void setCustomerRequest(CustomerRequest value) {
        this.customerRequest = value;
    }

}
