package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Credit_Card implements Parcelable
{
	private int credit_card_id;
	private int type_id;
	private int merchant_contact_id;
	private int customer_id;
	private String card_holder_name;
	private String card_number;
	private Byte[] card_number_encrypted;
	private String expiration_date;
	private String billing_zip_code;
	private Date entry_date_utc_stamp;
	private boolean default_flag;
	private boolean deleted;
	private Credit_Card_Type credit_card_type_obj;
	private Customer customer_obj;
	private Merchant_Contact merchant_contact_obj;
	private String security_code;
	private Application_Type application_type_obj;
	private Login_Log login_log_obj;

	public Credit_Card()
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
		out.writeInt(credit_card_id);
		out.writeInt(type_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(customer_id);
		out.writeString(card_holder_name);
		out.writeString(card_number);
		out.writeSerializable(card_number_encrypted);
		out.writeString(expiration_date);
		out.writeString(billing_zip_code);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeByte((byte) (default_flag ? 1:0));
		out.writeByte((byte) (deleted ? 1:0));
		out.writeParcelable(credit_card_type_obj, flag);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
		out.writeString(security_code);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Creator<Credit_Card> CREATOR = new Creator<Credit_Card>()
	{
		public Credit_Card createFromParcel(Parcel in)
		{
			return new Credit_Card(in);
		}

		public Credit_Card[] newArray(int size)
		{
			return new Credit_Card[size];
		}
	};

	private Credit_Card(Parcel in)
	{
		credit_card_id = in.readInt();
		type_id = in.readInt();
		merchant_contact_id = in.readInt();
		customer_id = in.readInt();
		card_holder_name = in.readString();
		card_number = in.readString();
		card_number_encrypted = (Byte[]) in.readSerializable();
		expiration_date = in.readString();
		billing_zip_code = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		default_flag = in.readByte() != 0;
		deleted = in.readByte() != 0;
		credit_card_type_obj = in.readParcelable(Credit_Card_Type.class.getClassLoader());
		customer_obj = in.readParcelable(Customer.class.getClassLoader());
		merchant_contact_obj = in.readParcelable(Merchant_Contact.class.getClassLoader());
		security_code = in.readString();
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
	}

	public int getCreditCardId()
	{
		return this.credit_card_id;
	}
	public void setCreditCardId(int credit_card_id)
	{
		this.credit_card_id = credit_card_id;
	}

	public int getTypeId()
	{
		return this.type_id;
	}
	public void setTypeId(int type_id)
	{
		this.type_id = type_id;
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

	public String getCardHolderName()
	{
		return this.card_holder_name;
	}
	public void setCardHolderName(String card_holder_name)
	{
		this.card_holder_name = card_holder_name;
	}

	public String getCardNumber()
	{
		return this.card_number;
	}
	public void setCardNumber(String card_number)
	{
		this.card_number = card_number;
	}

	public Byte[] getCardNumberEncrypted()
	{
		return this.card_number_encrypted;
	}
	public void setCardNumberEncrypted(Byte[] card_number_encrypted)
	{
		this.card_number_encrypted = card_number_encrypted;
	}

	public String getExpirationDate()
	{
		return this.expiration_date;
	}
	public void setExpirationDate(String expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public String getBillingZipCode()
	{
		return this.billing_zip_code;
	}
	public void setBillingZipCode(String billing_zip_code)
	{
		this.billing_zip_code = billing_zip_code;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public boolean getDefaultFlag()
	{
		return this.default_flag;
	}
	public void setDefaultFlag(boolean default_flag)
	{
		this.default_flag = default_flag;
	}

	public boolean getDeleted()
	{
		return this.deleted;
	}
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	public Credit_Card_Type getCreditCardTypeObj()
	{
		return this.credit_card_type_obj;
	}
	public void setCreditCardTypeObj(Credit_Card_Type credit_card_type_obj)
	{
		this.credit_card_type_obj = credit_card_type_obj;
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

	public String getSecurityCode()
	{
		return this.security_code;
	}
	public void setSecurityCode(String security_code)
	{
		this.security_code = security_code;
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
