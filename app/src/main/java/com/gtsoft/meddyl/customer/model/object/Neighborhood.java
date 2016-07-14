package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Neighborhood implements Parcelable
{
	private int neighborhood_id;
	private String zip_code;
	private String neighborhood;
	private Zip_Code zip_code_obj;

	public Neighborhood()
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
		out.writeInt(neighborhood_id);
		out.writeString(zip_code);
		out.writeString(neighborhood);
		out.writeParcelable(zip_code_obj, flag);
	}

	public static final Parcelable.Creator<Neighborhood> CREATOR = new Parcelable.Creator<Neighborhood>() 
	{
		public Neighborhood createFromParcel(Parcel in)
		{
			return new Neighborhood(in);
		}

		public Neighborhood[] newArray(int size)
		{
			return new Neighborhood[size];
		}
	};

	private Neighborhood(Parcel in)
	{
		neighborhood_id = in.readInt();
		zip_code = in.readString();
		neighborhood = in.readString();
		zip_code_obj = in.readParcelable(Zip_Code.class.getClassLoader());
	}

	public int getNeighborhoodId()
	{
		return this.neighborhood_id;
	}
	public void setNeighborhoodId(int neighborhood_id)
	{
		this.neighborhood_id = neighborhood_id;
	}

	public String getZipCode()
	{
		return this.zip_code;
	}
	public void setZipCode(String zip_code)
	{
		this.zip_code = zip_code;
	}

	public String getNeighborhood()
	{
		return this.neighborhood;
	}
	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	public Zip_Code getZipCodeObj()
	{
		return this.zip_code_obj;
	}
	public void setZipCodeObj(Zip_Code zip_code_obj)
	{
		this.zip_code_obj = zip_code_obj;
	}
}
