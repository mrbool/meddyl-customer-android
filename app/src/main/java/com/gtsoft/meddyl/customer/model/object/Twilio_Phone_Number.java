package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Twilio_Phone_Number implements Parcelable
{
	private int phone_id;
	private String phone;
	private boolean is_active;
	private Date entry_date_utc_stamp;

	public Twilio_Phone_Number()
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
		out.writeInt(phone_id);
		out.writeString(phone);
		out.writeByte((byte) (is_active ? 1:0));
		out.writeSerializable(entry_date_utc_stamp);
	}

	public static final Parcelable.Creator<Twilio_Phone_Number> CREATOR = new Parcelable.Creator<Twilio_Phone_Number>() 
	{
		public Twilio_Phone_Number createFromParcel(Parcel in)
		{
			return new Twilio_Phone_Number(in);
		}

		public Twilio_Phone_Number[] newArray(int size)
		{
			return new Twilio_Phone_Number[size];
		}
	};

	private Twilio_Phone_Number(Parcel in)
	{
		phone_id = in.readInt();
		phone = in.readString();
		is_active = in.readByte() != 0;
		entry_date_utc_stamp = (Date) in.readSerializable();
	}

	public int getPhoneId()
	{
		return this.phone_id;
	}
	public void setPhoneId(int phone_id)
	{
		this.phone_id = phone_id;
	}

	public String getPhone()
	{
		return this.phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public boolean getIsActive()
	{
		return this.is_active;
	}
	public void setIsActive(boolean is_active)
	{
		this.is_active = is_active;
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
