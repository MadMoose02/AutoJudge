package com.team4.Evaluator.TestCase;

public interface AbstractTestCollection {
    public int size();
    public void addTestCase(AbstractTestCase testCase);
    public void removeTestCase(AbstractTestCase testCase);
    public AbstractTestCollectionIterator getIterator();
}
