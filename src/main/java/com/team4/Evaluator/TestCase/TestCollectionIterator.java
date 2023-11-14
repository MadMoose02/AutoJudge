package com.team4.Evaluator.TestCase;

public class TestCollectionIterator implements AbstractTestCollectionIterator {

    // Attributes
    private TestCollection testCollection;
    private int index;

    /**
     * Default constructor
     * @param testCollection The test collection to iterate over
     */
    public TestCollectionIterator(TestCollection testCollection) {
        this.testCollection = testCollection;
        this.index = 0;
    }


    // Methods
    
    @Override
    public AbstractTestCase next() {
        if (this.index >= this.testCollection.size()) return null;
        return this.testCollection.get(this.index++);
    }

    @Override
    public boolean hasNext() {
        return this.index < this.testCollection.size();
    }
}
