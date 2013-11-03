

package taAllocation;

import java.util.ArrayList;

/*
	Course: - creates lectures/labs/times from predicates
			- takes eval queries in string form

	Error handling: prints error/warning messages to screen
	
	Errors handled:
		- lecture doesn't exist
		- lecture declared more than once
		- lab doesn't exist
		- lab declared more than once
		- lecture/lab already has a timeslot
		
	Associated errors in array list of courses:
		- avoid duplicate courses
		- make new courses on the fly
	
	Other related errors:
		- "at" predicates need their timeslot checked beforehand
*/

// Contains all predicates involving course/lecture/lab declarations and their associated times
public class Course {

	private String courseName = null; // eg CPSC433
	private String courseType = null; // senior-course, grad-course, or course
	private ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	private ArrayList<Lab> labList = new ArrayList<Lab>();
	
	private boolean ok = false; // throwaway: 'add' method returns boolean, don't really care

	public Course(Predicate in){ // course declaration predicate
		courseType = in.getName();
		courseName = in.getStringParam(0);
	}
	
	public String getName()
		{return courseName;}
	
	public String courseType()
		{return courseType;}
		
		// finds or creates lecture and returns its section
	public String findLecture(String query){
		for (int i = 0; i < lectureList.size(); i++){
			if (lectureList.get(i).section.equals(query) == true)
				return query;
		}
		System.err.println("Warning: lecture " + query +" has not been declared.");
		Lecture lec = new Lecture(query);
		lectureList.add(lec);
		return query;
	}
	
	// finds lab and returns its section (or null if it doesn't exist)
	public String findLab(String query){
		for (int i = 0; i < labList.size(); i++){
			if (labList.get(i).section.equals(query) == true)
				return query;
		}
		System.err.println("Error: lab " + query +" does not exist.");
		return null;
	}

		// packs all of the course's contents into a string
	public String getCourseInfo(){
		String out = courseType + "(" + courseName + ")\n";
		for (int i = 0; i < lectureList.size(); i++){
			out += "lecture(" + courseName + "," + lectureList.get(i) + ")\n";
			if (lectureList.get(i).time != null) // make sure time exists
				{out += "at(" + courseName + "," + lectureList.get(i).section + "," + lectureList.get(i).time +")\n";}
			else
				{System.err.println("Warning: missing timeslot for lecture "+lectureList.get(i).section);}
		}
		for (int i = 0; i < labList.size(); i++){
			out += "lab(" + courseName + "," + labList.get(i).lecture + "," + labList.get(i).section + ")\n";
			if (lectureList.get(i).time != null) // make sure time exists
				{out += "at(" + courseName + "," + labList.get(i).section + "," + labList.get(i).time +")\n";}
			else
				{System.err.println("Warning: missing timeslot for lab "+labList.get(i).section);}
		}
		return out;
	}
	
	
	
	// interpret 'lecture(course,section)' predicate
	public void addLecture(Predicate newLecture){
		// check for duplicate lecture declarations
		String query = newLecture.getStringParam(1);
		for (int i = 0; i < lectureList.size(); i++)
			if (lectureList.get(i).section.equals(query) == true){
				System.err.println("Warning: Duplicate declaration for lecture " + query);
				return;
			}
		Lecture lec = new Lecture(newLecture.getStringParam(1));
		ok = lectureList.add(lec);
	}
	
	// interpret 'lab(course,lecture,lab section)' predicate
	public void addLab(Predicate newLab){
		// check for duplicate lab declarations
		String query = newLab.getStringParam(2);
		for (int i = 0; i < labList.size(); i++){
			if (labList.get(i).section.equals(query) == true){
				System.err.println("Warning: Duplicate declaration for lab " + query);
				return;
			}
		}
		for (int i = 0; i < lectureList.size(); i++){
			if (lectureList.get(i).section.equals(newLab.getStringParam(1)) == true){
				System.err.println("Warning: lecture " + newLab.getStringParam(1) + " already has a lab!");
				return;
			}
		}
		Lab l = new Lab(newLab.getStringParam(1), query);
		ok = labList.add(l);
	}

	// interpret 'at(course, lec/lab, time)' predicate
	public void addTime(Predicate newTime){
		String query = newTime.getStringParam(1);
		if ((query.charAt(0) == 'L') || (query.charAt(0) == 'l')){ // lecture sections should have L instead of B
			for (int i = 0; i < lectureList.size(); i++){
				if ((lectureList.get(i).section.equals(query)) == true){
					if (lectureList.get(i).time != null)
						{System.err.println("Warning: lecture " + lectureList.get(i).section + " already has a timeslot.");}
					else 
						{lectureList.get(i).time  = newTime.getStringParam(2);}
					return;
				}
			}
			System.err.println("Warning: lecture " + query +" has not been declared.");
			Lecture newLec = new Lecture(query);
			lectureList.add(newLec);
			lectureList.get(lectureList.size() - 1).time = newTime.getStringParam(2);
		}
		else { // lab
			for (int i = 0; i < labList.size(); i++){
				if ((lectureList.get(i).section.equals(query)) == true){
					if (labList.get(i).time != null)
						{System.err.println("Warning: lab " + lectureList.get(i).section + " already has a timeslot.");}
					else 
						{labList.get(i).time  = newTime.getStringParam(2);}
					return;
				}
			}
			System.err.println("Error: lab " + query + "does not exist."); // Can't make a lab without lecture section
		}
	}

	// only used by Course objects
	private class Lecture {
		private String section;
		private String time;
		private Lecture (String a){
			section = a;
			time = null;
		}
	}
	
	// only used by Course objects
	private class Lab {
		private String lecture, section, time;
		private Lab (String a, String b){
			lecture = a;
			section = b;
			time = null;
		}
	}
}

