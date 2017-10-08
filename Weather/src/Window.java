import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Window extends JFrame {

	private String city;
	private static String text;
	private JList cityList;
	private JScrollPane jsp;
	private JComboBox jcb;
	private JTextArea textArea;
	private String[] cities = { "Вінниця", "Полтава", "Дніпро", "Донецьк", "Житомир", "Запоріжжя", "Івано-франківськ", "Київ", "Кіровоград", "Луганськ", "Львів", "Луцьк", "Миколаїв", "Одеса", "Рівне",
			"Севастопіль", "Сімферопіль", "Суми", "Тернопіль", "Ужгород", "Львів", "Харків", "Херсон", "Хмельницький", "Черкаси", "Чернігів", "Ялта" };

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public static void setText(String text) {
		Window.text = text;
	}

	Window() {
		JFrame frame = new JFrame("Weather forecast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		cityList = new JList(cities);
		cityList.setSelectedIndex(0);

		jcb = new JComboBox(cities);

		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		frame.add(jcb, c);

		textArea = new JTextArea(20, 10);
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 80;
		c.gridheight = 10;
		c.anchor = GridBagConstraints.PAGE_START;
		frame.add(textArea, c);

		frame.setVisible(true);

		jcb.addActionListener(new ActionEventListener());

	}

	class ActionEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JComboBox box = (JComboBox) e.getSource();
			String item = (String) box.getSelectedItem();

			String txt = item;

			GetSetDataFromSite.setMousePressed(true);
			GetSetDataFromSite.setCity(txt.toLowerCase());
			System.out.println(txt);

			GetSetDataFromSite connect = new GetSetDataFromSite();

			while (text == null) {
				try {
					Thread.sleep(25);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}

			textArea.setText(text);
			text = null;
		}

	}

	class MouseEventListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {

			System.out.println("в блоке MousePressed");
			JComboBox box = (JComboBox) e.getSource();
			int item = (int) box.getSelectedItem();

			System.out.println(item);

			String txt = cities[item];

			GetSetDataFromSite.setMousePressed(true);
			GetSetDataFromSite.setCity(txt.toLowerCase());
			System.out.println(txt);

			GetSetDataFromSite connect = new GetSetDataFromSite();

			while (text == null) {
				try {
					Thread.sleep(25);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}

			textArea.setText(text);
			text = null;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
