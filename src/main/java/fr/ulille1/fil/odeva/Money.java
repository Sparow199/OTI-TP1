package  fr.ulille1.fil.odeva;


public class Money {
	private int value;
	private String currency;
	

	Money(int value, String currency)
	{
		this.value=value;
		this.currency=currency;
	}

	public int getValue()
	{
		return this.value;
	}

	public String getCurrency()
	{
		return this.currency.toUpperCase();
	}

    public String toString() {
     	return this.getValue()+" ("+this.getCurrency()+")";
    }

	@Override
	public int hashCode() {
		int result = value;
		return 31 * result + (currency != null ? currency.hashCode() : 0);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || (object.getClass() != getClass())) {
			return false;
		}
		Money other = (Money) object;
		return this.getValue() == other.getValue() && this.getCurrency().equalsIgnoreCase(other.getCurrency());
	}




}
