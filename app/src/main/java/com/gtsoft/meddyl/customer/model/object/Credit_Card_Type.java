package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Credit_Card_Type implements Parcelable
{
	private int type_id;
	private String type;
	private String image;

	public Credit_Card_Type()
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
		out.writeInt(type_id);
		out.writeString(type);
		out.writeString(image);
	}

	public static final Parcelable.Creator<Credit_Card_Type> CREATOR = new Parcelable.Creator<Credit_Card_Type>() 
	{
		public Credit_Card_Type createFromParcel(Parcel in)
		{
			return new Credit_Card_Type(in);
		}

		public Credit_Card_Type[] newArray(int size)
		{
			return new Credit_Card_Type[size];
		}
	};

	private Credit_Card_Type(Parcel in)
	{
		type_id = in.readInt();
		type = in.readString();
		image = in.readString();
	}

	public int getTypeId()
	{
		return this.type_id;
	}
	public void setTypeId(int type_id)
	{
		this.type_id = type_id;
	}

	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
	}

	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
}
