package com.example.githubissuetracker.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public static String formatDate(String inputDate) {
        if(inputDate == null) {
            return "";
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDate);
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                Calendar today = Calendar.getInstance();
                // Check if within the last 7 days
                if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                        today.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                    return "Today";
                } else if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                        today.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) - 1) {
                    return "Yesterday";
                } else if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                        today.get(Calendar.DAY_OF_YEAR) <= calendar.get(Calendar.DAY_OF_YEAR) + 7) {
                    return dayFormat.format(date);
                }

                // Fallback to MM/dd/yyyy format
                return outputFormat.format(date);
            }
        } catch (Exception e) {
            return "";
        }
        return ""; // or throw an exception based on your use case
    }

}

