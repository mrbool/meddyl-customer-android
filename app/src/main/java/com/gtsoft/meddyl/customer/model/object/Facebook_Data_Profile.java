package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Facebook_Data_Profile implements Parcelable
{
	private int id;
	private long fb_profile_id;
	private String birthday;
	private String email;
	private String first_name;
	private String gender;
	private String last_name;
	private String link;
	private String locale;
	private String middle_name;
	private String name;
	private String timezone;
	private String updated_time;
	private String username;
	private String verified;
	private Date entry_date_utc_stamp;

	public Facebook_Data_Profile()
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
		out.writeString(birthday);
		out.writeString(email);
		out.writeString(first_name);
		out.writeString(gender);
		out.writeString(last_name);
		out.writeString(link);
		out.writeString(locale);
		out.writeString(middle_name);
		out.writeString(name);
		out.writeString(timezone);
		out.writeString(updated_time);
		out.writeString(username);
		out.writeString(verified);
		out.writeSerializable(entry_date_utc_stamp);
	}

	public static final Parcelable.Creator<Facebook_Data_Profile> CREATOR = new Parcelable.Creator<Facebook_Data_Profile>() 
	{
		public Facebook_Data_Profile createFromParcel(Parcel in)
		{
			return new Facebook_Data_Profile(in);
		}

		public Facebook_Data_Profile[] newArray(int size)
		{
			return new Facebook_Data_Profile[size];
		}
	};

	private Facebook_Data_Profile(Parcel in)
	{
		id = in.readInt();
		fb_profile_id = in.readLong();
		birthday = in.readString();
		email = in.readString();
		first_name = in.readString();
		gender = in.readString();
		last_name = in.readString();
		link = in.readString();
		locale = in.readString();
		middle_name = in.readString();
		name = in.readString();
		timezone = in.readString();
		updated_time = in.readString();
		username = in.readString();
		verified = in.readString();
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

	public String getBirthday()
	{
		return this.birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return this.first_name;
	}
	public void setFirstName(String first_name)
	{
		this.first_name = first_name;
	}

	public String getGender()
	{
		return this.gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getLastName()
	{
		return this.last_name;
	}
	public void setLastName(String last_name)
	{
		this.last_name = last_name;
	}

	public String getLink()
	{
		return this.link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}

	public String getLocale()
	{
		return this.locale;
	}
	public void setLocale(String locale)
	{
		this.locale = locale;
	}

	public String getMiddleName()
	{
		return this.middle_name;
	}
	public void setMiddleName(String middle_name)
	{
		this.middle_name = middle_name;
	}

	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getTimezone()
	{
		return this.timezone;
	}
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	public String getUpdatedTime()
	{
		return this.updated_time;
	}
	public void setUpdatedTime(String updated_time)
	{
		this.updated_time = updated_time;
	}

	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getVerified()
	{
		return this.verified;
	}
	public void setVerified(String verified)
	{
		this.verified = verified;
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
