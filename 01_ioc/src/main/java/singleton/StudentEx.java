package singleton;

public class StudentEx {
	public static void main(String[] args) {
		// public protected() private

		// Student student = new Student();
		// System.out.println(student);

		// Factory pattern > prototype
		Student student2 = Student.getInstance();
		Student student3 = Student.getInstance();

		System.out.println(student2);
		System.out.println(student3);

		// Factory > singleton
		Student student4 = Student.getInstance2();
		Student student5 = Student.getInstance2();

		System.out.println(student4);
		System.out.println(student5);
	}
}
