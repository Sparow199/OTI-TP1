/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.ulille1.fil.odeva;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marius
 */
public class MoneyFactory {
  private static MoneyFactory defaultInstance;
  private static Map<String,Float> defaultCurrencies;
  private Map<String,Float> currencies;
  
  static {
    defaultCurrencies=new HashMap<>();
		defaultCurrencies.put("EUR",new Float(1.));
		defaultCurrencies.put("CHF",new Float(1/1.2));

    defaultInstance = new MoneyFactory();
  }

  private MoneyFactory() {
    this(defaultCurrencies);
  }

  private MoneyFactory(Map<String,Float> currencies) {
    this.currencies=new HashMap<>();
    this.currencies.putAll(currencies);
  }
  
  public static MoneyFactory  getDefaultFactory() {
    return defaultInstance;
  }

  public Money createMoney(int value, String currency) throws UnexistingCurrencyException,NonPositiveValueException
  {


    if(!defaultCurrencies.containsKey(currency.toUpperCase())){
      throw new UnexistingCurrencyException(currency);
    }

    if(value<0){
      throw new NonPositiveValueException(value);
    }

    return new Money(value,currency);
  }

}
