package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ws.StockQuote;
import core.ws.StockQuoteSoap;

public class Test2Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	StockQuote service = new StockQuote();
	StockQuoteSoap stub = service.getStockQuoteSoap();
	JButton btShow = new JButton("Show Stock Data");
	JTextField tfStockSymbol = new JTextField(50);

	public static void main(String[] args) {
		Test2Gui gui = new Test2Gui();
		gui.createAndShowGui();
		gui.setVisible(true);
	}

	public void createAndShowGui() {
		setBounds(100, 100, 1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel pn = new JPanel(new GridLayout(0, 1));
		JPanel pn = new JPanel();
		this.add(pn);

		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);

		pn1.add(new JLabel("Enter Stock Symbol"));
		pn2.add(tfStockSymbol);
		pn3.add(btShow);
		btShow.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = stub.getQuote(tfStockSymbol.getText());
		System.out.println(s);

	}

}
