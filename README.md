![](https://img.shields.io/maven-central/v/eu.payzen.sdk/payzen-ws-sdk.svg)
![](https://img.shields.io/github/license/payzen/payzen-webservices-v5-sdk-java.svg)

# Payzen SDK SOAP Web Services v5 in Java #

This SDK simplifies the use of Web Services SOAP Payzen v5 in order to make payment operations. 

## Basic Usage ##
To use this SDK in your Java project:

If you use Maven or other software project management tool, you will find the dependency here:

http://mvnrepository.com/artifact/eu.payzen.sdk/payzen-ws-sdk/1.0.7

Instead, you can download manually the last version here:

http://mvnrepository.com/artifact/eu.payzen.sdk/payzen-ws-sdk/1.0.7/payzen-ws-sdk-1.0.7.jar

In this last case, you have to add manually the dependencies:

- *commons-codec-1.11.jar*
- *commons-lang-2.6.jar*
- *slf4j-api-1.7.25.jar*

Once all the dependencies of application are resolved, it is necessary to set up the config file.

In order to do that you have to create a resource file called *payzen-config.properties* into your classpath and set the following configuration params:

    shopId=[shop identifier]
    shopKey=[shop private key]
    mode=[mode TEST or PRODUCTION]
    endpointHost=[the name of the host. Ex: secure.payzen.eu]


Once the configuration is set up, use the web services is as simple as doing: 

    create("Test Order",
                100, 
                978,
                "4970100000000003",
                12,
                2017,
                "123",
                (result) -> "Payment Done"
    );
 
Really, it is that easy.



## More examples ##

**Create payment using builder objects** 

    OrderRequestBuilder orderRequestBuilder
        = OrderRequestBuilder
        .create()
        .orderId("TestTRS");

    PaymentRequestBuilder paymentRequestBuilder
        = PaymentRequestBuilder
        .create()
        .amount(100)
        .currency(978);

    CardRequestBuilder cardRequestBuilder
        = CardRequestBuilder
        .create()
        .number("4970100000000003")
        .scheme("CB")
        .expiryMonth(12)
        .expiryYear(2017)
        .cardSecurityCode("123");

    create(PaymentBuilder
        .getBuilder()
        .order(orderRequestBuilder.build())
        .payment(paymentRequestBuilder.build())
        .card(cardRequestBuilder.build())
        .comment("Test payment")
        .buildCreate(),
        (result) -> "Payment Done"
    );

**Override configuration for an operation call** 

    Map<String, String>  myConfig = new HashMap<>();
    //Change shop
    myConfig.put("shopId", "123456789");
    myConfig.put("shopKey", "XXXXXXXXXXXXXXXXXXXXX");
    create("Test Order",
                100, 
                978,
                "4970100000000003",
                12,
                2017,
                "123",
                (result) -> "Payment Done",
                myConfig
    );

**Log all the response**

    create("Test Order",
                100, 
                978,
                "4970100000000003",
                12,
                2017,
                "123",
                new LogResponseHandler()
    );


**Use annonymous functions as callback handlers (needed for Java < 8)**


    create("Test Order",
                100, 
                978,
                "4970100000000003",
                12,
                2017,
                "123",
                new ResponseHandler() {
		            @Override
		            public void handle(ServiceResult result) throws Exception {
		                //Process response here
		            }
		        });
    );


**Handle the response in a classic way, not using callbacks**

    ServiceResult result = create("Test Order",
                100, 
                978,
                "4970100000000003",
                12,
                2017,
                "123"
    );

    //Process payment result using getters

## How to build the library ##


- Install Maven: https://maven.apache.org/install.html

- Download and import the project into your IDE or simply clone it from Git.

- First time run from root directory: *mvn -Pgenerate-ws-stubs install*. This will generate/update all the web service stubs.

- From that time, it is enough to execute *mvn install* each time to regenerate the library.
