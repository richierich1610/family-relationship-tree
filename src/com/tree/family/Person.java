package com.tree.family;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tree.exception.InvalidRelationEnteredException;

/*
 * Person model to keep information about individual member.
 * Holds household details if there is any.
 * 
 */

public class Person {

	private String name;
	private GENDER gender;
	private Household family;
	private Household parentFamily;
	
	public Person(GENDER gender,String name,Household parentFamily) {
		super();
		this.name = name;
		this.gender = gender;
		this.parentFamily = parentFamily;
	}

	public Person(GENDER gender, String name) {
		super();
		this.name = name;
		this.gender = gender;

	}

	public String getName() {
		return name;
	}
	
	public GENDER getGender() {
		return gender;
	}
	
	/**
	 * 
	 * @return Father reference of the person
	 */
	public Person getFather()
	{
		return  (this.parentFamily != null)?this.parentFamily.getHusband():null;
	}
	
	/**
	 * 
	 * @return Mother reference of the person
	 */
	public Person getMother()
	{
		return  (this.parentFamily != null)?this.parentFamily.getWife():null;
	}
	
	/**
	 * 
	 * @return : Spouse of the person if any.
	 */
	public Person getSpouse()
	{
		if(this.family != null)
		{
			return (this.gender.equals(GENDER.MALE))?this.family.getWife():this.family.getHusband();
		}
		return null;
	}
	
	/**
	 * @return Siblings of a person
	 */
	public List<Person> getSiblings()
	{
		List<Person> result = new ArrayList<>();
		if (this.parentFamily != null && this.parentFamily.getChildren() != null) {
			result.addAll(this.parentFamily.getChildren().stream().filter(child -> (!child.getName().equalsIgnoreCase(this.getName())))
					.collect(Collectors.toList()));
		}
		return result;
	}
	
	/**
	 * 
	 * @param name : Name of the new Son
	 * @return Newly added person
	 * @throws InvalidRelationEnteredException 
	 */
	public Person addSon(String name) throws InvalidRelationEnteredException
	{
		addChild(GENDER.MALE, name);
		return this;
	}
	
	/**
	 * 
	 * @param name : Name of the new daughter
	 * @return Newly added person
	 * @throws InvalidRelationEnteredException 
	 */
	public Person addDaughter(String name) throws InvalidRelationEnteredException
	{
		addChild(GENDER.FEMALE, name);
		return this;
	}	
	
	private Person addChild(GENDER gender,String childName) throws InvalidRelationEnteredException
	{
		if(this.family == null)
				throw new InvalidRelationEnteredException(childName+" can not be added as "+this.getName()+" has not family specified!");
		Person child = null;;
		if(childName != null && !childName.trim().isEmpty() && this.family != null)
		{
				child = new Person(gender,childName,this.family);
				if(this.family.getChildren() == null)
					this.family.setChildren(new ArrayList<>());
				this.family.getChildren().add(child);
		}
		return child;
	}
	
	/**
	 * 
	 * @return all children of the a person
	 */
	
	public List<Person> getChildren()
	{
		List<Person> result = new ArrayList<>();
		if(this.family != null && this.family.getChildren() != null)
			result.addAll(this.family.getChildren());
		return result;
	}
	
	/**
	 * 
	 * @param spouse : Spouse to marry
	 * @return : Newly married spouse person
	 */
	public Person addMarriage(Person spouse)
	{
		if(spouse != null)
		{
				this.family = new Household(this, spouse);
				spouse.family = this.family;
				return spouse;
		}
		return null;
	}
}
