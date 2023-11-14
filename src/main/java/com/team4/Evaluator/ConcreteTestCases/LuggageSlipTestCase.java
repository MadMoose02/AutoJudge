package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;

import com.team4.Evaluator.TestCase.TestCase;

public class LuggageSlipTestCase extends TestCase {

    public LuggageSlipTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){

        return false;
    }

    public boolean testOverloadedConstructor(){

        return false;
    }

    public boolean testHasOwner(){
        
        return false;
    }

    public boolean testToString(){

        return false;
    }

    @Override
    public boolean testCriteria() {
        if(testConstructor() && testOverloadedConstructor() && testHasOwner() && testToString()){
            return true;
        }
        return false;
    }
    
}
