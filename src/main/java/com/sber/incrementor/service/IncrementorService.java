package com.sber.incrementor.service;

import com.sber.incrementor.exception.model.InvalidMaxValueException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service providing logic for incrementing counter value in
 * a thread safe manner.
 */
@Service
public class IncrementorService implements IIncrementor {

    private AtomicInteger counter = new AtomicInteger(0);
    private AtomicInteger maxValue = new AtomicInteger(Integer.MAX_VALUE);

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
            if(getNumber() >= getMaximumValue()) {
                setCurrentNumberToZero();
                return;
            }
            if(counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    @Override
    public void setMaximumValue(int newMaxValue) {

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
