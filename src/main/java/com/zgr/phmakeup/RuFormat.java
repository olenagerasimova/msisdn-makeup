/*
 * Copyright (c) 2018 Zagruzka
 */
package com.zgr.phmakeup;

import javax.validation.constraints.NotNull;

/**
 * Russian phone numbers format descriptor.
 * @since 1.0
 */
final class RuFormat implements Format {

    @Override
    public @NotNull String prefix() {
        return "7";
    }

    @Override
    public int len() {
        //@checkstyle MagicNumberCheck (1 line)
        return 11;
    }

    @Override
    public boolean suits(final String number) {
        boolean res = false;
        if (number.length() == this.len() && number.startsWith(this.prefix())) {
            res = true;
        } else if (number.length() == this.len() - this.prefix().length()) {
            res = true;
        } else if (number.length() == this.len() && number.charAt(0) == '8') {
            res = true;
        }
        return res;
    }

    @Override
    public String format(final String number) {
        if (this.suits(number)) {
            final String res;
            if (number.length() == this.len() - this.prefix().length()) {
                res = this.prefix() + number;
            } else if (number.charAt(0) == '8') {
                res = this.prefix() + number.substring(1);
            } else {
                res = number;
            }
            return res;
        }
        throw new IllegalArgumentException(
            String.format(
                "Phone number %s does not suit Russian format", number
            )
        );
    }
}
