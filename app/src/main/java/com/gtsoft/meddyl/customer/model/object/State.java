package com.gtsoft.meddyl.customer.model.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Date;

public class State implements Parcelable
{
	private int state_id;
	private String state;
	private String abbreviation;

	public State()
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
		out.writeInt(state_id);
		out.writeString(state);
		out.writeString(abbreviation);
	}

	public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() 
	{
		public State createFromParcel(Parcel in)
		{
			return new State(in);
		}

		public State[] newArray(int size)
		{
			return new State[size];
		}
	};

	private State(Parcel in)
	{
		state_id = in.readInt();
		state = in.readString();
		abbreviation = in.readString();
	}

	public int getStateId()
	{
		return this.state_id;
	}
	public void setStateId(int state_id)
	{
		this.state_id = state_id;
	}

	public String getState()
	{
		return this.state;
	}
	public void setState(String state)
	{
		this.state = state;
	}

	public String getAbbreviation()
	{
		return this.abbreviation;
	}
	public void setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
	}
}
