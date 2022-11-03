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

@Path("/hello")
public class GreetingResource {

    @Inject
    MeterRegistry registry;

    @GET
    //@Timed(histogram = true)
    @Path("/exec")
    public String execute() throws InterruptedException {
        Random duration = new Random();
        Integer millis = duration.nextInt(4000);
        Timer timer = Timer.builder("my-awesome-timer").tag("name", "sample").publishPercentiles(0.95).publishPercentileHistogram(true).register(registry);
        timer.record(Duration.ofMillis(millis));
        // timer.record(Duration.ofMillis(1109));
        // timer.record(Duration.ofMillis(8909));
        // timer.record(Duration.ofMillis(909));
        //registry.timer("testings-timer", Tags.of("name", "timer")).record(Duration.ofMillis(1000));
        return "OK";
        // registry.timer("correlation_timer", Tags.of("name", "timer")).record(Duration.ofMillis(timeInMillis));
        // return new String("{\"exec\": \"success\", \"time\": " + timeInMillis + "}");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}