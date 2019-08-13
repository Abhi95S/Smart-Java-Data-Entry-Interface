package input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.EditInterface;
import GUI.GUIMainInterface;
import Student.Student;
public class StudentInputDialog extends GUIMainInterface{
	public static int flag = 0;
	static JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	public static JFrame DataEntryDialog = new JFrame("Student Database Manager");
	public static JLabel Header = new JLabel("Enter Student's Name");
	public static JTextArea Details = new JTextArea();
	static JButton Submit = new JButton("Submit");
	//static JButton Next = new JButton("Next");
	public static JButton Namebutton = new JButton();
	static JLabel NameCheck = new JLabel();
	static JButton GRbutton = new JButton();
	static JLabel GRCheck = new JLabel();
	static JButton ParentNamebutton = new JButton();
	static JLabel ParentNameCheck = new JLabel();
	static JButton ParentPhbutton = new JButton();
	static JLabel ParentPhCheck = new JLabel();
	static JButton Addressbutton = new JButton();
	static JLabel AddressCheck = new JLabel();
	static JButton Add = new JButton("Add");
	public static JButton View = new JButton("View");
	public static JButton Save = new JButton("Save");
	JButton Exit = new JButton("Exit");
	static int DetailsLocX = 0;
	static int DetailsLocY = 0;
	static int SubmitLocX = 0;
	static int SubmitLocY = 0;
	static int textWidth = 260;
	static int textHeight = 20;
	static int buttonWidth = 140;
	static int buttonHeight = 20;
	static String tick = "\u2713";
	File file = null;
	String fileName = "Database.xls";
	public static int RedirectFlag = 0;

