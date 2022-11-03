package org.acme;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import io.micrometer.core.annotation.Timed;

@ApplicationScoped
public class TestFunction {
    @Timed(value = "my-computation-timer", extraTags = {"name", "computation-timer"}, histogram = true, unit = MetricUnits.MILLISECONDS)
    public void loadAction() throws InterruptedException {
        Random duration = new Random();
        Integer millis = duration.nextInt(2567);
        Thread.sleep(millis);
    }
}