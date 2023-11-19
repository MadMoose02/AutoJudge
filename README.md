# Introduction

### Team Members of Team 4
- Dillon Jookoo
- Virendra Narine
- Josiah Tenia
- Shaniah Baldeo
- Alexander Frederick
- Keshan Moosai

## Scope and Purpose of AutoJudge
This project is an **Auto**mated **Judge** System that is intended to assist lecturers, tutors, and other
such persons with grading student assignment submissions. Submissions will be analysed by AutoJudge 
in batches as a compressed zip file. For each source file, accurate Java syntax and adherence to programming 
paradigms will be assessed. After all source files have been analysed, a PDF report is generated with an 
overall score and constructive feedback


# Analysis

## Major Requirements and Use Cases
AutoJudge has the following requirements:
- [`JDK >= 18`](https://www.oracle.com/java/technologies/downloads)
- [`JUnit >= 4.13`](https://github.com/junit-team/junit4)
- [`Apache PDFBox == 3.0.0`](https://pdfbox.apache.org/download.html)

The major use cases of this project are as follows:
- For lecturers/professors to grade student assignment/exam submissions
- For anyone to assess quality of code and adherence to a rubric

## Target Students
The target students of this project are primarily College/University students that are required to produce Java programs


# Design

## Design Patterns Used in AutoJudge

| **Design Pattern** | **Class/Interface Name** |
| ------------------ | ------------------------ |
| Facade             | - AutoJudge
| Composite          | - SyntaxEvaluator (interface) <br>- Evaluator (composite)<br>- BehaviourEvaluator (leaf)<br>- HierarchyEvaluator (leaf)<br>- ConventionsEvaluator (leaf)
| Template           | - AbstractTestCase
| Iterator           | - AbstractTestCollectionIterator

## Benefits of Design Patterns Used
- Facade: 
- Composite: 
- Template: 
- Iterator: 

## Conformance to SOLID
This project conforms to the SOLID principles as follows:<br>
**S** - Single Responsibility Principle of each class is enforced. Delegations are made where necessary<br>
**O** - Open/Closed Priciple of all modules is adhered to, whereby all are open to extension but closed to modification<br>
**L** - Liskov Substitution Principle is complied with to ensure that sub-modules of a major module can be interchanged<br>
**I** - Interface Segregation Principle is maximally observed to ensure that no module is required to use code that it does not need<br>
**D** - Dependency Inversion Principle is obeyed such that every module is loosely-coupled and depends on abstractions<br>

## Class Diagram
The following is an overview of the UML class diagram for AutoJudge
[Insert image with hyperlink here]


# Implementation

## How to Run AutoJudge
1. Download or clone the project's respository onto your machine
2. Place your zip-file containing all the student submissions inside the [resources](https://github.com/MadMoose02/AutoJudge/tree/main/src/main/resources) directory (a sample file `Submissions.zip` is already provided for test runs)
3. Modify the name of the submission zip-file to be evaluated as necessary in [App.java](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/App.java)
3. Compile the project and run [App.java](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/App.java) using Maven build tools

## Setup Requirements
- Ensure that your Java Runtime Environment is active and up-to-spec (JDK >= 18)<br>
- Ensure that Maven is installed and functional
- Before compiling the project, run `mvn package` inside a terminal at the project's root directory
- Ensure there is a submission file in the [resources](https://github.com/MadMoose02/AutoJudge/tree/main/src/main/resources) directory


# Testing and Evaluation

## [Test Cases](https://github.com/MadMoose02/AutoJudge/tree/main/src/test/java/com/team4)
**Evaluator Test Suite**
 - AssociationHierarchyEvaluatorTest.java
 - ConstructorBehaviourEvaluatorTest.java
 - InheritanceHierarchyEvaluatorTest.java
 - ReturnTypeEvaluatorTest.java

**AutoJudge Modules Test Suite**
 - ReportGeneratorTest.java
 - SubmissionDecompressorTest.java

## Demo Video Link
YouTube Link to demonstration of the functionality, features and expected behaviour of AutoJudge
