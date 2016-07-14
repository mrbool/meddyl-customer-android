package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Deal_Payment implements Parcelable
{
	private int payment_id;
	private int deal_id;
	private int credit_card_id;
	private int promotion_activity_id;
	private String card_holder_name;
	private String card_number;
	private String card_expiration_date;
	private BigDecimal payment_amount;
	private Date payment_date_utc_stamp;
	private Credit_Card credit_card_obj;
	private Deal deal_obj;
	private Promotion_Activity promotion_activity_obj;
	private Application_Type application_type_obj;

	public Deal_Payment()
	{

	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flag)
	{
		out.writeInt(payment_id);
		out.writeInt(deal_id);
		out.writeInt(credit_card_id);
		out.writeInt(promotion_activity_id);
		out.writeString(card_holder_name);
		out.writeString(card_number);
		out.writeString(card_expiration_date);
		out.writeSerializable(payment_amount);
		out.writeSerializable(payment_date_utc_stamp);
		out.writeParcelable(credit_card_obj, flag);
		out.writeParcelable(deal_obj, flag);
		out.writeParcelable(promotion_activity_obj, flag);
		out.writeParcelable(application_type_obj, flag);
	}

	public static final Parcelable.Creator<Deal_Payment> CREATOR = new Parcelable.Creator<Deal_Payment>() 
	{
		public Deal_Payment createFromParcel(Parcel in)
		{
			return new Deal_Payment(in);
		}

		public Deal_Payment[] newArray(int size)
		{
			return new Deal_Payment[size];
		}
	};

	private Deal_Payment(Parcel in)
	{
		payment_id = in.readInt();
		deal_id = in.readInt();
		credit_card_id = in.readInt();
		promotion_activity_id = in.readInt();
		card_holder_name = in.readString();
		card_number = in.readString();
		card_expiration_date = in.readString();
		payment_amount = (BigDecimal) in.readSerializable();
		payment_date_utc_stamp = (Date) in.readSerializable();
		credit_card_obj = in.readParcelable(Credit_Card.class.getClassLoader());
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
		promotion_activity_obj = in.readParcelable(Promotion_Activity.class.getClassLoader());
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
	}

	public int getPaymentId()
	{
		return this.payment_id;
	}
	public void setPaymentId(int payment_id)
	{
		this.payment_id = payment_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
	}

	public int getCreditCardId()
	{
		return this.credit_card_id;
	}
	public void setCreditCardId(int credit_card_id)
	{
		this.credit_card_id = credit_card_id;
	}

	public int getPromotionActivityId()
	{
		return this.promotion_activity_id;
	}
	public void setPromotionActivityId(int promotion_activity_id)
	{
		this.promotion_activity_id = promotion_activity_id;
	}

	public String getCardHolderName()
	{
		return this.card_holder_name;
	}
	public void setCardHolderName(String card_holder_name)
	{
		this.card_holder_name = card_holder_name;
	}

	public String getCardNumber()
	{
		return this.card_number;
	}
	public void setCardNumber(String card_number)
	{
		this.card_number = card_number;
	}

	public String getCardExpirationDate()
	{
		return this.card_expiration_date;
	}
	public void setCardExpirationDate(String card_expiration_date)
	{
		this.card_expiration_date = card_expiration_date;
	}

	public BigDecimal getPaymentAmount()
	{
		return this.payment_amount;
	}
	public void setPaymentAmount(BigDecimal payment_amount)
	{
		this.payment_amount = payment_amount;
	}

	public Date getPaymentDateUtcStamp()
	{
		return this.payment_date_utc_stamp;
	}
	public void setPaymentDateUtcStamp(Date payment_date_utc_stamp)
	{
		this.payment_date_utc_stamp = payment_date_utc_stamp;
	}

	public Credit_Card getCreditCardObj()
	{
		return this.credit_card_obj;
	}
	public void setCreditCardObj(Credit_Card credit_card_obj)
	{
		this.credit_card_obj = credit_card_obj;
	}

	public Deal getDealObj()
	{
		return this.deal_obj;
	}
	public void setDealObj(Deal deal_obj)
	{
		this.deal_obj = deal_obj;
	}

	public Promotion_Activity getPromotionActivityObj()
	{
		return this.promotion_activity_obj;
	}
	public void setPromotionActivityObj(Promotion_Activity promotion_activity_obj)
	{
		this.promotion_activity_obj = promotion_activity_obj;
	}

	public Application_Type getApplicationTypeObj()
	{
		return this.application_type_obj;
	}
	public void setApplicationTypeObj(Application_Type application_type_obj)
	{
		this.application_type_obj = application_type_obj;
	}
}
