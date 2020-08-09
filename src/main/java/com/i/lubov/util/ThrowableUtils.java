package com.i.lubov.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ThrowableUtils {
    public ThrowableUtils() {}

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String var3;
        try {
            throwable.printStackTrace(pw);
            var3 = sw.toString();
        } finally {
            pw.close();
        }
        return var3;
    }
}
