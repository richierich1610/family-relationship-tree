package com.tree.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tree.exception.DuplicatePersonFoundException;
import com.tree.exception.InvalidRelationEnteredException;
import com.tree.exception.NoMemberFoundException;
import com.tree.exception.OperationNotAllowedException;
import com.tree.family.GENDER;
import com.tree.family.Person;
import com.tree.family.Relation;

/**
 * Concrete family manager implementation to support family operations
 * @author rgora
 *
 */
public class FamilyRelationManager implements RelationManager {



	/**
	 * Method to search in family tree for a given relation
	 */
	@Override
	public List<Person> searchRelation(Person head,String name,String relation) throws InvalidRelationEnteredException, NoMemberFoundException {
		Person person = searchMember(head, name);
		if (person == null)
			throw new NoMemberFoundException("No Family member found with the name of " + name);
		List<Person> result = new ArrayList<>();
		Relation childRelation = Relation.enumValueOf(relation);
		if (childRelation.equals(Relation.BROTHER) || childRelation.equals(Relation.SISTER))
			result.addAll(checkForSiblings(person, childRelation));
		
		else if (childRelation.equals(Relation.WIFE) || childRelation.equals(Relation.HUSBAND)) {
			result.add(checkForSpouse(person, childRelation));
			
		} else if (childRelation.equals(Relation.SON) || childRelation.equals(Relation.DAUGHTER))
			result.addAll(checkForChildren(person, childRelation));
		
		else if (childRelation.equals(Relation.GRANDFATHER) || childRelation.equals(Relation.GRANDMOTHER)) {
			result.addAll(getGrandParents(person, childRelation));
			
		} else if (childRelation.equals(Relation.GRANDAUGHTER) || childRelation.equals(Relation.GRANDSON)) {
			result.addAll(getGrandChildren(person, childRelation));
			
		} else if (childRelation.equals(Relation.COUSIN)) {
			result.addAll(getCousin(person, childRelation));
			
		} else if (childRelation.equals(Relation.UNCLE) || childRelation.equals(Relation.AUNT)) {
			result.addAll(getParentsSiblings(person, childRelation));
			
		} else if (childRelation.equals(Relation.FATHER)) {
			if (person.getFather() != null)
				result.add(person.getFather());
			
		} else if (childRelation.equals(Relation.MOTHER)) {
			if (person.getMother() != null)
				result.add(person.getMother());
		}
		return result;
	}


	/**
	 * Utility method to search children of the current person.
	 * @param person
	 * @param childRelation
	 * @return Children of the person
	 */
	private List<Person> checkForChildren(Person person, Relation childRelation) {
		return person.getChildren().stream()
				.filter(child -> child.getGender().equals(childRelation.getGender())).collect(Collectors.toList());
	}


	/**
	 * Utility method to check for spouse
	 * @param person
	 * @param childRelation
	 * @return Spouse of the person
	 * @throws InvalidRelationEnteredException
	 */
	private Person checkForSpouse(Person person, Relation childRelation)
			throws InvalidRelationEnteredException {
		if (person.getSpouse() != null && person.getSpouse().getGender().equals(childRelation.getGender()))
			return person.getSpouse();
		else
			throw new InvalidRelationEnteredException("Invalid Relation Entered! Please use correct relations!");
	}

	/**
	 * Utility method to search in all siblings of a person
	 * @param person
	 * @param childRelation
	 * @return Siblings of the person
	 */

	private List<Person> checkForSiblings(Person person,Relation childRelation) {
		return person.getSiblings().stream()
				.filter(sibling -> sibling.getGender().equals(childRelation.getGender()))
				.collect(Collectors.toList());
	}
	
	
	/**
	 * utility method to search grandparents in family tree
	 * @param person
	 * @param childRelation
	 * @return
	 */
	private List<Person> getGrandParents(Person person,Relation childRelation)
	{
		List<Person> result = new ArrayList<>();
		if(childRelation.equals(Relation.GRANDFATHER))
		{
			if(person.getFather() != null && person.getFather().getFather() != null)
				result.add(person.getFather().getFather());
			if(person.getMother() != null && person.getMother().getFather() != null)
				result.add( person.getMother().getFather());
		}
			
		if(childRelation.equals(Relation.GRANDMOTHER) && person.getFather() != null && person.getFather().getFather() != null)
		{
			if(person.getFather() != null && person.getFather().getMother() != null)
				result.add(person.getFather().getMother());
			if(person.getMother() != null && person.getMother().getMother() != null)
				result.add( person.getMother().getMother());
		}
			
		return result;
	}
	
	
	/**
	 * Utility method to search grandchildren in family tree.
	 * @param person
	 * @param childRelation
	 * @return
	 */
	private List<Person> getGrandChildren(Person person,Relation childRelation)
	{
		List<Person> result = new ArrayList<>();
		if(person.getChildren() != null)
		{
			person.getChildren().stream().forEach(child->{
				if(child.getChildren() != null)
				{
					result.addAll(child.getChildren().stream().filter(grandChild->grandChild.getGender().equals(childRelation.getGender())).collect(Collectors.toList()));
				}
			});
		}
		return result;
	}
	
	
	/**
	 * Utility method to get cousins for a given person
	 * @param person
	 * @param childRelation
	 * @return
	 */
	private List<Person> getCousin(Person person,Relation childRelation)
	{
		List<Person> result = new ArrayList<>();
		if(person.getFather() != null && person.getFather().getSiblings() != null)
		{
			person.getFather().getSiblings().stream().forEach(child->{
				if(child.getChildren() != null)
				{
					result.addAll(child.getChildren().stream().collect(Collectors.toList()));
				}
			});
		}
		
		if(person.getMother() != null && person.getMother().getSiblings() != null)
		{
			person.getMother().getSiblings().stream().forEach(child->{
				if(child.getChildren() != null)
				{
					result.addAll(child.getChildren().stream().collect(Collectors.toList()));
				}
			});
		}
		return result;
	}
	
	
	/**
	 * Utility method to get uncle/aunts
	 * @param person
	 * @param childRelation
	 * @return
	 */
	private List<Person> getParentsSiblings(Person person,Relation childRelation)
	{
		List<Person> result = new ArrayList<>();
		if(person.getFather() != null && person.getFather().getSiblings() != null)
		{
			person.getFather().getSiblings().stream().forEach(sibling->{
				if(sibling.getGender().equals(childRelation.getGender()))
				{
					result.add(sibling);
				}
				else if(sibling.getSpouse() != null)
				{
					result.add(sibling.getSpouse());
				}
			});
		}
		
		if(person.getMother() != null && person.getMother().getSiblings() != null)
		{
			person.getMother().getSiblings().stream().forEach(sibling->{
				if(sibling.getGender().equals(childRelation.getGender()))
				{
					result.add(sibling);
				}
				else if(sibling.getSpouse() != null)
				{
					result.add(sibling.getSpouse());
				}
			});
		}
		return result;
	}

