package com.sber.incrementor.controller;

import com.sber.incrementor.service.IncrementorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incrementor")
@RequiredArgsConstructor
public class IncrementorController {

    private final IncrementorService incrementorService;

    @GetMapping(value = "/number", produces = "application/json")
    public int getNumber() {
        return incrementorService.getNumber();
    }

    @PutMapping(value = "/increment", produces = "application/json")
    public void incrementNumber() {
        incrementorService.incrementNumber();
    }

    @PutMapping(value = "/maximum/{newMaxValue}", produces = "application/json")
    public void setMaximumValue(@PathVariable int newMaxValue) {
        incrementorService.setMaximumValue(newMaxValue);
    }
}
