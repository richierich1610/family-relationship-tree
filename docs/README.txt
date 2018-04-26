This README file explains how to use Family Tree program. This file also specifies 
minimum system requirements needed to run the program.

System Requirements:
---------------------
	- System must have java-8 or higher version.
	- System must comply minimum requirements specified for jvm.

Libraries added in the application
-----------------------------------
	- Junit-4.12.jar
		- Used for unit testing of the code.All Unit tests are written in FamilyTreeTest.java, which
		can be run using TestRunner.java
	- Hamcrest-core-1.3.jar
		- Compile time dependency of Junit.

Running in an IDE
---------------------

If you want to run the application in an IDE, such as Eclipse, you should
be able to import the entire project into a project in the IDE. Alternatively 
you can copy entire source files into existing java project. After this add dependencies into classpath and
then run ApplicationRuunner.java to run application or TestRunner.java to run the tests.

                  
Run using executable JAR file
------------------------------
                  
If you find it easier to run programs by double-clicking, you can
use executable jar files to run the application. There is executable jar file 
in /runnable folder which can be used to run the application.
                 
You should be able to run an executable jar file just by double-clicking it.
(On Linux, you might have to right-click it and select a command such as
"Open with... JDK 8".)

Execution instructions:
--------------------------

Once you run the application using one of either ways, you must be prompted with 2 options: 

	- To create new family tree, enter 'new'.
		- You would have to enter family head detail with Gender as below:
			'Evan=Male' or 'Diana=Female'
	- To build default tree given with the problem, type 'default'.

Once you are done with setting head or using default tree, you can use 
lists of relations available in Family tree.
 
Below is a list of relations which can be used to search in family tree:
----------------------------------------------------------------------

[SON,DAUGHTER,FATHER,MOTHER,WIFE,HUSBAND,BROTHER,SISTER,COUSIN,GRANDMOTHER,GRANDFATHER,GRANDSON,GRANDAUGHTER,AUNT,UNCLE]

Second list is of relations which can be used to add new members in family tree:
--------------------------------------------------------------------------------

[SON,DAUGHTER,WIFE,HUSBAND,BROTHER,SISTER]


Inputs to the application:
-------------------------
	- To add new relation, Use below format!
		Husband=Bern Wife=Julia
		
	- To search existing relation, Use below format!
		Person=Bern Relation=Wife


Assumptions:
-------------

	-Members can be added in top-down flow only that means immediate members can be added in family tree. Each person who is added in family tree 
		must have parent associated with it except head of the family. 
	-All Persons would have unique name in the tree.
	-A member can only be added with an existing member only.

Extensions:-
--------------

	-Application can be extended to support further relations as well.
	-Add a new family relationship manager by implementing RelationManager interface and pass it while defining head of the family.
	-FamilyRelationshipManager.java also can be extended to support more relations.
	
Application will continue to prompt for inputs and will show results.
Enter 'quit' to terminate the program.
