package com.tree.test;

import org.junit.Before;
import org.junit.Test;
import com.tree.family.FamilyTree;
import com.tree.family.GENDER;
import com.tree.family.Person;
import junit.framework.Assert;

public class FamilyTreeTest {

	FamilyTree familyTree;
	
	@Before
	public void testAddHead()
	{

		Person head = new Person(GENDER.MALE, "Evan");
		familyTree = new FamilyTree(head);
		
	}
	
	@Test
	public void testAddWife()
	{
		try {
			Assert.assertEquals("Welcome to the family, Diana!",familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddSon()
	{
		try {
			Assert.assertEquals("Welcome to the family, John!",familyTree.addMember("Evan", "FATHER", "John", "SON"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddDaughter()
	{
		try {
			Assert.assertEquals("Welcome to the family, Nisha!",familyTree.addMember("Evan", "FATHER", "Nisha", "DAUGHTER"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddBrother()
	{
		try {
			familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE");
			familyTree.addMember("Evan", "FATHER", "John", "SON");
			Assert.assertEquals("Welcome to the family, Nikki!",familyTree.addMember("John", "BROTHER", "Nikki", "SISTER"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchDaughter()
	{
		try {
			familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE");
			familyTree.addMember("Evan", "FATHER", "John", "SON");
			familyTree.addMember("John", "BROTHER", "Nikki", "SISTER");
			Assert.assertEquals("Nikki",familyTree.searchMember("Evan", "DAUGHTER").get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchSon()
	{
		try {
			familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE");
			familyTree.addMember("Evan", "FATHER", "John", "SON");
			familyTree.addMember("John", "BROTHER", "Nikki", "SISTER");
			Assert.assertEquals("John",familyTree.searchMember("Evan", "SON").get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchWife()
	{
		try {
			familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE");
			familyTree.addMember("Evan", "FATHER", "John", "SON");
			familyTree.addMember("John", "BROTHER", "Nikki", "SISTER");
			Assert.assertEquals("Diana",familyTree.searchMember("Evan", "WIFE").get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
