package gizbo.com.gizbo.Preap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import gizbo.com.gizbo.Utils.CookieStore;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created  on 1/10/2020.
 */

public class Paren1 extends AppCompatActivity {

    private static OkHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity__view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_empl);
        setSupportActionBar(toolbar);
        client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .cookieJar(new CookieStore())
                .build();

        new GetParent().execute();


    }
    class GetParent extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(D.this);
            dialog.setMessage("Loading...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            jsonObject = Dasl.getDataFromWeb();

            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        int lenArray = array.length();
                        count_al = array.length();


                        count_str = String.valueOf(count_al);


                        System.out.println("dddddddddd"+count_al);
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                ParentNav actor = new ParentNav();
//  [{"id":1,"customer_id":"K","user_name":"K","img_name":"k","image":"custom.png",
// "created_at":"2018-03-27 10:45:54","updated_at":"2018-03-27 10:45:54","comments":3,"rating":3.5,"usercount":2},
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String id1 = innerObject.getString("id");
                                String customer_id = innerObject.getString("customer_id");
                                String user_name1 = innerObject.getString("customer_name");
                                String img_name1 = innerObject.getString("img_name");
                                String image1 = innerObject.getString("image");
                                String created_at1 = innerObject.getString("created_at");
                                String updated_at1 = innerObject.getString("updated_at");
                                String comments1 = innerObject.getString("comments");
                                strRating1 = innerObject.getString("rating");
                                String usercount1 = innerObject.getString("usercount");
                                String division1 = innerObject.getString("division");
                                String zone1 = innerObject.getString("zone");
                                String sales_offc_r1 = innerObject.getString("salse_office");
                                String distribution_channel1 = innerObject.getString("distribution_channel");


                                /*
                                String sales_dist1 = innerObject.getString("sales_dist");
                                String nameadd1 = innerObject.getString("nameadd");
                                String city1 = innerObject.getString("city");
                                String distribution_channel1 = innerObject.getString("distribution_channel");


                                String long1 = innerObject.getString("long");
                                String lat1 = innerObject.getString("lat");
                                String lm_mt1 = innerObject.getString("lm_mt");
                                String lm_inr1 = innerObject.getString("lm_inr");
                                String l6m_mt1 = innerObject.getString("l6m_mt");
                                String l6m_inr1 = innerObject.getString("l6m_inr");
*/


                                if(strRating1.equals("null")){
                                    String getRat = "0";
                                    rating1 = getRat;
                                }
                                else{
                                    rating1 =strRating1;
                                }



                                // String id1 = innerObject.getString("MenuID");
                                //  String id1 = innerObject.getString("SmallImage");

                                actor.setId(id1);
                                actor.setCustomer_id(customer_id);
                                actor.setUser_name1(user_name1);
                                actor.setImg_name1(img_name1);
                                actor.setImage1(image1);
                                actor.setCreated_at1(created_at1);
                                actor.setUpdated_at1(updated_at1);
                                actor.setComments1(comments1);
                                actor.setRating1(rating1);
                                actor.setUsercount1(usercount1);
                                actor.setDivision1(division1);
                                actor.setZone1(zone1);
                                actor.setSalesOffc1(sales_offc_r1);
                                actor.setDistribution_channel(distribution_channel1);

                                /*actor.setSales_dist(sales_dist1);
                                actor.setDistribution_channel(distribution_channel1);
                                actor.setNameadd(nameadd1);
                                actor.setCity(city1);

                                actor.setLong1(long1);
                                actor.setLat1(lat1);
                                actor.setLm_mt1(lm_mt1);
                                actor.setLm_inr1(lm_inr1);
                                actor.setLl6m_mt1(l6m_mt1);
                                actor.setLl6m_inr1(l6m_inr1);
                                productlist.add(actor);*/




                                productlist.add(actor);


                                System.out.println("arraylist_name1"+user_name1);

                            }
                            Collections.reverse(productlist);
                        }
                    }


                } else {
                }
            } catch (JSONException je) {
                Log.i(Dal.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if (productlist.size() > 0) {
                productadapter.notifyDataSetChanged();
                new GetData_state().execute();
            } else {
                //    Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
                //  Toast.makeText(getApplicationContext(), "No Data Found!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(final_url)
                    .build();
            System.out.println("nkkkkkkk_dashboard_emplall"+Constant.BASE_API_URL+"api/showallphoto/"+gethierarchy_usernamee);

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static String GET(OkHttpClient client, HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                /// .url(Constant.BASE_API_URL+"DeletefromCart/"+email.replace(".",",")+"/"+prodcut_id)
                .build();
        Response responseOK = client.newCall(request).execute();
        return responseOK.body().string();
    }
    public static HttpUrl buildURL() {
        return new HttpUrl.Builder()
                .scheme("https") //http
                .host("www.a.com")
                .addPathSegment("a")//adds "/pathSegment" at the end of hostname
                .addQueryParameter("a", "a") //add query parameters to the URL
                .addEncodedQueryParameter("a", "a")//add encoded query parameters to the URL
                .build();
    }
    public static class MyArrayAdapter extends ArrayAdapter<ParentNav> {

        List<ParentNav> modelList;
        Context context;
        private LayoutInflater mInflater;

        // Constructors
        public MyArrayAdapter(Context context, List<ParentNav> objects) {
            super(context, 0, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            modelList = objects;
        }

        @Override
        public ParentNav getItem(int position) {
            return modelList.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final MyArrayAdapter.ViewHolder vh;
            if (convertView == null) {
                View view = mInflater.inflate(R.layout.parent_layout, parent, false);
                vh = Dashboard_empl.MyArrayAdapter.ViewHolder.create((LinearLayout) view);
                view.setTag(vh);





            } else {
                vh = (MyArrayAdapter.ViewHolder) convertView.getTag();
            }

            final ParentNav item = getItem(position);
            assert item != null;




            vh.imagevw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inaction3 = new Intent(v.getContext(),ParticularImageEmpl.class);
                    // inaction3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    inaction3.putExtra("nameeee", item.getUser_name1());
                    inaction3.putExtra("idddd", item.getId());
                    inaction3.putExtra("commentsss", item.getComments1());
                    inaction3.putExtra("empl_address", item.getNameadd());
                    inaction3.putExtra("empl_city", item.getCity());
                    inaction3.putExtra("createdattt", item.getCreated_at1());
                    inaction3.putExtra("imageeee22","http://.com/"+item.getImage1());
                    inaction3.putExtra("divisionnn", item.getDivision1());
                    inaction3.putExtra("zoneee", item.getZone1());
                    inaction3.putExtra("salesofccc", item.getSalesOffc1());


                    inaction3.putExtra("customer_id", item.getCustomer_id());
                    inaction3.putExtra("img_name1", item.getImg_name1());
                    inaction3.putExtra("updated_at1", item.getUpdated_at1());
                    inaction3.putExtra("strRating1", item.getRating1());
                    inaction3.putExtra("usercount1", item.getUsercount1());
                    inaction3.putExtra("distribution_channel1", item.getDistribution_channel());

                    System.out.println("c_000city1__"+item.getCity()+"c_000address1__"+item.getNameadd());


                            /*    String customer_id = innerObject.getString("customer_id");
                                String img_name1 = innerObject.getString("img_name");
                                String updated_at1 = innerObject.getString("updated_at");
                                strRating1 = innerObject.getString("rating");
                                String usercount1 = innerObject.getString("usercount");
                                String distribution_channel1 = innerObject.getString("distribution_channel");
*/

                    v.getContext().startActivity(inaction3);


                }
            });


            vh.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   /* Intent inaction3 = new Intent(v.getContext(),UploadImage.class);


                    inaction3.putExtra("empl_Customer_id", item.getCustomer_id());
                    inaction3.putExtra("empl_name", item.getUser_name1());
                    inaction3.putExtra("empl_address", item.getNameadd());
                    inaction3.putExtra("empl_city", item.getCity());
                    inaction3.putExtra("empl_Distribution_channel", item.getDistribution_channel());
                    inaction3.putExtra("empl_Division", item.getDivision1());
                    inaction3.putExtra("empl_Sales_office", item.getSalesOffc1());
                    inaction3.putExtra("empl_dist", item.getZone1());

                    System.out.println("thursday1__"+item.getCustomer_id()+item.getUser_name1()+
                            item.getNameadd()+item.getCity()+ item.getDistribution_channel()
                   +item.getDivision1()+item.getSalesOffc1()+item.getZone1()


                    );




                *//*    inaction3.putExtra("empl_dist", item.getSales_dist());
                    inaction3.putExtra("empl_lat1", item.getLat1());
                    inaction3.putExtra("empl_long1", item.getLong1());
                    inaction3.putExtra("empl_lm_mt1", item.getLm_mt1());
                    inaction3.putExtra("empl_lm_inr1", item.getLm_inr1());
                    inaction3.putExtra("empl_l6m_mt1", item.getLl6m_mt1());
                    inaction3.putExtra("empl_l6m_inr1", item.getLl6m_inr1());*//*






                    v.getContext().startActivity(inaction3);
*/

                }
            });





            vh.name.setText(item.getUser_name1());
            // vh.createdat.setText("Created At: "+item.getCreated_at1());
            // vh.updatedate.setText("Updated At: "+item.getUpdated_at1());
            vh.comments.setText(item.getComments1());


            if(item.getDivision1().equals("50")){
                vh.division_r.setText("Roofing");
            }
            else if(item.getDivision1().equals("51")||item.getDivision1().equals("52")) {
              /*  vh.division_r.setText("Panels");
            }
            else if(item.getDivision1().equals("52")) {*/
                vh.division_r.setText("Boards");
            }



            vh.zone_r.setText(item.getZone1());
            vh.sales_offc_r.setText("("+item.getSalesOffc1()+")");
            vh.customerid.setText(item.getCustomer_id());








            String date_createdat =item.getCreated_at1();
            SimpleDateFormat input_createdat = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat output_createdat = new SimpleDateFormat("dd-mm-yyyy");
            try {
                Date oneWayTripDate_createdat = input_createdat.parse(date_createdat);                 // parse input
                vh.createdat.setText("Created At: "+output_createdat.format(oneWayTripDate_createdat));    // format output
            } catch (ParseException e) {
                e.printStackTrace();
            }



            String date_updatedate =item.getUpdated_at1();
            SimpleDateFormat input_updatedate = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat output_updatedate = new SimpleDateFormat("dd-mm-yyyy");
            try {
                Date oneWayTripDate_updatedate = input_updatedate.parse(date_updatedate);                 // parse input
                vh.updatedate.setText("Updated on: "+output_updatedate.format(oneWayTripDate_updatedate));    // format output
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double i2= Double.parseDouble(item.getRating1());
            vh.ratingBar.setText(new DecimalFormat("##.##").format(i2));



            Picasso.with(context).load("http:.com/"+item.getImage1()).into(vh.imagevw);








            return vh.rootView;
        }

        private static class ViewHolder {
            public final LinearLayout rootView;
            public final TextView name;
            public final TextView createdat;
            public final TextView updatedate;
            public final TextView comments;
            public final TextView ratingBar;
            public final TextView deletingBar;
            public final ImageView imagevw;
            public final TextView division_r;
            public final TextView zone_r;
            public final TextView customerid;
            public final TextView sales_offc_r;


            private ViewHolder(LinearLayout rootView, TextView name, TextView createdat, TextView updatedate, TextView comments, TextView ratingBar, TextView deletingBar, ImageView imagevw, TextView division_r, TextView zone_r, TextView customerid,TextView sales_offc_r) {
                this.rootView = rootView;
                this.name = name;
                this.createdat = createdat;
                this.updatedate = updatedate;
                this.comments = comments;
                this.ratingBar = ratingBar;
                this.deletingBar = deletingBar;

                this.imagevw = imagevw;
                this.division_r = division_r;
                this.zone_r = zone_r;
                this.customerid = customerid;
                this.sales_offc_r = sales_offc_r;

            }

            public static MyArrayAdapter.ViewHolder create(LinearLayout rootView) {
                TextView name = (TextView) rootView.findViewById(R.id.text567);
                TextView createdat = (TextView) rootView.findViewById(R.id.createdat);
                TextView updatedate = (TextView) rootView.findViewById(R.id.updatedate);
                TextView comments = (TextView) rootView.findViewById(R.id.comments_id);
                TextView ratingBar = (TextView)rootView.findViewById(R.id.ratingBar);
                TextView deletingBar = (TextView)rootView.findViewById(R.id.deleteBar);

                ImageView imagevw = (ImageView) rootView.findViewById(R.id.text_town);
                TextView division_r = (TextView) rootView.findViewById(R.id.text_division_id);
                TextView zone_r = (TextView) rootView.findViewById(R.id.text_zone_id);
                TextView customerid = (TextView) rootView.findViewById(R.id.customerid_id);


                TextView sales_offc_r = (TextView) rootView.findViewById(R.id.text_sales_office_id);

                //TextView off = (TextView) rootView.findViewById(R.id.count);

                return new MyArrayAdapter.ViewHolder(rootView,  name,createdat,updatedate,comments,ratingBar,deletingBar, imagevw,division_r,zone_r, customerid,sales_offc_r);
            }
        }
    }

}
