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
public class MoneyOps {
  // private static MoneyFactory mf = MoneyFactory.getDefaultFactory();

  private static MoneyFactory mf;

  public static void setMoneyFactory(MoneyFactory  injectedMF) {
    mf=injectedMF;
  }


  /**
   * Add moneys having the same currency
  */
  public static Money simpleAdd(Money m1, Money m2) throws IncompatibleCurrencyException, UnexistingCurrencyException, NonPositiveValueException
  {

    if (!Objects.equals(m1.getCurrency(), m2.getCurrency())) {
      throw new IncompatibleCurrencyException(m1.getCurrency(),m1.getCurrency());
    }

    Money m=mf.createMoney(m1.getValue()+m2.getValue(),m1.getCurrency());

    return m;
  }


  /**
   * Sub moneys having the same currency
   */
  public static Money simpleSub(Money m1, Money m2) throws IncompatibleCurrencyException, UnexistingCurrencyException, NonPositiveResultException,NonPositiveValueException{
    if (m1.getValue()-m2.getValue()<0) {
      throw new NonPositiveResultException(m1.getValue(),m2.getValue());
    }

    Money m=mf.createMoney(m1.getValue()-m2.getValue(),m1.getCurrency());
    return m;
  }


}