	public StudentInputDialog() {
		MainInterface.setVisible(false);
		int frameHeight = 380;
		DataEntryDialog.setVisible(true);
		DataEntryDialog.setBounds(20, 20, 500, frameHeight);
		DataEntryDialog.setLocationRelativeTo(null);
		try {
			DataEntryDialog.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/res/logo.png")));
		} catch (IOException exc) {
			// exc.printStackTrace();
		}
		DataEntryDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		DataEntryDialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.gc();
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Do you want to stop current task?", "Exit", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);

				@SuppressWarnings("unused")
				int x = JOptionPane.CANCEL_OPTION;

				if (PromptResult == 0) {
					if (!(AllStudents.size() == 0)) {
						String ObjButtons1[] = { "Yes", "No" };
						int PromptResult1 = JOptionPane.showOptionDialog(null,
								"Do you want to save before exiting?", "Exit", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, ObjButtons1, ObjButtons1[1]);

						if (PromptResult1 == 0) {
							if (!(AllStudents == null)) {
								Save.doClick();
								Exit.doClick();
							}
						}
					}
					exitAction();
				}
			}
		});
		
		Details.setLineWrap(true);
		Details.setFont(Details.getFont().deriveFont(14f));
		ToolTipManager.sharedInstance().setInitialDelay(0);
		
		panel1.setBackground(Color.lightGray);
		panel2.setBackground(Color.WHITE);
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel1.setPreferredSize(new Dimension (300,frameHeight));
		panel2.setPreferredSize(new Dimension(200,frameHeight));
		panel1.setVisible(true);
		
		createbuttonlinks();
		setcomponents();
		addcomponents();
		
		//Next.setEnabled(false);
		Add.setEnabled(false);
		if (AllStudents.size() == 0)
			View.setEnabled(false);
		else
			View.setEnabled(true);
		Save.setEnabled(false);
		Namebutton.doClick();
		Details.requestFocusInWindow();
	}
	
	private void exitAction() {
		Namebutton.doClick();
		DataEntryDialog.setVisible(false);
		//GUIMainInterface.studentInputDialog = null;
		MainInterface.setVisible(true);
	}

	private void createbuttonlinks() {
		// TODO Auto-generated method stub
		Namebutton.setBorder(null);
		GRbutton.setBorder(null);
		ParentNamebutton.setBorder(null);
		ParentPhbutton.setBorder(null);
		Addressbutton.setBorder(null);
		Details.getDocument().addDocumentListener(new DetailsListener());
		Submit.addActionListener(new SubmitButtonListener());
		//Next.addActionListener(new NextListener());
		
		Namebutton.setText("<html><u>Student's Name<u><html>");
		Namebutton.setHorizontalAlignment(SwingConstants.LEFT);
		Namebutton.setBorderPainted(false);
		Namebutton.setOpaque(false);
		Namebutton.setBackground(Color.blue);
		Namebutton.addActionListener(new addnameDialog());
		
		GRbutton.setText("<html><u>GR Number<u><html>");
		GRbutton.setHorizontalAlignment(SwingConstants.LEFT);
		GRbutton.setBorderPainted(false);
		GRbutton.setOpaque(false);
		GRbutton.setBackground(Color.lightGray);
		GRbutton.addActionListener(new addGRDialog());
		
		ParentNamebutton.setText("<html><u>Parent's Name<u><html>");
		ParentNamebutton.setHorizontalAlignment(SwingConstants.LEFT);
		ParentNamebutton.setBorderPainted(false);
		ParentNamebutton.setOpaque(false);
		ParentNamebutton.setBackground(Color.lightGray);
		ParentNamebutton.addActionListener(new addParentNameDialog());

		ParentPhbutton.setText("<html><u>Parent's Phone Number<u><html>");
		ParentPhbutton.setHorizontalAlignment(SwingConstants.LEFT);
		ParentPhbutton.setBorderPainted(false);
		ParentPhbutton.setOpaque(false);
		ParentPhbutton.setBackground(Color.lightGray);
		ParentPhbutton.addActionListener(new addParentPhDialog());
		
		Addressbutton.setText("<html><u>Address<u><html>");
		Addressbutton.setHorizontalAlignment(SwingConstants.LEFT);
		Addressbutton.setBorderPainted(false);
		Addressbutton.setOpaque(false);
		Addressbutton.setBackground(Color.lightGray);
		Addressbutton.addActionListener(new addAddressDialog());
		
		Add.addActionListener(new AddStudentListener());
		View.addActionListener(new ViewStudentListener());
		Save.addActionListener(new SaveActionListener());
		Exit.addActionListener(new exitButtonListener());
	}

	private void setcomponents() {
		// TODO Auto-generated method stub
		int x = 20;
		int x0 = x;
		int x1 = 0;
		int y = 10;
		int y0 = y;
		int ydiff = 40;
		int labelWidth = 200;
		
		y += ydiff/2;
		Header.setBounds(x, y, labelWidth, buttonHeight);
		y += ydiff;
		DetailsLocX = x;
		DetailsLocY = y;
		Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight);
		y += ydiff;
		SubmitLocX = x + 70;
		SubmitLocY = y;
		Submit.setBounds(SubmitLocX, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
		//x += (int) Math.ceil(buttonWidth/1.5);
		//Next.setBounds(x, y, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
		
		x = x0;
		y = y0;
		y += ydiff/2;
		x1 = buttonWidth - 45;
		Namebutton.setBounds(x, y, x1, buttonHeight);
		x += x1;
		NameCheck.setBounds(x, y, buttonWidth, buttonHeight);
		x = x0;
		y += ydiff;
		x1 = buttonWidth - 70;
		GRbutton.setBounds(x, y, x1, buttonHeight);
		x += x1;
		GRCheck.setBounds(x, y, buttonWidth, buttonHeight);
		x = x0;
		y += ydiff;
		x1 = buttonWidth - 50;
		ParentNamebutton.setBounds(x, y, buttonWidth - 50, buttonHeight);
		x += x1;
		ParentNameCheck.setBounds(x, y, buttonWidth, buttonHeight);
		x = x0;
		y += ydiff;
		x1 = buttonWidth;
		ParentPhbutton.setBounds(x, y, buttonWidth, buttonHeight);
		x += x1;
		ParentPhCheck.setBounds(x, y, buttonWidth, buttonHeight);
		x = x0;
		y += ydiff;
		x1 = buttonWidth - 80;
		Addressbutton.setBounds(x, y, buttonWidth - 80, buttonHeight);
		x += x1;
		AddressCheck.setBounds(x, y, buttonWidth, buttonHeight);
		x = x0;
		y += (int) Math.ceil(1.5*ydiff);
		Add.setBounds(x + 5, y, buttonWidth/2, buttonHeight);
		View.setBounds(x + buttonWidth/2 + 25, y, buttonWidth/2, buttonHeight);
		y += ydiff;
		Save.setBounds(x + 5, y, buttonWidth/2, buttonHeight);
		Exit.setBounds(x + buttonWidth/2 + 25, y, buttonWidth/2, buttonHeight);
	}

	private void addcomponents() {
		// TODO Auto-generated method stub
		
		panel1.add(Header);
		panel1.add(Details);
		panel1.add(Submit);
		//panel1.add(Next);
		DataEntryDialog.add(panel1, BorderLayout.WEST);
		DataEntryDialog.add(panel2, BorderLayout.EAST);
		panel1.revalidate();
		
		/*NameCheck.setText("a");
		GRCheck.setText("a");
		ParentNameCheck.setText("a");
		ParentPhCheck.setText("a");
		AddressCheck.setText("a");*/
		panel2.add(Namebutton);
		panel2.add(NameCheck);
		panel2.add(GRbutton);
		panel2.add(GRCheck);
		panel2.add(ParentNamebutton);
		panel2.add(ParentNameCheck);
		panel2.add(ParentPhbutton);
		panel2.add(ParentPhCheck);
		panel2.add(Addressbutton);
		panel2.add(AddressCheck);
		panel2.add(Add);
		panel2.add(View);
		panel2.add(Save);
		panel2.add(Exit);
		panel2.revalidate();
		
	}
	
	public class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub			
			
			String currentDirectoryPath=null;
			JFileChooser chooser=null;
			JFrame parentFrame = new JFrame();
			JOptionPane.showMessageDialog(null, "Select a file for saving database", "Database File",
					JOptionPane.OK_OPTION);
			try {
				currentDirectoryPath = (new File("..")).getCanonicalPath();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chooser = new JFileChooser(currentDirectoryPath);
			System.out.println("I came here *********************************************");
			file = new File(fileName);
			chooser.setSelectedFile(file);

			int response = chooser.showSaveDialog(parentFrame);
			if (response == JFileChooser.APPROVE_OPTION) {
				// user clicked Save
				file = chooser.getSelectedFile();
				try {
					writetofile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("I saved the file");
			} else {
				// user cancelled...
				System.out.println("I cancelled saving the file");
			}
		}
	}
	public class exitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AllStudents = null;
			clearMemory();
			exitAction();
		}
	}
	
	public void writetofile() throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("/*Student's Name" + "	" + "GR Number" + "	" + "Parent's Name" + "	" + "Parent's Number" + "	" + "Address*/\n");
		for (Student student : AllStudents) {
			bw.write(student.Name + "	");
			bw.write(student.GR + "	");
			bw.write(student.ParentName + "	");
			bw.write(student.ParentPhNum + "	");
			bw.write(student.Address + "\n");
		}
		bw.close();
	}
		
	public void clearMemory() {
		Submit.setEnabled(false);
		//Next.setEnabled(false);
		Details.setText("");
		NameCache = "";
		GRCache = "";
		ParentNameCache = "";
		ParentNumCache = "";
		AddressCache = "";
		NameCheck.setText("");
		GRCheck.setText("");
		ParentNameCheck.setText("");
		ParentPhCheck.setText("");
		AddressCheck.setText("");
	}
	
	private static void EnableAddButtonCheck() {
		// TODO Auto-generated method stub
		if (NameCheck.getText().equalsIgnoreCase(tick) && GRCheck.getText().equalsIgnoreCase(tick) && ParentNameCheck.getText().equalsIgnoreCase(tick) && ParentPhCheck.getText().equalsIgnoreCase(tick) && AddressCheck.getText().equalsIgnoreCase(tick))
			Add.setEnabled(true);
	}
	
	public class AddStudentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Student student = new Student(NameCache, GRCache, ParentNameCache, ParentNumCache, AddressCache);
			AllStudents.add(student);
			clearMemory();
			Add.setEnabled(false);
			Save.setEnabled(true);
			View.setEnabled(true);
			Namebutton.doClick();
			
		}

	}
		
	public class ViewStudentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Collections.sort(AllStudents, Student.NameComparator);
			RedirectFlag = 0;
			GUIMainInterface.flag = 1;
			GUIMainInterface.StudentDataFrame.setVisible(true);
			if (GUIMainInterface.allStudentData == null)
				editInterface = new EditInterface();
			else
				EditInterface.editInterface.setVisible(true);
			GUIMainInterface.allStudentData = new AllStudentData();
			/*for (Student student : AllStudents) {
				System.out.println(student.Name);
			}*/
		}

	}
	
	public class DetailsListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			FieldCheck();
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			FieldCheck();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			FieldCheck();
		}

		private void FieldCheck() {
			// TODO Auto-generated method stub
			if (Details.getText().equals("")) {
				Submit.setEnabled(false);
				//Next.setEnabled(false);
				return;
			}
			Submit.setEnabled(false);
			switch (flag) {
			case 0: if (Details.getText().matches("[a-z A-Z]+"))
						Submit.setEnabled(true);
					break;
			case 1: if (Details.getText().matches("[0-9]+"))
						Submit.setEnabled(true);
					break;
			case 2: if (Details.getText().matches("[a-z A-Z]+"))
						Submit.setEnabled(true);
					break;
			case 3: if (Details.getText().matches("[0-9 +]+"))
						Submit.setEnabled(true);
					break;
			default: Submit.setEnabled(true);
					 break; 
			}
		}

	}
	
	static class SubmitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			switch (flag) {
			case 0: NameCache = Details.getText();
					NameCheck.setText(tick);
					//Next.setEnabled(true);
					GRbutton.doClick();
					EnableAddButtonCheck();
					break;
			case 1: GRCache = Details.getText();
					GRCheck.setText(tick);
					//Next.setEnabled(true);
					ParentNamebutton.doClick();
					EnableAddButtonCheck();
					break;
			case 2: ParentNameCache = Details.getText();
					ParentNameCheck.setText(tick);
					//Next.setEnabled(true);
					ParentPhbutton.doClick();
					EnableAddButtonCheck();
					break;
			case 3: ParentNumCache = Details.getText();
					ParentPhCheck.setText(tick);
					//Next.setEnabled(true);
					Addressbutton.doClick();
					EnableAddButtonCheck();
					break;
			default:AddressCache = Details.getText();
					AddressCheck.setText(tick);
					//Next.setEnabled(true);
					Namebutton.doClick();
					EnableAddButtonCheck();
					break; 
			}
			EnableAddButtonCheck();
			Details.requestFocusInWindow();
			System.out.println(NameCache + " " + GRCache + " " + ParentNameCache + " " + ParentNumCache + " " + AddressCache);
		}
		// TODO Auto-generated method stub
		
	}

	static class addnameDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			//Submit.setEnabled(false);
			Details.setLineWrap(false);
			Details.setText("");
			//Next.setEnabled(false);
			if (!NameCache.equalsIgnoreCase("")) {
				Submit.setEnabled(true);
				Details.setText(NameCache);
				//Next.setEnabled(true);
			}
			Header.setText("Enter Student's Name (LN FN MN)");
			Header.setToolTipText("Format = Last-Name First-Name Middle-Name");
			Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight);
			Submit.setBounds(SubmitLocX, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			//Next.setBounds(SubmitLocX + (int) Math.ceil(buttonWidth/1.5) + 20, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			flag = 0;
		}
		// TODO Auto-generated method stub
		
	}

	static class addGRDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Submit.setEnabled(false);
			Details.setLineWrap(false);
			Details.setText("");
			//Next.setEnabled(false);
			if (!GRCache.equalsIgnoreCase("")) {
				Submit.setEnabled(true);
				Details.setText(GRCache);
				//Next.setEnabled(true);
				panel1.revalidate();
			}
			Header.setText("Enter Student's GR Number");
			Header.setToolTipText("");
			Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight);
			Submit.setBounds(SubmitLocX, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			//Next.setBounds(SubmitLocX + (int) Math.ceil(buttonWidth/1.5) + 20, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			flag = 1;
		}
		// TODO Auto-generated method stub
		
	}

	static class addParentNameDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Submit.setEnabled(false);
			Details.setLineWrap(false);
			Details.setText("");
			//Next.setEnabled(false);
			if (!ParentNameCache.equalsIgnoreCase("")) {
				Submit.setEnabled(true);
				Details.setText(ParentNameCache);
				//Next.setEnabled(true);
			}
			Header.setText("Enter Parent's Name");
			Header.setToolTipText("Format = Last-Name First-Name Middle-Name");
			Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight);
			Submit.setBounds(SubmitLocX, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			//Next.setBounds(SubmitLocX + (int) Math.ceil(buttonWidth/1.5) + 20, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			flag = 2;
		}
		// TODO Auto-generated method stub
		
	}

	static class addParentPhDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Submit.setEnabled(false);
			Details.setLineWrap(false);
			Details.setText("");
			//Next.setEnabled(false);
			if (!ParentNumCache.equalsIgnoreCase("")) {
				Submit.setEnabled(true);
				Details.setText(ParentNumCache);
				//Next.setEnabled(true);
			}
			Header.setText("Enter Parent's Phone Number");
			Header.setToolTipText("");
			Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight);
			Submit.setBounds(SubmitLocX, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			//Next.setBounds(SubmitLocX + (int) Math.ceil(buttonWidth/1.5) + 20, SubmitLocY, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			flag = 3;
		}
		// TODO Auto-generated method stub
		
	}

	static class addAddressDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//Submit.setEnabled(false);
			Details.setLineWrap(true);
			Details.setText("");
			//Next.setEnabled(false);
			if (!AddressCache.equalsIgnoreCase("")) {
				Submit.setEnabled(true);
				Details.setText(AddressCache);
				//Next.setEnabled(true);
			}
			Header.setText("Enter Student's Address");
			Header.setToolTipText("");
			Details.setBounds(DetailsLocX, DetailsLocY, textWidth, textHeight + 40);
			Submit.setBounds(SubmitLocX, SubmitLocY + 40, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			//Next.setBounds(SubmitLocX + (int) Math.ceil(buttonWidth/1.5) + 20, SubmitLocY + 40, (int) Math.ceil(buttonWidth/1.5), buttonHeight);
			flag = 4;
		}
		// TODO Auto-generated method stub
		
	}

}
