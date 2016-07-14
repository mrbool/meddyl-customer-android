package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class System_Error implements Parcelable
{
	private int code;
	private String message;

	public System_Error()
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

	public static final Parcelable.Creator<System_Error> CREATOR = new Parcelable.Creator<System_Error>() 
	{
		public System_Error createFromParcel(Parcel in)
		{
			return new System_Error(in);
		}

		public System_Error[] newArray(int size)
		{
			return new System_Error[size];
		}
	};

	private System_Error(Parcel in)
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
