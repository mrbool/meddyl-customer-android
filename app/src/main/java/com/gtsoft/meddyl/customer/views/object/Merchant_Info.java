package com.gtsoft.meddyl.customer.views.object;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Merchant;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import java.io.File;


public class Merchant_Info extends View_Controller implements OnMapReadyCallback
{
    private String address_1, address_2, phone;
    private Merchant merchant_obj;

    private GoogleMap mpvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_info);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "COMPANY INFO";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        merchant_obj = deal_controller.getDealObj().getMerchantContactObj().getMerchantObj();

        address_1 = merchant_obj.getAddress1() + " " + merchant_obj.getAddress2();
        address_2 = merchant_obj.getZipCodeObj().getCityObj().getCity() + ", " + merchant_obj.getZipCodeObj().getCityObj().getStateObj().getAbbreviation() + "  " + merchant_obj.getZipCodeObj().getZipCode();
        phone = Utils.Format_Phone(merchant_obj.getPhone());

        String neighborhood = "";
        if(merchant_obj.getNeighborhoodObj().getNeighborhoodId() == 0)
        {
            neighborhood = merchant_obj.getZipCodeObj().getCityObj().getCity();
        }
        else
        {
            neighborhood = merchant_obj.getNeighborhoodObj().getNeighborhood();
        }

        ((GTTextView) findViewById(R.id.txvCompanyName)).setText(merchant_obj.getCompanyName());
        ((GTTextView) findViewById(R.id.txvNeighborhood)).setText(neighborhood);
        ((GTTextView) findViewById(R.id.txvDescription)).setText(merchant_obj.getDescription());
        ((GTTextView) findViewById(R.id.txvAddress1)).setText(address_1);
        ((GTTextView) findViewById(R.id.txvAddress2)).setText(address_2);

        Set_Logo_And_Stars();

        SupportMapFragment map_fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mpvMap);
        map_fragment.getMapAsync(this);

        GTTextView txvDirections = (GTTextView) findViewById(R.id.txvDirections);
        txvDirections.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String uriString = "geo:0,0?q=" + address_1 + address_2;
                Uri uri = Uri.parse("google.navigation:q=" + address_1 + address_2);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        GTTextView txvPhone = (GTTextView) findViewById(R.id.txvPhone);
        txvPhone.setText(phone);
        txvPhone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }
        });

        GTTextView txvWebSite = (GTTextView) findViewById(R.id.txvWebSite);
        txvWebSite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String url = merchant_obj.getWebsite();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        mpvMap = map;

        LatLng coordinates = Utils.Get_Location_From_Address(this, address_1 + " " + address_2);
        mpvMap.addMarker(new MarkerOptions().position(coordinates).title(merchant_obj.getCompanyName()));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(12.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.moveCamera(cameraUpdate);
    }

    public void Set_Logo_And_Stars()
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int image_width = metrics.widthPixels/2;
        int image_height = Double.valueOf(image_width * .70).intValue();

        /* customer logo image */
        ImageView imvLogo = (ImageView) findViewById(R.id.imvLogo);
        imvLogo.getLayoutParams().width = image_width;
        imvLogo.getLayoutParams().height = image_width;
        imvLogo.requestLayout();

        AQuery aq = new AQuery(getApplicationContext());
        aq.id(imvLogo).image(merchant_obj.getImage());

        /* stars image */
        ImageView imvStars = (ImageView) findViewById(R.id.imvStars);

        if(merchant_obj.getMerchantRatingObj() == null)
        {
            imvStars.setVisibility(View.GONE);
        }
        else
        {
            AQuery aq_stars = new AQuery(getApplicationContext());
            int resource_id = (getApplicationContext()).getResources().getIdentifier(merchant_obj.getMerchantRatingObj().getImage().toLowerCase().replace(".png","") , "drawable", (getApplicationContext().getPackageName()));
            aq_stars.id(imvStars).image(resource_id);

            ViewGroup.LayoutParams params_stars = (ViewGroup.LayoutParams)imvStars.getLayoutParams();
            params_stars.width = image_width;
            params_stars.height = image_width/5;
            imvStars.setLayoutParams(params_stars);
        }
    }

    public void Debug()
    {

    }

}
