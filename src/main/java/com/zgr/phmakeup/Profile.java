/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.List;
import org.cactoos.list.ListOf;

/**
 * Msisdn profile.
 * @since 1.0
 */
public interface Profile {

    /**
     * Russian msisdn profile.
     * @since 1.0
     */
    Profile RU = () -> new ListOf<>(new RuFormat());

    /**
     * Profile, limited only by maximum msisdn length. Corresponds to mobicont
     * ValidMSISDNFormat.NO_CHECK_FORMAT_ID.
     * @since 1.0
     * @checkstyle MagicNumberCheck (5 lines)
     */
    Profile UNLIMITED = () -> new ListOf<>(Format.NO_CHECK);

    /**
     * Returns allowed formats for profile.
     * @return Iterable of {@link FormatFor}
     */
    List<Format> formats();
}
