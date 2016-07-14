package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Promotion implements Parcelable
{
	private int promotion_id;
	private int promotion_type_id;
	private int customer_id;
	private String promotion_code;
	private String description;
	private Date expiration_date;
	private Date entry_date_utc_stamp;
	private Customer customer_obj;
	private Promotion_Type promotion_type_obj;
	private String link;
	private Application_Type application_type_obj;

	public Promotion()
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
		out.writeInt(promotion_id);
		out.writeInt(promotion_type_id);
		out.writeInt(customer_id);
		out.writeString(promotion_code);
		out.writeString(description);
		out.writeSerializable(expiration_date);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(promotion_type_obj, flag);
		out.writeString(link);
		out.writeParcelable(application_type_obj, flag);
	}

	public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() 
	{
		public Promotion createFromParcel(Parcel in)
		{
			return new Promotion(in);
		}

		public Promotion[] newArray(int size)
		{
			return new Promotion[size];
		}
	};

	private Promotion(Parcel in)
	{
		promotion_id = in.readInt();
		promotion_type_id = in.readInt();
		customer_id = in.readInt();
		promotion_code = in.readString();
		description = in.readString();
		expiration_date = (Date) in.readSerializable();
		entry_date_utc_stamp = (Date) in.readSerializable();
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
		promotion_type_obj = in.readParcelable(Promotion_Type.class.getClassLoader());
		link = in.readString();
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
	}

	public int getPromotionId()
	{
		return this.promotion_id;
	}
	public void setPromotionId(int promotion_id)
	{
		this.promotion_id = promotion_id;
	}

	public int getPromotionTypeId()
	{
		return this.promotion_type_id;
	}
	public void setPromotionTypeId(int promotion_type_id)
	{
		this.promotion_type_id = promotion_type_id;
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
	}

	public String getPromotionCode()
	{
		return this.promotion_code;
	}
	public void setPromotionCode(String promotion_code)
	{
		this.promotion_code = promotion_code;
	}

	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
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

	public Promotion_Type getPromotionTypeObj()
	{
		return this.promotion_type_obj;
	}
	public void setPromotionTypeObj(Promotion_Type promotion_type_obj)
	{
		this.promotion_type_obj = promotion_type_obj;
	}

	public String getLink()
	{
		return this.link;
	}
	public void setLink(String link)
	{
		this.link = link;
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
