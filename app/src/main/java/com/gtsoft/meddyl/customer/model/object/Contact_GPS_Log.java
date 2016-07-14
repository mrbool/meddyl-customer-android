package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Contact_GPS_Log implements Parcelable
{
	private int log_id;
	private int contact_id;
	private double latitude;
	private double longitude;
	private Date entry_date_utc_stamp;
	private Contact contact_obj;

	public Contact_GPS_Log()
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
		out.writeInt(log_id);
		out.writeInt(contact_id);
		out.writeDouble(latitude);
		out.writeDouble(longitude);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(contact_obj, flag);
	}

	public static final Parcelable.Creator<Contact_GPS_Log> CREATOR = new Parcelable.Creator<Contact_GPS_Log>() 
	{
		public Contact_GPS_Log createFromParcel(Parcel in)
		{
			return new Contact_GPS_Log(in);
		}

		public Contact_GPS_Log[] newArray(int size)
		{
			return new Contact_GPS_Log[size];
		}
	};

	private Contact_GPS_Log(Parcel in)
	{
		log_id = in.readInt();
		contact_id = in.readInt();
		latitude = in.readDouble();
		longitude = in.readDouble();
		entry_date_utc_stamp = (Date) in.readSerializable();
		contact_obj = in.readParcelable(Contact.class.getClassLoader());
	}

	public int getLogId()
	{
		return this.log_id;
	}
	public void setLogId(int log_id)
	{
		this.log_id = log_id;
	}

	public int getContactId()
	{
		return this.contact_id;
	}
	public void setContactId(int contact_id)
	{
		this.contact_id = contact_id;
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

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Contact getContactObj()
	{
		return this.contact_obj;
	}
	public void setContactObj(Contact contact_obj)
	{
		this.contact_obj = contact_obj;
	}
}
