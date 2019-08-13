package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Student.Student;
import input.StudentInputDialog;

public class GUIMainInterface extends Interface{
	
	protected static JButton CreateButton = new JButton("Create");
	private static JButton EditButton = new JButton("Edit");
	private static JButton CreateButtonInfo = new JButton();
	private static JButton EditButtonInfo = new JButton();
	private static JPanel panel = new JPanel(new BorderLayout());
	protected static JFrame MainInterface = new JFrame("Student Database Manager");
	protected static StudentInputDialog studentInputDialog = null;
	protected static File file = null;
	protected static String filepath = "";
	public static ArrayList<Student> AllStudents = new ArrayList<Student>();
	protected static String NameCache = "";
	protected static String GRCache = "";
	protected static String ParentNameCache = "";
	protected static String ParentNumCache = "";
	protected static String AddressCache = "";
	public static AllStudentData allStudentData = null;
	public static EditInterface editInterface = null;
	static JTable StudentDataTable = null;
	public static JFrame StudentDataFrame = new JFrame("List of Students");
	public static int flag = 0;
	private static final Object[][] rowData = {};
	private static final Object[] columnNames = {"Student's Name", "GR Number", "Parent's Name", "Parent's Phone Number", "Address"};

	public static void MainPage() {
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
		MainInterface.setVisible(true);
		MainInterface.setBounds(20, 20, 300, 290);
		MainInterface.setLocationRelativeTo(null);
		
		try {
			MainInterface.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/res/logo.png")));
		} catch (IOException exc) {
			// exc.printStackTrace();
		}
		
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
        panel.setBorder(BorderFactory.createEtchedBorder());
        JLabel label =  new JLabel("Panel 2");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label,BorderLayout.CENTER);

		setComponents();
		addComponents();
		addActionListeners();
		setInfoButtons();

