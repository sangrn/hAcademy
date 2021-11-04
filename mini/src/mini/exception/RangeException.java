package mini.exception;

public class RangeException extends RuntimeException {
	int from;
	int to;
	
	public RangeException(int from, int to) {
		super(from + "부터" + to + "사이의 숫자를 입력하세요");
		this.from = from;
		this.to = to;
	}
	
}
