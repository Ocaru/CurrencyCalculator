package app;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.ExchangeRate;
import parser.XMLParser;


public class CalculatorTest {

	static Calculator calculator;
	static XMLParser xmlParser = new XMLParser();
	static String currencyName = "PLN";
	static List<ExchangeRate> exchangeRateList = xmlParser.getExchangeRateList();
	
	@BeforeAll
	public static void setUp()
	{
		calculator = new Calculator();
		xmlParser.parse();
		currencyName = "PLN";
		exchangeRateList = xmlParser.getExchangeRateList();
	}
	
	@Test
	public void calculateAmount_whenAmountIsCorrect_thenReturnBigDecimal()
	{
		//given
		String amountString = "1";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		BigDecimal rate = calculator.getRate();
		
		//then
		Assertions.assertEquals(result, new BigDecimal("1").multiply(rate));

	}
	
	@Test
	public void calculateAmount_whenAmountIsVeryBig_thenReturnBigDecimal()
	{
		//given
		String amountString = "1000000000";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		BigDecimal rate = calculator.getRate();
		
		//then
		Assertions.assertEquals(result, new BigDecimal("1000000000").multiply(rate));

	}
	
	@Test
	public void calculateAmount_whenAmountIsVerySmall_thenReturnBigDecimal()
	{
		//given
		String amountString = "0.000000001";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		BigDecimal rate = calculator.getRate();
		
		//then
		Assertions.assertEquals(result, new BigDecimal("0.000000001").multiply(rate));

	}
	
	
	@Test
	public void calculateAmount_whenAmountStringEmpty_thenReturnMinusOne()
	{
		//given
		String amountString = "";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal(-1));

	}
	
	@Test
	public void calculateAmount_whenAmountIsZero_thenReturnZero()
	{
		//given
		String amountString = "0";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("0.0000"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsNegative_thenReturnMinusOne()
	{
		//given
		String amountString = "-1";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsNegative_thenReturnMinusOne2()
	{
		//given
		String amountString = "-10000";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne()
	{
		//given
		String amountString = ".1";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne2()
	{
		//given
		String amountString = "a.1";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne3()
	{
		//given
		String amountString = "1.a";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}

	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne4()
	{
		//given
		String amountString = "1..8";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne5()
	{
		//given
		String amountString = "1a.8";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne6()
	{
		//given
		String amountString = "1.a8";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne7()
	{
		//given
		String amountString = "a1.8";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
	@Test
	public void calculateAmount_whenAmountIsIncorrect_thenReturnMinusOne8()
	{
		//given
		String amountString = "1.8a";
		
		//when
		BigDecimal result = calculator.calculateAmount(amountString, currencyName, exchangeRateList);
		
		//then
		Assertions.assertEquals(result, new BigDecimal("-1"));

	}
	
}
