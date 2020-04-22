package GPACalculator;

import java.util.ArrayList;

public class GPACalculator {
	
	// fields
	private ArrayList<GradePair> gradeList;
	
	// constructor
	public GPACalculator() {
		ArrayList<GradePair> grades = new ArrayList<GradePair>(0);
		this.gradeList = grades;
	}
	
	// methods
	public void add(GradePair newGrade) {
		this.gradeList.add(newGrade);
	}
	
	public void remove(int position) {
		for (int i = 0; i < this.gradeList.size(); i++) {
			if (i == position - 1) {
				this.gradeList.remove(i);
			}
		}
	}
	
	public void clearAll() {
		ArrayList<GradePair> grades = new ArrayList<GradePair>(0);
		this.gradeList = grades;
	}
  	
	/**
	 *  Based on the formula given on McGill website:
	 *  
	 *  GPA = SUM(course credits * GPA) / SUM(valid course credit)
	 *  
	 *  So the numerator is the sum of course credits * course GPA;
	 *  and the denominator is the sum of all credits;
	 */
	
	public double allCalculate() {
		double numerator = 0;
		double denominator = 0;
		for (GradePair pair: this.gradeList) {
			numerator += pair.getCredit() * letterGradeConverter(pair.getGrade());
			denominator += pair.getCredit();
		}
		return (numerator / denominator);
	}
	
	public double partialCalculate(double curGPA, int curCreds) {
		double numerator = curGPA * curCreds;
		double denominator = curCreds;
		for (GradePair pair: this.gradeList) {
			numerator += pair.getCredit() * letterGradeConverter(pair.getGrade());
			denominator += pair.getCredit();
		}
		return (numerator / denominator);
	}
	
	/*
	 *  Convert letter grade to GPA value.
	 */
	
	public static double letterGradeConverter(String c) {
		if (c.toUpperCase() == "A") {
			return 4.0;
		}
		else if (c.toUpperCase() == "A-") {
			return 3.7;
		}
		else if (c.toUpperCase() == "B+") {
			return 3.3;
		}
		else if (c.toUpperCase() == "B") {
			return 3.3;
		}
		else if (c.toUpperCase() == "B-") {
			return 2.7;
		}
		else if (c.toUpperCase() == "C+") {
			return 2.3;
		}
		else if (c.toUpperCase() == "C") {
			return 2.0;
		}
		else if (c.toUpperCase() == "D") {
			return 1.0;
		}
		else if (c.toUpperCase() == "F") {
			return 0.0;
		}
		else {
			throw new IllegalArgumentException ("The input is not a legal letter grade!");
		}
	}
	
	/**
	 *  Format:
	 *  
	 *  1. Credit: 3 || Grade: 4.0
	 *  
	 *  2. ...
	 *  
	 *  3. ...
	 *  
	 *  ...
	 *  
	 *  Your total GPA is: 4.0
	 */
	
	public void allDisplay() {
		for (int i = 0; i < this.gradeList.size(); i++) {
			int p = i + 1;
			String position = "" + p;
			System.out.println(position + ". " + "Credit: " + this.gradeList.get(i).getCredit() + 
					" || Grade: " + this.gradeList.get(i).getGrade() + "\n");
		}
		System.out.println("-------------------------------------" + "\n");
		System.out.println("Your total GPA is: " + this.allCalculate());
	}
	
	/**
	 *  Format:
	 *  
	 *  Your current GPA: 4.0 || current credits: 13
	 *  
	 *  1. Credit: 3 || Grade: 4.0
	 *  
	 *  2. ...
	 *  
	 *  3. ...
	 *  
	 *  ...
	 *  
	 *  Your expected GPA is: 4.0
	 */
	
	public void partialDisplay(double curGPA, int curCreds) {
		System.out.println("Your current GPA is: " + curGPA + " || current credits: " + curCreds + "\n");
		System.out.println("You add the following grade pair to your calculator:" + "\n");
		for (int i = 0; i < this.gradeList.size(); i++) {
			int p = i + 1;
			String position = "" + p;
			System.out.println(position + ". " + "Credit: " + this.gradeList.get(i).getCredit() + 
					" || Grade: " + this.gradeList.get(i).getGrade() + "\n");
		}
		System.out.println("-------------------------------------" + "\n");
		System.out.println("Your expected GPA is: " + this.partialCalculate(curGPA, curCreds));
	}
	
	public static void testAll1() {
		GradePair a = new GradePair(3, "A");
		GradePair b = new GradePair(3, "A");
		GradePair c = new GradePair(3, "A");
		GradePair d = new GradePair(4, "A");
		GradePair e = new GradePair(3, "A-");
		GradePair f = new GradePair(4, "A");
		GradePair g = new GradePair(4, "A");
		GradePair h = new GradePair(3, "A-");
		GradePair i = new GradePair(3, "B+");
		
		GPACalculator cal = new GPACalculator();
		
		cal.add(a);
		cal.add(b);
		cal.add(c);
		cal.add(d);
		cal.add(e);
		cal.add(f);
		cal.add(g);
		cal.add(h);
		cal.add(i);
		
		cal.allDisplay();
	}
	
	public static void testPartial1() {
		GradePair a = new GradePair(3, "A");
		GradePair b = new GradePair(3, "A");
		GradePair c = new GradePair(3, "B");
		GradePair d = new GradePair(3, "B");
		GradePair e = new GradePair(3, "B");
		
		GPACalculator cal = new GPACalculator();
		
		cal.add(a);
		cal.add(b);
		cal.add(c);
		cal.add(d);
		cal.add(e);
		
		cal.partialDisplay(1.0, 12);
	}
	

	public static void main(String[] args) {
		testPartial1();
		
	}

}
