package com.epam.examinationsystem.core.util.web;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CaptchaUtil {

    private static final String SECRET_PARAM = "secret";
    private static final String RESPONSE_PARAM = "response";

    private CaptchaUtil() {
    }

    public static boolean verifyCaptcha(String recaptchaResponseToken, String verifyUrl, String secretKey) throws IOException {
        URL url = new URL(verifyUrl);
        StringBuilder postData = new StringBuilder();
        addParam(postData, SECRET_PARAM, secretKey);
        addParam(postData, RESPONSE_PARAM, recaptchaResponseToken);
        JSONObject jsonObject = postAndParseJSON(url, postData.toString());
        return jsonObject.getBoolean("success");
    }

    private static void addParam(StringBuilder postData, String param, String value) throws UnsupportedEncodingException {
        if (postData.length() != 0) {
            postData.append("&");
        }
        postData.append(
                String.format("%s=%s",
                        URLEncoder.encode(param, StandardCharsets.UTF_8.displayName()),
                        URLEncoder.encode(value, StandardCharsets.UTF_8.displayName())
                )
        );
    }

    private static JSONObject postAndParseJSON(URL url, String postData) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("charset", StandardCharsets.UTF_8.displayName());
        urlConnection.setRequestProperty("Content-Length", Integer.toString(postData.length()));
        urlConnection.setUseCaches(false);
        urlConnection.getOutputStream().write(postData.getBytes(StandardCharsets.UTF_8));
        JSONTokener jsonTokener = new JSONTokener(urlConnection.getInputStream());
        return new JSONObject(jsonTokener);
    }
}
