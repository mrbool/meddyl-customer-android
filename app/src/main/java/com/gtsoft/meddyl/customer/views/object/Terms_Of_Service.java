package com.gtsoft.meddyl.customer.views.object;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

public class Terms_Of_Service extends View_Controller
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_of_service);

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "TERMS OF SERVICE";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        WebView myWebView = (WebView) findViewById(R.id.wbvTerms);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(system_controller.getSystemSettingsObj().getCustomerAppTerms());
    }
}
