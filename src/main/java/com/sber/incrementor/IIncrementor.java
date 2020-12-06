package com.sber.incrementor;

/**
 * Defines methods for working with simple incrementor which has
 * current value and maximum value.
 */
public interface IIncrementor {

    /**
     * Returns current value of the counter considering changes made
     * by other threads. Value of the counter is always positive
     * or equals to zero. By default, maximum possible value
     * equals to {@link Integer} max value.
     *
     * @return current value of the counter
     */
    int getNumber();

    /**
     * Increments counter value by 1. In case increment is done
     * and current value becomes more than maximum value, value of the
     * counter is set to zero.
     */
    void incrementNumber();

    /**
     * Sets maximum value of the counter. By default, maximum value
     * equals to {@link Integer} max value. In case after setting
     * new maximum value current value becomes more that it, current
     * value of the counter will be set to zero.
     *
     * @param maximumValue maximum value of the counter to be set.
     *                     Can not be less than zero. In case negative
     *                     number is passed, method will do nothing.
     */
    void setMaximumValue(int maximumValue);
}
