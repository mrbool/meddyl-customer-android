package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Facebook_Data_Hometown implements Parcelable
{
	private int id;
	private long fb_profile_id;
	private long fb_hometown_id;
	private String name;
	private Date entry_date_utc_stamp;

	public Facebook_Data_Hometown()
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
		out.writeInt(id);
		out.writeLong(fb_profile_id);
		out.writeLong(fb_hometown_id);
		out.writeString(name);
		out.writeSerializable(entry_date_utc_stamp);
	}

	public static final Parcelable.Creator<Facebook_Data_Hometown> CREATOR = new Parcelable.Creator<Facebook_Data_Hometown>() 
	{
		public Facebook_Data_Hometown createFromParcel(Parcel in)
		{
			return new Facebook_Data_Hometown(in);
		}

		public Facebook_Data_Hometown[] newArray(int size)
		{
			return new Facebook_Data_Hometown[size];
		}
	};

	private Facebook_Data_Hometown(Parcel in)
	{
		id = in.readInt();
		fb_profile_id = in.readLong();
		fb_hometown_id = in.readLong();
		name = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
	}

	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public long getFbProfileId()
	{
		return this.fb_profile_id;
	}
	public void setFbProfileId(long fb_profile_id)
	{
		this.fb_profile_id = fb_profile_id;
	}

	public long getFbHometownId()
	{
		return this.fb_hometown_id;
	}
	public void setFbHometownId(long fb_hometown_id)
	{
		this.fb_hometown_id = fb_hometown_id;
	}

	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}
}
