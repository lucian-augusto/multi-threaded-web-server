package com.lucianaugusto.base.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public final class IOUtils {

    private IOUtils() {
    }

    public static void closeQuietly(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(File file) {
        boolean exists = false;
        try {
            exists = file.exists();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
