package domain;

public class Student {
	String sno;
	String sname;
	String ssex;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	String sclass;
	public Student(String sno, String sname, String ssex, String sclass) {
		this.sno = sno;
		this.sname = sname;
		this.ssex = ssex;
		this.sclass = sclass;
	}
}
