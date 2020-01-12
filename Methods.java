package gizbo.com.gizbo;

import android.content.Context;
import android.content.SharedPreferences;

public class Methods {

    public static boolean saveSession(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("SESSION",cif).commit();
    }
    public static String getSession(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("SESSION", null);
    }


    public static boolean saveID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("ID",cif).commit();
    }
    public static String getID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("ID", null);
    }
    public static boolean saveIDLogin(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("IDLogin",cif).commit();
    }
    public static String getIDLogin(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("IDLogin", null);
    }

    public static boolean saveLoginID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LoginID",cif).commit();
    }
    public static String getLoginID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginID", null);
    }



    //  LoginFIO_ID
    public static boolean saveLoginFIO_ID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LoginFIO_ID",cif).commit();
    }
    public static String getLoginFIO_ID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginFIO_ID", null);
    }


    //  LoginName
    public static boolean saveLoginName(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("Name",cif).commit();
    }
    public static String getLoginName(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("Name", null);
    }
// saveLoginEmail
public static boolean saveLoginEmail(Context context, String cif){
    SharedPreferences sharedPreferences=getPreferances(context);
    return sharedPreferences.edit().putString("LoginEmail",cif).commit();
}
    public static String getLoginEmail(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginEmail", null);
    }

    //   saveLoginEmailStatus
    public static boolean saveLoginEmailStatus(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LoginEmailStatus",cif).commit();
    }
    public static String getLoginEmailStatus(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginEmailStatus", null);
    }

    //  saveLoginMobileStatus
    public static boolean saveLoginMobileStatus(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LoginMobileStatus",cif).commit();
    }
    public static String getLoginMobileStatus(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginMobileStatus", null);
    }


    //saveLoginMobile
public static boolean saveLoginMobile(Context context, String cif){
    SharedPreferences sharedPreferences=getPreferances(context);
    return sharedPreferences.edit().putString("LoginMobile",cif).commit();
}
    public static String getLoginMobile(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginMobile", null);
    }


    //  saveLoginAddress
    public static boolean saveLoginAddress(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LoginAddress",cif).commit();
    }
    public static String getLoginAddress(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LoginAddress", null);
    }



    public static boolean saveFULLNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("FULLNAME",cif).commit();
    }
    public static String getFULLNAME(MainActivity context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("FULLNAME", null);
    }


    public static boolean saveForgotPassNumber(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("ForgotPassNumber",cif).commit();
    }
    public static String getForgotPassNumber(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("ForgotPassNumber", null);
    }






    public static boolean saveEMAILID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("EMAILID",cif).commit();
    }
    public static String getEMAILID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("EMAILID", null);
    }


    public static boolean saveMOBILE(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MOBILE",cif).commit();
    }
    public static String getMOBILE(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MOBILE", null);
    }



    public static boolean saveFIO_ID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("FIO_ID",cif).commit();
    }
    public static String getFIO_ID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("FIO_ID", null);
    }

    public static boolean saveUSERNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("USERNAME",cif).commit();
    }
    public static String getUSERNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("USERNAME", null);
    }

    public static boolean saveRESIDENTIAL(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("RESIDENTIAL",cif).commit();
    }
    public static String getRESIDENTIAL(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("RESIDENTIAL", null);
    }


    public static boolean saveSTATUS(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("STATUS",cif).commit();
    }
    public static String getSTATUS(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("STATUS", null);
    }


    public static boolean saveMOBILE_STATUS(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MOBILE_STATUS",cif).commit();
    }
    public static String getMOBILE_STATUS(MainActivity context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MOBILE_STATUS", null);
    }


    public static boolean saveEMAIL_STATUS(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("EMAIL_STATUS",cif).commit();
    }
    public static String getEMAIL_STATUS(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("EMAIL_STATUS", null);
    }

    public static boolean saveSMGID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("SMGID",cif).commit();
    }
    public static String getSMGID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("SMGID", null);
    }




    public static boolean saveIMAGE(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("IMAGE",cif).commit();
    }
    public static String getIMAGE(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("IMAGE", null);
    }



    public static boolean saveVERIFICATION_KEY(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("VERIFICATION_KEY",cif).commit();
    }
    public static String getVERIFICATION_KEY(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("VERIFICATION_KEY", null);
    }


    public static boolean saveMSG(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MSG",cif).commit();
    }
    public static String getMSG(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MSG", null);
    }


    public static boolean saveFIRSTNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("FIRSTNAME",cif).commit();
    }
    public static String getFIRSTNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("FIRSTNAME", null);
    }





    public static boolean saveCITY_CID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("CITY_CID",cif).commit();
    }
    public static String getCITY_CID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("CITY_CID", null);
    }



    public static boolean saveAddressId_IID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("AddressId_IID",cif).commit();
    }
    public static String getAddressId_IID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("AddressId_IID", null);
    }


    public  static SharedPreferences getPreferances(Context context){
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }
}

