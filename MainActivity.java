package gizbo.com.gizbo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText et_username , et_password ;
    TextView tv_signup, tv_forgot_pass;
    Button btn_login;
    static String enter_username2, enter_pwd2;

    static String method_username, method_mobilestatus, method_loginID;
    public static String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        method_username=   Methods.getFULLNAME(MainActivity.this);
        method_mobilestatus=   Methods.getMOBILE_STATUS(MainActivity.this);
        method_loginID=   Methods.getLoginID(MainActivity.this);

        session= Methods.getSession(this);
        if(session==null){
            //     Intent in1 = new Intent(getApplicationContext(), AfterSplash.class);
            //     startActivity(in1);
            //   mProgress.setVisibility(View.GONE);
            //    finish();

        }
        else if(session.equals("zaaaa")){
            Intent in1 = new Intent(getApplicationContext(), DashboardNav.class);
            startActivity(in1);
            //   mProgress.setVisibility(View.GONE);
            finish();
        }

        et_username = findViewById(R.id.id_username);
        et_password = (EditText) findViewById(R.id.id_password);

        tv_signup = findViewById(R.id.id_signup);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   Intent in = new Intent(MainActivity.this, Signup.class);
                Intent in = new Intent(MainActivity.this, InviteVisMain.class);
                startActivity(in);

            }
        });

        tv_forgot_pass = findViewById(R.id.id_forgot_pass);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(in);

            }
        });


        btn_login = findViewById(R.id.id_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    //   if(enter_username2.equals(method_username)) {

                    //  if(method_mobilestatus.equalsIgnoreCase("1")){
                    new Login().execute();
                    //  }
                   /*else if(method_mobilestatus.equalsIgnoreCase("0")) {
                       Intent in = new Intent(MainActivity.this, Signup_VerifyOTP.class);
                       startActivity(in);
                   }*/


                  /* }
                   else {
                       Toast.makeText(getApplicationContext(), "Username did not match with name provided." , Toast.LENGTH_LONG).show();

                   }*/
                }
            }
        });

    }

    private boolean isValid() {

        enter_username2 = et_username.getText().toString();
        enter_pwd2 = et_password.getText().toString();

        if (!isValidEnterEmail(enter_username2)) {
            // et_email.setError("Invalid Format");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_email, (ViewGroup) findViewById(R.id.custom_toast_emailid));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();

            return false;
        }

        if (!isValidEnterPassword(enter_pwd2)) {
            //   et_password.setError("Empty Field");

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_password, (ViewGroup) findViewById(R.id.custom_toast_pass1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();


            return false;
        }
        return true;
    }


    private boolean isValidEnterEmail(String name1) {
        return name1 != null && name1.length() > 0;
        /*String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email1);
        return matcher.matches();*/
    }

    private boolean isValidEnterPassword(String pwd1) {
        return pwd1 != null && pwd1.length() > 0;
    }


    public class Login extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(Constant.BASE_API_URL_SERVICE+"login"); // here is your URL path

                JSONObject postDataParams = new JSONObject();

// {"user_name":"aryan","password":"12345"}
                postDataParams.put("user_name", enter_username2);
                postDataParams.put("password", enter_pwd2);


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.addRequestProperty("authorization","Basic YWRtaW46MTIzNA==");
                conn.setRequestMethod("POST");
                conn.addRequestProperty("x-api-key","GenXapi@2018");
                conn.setRequestProperty("Accept-Language", "UTF-8");


                conn.setDoInput(true);
                conn.setDoOutput(true);

               /* OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();*/


                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(postDataParams.toString());
                outputStreamWriter.flush();
                int responseCode = conn.getResponseCode();

                //    if (responseCode == HttpsURLConnection.HTTP_OK) {

                System.out.println("5225Sending 'POST' request to URL : " + url);
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

                //   } else {
                //       return new String("false : " + responseCode);
                //   }
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

            /*Response:
{
    "status": true,
    "result": {
        "full_name": "Anhi",
        "email": "anandt@gmai9"
    },
    "message": "Result Provided"
}
*/

            /*{
    "status": true,
    "result": {
        "id": "17",
        "full_name": "",
        "email": "anagmail.com",
        "mobile": "99114",
        "fio_id": "3224",
        "email_status": "0",
        "mobile_status": "0"
    },
    "message": "Result Provided"
}*/


            try {
                JSONObject json = new JSONObject(newresult);
                String result_data=json.getString("status");
                String result_data2=json.getString("message");
                if(result_data.equals("true")){
                    //    Toast.makeText(getApplicationContext(), result_data2 , Toast.LENGTH_LONG).show();


                    JSONObject json2 = json.getJSONObject("result");
                    String st_id=json2.getString("id");
                    String st_full_name=json2.getString("full_name");
                    String st_email=json2.getString("email");
                    String st_mobile=json2.getString("mobile");
                    String st_fio_id=json2.getString("fio_id");
                    String st_smgid=json2.getString("smgid");
                    String st_email_status=json2.getString("email_status");
                    String st_mobile_status=json2.getString("mobile_status");
                    String st_address=json2.getString("address");

                    Methods.saveLoginID(MainActivity.this,st_id);
                    Methods.saveIDLogin(MainActivity.this,st_smgid);
                    Methods.saveSession(MainActivity.this,"zaaaa");

                    Methods.saveLoginName(MainActivity.this,st_full_name);
                    Methods.saveLoginEmail(MainActivity.this,st_email);
                    Methods.saveLoginMobile(MainActivity.this,st_mobile);
                    Methods.saveLoginFIO_ID(MainActivity.this,st_fio_id);
                    Methods.saveLoginEmailStatus(MainActivity.this,st_email_status);
                    Methods.saveLoginMobileStatus(MainActivity.this,st_mobile_status);
                    Methods.saveLoginAddress(MainActivity.this,st_address);


                    System.out.println("RESPONSE_loginn2"+st_smgid);
                    System.out.println("RESPONSE_loginn2"+st_full_name);
                    System.out.println("RESPONSE_loginn2"+st_email);
                    System.out.println("RESPONSE_loginn2"+st_mobile);
                    System.out.println("RESPONSE_loginn2"+st_fio_id);
                    System.out.println("RESPONSE_loginn2"+st_email_status);
                    System.out.println("RESPONSE_loginn2"+st_mobile_status);


                     /*if(st_mobile_status.equalsIgnoreCase("0")) {
                        Toast.makeText(getApplicationContext(), "Mobile number is not verified!" , Toast.LENGTH_LONG).show();
                        Intent in = new Intent(MainActivity.this, Signup_VerifyOTP.class);
                        startActivity(in);
                        }
                        else if(st_address.equalsIgnoreCase("0")) {
                         Toast.makeText(getApplicationContext(), "Address not added", Toast.LENGTH_LONG).show();
                         Intent in = new Intent(MainActivity.this, Signup_after.class);
                         startActivity(in);
                         }
                         else {
                             Intent in1 = new Intent(getApplicationContext(), DashboardNav.class);
                             startActivity(in1);
                             finish();
                             }*/

                    Intent in1 = new Intent(getApplicationContext(), DashboardNav.class);
                    startActivity(in1);
                    finish();


                }
                else {
                    Toast.makeText(getApplicationContext(), result_data2 , Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        // System.exit(1);

        this.finish();
        super.onBackPressed();
    }


    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                // do something here
                System.exit(1);

                return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
