package edu.tdt.exercise;

public class StudentOut {

	private String id;
	private String firstName;
	private String lastName;
	private String faculty;
	private String schoolYear;
	
	public StudentOut() {}
	
	public StudentOut(String id, String firstName, String lastName, String faculty, String schoolYear) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.faculty = faculty;
		this.schoolYear = schoolYear;
	}
	
	public String getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFaculty() {
		return faculty;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	@Override
	public String toString() {
		return "id = " + id + "first name = " + firstName + "last name" + lastName + "faculty = " + faculty + "school year" + schoolYear;
	}
	
}
