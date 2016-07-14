package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Customer implements Parcelable
{
	private int customer_id;
	private int contact_id;
	private int status_id;
	private int search_location_type_id;
	private String search_zip_code;
	private int search_industry_id;
	private int deal_range;
	private Date accept_terms_date_utc_stamp;
	private Date entry_date_utc_stamp;
	private Contact contact_obj;
	private Customer_Search_Location_Type customer_search_location_type_obj;
	private Customer_Status customer_status_obj;
	private Industry industry_obj;
	private Zip_Code zip_code_obj;
	private Application_Type application_type_obj;
	private Login_Log login_log_obj;
	private Promotion promotion_obj;

	public Customer()
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
		out.writeInt(customer_id);
		out.writeInt(contact_id);
		out.writeInt(status_id);
		out.writeInt(search_location_type_id);
		out.writeString(search_zip_code);
		out.writeInt(search_industry_id);
		out.writeInt(deal_range);
		out.writeSerializable(accept_terms_date_utc_stamp);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(contact_obj, flag);
		out.writeParcelable(customer_search_location_type_obj, flag);
		out.writeParcelable(customer_status_obj, flag);
		out.writeParcelable(industry_obj, flag);
		out.writeParcelable(zip_code_obj, flag);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(login_log_obj, flag);
		out.writeParcelable(promotion_obj, flag);
	}

	public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() 
	{
		public Customer createFromParcel(Parcel in)
		{
			return new Customer(in);
		}

		public Customer[] newArray(int size)
		{
			return new Customer[size];
		}
	};

	private Customer(Parcel in)
	{
		customer_id = in.readInt();
		contact_id = in.readInt();
		status_id = in.readInt();
		search_location_type_id = in.readInt();
		search_zip_code = in.readString();
		search_industry_id = in.readInt();
		deal_range = in.readInt();
		accept_terms_date_utc_stamp = (Date) in.readSerializable();
		entry_date_utc_stamp = (Date) in.readSerializable();
		contact_obj = in.readParcelable(Contact.class.getClassLoader());
		customer_search_location_type_obj = in.readParcelable(Customer_Search_Location_Type.class.getClassLoader());
		customer_status_obj = in.readParcelable(Customer_Status.class.getClassLoader());
		industry_obj = in.readParcelable(Industry.class.getClassLoader());
		zip_code_obj = in.readParcelable(Zip_Code.class.getClassLoader());
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
		promotion_obj = in.readParcelable(Promotion.class.getClassLoader());
	}

	public int getCustomerId()
	{
		return this.customer_id;
	}
	public void setCustomerId(int customer_id)
	{
		this.customer_id = customer_id;
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

	public int getSearchLocationTypeId()
	{
		return this.search_location_type_id;
	}
	public void setSearchLocationTypeId(int search_location_type_id)
	{
		this.search_location_type_id = search_location_type_id;
	}

	public String getSearchZipCode()
	{
		return this.search_zip_code;
	}
	public void setSearchZipCode(String search_zip_code)
	{
		this.search_zip_code = search_zip_code;
	}

	public int getSearchIndustryId()
	{
		return this.search_industry_id;
	}
	public void setSearchIndustryId(int search_industry_id)
	{
		this.search_industry_id = search_industry_id;
	}

	public int getDealRange()
	{
		return this.deal_range;
	}
	public void setDealRange(int deal_range)
	{
		this.deal_range = deal_range;
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

	public Customer_Search_Location_Type getCustomerSearchLocationTypeObj()
	{
		return this.customer_search_location_type_obj;
	}
	public void setCustomerSearchLocationTypeObj(Customer_Search_Location_Type customer_search_location_type_obj)
	{
		this.customer_search_location_type_obj = customer_search_location_type_obj;
	}

	public Customer_Status getCustomerStatusObj()
	{
		return this.customer_status_obj;
	}
	public void setCustomerStatusObj(Customer_Status customer_status_obj)
	{
		this.customer_status_obj = customer_status_obj;
	}

	public Industry getIndustryObj()
	{
		return this.industry_obj;
	}
	public void setIndustryObj(Industry industry_obj)
	{
		this.industry_obj = industry_obj;
	}

	public Zip_Code getZipCodeObj()
	{
		return this.zip_code_obj;
	}
	public void setZipCodeObj(Zip_Code zip_code_obj)
	{
		this.zip_code_obj = zip_code_obj;
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

	public Promotion getPromotionObj()
	{
		return this.promotion_obj;
	}
	public void setPromotionObj(Promotion promotion_obj)
	{
		this.promotion_obj = promotion_obj;
	}
}
