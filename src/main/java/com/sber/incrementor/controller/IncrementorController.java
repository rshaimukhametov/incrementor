package com.sber.incrementor.controller;

import com.sber.incrementor.exception.model.InvalidMaxValueException;
import com.sber.incrementor.service.IncrementorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller providing endpoints for working with {@link IncrementorService}.
 * Description of the underlying logic can be checked in service documentation.
 */
@RestController
@RequestMapping("incrementor")
@RequiredArgsConstructor
public class IncrementorController {

    private static final String MAX_VALUE_EXCEPTION_MESSAGE = "Maximum value can not be less than 0!";

    private final IncrementorService incrementorService;

    @GetMapping(value = "/number", produces = "application/json")
    public int getNumber() {
        return incrementorService.getNumber();
    }

    @PutMapping(value = "/increment", produces = "application/json")
    public void incrementNumber() {
        incrementorService.incrementNumber();
    }

    @PutMapping(value = "/maximum", produces = "application/json")
    public void setMaximumValue(@RequestParam int newMaxValue) throws InvalidMaxValueException {
        if(newMaxValue < 0) {
            throw new InvalidMaxValueException(MAX_VALUE_EXCEPTION_MESSAGE);
        }

        incrementorService.setMaximumValue(newMaxValue);
    }
}
