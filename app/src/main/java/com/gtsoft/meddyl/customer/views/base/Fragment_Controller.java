package com.gtsoft.meddyl.customer.views.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtsoft.meddyl.customer.controller.object.Customer_Controller;
import com.gtsoft.meddyl.customer.controller.object.Deal_Controller;
import com.gtsoft.meddyl.customer.controller.object.System_Controller;
import com.gtsoft.meddyl.customer.model.object.System_Error;
import com.gtsoft.meddyl.customer.model.object.System_Successful;


public class Fragment_Controller extends Fragment
{
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    protected int request_times=0;

    protected static boolean debug = false;
    protected boolean successful;
    protected System_Successful system_successful_obj;
    protected System_Error system_error_obj;
    protected String error_title;
    protected String error_message;

    protected Customer_Controller customer_controller;
    protected Deal_Controller deal_controller;
    protected System_Controller system_controller;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return null;
    }

    public void Show_Alert_Dialog(String title, String message, DialogInterface.OnClickListener okListener)
    {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton(title, okListener)
                .create()
                .show();
    }

    public int Check_Permission(final String permission, Boolean enforce, int call_times)
    {
        int has_permission = ActivityCompat.checkSelfPermission(getActivity(), permission);

        if (has_permission != PackageManager.PERMISSION_GRANTED)
        {
            if (!shouldShowRequestPermissionRationale(permission) && (call_times > 0))
            {
                if(enforce)
                {
                    String title = "";

                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION))
                    {
                        title = "You need to allow GPS access";
                    }
                    else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    {
                        title = "You need to allow storage access";
                    }
                    else if (permission.equals(Manifest.permission.CALL_PHONE))
                    {
                        title = "You need to allow access phone access";
                    }

                    Show_Alert_Dialog("Ok", title,
                            new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getActivity().getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivityForResult(intent, 2);

                                    //requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                }
                else
                {
                    requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
                }

                return has_permission;
            }

            requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
            request_times++;

            return has_permission;
        }

        return has_permission;
    }
}
