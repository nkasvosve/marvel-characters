package com.acme.service;


import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author nickk
 */

@Component
public class AddressAndPortPrinter implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    @Override
    public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
        int port = event.getEmbeddedServletContainer().getPort();

        System.out.println("\n");
        System.out.println("****************** ON PORT: " + port + " ******************");
        System.out.println("******************REST: http://<HOST>:" + port + "/swagger-ui.html******************");
        System.out.println("******************REST: http://<HOST>:" + port + "/v2/api-docs  ******************");

        System.out.println("\n");
        System.out.println("******************CXF:  http://<HOST>:" + port + "/services ******************");
        System.out.println("******************SOAP: http://<HOST>:" + port + "/services/characters?wsdl  ******************");
        System.out.println("\n\n");
    }
}