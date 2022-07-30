package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.Calculator;
import model.ExchangeRate;
import parser.XMLParser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;

public class UserInterface {

	private JFrame frame;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private Calculator calculator = new Calculator();
	private XMLParser parser;

	public UserInterface(XMLParser parser) {
		this.parser = parser;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Currency calculator");
		frame.setBounds(100, 100, 512, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Currency calculator");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setBounds(0, 0, 496, 42);
		frame.getContentPane().add(titleLabel);

		JTextField textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(75, 65, 100, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel eurLabel = new JLabel("EUR");
		eurLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		eurLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eurLabel.setBounds(175, 63, 30, 24);
		frame.getContentPane().add(eurLabel);

		JLabel targetLabel = new JLabel("Target currency");
		targetLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		targetLabel.setBounds(321, 50, 100, 15);
		frame.getContentPane().add(targetLabel);

		JLabel arrowLabel = new JLabel("");
		arrowLabel.setIcon(new ImageIcon(
				getClass().getResource("/blueArrows.png")));
		arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrowLabel.setBounds(218, 62, 61, 25);
		frame.getContentPane().add(arrowLabel);

		JLabel amountLabel = new JLabel("Euro amount");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amountLabel.setBounds(75, 50, 100, 15);
		frame.getContentPane().add(amountLabel);

		JButton convertButton = new JButton("Convert");
		convertButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		convertButton.setBounds(203, 100, 90, 23);
		frame.getContentPane().add(convertButton);

		JLabel resultLabel = new JLabel("");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		resultLabel.setBounds(10, 135, 476, 20);
		frame.getContentPane().add(resultLabel);

		JLabel resultLabel2 = new JLabel("");
		resultLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		resultLabel2.setBounds(10, 155, 476, 20);
		frame.getContentPane().add(resultLabel2);

		JLabel resultLabel3 = new JLabel("");
		resultLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		resultLabel3.setBounds(10, 175, 476, 20);
		frame.getContentPane().add(resultLabel3);

		assingValueToList();
		comboBox.setBounds(321, 65, 100, 20);
		frame.getContentPane().add(comboBox);

		JLabel invalidLabel = new JLabel("Invalid value");
		invalidLabel.setForeground(Color.RED);
		invalidLabel.setBounds(75, 85, 100, 15);
		invalidLabel.setVisible(false);
		frame.getContentPane().add(invalidLabel);

		convertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String amountString = textField.getText();
				String currencyName = (String) comboBox.getSelectedItem();
				BigDecimal resultAmount = calculator.calculateAmount(
						amountString, currencyName,
						parser.getExchangeRateList());

				if (resultAmount.equals(new BigDecimal(-1))) {
					invalidLabel.setVisible(true);
				} else {
					invalidLabel.setVisible(false);
					resultLabel.setText(amountString + " EUR = " + resultAmount
							+ " " + currencyName);
					resultLabel2.setText("1 EUR = " + calculator.getRate() + " "
							+ currencyName + ", ");
					resultLabel3.setText(
							"based on the average exchange rate of the European Central Bank, of "
									+ parser.getDate() + ".");
				}
			}
		});
	}

	private void assingValueToList() {
		for (ExchangeRate er : parser.getExchangeRateList()) {
			comboBox.addItem(er.getCurrencyName());
		}
	}

}
