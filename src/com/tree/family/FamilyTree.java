package com.tree.family;

import java.util.List;

import com.tree.exception.DuplicatePersonFoundException;
import com.tree.exception.InvalidRelationEnteredException;
import com.tree.exception.NoMemberFoundException;
import com.tree.exception.OperationNotAllowedException;
import com.tree.exception.RootAlreadyFoundException;
import com.tree.manager.FamilyRelationManager;
import com.tree.manager.RelationManager;


/**
 * Family Tree to hold all family person in hierarchical order.  
 * @author rgora
 *
 */
public class FamilyTree {
	
	private RelationManager relationManager;
	
	private RelationManager  defaultRelationManager = new FamilyRelationManager();
	
	private Person root;
	
	public Person getRoot() {
		return root;
	}

	public FamilyTree(Person head) {
		this.root = head;
		relationManager = defaultRelationManager;
	}
	
	/**
	 * 
	 * @param head : Head of the family.
	 * @param relationManager : Relationship manager for a family. 
	 */
	public FamilyTree(Person head,RelationManager relationManager) {
		this.root = head;
		this.relationManager = (relationManager != null)?relationManager:defaultRelationManager;
	}
	
	public Person addRoot(String name) throws RootAlreadyFoundException{
		if(root != null)
		{
			throw new RootAlreadyFoundException("Duplicate family head found.");
		}
		return null;
	}
		
	/*
	 * Method to add member in family
	 * @param1:  Parent name
	 * @param2: relation to parent
	 * @param3:  new member name
	 * @param4:	relation of child
	 */
	public String addMember(String parentName,String parentRelation,String childName,String relationToAdd) throws DuplicatePersonFoundException, OperationNotAllowedException, InvalidRelationEnteredException, NoMemberFoundException
	{
		 if(relationManager.AddMember(root, parentName, parentRelation, childName, relationToAdd) != null)
		 {
			 return String.format("Welcome to the family, %s!",childName);
		 }
		 return String.format("%s can not be added!",childName);
	}
	
	/*
	 * Method to search member in family
	 * @param: name of the member to search
	 * @param: relation to search
	 */
	public List<Person> searchMember(String name,String relation) throws NoMemberFoundException, InvalidRelationEnteredException
	{
		return relationManager.searchRelation(root, name, relation);
	}
	
	/**
	 * Utility method to print family tree at any point
	 */
	public void printMembers()
	{
		relationManager.printMembers(root,0);
	}
	
}
