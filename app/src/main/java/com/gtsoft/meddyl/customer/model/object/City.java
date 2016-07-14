package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class City implements Parcelable
{
	private int city_id;
	private int state_id;
	private String city;
	private State state_obj;

	public City()
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
		out.writeInt(city_id);
		out.writeInt(state_id);
		out.writeString(city);
		out.writeParcelable(state_obj, flag);
	}

	public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() 
	{
		public City createFromParcel(Parcel in)
		{
			return new City(in);
		}

		public City[] newArray(int size)
		{
			return new City[size];
		}
	};

	private City(Parcel in)
	{
		city_id = in.readInt();
		state_id = in.readInt();
		city = in.readString();
		state_obj = in.readParcelable(State.class.getClassLoader());
	}

	public int getCityId()
	{
		return this.city_id;
	}
	public void setCityId(int city_id)
	{
		this.city_id = city_id;
	}

	public int getStateId()
	{
		return this.state_id;
	}
	public void setStateId(int state_id)
	{
		this.state_id = state_id;
	}

	public String getCity()
	{
		return this.city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}

	public State getStateObj()
	{
		return this.state_obj;
	}
	public void setStateObj(State state_obj)
	{
		this.state_obj = state_obj;
	}
}
