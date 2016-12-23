package com.polovnev.trader;

import com.polovnev.constants.Constants;
import com.polovnev.util.RandomValueGenerator;

public class Trader {

	private FirstName firstName;
	private LastName lastName;
	private int profit;
	private Gender gender;
	private Country country;
	private String urlName;
	
	public Trader() {
		this.gender = RandomValueGenerator.getGender();
		this.firstName = RandomValueGenerator.getFirstName(gender);
		this.lastName = RandomValueGenerator.getLastName(gender);
		this.profit = RandomValueGenerator.getInt(Constants.MIN_PROFIT, Constants.MAX_PROFIT);
		this.country = RandomValueGenerator.getCountry();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(firstName.toString().toLowerCase());
		stringBuilder.append('_');
		stringBuilder.append(lastName.toString().toLowerCase());
		this.urlName = stringBuilder.toString();
	}

	public FirstName getFirstName() {
		return firstName;
	}

	public LastName getLastName() {
		return lastName;
	}

	public int getProfit() {
		return profit;
	}

	public Gender getGender() {
		return gender;
	}

	public Country getCountry() {
		return country;
	}

	public String getUrlName() {
		return urlName;
	}

	
	
	
	
	
}
