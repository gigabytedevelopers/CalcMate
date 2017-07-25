
package com.android2.calculator3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

import com.android2.calculator3.BaseModule.Mode;

class Persist {
    private static final int LAST_VERSION = 3;
    private static final String FILE_NAME = "calculator.data";
    private Context mContext;

    History history = new History();
    private int mDeleteMode;
    private Mode mode;

    Persist(Context context) {
        this.mContext = context;
    }

    public void setDeleteMode(int mode) {
        mDeleteMode = mode;
    }

    public int getDeleteMode() {
        return mDeleteMode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void load() {
        try {
            InputStream is = new BufferedInputStream(mContext.openFileInput(FILE_NAME), 8192);
            DataInputStream in = new DataInputStream(is);
            int version = in.readInt();
            if(version > LAST_VERSION) {
                throw new IOException("data version " + version + "; expected " + LAST_VERSION);
            }
            if(version > 1) {
                mDeleteMode = in.readInt();
            }
            if(version > 2) {
                int quickSerializable = in.readInt();
                for(Mode m : Mode.values()) {
                    if(m.getQuickSerializable() == quickSerializable) this.mode = m;
                }
            }
            history = new History(version, in);
            in.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            OutputStream os = new BufferedOutputStream(mContext.openFileOutput(FILE_NAME, 0), 8192);
            DataOutputStream out = new DataOutputStream(os);
            out.writeInt(LAST_VERSION);
            out.writeInt(mDeleteMode);
            out.writeInt(mode.quickSerializable);
            history.write(out);
            out.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
