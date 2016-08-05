package com.gtsoft.meddyl.customer.views.object;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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
import com.gtsoft.meddyl.customer.model.object.Certificate;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Customer;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.model.object.Merchant;
import com.gtsoft.meddyl.customer.system.gtsoft.GTButton;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.adapter.Certificates_Adapter;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Certificate_Detail extends View_Controller implements OnMapReadyCallback
{
    private String address_1, address_2, phone;
    private Merchant merchant_obj;

    private GoogleMap mpvMap;
    private SupportMapFragment map_fragment;

    private Get_Certificate_Detail_Async get_certificate_detail_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_detail);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "DETAILS";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        get_certificate_detail_async = new Get_Certificate_Detail_Async();
        get_certificate_detail_async.execute((Void) null);
    }

    private void Load_Data()
    {
        Certificate certificate_obj = deal_controller.getCertificateObj();
        Deal deal_obj = certificate_obj.getDealObj();
        Contact contact_obj = certificate_obj.getCustomerObj().getContactObj();
        merchant_obj = certificate_obj.getDealObj().getMerchantContactObj().getMerchantObj();

        SimpleDateFormat date_format =  new SimpleDateFormat("M/d/yyyy");
        Date entry_date = certificate_obj.getAssignedDate();
        Date expiration_date = certificate_obj.getExpirationDate();

        address_1 = merchant_obj.getAddress1() + " " + merchant_obj.getAddress2();
        address_2 = merchant_obj.getZipCodeObj().getCityObj().getCity() + ", " + merchant_obj.getZipCodeObj().getCityObj().getStateObj().getAbbreviation() + "  " + merchant_obj.getZipCodeObj().getZipCode();
        phone = Utils.Format_Phone(merchant_obj.getPhone());

        ((GTTextView) findViewById(R.id.txvCertificateCode)).setText(certificate_obj.getCertificateCode());
        ((GTTextView) findViewById(R.id.txvStatus)).setText(certificate_obj.getStatusText2());
        ((GTTextView) findViewById(R.id.txvIssuedTo)).setText("Issued to " + contact_obj.getFirstName() + " " + contact_obj.getLastName());
        ((GTTextView) findViewById(R.id.txvPurchasedOn)).setText("Purchased on " + date_format.format(entry_date));
        ((GTTextView) findViewById(R.id.txvCompanyName)).setText(merchant_obj.getCompanyName());
        ((GTTextView) findViewById(R.id.txvDeal)).setText(deal_obj.getDeal());
        ((GTTextView) findViewById(R.id.txvExpirationDate)).setText("Expires on " + date_format.format(expiration_date));
        ((GTTextView) findViewById(R.id.txvAddress1)).setText(address_1);
        ((GTTextView) findViewById(R.id.txvAddress2)).setText(address_2);
        ((GTTextView) findViewById(R.id.txvFinePrint)).setText(deal_obj.getFinePrintExt());
        ((GTTextView) findViewById(R.id.txvInstructions)).setText(deal_obj.getInstructions());

        /* customer logo image */
        ImageView imvMerchantLogo = (ImageView) findViewById(R.id.imvMerchantLogo);
        imvMerchantLogo.getLayoutParams().width = Double.valueOf(screen_width * .25).intValue();
        imvMerchantLogo.getLayoutParams().height = Double.valueOf(screen_width * .25).intValue();
        imvMerchantLogo.requestLayout();
        imvMerchantLogo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Merchant_Info.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });

        AQuery aq = new AQuery(getApplicationContext());
        aq.id(imvMerchantLogo).image(merchant_obj.getImage());

        /* set map */
        map_fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mpvMap);
        map_fragment.getMapAsync(Certificate_Detail.this);
        map_fragment.getView().setVisibility(View.INVISIBLE);

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


        ((View)findViewById(R.id.txvCertificateIdLabel)).setVisibility(View.VISIBLE);
        imvMerchantLogo.setVisibility(View.VISIBLE);
        txvDirections.setVisibility(View.VISIBLE);
        txvWebSite.setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.txvFinePrintLabel)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.txvInstructionsLabel)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw1)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw2)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw3)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw4)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw5)).setVisibility(View.VISIBLE);
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

        map_fragment.getView().setVisibility(View.VISIBLE);
    }


    private class Get_Certificate_Detail_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Certificate_Detail_Async()
        {
            dialog = new ProgressDialog(Certificate_Detail.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.Get_Certificate_Detail();
            successful = deal_controller.getSuccessful();
            system_error_obj = deal_controller.getSystemErrorObj();
            system_successful_obj = deal_controller.getSystemSuccessfulObj();

            return successful;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            try
            {
                if (dialog.isShowing())
                {
                    dialog.dismiss();
                }

                if(successful)
                {
                    Load_Data();
                }
                else
                {
                    Show_Alert_Dialog("Error", system_error_obj.getMessage());
                }
            }
            catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage());
            }
        }

        @Override
        protected void onCancelled()
        {
            get_certificate_detail_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
