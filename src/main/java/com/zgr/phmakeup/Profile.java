/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import org.cactoos.list.ListOf;

/**
 * Msisdn profile.
 * @since 1.0
 */
public interface Profile {

    /**
     * Russian msisdn profile.
     * @since 1.0
     * @checkstyle MagicNumberCheck (5 lines)
     */
    Profile RU = () -> new ListOf<>(
        new Format(11, "7")
    );

    /**
     * Profile, limited only by maximum msisdn length. Corresponds to mobicont
     * ValidMSISDNFormat.NO_CHECK_FORMAT_ID.
     * @since 1.0
     * @checkstyle MagicNumberCheck (5 lines)
     */
    Profile UNLIMITED = () -> new ListOf<>(
        new Format(15, "")
    );

    /**
     * Returns allowed formats for profile.
     * @return Iterable of {@link Format}
     */
    Iterable<Format> formats();
}
