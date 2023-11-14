package com.team4.Evaluator.TestCase;

import java.util.ArrayList;

public class TestCollection implements AbstractTestCollection {

    private ArrayList<AbstractTestCase> testCases;
    private int index;

    public TestCollection() {
        this.testCases = new ArrayList<>();
        this.index = 0;
    }

    public void addTestCase(AbstractTestCase testCase) {
        this.testCases.add(testCase);
    }

    public void removeTestCase(AbstractTestCase testCase) {
        this.testCases.remove(testCase);
    }

    public AbstractTestCase next() {
        return (index < this.testCases.size()) ? this.testCases.get(index++) : null;
    }
}
