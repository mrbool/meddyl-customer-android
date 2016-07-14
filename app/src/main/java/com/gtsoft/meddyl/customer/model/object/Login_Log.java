package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Login_Log implements Parcelable
{
	private int log_id;
	private int contact_id;
	private int customer_id;
	private int merchant_contact_id;
	private int application_type_id;
	private boolean registered;
	private boolean auto_login;
	private String ip_address;
	private Date login_date_utc_stamp;
	private String auth_token;
	private Application_Type application_type_obj;
	private Contact contact_obj;
	private Customer customer_obj;
	private Merchant_Contact merchant_contact_obj;

	public Login_Log()
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
		out.writeInt(contact_id);
		out.writeInt(customer_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(application_type_id);
		out.writeByte((byte) (registered ? 1:0));
		out.writeByte((byte) (auto_login ? 1:0));
		out.writeString(ip_address);
		out.writeSerializable(login_date_utc_stamp);
		out.writeString(auth_token);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(contact_obj, flag);
		out.writeParcelable(customer_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
	}

	public static final Parcelable.Creator<Login_Log> CREATOR = new Parcelable.Creator<Login_Log>() 
	{
		public Login_Log createFromParcel(Parcel in)
		{
			return new Login_Log(in);
		}

		public Login_Log[] newArray(int size)
		{
			return new Login_Log[size];
		}
	};

	private Login_Log(Parcel in)
	{
		log_id = in.readInt();
		contact_id = in.readInt();
		customer_id = in.readInt();
		merchant_contact_id = in.readInt();
		application_type_id = in.readInt();
		registered = in.readByte() != 0;
		auto_login = in.readByte() != 0;
		ip_address = in.readString();
		login_date_utc_stamp = (Date) in.readSerializable();
		auth_token = in.readString();
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		contact_obj = in.readParcelable(Contact.class.getClassLoader());
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

	public int getContactId()
	{
		return this.contact_id;
	}
	public void setContactId(int contact_id)
	{
		this.contact_id = contact_id;
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
	}

	public int getMerchantContactId()
	{
		return this.merchant_contact_id;
	}
	public void setMerchantContactId(int merchant_contact_id)
	{
		this.merchant_contact_id = merchant_contact_id;
	}

	public int getApplicationTypeId()
	{
		return this.application_type_id;
	}
	public void setApplicationTypeId(int application_type_id)
	{
		this.application_type_id = application_type_id;
	}

	public boolean getRegistered()
	{
		return this.registered;
	}
	public void setRegistered(boolean registered)
	{
		this.registered = registered;
	}

	public boolean getAutoLogin()
	{
		return this.auto_login;
	}
	public void setAutoLogin(boolean auto_login)
	{
		this.auto_login = auto_login;
	}

	public String getIpAddress()
	{
		return this.ip_address;
	}
	public void setIpAddress(String ip_address)
	{
		this.ip_address = ip_address;
	}

	public Date getLoginDateUtcStamp()
	{
		return this.login_date_utc_stamp;
	}
	public void setLoginDateUtcStamp(Date login_date_utc_stamp)
	{
		this.login_date_utc_stamp = login_date_utc_stamp;
	}

	public String getAuthToken()
	{
		return this.auth_token;
	}
	public void setAuthToken(String auth_token)
	{
		this.auth_token = auth_token;
	}

	public Application_Type getApplicationTypeObj()
	{
		return this.application_type_obj;
	}
	public void setApplicationTypeObj(Application_Type application_type_obj)
	{
		this.application_type_obj = application_type_obj;
	}

	public Contact getContactObj()
	{
		return this.contact_obj;
	}
	public void setContactObj(Contact contact_obj)
	{
		this.contact_obj = contact_obj;
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
