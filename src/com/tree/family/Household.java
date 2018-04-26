package com.tree.family;

import java.util.List;

/*
 * Model to keep information of Family for a person
 */

public class Household {
	private Person husband;
	private Person wife;
	private List<Person> children;
	
	public Household(Person member1, Person member2) {
		super();
		this.husband = (member1.getGender().equals(GENDER.MALE))?member1:member2;
		this.wife = (member1.getGender().equals(GENDER.FEMALE))?member1:member2;;
	}

	public Person getHusband() {
		return husband;
	}
	
	public Person getWife() {
		return wife;
	}
	
	public List<Person> getChildren() {
		return children;
	}
	
	public void setChildren(List<Person> children) {
		this.children = children;
	}
}
