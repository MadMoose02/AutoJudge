package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class FlightTestCase extends TestCase {

    // private LuggageManifestTestCase luggage;

    public FlightTestCase(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
    }

    public boolean testConstructor(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if ((line.contains("this.flightNo") || line.contains("flightNo")) && line.contains("=") && line.contains("flightNo")){
                    return true;
                };

                if ((line.contains("this.destination") || line.contains("destination")) && line.contains("=") && line.contains("destination")){
                    return true;
                };

                if ((line.contains("this.origin") || line.contains("origin")) && line.contains("=") && line.contains("origin")){
                    return true;
                };

                if ((line.contains("this.flightDate") || line.contains("flightDate")) && line.contains("=") && line.contains("flightDate")){
                    return true;
                };

                if (line.contains("LuggageManifest") && line.contains("new")){
                    return true;
                }
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testCheckInLuggage(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("if") && line.contains("(flightNo == p.flightNo)")){
                    return true;
                };

                if (line.contains("addLuggage")){
                    return true;
                };

                if (line.contains("System.out.println")){
                    return true;
                };

                if (line.contains("else")){
                    return true;
                };

                if (line.contains("Invalid flight")){
                    return true;
                }
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testPrintManifest(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("System.out.print") && line.contains(".toString()")){
                    return true;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testAllowedLuggage(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("if") && line.contains("(flightNo == p.flightNo)")){
                    return true;
                };

                if (line.contains("addLuggage")){
                    return true;
                };

                if (line.contains("System.out.println")){
                    return true;
                };

                if (line.contains("else")){
                    return true;
                };

                if (line.contains("Invalid flight")){
                    return true;
                }
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    public boolean testToString(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("System.out.print")||line.contains("System.out.println")){
                    return true;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return false;
    }

    @Override
    public boolean testCriteria() {
        if ((testConstructor() != false) && (testCheckInLuggage() != false) && (testPrintManifest() != false)
         && (testAllowedLuggage() != false) && (testToString() != false))
            return true;

        return false;
    }

    @Override
    public String getFeedbackComments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedbackComments'");
    }
    
}
