package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Deal_Validation implements Parcelable
{
	private int validation_id;
	private int deal_id;
	private String validation_code;
	private boolean is_validated;
	private Date entry_date_utc_stamp;
	private Deal deal_obj;

	public Deal_Validation()
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
		out.writeInt(deal_id);
		out.writeString(validation_code);
		out.writeByte((byte) (is_validated ? 1:0));
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(deal_obj, flag);
	}

	public static final Parcelable.Creator<Deal_Validation> CREATOR = new Parcelable.Creator<Deal_Validation>() 
	{
		public Deal_Validation createFromParcel(Parcel in)
		{
			return new Deal_Validation(in);
		}

		public Deal_Validation[] newArray(int size)
		{
			return new Deal_Validation[size];
		}
	};

	private Deal_Validation(Parcel in)
	{
		validation_id = in.readInt();
		deal_id = in.readInt();
		validation_code = in.readString();
		is_validated = in.readByte() != 0;
		entry_date_utc_stamp = (Date) in.readSerializable();
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
	}

	public int getValidationId()
	{
		return this.validation_id;
	}
	public void setValidationId(int validation_id)
	{
		this.validation_id = validation_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
	}

	public String getValidationCode()
	{
		return this.validation_code;
	}
	public void setValidationCode(String validation_code)
	{
		this.validation_code = validation_code;
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

	public Deal getDealObj()
	{
		return this.deal_obj;
	}
	public void setDealObj(Deal deal_obj)
	{
		this.deal_obj = deal_obj;
	}
}
