package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Fine_Print_Option implements Parcelable
{
	private int option_id;
	private String display;
	private String value;
	private boolean is_selected;
	private boolean is_active;
	private int order_id;

	public Fine_Print_Option()
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
		out.writeInt(option_id);
		out.writeString(display);
		out.writeString(value);
		out.writeByte((byte) (is_selected ? 1:0));
		out.writeByte((byte) (is_active ? 1:0));
		out.writeInt(order_id);
	}

	public static final Parcelable.Creator<Fine_Print_Option> CREATOR = new Parcelable.Creator<Fine_Print_Option>() 
	{
		public Fine_Print_Option createFromParcel(Parcel in)
		{
			return new Fine_Print_Option(in);
		}

		public Fine_Print_Option[] newArray(int size)
		{
			return new Fine_Print_Option[size];
		}
	};

	private Fine_Print_Option(Parcel in)
	{
		option_id = in.readInt();
		display = in.readString();
		value = in.readString();
		is_selected = in.readByte() != 0;
		is_active = in.readByte() != 0;
		order_id = in.readInt();
	}

	public int getOptionId()
	{
		return this.option_id;
	}
	public void setOptionId(int option_id)
	{
		this.option_id = option_id;
	}

	public String getDisplay()
	{
		return this.display;
	}
	public void setDisplay(String display)
	{
		this.display = display;
	}

	public String getValue()
	{
		return this.value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean getIsSelected()
	{
		return this.is_selected;
	}
	public void setIsSelected(boolean is_selected)
	{
		this.is_selected = is_selected;
	}

	public boolean getIsActive()
	{
		return this.is_active;
	}
	public void setIsActive(boolean is_active)
	{
		this.is_active = is_active;
	}

	public int getOrderId()
	{
		return this.order_id;
	}
	public void setOrderId(int order_id)
	{
		this.order_id = order_id;
	}
}
