package com.tree.runner;

import java.util.Scanner;
import java.util.stream.Collectors;
import com.tree.exception.DuplicatePersonFoundException;
import com.tree.exception.InvalidArgumentException;
import com.tree.exception.InvalidRelationEnteredException;
import com.tree.exception.NoMemberFoundException;
import com.tree.exception.OperationNotAllowedException;
import com.tree.family.FamilyTree;
import com.tree.family.GENDER;
import com.tree.family.Person;
import com.tree.family.Relation;

/*
 * Main program to run the Application
 */

public class ApplicationRunner {

	public static void main(String[] args) {

		printUserInformation();
		try {
			readUserInput();
		} catch (DuplicatePersonFoundException | OperationNotAllowedException | InvalidRelationEnteredException
				| NoMemberFoundException e1) {
			e1.printStackTrace();
		}
	}

	private static void readUserInput() throws DuplicatePersonFoundException, OperationNotAllowedException,
			InvalidRelationEnteredException, NoMemberFoundException {
		Scanner scanner = new Scanner(System.in);
		FamilyTree familyTree = null;
		String str = null;
		System.out.println("\nPlease enter choose options below!");
		System.out.println("\nType 'new' to build new family tree!");
		System.out.println("\nType anything to use default tree!");
		str = scanner.nextLine();
		familyTree = readTreeOptions(scanner, familyTree, str);
		printOptions();

		while (true) {
			str = scanner.nextLine();
			if ("quit".equals(str)) {
				scanner.close();
				System.exit(0);
			}

			try {
				FamilyInputModel familyTreeInput = resolveArguments(str.split(" "));
				if (familyTreeInput.getOperation().equals(TreeOperation.SEARCH)) {
					System.out.println(familyTreeInput.getRelation() + " = "
							+ familyTree
									.searchMember(familyTreeInput.getPerson().toUpperCase(),
											familyTreeInput.getRelation().toUpperCase())
									.stream().map(data -> data.getName()).collect(Collectors.joining(",")));
				} else {
					System.out.println(familyTree.addMember(familyTreeInput.getPerson().toUpperCase(),
							familyTreeInput.getRelation().toUpperCase(),
							familyTreeInput.getDependantPerson().toUpperCase(),
							familyTreeInput.getDependantRelation().toUpperCase()));
					familyTree.printMembers();
				}
			} catch (OperationNotAllowedException | DuplicatePersonFoundException | InvalidArgumentException | InvalidRelationEnteredException | NoMemberFoundException e) {
				System.out.println(e.getMessage());
				printOptions();
			}

		}
	}

	private static FamilyTree readTreeOptions(Scanner scanner, FamilyTree familyTree, String str)
			throws DuplicatePersonFoundException, OperationNotAllowedException, InvalidRelationEnteredException,
			NoMemberFoundException {
		if (str.equals("new")) {
			while (true) {
				System.out.println("\nEnter family head details! Please use this format-> 'Evan=Male' or 'Diana=Female'");
				System.out.println("\nType quit to exit!");
				str = scanner.nextLine();
				if ("quit".equals(str)) {
					scanner.close();
					System.exit(0);
				}
				String[] headDetails = str.split("=");
				if (headDetails.length == 2) {
					try {
						Person head = new Person(GENDER.valueOf(headDetails[1].toUpperCase()),
								headDetails[0].toUpperCase());
						familyTree = new FamilyTree(head);
						System.out.println("\nFamily head set!");
						break;
					} catch (Exception e) {
						System.out.println("\nPlease check inputs!");
					}
				}
			}
		} else {
			familyTree = buildDefaultFamilyTree();
			familyTree.printMembers();
		}
		return familyTree;
	}

	/**
	 * prints options to user
	 */
	private static void printOptions() {
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("To add new relation, please use below format!");
		System.out.println("Husband=Bern Wife=Julia");
		System.out.println("\n");
		System.out.println("To search existing relation, please use below format!");
		System.out.println("Person=Bern Relation=Wife");
		System.out.println("\n");
		System.out.println("Enter 'quit' to exit!");
	}

	private static void printUserInformation() {
		System.out.println(
				"<--------------------------Welcome to Family Relationship Tree------------------------------------------------>");
		System.out.println("\n");

		System.out.println("<----------------Below given relations are supported to search------------------>");
		for (Relation relation : Relation.values()) {
			System.out.print(relation.getValue() + "\t");
		}
		System.out.println("\n");
		System.out.println("\n");

		System.out.println("<----------------Below given relations can be added in the tree------------------>");
		for (Relation relation : Relation.values()) {
			if (relation.isAdditionAllowed())
				System.out.print(relation.getValue() + "\t");
		}
	}

