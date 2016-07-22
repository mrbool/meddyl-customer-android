package com.gtsoft.meddyl.customer.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
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


}
