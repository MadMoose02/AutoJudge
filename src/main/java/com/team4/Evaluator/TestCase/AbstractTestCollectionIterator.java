package com.team4.Evaluator.TestCase;

public interface AbstractTestCollectionIterator {

    /**
     * Returns the next AbstractTestCase object in the internal Collection of TestCase objects
     * @return The next AbstractTestCase object or {@code null} if reached the end of the collection
     */
    public AbstractTestCase next();

    /**
     * Checks if there is another AbstractTestCase object in the internal Collection of TestCase objects
     * @return {@code True} if there is another AbstractTestCase object, {@code False} otherwise
     */
    public boolean hasNext();
}
