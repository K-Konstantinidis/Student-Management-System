import java.util.ArrayList;

public class Course {

	private String title;
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public void addStudent(Student aStudent)
	{
		students.add(aStudent);
	}
	
	public String printDetails()
	{
		String details = "";
		
		details += "Course name: '"+ title + "' has the following students:" +"\n";
		
		if(students.isEmpty()) {
			details = "This course has no students enrolled.";
		}else {
			for(Student student: students)
				details += student +"\n";
			details += "----------------------------------------------------------------------------------------------------------------------";
		}
		return details;
	}
	
	public Course(String title) 
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
}
