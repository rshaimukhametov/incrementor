package com.sber.incrementor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
