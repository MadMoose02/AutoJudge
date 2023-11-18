package com.team4.Evaluator.TestCase;

public interface AbstractTestCollection {

    /**
     * Gets the size of the internal collection containing all the tests cases
     * @return Integer size of the collection
     */
    public int size();

    /**
     * Adds a new AbstactTestCase to the internal collection of this TestCollection
     * @param testCase AbstractTestCase to be added
     */
    public void addTestCase(AbstractTestCase testCase);
    public void removeTestCase(AbstractTestCase testCase);
    public AbstractTestCollectionIterator getIterator();
}
