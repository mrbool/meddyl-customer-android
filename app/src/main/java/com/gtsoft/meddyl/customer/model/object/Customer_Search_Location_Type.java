package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Customer_Search_Location_Type implements Parcelable
{
	private int search_location_type_id;
	private String location_type;

	public Customer_Search_Location_Type()
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
		out.writeInt(search_location_type_id);
		out.writeString(location_type);
	}

	public static final Parcelable.Creator<Customer_Search_Location_Type> CREATOR = new Parcelable.Creator<Customer_Search_Location_Type>() 
	{
		public Customer_Search_Location_Type createFromParcel(Parcel in)
		{
			return new Customer_Search_Location_Type(in);
		}

		public Customer_Search_Location_Type[] newArray(int size)
		{
			return new Customer_Search_Location_Type[size];
		}
	};

	private Customer_Search_Location_Type(Parcel in)
	{
		search_location_type_id = in.readInt();
		location_type = in.readString();
	}

	public int getSearchLocationTypeId()
	{
		return this.search_location_type_id;
	}
	public void setSearchLocationTypeId(int search_location_type_id)
	{
		this.search_location_type_id = search_location_type_id;
	}

	public String getLocationType()
	{
		return this.location_type;
	}
	public void setLocationType(String location_type)
	{
		this.location_type = location_type;
	}
}
