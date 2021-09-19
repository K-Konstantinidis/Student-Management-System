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
	
	CardLayout layout = new CardLayout();
	
	JPanel deck = new JPanel();
	JPanel panel = new JPanel(); //1st card
	JPanel secondpanel = new JPanel(); //2nd card
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Student> student = new ArrayList<Student>();
	
	public GUI() {
		deck.setLayout(layout); //Set the layout to card layout
		
		//Add the cards
		deck.add(panel, "first");
		deck.add(secondpanel, "second");
		
		panel.setLayout(null);
		
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
		
		createStudent = new JButton("Create student");
		createStudent.setBounds(250, 20, 150, 20);
		createStudent.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		panel.add(createStudent);
		
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
				String name = nameField.getText();
				String id = idField.getText();
				boolean flag = false;
				
				if(name.isEmpty() || id.isEmpty()) {
					if(name.isEmpty()){
						JOptionPane.showMessageDialog(null, "Can't leave Name empty");
					}else {
						JOptionPane.showMessageDialog(null, "Can't leave ID empty");
					}
				}
				else if(student.isEmpty()){
					student.add(new Student(name, id));
					System.out.println("New student created!");
				}
				else {
					for(Student s : student) {
						if(s.getName().equals(name) && s.getId().equals(id)) {
							JOptionPane.showMessageDialog(null, "Student already exists");
							flag = true;
							break;
						}
					}
					if(!flag) {
						student.add(new Student(name, id));
						System.out.println("New student created!");
					}
				}
			}
		});
		
		createCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String course = courseField.getText();
				boolean flag = false;
				
				if(course.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't leave course empty");
				}
				else if(courses.isEmpty()){
					courses.add(new Course(course));
					System.out.println("New course added");
				}
				else {
					for(Course c : courses) {
						if(c.getTitle().equals(course)){
							JOptionPane.showMessageDialog(null, "Course already exists");
							flag = true;
							break;
						}
					}
					if(!flag) {
						courses.add(new Course(course));
						System.out.println("New course added");
					}
				}
			}
		});
		
		addInCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String course = courseField.getText();
				String name = nameField.getText();
				String id = idField.getText();
				
				boolean flag = false;
				boolean flag1 = false;
				
				if(course.isEmpty() || name.isEmpty() || id.isEmpty()) {
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
					for(Course c : courses) {
						if(c.getTitle().equals(course)){
							for(Student s : student) {
								if(s.getName().equals(name) && s.getId().equals(id)) {
									c.addStudent(s);
									System.out.println("Student added");
									flag1 = true;
									break;
								}
							}
							flag = true;
							break;
						}
					}
					if(!flag) {
						JOptionPane.showMessageDialog(null, "Course doesn't exist");
						flag1 = true;
					}
					if(!flag1) {
						JOptionPane.showMessageDialog(null, "Student doesn't exist");
					}
				}
			}
		});
		
		secondpanel.setLayout(null);
		
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
				String course = courseField.getText();
				
				boolean flag = false;
				
				if(course.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Can't leave course empty");
				}else {
					Course selectedCourse = null;
					
					for(Course c : courses) {
						if(c.getTitle().equals(course)){
							selectedCourse = c;
							flag = true;
							break;
						}							
					}
					if(flag) {
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
				layout.show(deck, "first");
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
