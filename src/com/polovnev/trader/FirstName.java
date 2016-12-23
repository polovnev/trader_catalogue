package com.polovnev.trader;

public enum FirstName {
	
	ALEKSANDR(Gender.MALE), SERGEY(Gender.MALE), NIKOLAY(Gender.MALE), 
	DMYTRIY(Gender.MALE), ALEKSEY(Gender.MALE), MAGNUS(Gender.MALE), 
	MAURICE(Gender.MALE), MARIYA(Gender.FEMALE), ANNA(Gender.FEMALE), MONICA(Gender.FEMALE),
	NATALIYA(Gender.FEMALE), ANN(Gender.FEMALE), DARIYA(Gender.FEMALE);
	
	private Gender gender;

	private FirstName(Gender gender) {
		this.gender = gender;
	}
	
	public Gender getGender() {
		return gender;
	}


}
