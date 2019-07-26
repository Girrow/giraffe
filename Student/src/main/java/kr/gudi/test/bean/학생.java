package kr.gudi.test.bean;

public class 학생 {

	protected int 번호;
	protected String 이름;
	protected 성적표 성적;
	
	public int get번호() {
		return 번호;
	}
	public void set번호(int 번호) {
		this.번호 = 번호;
	}
	public String get이름() {
		return 이름;
	}
	public void set이름(String 이름) {
		this.이름 = 이름;
	}
	public 성적표 get성적() {
		return 성적;
	}
	public void set성적(성적표 성적) {
		this.성적 = 성적;
	}
	@Override
	public String toString() {
		return 이름  + " 학생 : 번호(" + 번호 + ") \t- 성적표 " + 성적.toString();
	}
}
