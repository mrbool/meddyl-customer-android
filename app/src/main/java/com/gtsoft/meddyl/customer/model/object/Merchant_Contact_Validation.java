package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Merchant_Contact_Validation implements Parcelable
{
	private int validation_id;
	private String validation_code;
	private String user_name;
	private String phone;
	private String ip_address;
	private boolean is_validated;
	private Date entry_date_utc_stamp;

	public Merchant_Contact_Validation()
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
		out.writeInt(validation_id);
		out.writeString(validation_code);
		out.writeString(user_name);
		out.writeString(phone);
		out.writeString(ip_address);
		out.writeByte((byte) (is_validated ? 1:0));
		out.writeSerializable(entry_date_utc_stamp);
	}

	public static final Parcelable.Creator<Merchant_Contact_Validation> CREATOR = new Parcelable.Creator<Merchant_Contact_Validation>() 
	{
		public Merchant_Contact_Validation createFromParcel(Parcel in)
		{
			return new Merchant_Contact_Validation(in);
		}

		public Merchant_Contact_Validation[] newArray(int size)
		{
			return new Merchant_Contact_Validation[size];
		}
	};

	private Merchant_Contact_Validation(Parcel in)
	{
		validation_id = in.readInt();
		validation_code = in.readString();
		user_name = in.readString();
		phone = in.readString();
		ip_address = in.readString();
		is_validated = in.readByte() != 0;
		entry_date_utc_stamp = (Date) in.readSerializable();
	}

	public int getValidationId()
	{
		return this.validation_id;
	}
	public void setValidationId(int validation_id)
	{
		this.validation_id = validation_id;
	}

	public String getValidationCode()
	{
		return this.validation_code;
	}
	public void setValidationCode(String validation_code)
	{
		this.validation_code = validation_code;
	}

	public String getUserName()
	{
		return this.user_name;
	}
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
	}

	public String getPhone()
	{
		return this.phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getIpAddress()
	{
		return this.ip_address;
	}
	public void setIpAddress(String ip_address)
	{
		this.ip_address = ip_address;
	}

	public boolean getIsValidated()
	{
		return this.is_validated;
	}
	public void setIsValidated(boolean is_validated)
	{
		this.is_validated = is_validated;
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
