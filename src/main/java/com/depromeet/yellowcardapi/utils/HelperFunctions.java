package com.depromeet.yellowcardapi.utils;

public class HelperFunctions {

    public static boolean isNullOrZero(final Object obj) {

        if(obj == null) return true;

        if (obj instanceof Integer){
            Integer i = (Integer) obj;
            return (i == 0);
        } else if(obj instanceof Long){
            Long l = (Long) obj;
            return (l == 0);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
