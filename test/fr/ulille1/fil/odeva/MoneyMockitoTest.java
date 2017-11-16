package fr.ulille1.fil.odeva;

import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

/*
 * Unit test for simple App.
 */
public class MoneyMockitoTest {

    private Money real_M1EUR = new Money(1,"EUR");
    private Money real_M2EUR = new Money(2,"EUR");

    private Money mock_M1EUR = mock(Money.class);
    private Money mock_M2EUR = mock(Money.class);

    private Money spy_M1EUR = spy(real_M1EUR);
    private Money spy_M2EUR = spy(real_M2EUR);




    @Before
    public void init() throws UnexistingCurrencyException {
        when(mock_M1EUR.getValue()).thenReturn(1);
        when(mock_M1EUR.getCurrency()).thenReturn("EUR");
        doReturn(1).when(mock_M1EUR).getValue();
        doReturn("EUR").when(mock_M1EUR).getCurrency();

        when(mock_M2EUR.getValue()).thenReturn(2);
        when(mock_M2EUR.getCurrency()).thenReturn("EUR");
        doReturn(2).when(mock_M2EUR).getValue();
        doReturn("EUR").when(mock_M2EUR).getCurrency();

        when(spy_M1EUR.getValue()).thenReturn(1);
        when(spy_M1EUR.getCurrency()).thenReturn("EUR");
        doReturn(1).when(spy_M1EUR).getValue();
        doReturn("EUR").when(spy_M1EUR).getCurrency();

        when(spy_M2EUR.getValue()).thenReturn(2);
        when(spy_M2EUR.getCurrency()).thenReturn("EUR");
        doReturn(2).when(spy_M2EUR).getValue();
        doReturn("EUR").when(spy_M2EUR).getCurrency();


    }


    /**
     * test equal
     */
    @Test
    public void testEqual() throws UnexistingCurrencyException,NonPositiveValueException {
        assertTrue(spy_M1EUR.equals(spy_M1EUR));
        assertFalse(spy_M1EUR.equals(spy_M2EUR));
        assertFalse(spy_M1EUR.equals(mock_M1EUR));
        assertFalse(spy_M1EUR.equals(real_M1EUR));
        assertFalse(mock_M1EUR.equals(real_M1EUR));
        assertTrue(real_M1EUR.equals(real_M1EUR));
        assertFalse(real_M1EUR.equals(real_M2EUR));
    }


    /**
     * test MoneyAdd
     */
    @Test
    public void testMoneyAdd() throws UnexistingCurrencyException,NonPositiveValueException {

        MoneyFactory mockFactory = mock(MoneyFactory.class);
        when(mockFactory.createMoney(anyInt(),anyString())).thenCallRealMethod();
        Money expected = mockFactory.createMoney(3, "EUR");

        Money realResult = MoneyOps.simpleAdd(real_M1EUR,real_M2EUR);
        assertEquals(expected,realResult);

        Money mockResult = MoneyOps.simpleAdd(mock_M1EUR,mock_M2EUR);
        assertEquals(expected,mockResult);

        Money spyResult = MoneyOps.simpleAdd(spy_M1EUR,spy_M2EUR);
        assertEquals(expected,spyResult);

        Money realmockResult = MoneyOps.simpleAdd(real_M1EUR,mock_M2EUR);
        assertEquals(expected,realmockResult);

        Money realspyResult = MoneyOps.simpleAdd(real_M1EUR,spy_M2EUR);
        assertEquals(expected,realspyResult);

        Money mockspyResult = MoneyOps.simpleAdd(mock_M1EUR,spy_M2EUR);
        assertEquals(expected,mockspyResult);

    }


    /**
     * test MoneySub
     */
    @Test(expected=NonPositiveValueException.class)
    public void testMoneySub() throws UnexistingCurrencyException, NonPositiveResultException,NonPositiveValueException {

        MoneyFactory mockFactory = mock(MoneyFactory.class);
        when(mockFactory.createMoney(anyInt(),anyString())).thenCallRealMethod();
        Money expected = mockFactory.createMoney(-1, "EUR");

        Money realResult = MoneyOps.simpleSub(real_M1EUR,real_M2EUR);
        assertEquals(expected,realResult);

        Money mockResult = MoneyOps.simpleSub(mock_M1EUR,mock_M2EUR);
        assertEquals(expected,mockResult);

        Money spyResult = MoneyOps.simpleSub(spy_M1EUR,spy_M2EUR);
        assertEquals(expected,spyResult);

        Money realmockResult = MoneyOps.simpleSub(real_M1EUR,mock_M2EUR);
        assertEquals(expected,realmockResult);

        Money realspyResult = MoneyOps.simpleSub(real_M1EUR,spy_M2EUR);
        assertEquals(expected,realspyResult);

        Money mockspyResult = MoneyOps.simpleSub(mock_M1EUR,spy_M2EUR);
        assertEquals(expected,mockspyResult);

    }




    /**
     * test MoneyFactory
     */
    @Test
    public void testMoneyFactory() throws UnexistingCurrencyException,NonPositiveValueException {

        MoneyFactory mockFactory = mock(MoneyFactory.class);
        when(mockFactory.createMoney(anyInt(),anyString())).thenCallRealMethod();
        mockFactory.createMoney(3, "EUR");

        verify(mockFactory).createMoney(3, "EUR");
        verify(mockFactory, atLeastOnce()).createMoney(3, "EUR");
        verify(mockFactory, times(1)).createMoney(3, "EUR");
        verify(mockFactory, atMost(1)).createMoney(3, "EUR");

    }




}
