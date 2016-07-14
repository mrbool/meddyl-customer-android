package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class System_Successful implements Parcelable
{
	private int code;
	private String message;

	public System_Successful()
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
		out.writeInt(code);
		out.writeString(message);
	}

	public static final Parcelable.Creator<System_Successful> CREATOR = new Parcelable.Creator<System_Successful>() 
	{
		public System_Successful createFromParcel(Parcel in)
		{
			return new System_Successful(in);
		}

		public System_Successful[] newArray(int size)
		{
			return new System_Successful[size];
		}
	};

	private System_Successful(Parcel in)
	{
		code = in.readInt();
		message = in.readString();
	}

	public int getCode()
	{
		return this.code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return this.message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}
