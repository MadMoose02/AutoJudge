package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;
/**
 * A concrete subclass of the TestCase class that evaluates the association of a class.
 * It contains a constructor which initialises attributes based on given parameters 
 * One of the attributes in the constructor is initialized via the extraction of the  filename.
 * The constructor also initializes an attribute as a StringBuilder.
 * This class has a unique method which determines whether or not an association exists between specific classes.
 * It overrides a class that was inherited by TestCase. 
 */
public class AssociationHierarchyEvaluator extends TestCase {
    /** Name of the class in which the association is being made to */
    private String evalClassName;

    /** Feedback comments generated for the submission File by the StringBuilder */
    private StringBuilder feedbackCommentSB;

    /** Failure message that is displayed if the test case fails */
    private String failureMsg;

    /** Number of test cases passed */
    private int numTestsPassed = 0;
    
    /** Object type that indicates the class in which the association is from */
    private String associationType;


    /**
     * Creates a test case to evaluate the association of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     */
    public AssociationHierarchyEvaluator(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
        this.evalClassName = testFile.getName().substring(0, testFile.getName().indexOf("."));
        this.feedbackCommentSB = new StringBuilder();
        this.associationType = parameters[0];
    }


    // Methods
    /**
     * Checks if the specified file contains an association based on predefined criteria. 
     * The method reads the file until it finds lines that match the specified association conditions.
     * @return {@code true} if an association is found according to the predefined criteria, {@code false} otherwise.
     * @throws Exception if an error occurs during file reading.
     * The detailed error information is printed to the console.
     */
    private boolean associationCheck() throws Exception {
        int iter = 0;
        String line = "";
        Scanner scan;

        try {
            scan = new Scanner(this.testFile);

            // Skip lines until reached class
            while (scan.hasNextLine()) {
                if (++iter > MAX_LINES) break;
                line = scan.nextLine();

                // Checks if the specified file contains the required association 
                if (!line.contains(".") && this.evalClassName.equals("LuggageManagementSystem") && line.contains(this.associationType)) {
                    scan.close();
                    return true;
                } else if (!line.contains(".") && this.evalClassName.equals("LuggageManagementSystem") && line.contains(this.associationType)){  
                    scan.close();
                    return true;
                } else if (!line.contains(".") && line.contains(this.associationType) && this.evalClassName.equals("LuggageManifest")) {
                    scan.close();
                    return true;
                } else { continue; }
            }

        } catch (Exception e) {
            System.out.println("Unable to extract attributes from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }

        this.failureMsg = "No association found ";
        return false;
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;

        //Feedback comments produced based on result of the association check
        if (!(status = this.associationCheck())) {
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;

        this.feedbackCommentSB.append("Association check: " + (status ? "Passed" : "Failed") + "\n");
        this.feedbackComments = this.feedbackCommentSB.toString();
        return (this.numTestsPassed == 1);
    }
}
