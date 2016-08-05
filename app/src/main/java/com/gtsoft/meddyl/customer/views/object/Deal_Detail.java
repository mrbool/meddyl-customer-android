package com.gtsoft.meddyl.customer.views.object;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.model.object.Deal_Payment;
import com.gtsoft.meddyl.customer.model.object.Merchant;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.androidquery.AQuery;

public class Deal_Detail extends View_Controller implements OnMapReadyCallback
{
    private String address_1, address_2, phone;
    private Merchant merchant_obj;
    private Deal deal_obj;

    private Bitmap bitmap;
    private GoogleMap mpvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_detail);

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "DETAILS";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        merchant_obj = deal_controller.getDealObj().getMerchantContactObj().getMerchantObj();
        deal_obj = deal_controller.getDealObj();

        Load_Merchant_Info();
        Load_Deal_Info();
    }

    public void Debug()
    {

    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        mpvMap = map;

        LatLng coordinates = Utils.Get_Location_From_Address(this, address_1 + ", " + address_2);

        mpvMap.addMarker(new MarkerOptions().position(coordinates).title(deal_controller.getDealObj().getMerchantContactObj().getMerchantObj().getCompanyName()));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(12.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.moveCamera(cameraUpdate);
    }

    public void Load_Merchant_Info()
    {
        String neighborhood;

        if(merchant_obj.getNeighborhoodObj().getNeighborhoodId() == 0)
            neighborhood = merchant_obj.getZipCodeObj().getCityObj().getCity();
        else
            neighborhood = merchant_obj.getNeighborhoodObj().getNeighborhood();

        neighborhood = neighborhood + " - " + deal_obj.getDistance().toString() + "mi";

        address_1 = merchant_obj.getAddress1() + " " + merchant_obj.getAddress2();
        address_2 = merchant_obj.getZipCodeObj().getCityObj().getCity() + ", " + merchant_obj.getZipCodeObj().getCityObj().getStateObj().getAbbreviation() + "  " + merchant_obj.getZipCodeObj().getZipCode();
        phone = Utils.Format_Phone(merchant_obj.getPhone());

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int screen_width = metrics.widthPixels;

        /* customer logo image */
        ImageView imvLogo = (ImageView) findViewById(R.id.imvLogo);
        imvLogo.getLayoutParams().width = screen_width/4;
        imvLogo.getLayoutParams().height = screen_width/4;
        imvLogo.requestLayout();
        imvLogo.setVisibility(View.VISIBLE);
        imvLogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //deal_controller.setDealObj(deal_controller.getCertificateObj().getDealObj());

                Intent intent = new Intent(getApplicationContext(), Merchant_Info.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });

        AQuery aq = new AQuery(getApplicationContext());
        aq.id(imvLogo).image(merchant_obj.getImage());

        /* stars image */
        ImageView imvStars = (ImageView) findViewById(R.id.imvStars);

        if(merchant_obj.getMerchantRatingObj() == null || merchant_obj.getMerchantRatingObj().getImage() == null)
        {
            imvStars.setVisibility(View.INVISIBLE);
        }
        else
        {
            AQuery aq_stars = new AQuery(getApplicationContext());
            int resource_id = (getApplicationContext()).getResources().getIdentifier(merchant_obj.getMerchantRatingObj().getImage().toLowerCase().replace(".png","") , "drawable", (getApplicationContext().getPackageName()));
            aq_stars.id(imvStars).image(resource_id);

            ViewGroup.LayoutParams params_stars = (ViewGroup.LayoutParams)imvStars.getLayoutParams();
            params_stars.width = screen_width/3;
            params_stars.height = (screen_width/3)/5;
            imvStars.setLayoutParams(params_stars);
            imvStars.setVisibility(View.VISIBLE);
        }

        /* set text views */
        GTTextView txvCompanyName = (GTTextView) findViewById(R.id.txvCompanyName);
        txvCompanyName.getLayoutParams().width = Double.valueOf(screen_width * .7).intValue();
        txvCompanyName.setText(merchant_obj.getCompanyName());

        GTTextView txvNeighborhood = (GTTextView) findViewById(R.id.txvNeighborhood);
        txvNeighborhood.setText(neighborhood);

        View vw1 = (View)findViewById(R.id.vw1);
        vw1.setVisibility(View.VISIBLE);

        View vw2 = (View)findViewById(R.id.vw2);
        vw2.setVisibility(View.VISIBLE);

        View vw3 = (View)findViewById(R.id.vw3);
        vw3.setVisibility(View.VISIBLE);

        View vw4 = (View)findViewById(R.id.vw4);
        vw4.setVisibility(View.VISIBLE);

        SupportMapFragment map_fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mpvMap);
        map_fragment.getMapAsync(this);

        RelativeLayout rloMapContainer = (RelativeLayout) findViewById(R.id.rloMapContainer);
        rloMapContainer.setVisibility(View.VISIBLE);

        GTTextView txvCompanyInfoLabel = (GTTextView) findViewById(R.id.txvCompanyInfoLabel);
        txvCompanyInfoLabel.setVisibility(View.VISIBLE);

        GTTextView txvAddress1 = (GTTextView) findViewById(R.id.txvAddress1);
        txvAddress1.setText(address_1);

        GTTextView txvAddress2 = (GTTextView) findViewById(R.id.txvAddress2);
        txvAddress2.setText(address_2);

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

    private void Load_Deal_Info()
    {
        SimpleDateFormat date_format =  new SimpleDateFormat("M/d/yyyy");
        date_format.setTimeZone(TimeZone.getTimeZone(deal_obj.getTimeZoneObj().getTimeZone()));
        Date expiration_date = deal_obj.getExpirationDate();

        /* set deal image */
        ImageView imgDealImage = (ImageView) findViewById(R.id.imvDealImage);
        AQuery aq = new AQuery(getApplicationContext());
        aq.id(imgDealImage).image(deal_obj.getImage());

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        imgDealImage.getLayoutParams().width = size.x;
        imgDealImage.getLayoutParams().height = (int)Math.floor(size.x *.7);
        imgDealImage.requestLayout();

        /* set text views */
        GTTextView txvDeal = (GTTextView) findViewById(R.id.txvDeal);
        txvDeal.setText(deal_obj.getDeal());

        GTTextView txvExpirationDate = (GTTextView) findViewById(R.id.txvExpirationDate);
        txvExpirationDate.setText("Deal ends on " + date_format.format(expiration_date));

        GTTextView txvCertificatesRemaining = (GTTextView) findViewById(R.id.txvCertificatesRemaining);
        txvCertificatesRemaining.setText(deal_obj.getCertificateQuantity() + " certificates left!");

        GTTextView txvFinePrintLabel = (GTTextView) findViewById(R.id.txvFinePrintLabel);
        txvFinePrintLabel.setVisibility(View.VISIBLE);

        GTTextView txvFinePrint = (GTTextView) findViewById(R.id.txvFinePrint);
        txvFinePrint.setText(deal_obj.getFinePrintExt());

        Button btnBuy = (Button) findViewById(R.id.btnBuy);
        btnBuy.setVisibility(View.VISIBLE);
        btnBuy.setText("Buy this Deal for $" + deal_obj.getCertificateAmount().toString());
        btnBuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Deal_Purchase.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });
    }
}
