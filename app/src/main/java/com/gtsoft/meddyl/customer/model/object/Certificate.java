package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Certificate implements Parcelable
{
	private int certificate_id;
	private int deal_id;
	private int customer_id;
	private int status_id;
	private String certificate_code;
	private Date assigned_date;
	private Date start_date;
	private Date expiration_date;
	private Date redeemed_date;
	private BigDecimal amount;
	private Date entry_date_utc_stamp;
	private Certificate_Status certificate_status_obj;
	private Customer customer_obj;
	private Deal deal_obj;
	private String status_text_1;
	private String status_text_2;
	private String search;
	private Application_Type application_type_obj;
	private Login_Log login_log_obj;

	public Certificate()
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
		out.writeInt(certificate_id);
		out.writeInt(deal_id);
		out.writeInt(customer_id);
		out.writeInt(status_id);
		out.writeString(certificate_code);
		out.writeSerializable(assigned_date);
		out.writeSerializable(start_date);
		out.writeSerializable(expiration_date);
		out.writeSerializable(redeemed_date);
		out.writeSerializable(amount);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(certificate_status_obj, flag);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(deal_obj, flag);
		out.writeString(status_text_1);
		out.writeString(status_text_2);
		out.writeString(search);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Parcelable.Creator<Certificate> CREATOR = new Parcelable.Creator<Certificate>() 
	{
		public Certificate createFromParcel(Parcel in)
		{
			return new Certificate(in);
		}

		public Certificate[] newArray(int size)
		{
			return new Certificate[size];
		}
	};

	private Certificate(Parcel in)
	{
		certificate_id = in.readInt();
		deal_id = in.readInt();
		customer_id = in.readInt();
		status_id = in.readInt();
		certificate_code = in.readString();
		assigned_date = (Date) in.readSerializable();
		start_date = (Date) in.readSerializable();
		expiration_date = (Date) in.readSerializable();
		redeemed_date = (Date) in.readSerializable();
		amount = (BigDecimal) in.readSerializable();
		entry_date_utc_stamp = (Date) in.readSerializable();
		certificate_status_obj = in.readParcelable(Certificate_Status.class.getClassLoader());
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
		status_text_1 = in.readString();
		status_text_2 = in.readString();
		search = in.readString();
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
	}

	public int getCertificateId()
	{
		return this.certificate_id;
	}
	public void setCertificateId(int certificate_id)
	{
		this.certificate_id = certificate_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
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

	public String getCertificateCode()
	{
		return this.certificate_code;
	}
	public void setCertificateCode(String certificate_code)
	{
		this.certificate_code = certificate_code;
	}

	public Date getAssignedDate()
	{
		return this.assigned_date;
	}
	public void setAssignedDate(Date assigned_date)
	{
		this.assigned_date = assigned_date;
	}

	public Date getStartDate()
	{
		return this.start_date;
	}
	public void setStartDate(Date start_date)
	{
		this.start_date = start_date;
	}

	public Date getExpirationDate()
	{
		return this.expiration_date;
	}
	public void setExpirationDate(Date expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public Date getRedeemedDate()
	{
		return this.redeemed_date;
	}
	public void setRedeemedDate(Date redeemed_date)
	{
		this.redeemed_date = redeemed_date;
	}

	public BigDecimal getAmount()
	{
		return this.amount;
	}
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
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

	public Deal getDealObj()
	{
		return this.deal_obj;
	}
	public void setDealObj(Deal deal_obj)
	{
		this.deal_obj = deal_obj;
	}

	public String getStatusText1()
	{
		return this.status_text_1;
	}
	public void setStatusText1(String status_text_1)
	{
		this.status_text_1 = status_text_1;
	}

	public String getStatusText2()
	{
		return this.status_text_2;
	}
	public void setStatusText2(String status_text_2)
	{
		this.status_text_2 = status_text_2;
	}

	public String getSearch()
	{
		return this.search;
	}
	public void setSearch(String search)
	{
		this.search = search;
	}

	public Application_Type getApplicationTypeObj()
	{
		return this.application_type_obj;
	}
	public void setApplicationTypeObj(Application_Type application_type_obj)
	{
		this.application_type_obj = application_type_obj;
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
