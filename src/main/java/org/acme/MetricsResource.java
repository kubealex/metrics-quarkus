package org.acme;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/")
public class MetricsResource {
    private static final Logger LOG = Logger.getLogger(MetricsResource.class);

    @Inject
    TestFunction testFunction;

    @GET
    @Path("/run")
    public String execute() throws InterruptedException {
        testFunction.loadAction();
        LOG.info("Traced");
        return "OK";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}