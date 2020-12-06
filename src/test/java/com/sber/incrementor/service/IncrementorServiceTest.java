package com.sber.incrementor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncrementorServiceTest {

    private IncrementorService incrementorService;

    @BeforeEach
    void init() {
        incrementorService = new IncrementorService();
    }

    @Test
    void shouldGetNumber() {
        assertEquals(0, incrementorService.getNumber());
    }

    @Test
    void shouldIncrementNumber() {
        var initialValue = incrementorService.getNumber();
        incrementorService.incrementNumber();

        assertEquals(1, incrementorService.getNumber() - initialValue);
    }

    @Test
    void shouldSetMaximumValue() {
        incrementorService.setMaximumValue(3);

        assertEquals(3, incrementorService.getMaximumValue());
    }

    @Test
    void shouldSetNumberToZero() {
        incrementorService.setMaximumValue(2);
        incrementorService.incrementNumber();
        incrementorService.incrementNumber();
        incrementorService.incrementNumber();

        assertEquals(0, incrementorService.getNumber());
    }

    @Test
    void shouldSetNumberToZeroWhenCurrentValueIsGreater() {
        incrementorService.incrementNumber();
        incrementorService.incrementNumber();
        incrementorService.incrementNumber();
        incrementorService.setMaximumValue(2);

        assertEquals(0, incrementorService.getNumber());
    }

    @Test
    void shouldSetMaxValuePlusOne() {
        ReflectionTestUtils.setField(incrementorService, "counter", new AtomicInteger(Integer.MAX_VALUE));
        incrementorService.incrementNumber();

        assertEquals(0, incrementorService.getNumber());
    }

    @Test
    void shouldSetMaxValue() {
        ReflectionTestUtils.setField(incrementorService, "counter", new AtomicInteger(Integer.MAX_VALUE - 1));
        incrementorService.incrementNumber();

        assertEquals(Integer.MAX_VALUE, incrementorService.getNumber());
    }

    @Test
    void shouldSetCorrectValue() throws InterruptedException {
        var threadsList = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threadsList.add(new Thread(() -> incrementorService.incrementNumber()));
        }

        for (Thread t : threadsList) {
            startThread(t);
        }

        for (Thread t : threadsList) {
            joinThread(t);
        }

        assertEquals(10, incrementorService.getNumber());
    }

    private void startThread(Thread thread5) {
        thread5.start();
    }

    private void joinThread(Thread thread5) throws InterruptedException {
        thread5.join();
    }
}
