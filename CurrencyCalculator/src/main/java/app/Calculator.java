package app;

import java.math.BigDecimal;
import java.util.List;

import model.ExchangeRate;

public class Calculator {

	private BigDecimal amount;
	private BigDecimal rate;

	public BigDecimal calculateAmount(String amountString, String currencyName,
			List<ExchangeRate> exchangeRateList) {
		if (!checkAmountValue(amountString)) {
			return new BigDecimal(-1);
		}
		prepareValues(amountString, currencyName, exchangeRateList);
		return amount.multiply(rate);
	}

	private void prepareValues(String amountString, String currencyName,
			List<ExchangeRate> exchangeRateList) {
		amount = new BigDecimal(amountString);

		for (ExchangeRate er : exchangeRateList) {
			if (er.getCurrencyName().equals(currencyName)) {
				rate = er.getRate();
				break;
			}
		}
	}

	private boolean checkAmountValue(String amountString) {
		String regex = "^[0-9]+(\\.[0-9]+)?$";
		return amountString.matches(regex);
	}

	public BigDecimal getRate() {
		return rate;
	}

}
