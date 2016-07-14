package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class Certificate_Payment implements Parcelable
{
	private int payment_id;
	private int certificate_id;
	private int credit_card_id;
	private int promotion_activity_id;
	private String card_holder_name;
	private String card_number;
	private Byte[] card_number_encrypted;
	private String card_expiration_date;
	private BigDecimal payment_amount;
	private Date payment_date_utc_stamp;
	private Certificate certificate_obj;
	private Credit_Card credit_card_obj;
	private Promotion_Activity promotion_activity_obj;
	private Application_Type application_type_obj;
	private Login_Log login_log_obj;

	public Certificate_Payment()
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
		out.writeInt(payment_id);
		out.writeInt(certificate_id);
		out.writeInt(credit_card_id);
		out.writeInt(promotion_activity_id);
		out.writeString(card_holder_name);
		out.writeString(card_number);
		out.writeSerializable(card_number_encrypted);
		out.writeString(card_expiration_date);
		out.writeSerializable(payment_amount);
		out.writeSerializable(payment_date_utc_stamp);
		out.writeParcelable(certificate_obj, flag);
		out.writeParcelable(credit_card_obj, flag);
		out.writeParcelable(promotion_activity_obj, flag);
		out.writeParcelable(application_type_obj, flag);
		out.writeParcelable(login_log_obj, flag);
	}

	public static final Parcelable.Creator<Certificate_Payment> CREATOR = new Parcelable.Creator<Certificate_Payment>() 
	{
		public Certificate_Payment createFromParcel(Parcel in)
		{
			return new Certificate_Payment(in);
		}

		public Certificate_Payment[] newArray(int size)
		{
			return new Certificate_Payment[size];
		}
	};

	private Certificate_Payment(Parcel in)
	{
		payment_id = in.readInt();
		certificate_id = in.readInt();
		credit_card_id = in.readInt();
		promotion_activity_id = in.readInt();
		card_holder_name = in.readString();
		card_number = in.readString();
		card_number_encrypted = (Byte[]) in.readSerializable();
		card_expiration_date = in.readString();
		payment_amount = (BigDecimal) in.readSerializable();
		payment_date_utc_stamp = (Date) in.readSerializable();
		certificate_obj = in.readParcelable(Certificate.class.getClassLoader());
		credit_card_obj = in.readParcelable(Credit_Card.class.getClassLoader());
		promotion_activity_obj = in.readParcelable(Promotion_Activity.class.getClassLoader());
		application_type_obj = in.readParcelable(Application_Type.class.getClassLoader());
		login_log_obj = in.readParcelable(Login_Log.class.getClassLoader());
	}

	public int getPaymentId()
	{
		return this.payment_id;
	}
	public void setPaymentId(int payment_id)
	{
		this.payment_id = payment_id;
	}

	public int getCertificateId()
	{
		return this.certificate_id;
	}
	public void setCertificateId(int certificate_id)
	{
		this.certificate_id = certificate_id;
	}

	public int getCreditCardId()
	{
		return this.credit_card_id;
	}
	public void setCreditCardId(int credit_card_id)
	{
		this.credit_card_id = credit_card_id;
	}

	public int getPromotionActivityId()
	{
		return this.promotion_activity_id;
	}
	public void setPromotionActivityId(int promotion_activity_id)
	{
		this.promotion_activity_id = promotion_activity_id;
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

	public String getCardExpirationDate()
	{
		return this.card_expiration_date;
	}
	public void setCardExpirationDate(String card_expiration_date)
	{
		this.card_expiration_date = card_expiration_date;
	}

	public BigDecimal getPaymentAmount()
	{
		return this.payment_amount;
	}
	public void setPaymentAmount(BigDecimal payment_amount)
	{
		this.payment_amount = payment_amount;
	}

	public Date getPaymentDateUtcStamp()
	{
		return this.payment_date_utc_stamp;
	}
	public void setPaymentDateUtcStamp(Date payment_date_utc_stamp)
	{
		this.payment_date_utc_stamp = payment_date_utc_stamp;
	}

	public Certificate getCertificateObj()
	{
		return this.certificate_obj;
	}
	public void setCertificateObj(Certificate certificate_obj)
	{
		this.certificate_obj = certificate_obj;
	}

	public Credit_Card getCreditCardObj()
	{
		return this.credit_card_obj;
	}
	public void setCreditCardObj(Credit_Card credit_card_obj)
	{
		this.credit_card_obj = credit_card_obj;
	}

	public Promotion_Activity getPromotionActivityObj()
	{
		return this.promotion_activity_obj;
	}
	public void setPromotionActivityObj(Promotion_Activity promotion_activity_obj)
	{
		this.promotion_activity_obj = promotion_activity_obj;
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
