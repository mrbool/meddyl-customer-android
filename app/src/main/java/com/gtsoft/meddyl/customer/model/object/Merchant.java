package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Merchant implements Parcelable
{
	private int merchant_id;
	private int industry_id;
	private int status_id;
	private String zip_code;
	private int neighborhood_id;
	private int rating_id;
	private String company_name;
	private String address_1;
	private String address_2;
	private double latitude;
	private double longitude;
	private String phone;
	private String website;
	private String description;
	private String image;
	private int max_active_deals;
	private String yelp_business_id;
	private Date entry_date_utc_stamp;
	private Industry industry_obj;
	private Merchant_Rating merchant_rating_obj;
	private Merchant_Status merchant_status_obj;
	private Neighborhood neighborhood_obj;
	private Zip_Code zip_code_obj;
	private String image_base64;
	private Industry top_industry_obj;

	public Merchant()
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
		out.writeInt(merchant_id);
		out.writeInt(industry_id);
		out.writeInt(status_id);
		out.writeString(zip_code);
		out.writeInt(neighborhood_id);
		out.writeInt(rating_id);
		out.writeString(company_name);
		out.writeString(address_1);
		out.writeString(address_2);
		out.writeDouble(latitude);
		out.writeDouble(longitude);
		out.writeString(phone);
		out.writeString(website);
		out.writeString(description);
		out.writeString(image);
		out.writeInt(max_active_deals);
		out.writeString(yelp_business_id);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(industry_obj, flag);
		out.writeParcelable(merchant_rating_obj, flag);
		out.writeParcelable(merchant_status_obj, flag);
		out.writeParcelable(neighborhood_obj, flag);
		out.writeParcelable(zip_code_obj, flag);
		out.writeString(image_base64);
		out.writeParcelable(top_industry_obj, flag);
	}

	public static final Parcelable.Creator<Merchant> CREATOR = new Parcelable.Creator<Merchant>() 
	{
		public Merchant createFromParcel(Parcel in)
		{
			return new Merchant(in);
		}

		public Merchant[] newArray(int size)
		{
			return new Merchant[size];
		}
	};

	private Merchant(Parcel in)
	{
		merchant_id = in.readInt();
		industry_id = in.readInt();
		status_id = in.readInt();
		zip_code = in.readString();
		neighborhood_id = in.readInt();
		rating_id = in.readInt();
		company_name = in.readString();
		address_1 = in.readString();
		address_2 = in.readString();
		latitude = in.readDouble();
		longitude = in.readDouble();
		phone = in.readString();
		website = in.readString();
		description = in.readString();
		image = in.readString();
		max_active_deals = in.readInt();
		yelp_business_id = in.readString();
		entry_date_utc_stamp = (Date) in.readSerializable();
		industry_obj = in.readParcelable(Industry.class.getClassLoader());
		merchant_rating_obj = in.readParcelable(Merchant_Rating.class.getClassLoader());
		merchant_status_obj = in.readParcelable(Merchant_Status.class.getClassLoader());
		neighborhood_obj = in.readParcelable(Neighborhood.class.getClassLoader());
		zip_code_obj = in.readParcelable(Zip_Code.class.getClassLoader());
		image_base64 = in.readString();
		top_industry_obj = in.readParcelable(Industry.class.getClassLoader());
	}

	public int getMerchantId()
	{
		return this.merchant_id;
	}
	public void setMerchantId(int merchant_id)
	{
		this.merchant_id = merchant_id;
	}

	public int getIndustryId()
	{
		return this.industry_id;
	}
	public void setIndustryId(int industry_id)
	{
		this.industry_id = industry_id;
	}

	public int getStatusId()
	{
		return this.status_id;
	}
	public void setStatusId(int status_id)
	{
		this.status_id = status_id;
	}

	public String getZipCode()
	{
		return this.zip_code;
	}
	public void setZipCode(String zip_code)
	{
		this.zip_code = zip_code;
	}

	public int getNeighborhoodId()
	{
		return this.neighborhood_id;
	}
	public void setNeighborhoodId(int neighborhood_id)
	{
		this.neighborhood_id = neighborhood_id;
	}

	public int getRatingId()
	{
		return this.rating_id;
	}
	public void setRatingId(int rating_id)
	{
		this.rating_id = rating_id;
	}

	public String getCompanyName()
	{
		return this.company_name;
	}
	public void setCompanyName(String company_name)
	{
		this.company_name = company_name;
	}

	public String getAddress1()
	{
		return this.address_1;
	}
	public void setAddress1(String address_1)
	{
		this.address_1 = address_1;
	}

	public String getAddress2()
	{
		return this.address_2;
	}
	public void setAddress2(String address_2)
	{
		this.address_2 = address_2;
	}

	public double getLatitude()
	{
		return this.latitude;
	}
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return this.longitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public String getPhone()
	{
		return this.phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getWebsite()
	{
		return this.website;
	}
	public void setWebsite(String website)
	{
		this.website = website;
	}

	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}

	public int getMaxActiveDeals()
	{
		return this.max_active_deals;
	}
	public void setMaxActiveDeals(int max_active_deals)
	{
		this.max_active_deals = max_active_deals;
	}

	public String getYelpBusinessId()
	{
		return this.yelp_business_id;
	}
	public void setYelpBusinessId(String yelp_business_id)
	{
		this.yelp_business_id = yelp_business_id;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Industry getIndustryObj()
	{
		return this.industry_obj;
	}
	public void setIndustryObj(Industry industry_obj)
	{
		this.industry_obj = industry_obj;
	}

	public Merchant_Rating getMerchantRatingObj()
	{
		return this.merchant_rating_obj;
	}
	public void setMerchantRatingObj(Merchant_Rating merchant_rating_obj)
	{
		this.merchant_rating_obj = merchant_rating_obj;
	}

	public Merchant_Status getMerchantStatusObj()
	{
		return this.merchant_status_obj;
	}
	public void setMerchantStatusObj(Merchant_Status merchant_status_obj)
	{
		this.merchant_status_obj = merchant_status_obj;
	}

	public Neighborhood getNeighborhoodObj()
	{
		return this.neighborhood_obj;
	}
	public void setNeighborhoodObj(Neighborhood neighborhood_obj)
	{
		this.neighborhood_obj = neighborhood_obj;
	}

	public Zip_Code getZipCodeObj()
	{
		return this.zip_code_obj;
	}
	public void setZipCodeObj(Zip_Code zip_code_obj)
	{
		this.zip_code_obj = zip_code_obj;
	}

	public String getImageBase64()
	{
		return this.image_base64;
	}
	public void setImageBase64(String image_base64)
	{
		this.image_base64 = image_base64;
	}

	public Industry getTopIndustryObj()
	{
		return this.top_industry_obj;
	}
	public void setTopIndustryObj(Industry top_industry_obj)
	{
		this.top_industry_obj = top_industry_obj;
	}
}
