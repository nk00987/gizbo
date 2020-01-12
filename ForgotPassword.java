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
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForgotPassword extends AppCompatActivity {


   EditText et_getMobileNum;
    Button btn_submit;

    static String enter_number;
    static  String ssaveForgotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        et_getMobileNum = (EditText) findViewById(R.id.id_getMobileNum);
        btn_submit = (Button) findViewById(R.id.id_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    new SendNumber_Post().execute();
                }

              //  Intent it = new Intent(ForgotPassword.this, ForgotPass_VerifyOTP.class);
              //  startActivity(it);
            }
        });

    }



    private boolean isValid() {

        enter_number = et_getMobileNum.getText().toString();


        if (!isValidEnterMobile(enter_number)) {

            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.custom_toast_mobile, (ViewGroup) findViewById(R.id.custom_toast_mobile1));
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();

            return false;
        }

        return true;
    }


    private boolean isValidEnterMobile(String mob1) {
        return mob1 != null && mob1.length() > 9 && mob1.length() < 13;
    }







    public class SendNumber_Post extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(ForgotPassword.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);

        }

        protected String doInBackground(String... arg0) {

            try {

                // resend
                URL url = new URL(Constant.BASE_API_URL_EALS+"/forgotpasswordbymobile"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
// {"mobile":"9944"}
                postDataParams.put("mobile", enter_number);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.addRequestProperty("authorization","Basic YWRtaW46MTIzNA==");
                conn.setRequestMethod("POST");
                conn.addRequestProperty("x-api-key","api@2018");
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

                /*{ "status": true,
                    "result": "OTP sent to your Mobile/Email",
                    "message": "OTP sent to your Mobile/Email" }*/



            try {
                JSONObject json = new JSONObject(newresult);
                String result_data1=json.getString("status");
                String result_data3=json.getString("msg");

                if(result_data1.equals("1")){
                    Toast.makeText(getApplicationContext(), result_data3, Toast.LENGTH_LONG).show();
                    Methods.saveForgotPassNumber(ForgotPassword.this,enter_number);


                   System.out.println("RESPONSE_forgotpassword"+result_data1+result_data3);
                    Intent in1 = new Intent(getApplicationContext(), Forgetpass_VerifyOTPnew.class);
                    in1.putExtra("mob", ssaveForgotNumber);

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


}
