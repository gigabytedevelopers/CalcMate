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

/**
 * A super class for BaseModule, GraphModule, MatrixModule
 */
public class Module {
    // Used whenever math is necessary
    private final Solver mSolver;

    // Used for formatting Dec, Bin, and Hex.
    // Dec looks like 1,234,567. Bin is 1010 1010. Hex is 0F 1F 2F.
    private final int mDecSeparatorDistance = 3;
    private final int mBinSeparatorDistance = 4;
    private final int mOctSeparatorDistance = 3;
    private final int mHexSeparatorDistance = 2;

    Module(Solver solver) {
        mSolver = solver;
    }

    public int getDecSeparatorDistance() {
        return mDecSeparatorDistance;
    }

    public int getBinSeparatorDistance() {
        return mBinSeparatorDistance;
    }

    public int getOctSeparatorDistance() {
        return mOctSeparatorDistance;
    }

    public int getHexSeparatorDistance() {
        return mHexSeparatorDistance;
    }

    public char getDecimalPoint() {
        return Constants.DECIMAL_POINT;
    }

    public char getDecSeparator() {
        return Constants.DECIMAL_SEPARATOR;
    }

    public char getBinSeparator() {
        return Constants.BINARY_SEPARATOR;
    }

    public char getOctSeparator() {
        return Constants.OCTAL_SEPARATOR;
    }

    public char getHexSeparator() {
        return Constants.HEXADECIMAL_SEPARATOR;
    }

    public char getMatrixSeparator() {
        return Constants.MATRIX_SEPARATOR;
    }

    public Solver getSolver() {
        return mSolver;
    }
}
