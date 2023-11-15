package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class LuggageManifestTestCase extends TestCase {

    public LuggageManifestTestCase(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
    }

    public boolean testConstructor(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("new") && line.contains("ArrayList<LuggageSlip>")){
                    return true;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testAddLuggage(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("new") && line.contains("LuggageSlip")){
                    return true;
                };
                
                if (line.contains("slips.add")){
                    return true;
                };
                
                if (line.contains("") && line.contains("LuggageSlip")){
                    return true;
                };
                
                if (line.contains("new") && line.contains("LuggageSlip")){
                    return true;
                };
                
                if (line.contains("new") && line.contains("LuggageSlip")){
                    return true;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testGetExcessLuggage(){
        
        return false;
    }

    public boolean testGetExcessLuggageCost(){

        return false;
    }

    public boolean testToString(){

        return false;
    }

    @Override
    public boolean testCriteria() {
        if (testConstructor() && testAddLuggage() && testGetExcessLuggage() && testToString()){
            return true;
        }
        return false;
    }

    @Override
    public String getFeedbackComments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedbackComments'");
    }
    
}
