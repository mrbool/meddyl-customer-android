package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Zip_Code implements Parcelable
{
	private String zip_code;
	private int city_id;
	private int time_zone_id;
	private double latitude;
	private double longitude;
	private City city_obj;
	private Time_Zone time_zone_obj;
	private Neighborhood[] neighborhood_obj_array;

	public Zip_Code()
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
		out.writeString(zip_code);
		out.writeInt(city_id);
		out.writeInt(time_zone_id);
		out.writeDouble(latitude);
		out.writeDouble(longitude);
		out.writeParcelable(city_obj, flag);
		out.writeParcelable(time_zone_obj, flag);
		out.writeTypedArray(neighborhood_obj_array, flag);
	}

	public static final Parcelable.Creator<Zip_Code> CREATOR = new Parcelable.Creator<Zip_Code>() 
	{
		public Zip_Code createFromParcel(Parcel in)
		{
			return new Zip_Code(in);
		}

		public Zip_Code[] newArray(int size)
		{
			return new Zip_Code[size];
		}
	};

	private Zip_Code(Parcel in)
	{
		zip_code = in.readString();
		city_id = in.readInt();
		time_zone_id = in.readInt();
		latitude = in.readDouble();
		longitude = in.readDouble();
		city_obj = in.readParcelable(City.class.getClassLoader());
		time_zone_obj = in.readParcelable(Time_Zone.class.getClassLoader());
		neighborhood_obj_array = in.createTypedArray(Neighborhood.CREATOR);
	}

	public String getZipCode()
	{
		return this.zip_code;
	}
	public void setZipCode(String zip_code)
	{
		this.zip_code = zip_code;
	}

	public int getCityId()
	{
		return this.city_id;
	}
	public void setCityId(int city_id)
	{
		this.city_id = city_id;
	}

	public int getTimeZoneId()
	{
		return this.time_zone_id;
	}
	public void setTimeZoneId(int time_zone_id)
	{
		this.time_zone_id = time_zone_id;
	}

	public double getLatitude()
	{
		return this.latitude;
	}
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return this.longitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public City getCityObj()
	{
		return this.city_obj;
	}
	public void setCityObj(City city_obj)
	{
		this.city_obj = city_obj;
	}

	public Time_Zone getTimeZoneObj()
	{
		return this.time_zone_obj;
	}
	public void setTimeZoneObj(Time_Zone time_zone_obj)
	{
		this.time_zone_obj = time_zone_obj;
	}

	public Neighborhood[] getNeighborhoodObjArray()
	{
		return this.neighborhood_obj_array;
	}
	public void setNeighborhoodObjArray(Neighborhood[] neighborhood_obj_array)
	{
		this.neighborhood_obj_array = neighborhood_obj_array;
	}
}
