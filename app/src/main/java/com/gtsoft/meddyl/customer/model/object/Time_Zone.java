package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Time_Zone implements Parcelable
{
	private int time_zone_id;
	private String time_zone;
	private String abbreviation;
	private int offset;

	public Time_Zone()
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
		out.writeInt(time_zone_id);
		out.writeString(time_zone);
		out.writeString(abbreviation);
		out.writeInt(offset);
	}

	public static final Parcelable.Creator<Time_Zone> CREATOR = new Parcelable.Creator<Time_Zone>() 
	{
		public Time_Zone createFromParcel(Parcel in)
		{
			return new Time_Zone(in);
		}

		public Time_Zone[] newArray(int size)
		{
			return new Time_Zone[size];
		}
	};

	private Time_Zone(Parcel in)
	{
		time_zone_id = in.readInt();
		time_zone = in.readString();
		abbreviation = in.readString();
		offset = in.readInt();
	}

	public int getTimeZoneId()
	{
		return this.time_zone_id;
	}
	public void setTimeZoneId(int time_zone_id)
	{
		this.time_zone_id = time_zone_id;
	}

	public String getTimeZone()
	{
		return this.time_zone;
	}
	public void setTimeZone(String time_zone)
	{
		this.time_zone = time_zone;
	}

	public String getAbbreviation()
	{
		return this.abbreviation;
	}
	public void setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
	}

	public int getOffset()
	{
		return this.offset;
	}
	public void setOffset(int offset)
	{
		this.offset = offset;
	}
}
