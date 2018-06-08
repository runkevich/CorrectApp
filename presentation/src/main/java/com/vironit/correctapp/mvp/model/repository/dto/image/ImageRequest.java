package com.vironit.correctapp.mvp.model.repository.dto.image;

import com.vironit.correctapp.BuildConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageRequest {

    private File file;
    private String apiKey;
    private String apiSecret;
    private String timestamp;
    private String signature;

    public ImageRequest(String image) {
        this.file = new File(image);
        init();
    }
    public ImageRequest(File file) {
        this.file = file;
        init();
    }
    private void init() {
        this.apiKey = BuildConfig.IMAGE_API_KEY;
        this.apiSecret = BuildConfig.IMAGE_SECRET_KEY;
        this.timestamp = getTimeStamp();
        this.signature = SHA1("timestamp=" + timestamp + apiSecret);
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    private static String SHA1(final String text) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("UTF-8"),
                    0, text.length());
            byte[] sha1hash = md.digest();

            return toHex(sha1hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String toHex(final byte[] buf) {
        if (buf == null) return "";

        int l = buf.length;
        StringBuffer result = new StringBuffer(2 * l);

        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(final StringBuffer sb, final byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f))
                .append(HEX.charAt(b & 0x0f));
    }

    public File getFile() {
        return file;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSignature() {
        return signature;
    }
}
