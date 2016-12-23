package com.polovnev.trader;

public enum LastName {
	
	POLOVNEV(Gender.MALE), POLOVNEVA(Gender.FEMALE), IVANOV(Gender.MALE), IVANOVA(Gender.FEMALE);
	
	private Gender gender;

	private LastName(Gender gender) {
		this.gender = gender;
	}
	
	public Gender getGender() {
		return gender;
	}
	

}