		MainInterface.add(panel);
		panel.revalidate();
		MainInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static void setInfoButtons() {
		/**
		 * Show information when mouse hovered over the labels
		 */
		try {
			CreateButtonInfo.setOpaque(false);
			CreateButtonInfo.setFocusPainted(false);
			CreateButtonInfo.setBorderPainted(false);
			CreateButtonInfo.setContentAreaFilled(false);
			CreateButtonInfo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			
			EditButtonInfo.setOpaque(false);
			EditButtonInfo.setFocusPainted(false);
			EditButtonInfo.setBorderPainted(false);
			EditButtonInfo.setContentAreaFilled(false);
			EditButtonInfo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			
		    Image img = ImageIO.read(new File(System.getProperty("user.dir") + "/res/info.png"));
		    
		    CreateButtonInfo.setIcon(new ImageIcon(img));
		    EditButtonInfo.setIcon(new ImageIcon(img));
		    
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		ToolTipManager.sharedInstance().setInitialDelay(0);
		CreateButtonInfo.setToolTipText("Create a new database");
		EditButtonInfo.setToolTipText("Choose and Edit an existing database");
	}

	private static void addActionListeners() {
		/**
		 * Add functions to the buttons
		 */
		CreateButton.addActionListener(new Create());
		EditButton.addActionListener(new Edit());
		StudentDataFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		StudentDataFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.gc();
				if (StudentInputDialog.RedirectFlag == 1)
					MainInterface.setVisible(true);
				else
					MainInterface.setVisible(false);
				StudentDataFrame.setVisible(false);
				EditInterface.editInterface.setVisible(false);
			}
		});
	}

	private static void addComponents() {
		/**
		 * Add all the buttons, labels, text-fields in the panel
		 */

		System.out.println("adding components");
		panel.add(CreateButton);
		panel.add(EditButton);
		panel.add(CreateButtonInfo);
		panel.add(EditButtonInfo);
		panel.setVisible(true);
	}

	private static void setComponents() {
		/**
		 * Set the location of all buttons, labels and text-fields
		 */

		int xdiff = 100;
		int ydiff = 50;
		int x0 = 80;
		int y0 = 10;
		int x = x0;
		int y = y0;
		int fieldwidth = 70;
		int fieldheight = 25;

		y += ydiff;
		CreateButton.setBounds(x, y, fieldwidth + 30, fieldheight);
		x += xdiff;
		CreateButtonInfo.setBounds(x, y, fieldwidth, fieldheight);
		
		x = x0;
		y += 2*ydiff;
		EditButton.setBounds(x, y, 100, fieldheight);
		x += xdiff;
		EditButtonInfo.setBounds(x, y, fieldwidth, fieldheight);
	}

	static class Create implements ActionListener {
		/**
		 * This method is called when "File 1" button is pressed
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			file = null;
			if (studentInputDialog == null)
				studentInputDialog = new StudentInputDialog();
			else {
				StudentInputDialog.Details.requestFocusInWindow();
				StudentInputDialog.DataEntryDialog.setVisible(true);
			}
			//studentInputDialog.setVisible(true);
		}
	}

	static class Edit implements ActionListener {
		/**
		 * This method is called when "File 1" button is pressed
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "Database.xls", "xls");
	        chooser.setFileFilter(filter);
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	AllStudents = new ArrayList<Student>();
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile().getName());
	            file = chooser.getSelectedFile();
		        readfile();
		        MainInterface.setVisible(false);
		        StudentInputDialog.RedirectFlag = 1;
	        	allStudentData = new AllStudentData();
		        if (allStudentData == null) {
					editInterface = new EditInterface();
					flag = 1;
		        }
		        else {
		        	StudentDataFrame.setVisible(true);
		        	allStudentData = new AllStudentData();
		        	EditInterface.reInitialize();
		        	EditInterface.editInterface.setVisible(true);
		        }
	        }
		}
	}

	private static void readfile() {
		try {		
			Scanner read = new Scanner (new File(file.getPath()));
			Student student = new Student();
			while (read.hasNextLine()) {
				String line = read.nextLine();
				if (!line.startsWith("/*")){
					String[] items= line.split("\t", -1);
					NameCache = items[0];
					GRCache = items[1];
					ParentNameCache = items[2];
					ParentNumCache = items[3];
					AddressCache = items[4];
					System.out.println(NameCache + " " + GRCache + " " + ParentNameCache + " " + ParentNumCache + " " + AddressCache);
					student = new Student(NameCache, GRCache, ParentNameCache, ParentNumCache, AddressCache);
					System.out.println(student.Name);
					AllStudents.add(student);
					for (Student s : AllStudents) {
						System.out.println(s.Name);
					}
				}
			}
			/*read.useDelimiter("\t");
			read.nextLine();
			String NameCache;
			String GRCache;
			String ParentNameCache;
			String ParentNumCache;
			String AddressCache;
			while (read.hasNext())
			{
				NameCache = read.next();
				GRCache = read.next();
				ParentNameCache = read.next();
				ParentNumCache = read.next();
				AddressCache = read.next();
				System.out.println(NameCache + " " + GRCache + " " + ParentNameCache + " " + ParentNumCache + " " + AddressCache);
				Student student = new Student(NameCache, GRCache, ParentNameCache, ParentNumCache, AddressCache);
				AllStudents.add(student);
			}*/
			read.close();
			Collections.sort(AllStudents, Student.NameComparator);
		
		} catch (Exception e1) {
			if (file != null) {
				JOptionPane.showMessageDialog(null, "Please Select Database.xls file", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public static class AllStudentData implements TableModelListener {
		/**
		* 
		*/
		public AllStudentData() {
			
			/*DefaultTableModel listTableModel;
		    listTableModel = new DefaultTableModel(rowData, columnNames);
		    for (int i = 0; i < AllStudents.size(); i++) {
		    	Student student = AllStudents.get(i);
		    	System.out.println("ListTable " + student.Name);
		        String NameString = student.Name;
		        String GRString = student.GR;
		        String ParentNameString = student.ParentName;
		        String ParentPhNumberString = student.ParentPhNum;
		        String AddressString = student.Address;
		        listTableModel.addRow(new Object[]{NameString, GRString, ParentNameString, ParentPhNumberString, AddressString});
		    }

		    //listTableModel = new DefaultTableModel(50, 50);

		    StudentDataTable = new JTable(listTableModel);
		    StudentDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    StudentDataTable.setCellEditor(null);*/

			StudentDataTable = new JTable(new AbstractTableModel() {
				private static final long serialVersionUID = 1L;
				String[] columnNames = { "Student's Name", "GR Number", "Parent's Name", "Parent's Number", "Address" };
				
				@Override
				public String getColumnName(int col) {
					return columnNames[col];
				}

				@Override
				public int getRowCount() {
					return AllStudents.size();
				}

				@Override
				public int getColumnCount() {
					return 5;
				}

				@Override
				public Object getValueAt(int row, int col) {

					switch (col) {
					case 0:
						return AllStudents.get(row).Name;
					case 1:
						return AllStudents.get(row).GR;
					case 2:
						return AllStudents.get(row).ParentName;
					case 3:
						return AllStudents.get(row).ParentPhNum;
					case 4:
						return AllStudents.get(row).Address;
					}

					return null;
				}
				
				/*public boolean isCellEditable(int row, int col)
	        	{ fireTableCellUpdated(row, col); return true; }
	        
		        public void setValue(Object object, int row, int col) {
		        	//rowData[row][col] = value;
		        	fireTableCellUpdated(row, col);*/

			});
			//StudentDataFrame.removeAll();
			JScrollPane scrollPane = new JScrollPane(StudentDataTable);
			StudentDataTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
			StudentDataFrame.add(scrollPane);
			StudentDataFrame.setBounds(100, 100, 600, 600);
			StudentDataFrame.setLocationRelativeTo(null);
			StudentDataTable.getModel().addTableModelListener(new TableListener());

			if (flag == 0)
				StudentDataFrame.setUndecorated(true);
			StudentDataFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
			StudentDataFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			try {
				StudentDataFrame.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/res/logo.png")));
			} catch (IOException exc) {
				// exc.printStackTrace();
			}
			StudentDataFrame.revalidate();
			scrollPane.revalidate();
			StudentDataFrame.setVisible(true);
			((AbstractTableModel)StudentDataTable.getModel()).fireTableCellUpdated(0, 0);
		}

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        Object data = model.getValueAt(row, column);
	        model.setValueAt(data, row, column);
	        switch (column) {
	        	case 0:AllStudents.get(row).Name = data.toString();
	        	case 1:AllStudents.get(row).GR = data.toString();
	        	case 2:AllStudents.get(row).ParentName = data.toString();
	        	case 3:AllStudents.get(row).ParentPhNum = data.toString();
	        	case 4:AllStudents.get(row).Address = data.toString();
        	}
		}
	}
	
	public static class TableListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        Object data = model.getValueAt(row, column);
	        System.out.println(data.toString());
	        model.setValueAt(data, row, column);
	        switch (column) {
	        	case 0: AllStudents.get(row).Name = data.toString();
	        			break;
	        	case 1:	AllStudents.get(row).GR = data.toString();
	        			break;
	        	case 2:	AllStudents.get(row).ParentName = data.toString();
	        			break;
	        	case 3:	AllStudents.get(row).ParentPhNum = data.toString();
	        			break;
	        	case 4:	AllStudents.get(row).Address = data.toString();
	        			break;
        	}
		}

	}
}
