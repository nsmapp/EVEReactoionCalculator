package by.nepravsky.sm.domain.utils;

import java.util.Locale;

public class StringUtils {

    public static String formatDouble(double data){
        return String.format(Locale.getDefault(), "%,3.2f", data);
    }
}
