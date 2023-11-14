package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class FlightTestCase extends TestCase {

    // private LuggageManifestTestCase luggage;

    public FlightTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){
        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNext()) {

                String line = scan.nextLine();
                
                if (line.contains("this.flightNo") && line.contains("=")){
                    return false;
                };

                if (line.contains("this.destination") && line.contains("=")){
                    return false;
                };

                if (line.contains("this.origin") && line.contains("=")){
                    return false;
                };

                if (line.contains("this.flightDate") && line.contains("=")){
                    return false;
                };
                
            }
        }
        catch (FileNotFoundException e) {
            System.out.print("File Not Found");
        }

        return true;
    }

    public boolean testCheckInLuggage(){

        return false;
    }

    public boolean testPrintManifest(){
        
        return false;
    }

    public boolean testAllowedLuggage(){

        return false;
    }

    public boolean testToString(){

        return false;
    }

    @Override
    public boolean testCriteria() {
        if ((testConstructor() != false) && (testCheckInLuggage() != false) && (testPrintManifest() != false)
         && (testAllowedLuggage() != false) && (testToString() != false))
            return true;

        return false;
    }
    
}
