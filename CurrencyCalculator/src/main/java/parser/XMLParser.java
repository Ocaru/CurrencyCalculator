package parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import model.ExchangeRate;

import javax.xml.parsers.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

	private List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
	private String date;

	public void parse() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		NodeList nodeList;

		try {
			builder = factory.newDocumentBuilder();
			InputStream inputStream = getClass()
					.getResourceAsStream("/eurofxref-daily.xml");
			document = builder.parse(inputStream);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		nodeList = document.getElementsByTagName("Cube");
		addExchangeRatesToList(nodeList);
		setDate(nodeList);
	}

	private void addExchangeRatesToList(NodeList nodeList) {
		Element element;
		ExchangeRate exchangeRate;

		for (int i = 2; i < nodeList.getLength(); i++) {
			element = (Element) nodeList.item(i);
			exchangeRate = new ExchangeRate();
			exchangeRate.setCurrencyName(element.getAttribute("currency"));
			exchangeRate.setRate(new BigDecimal(element.getAttribute("rate")));
			exchangeRateList.add(exchangeRate);
		}
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	private void setDate(NodeList nodeList) {
		Element element = (Element) nodeList.item(1);
		date = element.getAttribute("time");
	}

	public String getDate() {
		return date;
	}

}
