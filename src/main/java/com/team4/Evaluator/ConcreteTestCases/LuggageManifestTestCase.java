package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;

import com.team4.Evaluator.TestCase.TestCase;

public class LuggageManifestTestCase extends TestCase {

    public LuggageManifestTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){

        return false;
    }

    public boolean testAddLuggage(){

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
    
}
