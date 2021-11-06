package singleton;

public class Student {
	private static Student student = new Student();

	// 외부 호출 안받으려면 생성자를 private
	// 객체를 생성할때 초기화 시키는 역할

	private Student() {
	}

	public static Student getInstance() {
		return new Student();
	}

	public static Student getInstance2() {
		return student;
	}
}