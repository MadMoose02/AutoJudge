package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;

import com.team4.Evaluator.TestCase.TestCase;

public class FlightTestCase extends TestCase {

    // private LuggageManifestTestCase luggage;

    public FlightTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){

        return false;
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
