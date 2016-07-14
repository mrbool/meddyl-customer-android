package com.gtsoft.meddyl.customer.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Certificate;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.model.object.Merchant;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Certificates_Adapter extends ArrayAdapter<Certificate>
{
    private Context context;
    private int layoutResourceId;
    private Certificate data[] = null;

    public Certificates_Adapter(Context context, int layoutResourceId, Certificate[] data)
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
        int image_width = Double.valueOf(metrics.widthPixels * .3).intValue();
        int image_height = Double.valueOf(image_width * .75).intValue();

        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageView imvDealImage=(ImageView)row.findViewById(R.id.imvDealImage);
        TextView txvCompanyName = (TextView)row.findViewById(R.id.txvCompanyName);
        TextView txvDeal = (TextView)row.findViewById(R.id.txvDeal);
        TextView txvStatus = (TextView)row.findViewById(R.id.txvStatus);

        Certificate certificate_obj;
        certificate_obj = data[position];

        Deal deal_obj = certificate_obj.getDealObj();
        Merchant merchant_obj = certificate_obj.getDealObj().getMerchantContactObj().getMerchantObj();

        /* set deal image */
        AQuery aq = new AQuery(getContext());
        aq.id(imvDealImage).image(deal_obj.getImage());

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)imvDealImage.getLayoutParams();
        params.width = image_width;
        params.height = image_height;
        imvDealImage.setLayoutParams(params);

        SimpleDateFormat date_format =  new SimpleDateFormat("M/d/yyyy");

        /* set text */
        txvCompanyName.setText(merchant_obj.getCompanyName());
        txvDeal.setText(deal_obj.getDeal());

        Date redeemed_date = certificate_obj.getRedeemedDate();
        Date expiration_date = certificate_obj.getExpirationDate();

        switch (certificate_obj.getCertificateStatusObj().getStatus())
        {
            case "Active":
            case "Waiting":
                txvStatus.setText("Expires on " + date_format.format(expiration_date));
                break;
            case "Expired":
                row.setBackgroundColor(Color.parseColor("#E9E9E9"));
                txvStatus.setText("Expired on " + date_format.format(expiration_date));
                break;
            case "Redeemed":
                row.setBackgroundColor(Color.parseColor("#E9E9E9"));
                txvStatus.setText("Redeemed on " + date_format.format(redeemed_date));
                break;
            case "Cancelled":
            case "Valid":
                row.setBackgroundColor(Color.parseColor("#E9E9E9"));
                break;
            default:
        }

        return row;
    }



}
