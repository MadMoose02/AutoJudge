package com.team4.Evaluator.TestCase;

import java.util.ArrayList;

/**
 * A collection of TestCase objects
 */
public class TestCollection implements AbstractTestCollection {

    // Attributes

    /** Collection of all the TestCase objects for this TestCollection */
    private ArrayList<AbstractTestCase> testCases;

    /**
     * Default constructor
     */
    public TestCollection() {
        this.testCases = new ArrayList<>();
    }

    
    // Interface Methods

    public int size() { return this.testCases.size(); }
    public AbstractTestCase get(int index) { return this.testCases.get(index); }
    public void addTestCase(AbstractTestCase testCase) { this.testCases.add(testCase); }
    public void removeTestCase(AbstractTestCase testCase) { this.testCases.remove(testCase); }
    public AbstractTestCollectionIterator getIterator() { return new TestCollectionIterator(this); }
}
