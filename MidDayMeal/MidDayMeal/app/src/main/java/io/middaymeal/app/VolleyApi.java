package io.middaymeal.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by siddharthgautam on 10/03/18.
 */

public class VolleyApi {

    private ProgressDialog mProgressDialog;
    private static VolleyApi volleyApi = null;
    private ResponseListener mlistener_response;
    //public static final String BASE_URL = "http://techfizone.com/school_van_webservice/index.php/";
    public static final String BASE_URL = "http://10.0.2.2:8080/mid_day_meal_service/index.php/";

    public static final String BASE_Image_URL = "http://techfizone.com/school_van_webservice/";


    public static VolleyApi getInstance() {

        if (volleyApi != null)
            return volleyApi;
        else
            return new VolleyApi();
    }


    public interface ResponseListener {

        void _onResponseError(Throwable e);

        void _onNext(String obj);

    }


    //----------------------------------------------- Volly Api-------------------------------------------------------


    //----------------------------------------------- Login Api-------------------------------------------------------


    public void login(final Activity activity, final ResponseListener _mlistener_response, final String username , final String password,String type) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/get/type/"+type+"/user_name/"+username+"/password/"+password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void getStudent(final Activity activity, final ResponseListener _mlistener_response,final String StdId) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Student/get/school_id/"+StdId+"/class/"+SingltonData.classId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void getFeedback(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "School/getFeedbackHead/userId/"+SingltonData.schoolId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }



    public void getMennu(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Menu/get/school_id/"+Utility.getPreferences(activity,Constants.id),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }
    public void getClaassApi(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Student/getClass",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }

    public void getSchool(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "School/get/feedback_manager_id/"+Utility.getPreferences(activity,Constants.id),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void getBudgetInfo(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Budget/get/school_id/"+Utility.getPreferences(activity,Constants.id),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }

    public void innsetAttandance(final Activity activity, final ResponseListener _mlistener_response, final String student_id) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Student/post",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("school_id",Utility.getPreferences(activity,Constants.id));
                    params.put("student_id",student_id);
                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }




    public void insetFeedback(final Context activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "School/post",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("feedback_manager_id",Utility.getPreferences(activity,Constants.id));
                    params.put("school_id",SingltonData.schoolId);
                    params.put("user_id",SingltonData.userId);
                    params.put("feedback",SingltonData.feedback);
                    params.put("comments",SingltonData.comment);
                    params.put("feedback_head_id",SingltonData.feedback_head_id);
                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void enrollment(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Student/enroll/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("school_id",Utility.getPreferences(activity,Constants.id));
                    params.put("first_name",EnrollmentActivity.strfname);
                    params.put("last_name",EnrollmentActivity.strlname);
                    params.put("father_name",EnrollmentActivity.strfatherName);
                    params.put("mother_name",EnrollmentActivity.strmotherName);
                    params.put("father_income",EnrollmentActivity.strfatherIncome);
                    params.put("total_family_member",EnrollmentActivity.strtotalMembers);
                    params.put("d_o_b",EnrollmentActivity.strdob);
                    params.put("caste",EnrollmentActivity.strcaste);
                    params.put("class",EnrollmentActivity.stretClass);
                    params.put("father_aadhar_no",EnrollmentActivity.strfatherAddhar);
                    params.put("photo",EnrollmentActivity.strImg);
                    params.put("gender",EnrollmentActivity.strgender);
                    return params;
                }



            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }

}


