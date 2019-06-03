package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import cities.bind.NewDataSet;
import cities.bind.Table;
import core.ws.GlobalWeather;
import core.ws.GlobalWeatherSoap;

public class Test3Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GlobalWeather service = new GlobalWeather();
	private GlobalWeatherSoap stub = service.getGlobalWeatherSoap();
	private JButton btShow = new JButton("Show Cities");
	private JTextField tfCountryName = new JTextField(50);
	private JList<Table> jlist = new JList<>();

	public static void main(String[] args) {
		Test3Gui gui = new Test3Gui();
		gui.createAndShowGui();
		gui.setVisible(true);
	}

	public void createAndShowGui() {
		setBounds(100, 100, 1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pn = new JPanel();
		this.add(pn);

		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		pn.add(pn4);

		pn1.add(new JLabel("Enter Country Name"));
		pn2.add(tfCountryName);
		pn3.add(btShow);
		btShow.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(jlist);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		pn4.add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append(
				"<NewDataSet xmlns=\"eldar.net\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"eldar.net NewDataSet.xsd \">");

		String s = stub.getCitiesByCountry(tfCountryName.getText());
		System.out.println(s);
		s = s.substring(12);
		sb.append(s);

		try (StringReader reader = new StringReader(sb.toString());) {
			JAXBContext jaxbContext = JAXBContext.newInstance("cities.bind");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			NewDataSet newDataSet = (NewDataSet) unmarshaller.unmarshal(reader);
			List<Table> tables = newDataSet.getTable();
			jlist.setListData(tables.toArray(new Table[tables.size()]));

		} catch (JAXBException e1) {
			JOptionPane.showMessageDialog(this, "No cities found for country named: " + tfCountryName.getText());
			jlist.setListData(new Table[0]);
		}

	}

}
