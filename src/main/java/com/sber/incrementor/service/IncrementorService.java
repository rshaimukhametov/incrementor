package com.sber.incrementor.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IncrementorService implements IIncrementor {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicInteger maxValue = new AtomicInteger(Integer.MAX_VALUE);

    @Override
    public int getNumber() {
        return counter.get();
    }

    @Override
    public void incrementNumber() {
        while(true) {
            int existingValue = getNumber();
            int newValue = existingValue + 1;
            if(counter.compareAndSet(existingValue, newValue)) {
                if(getNumber() > maxValue.get()) {
                    setCurrentNumberToZero();
                }
                return;
            }
        }
    }

    @Override
    public void setMaximumValue(int newMaxValue) {
        if(newMaxValue < 0) {
            return;
        }

        while(true) {
            int existingValue = maxValue.get();
            if(maxValue.compareAndSet(existingValue, newMaxValue)) {
                if(getNumber() > maxValue.get()) {
                    setCurrentNumberToZero();
                }
                return;
            }
        }
    }

    private void setCurrentNumberToZero() {
        while(true) {
            int currentValue = getNumber();
            if(counter.compareAndSet(currentValue, 0)) {
                return;
            }
        }
    }
}
