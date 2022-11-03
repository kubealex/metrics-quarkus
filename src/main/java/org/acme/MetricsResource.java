package org.acme;

import java.time.Duration;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

@Path("/")
public class MetricsResource {

    @Inject
    MeterRegistry registry;
    @Inject
    TestFunction testFunction;

    @GET
    @Path("/run")
    public String execute() throws InterruptedException {
        testFunction.loadAction();
        // Random duration = new Random();
        // Integer millis = duration.nextInt(5000);
        // Timer timer = Timer.builder("my-awesome-timer").tag("name", "sample").publishPercentiles(0.95).publishPercentileHistogram(true).register(registry);
        // timer.record(Duration.ofMillis(millis));
        return "OK";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}