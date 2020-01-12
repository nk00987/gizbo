package gizbo.com.gizbo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import gizbo.com.gizbo.Utils.Constant;

/**
 * Created by Neha on 1/8/2020.
 */

public class ReserPassword extends AppCompatActivity {

    EditText et_newPass,et_confirmPass, et_oldPass ;
    Button btn_savePass;

    static String enter_pwd, enter_re_pwd, enter_old_pwd, ent_chk;
    static String get_entered_otp;
    static String method_fid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("RESET PASSWORD");*/



        Intent i=getIntent();
        get_entered_otp=i.getStringExtra("entered_otp");

        et_newPass = (EditText) findViewById(R.id.id_newPass);
        et_confirmPass = (EditText) findViewById(R.id.id_confirmPass);
        et_oldPass = (EditText) findViewById(R.id.id_oldPass);

        btn_savePass = (Button) findViewById(R.id.id_savePass);
        btn_savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();

                if (isValid()) {
                    new ResetPassword_Post().execute();
                }

                //   Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        });





    }


    private boolean isValid() {

        enter_old_pwd = et_oldPass.getText().toString();
        enter_pwd = et_newPass.getText().toString();
        enter_re_pwd = et_confirmPass.getText().toString();

        if (!isValidEnterOldPassword(enter_old_pwd)) {
            //   et_password.setError("Empty Field");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_oldpassword, (ViewGroup) findViewById(R.id.custom_toast_oldpass1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();


            return false;
        }

        if (!isValidEnterPassword(enter_pwd)) {
            //   et_password.setError("Empty Field");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_password, (ViewGroup) findViewById(R.id.custom_toast_pass1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();


            return false;
        }


        if (!isValidEnterRePassword(enter_re_pwd)) {
            //   et_password.setError("Empty Field");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_repassword, (ViewGroup) findViewById(R.id.custom_toast_re_pass1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();

            return false;
        }

        return true;
    }

    private boolean isValidEnterOldPassword(String oldpwd1) {
        return oldpwd1 != null && oldpwd1.length() > 0;
    }

    private boolean isValidEnterPassword(String pwd1) {
        return pwd1 != null && pwd1.length() > 0;
    }

    private boolean isValidEnterRePassword(String repwd1) {
        return repwd1 != null && repwd1.length() > 0 && enter_pwd.equals(enter_re_pwd);
    }



    public class ResetPassword_Post extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(ReserPassword.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);

        }

        protected String doInBackground(String... arg0) {
            method_fid =   Methods.getLoginFIO_ID(ReserPassword.this);


            try {

                // URL url = new URL(Constant.BASE_API_URL_SERVICE+"changepassword"); // here is your URL path
                URL url = new URL(Constant.BASE_API_URL_DEALS+"changenewpassword"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                //  {"mobile":"4","otp":"1374","password":"12345"}

                //{"fid":"757","password":"abc@123","oldpassword":"abc@123"}
                postDataParams.put("fid", method_fid);
                postDataParams.put("password", enter_pwd);
                postDataParams.put("oldpassword", enter_old_pwd);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.addRequestProperty("authorization","Basic YWRtaW46MTIzNA==");
                conn.setRequestMethod("POST");
                conn.addRequestProperty("x-api-key","Xapi@2018");
                conn.setRequestProperty("Accept-Language", "UTF-8");


                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(postDataParams.toString());
                outputStreamWriter.flush();
                int responseCode = conn.getResponseCode();

                System.out.println("\n5235Sending 'POST' request to URL : " + url);
                System.out.println("5235Post parameters : " + postDataParams);
                System.out.println("5235Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            } catch (Exception e) {

                String gk = e.getMessage().toString();
                System.out.println("zinccccc" + gk);
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();

            String newresult = result;
            Log.d("widout xml", newresult);
            /*Response:{ "status": true,
            "result": "Password has been changed succfully",
            "message": "Password has been changed succfully"}*/


            /*{"status":"1","msg":"Your password is changed successfully!."}*/

            try {
                JSONObject json = new JSONObject(newresult);
                String result_data1=json.getString("status");
                String result_data3=json.getString("msg");

                if(result_data1.equals("1")){
                    Toast.makeText(getApplicationContext(), result_data3, Toast.LENGTH_LONG).show();


                    System.out.println("RESPONSE_resetPass"+result_data1+result_data3);
                    Intent in1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in1);
                }
                else {
                    Toast.makeText(getApplicationContext(), result_data3 , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        this.finish();
        return true;
    }

}

