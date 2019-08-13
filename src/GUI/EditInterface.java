package GUI;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Student.Student;
import input.StudentInputDialog;

public class EditInterface extends GUIMainInterface {
	
	public static JFrame editInterface = new JFrame("Search and Edit");
	static JPanel editPanel = new JPanel();
	static JLabel searchbylabel = new JLabel("Search by:");
	static String[] Options = {"Name","GR"};
	static JComboBox<String> searchbyBox = new JComboBox<String>(Options);
	static JTextField searchField = new JTextField();
	static JButton searchButton = new JButton("\uD83D\uDD0D");
	static JLabel NameLabel = new JLabel("Name");
	static JTextField NameField = new JTextField();
	static JLabel GRLabel = new JLabel("GR Number");
	static JTextField GRField = new JTextField();
	static JLabel ParentNameLabel = new JLabel("Parent's Name");
	static JTextField ParentNameField = new JTextField();
	static JLabel ParentPhLabel = new JLabel("Parent's Phone Number");
	static JTextField ParentPhField = new JTextField();
	static JLabel AddressLabel = new JLabel("Name");
	static JTextField AddressField = new JTextField();
	static JButton editButton = new JButton("Edit");
	static JButton Update = new JButton("Update");
	static JButton Add = new JButton("Add More");
	static JButton Save = new JButton("Save");
	static int row = 0;
	static int flag = 0;
	
	public EditInterface() {
		editInterface.setVisible(true);
		editInterface.setBounds(20, 20, 420, 380);
		editInterface.setLocationRelativeTo(null);
		try {
			editInterface.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/res/logo.png")));
		} catch (IOException exc) {
			// exc.printStackTrace();
		}
		
		editPanel.setLayout(null);
		editPanel.setBackground(Color.WHITE);
		
		createEditComponents();
		addEditComponents();
		setupComponents();
		initializeComponents();
		addActionListeners();
		//searchbyBox.setSelectedIndex(1);
		//searchbyBox.setSelectedIndex(0);
	}
	
