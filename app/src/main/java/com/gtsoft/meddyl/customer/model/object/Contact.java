package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Contact implements Parcelable
{
	private int contact_id;
	private long facebook_id;
	private String zip_code;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private String user_name;
	private String password;
	private Date entry_date_utc_stamp;
	private Zip_Code zip_code_obj;

	public Contact()
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
		out.writeInt(contact_id);
		out.writeLong(facebook_id);
		out.writeString(zip_code);
		out.writeString(first_name);
		out.writeString(last_name);
		out.writeString(email);
		out.writeString(phone);
		out.writeString(user_name);
		out.writeString(password);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(zip_code_obj, flag);
	}

	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() 
	{
		public Contact createFromParcel(Parcel in)
		{
			return new Contact(in);
		}

		public Contact[] newArray(int size)
		{
			return new Contact[size];
		}
	};

	private Contact(Parcel in)
	{
		contact_id = in.readInt();
		facebook_id = in.readLong();
		zip_code = in.readString();
		first_name = in.readString();
		last_name = in.readString();
		email = in.readString();
		phone = in.readString();
		user_name = in.readString();
		password = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		zip_code_obj = in.readParcelable(Zip_Code.class.getClassLoader());
	}

	public int getContactId()
	{
		return this.contact_id;
	}
	public void setContactId(int contact_id)
	{
		this.contact_id = contact_id;
	}

	public long getFacebookId()
	{
		return this.facebook_id;
	}
	public void setFacebookId(long facebook_id)
	{
		this.facebook_id = facebook_id;
	}

	public String getZipCode()
	{
		return this.zip_code;
	}
	public void setZipCode(String zip_code)
	{
		this.zip_code = zip_code;
	}

	public String getFirstName()
	{
		return this.first_name;
	}
	public void setFirstName(String first_name)
	{
		this.first_name = first_name;
	}

	public String getLastName()
	{
		return this.last_name;
	}
	public void setLastName(String last_name)
	{
		this.last_name = last_name;
	}

	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return this.phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getUserName()
	{
		return this.user_name;
	}
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
	}

	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
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
