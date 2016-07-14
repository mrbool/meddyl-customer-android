package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class System_Settings implements Parcelable
{
	private String auth_net_api_login_id;
	private String auth_net_transaction_key;
	private int certificate_cost_mode;
	private BigDecimal certificate_customer_amount;
	private int certificate_days_valid_default;
	private int certificate_delay_hours;
	private BigDecimal certificate_merchant_amount;
	private int certificate_merchant_percentage;
	private String certificate_quantity_info;
	private int certificate_quantity_min;
	private int certificate_quantity_max;
	private String customer_app_android_id;
	private String customer_app_ios_id;
	private String customer_app_terms;
	private int customer_deal_range_default;
	private int customer_deal_range_max;
	private int customer_deal_range_min;
	private int customer_description_characters;
	private String customer_description_default;
	private String deal_instructions_default;
	private int deal_min_ranking;
	private int deal_max_ranking;
	private boolean deal_needs_credit_card;
	private String deal_new_customer_only_info;
	private boolean deal_new_customer_only;
	private boolean deal_use_immediately;
	private String deal_use_immediately_info;
	private boolean deal_validate;
	private String dollar_value_info;
	private BigDecimal dollar_value_min;
	private BigDecimal dollar_value_max;
	private String email_admin;
	private boolean email_on;
	private String email_system;
	private boolean email_validation;
	private String expiration_days_info;
	private int expiration_days_max;
	private int expiration_days_min;
	private String fb_app_id;
	private String fb_app_secret;
	private String fb_scope;
	private String fb_redirect_url;
	private int fine_print_more_characters;
	private String fine_print_more_default;
	private String google_api_key;
	private String gps_accuracy;
	private int gps_timeout;
	private String image_url;
	private String image_folder;
	private String mailgun_domain;
	private String mailgun_api_private_key;
	private String mailgun_api_public_key;
	private String mailgun_url;
	private int merchant_active_deals_max;
	private String merchant_app_android_id;
	private String merchant_app_ios_id;
	private String merchant_app_terms;
	private boolean merchant_contact_approve;
	private boolean merchant_contact_validate;
	private int password_reset_days;
	private String pci_key;
	private int percent_off_default;
	private int percent_off_max;
	private int percent_off_min;
	private String plivo_auth_id;
	private String plivo_auth_token;
	private int promotion_referral_days;
	private String smtp_from_email;
	private boolean sms_on;
	private String sms_system;
	private int smtp_port;
	private String smtp_server;
	private String twilio_account_sid;
	private String twilio_auth_token;
	private String twilio_test_account_sid;
	private String twilio_test_auth_token;
	private String report;
	private int quantity;
	private Login_Log login_log_obj;

	public System_Settings()
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
		out.writeString(auth_net_api_login_id);
		out.writeString(auth_net_transaction_key);
		out.writeInt(certificate_cost_mode);
		out.writeSerializable(certificate_customer_amount);
		out.writeInt(certificate_days_valid_default);
		out.writeInt(certificate_delay_hours);
		out.writeSerializable(certificate_merchant_amount);
		out.writeInt(certificate_merchant_percentage);
		out.writeString(certificate_quantity_info);
		out.writeInt(certificate_quantity_min);
		out.writeInt(certificate_quantity_max);
		out.writeString(customer_app_android_id);
		out.writeString(customer_app_ios_id);
		out.writeString(customer_app_terms);
		out.writeInt(customer_deal_range_default);
		out.writeInt(customer_deal_range_max);
		out.writeInt(customer_deal_range_min);
		out.writeInt(customer_description_characters);
		out.writeString(customer_description_default);
		out.writeString(deal_instructions_default);
		out.writeInt(deal_min_ranking);
		out.writeInt(deal_max_ranking);
		out.writeByte((byte) (deal_needs_credit_card ? 1:0));
		out.writeString(deal_new_customer_only_info);
		out.writeByte((byte) (deal_new_customer_only ? 1:0));
		out.writeByte((byte) (deal_use_immediately ? 1:0));
		out.writeString(deal_use_immediately_info);
		out.writeByte((byte) (deal_validate ? 1:0));
		out.writeString(dollar_value_info);
		out.writeSerializable(dollar_value_min);
		out.writeSerializable(dollar_value_max);
		out.writeString(email_admin);
		out.writeByte((byte) (email_on ? 1:0));
		out.writeString(email_system);
		out.writeByte((byte) (email_validation ? 1:0));
		out.writeString(expiration_days_info);
		out.writeInt(expiration_days_max);
		out.writeInt(expiration_days_min);
		out.writeString(fb_app_id);
		out.writeString(fb_app_secret);
		out.writeString(fb_scope);
		out.writeString(fb_redirect_url);
		out.writeInt(fine_print_more_characters);
		out.writeString(fine_print_more_default);
		out.writeString(google_api_key);
		out.writeString(gps_accuracy);
		out.writeInt(gps_timeout);
		out.writeString(image_url);
		out.writeString(image_folder);
		out.writeString(mailgun_domain);
		out.writeString(mailgun_api_private_key);
		out.writeString(mailgun_api_public_key);
		out.writeString(mailgun_url);
		out.writeInt(merchant_active_deals_max);
		out.writeString(merchant_app_android_id);
		out.writeString(merchant_app_ios_id);
		out.writeString(merchant_app_terms);
		out.writeByte((byte) (merchant_contact_approve ? 1:0));
		out.writeByte((byte) (merchant_contact_validate ? 1:0));
		out.writeInt(password_reset_days);
		out.writeString(pci_key);
		out.writeInt(percent_off_default);
		out.writeInt(percent_off_max);
		out.writeInt(percent_off_min);
		out.writeString(plivo_auth_id);
		out.writeString(plivo_auth_token);
		out.writeInt(promotion_referral_days);
		out.writeString(smtp_from_email);
		out.writeByte((byte) (sms_on ? 1:0));
		out.writeString(sms_system);
		out.writeInt(smtp_port);
		out.writeString(smtp_server);
		out.writeString(twilio_account_sid);
		out.writeString(twilio_auth_token);
		out.writeString(twilio_test_account_sid);
		out.writeString(twilio_test_auth_token);
		out.writeString(report);
		out.writeInt(quantity);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Parcelable.Creator<System_Settings> CREATOR = new Parcelable.Creator<System_Settings>() 
	{
		public System_Settings createFromParcel(Parcel in)
		{
			return new System_Settings(in);
		}

		public System_Settings[] newArray(int size)
		{
			return new System_Settings[size];
		}
	};

	private System_Settings(Parcel in)
	{
		auth_net_api_login_id = in.readString();
		auth_net_transaction_key = in.readString();
		certificate_cost_mode = in.readInt();
		certificate_customer_amount = (BigDecimal) in.readSerializable();
		certificate_days_valid_default = in.readInt();
		certificate_delay_hours = in.readInt();
		certificate_merchant_amount = (BigDecimal) in.readSerializable();
		certificate_merchant_percentage = in.readInt();
		certificate_quantity_info = in.readString();
		certificate_quantity_min = in.readInt();
		certificate_quantity_max = in.readInt();
		customer_app_android_id = in.readString();
		customer_app_ios_id = in.readString();
		customer_app_terms = in.readString();
		customer_deal_range_default = in.readInt();
		customer_deal_range_max = in.readInt();
		customer_deal_range_min = in.readInt();
		customer_description_characters = in.readInt();
		customer_description_default = in.readString();
		deal_instructions_default = in.readString();
		deal_min_ranking = in.readInt();
		deal_max_ranking = in.readInt();
		deal_needs_credit_card = in.readByte() != 0;
		deal_new_customer_only_info = in.readString();
		deal_new_customer_only = in.readByte() != 0;
		deal_use_immediately = in.readByte() != 0;
		deal_use_immediately_info = in.readString();
		deal_validate = in.readByte() != 0;
		dollar_value_info = in.readString();
		dollar_value_min = (BigDecimal) in.readSerializable();
		dollar_value_max = (BigDecimal) in.readSerializable();
		email_admin = in.readString();
		email_on = in.readByte() != 0;
		email_system = in.readString();
		email_validation = in.readByte() != 0;
		expiration_days_info = in.readString();
		expiration_days_max = in.readInt();
		expiration_days_min = in.readInt();
		fb_app_id = in.readString();
		fb_app_secret = in.readString();
		fb_scope = in.readString();
		fb_redirect_url = in.readString();
		fine_print_more_characters = in.readInt();
		fine_print_more_default = in.readString();
		google_api_key = in.readString();
		gps_accuracy = in.readString();
		gps_timeout = in.readInt();
		image_url = in.readString();
		image_folder = in.readString();
		mailgun_domain = in.readString();
		mailgun_api_private_key = in.readString();
		mailgun_api_public_key = in.readString();
		mailgun_url = in.readString();
		merchant_active_deals_max = in.readInt();
		merchant_app_android_id = in.readString();
		merchant_app_ios_id = in.readString();
		merchant_app_terms = in.readString();
		merchant_contact_approve = in.readByte() != 0;
		merchant_contact_validate = in.readByte() != 0;
		password_reset_days = in.readInt();
		pci_key = in.readString();
		percent_off_default = in.readInt();
		percent_off_max = in.readInt();
		percent_off_min = in.readInt();
		plivo_auth_id = in.readString();
		plivo_auth_token = in.readString();
		promotion_referral_days = in.readInt();
		smtp_from_email = in.readString();
		sms_on = in.readByte() != 0;
		sms_system = in.readString();
		smtp_port = in.readInt();
		smtp_server = in.readString();
		twilio_account_sid = in.readString();
		twilio_auth_token = in.readString();
		twilio_test_account_sid = in.readString();
		twilio_test_auth_token = in.readString();
		report = in.readString();
		quantity = in.readInt();
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
	}

	public String getAuthNetApiLoginId()
	{
		return this.auth_net_api_login_id;
	}
	public void setAuthNetApiLoginId(String auth_net_api_login_id)
	{
		this.auth_net_api_login_id = auth_net_api_login_id;
	}

	public String getAuthNetTransactionKey()
	{
		return this.auth_net_transaction_key;
	}
	public void setAuthNetTransactionKey(String auth_net_transaction_key)
	{
		this.auth_net_transaction_key = auth_net_transaction_key;
	}

	public int getCertificateCostMode()
	{
		return this.certificate_cost_mode;
	}
	public void setCertificateCostMode(int certificate_cost_mode)
	{
		this.certificate_cost_mode = certificate_cost_mode;
	}

	public BigDecimal getCertificateCustomerAmount()
	{
		return this.certificate_customer_amount;
	}
	public void setCertificateCustomerAmount(BigDecimal certificate_customer_amount)
	{
		this.certificate_customer_amount = certificate_customer_amount;
	}

	public int getCertificateDaysValidDefault()
	{
		return this.certificate_days_valid_default;
	}
	public void setCertificateDaysValidDefault(int certificate_days_valid_default)
	{
		this.certificate_days_valid_default = certificate_days_valid_default;
	}

	public int getCertificateDelayHours()
	{
		return this.certificate_delay_hours;
	}
	public void setCertificateDelayHours(int certificate_delay_hours)
	{
		this.certificate_delay_hours = certificate_delay_hours;
	}

	public BigDecimal getCertificateMerchantAmount()
	{
		return this.certificate_merchant_amount;
	}
	public void setCertificateMerchantAmount(BigDecimal certificate_merchant_amount)
	{
		this.certificate_merchant_amount = certificate_merchant_amount;
	}

	public int getCertificateMerchantPercentage()
	{
		return this.certificate_merchant_percentage;
	}
	public void setCertificateMerchantPercentage(int certificate_merchant_percentage)
	{
		this.certificate_merchant_percentage = certificate_merchant_percentage;
	}

	public String getCertificateQuantityInfo()
	{
		return this.certificate_quantity_info;
	}
	public void setCertificateQuantityInfo(String certificate_quantity_info)
	{
		this.certificate_quantity_info = certificate_quantity_info;
	}

	public int getCertificateQuantityMin()
	{
		return this.certificate_quantity_min;
	}
	public void setCertificateQuantityMin(int certificate_quantity_min)
	{
		this.certificate_quantity_min = certificate_quantity_min;
	}

	public int getCertificateQuantityMax()
	{
		return this.certificate_quantity_max;
	}
	public void setCertificateQuantityMax(int certificate_quantity_max)
	{
		this.certificate_quantity_max = certificate_quantity_max;
	}

	public String getCustomerAppAndroidId()
	{
		return this.customer_app_android_id;
	}
	public void setCustomerAppAndroidId(String customer_app_android_id)
	{
		this.customer_app_android_id = customer_app_android_id;
	}

	public String getCustomerAppIosId()
	{
		return this.customer_app_ios_id;
	}
	public void setCustomerAppIosId(String customer_app_ios_id)
	{
		this.customer_app_ios_id = customer_app_ios_id;
	}

	public String getCustomerAppTerms()
	{
		return this.customer_app_terms;
	}
	public void setCustomerAppTerms(String customer_app_terms)
	{
		this.customer_app_terms = customer_app_terms;
	}

	public int getCustomerDealRangeDefault()
	{
		return this.customer_deal_range_default;
	}
	public void setCustomerDealRangeDefault(int customer_deal_range_default)
	{
		this.customer_deal_range_default = customer_deal_range_default;
	}

	public int getCustomerDealRangeMax()
	{
		return this.customer_deal_range_max;
	}
	public void setCustomerDealRangeMax(int customer_deal_range_max)
	{
		this.customer_deal_range_max = customer_deal_range_max;
	}

	public int getCustomerDealRangeMin()
	{
		return this.customer_deal_range_min;
	}
	public void setCustomerDealRangeMin(int customer_deal_range_min)
	{
		this.customer_deal_range_min = customer_deal_range_min;
	}

	public int getCustomerDescriptionCharacters()
	{
		return this.customer_description_characters;
	}
	public void setCustomerDescriptionCharacters(int customer_description_characters)
	{
		this.customer_description_characters = customer_description_characters;
	}

	public String getCustomerDescriptionDefault()
	{
		return this.customer_description_default;
	}
	public void setCustomerDescriptionDefault(String customer_description_default)
	{
		this.customer_description_default = customer_description_default;
	}

	public String getDealInstructionsDefault()
	{
		return this.deal_instructions_default;
	}
	public void setDealInstructionsDefault(String deal_instructions_default)
	{
		this.deal_instructions_default = deal_instructions_default;
	}

	public int getDealMinRanking()
	{
		return this.deal_min_ranking;
	}
	public void setDealMinRanking(int deal_min_ranking)
	{
		this.deal_min_ranking = deal_min_ranking;
	}

	public int getDealMaxRanking()
	{
		return this.deal_max_ranking;
	}
	public void setDealMaxRanking(int deal_max_ranking)
	{
		this.deal_max_ranking = deal_max_ranking;
	}

	public boolean getDealNeedsCreditCard()
	{
		return this.deal_needs_credit_card;
	}
	public void setDealNeedsCreditCard(boolean deal_needs_credit_card)
	{
		this.deal_needs_credit_card = deal_needs_credit_card;
	}

	public String getDealNewCustomerOnlyInfo()
	{
		return this.deal_new_customer_only_info;
	}
	public void setDealNewCustomerOnlyInfo(String deal_new_customer_only_info)
	{
		this.deal_new_customer_only_info = deal_new_customer_only_info;
	}

	public boolean getDealNewCustomerOnly()
	{
		return this.deal_new_customer_only;
	}
	public void setDealNewCustomerOnly(boolean deal_new_customer_only)
	{
		this.deal_new_customer_only = deal_new_customer_only;
	}

	public boolean getDealUseImmediately()
	{
		return this.deal_use_immediately;
	}
	public void setDealUseImmediately(boolean deal_use_immediately)
	{
		this.deal_use_immediately = deal_use_immediately;
	}

	public String getDealUseImmediatelyInfo()
	{
		return this.deal_use_immediately_info;
	}
	public void setDealUseImmediatelyInfo(String deal_use_immediately_info)
	{
		this.deal_use_immediately_info = deal_use_immediately_info;
	}

	public boolean getDealValidate()
	{
		return this.deal_validate;
	}
	public void setDealValidate(boolean deal_validate)
	{
		this.deal_validate = deal_validate;
	}

	public String getDollarValueInfo()
	{
		return this.dollar_value_info;
	}
	public void setDollarValueInfo(String dollar_value_info)
	{
		this.dollar_value_info = dollar_value_info;
	}

	public BigDecimal getDollarValueMin()
	{
		return this.dollar_value_min;
	}
	public void setDollarValueMin(BigDecimal dollar_value_min)
	{
		this.dollar_value_min = dollar_value_min;
	}

	public BigDecimal getDollarValueMax()
	{
		return this.dollar_value_max;
	}
	public void setDollarValueMax(BigDecimal dollar_value_max)
	{
		this.dollar_value_max = dollar_value_max;
	}

	public String getEmailAdmin()
	{
		return this.email_admin;
	}
	public void setEmailAdmin(String email_admin)
	{
		this.email_admin = email_admin;
	}

	public boolean getEmailOn()
	{
		return this.email_on;
	}
	public void setEmailOn(boolean email_on)
	{
		this.email_on = email_on;
	}

	public String getEmailSystem()
	{
		return this.email_system;
	}
	public void setEmailSystem(String email_system)
	{
		this.email_system = email_system;
	}

	public boolean getEmailValidation()
	{
		return this.email_validation;
	}
	public void setEmailValidation(boolean email_validation)
	{
		this.email_validation = email_validation;
	}

	public String getExpirationDaysInfo()
	{
		return this.expiration_days_info;
	}
	public void setExpirationDaysInfo(String expiration_days_info)
	{
		this.expiration_days_info = expiration_days_info;
	}

	public int getExpirationDaysMax()
	{
		return this.expiration_days_max;
	}
	public void setExpirationDaysMax(int expiration_days_max)
	{
		this.expiration_days_max = expiration_days_max;
	}

	public int getExpirationDaysMin()
	{
		return this.expiration_days_min;
	}
	public void setExpirationDaysMin(int expiration_days_min)
	{
		this.expiration_days_min = expiration_days_min;
	}

	public String getFbAppId()
	{
		return this.fb_app_id;
	}
	public void setFbAppId(String fb_app_id)
	{
		this.fb_app_id = fb_app_id;
	}

	public String getFbAppSecret()
	{
		return this.fb_app_secret;
	}
	public void setFbAppSecret(String fb_app_secret)
	{
		this.fb_app_secret = fb_app_secret;
	}

	public String getFbScope()
	{
		return this.fb_scope;
	}
	public void setFbScope(String fb_scope)
	{
		this.fb_scope = fb_scope;
	}

	public String getFbRedirectUrl()
	{
		return this.fb_redirect_url;
	}
	public void setFbRedirectUrl(String fb_redirect_url)
	{
		this.fb_redirect_url = fb_redirect_url;
	}

	public int getFinePrintMoreCharacters()
	{
		return this.fine_print_more_characters;
	}
	public void setFinePrintMoreCharacters(int fine_print_more_characters)
	{
		this.fine_print_more_characters = fine_print_more_characters;
	}

	public String getFinePrintMoreDefault()
	{
		return this.fine_print_more_default;
	}
	public void setFinePrintMoreDefault(String fine_print_more_default)
	{
		this.fine_print_more_default = fine_print_more_default;
	}

	public String getGoogleApiKey()
	{
		return this.google_api_key;
	}
	public void setGoogleApiKey(String google_api_key)
	{
		this.google_api_key = google_api_key;
	}

	public String getGpsAccuracy()
	{
		return this.gps_accuracy;
	}
	public void setGpsAccuracy(String gps_accuracy)
	{
		this.gps_accuracy = gps_accuracy;
	}

	public int getGpsTimeout()
	{
		return this.gps_timeout;
	}
	public void setGpsTimeout(int gps_timeout)
	{
		this.gps_timeout = gps_timeout;
	}

	public String getImageUrl()
	{
		return this.image_url;
	}
	public void setImageUrl(String image_url)
	{
		this.image_url = image_url;
	}

	public String getImageFolder()
	{
		return this.image_folder;
	}
	public void setImageFolder(String image_folder)
	{
		this.image_folder = image_folder;
	}

	public String getMailgunDomain()
	{
		return this.mailgun_domain;
	}
	public void setMailgunDomain(String mailgun_domain)
	{
		this.mailgun_domain = mailgun_domain;
	}

	public String getMailgunApiPrivateKey()
	{
		return this.mailgun_api_private_key;
	}
	public void setMailgunApiPrivateKey(String mailgun_api_private_key)
	{
		this.mailgun_api_private_key = mailgun_api_private_key;
	}

	public String getMailgunApiPublicKey()
	{
		return this.mailgun_api_public_key;
	}
	public void setMailgunApiPublicKey(String mailgun_api_public_key)
	{
		this.mailgun_api_public_key = mailgun_api_public_key;
	}

	public String getMailgunUrl()
	{
		return this.mailgun_url;
	}
	public void setMailgunUrl(String mailgun_url)
	{
		this.mailgun_url = mailgun_url;
	}

	public int getMerchantActiveDealsMax()
	{
		return this.merchant_active_deals_max;
	}
	public void setMerchantActiveDealsMax(int merchant_active_deals_max)
	{
		this.merchant_active_deals_max = merchant_active_deals_max;
	}

	public String getMerchantAppAndroidId()
	{
		return this.merchant_app_android_id;
	}
	public void setMerchantAppAndroidId(String merchant_app_android_id)
	{
		this.merchant_app_android_id = merchant_app_android_id;
	}

	public String getMerchantAppIosId()
	{
		return this.merchant_app_ios_id;
	}
	public void setMerchantAppIosId(String merchant_app_ios_id)
	{
		this.merchant_app_ios_id = merchant_app_ios_id;
	}

	public String getMerchantAppTerms()
	{
		return this.merchant_app_terms;
	}
	public void setMerchantAppTerms(String merchant_app_terms)
	{
		this.merchant_app_terms = merchant_app_terms;
	}

	public boolean getMerchantContactApprove()
	{
		return this.merchant_contact_approve;
	}
	public void setMerchantContactApprove(boolean merchant_contact_approve)
	{
		this.merchant_contact_approve = merchant_contact_approve;
	}

	public boolean getMerchantContactValidate()
	{
		return this.merchant_contact_validate;
	}
	public void setMerchantContactValidate(boolean merchant_contact_validate)
	{
		this.merchant_contact_validate = merchant_contact_validate;
	}

	public int getPasswordResetDays()
	{
		return this.password_reset_days;
	}
	public void setPasswordResetDays(int password_reset_days)
	{
		this.password_reset_days = password_reset_days;
	}

	public String getPciKey()
	{
		return this.pci_key;
	}
	public void setPciKey(String pci_key)
	{
		this.pci_key = pci_key;
	}

	public int getPercentOffDefault()
	{
		return this.percent_off_default;
	}
	public void setPercentOffDefault(int percent_off_default)
	{
		this.percent_off_default = percent_off_default;
	}

	public int getPercentOffMax()
	{
		return this.percent_off_max;
	}
	public void setPercentOffMax(int percent_off_max)
	{
		this.percent_off_max = percent_off_max;
	}

	public int getPercentOffMin()
	{
		return this.percent_off_min;
	}
	public void setPercentOffMin(int percent_off_min)
	{
		this.percent_off_min = percent_off_min;
	}

	public String getPlivoAuthId()
	{
		return this.plivo_auth_id;
	}
	public void setPlivoAuthId(String plivo_auth_id)
	{
		this.plivo_auth_id = plivo_auth_id;
	}

	public String getPlivoAuthToken()
	{
		return this.plivo_auth_token;
	}
	public void setPlivoAuthToken(String plivo_auth_token)
	{
		this.plivo_auth_token = plivo_auth_token;
	}

	public int getPromotionReferralDays()
	{
		return this.promotion_referral_days;
	}
	public void setPromotionReferralDays(int promotion_referral_days)
	{
		this.promotion_referral_days = promotion_referral_days;
	}

	public String getSmtpFromEmail()
	{
		return this.smtp_from_email;
	}
	public void setSmtpFromEmail(String smtp_from_email)
	{
		this.smtp_from_email = smtp_from_email;
	}

	public boolean getSmsOn()
	{
		return this.sms_on;
	}
	public void setSmsOn(boolean sms_on)
	{
		this.sms_on = sms_on;
	}

	public String getSmsSystem()
	{
		return this.sms_system;
	}
	public void setSmsSystem(String sms_system)
	{
		this.sms_system = sms_system;
	}

	public int getSmtpPort()
	{
		return this.smtp_port;
	}
	public void setSmtpPort(int smtp_port)
	{
		this.smtp_port = smtp_port;
	}

	public String getSmtpServer()
	{
		return this.smtp_server;
	}
	public void setSmtpServer(String smtp_server)
	{
		this.smtp_server = smtp_server;
	}

	public String getTwilioAccountSid()
	{
		return this.twilio_account_sid;
	}
	public void setTwilioAccountSid(String twilio_account_sid)
	{
		this.twilio_account_sid = twilio_account_sid;
	}

	public String getTwilioAuthToken()
	{
		return this.twilio_auth_token;
	}
	public void setTwilioAuthToken(String twilio_auth_token)
	{
		this.twilio_auth_token = twilio_auth_token;
	}

	public String getTwilioTestAccountSid()
	{
		return this.twilio_test_account_sid;
	}
	public void setTwilioTestAccountSid(String twilio_test_account_sid)
	{
		this.twilio_test_account_sid = twilio_test_account_sid;
	}

	public String getTwilioTestAuthToken()
	{
		return this.twilio_test_auth_token;
	}
	public void setTwilioTestAuthToken(String twilio_test_auth_token)
	{
		this.twilio_test_auth_token = twilio_test_auth_token;
	}

	public String getReport()
	{
		return this.report;
	}
	public void setReport(String report)
	{
		this.report = report;
	}

	public int getQuantity()
	{
		return this.quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
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
