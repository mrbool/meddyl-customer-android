package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Application_Type implements Parcelable
{
	private int application_type_id;
	private String application_type;
	private String version;
	private boolean is_down;
	private String down_message;

	public Application_Type()
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
		out.writeInt(application_type_id);
		out.writeString(application_type);
		out.writeString(version);
		out.writeByte((byte) (is_down ? 1:0));
		out.writeString(down_message);
	}

	public static final Parcelable.Creator<Application_Type> CREATOR = new Parcelable.Creator<Application_Type>() 
	{
		public Application_Type createFromParcel(Parcel in)
		{
			return new Application_Type(in);
		}

		public Application_Type[] newArray(int size)
		{
			return new Application_Type[size];
		}
	};

	private Application_Type(Parcel in)
	{
		application_type_id = in.readInt();
		application_type = in.readString();
		version = in.readString();
		is_down = in.readByte() != 0;
		down_message = in.readString();
	}

	public int getApplicationTypeId()
	{
		return this.application_type_id;
	}
	public void setApplicationTypeId(int application_type_id)
	{
		this.application_type_id = application_type_id;
	}

	public String getApplicationType()
	{
		return this.application_type;
	}
	public void setApplicationType(String application_type)
	{
		this.application_type = application_type;
	}

	public String getVersion()
	{
		return this.version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}

	public boolean getIsDown()
	{
		return this.is_down;
	}
	public void setIsDown(boolean is_down)
	{
		this.is_down = is_down;
	}

	public String getDownMessage()
	{
		return this.down_message;
	}
	public void setDownMessage(String down_message)
	{
		this.down_message = down_message;
	}
}
