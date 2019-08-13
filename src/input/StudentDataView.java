package input;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StudentDataView {
	private static final long serialVersionUID = 1L;
	public JLabel studentName = new JLabel("Student's Name");
	public JLabel aadhar = new JLabel("Train Number");
	public JLabel parentName = new JLabel("Train Number");
	public JLabel parentNumber = new JLabel("Train Number");
	public JLabel address = new JLabel("Train Number");
	
	public JLabel scheduledTrainTimeTableLabel = new JLabel("Timetable");
	public JLabel stationLabel = new JLabel("Station");
	public JLabel loopLabel = new JLabel("Loop Id");
	public JLabel arrivalTimeLabel = new JLabel("Arrival in HHMM");
	public JLabel departureTimeLabel = new JLabel("Departure in HHMM");
	public JLabel noHaltLabel = new JLabel("No stopping");
	
	public ArrayList<JTextField> NameList = new ArrayList<JTextField>();
	public ArrayList<JTextField> GRNumList = new ArrayList<JTextField>();
	public ArrayList<JTextField> ParentNameList = new ArrayList<JTextField>();
	public ArrayList<JTextField> ParentNumList = new ArrayList<JTextField>();
	public ArrayList<JTextField> AddressList = new ArrayList<JTextField>();
	
	public ArrayList<JLabel> ErrorExclamationArray = new ArrayList<JLabel>();
	
	public JTextField Name = new JTextField();
	public JTextField GRNum = null;
	public JTextField ParentName = new JTextField();
	public JTextField ParentNum = new JTextField();
	public JLabel ErrorExclamation = new JLabel();
	
	public JButton add = new JButton("Add");
	public JButton view = new JButton("View");
	
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	
	private void clearTimeTablePanel() {
		for (int i = 0; i < NameList.size(); i++) {
			GRNum = NameList.get(i);
			ParentName = GRNumList.get(i);
			ParentName = ParentNameList.get(i);
			ErrorExclamation = ErrorExclamationArray.get(i);
			// checkHalt=checkHaltFieldList.get(i);
			panel2.remove(Name);
			panel2.remove(GRNum);
			panel2.remove(ParentName);
			panel2.remove(ParentName);
			panel2.remove(ErrorExclamation);
			// jpanel.remove(checkHalt);
		}
	}
	
	private void addTimeTableEntry() {
		ParentName = new JTextField();
		ParentName = new JTextField();
		Name = new JTextField();
		ErrorExclamation = new JLabel();
		// checkHalt=new JCheckBox();
		// NameList.add(GRNum);

		GRNumList.add(ParentName);
		ParentNameList.add(ParentName);
		ErrorExclamationArray.add(ErrorExclamation);
		// checkHaltFieldList.add(checkHalt);
	}
	
	public StudentDataView() {
		int height = 30;
		int totalHeight = 500;
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame InputDialog = new JFrame("Student Database Manager");
		InputDialog.setVisible(true);
		InputDialog.setBounds(20, 20, 900, 500);
		InputDialog.setLocationRelativeTo(null);
		
		panel1.setLayout(null);
		panel2.setLayout(null);
		
		panel1.setBounds(0, 0, totalHeight, height);
		panel2.setBounds(0, height, 900, totalHeight-height);
		
		InputDialog.add(panel1);
		InputDialog.add(panel2);
		
		int x0 = 20;
		int x1 = 90;
		int x2 = 200;
		int x3 = 310;
		int x4 = 420;
		int labelHeight = 30;
		int y1 = 20;
		int fieldWidth = 70;
		int yDifference = 25;
		int fieldHeight = 20;

		scheduledTrainTimeTableLabel.setBounds(x0, y1, 200, labelHeight);

		y1 += yDifference;
		stationLabel.setBounds(x0 + 60, y1, 150, labelHeight);
		loopLabel.setBounds(x1 + 150, y1, 90, labelHeight);
		arrivalTimeLabel.setBounds(x2 + 160, y1, 140, labelHeight);
		departureTimeLabel.setBounds(x3 + 150, y1, 140, labelHeight);
		noHaltLabel.setBounds(x4, y1, 90, labelHeight);
		clearTimeTablePanel();

		if (NameList.size() != 0) {
			for (int i = 0; i < NameList.size(); i++) {
				y1 += yDifference;
				addTimeTableEntry();
				//ErrorExclamation.setText("");
				Name.setText(NameList.get(i).toString());
				Name.setEditable(false);
				ParentNumList.add(Name);
				Name.setBounds(x0, y1, 150, fieldHeight);
				//GlobalVar.getSectionInputDialog();
				//ArrayList AddressList = SectionInputDialog.AddressList;
				NameList.add(GRNum);
				GRNum.setBounds(x1 + 110, y1, fieldWidth + 60, fieldHeight);
				// loopField.setBounds(x1, y1, fieldWidth, fieldHeight);
				ParentName.setBounds(x2 + 170, y1, fieldWidth, fieldHeight);
				ParentName.setBounds(x3 + 170, y1, fieldWidth, fieldHeight);
				ErrorExclamation.setBounds((int) (ParentName.getBounds().getX() + fieldWidth + 10), y1, fieldWidth, fieldHeight);
				/*
				 * checkHalt=new JCheckBox(); checkHalt.addItemListener(haltItemListener);
				 * checkHaltFieldList.add(checkHalt); checkHalt.setBounds(x4,y1,20,20);
				 */
				panel2.add(Name);
				panel2.add(GRNum);
				panel2.add(ParentName);
				panel2.add(ParentName);
				panel2.add(ErrorExclamation);
			
			}
		}
	}
}
