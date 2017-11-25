package fr.ulille1.fil.odeva;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class TestSuite {

    @RunWith(Suite.class)

    @Suite.SuiteClasses({
            MoneyMockitoTest.class,
            MoneyTestCase.class
    })

    public class JunitTestSuite {
    }

}