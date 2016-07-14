package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Customer_Deal_Log implements Parcelable
{
	private int log_id;
	private int customer_id;
	private int deal_id;
	private int search_location_type_id;
	private double longitude;
	private double latitude;
	private String zip_code;
	private Date entry_date_utc_stamp;

	public Customer_Deal_Log()
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
		out.writeInt(log_id);
		out.writeInt(customer_id);
		out.writeInt(deal_id);
		out.writeInt(search_location_type_id);
		out.writeDouble(longitude);
		out.writeDouble(latitude);
		out.writeString(zip_code);
		out.writeSerializable(entry_date_utc_stamp);
	}

	public static final Parcelable.Creator<Customer_Deal_Log> CREATOR = new Parcelable.Creator<Customer_Deal_Log>() 
	{
		public Customer_Deal_Log createFromParcel(Parcel in)
		{
			return new Customer_Deal_Log(in);
		}

		public Customer_Deal_Log[] newArray(int size)
		{
			return new Customer_Deal_Log[size];
		}
	};

	private Customer_Deal_Log(Parcel in)
	{
		log_id = in.readInt();
		customer_id = in.readInt();
		deal_id = in.readInt();
		search_location_type_id = in.readInt();
		longitude = in.readDouble();
		latitude = in.readDouble();
		zip_code = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
	}

	public int getLogId()
	{
		return this.log_id;
	}
	public void setLogId(int log_id)
	{
		this.log_id = log_id;
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
	}

	public int getSearchLocationTypeId()
	{
		return this.search_location_type_id;
	}
	public void setSearchLocationTypeId(int search_location_type_id)
	{
		this.search_location_type_id = search_location_type_id;
	}

	public double getLongitude()
	{
		return this.longitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getLatitude()
	{
		return this.latitude;
	}
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public String getZipCode()
	{
		return this.zip_code;
	}
	public void setZipCode(String zip_code)
	{
		this.zip_code = zip_code;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}
}
