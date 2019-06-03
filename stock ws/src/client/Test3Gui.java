package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceException;

import core.ws.StockQuote;
import core.ws.StockQuoteSoap;
import stock.bind.Stock;
import stock.bind.StockQuotes;

/*
 * compare results to stocks web site in link:
 * 
 * http://eoddata.com/symbols.aspx
 * */

public class Test3Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btShow = new JButton("Show Stock Data");
	private JTextField tfStockSymbol = new JTextField(10);
	private String[][] rowData = {};
	private String[] columnNames = { "Key", "Value" };
	private JTable table = new JTable(rowData, columnNames);

	{
	}

	public static void main(String[] args) {
		Test3Gui gui = new Test3Gui();
		gui.createAndShowGui();
		gui.setVisible(true);
	}

	public void createAndShowGui() {
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pn = new JPanel();
		pn.setBackground(new Color(0xF4A460));
		this.add(pn, BorderLayout.CENTER);

		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);

		JLabel lb = new JLabel("Enter Stock Symbol");
		lb.setToolTipText(
				"stock symbol is an abbreviation used to uniquely identify publicly traded shares of a particular stock on a particular stock market. A stock symbol may consist of letters, numbers or a combination of both.");
		lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pn1.add(lb);
		tfStockSymbol.setText("KO");
		pn2.add(tfStockSymbol);
		pn3.add(btShow);
		btShow.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450, 250));
		pn4.add(scrollPane);

		JLabel lbTitle = new JLabel("Stock Quotes Basic");
		lbTitle.setFont(new Font("arial", Font.BOLD, 20));
		lbTitle.setForeground(Color.WHITE);
		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(new Color(0xCD853F));
		pnTitle.add(lbTitle);
		this.add(pnTitle, BorderLayout.NORTH);

		// footer
		JLabel lbFooter = new JLabel("Eldar - a web service demo");
		lbFooter.setOpaque(true);
		lbFooter.setForeground(Color.WHITE);
		lbFooter.setBackground(new Color(0xCD853F));
		lbFooter.setFont(new Font(null, Font.BOLD, 13));
		this.add(lbFooter, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String s = null;
		StringBuilder sb = null;

		try {
			StockQuote service = new StockQuote();
			StockQuoteSoap stub = service.getStockQuoteSoap();

			sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append(
					"<StockQuotes xmlns=\"http://www.eldar.net/StockQuotes\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.eldar.net/StockQuotes StockQuotes.xsd \">");

			s = stub.getQuote(tfStockSymbol.getText());
			// System.out.println(s);
			s = s.substring(13);

		} catch (IndexOutOfBoundsException e1) {
			// JOptionPane.showMessageDialog(this, "error looking up: " +
			// tfStockSymbol.getText() + ". " + s + ".\nTry again!");
			JOptionPane.showMessageDialog(this, "error looking up: " + tfStockSymbol.getText() + "\nTry that again!");
			return;
		} catch (WebServiceException e1) {
			JOptionPane.showMessageDialog(this, "Error - check internet connection!", "Internet Connection Error",
					JOptionPane.ERROR_MESSAGE);
			// JOptionPane.showConfirmDialog(this, "Error - check internet
			// connection!");
			return;

		}
		sb.append(s);

		try (StringReader reader = new StringReader(sb.toString());) {
			JAXBContext jaxbContext = JAXBContext.newInstance("stock.bind");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StockQuotes stockQuotes = (StockQuotes) unmarshaller.unmarshal(reader);
			Stock stock = stockQuotes.getStock();

			rowData = new String[][] {

					{ "Symbol", stock.getSymbol() },

					{ "Name", stock.getName() },

					{ "High", stock.getHigh() },

					{ "Low", stock.getLow() },

					{ "Last", stock.getLast() },

					{ "Volume", stock.getVolume() },

					{ "Change", stock.getChange() },

					{ "PreviousClose", stock.getPreviousClose() },

					{ "Date", stock.getDate() },

					{ "Time", stock.getTime() },

					{ "Open", stock.getOpen() },

					{ "MktCap", stock.getMktCap() },

					{ "PercentageChange", stock.getPercentageChange() },

					{ "AnnRange", stock.getAnnRange() },

					{ "Earns", stock.getEarns() },

					{ "PE", stock.getPE() },

			};

			DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
			table.setModel(tableModel);

		} catch (JAXBException e1) {
			JOptionPane.showMessageDialog(this, "No stock Quotes found for symbol: " + tfStockSymbol.getText());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this,
					"error looking up: " + tfStockSymbol.getText() + ". " + e1.getMessage() + ". Try again");
		}

	}

}
