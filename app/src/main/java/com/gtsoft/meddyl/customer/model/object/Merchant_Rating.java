package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Merchant_Rating implements Parcelable
{
	private int rating_id;
	private BigDecimal rating;
	private String image;

	public Merchant_Rating()
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
		out.writeInt(rating_id);
		out.writeSerializable(rating);
		out.writeString(image);
	}

	public static final Parcelable.Creator<Merchant_Rating> CREATOR = new Parcelable.Creator<Merchant_Rating>() 
	{
		public Merchant_Rating createFromParcel(Parcel in)
		{
			return new Merchant_Rating(in);
		}

		public Merchant_Rating[] newArray(int size)
		{
			return new Merchant_Rating[size];
		}
	};

	private Merchant_Rating(Parcel in)
	{
		rating_id = in.readInt();
		rating = (BigDecimal) in.readSerializable();
		image = in.readString();
	}

	public int getRatingId()
	{
		return this.rating_id;
	}
	public void setRatingId(int rating_id)
	{
		this.rating_id = rating_id;
	}

	public BigDecimal getRating()
	{
		return this.rating;
	}
	public void setRating(BigDecimal rating)
	{
		this.rating = rating;
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
