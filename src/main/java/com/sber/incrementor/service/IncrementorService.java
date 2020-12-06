package com.sber.incrementor.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service providing logic for incrementing counter value in
 * a thread safe manner.
 */
@Service
public class IncrementorService implements IIncrementor {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicInteger maxValue = new AtomicInteger(Integer.MAX_VALUE);

    @Override
    public int getNumber() {
        return counter.get();
    }

    public int getMaximumValue() {
        return maxValue.get();
    }

    @Override
    public void incrementNumber() {
        while(true) {
            int existingValue = getNumber();
            int newValue = existingValue + 1;
            if(counter.compareAndSet(existingValue, newValue)) {
                if(getNumber() > getMaximumValue()) {
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
            int existingValue = getMaximumValue();
            if(maxValue.compareAndSet(existingValue, newMaxValue)) {
                if(getNumber() > getMaximumValue()) {
                    setCurrentNumberToZero();
                }
                return;
            }
        }
    }

    /**
     * Setting current value of the counter to zero. Can be used when
     * maximum value is reached.
     */
    private void setCurrentNumberToZero() {
        while(true) {
            int currentValue = getNumber();
            if(counter.compareAndSet(currentValue, 0)) {
                return;
            }
        }
    }
}
