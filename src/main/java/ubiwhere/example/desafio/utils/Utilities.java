package ubiwhere.example.desafio.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

/**
 * UTILITIES
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public class Utilities {

    /**
     *
     * @param timestamp
     * @return
     */
    public static long convertStringISOFormatToLong(String timestamp) {
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            df.setTimeZone(tz);
            Date timestampDate = df.parse(timestamp);
            return timestampDate.getTime();
        } catch (Exception e) {
            System.out.println(Responses.errorParameterInvalid("The Timestamp is invalid : " + e.getMessage()));
            return (long) -1;
        }
    }

    /**
     *
     * @return
     */
    public static String getNowDateISO() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        df.setTimeZone(tz);
        return df.format(new Date());
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getDateISO(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        df.setTimeZone(tz);
        return df.format(date);
    }

    /**
     *
     * @return
     */
    public static Gson getGsonFactory() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    /**
     *
     * @param property
     * @return
     * @throws IOException
     */
    public static String getPropValues(String property) throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName));
        // get the property value and print it out
        String timeOutSamples = prop.getProperty(property);
        return timeOutSamples;
    }
}
