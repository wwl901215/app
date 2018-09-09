//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wwl.can.utils;

import android.annotation.SuppressLint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    public StringUtils() {
    }

    public static String string(Object var0) {
        if (var0 != null && !"null".equals(var0)) {
            String var1 = "";

            try {
                var1 = String.valueOf(var0);
            } catch (Exception var3) {
                ;
            }

            return var1;
        } else {
            return "";
        }
    }

    public static boolean isEmpty(String var0) {
        return var0 == null || var0.length() == 0;
    }

    public static boolean isNotEmpty(String var0) {
        return !isEmpty(var0);
    }

    public static boolean isBlank(String var0) {
        int var1;
        if (var0 != null && (var1 = var0.length()) != 0) {
            for(int var2 = 0; var2 < var1; ++var2) {
                if (!Character.isWhitespace(var0.charAt(var2))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String var0) {
        return !isBlank(var0);
    }

    public static String clean(String var0) {
        return var0 == null ? "" : var0.trim();
    }

    public static String trim(String var0) {
        return var0 == null ? null : var0.trim();
    }

    public static String trimToNull(String var0) {
        String var1 = trim(var0);
        return isEmpty(var1) ? null : var1;
    }

    public static String trimToEmpty(String var0) {
        return var0 == null ? "" : var0.trim();
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isEquals(String var0, String var1) {
        return ObjectUtils.isEquals(var0, var1);
    }

    public static String nullStrToEmpty(String var0) {
        return var0 == null ? "" : var0;
    }

    public static int toInt(String var0, int var1) {
        try {
            return Integer.parseInt(var0);
        } catch (Exception var3) {
            return var1;
        }
    }

    public static int toInt(Object var0) {
        return var0 == null ? 0 : toInt(var0.toString(), 0);
    }

    public static long toLong(String var0) {
        try {
            return Long.parseLong(var0);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static double toDouble(String var0) {
        try {
            return Double.parseDouble(var0);
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public static boolean toBool(String var0) {
        try {
            return Boolean.parseBoolean(var0);
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isNumber(CharSequence var0) {
        try {
            Integer.parseInt(var0.toString());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static String capitalizeFirstLetter(String var0) {
        if (isEmpty(var0)) {
            return var0;
        } else {
            char var1 = var0.charAt(0);
            return Character.isLetter(var1) && !Character.isUpperCase(var1) ? (new StringBuilder(var0.length())).append(Character.toUpperCase(var1)).append(var0.substring(1)).toString() : var0;
        }
    }

    public static String utf8Encode(String var0) {
        if (!isEmpty(var0) && var0.getBytes().length != var0.length()) {
            try {
                return URLEncoder.encode(var0, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", var2);
            }
        } else {
            return var0;
        }
    }

    public static String utf8Encode(String var0, String var1) {
        if (!isEmpty(var0) && var0.getBytes().length != var0.length()) {
            try {
                return URLEncoder.encode(var0, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                return var1;
            }
        } else {
            return var0;
        }
    }

    public static String htmlEscapeCharsToString(String var0) {
        return isEmpty(var0) ? var0 : var0.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String var0) {
        if (isEmpty(var0)) {
            return var0;
        } else {
            char[] var1 = var0.toCharArray();

            for(int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2] == 12288) {
                    var1[var2] = ' ';
                } else if (var1[var2] >= '！' && var1[var2] <= '～') {
                    var1[var2] -= 'ﻠ';
                } else {
                    var1[var2] = var1[var2];
                }
            }

            return new String(var1);
        }
    }

    public static String halfWidthToFullWidth(String var0) {
        if (isEmpty(var0)) {
            return var0;
        } else {
            char[] var1 = var0.toCharArray();

            for(int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2] == ' ') {
                    var1[var2] = 12288;
                } else if (var1[var2] >= '!' && var1[var2] <= '~') {
                    var1[var2] += 'ﻠ';
                } else {
                    var1[var2] = var1[var2];
                }
            }

            return new String(var1);
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static String byteArrayToHexString(byte[] var0) {
        StringBuilder var1 = new StringBuilder(var0.length * 2);
        byte[] var5 = var0;
        int var4 = var0.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte var2 = var5[var3];
            int var6 = var2 & 255;
            if (var6 < 16) {
                var1.append('0');
            }

            var1.append(Integer.toHexString(var6));
        }

        return var1.toString().toUpperCase();
    }

    public static byte[] hexStringToByteArray(String var0) {
        int var1 = var0.length();
        byte[] var2 = new byte[var1 / 2];

        for(int var3 = 0; var3 < var1; var3 += 2) {
            var2[var3 / 2] = (byte)((Character.digit(var0.charAt(var3), 16) << 4) + Character.digit(var0.charAt(var3 + 1), 16));
        }

        return var2;
    }

    public static String hexStr2Str(String var0) {
        String var1 = "0123456789ABCDEF";
        char[] var2 = var0.toCharArray();
        byte[] var3 = new byte[var0.length() / 2];

        for(int var5 = 0; var5 < var3.length; ++var5) {
            int var4 = var1.indexOf(var2[2 * var5]) * 16;
            var4 += var1.indexOf(var2[2 * var5 + 1]);
            var3[var5] = (byte)(var4 & 255);
        }

        return new String(var3);
    }

    public static InputStream StringToInputStream(String var0) {
        if (TextUtils.isEmpty(var0)) {
            return null;
        } else {
            ByteArrayInputStream var1 = new ByteArrayInputStream(var0.getBytes());
            return var1;
        }
    }

    public static final String replace(String var0, String var1, String var2) {
        if (var0 == null) {
            return null;
        } else {
            byte var3 = 0;
            int var9;
            if ((var9 = var0.indexOf(var1, var3)) < 0) {
                return var0;
            } else {
                char[] var4 = var0.toCharArray();
                char[] var5 = var2.toCharArray();
                int var6 = var1.length();
                StringBuffer var7 = new StringBuffer(var4.length);
                var7.append(var4, 0, var9).append(var5);
                var9 += var6;

                int var8;
                for(var8 = var9; (var9 = var0.indexOf(var1, var9)) > 0; var8 = var9) {
                    var7.append(var4, var8, var9 - var8).append(var5);
                    var9 += var6;
                }

                var7.append(var4, var8, var4.length - var8);
                return var7.toString();
            }
        }
    }

    public static void highlight(int var0, int var1, TextView var2) {
        SpannableStringBuilder var3 = new SpannableStringBuilder(var2.getText().toString());
        ForegroundColorSpan var4 = new ForegroundColorSpan(-65536);
        var3.setSpan(var4, var0, var1, 33);
        var2.setText(var3);
    }

    public static String getHightLightText(String var0) {
        Pattern var1 = Pattern.compile("<em>([^</em>]*)");
        Matcher var2 = var1.matcher(var0);

        String var3;
        for(var3 = null; var2.find(); var3 = var2.group(1)) {
            ;
        }

        return var3;
    }

    public static String strip(String var0) {
        return strip(var0, (String)null);
    }

    public static String stripToNull(String var0) {
        if (var0 == null) {
            return null;
        } else {
            var0 = strip(var0, (String)null);
            return var0.length() == 0 ? null : var0;
        }
    }

    public static String stripToEmpty(String var0) {
        return var0 == null ? "" : strip(var0, (String)null);
    }

    public static String strip(String var0, String var1) {
        if (isEmpty(var0)) {
            return var0;
        } else {
            var0 = stripStart(var0, var1);
            return stripEnd(var0, var1);
        }
    }

    public static String stripStart(String var0, String var1) {
        int var2;
        if (var0 != null && (var2 = var0.length()) != 0) {
            int var3 = 0;
            if (var1 == null) {
                while(var3 != var2 && Character.isWhitespace(var0.charAt(var3))) {
                    ++var3;
                }
            } else {
                if (var1.length() == 0) {
                    return var0;
                }

                while(var3 != var2 && var1.indexOf(var0.charAt(var3)) != -1) {
                    ++var3;
                }
            }

            return var0.substring(var3);
        } else {
            return var0;
        }
    }

    public static String stripEnd(String var0, String var1) {
        int var2;
        if (var0 != null && (var2 = var0.length()) != 0) {
            if (var1 == null) {
                while(var2 != 0 && Character.isWhitespace(var0.charAt(var2 - 1))) {
                    --var2;
                }
            } else {
                if (var1.length() == 0) {
                    return var0;
                }

                while(var2 != 0 && var1.indexOf(var0.charAt(var2 - 1)) != -1) {
                    --var2;
                }
            }

            return var0.substring(0, var2);
        } else {
            return var0;
        }
    }

    public static String[] stripAll(String[] var0) {
        return stripAll(var0, (String)null);
    }

    public static String[] stripAll(String[] var0, String var1) {
        int var2;
        if (var0 != null && (var2 = var0.length) != 0) {
            String[] var3 = new String[var2];

            for(int var4 = 0; var4 < var2; ++var4) {
                var3[var4] = strip(var0[var4], var1);
            }

            return var3;
        } else {
            return var0;
        }
    }

    public static boolean equals(String var0, String var1) {
        return var0 == null ? var1 == null : var0.equals(var1);
    }

    public static boolean equalsIgnoreCase(String var0, String var1) {
        return var0 == null ? var1 == null : var0.equalsIgnoreCase(var1);
    }

    public static int indexOf(String var0, char var1) {
        return isEmpty(var0) ? -1 : var0.indexOf(var1);
    }

    public static int indexOf(String var0, char var1, int var2) {
        return isEmpty(var0) ? -1 : var0.indexOf(var1, var2);
    }

    public static int indexOf(String var0, String var1) {
        return var0 != null && var1 != null ? var0.indexOf(var1) : -1;
    }

    public static int ordinalIndexOf(String var0, String var1, int var2) {
        if (var0 != null && var1 != null && var2 > 0) {
            if (var1.length() == 0) {
                return 0;
            } else {
                int var3 = 0;
                int var4 = -1;

                do {
                    var4 = var0.indexOf(var1, var4 + 1);
                    if (var4 < 0) {
                        return var4;
                    }

                    ++var3;
                } while(var3 < var2);

                return var4;
            }
        } else {
            return -1;
        }
    }

    public static int indexOf(String var0, String var1, int var2) {
        if (var0 != null && var1 != null) {
            return var1.length() == 0 && var2 >= var0.length() ? var0.length() : var0.indexOf(var1, var2);
        } else {
            return -1;
        }
    }

    public static int lastIndexOf(String var0, char var1) {
        return isEmpty(var0) ? -1 : var0.lastIndexOf(var1);
    }

    public static int lastIndexOf(String var0, char var1, int var2) {
        return isEmpty(var0) ? -1 : var0.lastIndexOf(var1, var2);
    }

    public static int lastIndexOf(String var0, String var1) {
        return var0 != null && var1 != null ? var0.lastIndexOf(var1) : -1;
    }

    public static int lastIndexOf(String var0, String var1, int var2) {
        return var0 != null && var1 != null ? var0.lastIndexOf(var1, var2) : -1;
    }

    public static boolean contains(String var0, char var1) {
        if (isEmpty(var0)) {
            return false;
        } else {
            return var0.indexOf(var1) >= 0;
        }
    }

    public static boolean contains(String var0, String var1) {
        if (var0 != null && var1 != null) {
            return var0.indexOf(var1) >= 0;
        } else {
            return false;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean containsIgnoreCase(String var0, String var1) {
        return var0 != null && var1 != null ? contains(var0.toUpperCase(), var1.toUpperCase()) : false;
    }

    public static String getHideBankCardNum(String var0) {
        String var1 = "";
        String var2 = "";
        if (var0 != null && !isEmpty(var0)) {
            if (var0.length() >= 16) {
                for(int var3 = 0; var3 < var0.length() - 10; ++var3) {
                    var1 = var1 + "*";
                }

                var2 = var0.substring(0, 6) + var1 + var0.substring(var0.length() - 4, var0.length());
            } else {
                var2 = var0;
            }

            return var2;
        } else {
            return "";
        }
    }

    public static String getHidePhoneNum(String var0) {
        return var0 != null && var0.length() == 11 ? var0.substring(0, 3) + "****" + var0.substring(var0.length() - 4, var0.length()) : "";
    }

    public static String getHideIDCardNo(String var0) {
        String var1 = "";
        String var2 = "";
        if (var0 != null && !isEmpty(var0)) {
            if (var0.length() >= 15) {
                for(int var3 = 0; var3 < var0.length() - 7; ++var3) {
                    var1 = var1 + "*";
                }

                var2 = var0.substring(0, 3) + var1 + var0.substring(var0.length() - 4, var0.length());
            } else {
                var2 = var0;
            }

            return var2;
        } else {
            return "";
        }
    }
}
