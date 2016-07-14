package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Email_Template implements Parcelable
{
	private int template_id;
	private String from_email;
	private String from_display;
	private String description;
	private String subject;
	private String body;
	private boolean is_html;

	public Email_Template()
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
		out.writeString(from_email);
		out.writeString(from_display);
		out.writeString(description);
		out.writeString(subject);
		out.writeString(body);
		out.writeByte((byte) (is_html ? 1:0));
	}

	public static final Parcelable.Creator<Email_Template> CREATOR = new Parcelable.Creator<Email_Template>() 
	{
		public Email_Template createFromParcel(Parcel in)
		{
			return new Email_Template(in);
		}

		public Email_Template[] newArray(int size)
		{
			return new Email_Template[size];
		}
	};

	private Email_Template(Parcel in)
	{
		template_id = in.readInt();
		from_email = in.readString();
		from_display = in.readString();
		description = in.readString();
		subject = in.readString();
		body = in.readString();
		is_html = in.readByte() != 0;
	}

	public int getTemplateId()
	{
		return this.template_id;
	}
	public void setTemplateId(int template_id)
	{
		this.template_id = template_id;
	}

	public String getFromEmail()
	{
		return this.from_email;
	}
	public void setFromEmail(String from_email)
	{
		this.from_email = from_email;
	}

	public String getFromDisplay()
	{
		return this.from_display;
	}
	public void setFromDisplay(String from_display)
	{
		this.from_display = from_display;
	}

	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getSubject()
	{
		return this.subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getBody()
	{
		return this.body;
	}
	public void setBody(String body)
	{
		this.body = body;
	}

	public boolean getIsHtml()
	{
		return this.is_html;
	}
	public void setIsHtml(boolean is_html)
	{
		this.is_html = is_html;
	}
}
