package com.tree.runner;

public class FamilyInputModel {

	private String person;
	private String dependantPerson;
	private String relation;
	private String dependantRelation;
	private TreeOperation operation;
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getDependantPerson() {
		return dependantPerson;
	}
	public void setDependantPerson(String dependantPerson) {
		this.dependantPerson = dependantPerson;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getDependantRelation() {
		return dependantRelation;
	}
	public void setDependantRelation(String dependantRelation) {
		this.dependantRelation = dependantRelation;
	}
	public TreeOperation getOperation() {
		return operation;
	}
	public void setOperation(TreeOperation operation) {
		this.operation = operation;
	}
	
	
}

enum TreeOperation
{
	SEARCH,ADD
}
