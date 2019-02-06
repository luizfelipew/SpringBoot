package br.com.dev.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;


@ControllerAdvice
public class RestResponseExceptionHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        System.out.println("Inside hasError");
        return super.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        System.out.println("Doing something with status code " + clientHttpResponse.getStatusCode());
        System.out.println("Doing something with body " + IOUtils.toString(clientHttpResponse.getBody(), "UTF-8"));
//        super.handleError(clientHttpResponse);
    }
}
