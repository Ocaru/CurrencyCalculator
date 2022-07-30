package model;

import java.math.BigDecimal;

public class ExchangeRate {

	private String currencyName;
	private BigDecimal rate;

	public ExchangeRate() {
	}

	public ExchangeRate(String currency, BigDecimal rate) {
		this.currencyName = currency;
		this.rate = rate;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ExchangeRate [currencyName=" + currencyName + ", rate=" + rate
				+ "]";
	}

}
