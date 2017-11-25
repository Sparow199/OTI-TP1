/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ulille1.fil.odeva;

import java.util.Objects;

/**
 *
 * @author marius
 */
public final class MoneyOps {

  private MoneyOps() {
    throw new IllegalStateException("MoneyOps class");
  }

  private static MoneyFactory mf;

  public static void setMoneyFactory(MoneyFactory injectedMF) {
    mf = injectedMF;
  }


  /**
   * Add moneys having the same currency
   */
  public static Money simpleAdd(Money m1, Money m2) throws UnexistingCurrencyException, NonPositiveValueException {

    if (!Objects.equals(m1.getCurrency(), m2.getCurrency())) {
      throw new IncompatibleCurrencyException(m1.getCurrency(), m1.getCurrency());
    }

    return mf.createMoney(m1.getValue() + m2.getValue(), m1.getCurrency());
  }


  /**
   * Sub moneys having the same currency
   */
  public static Money simpleSub(Money m1, Money m2) throws UnexistingCurrencyException, NonPositiveResultException, NonPositiveValueException {
    if (m1.getValue() - m2.getValue() < 0) {
      throw new NonPositiveResultException(m1.getValue(), m2.getValue());
    }

    return mf.createMoney(m1.getValue() - m2.getValue(), m1.getCurrency());

  }
}