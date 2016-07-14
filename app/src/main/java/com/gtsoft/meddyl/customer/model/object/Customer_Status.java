package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Customer_Status implements Parcelable
{
	private int status_id;
	private String status;

	public Customer_Status()
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
		out.writeInt(status_id);
		out.writeString(status);
	}

	public static final Parcelable.Creator<Customer_Status> CREATOR = new Parcelable.Creator<Customer_Status>() 
	{
		public Customer_Status createFromParcel(Parcel in)
		{
			return new Customer_Status(in);
		}

		public Customer_Status[] newArray(int size)
		{
			return new Customer_Status[size];
		}
	};

	private Customer_Status(Parcel in)
	{
		status_id = in.readInt();
		status = in.readString();
	}

	public int getStatusId()
	{
		return this.status_id;
	}
	public void setStatusId(int status_id)
	{
		this.status_id = status_id;
	}

	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
}
