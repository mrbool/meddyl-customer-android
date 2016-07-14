package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Payment_Log implements Parcelable
{
	private int log_id;
	private int payment_id;
	private int deal_id;
	private int certificate_id;
	private int merchant_contact_id;
	private int customer_id;
	private int credit_card_id;
	private BigDecimal amount;
	private boolean is_successful;
	private String type;
	private String notes;
	private Date entry_date_utc_stamp;
	private Certificate certificate_obj;
	private Customer customer_obj;
	private Deal deal_obj;
	private Merchant_Contact merchant_contact_obj;

	public Payment_Log()
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
		out.writeInt(payment_id);
		out.writeInt(deal_id);
		out.writeInt(certificate_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(customer_id);
		out.writeInt(credit_card_id);
		out.writeSerializable(amount);
		out.writeByte((byte) (is_successful ? 1:0));
		out.writeString(type);
		out.writeString(notes);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(certificate_obj, flag);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(deal_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
	}

	public static final Parcelable.Creator<Payment_Log> CREATOR = new Parcelable.Creator<Payment_Log>() 
	{
		public Payment_Log createFromParcel(Parcel in)
		{
			return new Payment_Log(in);
		}

		public Payment_Log[] newArray(int size)
		{
			return new Payment_Log[size];
		}
	};

	private Payment_Log(Parcel in)
	{
		log_id = in.readInt();
		payment_id = in.readInt();
		deal_id = in.readInt();
		certificate_id = in.readInt();
		merchant_contact_id = in.readInt();
		customer_id = in.readInt();
		credit_card_id = in.readInt();
		amount = (BigDecimal) in.readSerializable();
		is_successful = in.readByte() != 0;
		type = in.readString();
		notes = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		certificate_obj = in.readParcelable(Certificate.class.getClassLoader());
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
		deal_obj = in.readParcelable(Deal.class.getClassLoader());
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

	public int getPaymentId()
	{
		return this.payment_id;
	}
	public void setPaymentId(int payment_id)
	{
		this.payment_id = payment_id;
	}

	public int getDealId()
	{
		return this.deal_id;
	}
	public void setDealId(int deal_id)
	{
		this.deal_id = deal_id;
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

	public int getCreditCardId()
	{
		return this.credit_card_id;
	}
	public void setCreditCardId(int credit_card_id)
	{
		this.credit_card_id = credit_card_id;
	}

	public BigDecimal getAmount()
	{
		return this.amount;
	}
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public boolean getIsSuccessful()
	{
		return this.is_successful;
	}
	public void setIsSuccessful(boolean is_successful)
	{
		this.is_successful = is_successful;
	}

	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
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

	public Merchant_Contact getMerchantContactObj()
	{
		return this.merchant_contact_obj;
	}
	public void setMerchantContactObj(Merchant_Contact merchant_contact_obj)
	{
		this.merchant_contact_obj = merchant_contact_obj;
	}
}
