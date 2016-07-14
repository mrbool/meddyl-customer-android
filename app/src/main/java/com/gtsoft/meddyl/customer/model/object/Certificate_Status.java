package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Certificate_Status implements Parcelable
{
	private int status_id;
	private String status;
	private int order_id;

	public Certificate_Status()
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
		out.writeInt(order_id);
	}

	public static final Parcelable.Creator<Certificate_Status> CREATOR = new Parcelable.Creator<Certificate_Status>() 
	{
		public Certificate_Status createFromParcel(Parcel in)
		{
			return new Certificate_Status(in);
		}

		public Certificate_Status[] newArray(int size)
		{
			return new Certificate_Status[size];
		}
	};

	private Certificate_Status(Parcel in)
	{
		status_id = in.readInt();
		status = in.readString();
		order_id = in.readInt();
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

	public int getOrderId()
	{
		return this.order_id;
	}
	public void setOrderId(int order_id)
	{
		this.order_id = order_id;
	}
}