	/*
	 * Method to search member in family
	 * @param: name of the member to search
	 */
	@Override
	public Person searchMember(Person head, String name) {
		if (head == null || head.getName().equalsIgnoreCase(name))
			return head;
		if(head.getSpouse() != null && head.getSpouse().getName().equalsIgnoreCase(name))
			return head.getSpouse();
		if (head.getChildren() != null) {
			Person temp = null;
			for (Person person : head.getChildren()) {
				temp = searchMember(person, name);
				if (temp != null)
					return temp;
			}
		}
		return null;
	}

	/**
	 * Method to add member in family tree
	 */
	
	@Override
	public Person AddMember(Person root,String parentName, String parentRelation, String childName, String relationToAdd)
			throws DuplicatePersonFoundException, OperationNotAllowedException, InvalidRelationEnteredException,
			NoMemberFoundException {
		Relation childRelation = Relation.enumValueOf(relationToAdd.toUpperCase());
		Relation relationToParent = Relation.enumValueOf(parentRelation.toUpperCase());
		if(!childRelation.isAdditionAllowed())
		{
			throw new OperationNotAllowedException("Adding "+childRelation.getValue()+" is not allowed! Please add successor or siblings to parent");
		}
		
		if(searchMember(root,childName) != null)
		{
			throw new DuplicatePersonFoundException("Duplicate Person Found! There is already a person with the name of "+childName);
		}
		
		Person parent = searchMember(root,parentName);
		if (parent == null)
			throw new NoMemberFoundException("No Family member found with the name of " + parentName);
		if(!parent.getGender().equals(relationToParent.getGender()))
		{
			throw new InvalidRelationEnteredException("Invalid relation entered! Please use correct relation!");
		}
		return addMember(root,parent, childName,childRelation);
	}
	
	/**
	 * Utility method to add a member in family tree
	 * @throws InvalidRelationEnteredException 
	 */

	private Person addMember(Person root,Person parent, String child,Relation childRelation) throws InvalidRelationEnteredException {
		if(childRelation == Relation.SON)
			return parent.addSon(child);
		else if (childRelation == Relation.DAUGHTER)
			return parent.addDaughter(child);
		else if(childRelation == Relation.WIFE || childRelation == Relation.HUSBAND)
		{
			return addSpouse(root, parent, child, childRelation);
		}
		else if(childRelation == Relation.SISTER)
		{
			return addSister(parent, child);
		}
		else if(childRelation == Relation.BROTHER)
		{
			return addBrother(parent, child);
		}
		return null;
	}


	private Person addBrother(Person parent, String child) throws InvalidRelationEnteredException {
		if(parent.getFather() != null)
			return parent.getFather().addSon(child);
		throw new InvalidRelationEnteredException(child+" can not be added as "+parent.getName()+" has no parent family entered!");
	}

	/**
	 * Utility method to add sister
	 * @param parent
	 * @param child
	 * @return
	 * @throws InvalidRelationEnteredException 
	 */

	private Person addSister(Person parent, String child) throws InvalidRelationEnteredException {
		if(parent.getFather() != null)
			return parent.getFather().addDaughter(child);
		throw new InvalidRelationEnteredException(child+" can not be added as "+parent.getName()+" has no parent family entered!");
	}


	/**
	 * Utility method to add spouse
	 * @param root
	 * @param parent
	 * @param child
	 * @param childRelation
	 * @return
	 */
	private Person addSpouse(Person root, Person parent, String child, Relation childRelation) {
		Person spouse = searchMember(root, child);
		if(spouse == null)
		{
			spouse = new Person(childRelation.getGender(),child);
		}
		return parent.addMarriage(spouse);
	}
	
	/**
	 * Utility method to print family tree at any point
	 */
	
	@Override
	public void printMembers(Person root,int depth)
	{
		if (root == null)
			return;
		String indent="";
		for(int i=depth;i>0;i--)
			indent +="\t";
		if(root.getSpouse() != null)
			System.out.println(indent+"|->"+root.getName()+"/"+root.getSpouse().getName());
		else
			System.out.println(indent+"|->"+root.getName());
		
		if (root.getChildren() != null) {
			depth++;
			for (Person person : root.getChildren()) {
				printMembers(person,depth);
			}
		}
	}
}
	
	
