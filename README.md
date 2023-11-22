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
| Facade             | - [AutoJudge](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/AutoJudge.java)
| Composite          | - [SyntaxEvaluator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/SyntaxEvaluator.java) (interface) <br>- [Evaluator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/Evaluator.java) (composite)<br>- [BehaviourEvaluator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/BehaviourEvaluator.java) (leaf)<br>- [HierarchyEvaluator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/HierarchyEvaluator.java) (leaf)<br>- [ConventionsEvaluator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/ConventionsEvaluator.java) (leaf)
| Template           | - [AbstractTestCase](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/TestCase/AbstractTestCase.java)
| Iterator           | - [AbstractTestCollectionIterator](https://github.com/MadMoose02/AutoJudge/blob/main/src/main/java/com/team4/Evaluator/TestCase/AbstractTestCollectionIterator.java)

## Benefits of Design Patterns Used

- ### Facade
    - **Complexity Masking**<br>
        Manifested implicitly through the AutoJudge and SyntaxEvaluator interfaces, which acts as unified fronts for orchestrating and coordinating the evaluation of distinct aspects code in Java documents.
        - The AutoJudge interface provides three main focal methods that emulate the primary checkpoints during evaluation: `evaluateSubmissions()`, `generatePDFReport()` and `displayEvaluationResults()`.
        - The SyntaxEvaluator interface encapsulates the complexity of submission evaluation by aggregating specialised evaluators and offering a simplified client interface `evaluate(File javaDocument)`, shielding clients from the intricacies of individual evaluators.
        This design pattern promotes modularity, ease of use, and clear abstraction of the underlying complexities that comprise the AutoJudge system. 

- ### Composite
    - **Hierarchy Coordination**<br>
        The Evaluator class acts as a composite, coordinating different types of evaluators (Hierarchy, Conventions & Behaviour). This pattern allows treating individual evaluators uniformly.
    - **Simplified Client Code**<br>
        Clients interact with a unified interface `evaluate(File javaDocument)`, making the client code more straightforward. Clients don't need to understand the internal details of an individual evaluatorm which would have their own implementation for evaluating a submission file.
    - **Encapsulation of Complexity**<br>
        The internal details of each evaluator are encapsulated within their respective classes. The Evaluator shields the client from the complexity of the evaluation process and simply allows for operating over multiple sub-components through a single method.

- ### Template
    - **Algorithm Skeleton**<br>
        The AbstractTestCase interface provides a template method `testCriteria()`, that defines the skeleton of an algorithm for constructing a test cases. Concrete subclasses provide specific implementations, allowing re-use of the common structure.
    - **Consistency Across Subclasses**<br>
        Ensures that the evaluation process follows a consistent structure across different types of test cases. This helps maintain a common interface while allowing variations in specific steps.

- ### Iterator
    - **Sequential Access**<br>
        If there were collections or structures to iterate over, an iterator provides a consistent way to access elements sequentially without exposing underlying details.
    - **Uniform Interface**<br>
        Provides a uniform interface for traversing different types of collections or structures. This simplifies client code, making it independent of the specific structure being traversed.
    - **Encapsulation of Iteration Logic**<br>
        The iterator pattern encapsulates the logic for iterating over elements within the iterator object. This allows changing the traversal algorithm without affecting the client code.


## Conformance to SOLID
This project conforms to the SOLID principles as follows:<br>
| **Principle** | **Application** |
| ------------- | --------------- |
| **S** | Single Responsibility Principle of each class is enforced. Delegations are made where necessary |
| **O** | Open/Closed Priciple of all modules is adhered to, whereby all are open to extension but closed to modification |
| **L** | Liskov Substitution Principle is complied with to ensure that sub-modules of a major module can be interchanged |
| **I** | Interface Segregation Principle is maximally observed to ensure that no module is required to use code that it does not need |
| **D** | Dependency Inversion Principle is obeyed such that every module is loosely-coupled and depends on abstractions |

## Class Diagram
The following is an overview of the UML class diagram for AutoJudge<br>
![AutoJudge UML Class Diagram](https://raw.githubusercontent.com/MadMoose02/AutoJudge/main/Team4_AutoJudge_UML_bkg.svg)<hr>


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
