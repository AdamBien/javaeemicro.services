package com.airhacks.ping.client.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class PingService {

    private WebTarget pingTarget;

    @PostConstruct
    public void initClient() {
        Client newClient = ClientBuilder.newClient();
        this.pingTarget = newClient.target(uri());
    }

    //http://192.168.99.100:8282/ping/resources/pings/echo/+
    String uri() {
        String hostName = System.getenv("PING_PORT_8080_TCP_ADDR");
        String port = System.getenv("PING_PORT_8080_TCP_PORT");
        return "http://" + hostName + ":" + port + "/ping/resources/pings/echo/+";
    }

    public String message() {
        return this.pingTarget.
                request().
                get(String.class);
    }

}
