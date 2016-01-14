package org.solovyev.android.io;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BaseIoLoader {

    @Nullable
    public CharSequence load() {
        BufferedReader reader = null;
        try {
            final InputStream is = getInputStream();
            if (is == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(is));
            final StringBuilder result = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                result.append(line).append("\n");
                line = reader.readLine();
            }
            return result;
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        } finally {
            Io.close(reader);
        }
        return null;
    }

    @Nullable
    protected abstract InputStream getInputStream() throws IOException;
}