package com.arek00.timezone.content;

import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Admin on 2015-01-18.
 */
public class HourGenerator {

    private static HourGenerator instance = new HourGenerator();

    public static HourGenerator getInstance() {
        return instance;
    }


   // private Calendar calendar = Calendar.getInstance();
    private TimeZone userTimeZone;
    private int userUTCOffset;

    private HourGenerator() {
        userTimeZone = TimeZone.getDefault();
        userUTCOffset = userTimeZone.getRawOffset();
        userUTCOffset = millisecondToHour(userUTCOffset);

        Log.i("User UTC Timezone Offset: ", Integer.toString(userUTCOffset));
    }

    public Hour getCurrentHourInTimeZone(City city) {

        Calendar calendar = Calendar.getInstance();

        int utcOffset = city.getUTCOffset();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        hour = validateHour(hour - userUTCOffset + utcOffset);

        return new Hour(hour, minute, second);
    }

    private int validateHour(int hour) {

        Log.i("Validate hour: ",Integer.toString(hour));

        if (hour < 0) {
            return 24 + hour;
        } else if (hour > 23) {
            return hour % 24;
        }

        Log.i("Validate hour: ",Integer.toString(hour));

        return hour;
    }

    private int millisecondToHour(int millisecond)
    {
        return (millisecond / 3600000);
    }
}
