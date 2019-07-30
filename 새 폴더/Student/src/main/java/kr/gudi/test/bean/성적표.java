package kr.gudi.test.bean;

public class 성적표 {
	
	protected int 국어;
	protected int 영어;
	protected int 수학;
	protected int 과학;
	
	public int get국어() {
		return 국어;
	}
	public void set국어(int 국어) {
		this.국어 = 국어;
	}
	public int get영어() {
		return 영어;
	}
	public void set영어(int 영어) {
		this.영어 = 영어;
	}
	public int get수학() {
		return 수학;
	}
	public void set수학(int 수학) {
		this.수학 = 수학;
	}
	public int get과학() {
		return 과학;
	}
	public void set과학(int 과학) {
		this.과학 = 과학;
	}
	@Override
	public String toString() {
		return "[국어=" + 국어 + ",\t영어=" + 영어 + ",\t수학=" + 수학 + ",\t과학=" + 과학 + "]";
	}
}
