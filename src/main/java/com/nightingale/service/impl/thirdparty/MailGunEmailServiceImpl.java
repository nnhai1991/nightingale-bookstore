package com.nightingale.service.impl.thirdparty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightingale.service.MailGunEmailService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@Service
public class MailGunEmailServiceImpl implements MailGunEmailService {

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    private ObjectMapper objectMapper;

    public MailGunEmailServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public Boolean sendEmail(String recipient, String subject, String content) {

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", apiKey));

        WebResource webResource = client.resource("https://api.mailgun.net/v3/" + domain + "/messages");

        MultivaluedMapImpl map = new MultivaluedMapImpl();
        map.add("from", "Nightingale Bookstore <contact@www.nightingale.sg>");
        map.add("to", recipient);
        map.add("subject", subject);
        map.add("html", content);
        map.add("text",content);

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                                                   .post(ClientResponse.class, map);

        return clientResponse.getStatus() == HttpStatus.OK.value();


    }
}
