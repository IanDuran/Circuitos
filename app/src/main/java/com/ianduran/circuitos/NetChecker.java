package com.ianduran.circuitos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetChecker {
    public static boolean isInternetAvailable(Context context){
        boolean isInternetWorking = false;
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if(info != null)
            isInternetWorking = true;
        return isInternetWorking;
    }
}
