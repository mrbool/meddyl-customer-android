package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Certificate_Log implements Parcelable
{
	private int log_id;
	private int certificate_id;
	private int merchant_contact_id;
	private int customer_id;
	private int status_id;
	private String notes;
	private Date entry_date_utc_stamp;
	private Certificate certificate_obj;
	private Certificate_Status certificate_status_obj;
	private Customer customer_obj;
	private Merchant_Contact merchant_contact_obj;

	public Certificate_Log()
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
		out.writeInt(certificate_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(customer_id);
		out.writeInt(status_id);
		out.writeString(notes);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(certificate_obj, flag);
		out.writeParcelable(certificate_status_obj, flag);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
	}

	public static final Parcelable.Creator<Certificate_Log> CREATOR = new Parcelable.Creator<Certificate_Log>() 
	{
		public Certificate_Log createFromParcel(Parcel in)
		{
			return new Certificate_Log(in);
		}

		public Certificate_Log[] newArray(int size)
		{
			return new Certificate_Log[size];
		}
	};

	private Certificate_Log(Parcel in)
	{
		log_id = in.readInt();
		certificate_id = in.readInt();
		merchant_contact_id = in.readInt();
		customer_id = in.readInt();
		status_id = in.readInt();
		notes = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		certificate_obj = in.readParcelable(Certificate.class.getClassLoader());
		certificate_status_obj = in.readParcelable(Certificate_Status.class.getClassLoader());
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
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

	public int getCertificateId()
	{
		return this.certificate_id;
	}
	public void setCertificateId(int certificate_id)
	{
		this.certificate_id = certificate_id;
	}

	public int getMerchantContactId()
	{
		return this.merchant_contact_id;
	}
	public void setMerchantContactId(int merchant_contact_id)
	{
		this.merchant_contact_id = merchant_contact_id;
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
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

	public Certificate getCertificateObj()
	{
		return this.certificate_obj;
	}
	public void setCertificateObj(Certificate certificate_obj)
	{
		this.certificate_obj = certificate_obj;
	}

	public Certificate_Status getCertificateStatusObj()
	{
		return this.certificate_status_obj;
	}
	public void setCertificateStatusObj(Certificate_Status certificate_status_obj)
	{
		this.certificate_status_obj = certificate_status_obj;
	}

	public Customer getCustomerObj()
	{
		return this.customer_obj;
	}
	public void setCustomerObj(Customer customer_obj)
	{
		this.customer_obj = customer_obj;
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
