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
  private static MoneyFactory _defaultInstance;
  private static Map<String,Float> _defaultCurrencies;
  private Map<String,Float> currencies;
  
  static {
    _defaultCurrencies=new HashMap<String,Float>();
		_defaultCurrencies.put("EUR",new Float(1.));
		_defaultCurrencies.put("CHF",new Float(1/1.2));

    _defaultInstance = new MoneyFactory();
  }

  private MoneyFactory() {
    this(_defaultCurrencies);
  }

  private MoneyFactory(Map<String,Float> currencies) {
    this.currencies=new HashMap<String,Float>();
    this.currencies.putAll(currencies);
  }
  
  public static MoneyFactory  getDefaultFactory() {
    return _defaultInstance;
  }

  public Money createMoney(int value, String currency) throws UnexistingCurrencyException,NonPositiveValueException
  {


    if(!_defaultCurrencies.containsKey(currency.toUpperCase())){
      throw new UnexistingCurrencyException(currency);
    }

    if(value<0){
      throw new NonPositiveValueException(value);
    }

    return new Money(value,currency);
  }

}
