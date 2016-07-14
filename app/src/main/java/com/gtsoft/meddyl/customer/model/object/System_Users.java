package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class System_Users implements Parcelable
{
	private int user_id;
	private String user_name;
	private String password;

	public System_Users()
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
		out.writeInt(user_id);
		out.writeString(user_name);
		out.writeString(password);
	}

	public static final Parcelable.Creator<System_Users> CREATOR = new Parcelable.Creator<System_Users>() 
	{
		public System_Users createFromParcel(Parcel in)
		{
			return new System_Users(in);
		}

		public System_Users[] newArray(int size)
		{
			return new System_Users[size];
		}
	};

	private System_Users(Parcel in)
	{
		user_id = in.readInt();
		user_name = in.readString();
		password = in.readString();
	}

	public int getUserId()
	{
		return this.user_id;
	}
	public void setUserId(int user_id)
	{
		this.user_id = user_id;
	}

	public String getUserName()
	{
		return this.user_name;
	}
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
	}

	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
