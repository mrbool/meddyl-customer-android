package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Promotion_Activity implements Parcelable
{
	private int promotion_activity_id;
	private int promotion_id;
	private int parent_activity_id;
	private int customer_id;
	private int merchant_contact_id;
	private Date redeemed_date;
	private Date expiration_date;
	private Date entry_date_utc_stamp;
	private Customer customer_obj;
	private Merchant_Contact merchant_contact_obj;
	private Promotion promotion_obj;
	private Promotion_Activity promotion_activity_obj;

	public Promotion_Activity()
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
		out.writeInt(promotion_activity_id);
		out.writeInt(promotion_id);
		out.writeInt(parent_activity_id);
		out.writeInt(customer_id);
		out.writeInt(merchant_contact_id);
		out.writeSerializable(redeemed_date);
		out.writeSerializable(expiration_date);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
		out.writeParcelable(promotion_obj, flag);
		out.writeParcelable(promotion_activity_obj, flag);
	}

	public static final Parcelable.Creator<Promotion_Activity> CREATOR = new Parcelable.Creator<Promotion_Activity>() 
	{
		public Promotion_Activity createFromParcel(Parcel in)
		{
			return new Promotion_Activity(in);
		}

		public Promotion_Activity[] newArray(int size)
		{
			return new Promotion_Activity[size];
		}
	};

	private Promotion_Activity(Parcel in)
	{
		promotion_activity_id = in.readInt();
		promotion_id = in.readInt();
		parent_activity_id = in.readInt();
		customer_id = in.readInt();
		merchant_contact_id = in.readInt();
		redeemed_date = (Date) in.readSerializable();
		expiration_date = (Date) in.readSerializable();
		entry_date_utc_stamp = (Date) in.readSerializable();
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
		merchant_contact_obj = in.readParcelable(Merchant_Contact.class.getClassLoader());
		promotion_obj = in.readParcelable(Promotion.class.getClassLoader());
		promotion_activity_obj = in.readParcelable(Promotion_Activity.class.getClassLoader());
	}

	public int getPromotionActivityId()
	{
		return this.promotion_activity_id;
	}
	public void setPromotionActivityId(int promotion_activity_id)
	{
		this.promotion_activity_id = promotion_activity_id;
	}

	public int getPromotionId()
	{
		return this.promotion_id;
	}
	public void setPromotionId(int promotion_id)
	{
		this.promotion_id = promotion_id;
	}

	public int getParentActivityId()
	{
		return this.parent_activity_id;
	}
	public void setParentActivityId(int parent_activity_id)
	{
		this.parent_activity_id = parent_activity_id;
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
	}

	public int getMerchantContactId()
	{
		return this.merchant_contact_id;
	}
	public void setMerchantContactId(int merchant_contact_id)
	{
		this.merchant_contact_id = merchant_contact_id;
	}

	public Date getRedeemedDate()
	{
		return this.redeemed_date;
	}
	public void setRedeemedDate(Date redeemed_date)
	{
		this.redeemed_date = redeemed_date;
	}

	public Date getExpirationDate()
	{
		return this.expiration_date;
	}
	public void setExpirationDate(Date expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Customer getCustomerObj()
	{
		return this.customer_obj;
	}
	public void setCustomerObj(Customer customer_obj)
	{
		this.customer_obj = customer_obj;
	}

	public Merchant_Contact getMerchantContactObj()
	{
		return this.merchant_contact_obj;
	}
	public void setMerchantContactObj(Merchant_Contact merchant_contact_obj)
	{
		this.merchant_contact_obj = merchant_contact_obj;
	}

	public Promotion getPromotionObj()
	{
		return this.promotion_obj;
	}
	public void setPromotionObj(Promotion promotion_obj)
	{
		this.promotion_obj = promotion_obj;
	}

	public Promotion_Activity getPromotionActivityObj()
	{
		return this.promotion_activity_obj;
	}
	public void setPromotionActivityObj(Promotion_Activity promotion_activity_obj)
	{
		this.promotion_activity_obj = promotion_activity_obj;
	}
}
