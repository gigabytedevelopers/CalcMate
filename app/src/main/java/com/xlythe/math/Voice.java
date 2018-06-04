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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse spoken text (eg. sign as sin) or speak spoken text (eg. *  as multiplied by)
 */
public class Voice {
    public static String parseSpokenText(String text) {
        if (Locale.getDefault().equals(Locale.ENGLISH)) {
            List<String> exceptions = new LinkedList<String>();
            text = text.toLowerCase(Locale.ENGLISH);
            text = text.replace("percent", "%");
            text = text.replace("point", ".");
            text = text.replace("minus", "-");
            text = text.replace("plus", "+");
            text = text.replace("divided", "/");
            text = text.replace("over", "/");
            text = text.replace("times", "*");
            text = text.replace("x", "*");
            text = text.replace("multiplied", "*");
            text = text.replace("raise", "^");
            text = text.replace("square root", "sqrt(");
            exceptions.add("sqrt");
            text = text.replace("sign", "sin(");
            exceptions.add("sin");
            text = text.replace("cosine", "cos(");
            exceptions.add("cos");
            text = text.replace("tangent", "tan(");
            exceptions.add("tan");
            text = text.replace("pie", "\u03C0");
            text = text.replace("pi", "\u03C0");
            text = text.replace(" ", "");
            text = SpellContext.replaceAllWithNumbers(text);
            text = removeChars(text, exceptions);
            text = EquationFormatter.appendParenthesis(text);
            return text;
        } else {
            return text;
        }
    }

    private static String removeChars(String input, List<String> exceptions) {
        Pattern pattern = Pattern.compile("[a-z']");
        String text = "";
        for (int i = 0; i < input.length(); i++) {
            for (String ex : exceptions) {
                if (input.substring(i).startsWith(ex)) {
                    text += input.substring(i, i + ex.length());
                    i += ex.length();
                    continue;
                }
            }

            // Check for characters that don't belong
            Matcher matcher = pattern.matcher(input.substring(i, i + 1));
            if (!matcher.matches()) text += input.substring(i, i + 1);
        }
        return text;
    }

    public static String createSpokenText(String text) {
        if (Locale.getDefault().equals(Locale.ENGLISH)) {
            if (text.startsWith(String.valueOf(Constants.MINUS))) {
                // Speech can't say "-1". It says "1" instead.
                text = "Negative " + text.substring(1);
            }
            text = text.replace("-", " minus ");
            text = text.replace("*", " times ");
            text = text.replace("*", " times ");
            text = text.replace("sin", " sine of ");
            text = text.replace("cos", " cosine of ");
            text = text.replace("tan", " tangent of ");
            text = text.replace("sqrt", " square root of ");
            text = text.replace("^", " raised to ");
            return text;
        } else {
            return text;
        }
    }
}
