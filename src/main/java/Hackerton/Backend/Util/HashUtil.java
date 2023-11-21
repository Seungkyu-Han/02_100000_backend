package Hackerton.Backend.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class HashUtil {

    public static String tashName(int id) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDatetime = formatter.format(now);

        String combinedString = id + formattedDatetime;
        byte[] combinedBytes = combinedString.getBytes();

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hashBytes = messageDigest.digest(combinedBytes);

        StringBuilder stringBuilder = new StringBuilder();
        for (byte hashByte : hashBytes) {
            stringBuilder.append(String.format("%02x", hashByte));
        }

        return stringBuilder.substring(0, 8).toUpperCase(Locale.getDefault());
    }
}
