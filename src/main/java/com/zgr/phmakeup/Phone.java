/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

/**
 * Phone number (msisdn).
 * @since 1.0
 */
public interface Phone {

    /**
     * Makeups phone numbers.
     * @return Clean phone number.
     * @throws IllegalArgumentException if makeup is not possible
     */
    String makeup();

}
