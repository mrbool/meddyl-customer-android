package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Merchant_Contact implements Parcelable
{
	private int merchant_contact_id;
	private int merchant_id;
	private int contact_id;
	private int status_id;
	private int validation_id;
	private String title;
	private Date accept_terms_date_utc_stamp;
	private Date entry_date_utc_stamp;
	private Contact contact_obj;
	private Merchant merchant_obj;
	private Merchant_Contact_Status merchant_contact_status_obj;
	private Merchant_Contact_Validation merchant_contact_validation_obj;
	private String search;
	private Login_Log login_log_obj;

	public Merchant_Contact()
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
		out.writeInt(merchant_contact_id);
		out.writeInt(merchant_id);
		out.writeInt(contact_id);
		out.writeInt(status_id);
		out.writeInt(validation_id);
		out.writeString(title);
		out.writeSerializable(accept_terms_date_utc_stamp);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(contact_obj, flag);
		out.writeParcelable(merchant_obj, flag);
		out.writeParcelable(merchant_contact_status_obj, flag);
		out.writeParcelable(merchant_contact_validation_obj, flag);
		out.writeString(search);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Parcelable.Creator<Merchant_Contact> CREATOR = new Parcelable.Creator<Merchant_Contact>() 
	{
		public Merchant_Contact createFromParcel(Parcel in)
		{
			return new Merchant_Contact(in);
		}

		public Merchant_Contact[] newArray(int size)
		{
			return new Merchant_Contact[size];
		}
	};

	private Merchant_Contact(Parcel in)
	{
		merchant_contact_id = in.readInt();
		merchant_id = in.readInt();
		contact_id = in.readInt();
		status_id = in.readInt();
		validation_id = in.readInt();
		title = in.readString();
		accept_terms_date_utc_stamp = (Date) in.readSerializable();
		entry_date_utc_stamp = (Date) in.readSerializable();
		contact_obj = in.readParcelable(Contact.class.getClassLoader());
		merchant_obj = in.readParcelable(Merchant.class.getClassLoader());
		merchant_contact_status_obj = in.readParcelable(Merchant_Contact_Status.class.getClassLoader());
		merchant_contact_validation_obj = in.readParcelable(Merchant_Contact_Validation.class.getClassLoader());
		search = in.readString();
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
	}

	public int getMerchantContactId()
	{
		return this.merchant_contact_id;
	}
	public void setMerchantContactId(int merchant_contact_id)
	{
		this.merchant_contact_id = merchant_contact_id;
	}

	public int getMerchantId()
	{
		return this.merchant_id;
	}
	public void setMerchantId(int merchant_id)
	{
		this.merchant_id = merchant_id;
	}

	public int getContactId()
	{
		return this.contact_id;
	}
	public void setContactId(int contact_id)
	{
		this.contact_id = contact_id;
	}

	public int getStatusId()
	{
		return this.status_id;
	}
	public void setStatusId(int status_id)
	{
		this.status_id = status_id;
	}

	public int getValidationId()
	{
		return this.validation_id;
	}
	public void setValidationId(int validation_id)
	{
		this.validation_id = validation_id;
	}

	public String getTitle()
	{
		return this.title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public Date getAcceptTermsDateUtcStamp()
	{
		return this.accept_terms_date_utc_stamp;
	}
	public void setAcceptTermsDateUtcStamp(Date accept_terms_date_utc_stamp)
	{
		this.accept_terms_date_utc_stamp = accept_terms_date_utc_stamp;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Contact getContactObj()
	{
		return this.contact_obj;
	}
	public void setContactObj(Contact contact_obj)
	{
		this.contact_obj = contact_obj;
	}

	public Merchant getMerchantObj()
	{
		return this.merchant_obj;
	}
	public void setMerchantObj(Merchant merchant_obj)
	{
		this.merchant_obj = merchant_obj;
	}

	public Merchant_Contact_Status getMerchantContactStatusObj()
	{
		return this.merchant_contact_status_obj;
	}
	public void setMerchantContactStatusObj(Merchant_Contact_Status merchant_contact_status_obj)
	{
		this.merchant_contact_status_obj = merchant_contact_status_obj;
	}

	public Merchant_Contact_Validation getMerchantContactValidationObj()
	{
		return this.merchant_contact_validation_obj;
	}
	public void setMerchantContactValidationObj(Merchant_Contact_Validation merchant_contact_validation_obj)
	{
		this.merchant_contact_validation_obj = merchant_contact_validation_obj;
	}

	public String getSearch()
	{
		return this.search;
	}
	public void setSearch(String search)
	{
		this.search = search;
	}

	public Login_Log getLoginLogObj()
	{
		return this.login_log_obj;
	}
	public void setLoginLogObj(Login_Log login_log_obj)
	{
		this.login_log_obj = login_log_obj;
	}
}
