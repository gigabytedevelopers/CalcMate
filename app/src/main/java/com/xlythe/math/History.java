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
import java.util.LinkedList;
import java.util.List;

public class History {
    private static final int VERSION_1 = 1;
    private static final int VERSION_4 = 4;
    private static final int MAX_ENTRIES = 100;
    private List<HistoryEntry> mEntries = new LinkedList<HistoryEntry>();
    private int mPos;
    private int mGroupId;
    private Observer mObserver;

    History() {
        clear();
    }

    History(int version, DataInput in) throws IOException {
        if (version >= VERSION_1) {
            int size = in.readInt();
            for (int i = 0; i < size; ++i) {
                mEntries.add(new HistoryEntry(version, in));
            }
            mPos = in.readInt();
        }
        if (version >= VERSION_4) {
            mGroupId = in.readInt();
        }
    }

    public void clear() {
        mEntries.clear();
        mPos = -1;
        mGroupId = 0;
        notifyChanged();
    }

    private void notifyChanged() {
        if (mObserver != null) {
            mObserver.notifyDataSetChanged();
        }
    }

    public void setObserver(Observer observer) {
        mObserver = observer;
    }

    void write(DataOutput out) throws IOException {
        out.writeInt(mEntries.size());
        for (HistoryEntry entry : mEntries) {
            entry.write(out);
        }
        out.writeInt(mPos);
        out.writeInt(mGroupId);
    }

    public HistoryEntry current() {
        if (mPos >= mEntries.size()) {
            mPos = mEntries.size() - 1;
        }
        if (mPos == -1) {
            return null;
        }
        return mEntries.get(mPos);
    }

    public void enter(String formula, String result) {
        if (mEntries.size() >= MAX_ENTRIES) {
            mEntries.remove(0);
        }
        mEntries.add(new HistoryEntry(formula, result, mGroupId));
        mPos = mEntries.size() - 1;
        notifyChanged();
    }

    public void incrementGroupId() {
        ++mGroupId;
    }

    public String getText() {
        return current().getResult();
    }

    public String getBase() {
        return current().getFormula();
    }

    public void remove(HistoryEntry he) {
        mEntries.remove(he);
        mPos--;
    }

    public List<HistoryEntry> getEntries() {
        return mEntries;
    }

    public interface Observer {
        void notifyDataSetChanged();
    }
}
