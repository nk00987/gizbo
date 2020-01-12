package gizbo.com.gizbo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gizbo.com.gizbo.Actor.ImplementationApiNav;
import gizbo.com.gizbo.Utils.Constant;
import gizbo.com.gizbo.Utils.CookieStore;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by on 1/8/2020.
 */

public class ArrList extends AppCompatActivity {

    static JSONObject jsonObject;
    static private ArrayList<ImplementationApiNav> societylist_list, residencelist_list;
    static String resultdata_societylist,   resultdata_Residencelist;
    private static OkHttpClient client;
    static private MyArrayAdapterSocietylist adapter_societylist;
    static private MyArrayAdapterResidencelist adapter_residencelist;
    private ListView gridview_societylist, gridview_residencelist;
    public static final String TAG = "TAG";
    private static Response response, response2;

    static String strr_cid;
    static String societylist_url, residencelist_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implementation_apis);



        societylist_url = "https://www.fioho.com/getSocietyList/112";
        residencelist_url = Constant.BASE_API_URL_GENXDEALS+"getsocietyresidencelist/833//29/1";



        client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .cookieJar(new CookieStore())
                .build();

        societylist_list = new ArrayList<>();
        adapter_societylist = new MyArrayAdapterSocietylist(this, societylist_list);
        gridview_societylist = (ListView) findViewById(R.id.lv_societylist);
        gridview_societylist.setAdapter(adapter_societylist);


        residencelist_list = new ArrayList<>();
        adapter_residencelist = new MyArrayAdapterResidencelist(this, residencelist_list);
        gridview_residencelist = (ListView) findViewById(R.id.lv_residencelist);
        gridview_residencelist.setAdapter(adapter_residencelist);


        // new GetSocietyList().execute();
        new GetResidencelist().execute();


    }


    // ___________________ SocietyList ___________________

    class GetSocietyList extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ArrList.this);
            dialog.setMessage("Loading...");

            dialog.show();
            dialog.setCancelable(false);
        }
        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            jsonObject = getDataFromWeb_Societylist();


            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {

                        resultdata_societylist=jsonObject.getString("status");
                        JSONArray array = jsonObject.getJSONArray("result");

                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                ImplementationApiNav actor = new ImplementationApiNav();
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String soid1 = innerObject.getString("soid");
                                String sid1 = innerObject.getString("sid");
                                String cid1 = innerObject.getString("cid");
                                String lid1 = innerObject.getString("lid");
                                String societys1 = innerObject.getString("societys");
                                String date1 = innerObject.getString("date");

                                actor.setSoid(soid1);
                                actor.setSid(sid1);
                                actor.setCcid(cid1);
                                actor.setLid(lid1);
                                actor.setsocietys(societys1);
                                actor.setdate(date1);

                                societylist_list.add(actor);
                                System.out.println("arraylist_name1"+cid1);
                            }
                        }
                    }
                } else {
                }
            } catch (JSONException je) {
                Log.i(ArrList.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if (societylist_list.size() > 0) {
                adapter_societylist.notifyDataSetChanged();
            } else {
            }
        }
    }
    public static JSONObject getDataFromWeb_Societylist() {
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .addHeader("authorization", "Basic YWRtaW46MTIzNA==")
                    .addHeader("x-api-key", "")
                    .url(societylist_url)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
    public static class MyArrayAdapterSocietylist extends ArrayAdapter<ImplementationApiNav> {

        List<ImplementationApiNav> modelList;
        Context context;
        private LayoutInflater mInflater;

        // Constructors
        public MyArrayAdapterSocietylist(Context context, List<ImplementationApiNav> objects) {
            super(context, 0, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            modelList = objects;
        }

        @Override
        public ImplementationApiNav getItem(int position) {
            return modelList.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                View view = mInflater.inflate(R.layout.parent_layout, parent, false);
                vh = ViewHolder.create((LinearLayout) view);
                view.setTag(vh);

            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            final ImplementationApiNav item = getItem(position);
            assert item != null;

            vh.c_cid.setText(item.getSoid());
            vh.c_cname.setText(item.getsocietys());
            vh.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strr_cid = item.getCcid();
                    Toast.makeText(getContext(), item.getSoid(), Toast.LENGTH_LONG).show();
                }
            });

            return vh.rootView;
        }

        private static class ViewHolder {
            public final LinearLayout rootView;
            public final TextView c_cid;
            public final TextView c_cname;

            private ViewHolder(LinearLayout rootView, TextView c_cid, TextView c_cname) {
                this.rootView = rootView;
                this.c_cid = c_cid;
                this.c_cname = c_cname;
            }

            public static ViewHolder create(LinearLayout rootView) {
                TextView c_cid = (TextView) rootView.findViewById(R.id.id_cityid);
                TextView c_cname = (TextView) rootView.findViewById(R.id.id_cityname);

                return new ViewHolder(rootView,  c_cid,c_cname);
            }
        }
    }




    // ___________________ Residencelist____________________

    class GetResidencelist extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ArrList.this);
            dialog.setMessage("Loading...");

            dialog.show();
            dialog.setCancelable(false);
        }
        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            jsonObject = getDataFromWeb_Residencelist();


            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {

                        resultdata_Residencelist=jsonObject.getString("status");
                        JSONArray array = jsonObject.getJSONArray("result");

                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                ImplementationApiNav actor = new ImplementationApiNav();
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String soid1 = innerObject.getString("fid");
                                String sid1 = innerObject.getString("first_name");
                                String cid1 = innerObject.getString("email_id");
                                String lid1 = innerObject.getString("mobile");
                                String societys1 = innerObject.getString("flat_tower");

                                actor.setfid(soid1);
                                actor.setfirst_name(sid1);
                                actor.setemail_id(cid1);
                                actor.setMobile(lid1);
                                actor.setFlat_tower(societys1);

                                societylist_list.add(actor);
                                System.out.println("arraylist_name1"+cid1);
                            }
                        }
                    }
                } else {
                }
            } catch (JSONException je) {
                Log.i(ArrList.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if (residencelist_list.size() > 0) {
                adapter_residencelist.notifyDataSetChanged();
                System.out.println("8888_4");
            } else {
            }
        }
    }
    public static JSONObject getDataFromWeb_Residencelist() {
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .addHeader("authorization", "Basic YWRtaW46MTIzNA==")
                    .addHeader("x-api-key", "G")
                    .url(residencelist_url)
                    .build();

            System.out.println("ghghg"+residencelist_url);

            System.out.println("8888_5");
            response2 = client.newCall(request).execute();
            return new JSONObject(response2.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
    public static class MyArrayAdapterResidencelist extends ArrayAdapter<ImplementationApiNav> {

        List<ImplementationApiNav> modelList;
        Context context;
        private LayoutInflater mInflater;

        // Constructors
        public MyArrayAdapterResidencelist(Context context, List<ImplementationApiNav> objects) {
            super(context, 0, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            modelList = objects;
            System.out.println("8888_6");
        }

        @Override
        public ImplementationApiNav getItem(int position) {
            return modelList.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                View view = mInflater.inflate(R.layout.parent_layout, parent, false);
                vh = ViewHolder.create((LinearLayout) view);
                view.setTag(vh);

            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            System.out.println("8888_7");
            final ImplementationApiNav item = getItem(position);
            assert item != null;

            vh.c_cid.setText(item.getfid());
            System.out.println("jhngjhgj"+item.getfirst_name());

            vh.c_cname.setText(item.getfirst_name());
            vh.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strr_cid = item.getfirst_name();
                    Toast.makeText(getContext(), item.getSoid(), Toast.LENGTH_LONG).show();
                }
            });
            System.out.println("8888_8");

            return vh.rootView;
        }

        private static class ViewHolder {
            public final LinearLayout rootView;
            public final TextView c_cid;
            public final TextView c_cname;

            private ViewHolder(LinearLayout rootView, TextView c_cid, TextView c_cname) {
                this.rootView = rootView;
                this.c_cid = c_cid;
                this.c_cname = c_cname;
            }

            public static ViewHolder create(LinearLayout rootView) {
                TextView c_cid = (TextView) rootView.findViewById(R.id.id_cityid);
                TextView c_cname = (TextView) rootView.findViewById(R.id.id_cityname);
                System.out.println("8888_9");

                return new ViewHolder(rootView,  c_cid,c_cname);
            }
        }
    }




}
