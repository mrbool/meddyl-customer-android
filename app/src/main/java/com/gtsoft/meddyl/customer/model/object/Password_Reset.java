package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Password_Reset implements Parcelable
{
	private String reset_id;
	private int status_id;
	private int contact_id;
	private String email;
	private Date entry_date_utc_stamp;
	private Date expiration_date;
	private Contact contact_obj;
	private Password_Reset_Status password_reset_status_obj;

	public Password_Reset()
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
		out.writeString(reset_id);
		out.writeInt(status_id);
		out.writeInt(contact_id);
		out.writeString(email);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeSerializable(expiration_date);
		out.writeParcelable(contact_obj, flag);
		out.writeParcelable(password_reset_status_obj, flag);
	}

	public static final Parcelable.Creator<Password_Reset> CREATOR = new Parcelable.Creator<Password_Reset>() 
	{
		public Password_Reset createFromParcel(Parcel in)
		{
			return new Password_Reset(in);
		}

		public Password_Reset[] newArray(int size)
		{
			return new Password_Reset[size];
		}
	};

	private Password_Reset(Parcel in)
	{
		reset_id = in.readString();
		status_id = in.readInt();
		contact_id = in.readInt();
		email = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		expiration_date = (Date) in.readSerializable();
		contact_obj = in.readParcelable(Contact.class.getClassLoader());
		password_reset_status_obj = in.readParcelable(Password_Reset_Status.class.getClassLoader());
	}

	public String getResetId()
	{
		return this.reset_id;
	}
	public void setResetId(String reset_id)
	{
		this.reset_id = reset_id;
	}

	public int getStatusId()
	{
		return this.status_id;
	}
	public void setStatusId(int status_id)
	{
		this.status_id = status_id;
	}

	public int getContactId()
	{
		return this.contact_id;
	}
	public void setContactId(int contact_id)
	{
		this.contact_id = contact_id;
	}

	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Date getExpirationDate()
	{
		return this.expiration_date;
	}
	public void setExpirationDate(Date expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public Contact getContactObj()
	{
		return this.contact_obj;
	}
	public void setContactObj(Contact contact_obj)
	{
		this.contact_obj = contact_obj;
	}

	public Password_Reset_Status getPasswordResetStatusObj()
	{
		return this.password_reset_status_obj;
	}
	public void setPasswordResetStatusObj(Password_Reset_Status password_reset_status_obj)
	{
		this.password_reset_status_obj = password_reset_status_obj;
	}
}
