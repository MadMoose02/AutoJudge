package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;

import com.team4.Evaluator.TestCase.TestCase;

public class PassengerTestCase extends TestCase{

    public PassengerTestCase(String testName, File testFile) {
        super(testName, testFile);
    }

    public boolean testConstructor(){

        return false;
    }

    public boolean testAssignRandomCabin(){

        return false;
    }

    public boolean testToString(){

        return false;
    }

    @Override
    public boolean testCriteria() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCriteria'");
    }
    
}
