
package com.android2.calculator3;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HistoryEntry {
    private static final int VERSION_1 = 1;
    private String mBase;
    private String mEdited;

    HistoryEntry(String base, String edited) {
        mBase = base;
        mEdited = edited;
    }

    HistoryEntry(int version, DataInput in) throws IOException {
        if(version >= VERSION_1) {
            mBase = in.readUTF();
            mEdited = in.readUTF();
        }
        else {
            throw new IOException("invalid version " + version);
        }
    }

    void write(DataOutput out) throws IOException {
        out.writeUTF(mBase);
        out.writeUTF(mEdited);
    }

    @Override
    public String toString() {
        return mBase;
    }

    void clearEdited() {
        mEdited = mBase;
    }

    public String getEdited() {
        return mEdited;
    }

    void setEdited(String edited) {
        mEdited = edited;
    }

    public String getBase() {
        return mBase;
    }
}
