package com.team4.Evaluator.TestCase;

/**
 * An iterator for iterating over the TestCase objects belonging to a TestCollection object
 */
public class TestCollectionIterator implements AbstractTestCollectionIterator {

    // Attributes

    /** The TestCollection object to iterate over */
    private TestCollection testCollection;

    /** The index of the current AbstractTestCase object in the TestCollection */
    private int index;

    /**
     * Default constructor for a TestCollectionIterator object
     * @param testCollection The test collection to iterate over
     */
    public TestCollectionIterator(TestCollection testCollection) {
        this.testCollection = testCollection;
        this.index = 0;
    }


    // Interface Methods
    
    public AbstractTestCase next() {
        if (this.index >= this.testCollection.size()) return null;
        return this.testCollection.get(this.index++);
    }

    public boolean hasNext() {
        return this.index < this.testCollection.size();
    }
}
