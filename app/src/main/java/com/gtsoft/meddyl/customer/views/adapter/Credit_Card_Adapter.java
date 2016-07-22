package com.gtsoft.meddyl.customer.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Credit_Card;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;


public class Credit_Card_Adapter extends ArrayAdapter<Credit_Card>
{
    private Context context;
    private int layoutResourceId;
    private Credit_Card data[] = null;


    public Credit_Card_Adapter(Context context, int layoutResourceId, Credit_Card[] data)
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

        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Credit_Card credit_card_obj;
        credit_card_obj = data[position];

        ImageView imvCardType=(ImageView)row.findViewById(R.id.imvCardType);
        AQuery aq = new AQuery(getContext());
        int resource_id = ((Activity)context).getResources().getIdentifier(credit_card_obj.getCreditCardTypeObj().getImage().toLowerCase().replace(".png","") , "drawable", ((Activity)context).getPackageName());
        aq.id(imvCardType).image(resource_id);
        imvCardType.getLayoutParams().width = Double.valueOf(size.y * .1).intValue();
        imvCardType.getLayoutParams().height = Double.valueOf(size.y * .1).intValue();
        imvCardType.requestLayout();

        TextView txtCardNumber = (TextView)row.findViewById(R.id.txtCardNumber);
        TextView txtExpirationDate = (TextView)row.findViewById(R.id.txtExpirationDate);

        txtCardNumber.setText(Utils.Format_Credit_Card(credit_card_obj.getCardNumber()));
        txtExpirationDate.setText(credit_card_obj.getExpirationDate().substring(0,2) + "/" + credit_card_obj.getExpirationDate().substring(2,4));

        ImageView imvDefault = (ImageView) row.findViewById(R.id.imvDefault);
        if(credit_card_obj.getDefaultFlag())
        {
            imvDefault.getLayoutParams().width = Double.valueOf(size.y * .045).intValue();
            imvDefault.getLayoutParams().height = Double.valueOf(size.y * .045).intValue();
            imvDefault.requestLayout();
        }
        else
        {
            imvDefault.setVisibility(View.GONE);
        }

        return row;
    }

}
