package jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BoardEx {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc.xml");
		BoardService service = ctx.getBean("boardService", BoardService.class);
		System.out.println(service.getTime());
		// service.getList().forEach(System.out::println);
		
		// 글 작성
//		Board board = new Board();
//		board.setTitle("스프링에서 작성한 제목!");
//		board.setContent("ㅎㅎ");
//		service.write(board);
		
		// 글 수정
//		Board board2 = new Board();
//		board2.setBno(3L);
//		board2.setTitle("엄");
//		service.modify(board2);
		
		
		
		
		// 글 목록 조회
//		List<Map<String, Object>> obj = service.getList();
//		System.out.println(obj);
//		for (Map<String, Object> map : obj) {
//			System.out.println(map.get("BNO") + "::" + map.get("TITLE"));
//		}
		service.getListBoard().forEach(System.out::println);
		
		// 글 단일 조회
		System.out.println("=============================================================================================");
		System.out.println(service.findBy(1L));
		
		
		
		ctx.close();
	}
}
