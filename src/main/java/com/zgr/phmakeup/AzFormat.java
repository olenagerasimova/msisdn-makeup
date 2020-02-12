package com.zgr.phmakeup;

import javax.validation.constraints.NotNull;

/**
 * Azerbaijan phone numbers format descriptor.
 * @since 1.0
 */
final class AzFormat implements Format {

    @Override
    public @NotNull String prefix() {
        return "994";
    }

    @Override
    public int len() {
        //@checkstyle MagicNumberCheck (1 line)
        return 12;
    }

    @Override
    public boolean suits(final String number) {
        boolean res = false;
        if (number.length() == this.len() && number.startsWith(this.prefix())) {
            res = true;
        } else if (number.length() == this.len() - this.prefix().length()) {
            res = true;
            //@checkstyle MagicNumberCheck (1 line)
        } else if (number.length() == 10 && number.charAt(0) == '0') {
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
            } else if (number.charAt(0) == '0') {
                res = this.prefix() + number.substring(1);
            } else {
                res = number;
            }
            return res;
        }
        throw new IllegalArgumentException(
            String.format(
                "Phone number %s does not suit Azerbaijan format", number
            )
        );
    }
}
