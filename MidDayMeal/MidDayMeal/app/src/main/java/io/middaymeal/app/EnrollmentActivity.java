package io.middaymeal.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EnrollmentActivity extends AppCompatActivity implements VolleyApi.ResponseListener{
    EditText fname,lname,dob,fatherName,motherName,fatherIncome,fatherAddhar,totalMembers,etClass;
    public static String  strImg="",strfname,strlname,strdob,strfatherName,strmotherName,strfatherIncome,strfatherAddhar,strtotalMembers,stretClass,strgender,strcaste;
    RadioButton male ,female,sc,st,genral;
    Button btEnroll;
    private String userChoosenTask;
    String img;
    LinearLayout photoLL;
    ImageView profile_photo;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        findViewById();
    }

    private void findViewById() {
        fname=(EditText)findViewById(R.id.et_firstName);
        lname=(EditText)findViewById(R.id.et_lastName);
        fatherName=(EditText)findViewById(R.id.et_fatherName);
        motherName=(EditText)findViewById(R.id.et_mother);
        dob=(EditText)findViewById(R.id.dob);
        fatherIncome = (EditText)findViewById(R.id.et_income);
        fatherAddhar = (EditText)findViewById(R.id.aadhar);
        totalMembers=(EditText)findViewById(R.id.totalMember);
        etClass=(EditText)findViewById(R.id.etClass);
        photoLL=(LinearLayout) findViewById(R.id.photoLL);
        profile_photo=(ImageView) findViewById(R.id.profile_photo);

        male =(RadioButton)findViewById(R.id.rb_male);
        female =(RadioButton)findViewById(R.id.rb_female);
        sc =(RadioButton)findViewById(R.id.rb_sc);
        st =(RadioButton)findViewById(R.id.rb_st);
        genral =(RadioButton)findViewById(R.id.rb_genral);

        btEnroll=(Button)findViewById(R.id.tv_save);

        photoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePic();
            }
        });
        btEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()){

                    saving();
                }
            }
        });

    }

    private void saving() {

        btEnroll.setEnabled(false);
        strfname = fname.getText().toString().trim();
        strlname =fname.getText().toString().trim();
        strdob =fname.getText().toString().trim();
        strfatherName=fname.getText().toString().trim();
        strmotherName =fname.getText().toString().trim();
        strfatherIncome=fname.getText().toString().trim();
        strfatherAddhar=fname.getText().toString().trim();
        strtotalMembers=fname.getText().toString().trim();
        stretClass=fname.getText().toString().trim();
        if (male.isChecked()){
            strgender="male";
        }

        if (female.isChecked()){
            strgender="female";
        }

        if (genral.isChecked()){
            strcaste="genral";
        }

        if (sc.isChecked()){
            strcaste="sc";
        }

        if (st.isChecked()){
            strcaste="st";
        }


        VolleyApi.getInstance().enrollment(this,this);
    }


    boolean checkValidation() {
        boolean ret = true;
        if (!Utility.hasText(fname)) ret = false;
        if(!Utility.hasText(lname))ret=false;
        if (!Utility.hasText(fatherAddhar)) ret = false;
        if (!Utility.hasText(motherName)) ret = false;
        if (!Utility.hasText(fatherName)) ret = false;
        if (!Utility.hasText(totalMembers)) ret = false;
        if (!Utility.hasText(etClass)) ret = false;

        //if(!Utility.isPhoneNumber(alternate_mobile,true))ret=false;


        return ret;
    }


    public void chooseProfilePic() // replcae method name with chooseProfilePic

    {
        final CharSequence[] items = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Profile Picture");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermissions(getApplicationContext());

                if (items[item].equals("Camera")) {
                    userChoosenTask = "Camera";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Gallery")) {
                    userChoosenTask = "Gallery";
                    if (result)
                        galleryIntent();

                }
            }
        });
        builder.show();
    }


    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }


    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        img = getStringImage(bm);
        setPhoto(bm);

    }

    private void setPhoto(Bitmap bitmap) {
        profile_photo.setImageBitmap(bitmap);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
               /* if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }*/
                break;
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPhoto(thumbnail);
        img = getStringImage(thumbnail);
        Log.v("hello", "base64 >>>" + img + " <<<<<");
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {


            default:

                if (resultCode == Activity.RESULT_OK) {
                    if (requestCode == SELECT_FILE)
                        onSelectFromGalleryResult(data);
                    else if (requestCode == REQUEST_CAMERA)
                        onCaptureImageResult(data);

                }

                break;


        }

    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {
            Log.e("msg", obj);

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray;


                jArray = obj1.getJSONArray("msg");
                //int len = jArray.length();
                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject json_data = jArray.getJSONObject(i);

                    if (json_data.getString("status").equalsIgnoreCase("200")) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setCancelable(true);

                        //if successful
                        builder.setTitle(json_data.getString("msg") + "!!!");
                        builder.setMessage(strfname +" "+ strlname +" enroll successful" );
                        builder.setInverseBackgroundForced(true);
                        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.dismiss();
                                recreate();

                            }
                        });
                        builder.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setCancelable(true);
                        builder.setTitle(json_data.getString("msg")+ "!!!");
                        builder.setMessage(strfname +" "+ strlname +" enroll fail please try again" );
                        builder.setInverseBackgroundForced(true);
                        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.dismiss();


                            }
                        });
                        builder.show();
                        btEnroll.setEnabled(true);
                    }
                }

                btEnroll.setEnabled(true);


            //----------------------

        } catch (JSONException e) {
            e.printStackTrace();
            btEnroll.setEnabled(true);


        } catch (Exception e) {
            e.printStackTrace();
            btEnroll.setEnabled(true);
        }

    }
}
