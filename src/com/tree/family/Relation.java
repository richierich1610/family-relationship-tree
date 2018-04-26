package com.tree.family;

import com.tree.exception.InvalidRelationEnteredException;

/*
 * defines all supported relations in family tree
 */
public enum Relation {
	
	SON("SON","SONS",true,GENDER.MALE),DAUGHTER("DAUGHTER","DAUGHTERS",true,GENDER.FEMALE),FATHER("FATHER",false,GENDER.MALE),MOTHER("MOTHER",false,GENDER.FEMALE),WIFE("WIFE",true,GENDER.FEMALE),HUSBAND("HUSBAND",true,GENDER.MALE),
	BROTHER("BROTHER","BROTHERS",true,GENDER.MALE),SISTER("SISTER","SISTERS",true,GENDER.FEMALE),COUSIN("COUSIN","COUSINS"),GRANDMOTHER("GRANDMOTHER",false,GENDER.FEMALE),
	GRANDFATHER("GRANDFATHER",false,GENDER.MALE),GRANDSON("GRANDSON","GRANDSONS",GENDER.MALE),GRANDAUGHTER("GRANDAUGHTER","GRANDAUGHTERS",GENDER.FEMALE),AUNT("AUNT","AUNTS",GENDER.FEMALE),UNCLE("UNCLE","UNCLES",GENDER.MALE);
	
	Relation(String value,boolean additionAllowed,GENDER gender)
	{
		this.value = value;
		this.additionAllowed = additionAllowed;
		this.gender = gender;
	}
	
	Relation(String value,String alias)
	{
		this.value = value;
		this.alias = alias;
	}
	
	Relation(String value,String alias,GENDER gender)
	{
		this.value = value;
		this.alias = alias;
		this.gender = gender;
	}
	
	Relation(String value,String alias,boolean additionAllowed,GENDER gender)
	{
		this.value = value;
		this.alias = alias;
		this.additionAllowed = additionAllowed;
		this.gender = gender;
	}
	
	
	private String value;
	private String alias;
	private boolean additionAllowed;
	private GENDER gender;
	
	public String getValue() {
		return value;
	}

	public boolean isAdditionAllowed() {
		return additionAllowed;
	}

	public String getAlias() {
		return alias;
	}
	

	public GENDER getGender() {
		return gender;
	}

	public static Relation enumValueOf(String value) throws InvalidRelationEnteredException
	{
		for (Relation enumValue : Relation.values()) {
			if(enumValue.getValue().equals(value) || value.equals(enumValue.getAlias()))
				return enumValue;
		}
		
		throw new InvalidRelationEnteredException("Invalid Relation Entered!");
	}
}
	