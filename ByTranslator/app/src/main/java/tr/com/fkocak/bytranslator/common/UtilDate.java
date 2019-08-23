package tr.com.fkocak.bytranslator.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class UtilDate {

    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public String getCurrentDate() {
        Date date = new Date();

        switch (String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))) {
            case "1":
                return "Pazar-" + sdf.format(date);

            case "2":
                return "Pazartesi-" + sdf.format(date);

            case "3":
                return "Salı-" + sdf.format(date);

            case "4":
                return "Çarşamba-" + sdf.format(date);

            case "5":
                return "Perşembe-" + sdf.format(date);

            case "6":
                return "Cuma-" + sdf.format(date);

            case "7":
                return "Cumartesi-" + sdf.format(date);

        }
        return null;
    }
}
