package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Deal_Log implements Parcelable
{
	private int log_id;
	private int deal_id;
	private int merchant_contact_id;
	private int status_id;
	private String notes;
	private Date entry_date_utc_stamp;
	private Deal deal_obj;
	private Deal_Status deal_status_obj;
	private Merchant_Contact merchant_contact_obj;

	public Deal_Log()
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
		out.writeInt(log_id);
		out.writeInt(deal_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(status_id);
		out.writeString(notes);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(deal_obj, flag);
		out.writeParcelable(deal_status_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
	}

	public static final Parcelable.Creator<Deal_Log> CREATOR = new Parcelable.Creator<Deal_Log>() 
	{
		public Deal_Log createFromParcel(Parcel in)
		{
			return new Deal_Log(in);
		}

		public Deal_Log[] newArray(int size)
		{
			return new Deal_Log[size];
		}
	};

	private Deal_Log(Parcel in)
	{
		log_id = in.readInt();
		deal_id = in.readInt();
		merchant_contact_id = in.readInt();
		status_id = in.readInt();
		notes = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
		deal_status_obj = in.readParcelable(Deal_Status.class.getClassLoader());
		merchant_contact_obj = in.readParcelable(Merchant_Contact.class.getClassLoader());
	}

	public int getLogId()
	{
		return this.log_id;
	}
	public void setLogId(int log_id)
	{
		this.log_id = log_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
	}

	public int getMerchantContactId()
	{
		return this.merchant_contact_id;
	}
	public void setMerchantContactId(int merchant_contact_id)
	{
		this.merchant_contact_id = merchant_contact_id;
	}

	public int getStatusId()
	{
		return this.status_id;
	}
	public void setStatusId(int status_id)
	{
		this.status_id = status_id;
	}

	public String getNotes()
	{
		return this.notes;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
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

	public Deal_Status getDealStatusObj()
	{
		return this.deal_status_obj;
	}
	public void setDealStatusObj(Deal_Status deal_status_obj)
	{
		this.deal_status_obj = deal_status_obj;
	}

	public Merchant_Contact getMerchantContactObj()
	{
		return this.merchant_contact_obj;
	}
	public void setMerchantContactObj(Merchant_Contact merchant_contact_obj)
	{
		this.merchant_contact_obj = merchant_contact_obj;
	}
}
