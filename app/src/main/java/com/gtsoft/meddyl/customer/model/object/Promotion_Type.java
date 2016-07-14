package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Promotion_Type implements Parcelable
{
	private int promotion_type_id;
	private String type;

	public Promotion_Type()
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
		out.writeInt(promotion_type_id);
		out.writeString(type);
	}

	public static final Parcelable.Creator<Promotion_Type> CREATOR = new Parcelable.Creator<Promotion_Type>() 
	{
		public Promotion_Type createFromParcel(Parcel in)
		{
			return new Promotion_Type(in);
		}

		public Promotion_Type[] newArray(int size)
		{
			return new Promotion_Type[size];
		}
	};

	private Promotion_Type(Parcel in)
	{
		promotion_type_id = in.readInt();
		type = in.readString();
	}

	public int getPromotionTypeId()
	{
		return this.promotion_type_id;
	}
	public void setPromotionTypeId(int promotion_type_id)
	{
		this.promotion_type_id = promotion_type_id;
	}

	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}
