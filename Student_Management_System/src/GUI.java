import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField nameField, idField;
	private JLabel name, id, course;
	private JButton createStudent, createCourse;
	private JTextField courseField;
	private JButton addInCourse;
	private JButton printCourseDetails;
	private JButton backToMainPanel;
	private JTextArea detailsArea;
	
	CardLayout layout = new CardLayout(); //Use the Card Layout
	
	JPanel deck = new JPanel();
	JPanel panel = new JPanel(); //1st card
	JPanel secondpanel = new JPanel(); //2nd card
	
	private ArrayList<Course> courses = new ArrayList<Course>(); //Add courses in a list
	private ArrayList<Student> student = new ArrayList<Student>(); //Add students in a list
	
	public GUI() {
		deck.setLayout(layout); //Set the layout to card layout
		
		//Add the cards
		deck.add(panel, "first");
		deck.add(secondpanel, "second");
		
		panel.setLayout(null);
		
		//Add labels and their text fields
		name = new JLabel("Name:");
		name.setBounds(10, 5, 100, 20);
		name.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(name);
		
		nameField = new JTextField();
		nameField.setBounds(80, 5, 150, 20);
		nameField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(nameField);
		
		id = new JLabel("ID:");
		id.setBounds(10, 35, 100, 20);
		id.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(id);
		
		idField = new JTextField("");
		idField.setBounds(80, 35, 150, 20);
		idField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(idField);
		
		//Button to create the student
		createStudent = new JButton("Create student");
		createStudent.setBounds(250, 20, 150, 20);
		createStudent.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(createStudent);
		
		//Add the 3 buttons, a label and a text field for the course
		course = new JLabel("Course:");
		course.setBounds(125, 100, 100, 20);
		course.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(course);
		
		courseField = new JTextField("");
		courseField.setBounds(225, 100, 150, 20);
		courseField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(courseField);
		
		createCourse = new JButton("Create new course");
		createCourse.setBounds(125, 150, 250, 20);
		createCourse.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(createCourse);
		
		addInCourse = new JButton("Add student in course");
		addInCourse.setBounds(125, 180, 250, 20);
		addInCourse.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(addInCourse);
		
		printCourseDetails = new JButton("Print course details");
		printCourseDetails.setBounds(125, 210, 250, 20);
		printCourseDetails.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(printCourseDetails);
		
		createStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText(); //Get the name
				String id = idField.getText(); //Get the id
				boolean flag = false;
				
				if(name.isEmpty() || id.isEmpty()) { //If they are empty show message
					if(name.isEmpty()){
						JOptionPane.showMessageDialog(null, "Can't leave Name empty");
					}else {
						JOptionPane.showMessageDialog(null, "Can't leave ID empty");
					}
				}
				else if(student.isEmpty()){ //If student list is empty add the 1st student in
					student.add(new Student(name, id));
					System.out.println("New student created!");
				}
				else {
					for(Student s : student) { //Check if the student already exists
						if(s.getName().equals(name) && s.getId().equals(id)) {
							JOptionPane.showMessageDialog(null, "Student already exists");
							flag = true;
							break;
						}
					}
					if(!flag) { //If they don't exist add them in the list
						student.add(new Student(name, id));
						System.out.println("New student created!");
					}
				}
			}
		});
		
		createCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String course = courseField.getText(); //Get the course
				boolean flag = false;
				
				if(course.isEmpty()) { //If field is empty show message
					JOptionPane.showMessageDialog(null, "Can't leave course empty");
				}
				else if(courses.isEmpty()){ //If course list is empty
					courses.add(new Course(course));
					System.out.println("New course added");
				}
				else {
					for(Course c : courses) { //If course already exists
						if(c.getTitle().equals(course)){
							JOptionPane.showMessageDialog(null, "Course already exists");
							flag = true;
							break;
						}
					}
					if(!flag) { //If the course doesn't exist add it in the list
						courses.add(new Course(course));
						System.out.println("New course added");
					}
				}
			}
		});
		
		addInCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String course = courseField.getText(); //Get the course
				String name = nameField.getText(); //Get the name
				String id = idField.getText(); //Get the id
				
				boolean flag = false;
				boolean flag1 = false;
				
				if(course.isEmpty() || name.isEmpty() || id.isEmpty()) { //If a field is empty show message
					if(course.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Can't leave Course empty");
					}
					else if(name.isEmpty()){
						JOptionPane.showMessageDialog(null, "Can't leave Name empty");
					}
					else {
						JOptionPane.showMessageDialog(null, "Can't leave ID empty");
					}

				}else {
					for(Course c : courses) { //If course exists
						if(c.getTitle().equals(course)){
							for(Student s : student) { //If student exists
								if(s.getName().equals(name) && s.getId().equals(id)) {
									c.addStudent(s);
									System.out.println("Student added"); //Enroll student in course
									flag1 = true;
									break;
								}
							}
							flag = true;
							break;
						}
					}
					if(!flag) { //If course doesn't exist
						JOptionPane.showMessageDialog(null, "Course doesn't exist");
						flag1 = true; //Make flag true so the message for the student wont appear
					}
					if(!flag1) { //If student doesn't exist
						JOptionPane.showMessageDialog(null, "Student doesn't exist");
					}
				}
			}
		});
		
		secondpanel.setLayout(null);
		
		//Add a text area with the details of the course and a back to main panel button
		detailsArea = new JTextArea();
		detailsArea.setBounds(10, 5, 475, 220);
		detailsArea.setEditable(false);
		detailsArea.setLineWrap(true);
		detailsArea.setWrapStyleWord(true);
		detailsArea.setAutoscrolls(true);
		secondpanel.add(detailsArea);
		
		backToMainPanel = new JButton("> Back To Main Screen <");
		backToMainPanel.setBounds(100, 235, 300, 30);
		secondpanel.add(backToMainPanel);
		
		printCourseDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String course = courseField.getText(); //Get course
				
				boolean flag = false;
				
				if(course.isEmpty()) { //If field is empty show message
					JOptionPane.showMessageDialog(null, "Can't leave course empty");
				}else {
					Course selectedCourse = null;
					
					for(Course c : courses) { //Find the course
						if(c.getTitle().equals(course)){
							selectedCourse = c;
							flag = true;
							break;
						}							
					}
					if(flag) { //If course exists show its details in the new panel
						detailsArea.setText(selectedCourse.printDetails());
						layout.show(deck, "second");
					}else { 
						JOptionPane.showMessageDialog(null, "Course doesn't exist");					
					}
				}
			}
		});
		
		backToMainPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(deck, "first"); //Change to main panel
			}
		});
		
		layout.show(deck, "first");
		
		this.add(deck);
		this.setSize(500, 300);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Student form");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
