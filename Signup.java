package gizbo.com.gizbo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {


    EditText et_firstname, et_mobile , et_email , et_password , et_retype_password ;
    Button btn_createaccount;
    CheckBox chk_yes;

    static String str_firstname,str_mobile,str_email, str_password, str_retype_password;
    static String enter_user_name,enter_mobile,enter_email, enter_pwd, enter_re_pwd, ent_chk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        et_firstname = findViewById(R.id.id_firstname);
        et_mobile = findViewById(R.id.id_mobile);
        et_email = findViewById(R.id.id_email);
        et_password = findViewById(R.id.id_password);
        et_retype_password = findViewById(R.id.id_retype_password);
        chk_yes = findViewById(R.id.id_checkbox);


        btn_createaccount = findViewById(R.id.id_createaccount);
        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_firstname = et_firstname.getText().toString();
                str_mobile = et_mobile.getText().toString();
                str_email = et_email.getText().toString();
                str_password = et_password.getText().toString();
                str_retype_password = et_retype_password.getText().toString();

               if (isValid()) {
                    //  Toast.makeText(getApplicationContext(), "Done Successfully", Toast.LENGTH_LONG).show();
                    //   new SendPostRequest_Login().execute();
                    new Register_Post().execute();

              }

            }
        });



    }

    private boolean isValid() {

        enter_user_name = et_firstname.getText().toString();
        enter_mobile = et_mobile.getText().toString();
        enter_email = et_email.getText().toString();
        enter_pwd = et_password.getText().toString();
        enter_re_pwd = et_retype_password.getText().toString();

        if (!isValidEnterName(enter_user_name)) {
            // et_email.setError("Invalid Format");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_name, (ViewGroup) findViewById(R.id.custom_toast_name1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();

            return false;
        }

        if (!isValidEnterMobile(enter_mobile)) {
            // et_email.setError("Invalid Format");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_mobile, (ViewGroup) findViewById(R.id.custom_toast_mobile1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();

            return false;
        }


        if (!isValidEnterEmail(enter_email)) {
            // et_email.setError("Invalid Format");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_email, (ViewGroup) findViewById(R.id.custom_toast_emailid));
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

        if (!isValidCheck(ent_chk)) {
            //   et_password.setError("Empty Field");

            Toast.makeText(getApplicationContext(), "Please accept the Terms and Conditions to proceed.", Toast.LENGTH_LONG).show();



            return false;
        }





        return true;
    }

    private boolean isValidEnterName(String name1) {
        return name1 != null && name1.length() > 0;
    }
    private boolean isValidEnterMobile(String mob1) {
        return mob1 != null && mob1.length() > 9 && mob1.length() < 13;
    }
    private boolean isValidEnterEmail(String email1) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email1);
        return matcher.matches();
    }
    private boolean isValidEnterPassword(String pwd1) {
        return pwd1 != null && pwd1.length() > 0;
    }

    private boolean isValidEnterRePassword(String repwd1) {
        return repwd1 != null && repwd1.length() > 0 && enter_pwd.equals(enter_re_pwd);
    }


    private boolean isValidCheck(String repwd1) { // !chk_yes.isChecked()
        return chk_yes.isChecked();
    }




    public class Register_Post extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(Signup.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(Constant.BASE_API_URL_SERVICE+"addresidents"); // here is your URL path

                JSONObject postDataParams = new JSONObject();

                // {"user_name":"ffg","email":"fgf@gmail.com",
                // "mobile":"9944","password":"12345","source":"mobile"}

                /*{"user_name":"anu","email":"fgf@gmail.com",
                "mobile":"879","password":"12345","source":"web"}*/

                postDataParams.put("user_name", enter_user_name);
                postDataParams.put("email", enter_email);
                postDataParams.put("mobile", enter_mobile);
                postDataParams.put("password", enter_pwd);
                postDataParams.put("source", "mobile");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.addRequestProperty("authorization","Basic YWRtaW46MTIzNA==");
                conn.setRequestMethod("POST");
                conn.addRequestProperty("x-api-key","pi@2018");
                conn.setRequestProperty("Accept-Language", "UTF-8");


                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(postDataParams.toString());
                outputStreamWriter.flush();
                int responseCode = conn.getResponseCode();


                System.out.println("\n5225Sending 'POST' request to URL : " + url);
                System.out.println("5225Post parameters : " + postDataParams);
                System.out.println("5225Response Code : " + responseCode);

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
            /*{"status":true,"result":{"full_name":"Anathi",
            "email":"fgffgdt@gmail.com","mobile":"9944"},
            "message":"Result Provided"}*/

            /*{
            "status": true,"result": {"id": "18","full_name": "",
                "email": "anfgfgandt@gmail.com","mobile": "4"},
            "message": "Result Provided"}*/

            /*{
    "status": true,
    "result": {
        "id": "22",
        "full_name": "",
        "email": "anafgfndanu@gmail.com",
        "mobile": "869",
        "fio_id": "3228",
        "email_status": "0",
        "mobile_status": "0"
    },
    "message": "Result Provided"
}*/

            try {
                JSONObject json = new JSONObject(newresult);
                String result_data=json.getString("status");
                String st_message=json.getString("message");

                if(result_data.equals("true")){
                    Toast.makeText(getApplicationContext(), st_message, Toast.LENGTH_LONG).show();

                    JSONObject json2 = json.getJSONObject("result");
                    String st_id=json2.getString("id");
                    String st_full_name=json2.getString("full_name");
                    String st_email=json2.getString("email");
                    String st_mobile=json2.getString("mobile");
                    String st_fio_id=json2.getString("fio_id");
                    String st_email_status=json2.getString("email_status");
                    String st_mobile_status=json2.getString("mobile_status");

        //        "mobile_status": "0"    not verified
        //        "mobile_status": "1"  verified

                    Methods.saveID(Signup.this,st_id);
                    Methods.saveFULLNAME(Signup.this,st_full_name);
                    Methods.saveEMAILID(Signup.this,st_email);
                    Methods.saveMOBILE(Signup.this,st_mobile);
                    Methods.saveFIO_ID(Signup.this,st_fio_id);
                    Methods.saveEMAIL_STATUS(Signup.this,st_email_status);
                    Methods.saveMOBILE_STATUS(Signup.this,st_mobile_status);


                    System.out.println("RESPONSE_register"+st_full_name+st_email+st_mobile+"st_message:--"+st_message);
                  //  Intent in1 = new Intent(getApplicationContext(), Profile.class);

                    Toast.makeText(getApplicationContext(), st_message , Toast.LENGTH_LONG).show();


                   Intent in1 = new Intent(getApplicationContext(), Signup_VerifyOTP.class);
                    startActivity(in1);
                }
                else {
                    Toast.makeText(getApplicationContext(), st_message , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
