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

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Persist {
    private static final String TAG = Persist.class.getSimpleName();
    private static final int LAST_VERSION = 4;
    private static final String FILE_NAME = "calculator.data";
    private final Context mContext;
    History mHistory = new History();
    private int mDeleteMode;
    private Base mMode;

    public Persist(Context context) {
        this.mContext = context;
    }

    public int getDeleteMode() {
        return mDeleteMode;
    }

    public void setDeleteMode(int mode) {
        mDeleteMode = mode;
    }

    public Base getMode() {
        return mMode;
    }

    public void setMode(Base mode) {
        this.mMode = mode;
    }

    public void load() {
        try {
            InputStream is = new BufferedInputStream(mContext.openFileInput(FILE_NAME), 8192);
            DataInputStream in = new DataInputStream(is);
            int version = in.readInt();
            if (version > LAST_VERSION) {
                throw new IOException("data version " + version + "; expected " + LAST_VERSION);
            }
            if (version > 1) {
                mDeleteMode = in.readInt();
            }
            if (version > 2) {
                int quickSerializable = in.readInt();
                for (Base m : Base.values()) {
                    if (m.getQuickSerializable() == quickSerializable) this.mMode = m;
                }
            }
            mHistory = new History(version, in);
            in.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "No save file yet. First time running the app?", e);
        } catch (IOException e) {
            Log.e(TAG, "Couldn't read from disc", e);
        }
    }

    public void save() {
        try {
            OutputStream os = new BufferedOutputStream(mContext.openFileOutput(FILE_NAME, 0), 8192);
            DataOutputStream out = new DataOutputStream(os);
            out.writeInt(LAST_VERSION);
            out.writeInt(mDeleteMode);
            out.writeInt(mMode == null ? Base.DECIMAL.getQuickSerializable() : mMode.getQuickSerializable());
            mHistory.write(out);
            out.close();
        } catch (IOException e) {
            Log.e(TAG, "Cannot save to disc", e);
        }
    }

    public History getHistory() {
        return mHistory;
    }
}
