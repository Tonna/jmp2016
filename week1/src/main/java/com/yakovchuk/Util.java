package com.yakovchuk;

import java.util.Iterator;
import java.util.List;

public class Util {
    public static String join(List<String> strings, String separator) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            if (iterator.hasNext()) {
                buffer.append(separator);
            }
        }
        return buffer.toString();
    }
}
