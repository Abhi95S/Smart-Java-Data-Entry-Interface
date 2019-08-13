package Student;

import java.util.Comparator;

public class Student {
	public String Name = "";
	public String GR = "";
	public String ParentName = "";
	public String ParentPhNum = "";
	public String Address = "";
 
	public Student(String name, String gr, String parentName, String parentPhNum, String address) {
		// TODO Auto-generated constructor stub
		Name = name;
		GR = gr;
		ParentName = parentName;
		ParentPhNum = parentPhNum;
		Address = address;
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public static Comparator<Student> NameComparator = new Comparator<Student>() {

		public int compare(Student s1, Student s2) {
		   String StudentName1 = s1.Name.toUpperCase();
		   String StudentName2 = s2.Name.toUpperCase();

		   //ascending order
		   return StudentName1.compareTo(StudentName2);

		   //descending order
		   //return StudentName2.compareTo(StudentName1);
	    }
	};

}
