package mini.service;

import java.util.List;

import mini.exception.RangeException;
import mini.vo.*;

public interface UserService {
	/**회원가입
	 * @author 김상민
	 */
	public void register() throws Exception;
	
	/**성향입력
	 * @return 
	 */
//	public boolean[][] prefRegi(String msg);
	
	/**로그인
	 * @author 김상민
	 * @throws Exception 
	 */
	public void login() throws Exception;
	
	/**관리자 메뉴
	 * @author 최보원
	 * @throws Exception 
	 */
	public void adminLogin() throws RangeException;
	
	/**유저 로그인 후 메뉴
	 * @author 최보원
	 * @throws Exception 
	 */
	public void loginMenu() throws RangeException;
	
	/**유저 본인 정보 리스트
	 * @author 최보원
	 */
	public void myList();
	
	/**유저 본인 정보 수정
	 * @author 최보원
	 */
	public void modify() throws RangeException;
	
	/**유저 회원 탈퇴
	 * @author 김상민
	 */
	public void withDraw();
	
	/**유저 리스트
	 * @author 최보원
	 */
	public void adminUserList();
	
	/**관리자 유저 정보 수정
	 * @author 최보원 
	 */
	public void adminModify() throws RangeException;
	
	/**관리자 유저 삭제
	 * @author 최보원
	 */
	public void adminRemove();
	
	/**커뮤니티 메뉴
	 * @author 최보원
	 */
	public void community() throws RangeException;
	
	/**
	 * @author 
	 */
	public void meeting();
	
	/**과거 매칭 유저 리스트 <br>
	 * 즐겨찾기 수정, 별점 주기
	 * @author 최보원
	 */
	public void pastMeeting() throws RangeException;
	
	/**쪽지 메뉴
	 * @author 최보원
	 */
	public void note() throws RangeException;
	
	/**쪽지 보내기
	 * @author 최보원
	 */
	public void send();
	
	/**쪽지 보낸 리스트
	 * @author 최보원
	 */
	public void noteSendList();
	
	/**쪽지 받은 리스트
	 * @author 최보원
	 */
	public void noteReceiveList();
	
	/**쪽지 보낸 리스트
	 * @author 최보원
	 * @param notes
	 */
	public void printNoteArray(List<Note> notes);
	
	/**쪽지 삭제
	 * @author 최보원
	 */
	public void noteRemove();
	
	/**유저 리스트 중 id에 일치하는 <br>
	 * 유저 정보 반환
	 * @author 최보원
	 * @param id
	 * @return
	 */
	User findUserBy(String id);
	
	/**유저 리스트 중 id,pw와 일치하는<br>
	 * 유저 정보 반환
	 * @author 최보원
	 * @param id, pw
	 * @return
	 */
	User findByPw(String id, String pw);
	
	/**노트 리스트 중 보낸 유저와 일치하는 <br>
	 * 정보 반환
	 * @author 최보원
	 * @param sender
	 * @return
	 */
	public List<Note> findNoteBySender(String sender);
	
	/**노트 리스트 중 받은 유저와 일치하는 <br>
	 * 정보 반환
	 * @author 최보원
	 * @param receiver
	 * @return
	 */
	public List<Note> findNoteByReceiver(String receiver);
	
	/**즐겨찾기 등록 <br>
	 * '♥' + user 정보인 경우 즐겨찾기 한 상태 <br>
	 * '♡' + user 정보인 경우 즐겨찾기 안 한 상태
	 * @author 최보원
	 */
	public void addFavi(User user) throws RangeException;
	
	/**즐겨찾기 수정
	 * @author 최보원
	 */
	public void modifyFavi() throws RangeException;
	
	/**
	 * @author 최보원
	 * @param id
	 * @return
	 */
	Favorite findFaviByMe(String id);
	
	/**매칭이력 리스트 <br>
	 * @author 최보원
	 */
	public void meetingList();
	
	/**
	 * @author 이창연
	 */
	public void starScore() throws RangeException;
	
	/**
	 * @author 이창연
	 */
	public void checkScore();
	
	/**좌표로 거리 탐색
	 * @author 이창연
	 * @param 경위도 좌표(x:경도,y:위도)
	 * @return 거리(m or km)
	 */
	public String fromTo(User u1, User u2);
	
	/**
	 * @author 김상민
	 * @param pw
	 * @param name
	 * @throws Exception
	 */
	public void sendPw() throws Exception ;
}