	private void addActionListeners() {
		// TODO Auto-generated method stub
		//searchField.setFocusTraversalKeysEnabled(false);
		//System.out.println(GUIMainInterface.AllStudents.size());
		// Our words to complete
		//Autocomplete autoComplete = new Autocomplete(searchField, StudentNames);
		//searchField.getDocument().addDocumentListener(autoComplete);
		// Maps the tab key to the commit action, which finishes the autocomplete
		// when given a suggestion
		//searchField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		//searchField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
		
		searchbyBox.addItemListener(StateChangeListener);
		searchButton.addActionListener(SearchButtonListener());
		clearMemory();
		Add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				editInterface.setVisible(false);
				StudentDataFrame.setVisible(false);
				StudentInputDialog.Details.setText("");
				StudentInputDialog.Namebutton.doClick();
				if (StudentInputDialog.RedirectFlag == 1)
					MainInterface.setVisible(true);
				else
					MainInterface.setVisible(false);
				StudentInputDialog.Namebutton.doClick();
				clearMemory();
				CreateButton.doClick();
		        System.out.println("Clicked!");
		    }
		});
	}
	
	public void clearMemory() {
		searchField.setText("");
		NameCache = "";
		GRCache = "";
		ParentNameCache = "";
		ParentNumCache = "";
		AddressCache = "";
		editButton.setEnabled(false);
		Update.setEnabled(false);
		if (AllStudents.size() == 0) {
			Save.setEnabled(false);
			StudentInputDialog.Save.setEnabled(false);
		}
		else {
			Save.setEnabled(true);
			StudentInputDialog.Save.setEnabled(true);
		}
	}

	private ActionListener SearchButtonListener() {
		// TODO Auto-generated method stub
		String query = searchField.getText();
		Student student = new Student();
		if (!query.equalsIgnoreCase("")) {
			for (int i = 0; i < AllStudents.size(); i++) {
				if (query.equalsIgnoreCase(AllStudents.get(i).Name) && flag == 0) {
					row = i;
					student = AllStudents.get(i);
					NameField.setText(student.Name);
					GRField.setText(student.GR);
					ParentNameField.setText(student.ParentName);
					ParentPhField.setText(student.ParentPhNum);
					AddressField.setText(student.Address);
					break;
				}
				if (query.equalsIgnoreCase(AllStudents.get(i).GR) && flag == 1) {
					row = i;
					student = AllStudents.get(i);
					NameField.setText(student.Name);
					GRField.setText(student.GR);
					ParentNameField.setText(student.ParentName);
					ParentPhField.setText(student.ParentPhNum);
					AddressField.setText(student.Address);
					break;
				}
			}
			System.out.println("Not Found");
		}
		return null;
	}

	ItemListener StateChangeListener = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			switch (searchbyBox.getSelectedItem().toString()) {
			case "Name":
				System.out.println("Search by Name");
				flag = 0;
				break;
			case "GR":
				System.out.println("Search by GR");
				flag = 1;
				break;
			}
		}
	};
	

	private void initializeComponents() {
		// TODO Auto-generated method stub
		searchbyBox.setSelectedIndex(0);
		editInterface.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editInterface.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.gc();
				if (StudentInputDialog.RedirectFlag == 1)
					MainInterface.setVisible(true);
				else
					MainInterface.setVisible(false);
				GUIMainInterface.StudentDataFrame.setVisible(false);
				editInterface.setVisible(false);
			}
		});
	}

	private static void setupComponents() {
		// TODO Auto-generated method stub
		searchButton.setMargin(new Insets(1,1,1,1));
		Add.setMargin(new Insets(1, 1, 1, 1));
		editPanel.setVisible(true);
		editInterface.setAlwaysOnTop(true);
	}

	private static void addEditComponents() {
		// TODO Auto-generated method stub
		editPanel.add(searchbylabel);
		editPanel.add(searchbyBox);
		editPanel.add(searchField);
		editPanel.add(searchButton);
		editPanel.add(NameLabel);
		editPanel.add(NameField);
		editPanel.add(GRLabel);
		editPanel.add(GRField);
		editPanel.add(ParentNameField);
		editPanel.add(ParentNameLabel);
		editPanel.add(ParentPhLabel);
		editPanel.add(ParentPhField);
		editPanel.add(AddressLabel);
		editPanel.add(AddressField);
		editPanel.add(editButton);
		editPanel.add(Update);
		editPanel.add(Add);
		editPanel.add(Save);
		editInterface.add(editPanel);
	}
	
	private static void createEditComponents() {
		// TODO Auto-generated method stub
		int x0 = 20;
		int y0 = 10;
		int x = x0;
		int y = y0;
		int xdiff = 20;
		int ydiff = 30;
		int FieldWidth = 200;
		int FieldHeight = 20;
		
		searchbylabel.setBounds(x, y, FieldWidth, FieldHeight);
		y += ydiff;
		searchbyBox.setBounds(x, y, FieldWidth/2, FieldHeight);
		x += FieldWidth/2 + xdiff;
		searchField.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		searchButton.setBounds(x, y, 30, FieldHeight);
		
		FieldWidth /= 1.3;
		x = x0;
		y += 2*ydiff;
		NameLabel.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		NameField.setBounds(x, y, FieldWidth, FieldHeight);
		x = x0;
		y += ydiff;
		GRLabel.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		GRField.setBounds(x, y, FieldWidth, FieldHeight);
		x = x0;
		y += ydiff;
		ParentNameLabel.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		ParentNameField.setBounds(x, y, FieldWidth, FieldHeight);
		x = x0;
		y += ydiff;
		ParentPhLabel.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		ParentPhField.setBounds(x, y, FieldWidth, FieldHeight);
		x = x0;
		y += ydiff;
		AddressLabel.setBounds(x, y, FieldWidth, FieldHeight);
		x += FieldWidth + xdiff;
		AddressField.setBounds(x, y, FieldWidth, FieldHeight);
		y += 2*ydiff;
		
		x0 += 40;
		x = x0;
		xdiff = 100;
		editButton.setBounds(x, y, FieldWidth/2, FieldHeight);
		x += FieldWidth/2 + xdiff;
		Update.setBounds(x, y, FieldWidth/2, FieldHeight);
		y += ydiff;
		x = x0;
		Save.setBounds(x, y, FieldWidth/2, FieldHeight);
		x += FieldWidth/2 + xdiff;
		Add.setBounds(x, y, FieldWidth/2, FieldHeight);
	}

	public static void reInitialize() {
		// TODO Auto-generated method stub
		searchbyBox.setSelectedItem("Name");
		searchField.setText("");
		NameField.setText("");
		GRField.setText("");
		ParentNameField.setText("");
		ParentPhField.setText("");
		AddressField.setText("");
	}
}
