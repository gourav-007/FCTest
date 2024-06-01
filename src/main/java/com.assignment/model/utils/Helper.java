package com.assignment.utils;

import com.assignment.constants.Constants;

public final class TestHelper {

    private TestHelper() {
    }
  
    public static boolean isLatLongValid(double latitude, double longitude) {
        return latitude >= Constants.LAT_BEGIN && latitude <= Constants.LAT_END
                && longitude >= Constants.LONG_BEGIN && longitude <= Constants.LONG_END;
    }
  
    public static double calculateTaskCompletedPercentage(double completedTask, double totalTask) {
        if (totalTask == 0) {
            throw new IllegalArgumentException("Total task count cannot be zero");
        }
        return (completedTask / totalTask) * 100;
    }
}
