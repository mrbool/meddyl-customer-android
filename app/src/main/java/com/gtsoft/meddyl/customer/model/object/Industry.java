package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Industry implements Parcelable
{
	private int industry_id;
	private int parent_industry_id;
	private String industry;
	private String image;
	private int order_id;
	private Industry industry_obj;
	private int active;

	public Industry()
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
		out.writeInt(industry_id);
		out.writeInt(parent_industry_id);
		out.writeString(industry);
		out.writeString(image);
		out.writeInt(order_id);
		out.writeParcelable(industry_obj, flag);
		out.writeInt(active);
	}

	public static final Parcelable.Creator<Industry> CREATOR = new Parcelable.Creator<Industry>() 
	{
		public Industry createFromParcel(Parcel in)
		{
			return new Industry(in);
		}

		public Industry[] newArray(int size)
		{
			return new Industry[size];
		}
	};

	private Industry(Parcel in)
	{
		industry_id = in.readInt();
		parent_industry_id = in.readInt();
		industry = in.readString();
		image = in.readString();
		order_id = in.readInt();
		industry_obj = in.readParcelable(Industry.class.getClassLoader());
		active = in.readInt();
	}

	public int getIndustryId()
	{
		return this.industry_id;
	}
	public void setIndustryId(int industry_id)
	{
		this.industry_id = industry_id;
	}

	public int getParentIndustryId()
	{
		return this.parent_industry_id;
	}
	public void setParentIndustryId(int parent_industry_id)
	{
		this.parent_industry_id = parent_industry_id;
	}

	public String getIndustry()
	{
		return this.industry;
	}
	public void setIndustry(String industry)
	{
		this.industry = industry;
	}

	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}

	public int getOrderId()
	{
		return this.order_id;
	}
	public void setOrderId(int order_id)
	{
		this.order_id = order_id;
	}

	public Industry getIndustryObj()
	{
		return this.industry_obj;
	}
	public void setIndustryObj(Industry industry_obj)
	{
		this.industry_obj = industry_obj;
	}

	public int getActive()
	{
		return this.active;
	}
	public void setActive(int active)
	{
		this.active = active;
	}
}
