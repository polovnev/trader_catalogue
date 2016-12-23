package com.polovnev.util;

import java.util.ArrayList;
import java.util.List;

import com.polovnev.trader.Country;
import com.polovnev.trader.FirstName;
import com.polovnev.trader.Gender;
import com.polovnev.trader.LastName;

public class RandomValueGenerator {
	
	public static int getInt(int from, int to) {
		int coeficient = to - from;
		return   from + (int)Math.round((Math.random() * coeficient));
	}
	
	public static Gender getGender() {
		Gender[] genders = Gender.values();
		int id = getInt(0, genders.length - 1);
		return genders[id];
	}
	
	public static Country getCountry() {
		Country[] countries = Country.values();
		int id = getInt(0, countries.length - 1);
		return countries[id];
	}
	
	public static FirstName getFirstName(Gender gender) {
		FirstName[] firstNames = FirstName.values();
		List<FirstName> filteredFirstNames = new ArrayList<FirstName>();
		for(FirstName firstName : firstNames) {
			if(firstName.getGender() == gender) {
				filteredFirstNames.add(firstName);
			}
		}
		int id = getInt(0, filteredFirstNames.size() - 1);
		return filteredFirstNames.get(id);
	}
	
	public static LastName getLastName(Gender gender) {
		LastName[] lastNames = LastName.values();
		List<LastName> filteredLastNames = new ArrayList<LastName>();
		for(LastName lastName : lastNames) {
			if(lastName.getGender() == gender) {
				filteredLastNames.add(lastName);
			}
		}
		int id = getInt(0, filteredLastNames.size() - 1);
		return filteredLastNames.get(id);
	}

}
