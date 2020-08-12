package io.middaymeal.app;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by siddharthgautam on 30/10/18.
 */

public class Utility {

    public static final int PERMISSION_CALLBACK_CONSTANT = 100;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^[1-9][0-9]{9}$";
    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "invalid phone";
    public static String des = "";
    public static String source = "";
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    static Ringtone ringtone;
    static String rideID;
    static boolean status = false;
    static boolean statusAvailable = false;
    static String[] permissionsRequired = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    static String[] cameraPermissionsRequired = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    Activity activity1;

    public static void alert(final Context context, String Msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);

        //if successful
        builder.setTitle("Alert Dialog");
        builder.setMessage(Msg);
        builder.setInverseBackgroundForced(true);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();

            }
        });
        builder.show();

    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = getConnectivityStatus(context);
        String status = null;
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[0]) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[1])) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Access Location permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean checkCameraPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, cameraPermissionsRequired[0]) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, cameraPermissionsRequired[1]) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, cameraPermissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, cameraPermissionsRequired[0]) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, cameraPermissionsRequired[1]) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, cameraPermissionsRequired[2])) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Access Camera and Read/Write on disk permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, cameraPermissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, cameraPermissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;

        return true;
    }

    public static boolean checkIfAlreadyhavePermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int result2 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    public static boolean hasText_Textview(TextView editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    public static void showToast(Context mContext, String string) {
        Toast t = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        t.show();

    }

    public static boolean checkPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            int res = context.checkCallingOrSelfPermission(permissions[0]);
            if (res != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void addPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void addPreferences(Context context, String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        return text;
    }

    public static Double getPreferences(Context context, String key, Double defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Double.valueOf(text);
    }

    public static int getPreferences(Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Integer.parseInt(text);
    }

    public static Boolean getPreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        Boolean text = prefs.getBoolean(key, defaultValue);
        return text;
    }

    public static void showSnackBar(Activity context, String str) {
        View view = context.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, str, Snackbar.LENGTH_LONG).show();

    }

    public static boolean isInternetConnected(Activity mContext) {

        /*
         * final ConnectivityManager connec = (ConnectivityManager)
         * mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
         *
         * if (connec != null&& (connec.getNetworkInfo(1).getState() ==
         * NetworkInfo.State.CONNECTED)|| (connec.getNetworkInfo(0).getState()
         * == NetworkInfo.State.CONNECTED)) { return true; } return false;
         */
        hideKeyboard(mContext);
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            showSnackBar(mContext, Constants.errorMsdOffline);
        }

        return false;
    }

    public static void clearPreferenceData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public static void setStatusBarTranslucent(Activity context, boolean makeTranslucent) {
        if (makeTranslucent) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void datePickerDialogBackDate(Activity context, DatePickerDialog.OnDateSetListener listner) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, listner,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();
    }

    public static String formatDateForDisplay(String inputDate, String inputFormat, String outputFormat) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e(TAG, "ParseException - dateFormat");
        }

        return outputDate;
    }

    public static String getCurrentDate(String format) {

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat(format);

        return df.format(c.getTime());
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static Bitmap base64ToBitmap(String bm) {
        Bitmap decodedImage = null;
        if (!bm.equalsIgnoreCase("")) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageBytes = baos.toByteArray();

            //decode base64 string to image
            imageBytes = Base64.decode(bm, Base64.DEFAULT);
            decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
        return decodedImage;
    }

    public static boolean checkLocationPermission(Activity context) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public static void datePickerDialog(Activity context, DatePickerDialog.OnDateSetListener listner) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, listner,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dialog.show();
    }

    public static void openDialerPaidWithNumber(Context context, String mobile) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mobile));
        context.startActivity(intent);
    }

    public static void startNavigation(Context context, String lattitude, String longitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lattitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    public static void playRingSound(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(context, notification);
        ringtone.play();

    }

    public static void stopRingSound(Context context) {
        if (ringtone != null) {
            ringtone.stop();
        }

    }

    public static double getPrice(double base_fare, double time_rate, double distance_rate, double km, int min) {
        return (base_fare + (time_rate * min) + (distance_rate * km));
    }

    public static int minutesDiff(Date earlierDate, Date laterDate) {
        if (earlierDate == null || laterDate == null) return 0;

        long result = ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
        return (int) result;
    }

    public static Date GetItemDate(final String date) {
        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
        format.setCalendar(cal);

        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


    public interface ACTION {
        public static String MAIN_ACTION = "siddharth.com.schooldriverapp.action.main";
        public static String STARTFOREGROUND_ACTION = "siddharth.com.schooldriverapp.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "siddharth.com.schooldriverapp.action.stopforeground";
        public static String STOPFOREGROUND_CHECK = "siddharth.com.schooldriverapp.action.check";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }


}
