package com.tree.manager;

import java.util.List;
import com.tree.exception.DuplicatePersonFoundException;
import com.tree.exception.InvalidRelationEnteredException;
import com.tree.exception.NoMemberFoundException;
import com.tree.exception.OperationNotAllowedException;
import com.tree.family.Person;

/**
 * Family Relationship manager: Responsible for family interactions
 * @author rgora
 *
 */
public interface RelationManager {

	public Person AddMember(Person root,String parentName,String parentRelation,String childName,String relationToAdd) throws DuplicatePersonFoundException, OperationNotAllowedException, InvalidRelationEnteredException, NoMemberFoundException;
	public List<Person> searchRelation(Person head,String name,String relation)throws InvalidRelationEnteredException, NoMemberFoundException;
	public Person searchMember(Person head, String name);
	public void printMembers(Person root,int depth);
}