	private static FamilyTree buildDefaultFamilyTree() throws DuplicatePersonFoundException,
			OperationNotAllowedException, InvalidRelationEnteredException, NoMemberFoundException {
		Person evan = new Person(GENDER.MALE, "Evan");
		FamilyTree familyTree = new FamilyTree(evan);
		System.out.println(familyTree.addMember("Evan", "HUSBAND", "Diana", "WIFE"));
		System.out.println(familyTree.addMember("Diana", "MOTHER", "NISHA", "DAUGHTER"));
		System.out.println(familyTree.addMember("Evan", "FATHER", "JOHN", "SON"));
		System.out.println(familyTree.addMember("Evan", "FATHER", "ALEX", "SON"));
		System.out.println(familyTree.addMember("Evan", "FATHER", "JOE", "SON"));
		System.out.println(familyTree.addMember("NISHA", "WIFE", "Adam", "HUSBAND"));
		System.out.println(familyTree.addMember("ALEX", "HUSBAND", "NANCY", "WIFE"));
		System.out.println(familyTree.addMember("JOE", "HUSBAND", "NIKI", "WIFE"));
		System.out.println(familyTree.addMember("ALEX", "FATHER", "JACOB", "SON"));
		System.out.println(familyTree.addMember("ALEX", "FATHER", "SHAUN", "SON"));
		System.out.println(familyTree.addMember("JACOB", "HUSBAND", "RUFI", "WIFE"));
		System.out.println(familyTree.addMember("JACOB", "FATHER", "SOFIA", "DAUGHTER"));
		System.out.println(familyTree.addMember("SOFIA", "WIFE", "GEORGE", "HUSBAND"));
		System.out.println(familyTree.addMember("JACOB", "FATHER", "BERN", "SON"));
		System.out.println(familyTree.addMember("JOE", "FATHER", "PIERS", "SON"));
		System.out.println(familyTree.addMember("JOE", "FATHER", "SALLY", "DAUGHTER"));
		System.out.println(familyTree.addMember("ADAM", "FATHER", "RUTH", "DAUGHTER"));
		System.out.println(familyTree.addMember("ADAM", "FATHER", "WILLIAM", "SON"));
		System.out.println(familyTree.addMember("ADAM", "FATHER", "PAUL", "SON"));
		System.out.println(familyTree.addMember("WILLIAM", "HUSBAND", "ROSE", "WIFE"));
		System.out.println(familyTree.addMember("WILLIAM", "FATHER", "STEVE", "SON"));
		System.out.println(familyTree.addMember("PAUL", "HUSBAND", "ZOE", "WIFE"));
		System.out.println(familyTree.addMember("PAUL", "FATHER", "ROGER", "SON"));
		System.out.println(familyTree.addMember("RUTH", "WIFE", "NEIL", "HUSBAND"));
		System.out.println(familyTree.addMember("SALLY", "WIFE", "OWEN", "HUSBAND"));
		System.out.println(familyTree.addMember("PIERS", "HUSBAND", "PIPPA", "WIFE"));
		System.out.println(familyTree.addMember("PIERS", "FATHER", "SARAH", "DAUGHTER"));
		System.out.println(familyTree.addMember("SARAH", "WIFE", "PETER", "HUSBAND"));
		return familyTree;
	}

	private static FamilyInputModel resolveArguments(String[] args) throws InvalidArgumentException {
		if (args == null || args.length != 2)
			throw new InvalidArgumentException("Please enter valid arguments!");

		String[] paramSet1 = args[0].split("=");
		String[] paramSet2 = args[1].split("=");

		if (paramSet1.length != 2 || paramSet2.length != 2)
			throw new InvalidArgumentException("Please enter valid arguments!");
		FamilyInputModel inputModel = new FamilyInputModel();
		if (paramSet1[0].toUpperCase().equals("PERSON")) {
			inputModel.setOperation(TreeOperation.SEARCH);
			inputModel.setPerson(paramSet1[1]);
			if (!paramSet2[0].toUpperCase().equals("RELATION"))
				throw new InvalidArgumentException("Please enter valid arguments!");
			inputModel.setRelation(paramSet2[1]);

		} else {
			inputModel.setOperation(TreeOperation.ADD);
			inputModel.setRelation(paramSet1[0]);
			inputModel.setPerson(paramSet1[1]);
			inputModel.setDependantRelation(paramSet2[0]);
			inputModel.setDependantPerson(paramSet2[1]);
		}

		return inputModel;

	}

}