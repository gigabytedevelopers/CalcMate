/*
 * Calculator Begins
 * Copyright (C) 2017  Adithya J
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.xlythe.math;

import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

/**
 * Created by Will on 11/8/2014.
 */
public class Constants {
    public static final String INFINITY_UNICODE = "\u221e";
    // Double.toString() for Infinity
    public static final String INFINITY = "Infinity";
    // Double.toString() for NaN
    public static final String NAN = "NaN";
    public static final char MINUS = '\u2212';
    public static final char MUL = '*';
    //DONE : public static final char MUL = '\u00d7';
    public static final char PLUS = '+';
    public static final char DIV = '/';
    //DONE : public static final char DIV = '\u00f7';
    public static final char POWER_PLACEHOLDER = '\u200B';
    public static final char POWER = '^';
    public static final char EQUAL = '=';
    public static final char LEFT_PAREN = '(';
    public static final char RIGHT_PAREN = ')';
    public static char DECIMAL_POINT;
    public static char DECIMAL_SEPARATOR;
    public static char BINARY_SEPARATOR;
    public static char OCTAL_SEPARATOR;
    public static char HEXADECIMAL_SEPARATOR;
    public static char MATRIX_SEPARATOR;
    public static String REGEX_NUMBER;
    public static String REGEX_NOT_NUMBER;
    // Values for decimals and comas
    private static DecimalFormatSymbols DECIMAL_FORMAT;

    static {
        rebuildConstants();
    }

    /**
     * If the locale changes, but the app is still in memory, you may need to rebuild these constants
     */
    public static void rebuildConstants() {
        DECIMAL_FORMAT = new DecimalFormatSymbols();

        // These will already be known by Java
        DECIMAL_POINT = DECIMAL_FORMAT.getDecimalSeparator();
        DECIMAL_SEPARATOR = DECIMAL_FORMAT.getGroupingSeparator();

        // Use a space for Bin and Hex
        BINARY_SEPARATOR = ' ';
        OCTAL_SEPARATOR = ' ';
        HEXADECIMAL_SEPARATOR = ' ';

        // We have to be careful with the Matrix Separator.
        // It defaults to "," but that's a common decimal point.
        if (DECIMAL_POINT == ',') MATRIX_SEPARATOR = ' ';
        else MATRIX_SEPARATOR = ',';

        String number = "A-F0-9" +
                Pattern.quote(String.valueOf(DECIMAL_POINT)) +
                Pattern.quote(String.valueOf(DECIMAL_SEPARATOR)) +
                Pattern.quote(String.valueOf(BINARY_SEPARATOR)) +
                Pattern.quote(String.valueOf(OCTAL_SEPARATOR)) +
                Pattern.quote(String.valueOf(HEXADECIMAL_SEPARATOR));

        REGEX_NUMBER = "[" + number + "]";
        REGEX_NOT_NUMBER = "[^" + number + "]";
    }
}
