package com.examplej.salstrackerapp.utility;

import android.os.Build;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.IllegalFormatCodePointException;

public class Utility {
    public static String getStartOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return String.format("%tF", cal.getTime()); // Format: yyyy-MM-dd
    }

    public static String getEndOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return String.format("%tF", cal.getTime()); // Format: yyyy-MM-dd
    }
}
