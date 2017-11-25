package fr.ulille1.fil.odeva;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Unit test for simple App.
 */
public class MoneyTestCase {
    private Money f12EUR,  f14EUR;
    private MoneyFactory mf;
    
    @Before
    public void init() throws UnexistingCurrencyException,NonPositiveValueException {
      mf=MoneyFactory.getDefaultFactory();
      MoneyOps.setMoneyFactory(mf);
      f12EUR=mf.createMoney(12, "eur");
      f14EUR=mf.createMoney(14, "EUR");
    }


    /**
     * createMoneyCurrencyFail
     */
    @Test(expected=UnexistingCurrencyException.class)
    public void createMoneyCurrencyFailTest() throws UnexistingCurrencyException,NonPositiveValueException {
        mf.createMoney(1, "DZD");
    }

    /**
     * createMoneyValueFail
     */
    @Test(expected=NonPositiveValueException.class)
    public void createMoneyValueFailTest() throws UnexistingCurrencyException,NonPositiveValueException {
        mf.createMoney(-1, "EUR");
    }

    /**
     * simpleAdd
     */
    @Test
    public void simpleAddTest() throws UnexistingCurrencyException,NonPositiveValueException {
        Money expected=mf.createMoney(26, "EUR");
        Money result=MoneyOps.simpleAdd(f12EUR,f14EUR);
        assertEquals(expected.getCurrency(), result.getCurrency());
        assertEquals(expected.getValue(), result.getValue());
    }

    /**
     * simpleAddFail
     */
    @Test(expected= IncompatibleCurrencyException.class)
    public void simpleAddFailTest() throws UnexistingCurrencyException,NonPositiveValueException {
        Money f26CHF=mf.createMoney(26, "CHF");
        MoneyOps.simpleAdd(f12EUR, f26CHF);
    }

    /**
     * simpleSub
     */
    @Test
    public void simpleSubTest() throws UnexistingCurrencyException, NonPositiveResultException,NonPositiveValueException {
        Money expected=mf.createMoney(2, "EUR");
        Money result=MoneyOps.simpleSub(f14EUR,f12EUR);
        assertEquals(expected,result);
    }

    /**
     * simpleSubFail
     */
    @Test(expected=NonPositiveResultException.class)
    public void simpleSubFailTest() throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException{
        mf.createMoney(2, "EUR");
        MoneyOps.simpleSub(f12EUR,f14EUR);
    }

    /**
     * simpleBonus100%CodeCoverage
     */
    @Test
    public void simpleSubHashCodeTest() throws UnexistingCurrencyException, NonPositiveValueException {
        Money n = null;
        Money m = new Money(3, null);
        Money x = new Money(2, "EUR");
        Money y = mf.createMoney(2, "EUR");
        assertFalse(x.equals(mf));
        assertFalse(x.equals(n));
        assertTrue(x.equals(x));
        assertTrue(x.toString().equals(y.toString()));
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
        assertFalse(x.hashCode() == m.hashCode());
    }
}
