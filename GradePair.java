package GPACalculator;

public class GradePair {
	
	private int credit;
	private String grade;
	
	public GradePair(int credit, String grade) {
		this.credit = credit;
		this.grade = grade;
	}
	
	public int getCredit() {
		return this.credit;
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	public int compareTo(GradePair g) {
		if (this.getCredit() == g.getCredit() && this.getGrade() == g.getGrade()) {
			return 0;
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
