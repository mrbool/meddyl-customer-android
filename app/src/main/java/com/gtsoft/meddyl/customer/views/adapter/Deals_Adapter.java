package com.gtsoft.meddyl.customer.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.model.object.Merchant;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Deals_Adapter extends ArrayAdapter<Deal>
{
    private Context context;
    private int layoutResourceId;
    private Deal data[] = null;

    public Deals_Adapter(Context context, int layoutResourceId, Deal[] data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        String neighborhood;

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int image_width = metrics.widthPixels;
        int image_height = Double.valueOf(image_width * .70).intValue();

        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageView imvDealImage=(ImageView)row.findViewById(R.id.imvDealImage);
        ImageView imvMerchantLogo=(ImageView)row.findViewById(R.id.imvMerchantLogo);
        ImageView imvStars=(ImageView)row.findViewById(R.id.imvStars);
        TextView txvCompanyName = (TextView)row.findViewById(R.id.txvCompanyName);
        TextView txvNeighborhood = (TextView)row.findViewById(R.id.txvNeighborhood);
        TextView txvDeal = (TextView)row.findViewById(R.id.txvDeal);

        Deal deal_obj;
        deal_obj = data[position];

        Merchant merchant_obj = deal_obj.getMerchantContactObj().getMerchantObj();

        if(merchant_obj.getNeighborhoodObj().getNeighborhood() != null)
            neighborhood = merchant_obj.getNeighborhoodObj().getNeighborhood();
        else
            neighborhood = merchant_obj.getZipCodeObj().getCityObj().getCity();

        neighborhood = neighborhood + " - " + deal_obj.getDistance().toString() + "mi";

        /* set deal image */
        AQuery aq = new AQuery(getContext());
        aq.id(imvDealImage).image(deal_obj.getImage());

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)imvDealImage.getLayoutParams();
        params.width = image_width;
        params.height = image_height;
        imvDealImage.setLayoutParams(params);

        /* set merchant logo */
        int logo_width = image_width/4;

        AQuery aq_logo = new AQuery(getContext());
        aq_logo.id(imvMerchantLogo).image(merchant_obj.getImage());

        int logo_lag = -Double.valueOf((image_width/4) * .75).intValue();

        ViewGroup.LayoutParams params_logo = (ViewGroup.LayoutParams)imvMerchantLogo.getLayoutParams();
        params_logo.width = image_width/4;
        params_logo.height = image_width/4;

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imvMerchantLogo.getLayoutParams();
        layoutParams.width = image_width/4;
        layoutParams.height = image_width/4;
        layoutParams.setMargins(0, logo_lag, 0, 0);
        imvMerchantLogo.setLayoutParams(layoutParams);
        imvMerchantLogo.setX(image_width - (logo_width) - (Double.valueOf(logo_width * .25).intValue()));

        /* set stars */
        int stars_width = Double.valueOf(image_width * .275).intValue();

        if(merchant_obj.getMerchantRatingObj().getImage() != null)
        {
            AQuery aq_stars = new AQuery(getContext());
            int resource_id = ((Activity) context).getResources().getIdentifier(merchant_obj.getMerchantRatingObj().getImage().toLowerCase().replace(".png", ""), "drawable", ((Activity) context).getPackageName());
            aq_stars.id(imvStars).image(resource_id);

            ViewGroup.LayoutParams params_stars = (ViewGroup.LayoutParams) imvStars.getLayoutParams();
            params_stars.width = stars_width;
            params_stars.height = stars_width / 5;
            imvStars.setLayoutParams(params_stars);
            imvStars.setX(image_width - (stars_width) - (Double.valueOf(stars_width * .25).intValue()));
            imvStars.setVisibility(View.VISIBLE);
        }
        else
        {
            imvStars.setVisibility(View.INVISIBLE);
        }

        /* set text */
        txvCompanyName.setText(merchant_obj.getCompanyName());
        txvNeighborhood.setText(neighborhood);
        txvDeal.setText(deal_obj.getDeal());

        imvMerchantLogo.setVisibility(View.VISIBLE);

        return row;
    }



}
