package com.ganjarramadhan.bookdoctor.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class AppsUtil {

    public static void saveToPreference(Context context, String prefsFileName, String prefName, String prefValue) {

        SharedPreferences sharedPreferences = context.
                getSharedPreferences(prefsFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();

    }

    public static String readPreference(Context context, String prefsFileName, String prefName, String defaultValue) {

        SharedPreferences sharedPreferences = context.
                getSharedPreferences(prefsFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, defaultValue);

    }

    public static void deletePreference(Context context, String prefsFileName, String prefName) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(prefsFileName, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(prefName);
        editor.commit();

    }

    public static void clearAllPreferences(Context context, String prefsFileName) {

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(prefsFileName, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();

    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            if (activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void setFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static final String md5(final String password) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sha1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return toHexString(sha1hash);
    }

    public static String generateSignature(String email, String passwordInSha,
                                           String reqMethod, String url, String timeStamp)
            throws InvalidKeyException {

        try {
            String key = reqMethod + ":" + url + ":" + timeStamp + ":::";

            String hmacSignature = calculateRFC2104HMAC(passwordInSha, key);

            return email + ":" + hmacSignature;

        } catch (SignatureException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ":";
        }


    }

    private static String calculateRFC2104HMAC(String data, String key) throws SignatureException,
            NoSuchAlgorithmException, InvalidKeyException {

        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        String ret = formatter.toString();
        formatter.close();

        return ret;
    }

}

