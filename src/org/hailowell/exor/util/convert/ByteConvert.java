package org.hailowell.exor.util.convert;

import java.util.Locale;

/**
 * Created by hailowell on 2018/9/26.
 */
public class ByteConvert {
    public static String bytes2HexString(byte[] bits) {
        if (null == bits || bits.length <= 0) {
            return "null";
        }
        StringBuilder builder = new StringBuilder(12);
        String hs;
        for (int i = 0, len = bits.length; i < len; i++) {
            hs = Integer.toHexString(bits[i] & 0xFF);
            if (hs.length() < 2) {
                builder.append(0);
            }
            builder.append(hs);
        }
        return builder.toString().toUpperCase(Locale.CHINA);
    }
}
