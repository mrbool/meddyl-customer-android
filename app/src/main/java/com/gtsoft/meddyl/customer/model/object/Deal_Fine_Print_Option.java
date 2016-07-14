package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Deal_Fine_Print_Option implements Parcelable
{
	private int id;
	private int deal_id;
	private int option_id;
	private Deal deal_obj;
	private Fine_Print_Option fine_print_option_obj;

	public Deal_Fine_Print_Option()
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
		out.writeInt(deal_id);
		out.writeInt(option_id);
		out.writeParcelable(deal_obj, flag);
		out.writeParcelable(fine_print_option_obj, flag);
	}

	public static final Parcelable.Creator<Deal_Fine_Print_Option> CREATOR = new Parcelable.Creator<Deal_Fine_Print_Option>() 
	{
		public Deal_Fine_Print_Option createFromParcel(Parcel in)
		{
			return new Deal_Fine_Print_Option(in);
		}

		public Deal_Fine_Print_Option[] newArray(int size)
		{
			return new Deal_Fine_Print_Option[size];
		}
	};

	private Deal_Fine_Print_Option(Parcel in)
	{
		id = in.readInt();
		deal_id = in.readInt();
		option_id = in.readInt();
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
		fine_print_option_obj = in.readParcelable(Fine_Print_Option.class.getClassLoader());
	}

	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
	}

	public int getOptionId()
	{
		return this.option_id;
	}
	public void setOptionId(int option_id)
	{
		this.option_id = option_id;
	}

	public Deal getDealObj()
	{
		return this.deal_obj;
	}
	public void setDealObj(Deal deal_obj)
	{
		this.deal_obj = deal_obj;
	}

	public Fine_Print_Option getFinePrintOptionObj()
	{
		return this.fine_print_option_obj;
	}
	public void setFinePrintOptionObj(Fine_Print_Option fine_print_option_obj)
	{
		this.fine_print_option_obj = fine_print_option_obj;
	}
}
