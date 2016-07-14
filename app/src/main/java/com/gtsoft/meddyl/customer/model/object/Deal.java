package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Deal implements Parcelable
{
	private int deal_id;
	private int merchant_contact_id;
	private int status_id;
	private int promotion_activity_id;
	private int validation_id;
	private int time_zone_id;
	private String deal;
	private String fine_print;
	private String fine_print_ext;
	private int percent_off;
	private BigDecimal max_dollar_amount;
	private int certificate_quantity;
	private Date expiration_date;
	private String image;
	private BigDecimal deal_amount;
	private BigDecimal certificate_amount;
	private int certificate_days_valid;
	private int certificate_delay_hours;
	private boolean use_deal_immediately;
	private boolean is_valid_new_customer_only;
	private String instructions;
	private int ranking;
	private Date entry_date_utc_stamp;
	private Deal_Status deal_status_obj;
	private Deal_Validation deal_validation_obj;
	private Merchant_Contact merchant_contact_obj;
	private Promotion_Activity promotion_activity_obj;
	private Time_Zone time_zone_obj;
	private String derived_text_1;
	private String derived_text_2;
	private BigDecimal distance;
	private int certificates_remaining;
	private int certificates_sold;
	private int certificates_redeemed;
	private int certificates_unused;
	private int certificates_expired;
	private Date last_redeemed_date;
	private Date last_assigned_date;
	private String image_base64;
	private String search;
	private Fine_Print_Option[] fine_print_option_obj_array;
	private Application_Type application_type_obj;
	private Login_Log login_log_obj;

	public Deal()
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
		out.writeInt(deal_id);
		out.writeInt(merchant_contact_id);
		out.writeInt(status_id);
		out.writeInt(promotion_activity_id);
		out.writeInt(validation_id);
		out.writeInt(time_zone_id);
		out.writeString(deal);
		out.writeString(fine_print);
		out.writeString(fine_print_ext);
		out.writeInt(percent_off);
		out.writeSerializable(max_dollar_amount);
		out.writeInt(certificate_quantity);
		out.writeSerializable(expiration_date);
		out.writeString(image);
		out.writeSerializable(deal_amount);
		out.writeSerializable(certificate_amount);
		out.writeInt(certificate_days_valid);
		out.writeInt(certificate_delay_hours);
		out.writeByte((byte) (use_deal_immediately ? 1:0));
		out.writeByte((byte) (is_valid_new_customer_only ? 1:0));
		out.writeString(instructions);
		out.writeInt(ranking);
		out.writeSerializable(entry_date_utc_stamp);
		out.writeParcelable(deal_status_obj, flag);
		out.writeParcelable(deal_validation_obj, flag);
		out.writeParcelable(merchant_contact_obj, flag);
		out.writeParcelable(promotion_activity_obj, flag);
		out.writeParcelable(time_zone_obj, flag);
		out.writeString(derived_text_1);
		out.writeString(derived_text_2);
		out.writeSerializable(distance);
		out.writeInt(certificates_remaining);
		out.writeInt(certificates_sold);
		out.writeInt(certificates_redeemed);
		out.writeInt(certificates_unused);
		out.writeInt(certificates_expired);
		out.writeSerializable(last_redeemed_date);
		out.writeSerializable(last_assigned_date);
		out.writeString(image_base64);
		out.writeString(search);
		out.writeTypedArray(fine_print_option_obj_array, flag);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Parcelable.Creator<Deal> CREATOR = new Parcelable.Creator<Deal>() 
	{
		public Deal createFromParcel(Parcel in)
		{
			return new Deal(in);
		}

		public Deal[] newArray(int size)
		{
			return new Deal[size];
		}
	};

	private Deal(Parcel in)
	{
		deal_id = in.readInt();
		merchant_contact_id = in.readInt();
		status_id = in.readInt();
		promotion_activity_id = in.readInt();
		validation_id = in.readInt();
		time_zone_id = in.readInt();
		deal = in.readString();
		fine_print = in.readString();
		fine_print_ext = in.readString();
		percent_off = in.readInt();
		max_dollar_amount = (BigDecimal) in.readSerializable();
		certificate_quantity = in.readInt();
		expiration_date = (Date) in.readSerializable();
		image = in.readString();
		deal_amount = (BigDecimal) in.readSerializable();
		certificate_amount = (BigDecimal) in.readSerializable();
		certificate_days_valid = in.readInt();
		certificate_delay_hours = in.readInt();
		use_deal_immediately = in.readByte() != 0;
		is_valid_new_customer_only = in.readByte() != 0;
		instructions = in.readString();
		ranking = in.readInt();
		entry_date_utc_stamp = (Date) in.readSerializable();
		deal_status_obj = in.readParcelable(Deal_Status.class.getClassLoader());
		deal_validation_obj = in.readParcelable(Deal_Validation.class.getClassLoader());
		merchant_contact_obj = in.readParcelable(Merchant_Contact.class.getClassLoader());
		promotion_activity_obj = in.readParcelable(Promotion_Activity.class.getClassLoader());
		time_zone_obj = in.readParcelable(Time_Zone.class.getClassLoader());
		derived_text_1 = in.readString();
		derived_text_2 = in.readString();
		distance = (BigDecimal) in.readSerializable();
		certificates_remaining = in.readInt();
		certificates_sold = in.readInt();
		certificates_redeemed = in.readInt();
		certificates_unused = in.readInt();
		certificates_expired = in.readInt();
		last_redeemed_date = (Date) in.readSerializable();
		last_assigned_date = (Date) in.readSerializable();
		image_base64 = in.readString();
		search = in.readString();
		fine_print_option_obj_array = in.createTypedArray(Fine_Print_Option.CREATOR);
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
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

	public int getPromotionActivityId()
	{
		return this.promotion_activity_id;
	}
	public void setPromotionActivityId(int promotion_activity_id)
	{
		this.promotion_activity_id = promotion_activity_id;
	}

	public int getValidationId()
	{
		return this.validation_id;
	}
	public void setValidationId(int validation_id)
	{
		this.validation_id = validation_id;
	}

	public int getTimeZoneId()
	{
		return this.time_zone_id;
	}
	public void setTimeZoneId(int time_zone_id)
	{
		this.time_zone_id = time_zone_id;
	}

	public String getDeal()
	{
		return this.deal;
	}
	public void setDeal(String deal)
	{
		this.deal = deal;
	}

	public String getFinePrint()
	{
		return this.fine_print;
	}
	public void setFinePrint(String fine_print)
	{
		this.fine_print = fine_print;
	}

	public String getFinePrintExt()
	{
		return this.fine_print_ext;
	}
	public void setFinePrintExt(String fine_print_ext)
	{
		this.fine_print_ext = fine_print_ext;
	}

	public int getPercentOff()
	{
		return this.percent_off;
	}
	public void setPercentOff(int percent_off)
	{
		this.percent_off = percent_off;
	}

	public BigDecimal getMaxDollarAmount()
	{
		return this.max_dollar_amount;
	}
	public void setMaxDollarAmount(BigDecimal max_dollar_amount)
	{
		this.max_dollar_amount = max_dollar_amount;
	}

	public int getCertificateQuantity()
	{
		return this.certificate_quantity;
	}
	public void setCertificateQuantity(int certificate_quantity)
	{
		this.certificate_quantity = certificate_quantity;
	}

	public Date getExpirationDate()
	{
		return this.expiration_date;
	}
	public void setExpirationDate(Date expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public String getImage()
	{
		return this.image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}

	public BigDecimal getDealAmount()
	{
		return this.deal_amount;
	}
	public void setDealAmount(BigDecimal deal_amount)
	{
		this.deal_amount = deal_amount;
	}

	public BigDecimal getCertificateAmount()
	{
		return this.certificate_amount;
	}
	public void setCertificateAmount(BigDecimal certificate_amount)
	{
		this.certificate_amount = certificate_amount;
	}

	public int getCertificateDaysValid()
	{
		return this.certificate_days_valid;
	}
	public void setCertificateDaysValid(int certificate_days_valid)
	{
		this.certificate_days_valid = certificate_days_valid;
	}

	public int getCertificateDelayHours()
	{
		return this.certificate_delay_hours;
	}
	public void setCertificateDelayHours(int certificate_delay_hours)
	{
		this.certificate_delay_hours = certificate_delay_hours;
	}

	public boolean getUseDealImmediately()
	{
		return this.use_deal_immediately;
	}
	public void setUseDealImmediately(boolean use_deal_immediately)
	{
		this.use_deal_immediately = use_deal_immediately;
	}

	public boolean getIsValidNewCustomerOnly()
	{
		return this.is_valid_new_customer_only;
	}
	public void setIsValidNewCustomerOnly(boolean is_valid_new_customer_only)
	{
		this.is_valid_new_customer_only = is_valid_new_customer_only;
	}

	public String getInstructions()
	{
		return this.instructions;
	}
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}

	public int getRanking()
	{
		return this.ranking;
	}
	public void setRanking(int ranking)
	{
		this.ranking = ranking;
	}

	public Date getEntryDateUtcStamp()
	{
		return this.entry_date_utc_stamp;
	}
	public void setEntryDateUtcStamp(Date entry_date_utc_stamp)
	{
		this.entry_date_utc_stamp = entry_date_utc_stamp;
	}

	public Deal_Status getDealStatusObj()
	{
		return this.deal_status_obj;
	}
	public void setDealStatusObj(Deal_Status deal_status_obj)
	{
		this.deal_status_obj = deal_status_obj;
	}

	public Deal_Validation getDealValidationObj()
	{
		return this.deal_validation_obj;
	}
	public void setDealValidationObj(Deal_Validation deal_validation_obj)
	{
		this.deal_validation_obj = deal_validation_obj;
	}

	public Merchant_Contact getMerchantContactObj()
	{
		return this.merchant_contact_obj;
	}
	public void setMerchantContactObj(Merchant_Contact merchant_contact_obj)
	{
		this.merchant_contact_obj = merchant_contact_obj;
	}

	public Promotion_Activity getPromotionActivityObj()
	{
		return this.promotion_activity_obj;
	}
	public void setPromotionActivityObj(Promotion_Activity promotion_activity_obj)
	{
		this.promotion_activity_obj = promotion_activity_obj;
	}

	public Time_Zone getTimeZoneObj()
	{
		return this.time_zone_obj;
	}
	public void setTimeZoneObj(Time_Zone time_zone_obj)
	{
		this.time_zone_obj = time_zone_obj;
	}

	public String getDerivedText1()
	{
		return this.derived_text_1;
	}
	public void setDerivedText1(String derived_text_1)
	{
		this.derived_text_1 = derived_text_1;
	}

	public String getDerivedText2()
	{
		return this.derived_text_2;
	}
	public void setDerivedText2(String derived_text_2)
	{
		this.derived_text_2 = derived_text_2;
	}

	public BigDecimal getDistance()
	{
		return this.distance;
	}
	public void setDistance(BigDecimal distance)
	{
		this.distance = distance;
	}

	public int getCertificatesRemaining()
	{
		return this.certificates_remaining;
	}
	public void setCertificatesRemaining(int certificates_remaining)
	{
		this.certificates_remaining = certificates_remaining;
	}

	public int getCertificatesSold()
	{
		return this.certificates_sold;
	}
	public void setCertificatesSold(int certificates_sold)
	{
		this.certificates_sold = certificates_sold;
	}

	public int getCertificatesRedeemed()
	{
		return this.certificates_redeemed;
	}
	public void setCertificatesRedeemed(int certificates_redeemed)
	{
		this.certificates_redeemed = certificates_redeemed;
	}

	public int getCertificatesUnused()
	{
		return this.certificates_unused;
	}
	public void setCertificatesUnused(int certificates_unused)
	{
		this.certificates_unused = certificates_unused;
	}

	public int getCertificatesExpired()
	{
		return this.certificates_expired;
	}
	public void setCertificatesExpired(int certificates_expired)
	{
		this.certificates_expired = certificates_expired;
	}

	public Date getLastRedeemedDate()
	{
		return this.last_redeemed_date;
	}
	public void setLastRedeemedDate(Date last_redeemed_date)
	{
		this.last_redeemed_date = last_redeemed_date;
	}

	public Date getLastAssignedDate()
	{
		return this.last_assigned_date;
	}
	public void setLastAssignedDate(Date last_assigned_date)
	{
		this.last_assigned_date = last_assigned_date;
	}

	public String getImageBase64()
	{
		return this.image_base64;
	}
	public void setImageBase64(String image_base64)
	{
		this.image_base64 = image_base64;
	}

	public String getSearch()
	{
		return this.search;
	}
	public void setSearch(String search)
	{
		this.search = search;
	}

	public Fine_Print_Option[] getFinePrintOptionObjArray()
	{
		return this.fine_print_option_obj_array;
	}
	public void setFinePrintOptionObjArray(Fine_Print_Option[] fine_print_option_obj_array)
	{
		this.fine_print_option_obj_array = fine_print_option_obj_array;
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
