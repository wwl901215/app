//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wwl.can.utils;

public class ObjectUtils {
    public ObjectUtils() {
    }

    public static boolean isEquals(Object var0, Object var1) {
        if (var0 != var1) {
            if (var0 == null) {
                if (var1 != null) {
                    return false;
                }
            } else if (!var0.equals(var1)) {
                return false;
            }
        }

        return true;
    }

    public static Long[] transformLongArray(long[] var0) {
        Long[] var1 = new Long[var0.length];

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = var0[var2];
        }

        return var1;
    }

    public static long[] transformLongArray(Long[] var0) {
        long[] var1 = new long[var0.length];

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = var0[var2];
        }

        return var1;
    }

    public static Integer[] transformIntArray(int[] var0) {
        Integer[] var1 = new Integer[var0.length];

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = var0[var2];
        }

        return var1;
    }

    public static int[] transformIntArray(Integer[] var0) {
        int[] var1 = new int[var0.length];

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = var0[var2];
        }

        return var1;
    }

    public static <V> int compare(V var0, V var1) {
        return var0 == null ? (var1 == null ? 0 : -1) : (var1 == null ? 1 : ((Comparable)var0).compareTo(var1));
    }
}
