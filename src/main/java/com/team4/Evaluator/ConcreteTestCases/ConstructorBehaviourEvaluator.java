package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class ConstructorBehaviourEvaluator extends TestCase {

    private String methodBody;
    private String evalClassName;
    private ArrayList<String> evalParameters;
    private ArrayList<String> instanceAttributes;
    private ArrayList<String> classAttributes;
    private StringBuilder feedbackCommentSB;
    private String failureMsg;
    private int numTestsPassed = 0;


    /**
     * Creates a test case to evaluate the constructor of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     */
    public ConstructorBehaviourEvaluator(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
        this.evalClassName = testFile.getName().substring(0, testFile.getName().indexOf("."));
        this.feedbackCommentSB = new StringBuilder();
    }

    /**
     * Creates a test case to evaluate the constructor of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     * @param instanceAttributes Expected attributes to be initialised by the constructor
     */
    public ConstructorBehaviourEvaluator(String testName, 
                                        File testFile, 
                                        String[] parameters, 
                                        String[] instanceAttributes) {
        super(testName, testFile, parameters);
        this.evalClassName = testFile.getName().substring(0, testFile.getName().indexOf("."));
        this.instanceAttributes = (instanceAttributes != null) ? new ArrayList<>(List.of(instanceAttributes)) : null;
        this.feedbackCommentSB = new StringBuilder();
    }

    /**
     * Creates a test case to evaluate the constructor of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     * @param instanceAttributes Expected attributes to be initialised by the constructor
     * @param classAttributes Class attributes that are not expected to be initialised by the constructor
     */
    public ConstructorBehaviourEvaluator(String testName, 
                                        File testFile, 
                                        String[] parameters, 
                                        String[] instanceAttributes,
                                        String[] classAttributes) {
        super(testName, testFile, parameters);
        this.evalClassName = testFile.getName().substring(0, testFile.getName().indexOf("."));
        this.instanceAttributes = (instanceAttributes != null) ? new ArrayList<>(List.of(instanceAttributes)) : null;
        this.classAttributes = (classAttributes != null) ? new ArrayList<>(List.of(classAttributes)) : null;
        this.feedbackCommentSB = new StringBuilder();
    }


    // Methods

    @Override
    public String getFeedbackComments() {
        return this.feedbackCommentSB.toString();    
    }

    private void extractConstructorBody() throws Exception {
        int iter = 0;
        String line = "";
        ArrayList<String> methodBodyLines = new ArrayList<>();
        Scanner scan;

        try {
            scan = new Scanner(this.testFile);

            // Skip lines until reached constructor
            while (scan.hasNextLine()) {
                if (++iter > MAX_LINES) break;
                line = scan.nextLine();
                if (!line.contains(this.evalClassName)) continue;

                // Check if constructor is present
                if (line.contains(this.evalClassName) && line.contains("(")) {
                    while (!line.contains("}")){
                        methodBodyLines.add(line);
                        line = scan.nextLine();
                    }
                    methodBodyLines.add(line);
                
                } else { continue; }

                // Concatenate method body lines
                for (String each : methodBodyLines) {
                    methodBody += each.trim();
                }
                scan.close();
                break;
            }

        } catch (Exception e) {
            System.out.println("Unable to extract constructor body from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }
    }

    
    private boolean checkConstructorParameters() {
        String methodParams = this.methodBody.substring(this.methodBody.indexOf("(") + 1, methodBody.indexOf(")"));
        String[] rawParamsList = methodParams.split(",");
        this.evalParameters = new ArrayList<>();
        for (String each : rawParamsList) 
            if ((each.length() > 1) && (!each.equals(" "))) this.evalParameters.add(each.trim());
        
        // If no parameters are expected, check if constructor has no parameters
        if ((this.parameters.isEmpty()) && (this.evalParameters.isEmpty())) return true;

        if ((this.parameters.isEmpty()) && (!this.evalParameters.isEmpty())) {
            this.failureMsg = "\n  Expected no parameters, but found: ";
            for (String each : this.evalParameters) this.failureMsg += each + "; ";
            return false;
        }

        // Check for expected parameters
        if (!this.evalParameters.containsAll(this.parameters)) {
            this.failureMsg = "\n  Missing parameters: ";
            for (String each : this.parameters) {
                if (!this.evalParameters.contains(each)) this.failureMsg += each + "; ";
            }
        }

        return this.evalParameters.containsAll(this.parameters);
    }


    private boolean checkAttributeAssignment() {
        String methodBody = this.methodBody.substring(this.methodBody.indexOf("{") + 1, this.methodBody.lastIndexOf("}"));
        String[] methodBodyLines = methodBody.split(";");
        ArrayList<String> attributeAssignments = new ArrayList<>(List.of(methodBodyLines));
        ArrayList<String> checkedAttributes = new ArrayList<>();
        
        // Remove lines that do not assign values to attributes (class variable manipulation)
        if (this.classAttributes != null) {
            for (String each: this.classAttributes) {
                attributeAssignments.removeIf(line -> line.contains(each.split(" ")[1]));
            }
        }
        for (String each: attributeAssignments) {
            if (!each.contains("=")) checkedAttributes.add(each);
        }
        attributeAssignments.removeAll(checkedAttributes);
        checkedAttributes.clear();

        // Enumerate attribute assignments from parameters
        for (String each : attributeAssignments) {
            String attrValue = each.substring(each.indexOf("=") + 1).trim();

            if (this.parameters.isEmpty()) continue;
            for (String param: this.parameters) {
                String paramName = param.split(" ")[1].trim();
                if (attrValue.equals(paramName)) {
                    checkedAttributes.add(each);
                    break;
                }
            }
        }
        for (String each : checkedAttributes) attributeAssignments.remove(each);
        checkedAttributes.clear();
        
        // For unchecked assignments
        for (String each : attributeAssignments) {
            String attrValue = each.substring(each.indexOf("=") + 1).trim();

            // Skip final and random assignments
            if (each.contains("final") || each.matches("rand")) {
                checkedAttributes.add(each);
                continue;
            }
            
            if (this.instanceAttributes == null) continue;
            for (String attr: this.instanceAttributes) {
                String attrType = attr.split(" ")[0].trim();

                // If attr has a Type<T> declaration
                if (attrType.contains("<")) attrType = attrType.substring(0, attr.indexOf("<")).trim();
                
                if (attrValue.contains("new") && attrValue.contains(attrType)) {
                    checkedAttributes.add(each);
                    break;
                }
                
                if (attrType.equals("String")) {
                    if (attrValue.contains("\"") || attrValue.contains("'")) {
                        checkedAttributes.add(each);
                        break;
                    }
                }

                if (attrType.equals("int") || attrType.equals("double") || attrType.equals("float")) {
                    if (attrValue.matches("[0-9]+") || attrValue.contains("rand")) {
                        checkedAttributes.add(each);
                        break;
                    }
                }
                
                if (attrType.equals("boolean")) {
                    if (attrValue.equals("true") || attrValue.equals("false")) {
                        checkedAttributes.add(each);
                        break;
                    }
                }
            }
        }
        for (String each : checkedAttributes) attributeAssignments.remove(each);
        if (!attributeAssignments.isEmpty()) {
            this.failureMsg = "\n  Unrecognised attribute assignments: ";
            for (String each : attributeAssignments) this.failureMsg += each + "; ";
        }

        return attributeAssignments.isEmpty();
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;
        extractConstructorBody();

        // Sub test case 1: Check if constructor has correct parameters
        status = this.checkConstructorParameters();
        this.feedbackCommentSB.append("\nParameter check: " + (status ? "Passed" : "Failed") + "\n");
        if (!status) {
            this.feedbackCommentSB.append("\n- Constructor does not have correct parameters");
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;
        
        // Sub test case 2: Check if constructor assigns correct values to attributes
        ArrayList<String> expectedAttributes = (this.parameters != null) ? new ArrayList<>(this.parameters) : new ArrayList<>();
        if (this.instanceAttributes != null) expectedAttributes.addAll(this.instanceAttributes);
        status = this.checkAttributeAssignment();
        this.feedbackCommentSB.append("Assignment check: " + (status ? "Passed" : "Failed") + "\n");
        if (!status) {
            this.feedbackCommentSB.append("\n- Constructor does not assign correct values to attributes.");
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;

        return (this.numTestsPassed == 2);
    }
}
