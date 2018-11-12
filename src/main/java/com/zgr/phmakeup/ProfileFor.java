/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;

/**
 * Profile for given description. Descriptor format:
 * length,country_code;length,country_code;...
 *
 * Country code can be skipped.
 * For example: 11,7;11;12,380;12,375;12;13
 * @since 1.0
 */
public final class ProfileFor implements Profile {

    /**
     * Profile description.
     */
    private final String description;

    /** Ctor.
     * @param description Profile description
     */
    public ProfileFor(@NotNull final String description) {
        this.description = description;
    }

    @Override
    public List<Format> formats() {
        return new Mapped<>(
            ProfileFor::parse,
            new IterableOf<>(this.description.split(";"))
        );
    }

    /**
     * Parses string into {@link Format}.
     * @param frm String format representation to parse
     * @return Returns {@link Format} for given string
     * @throws NumberFormatException if string representation is invalid
     */
    private static Format parse(@NotNull final String frm) {
        final Format res;
        final int comma = frm.indexOf(',');
        if (comma < 0) {
            res = new FormatFor(Integer.parseInt(frm), "");
        } else if ("11,7".equals(frm)) {
            res = new RuFormat();
        } else if ("12,994".equals(frm)) {
            res = new AzFormat();
        } else {
            res = new FormatFor(
                Integer.parseInt(frm.substring(0, comma)),
                frm.substring(comma + 1)
            );
        }
        return res;
    }
}
