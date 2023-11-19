package com.team4.Evaluator.TestCase;

public interface AbstractTestCollection {

    /**
     * Gets the size of the internal collection containing all the tests cases
     * @return The number of AbstractTestCase objects in this TestCollection
     */
    public int size();

    /**
     * Gets the AbstractTestCase object at the specified index
     * @param  index The index of the AbstractTestCase object to be returned
     * @return The AbstractTestCase object at the specified index
     */
    public AbstractTestCase get(int index);

    /**
     * Adds a TestCase to the internal collection of this TestCollection
     * @param testCase TestCase to be added
     */
    public void addTestCase(AbstractTestCase testCase);

    /**
     * Removes a TestCase from the internal collection of this TestCollection
     * @param testCase TestCase to be removed
     */
    public void removeTestCase(AbstractTestCase testCase);

    /**
     * Creates a new TestCollectionIterator object to iterate over the internal collection of this TestCollection
     * @return A new TestCollectionIterator object
     */
    public AbstractTestCollectionIterator getIterator();
}
