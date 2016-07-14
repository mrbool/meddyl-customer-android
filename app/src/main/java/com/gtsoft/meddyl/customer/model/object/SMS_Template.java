package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class SMS_Template implements Parcelable
{
	private int template_id;
	private String description;
	private String body;

	public SMS_Template()
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
		out.writeInt(template_id);
		out.writeString(description);
		out.writeString(body);
	}

	public static final Parcelable.Creator<SMS_Template> CREATOR = new Parcelable.Creator<SMS_Template>() 
	{
		public SMS_Template createFromParcel(Parcel in)
		{
			return new SMS_Template(in);
		}

		public SMS_Template[] newArray(int size)
		{
			return new SMS_Template[size];
		}
	};

	private SMS_Template(Parcel in)
	{
		template_id = in.readInt();
		description = in.readString();
		body = in.readString();
	}

	public int getTemplateId()
	{
		return this.template_id;
	}
	public void setTemplateId(int template_id)
	{
		this.template_id = template_id;
	}

	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getBody()
	{
		return this.body;
	}
	public void setBody(String body)
	{
		this.body = body;
	}
}
