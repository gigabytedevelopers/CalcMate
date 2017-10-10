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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HistoryEntry {
    private static final int VERSION_1 = 1;
    private static final int VERSION_4 = 4;
    private String mFormula;
    private String mResult;
    private int mGroupId;

    public HistoryEntry(String formula, String result, int groupId) {
        mFormula = formula;
        mResult = result;
        mGroupId = groupId;
    }

    HistoryEntry(int version, DataInput in) throws IOException {
        if (version >= VERSION_1) {
            mFormula = in.readUTF();
            mResult = in.readUTF();
        }
        if (version >= VERSION_4) {
            mGroupId = in.readInt();
        }
    }

    void write(DataOutput out) throws IOException {
        out.writeUTF(mFormula);
        out.writeUTF(mResult);
        out.writeInt(mGroupId);
    }

    @Override
    public String toString() {
        return mFormula;
    }

    public String getResult() {
        return mResult;
    }

    void setResult(String result) {
        mResult = result;
    }

    public String getFormula() {
        return mFormula;
    }

    public int getGroupId() {
        return mGroupId;
    }

    void setGroupId(int groupId) {
        mGroupId = groupId;
    }
}
