package mini.service;

import static mini.util.UserCommon.nextInt;
import static mini.util.UserCommon.nextLine;
import static mini.util.UserCommon.nextAddr;
import static mini.util.UserCommon.preference;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import java.lang.Exception;

import mini.exception.*;
import mini.util.UserCommon;
import mini.vo.*;
import mail.MailSend;

public class UserServiceImpl implements UserService {

	User me;
	private List<User> users = new ArrayList<User>(); // 기본정보
	List<Note> notes = new ArrayList<>(); // 쪽지 리스트
	List<Favorite> favis = new ArrayList<>(); // 즐겨찾기 리스트
	List<StarRate> stars = new ArrayList<>(); // 별점 리스트

	{
		loadUser();
		loadNote();
		loadFavi();
		loadStar();
	}

	/**
	 * 회원가입하기
	 * 
	 * @author 김상민 아이디, 이메일 중복체크 및 정규식 적용(아이디,비밀번호 공백x)(이메일 형식체크)
	 * @throws Exception
	 */
	public void register() throws Exception { // 이름, 나이, 성별, 이메일주소, 주소
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━< 회원가입 >━━━━━━━━━━━━━━━━━━━━━━━━━");
		String id = nextLine("▷ 아이디 입력 : ");
		User user = findUserBy(id);
		if (user != null) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("아이디 있는거야");
			return;
		}
		if(!UserCommon.isValueId(id)) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("공백은 안돼");
			return;
		}
		String pw = nextLine("▷ 비밀번호 입력 : ");
		if(!UserCommon.isValuePw(pw)) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("공백 안된다니까 ㅉㅉ");
			return;
		}
		String email = nextLine("▷ 이메일주소 확인 : ");
		User mailId = findByEmail(email);
		if (mailId != null) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("이미 사용중인 이메일이야");
			return;
		}
		if (!UserCommon.isValueEmail(email)) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("이메일 잘 좀 입력해봐");
			return;
		}
		users.add(new User(id, pw, nextLine("▷ 이름 : ", true), nextInt("▷ 나이 : ", 1, 200),
				nextLine("▷ 성별 : ", '남', '여'), email, nextAddr("▷ 주소 : "), preference("▷ 키/성향입력 : ", 1)));
		saveUser();
	}

	/**
	 * @author 김상민 메일 정규식 적용하기 + 중복처리 고민
	 * @throws Exception 
	 */
	public void login() throws Exception {
		int cnt = 0;
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━< 로그인 >━━━━━━━━━━━━━━━━━━━━━━━━━━");
		while (true) {
			String id = nextLine("▷ 아이디 입력 : ");
			User user = findUserBy(id);
			if (user == null) { // 미체크시 오류
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("없는 아이디야");
				return;
			}
			String password = nextLine("▷ 비밀번호 입력 : ");
			user = findByPw(id, password);
			
			if (id.equals("admin") && password.equals("1234")) {
				adminLogin();
				return;
			}
			if (user != null) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getId().equals(id)) {
						me = users.get(i);
					}
				}
				loginMenu();
				break;
			} else {
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("로그인 실패");
				cnt++;
				if (cnt == 3) { 
					System.out.println("로그인 3회 실패 으휴");
					sendPw();
					return;
				}
			}
		}
		saveUser();
	}
	// 비밀번호 찾기 (이메일로 발송)
	public void sendPw() throws Exception {
		int no = nextInt("이메일은 아냐? ①ㅇㅇ ②ㄴㄴ");
		switch (no) {
		case 1:
			String email = nextLine("알면 써봐 : ");
			if(findByEmail(email) != null) {
				MailSend.sendMail(findByEmail(email).getPw(), email);
			}
			break;
		case 2:
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("걍 쓰지 마라");
			break;
		}
	}

	/**
	 * 관리자용 메뉴
	 * 
	 * @author 최보원
	 * @throws Exception
	 */
	// 관리자 로그인했을 경우
	public void adminLogin() throws RangeException {
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			int no = nextInt("① 유저 리스트    ② 유저 정보 수정    ③ 유저 추방    ④ 취소  ", 1, 4);
			switch (no) {
			case 1:
				adminUserList();
				break;
			case 2:
				adminModify();
				break;
			case 3:
				adminRemove();
				break;
			case 4:
				return;
			}
		}
	}

	// 유저가 로그인 했을 경우
	public void loginMenu() throws RangeException {
//		reflectScore();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(	"           ᕱ  ᕱ              \r\n" +
							"    („• - •„)     \r\n" +
							"    ┏━━∪∪━━━━━━━┓ \r\n" +
							"    ┃  반가워   ┃ \r\n" +
							"    ┗━━━━━━━━━━━┛ ");
		System.out.println(findUserBy(me.getId()).firstDisplay());
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			int input = nextInt("① 상대매칭 ② 커뮤니티 ③ 회원정보수정 ④ 회원탈퇴 ⑤ 로그아웃", 1, 5);
			switch (input) {
			case 1:
				meeting();
				break;
			case 2:
				community();
				break;
			case 3:
				modify();
				break;
			case 4:
				withDraw();
				return;
			case 5:
				return;
			}
		}
	}

	// 유저 개인 정보
	public void myList() {
		System.out.println(	" ┌───────────────────┐\r\n" +
							" ┃      내 정보      ┃\r\n" +
							" └───────────────────┘\r\n" +
							"           ᕱ  ᕱ    ||        \r\n " +
							"         (  ･ω･ )  ||          \r\n" +
							"     /     つΦ         \r\n");
		System.out.println(findUserBy(me.getId()));
	}

	/**
	 * 본인 회원 정보 수정하기
	 * 
	 * @author 최보원
	 * @throws Exception
	 */
	// 유저 개인 정보 수정
	public void modify() throws RangeException {
		myList();
		while(true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━< 정보 수정 >━━━━━━━━━━━━━━━━━━━━━━━━━");
			int no = nextInt("①id ②pw ③이름 ④나이 ⑤성별 ⑥이메일 ⑦주소 ⑧성향 ⑨수정그만", 1, 9);
			switch (no) {
			case 1:
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("아이디는 못 바꿔 ㅋ");
				break;
			case 2:
				me.setPw(nextLine("▷ 수정할 pw = "));
				break;
			case 3:
				me.setName(nextLine("▷ 수정할 이름 = "));
				break;
			case 4:
				me.setAge(nextInt("▷ 수정할 나이 = "));
				break;
			case 5:
				me.setGender(nextLine("▷ 수정할 성별 = "));
				break;
			case 6:
				me.setEmail(nextLine("▷ 수정할 이메일 = "));
				break;
			case 7:
				me.setAddress(nextLine("▷ 수정할 주소 = "));
				break;
			case 8:
				me.setPrefs(preference("▷ 성향 수정 = ", 0));
				break;
			case 9:
				return;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(findUserBy(me.getId()));
			saveUser();
		}
	}

	/**
	 * 회원탈퇴하기<br>
	 * 최종 확인 수정 필요 로그인 후 화면에서 보여질메뉴
	 * 
	 * @author 김상민
	 */
	public void withDraw() throws RangeException { // 회원탈퇴
		int s = nextInt("정말 탈퇴? ① ㅇㅇ ② ㄴㄴ", 1, 2);
		switch (s) {
		case 1:
			String id = nextLine("▷ 아이디 입력 : ");
			User user = findUserBy(id);
			if (user == null) {// 미체크시 오류
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("아이디 틀렸어");
			}
			String password = nextLine("▷ 비밀번호 입력 : ");
			user = findByPw(id, password);
			if (user != null) {
				users.remove(findUserBy(id));
				System.out.println("   ꓕ              ꓕ      ꓯ   ꓕ");
				System.out.println(" O ꓩ   ꓵ ꓩ  O H ꓕ  O ꓩ  ꓶ I");
				System.out.println("  O   O  O  ꓶ          Z");
				System.out.println();
				break;
			} else {
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("응 탈퇴 안돼");
			}
			return;
		case 2:
			break;
		}
		saveUser();
	}

	// 관리자가 볼 수 있는 전체 유저
	public void adminUserList() {
		int cnt = 0;
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━< 회원 리스트 >━━━━━━━━━━━━━━━━━━━━━━━━");
		for (User u : users) {
			cnt++;
			System.out.println(cnt + ". " + "\n" + findUserBy(u.getId()).adminToString());
		}
	}

	// 관리자가 유저 수정
	public void adminModify() throws RangeException {
		adminUserList();
		String id = nextLine("▷ 수정할 id : ");
		User user = findUserBy(id);
		if (user == null) {
			System.out.println("존재하지 않는 id");
			return;
		}
		int no = nextInt("①id ②pw ③이름 ④나이 ⑤성별 ⑥이메일 ⑦주소 ⑧성향", 1, 8);
		switch (no) {
		case 1:
			user.setId(nextLine("▷ 수정할 id : "));
			break;
		case 2:
			user.setPw(nextLine("▷ 수정할 pw : "));
			break;
		case 3:
			user.setName(nextLine("▷ 수정할 이름 : "));
			break;
		case 4:
			user.setAge(nextInt("▷ 수정할 나이 : "));
			break;
		case 5:
			user.setGender(nextLine("▷ 수정할 성별 : "));
			break;
		case 6:
			user.setEmail(nextLine("▷ 수정할 이메일 : "));
			break;
		case 7:
			user.setAddress(nextLine("▷ 수정할 주소 : "));
			break;
		case 8:
			user.setPrefs(preference("▷ 성향 수정", 0));
		}
		saveUser();
	}

	/**
	 * 커뮤니티
	 * 
	 * @author 최보원
	 * @throws Exception
	 */
	// 관리자가 유저 삭제
	public void adminRemove() {
		String id = nextLine("삭제할 아이디 : ");
		users.remove(findUserBy(id));
		saveUser();
	}

	// 커뮤니티 메뉴
	public void community() throws RangeException {
		while(true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			int no = nextInt("① 매칭이력 ② 쪽지 ③ 취소", 1, 3);
			switch (no) {
			case 1:
				pastMeeting();
				break;
			case 2:
				note();
				break;
			case 3:
				return;
			}
		}
	}

	/**
	 * 상대 매칭
	 * 
	 * @author 이창연
	 */

	public void meeting() {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━< 상대매칭 >━━━━━━━━━━━━━━━━━━━━━━━━━");
		int no = nextInt("① 성향탐색매칭 ② 랜덤매칭 ③ 취소", 1, 3);
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		switch (no) {
		case 1:
			boolean[][] myWish = preference("성향 선택해서 원하는 회원을 찾아봐.", 1);
//			HashSet<User> prevList = new HashSet<>();
			LinkedHashSet<User> nextList = new LinkedHashSet<>();
//			HashSet<User> removeList = new HashSet<>();

			for (User u : users) {
				for (int i = 0; i < myWish.length; i++) {
					if (me.getGender().equals(u.getGender())) {
						break;
					}
					for (int j = 0; j < myWish[i].length; j++) {
						if (myWish[i][j] && u.getMyPrefs()[i][j]) {
							nextList.add(u);
						}
					}
				}
			}

			Iterator<User> iter = nextList.iterator();
			int random = (int) (Math.random() * nextList.size());
			for (int i = 0; i < nextList.size(); i++) {
				if (i == random) {
					User u1 = iter.next();
					System.out.println(	"    ♡  ♡ ᕬ ᕬ  ♡  ♡\r\n" + 
										"  + ♡ ( ⌯＇^＇ ) ♡  +\r\n" + 
										"   ┏━━━♡━ U U━♡━━━┓\r\n" + 
										"   ♡   매칭 결과　♡\r\n" + 
										"   ┗━━━♡━━━━━━♡━━━┛");
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println(u1.matchDisplay());
					System.out.println(fromTo(me, u1));
					addFavi(u1);
				} else {
					iter.next();
				}
			}
			break;
//			for (User u : users) {
//				for (int i = 0; i < myWish.length; i++) {
//					if (me.getGender().equals(u.getGender())) {
//						break;
//					}
//					for (int j = 0; j < myWish[i].length; j++) {
//						if (i == 1 && myWish[i][j] && u.getMyPrefs()[i][j]) {
//							prevList.add(u);
//							nextList.add(u);
//						}
//						else if (i != 1 && myWish[i][j] && u.getMyPrefs()[i][j]) {
//							nextList.add(u);
//							nextList.retainAll(prevList);
//							prevList = nextList;
//						}
//						else {
//							removeList.add(u);
//							nextList.removeAll(removeList);
//							removeList.clear();
//						}
//					}
//				}
//			}
		case 2:
			randomMeeting();
			break;
		case 3:
			return;

		}
	}

	public void randomMeeting() {
		List<User> rdUsers = new ArrayList<User>();
		int count = 0;
		for (User u : users) {
			if (!(me.getGender()).equals(u.getGender()) || !(u.equals(users.get(0)))) {
				rdUsers.add(u);
			}
		}
		int random2 = (int) (Math.random() * rdUsers.size());
		for (User u1 : rdUsers) {
			count++;
			if (count == random2) {
				System.out.println(	"    ♡  ♡ ᕬ ᕬ  ♡  ♡\r\n" + 
									"  + ♡ ( ⌯＇^＇ ) ♡  +\r\n" + 
									"   ┏━━━♡━ U U━♡━━━┓\r\n" + 
									"   ♡   매칭 결과　♡\r\n" + 
									"   ┗━━━♡━━━━━━♡━━━┛");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println(u1.matchDisplay());
				System.out.println(fromTo(me, u1));
				addFavi(u1);
			}
		}
	}

	/**
	 * @author 최보원
	 * @throws Exception
	 */
	// 과거 매칭 이력 중 즐겨찾기 수정, 별점 주기

	public void pastMeeting() throws RangeException {
		while(true) {
			meetingList();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			int no = nextInt("① 즐겨찾기 추가/제거 ② 별점 주기 ③ 취소", 1, 3);
			switch (no) {
			case 1:
				modifyFavi();
				break;
			case 2:
				starScore();
				break;
			case 3:
				return;
			}
		}
	}

	/**
	 * @author 최보원
	 * @throws Exception
	 */
	// 쪽지 메뉴
	public void note() throws RangeException {
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			int no = nextInt("① 쪽지 보내기 ② 보낸 쪽지함 ③ 받은 쪽지함 ④ 쪽지 삭제 ⑤ 취소", 1, 5);
			switch (no) {
			case 1:
				send();
				break;
			case 2:
				noteSendList();
				break;
			case 3:
				noteReceiveList();
				break;
			case 4:
				noteRemove();
				break;
			case 5:
				return;
			}
		}
	}

	// 쪽지 보내기
	public void send() {
		meetingList();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		String id = nextLine("▷ 받는 사람 아이디 : ");
		User user = findUserBy(id);
		if (user == null) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("없는 아이디인데?");
			return;
		}
		String content = nextLine("▷ 쪽지 내용 : ");
		notes.add(new Note(me.getId(), id, content, new Date()));
		saveNote();
	}

	// 보낸 쪽지함
	public void noteSendList() {
		if(findNoteBySender(me.getId()).size() != 0) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━< 보낸 쪽지 >━━━━━━━━━━━━━━━━━━━━━━━━━");
			printNoteArray(findNoteBySender(me.getId()));
		} else {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("            ╭◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡ ◜◝╮");
			System.out.println("                  보낸 쪽지가 없는데     ");
			System.out.println("   Δ~~~Δ    ╰◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
			System.out.println("  ξ ･ェ･ ξ       O");
			System.out.println("  ξ  ~  ξ     °");
			System.out.println("  ξ     ξ    ");
			System.out.println("  ξ     “~～~～O");
			System.out.println("  ξ             ξ");
			System.out.println("  ξ ξ ξ~～~ξ ξ");
			System.out.println("  ξ_ξ_ξ  ξ_ξ_ξ");
		}
	}

	// 받은 쪽지함
	public void noteReceiveList() {
		if(findNoteByReceiver(me.getId()).size() != 0) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━< 받은 쪽지 >━━━━━━━━━━━━━━━━━━━━━━━━━");
			printNoteArray(findNoteByReceiver(me.getId()));
		} else {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("            ╭◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡ ◜◝╮");
			System.out.println("                  받은 쪽지가 없는데     ");
			System.out.println("   Δ~~~Δ    ╰◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
			System.out.println("  ξ ･ェ･ ξ       O");
			System.out.println("  ξ  ~  ξ     °");
			System.out.println("  ξ     ξ    ");
			System.out.println("  ξ     “~～~～O");
			System.out.println("  ξ             ξ");
			System.out.println("  ξ ξ ξ~～~ξ ξ");
			System.out.println("  ξ_ξ_ξ  ξ_ξ_ξ");
		}
	}

	// 쪽지 전체 리스트
	public void printNoteArray(List<Note> notes) {
		int cnt = 0;
		for (Note n : notes) {
			cnt++;
			System.out.println(cnt + ". " + n);
		}
	}

	// 쪽지 삭제
	public void noteRemove() throws RangeException{
		if (findNoteBySender(me.getId()).size() != 0 || findNoteByReceiver(me.getId()).size() != 0) {
			while(true) {
				int kind = nextInt("① 보낸 쪽지 삭제 ② 받은 쪽지 삭제 ③ 취소", 1, 3);
				switch (kind) {
				case 1:
					noteSendList();
					int no = nextInt("▷ 삭제할 번호 >");
					notes.remove(findNoteBySender(me.getId()).get(no-1));
					break;
				case 2:
					noteReceiveList();
					int no2 = nextInt("▷ 삭제할 번호 >");
					notes.remove(findNoteByReceiver(me.getId()).get(no2 - 1));
					break;
				case 3:
					return;
				}
			}
		} else {
			System.out.println("            ╭◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡◜◝͡ ◜◝╮");
			System.out.println("                삭제할 쪽지가 없는데     ");
			System.out.println("   Δ~~~Δ    ╰◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
			System.out.println("  ξ ･ェ･ ξ       O");
			System.out.println("  ξ  ~  ξ     °");
			System.out.println("  ξ     ξ    ");
			System.out.println("  ξ     “~～~～O");
			System.out.println("  ξ             ξ");
			System.out.println("  ξ ξ ξ~～~ξ ξ");
			System.out.println("  ξ_ξ_ξ  ξ_ξ_ξ");
		}
		saveNote();
	}

	// 유저 리스트 중 id에 맞는 유저정보 찾기
	public User findUserBy(String id) {
		for (User u : users) {
			if (id.equals(u.getId())) {
				return u;
			}
		}
		return null;
	}

	// 유저 리스트 중 이메일에 맞는 유저
	public User findByEmail(String email) {
		for (User u : users) {
			if (email.equals(u.getEmail())) {
				return u;
			}
		}
		return null;
	}

	// 유저 리스트 중 id,pw 둘 다 일치하는 정보 찾기
	public User findByPw(String id, String pw) {
		for (User u : users) {
			if (id.equals(u.getId()) && pw.equals(u.getPw())) {
				return u;
			}
		}
		return null;
	}

	// 노트 리스트 중 보낸사람 일치한 정보 찾기
	public List<Note> findNoteBySender(String sender) {
		List<Note> list = new ArrayList<>();
		for (Note n : notes) {
			if (n.getSender().equals(sender)) {
				list.add(n);
			}
		}
		return list;
	}

	// 노트 리스트 중 받은사람 일치한 정보 찾기
	public List<Note> findNoteByReceiver(String receiver) {
		List<Note> list = new ArrayList<>();
		for (Note n : notes) {
			if (n.getReceiver().equals(receiver)) {
				list.add(n);
			}
		}
		return list;
	}

	// 즐겨찾기 등록
	public void addFavi(User user) throws RangeException {
		System.out.println("        ☆    ★");
		System.out.println("    ＊    ＿＿＿ +");
		System.out.println("★   +  ／) ⌒  ⌒ ＼   ");
		System.out.println("+ _ヘ／／ (●) ＜  ヽ ");
		System.out.println("／ﾋﾉﾉ _二⊃(_人_)   | 찡-긋");
		System.out.println("     < +   ヽノ     ノ 징-글");
		System.out.println("／￣＼) ＊        ＼");
		int input = nextInt("즐겨찾기 등록할거? ① ㅇㅇ ② ㄴㄴ", 1, 2);
		switch (input) {
		case 1:
			favis.add(new Favorite(true, me.getId(), user.getId(), user.getName(),
					user.getAge()));
			break;
		case 2:
			favis.add(new Favorite(false, me.getId(), user.getId(), user.getName(),
					user.getAge()));
			break;
		}
		saveFavi();
	}
	// 즐겨찾기 수정
	public void modifyFavi() throws RangeException {
		String id = nextLine("▷ 즐겨찾기 바꿀 사람 아이디 : ");
		Favorite favi = findFaviByYou(id);
		if (favi == null) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("아이디가 없어");
			return;
		}
		if (findFaviByOther(id).get(0).isFavi()) {
			System.out.println("     ｡・｡ ∧_∧ ｡・｡      ");
			System.out.println("  ｡ﾟ    (  ﾟ´Д｀)   ﾟ｡   ");
			System.out.println("     o( U U        ");
			System.out.println("        'ｰ'ｰ'            ");
			int input = nextInt("즐겨찾기 해제? ①ㅇㅇ ②ㄴㄴ", 1, 2);
			if (input == 1) {
				favi.setFavi(false);
			} else
				return;
		} else {
			System.out.println("             ♪ ∧ , , ∧");
			System.out.println("       ♪ ∧ , , ∧ ・  ω ・)");
			System.out.println("   ∧ , , ∧ ・ ω ・)     )っ");
			System.out.println("  ( ・  ω ・ )      )っ＿_フ");
			System.out.println("  (っ    )っ＿_フ(_/彡");
			System.out.println("   ( ＿_フ(_/彡");
			System.out.println("    (_/ 彡 ♪");
			int input = nextInt("즐겨찾기 등록? ①ㅇㅇ ②ㄴㄴ", 1, 2);
			if (input == 1) {
				favi.setFavi(true);
			} else
				return;
		}
		saveFavi();
	}

	// 즐겨찾기 리스트 중 id와 일치하는 정보 찾기
	public Favorite findFaviByMe(String id) {
		for (Favorite f : favis) {
			if (id.equals(f.getId())) {
				return f;
			}
		}
		return null;
	}
	
	public Favorite findFaviByYou(String id) {
		for (Favorite f : favis) {
			if (f.getOtherUser().equals(id)) {
				return f;
			}
		}
		return null;
	}

	public List<Favorite> findFaviByMine(String myId) {
		List<Favorite> list = new ArrayList<>();
		for (Favorite f : favis) {
			if (f.getId().equals(myId)) {
				list.add(f);
			}
		}
		return list;
	}
	
	public List<Favorite> findFaviByOther(String otherUser) {
		List<Favorite> list = new ArrayList<>();
		for (Favorite f : favis) {
			if (f.getOtherUser().equals(otherUser)) {
				list.add(f);
			}
		}
		return list;
	}

	/**
	 * 매칭이력 리스트 <br>
	 * 
	 * @author 최보원
	 */
	// 과거 매칭 이력
	public void meetingList() {
		for (User u : users) {
			reflectScore(u);
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━< 매칭이력 >━━━━━━━━━━━━━━━━━━━━━━━━━");
		for (Favorite f : favis) {
			if (f.getId().equals(me.getId())) {
				System.out.print(f);
				System.out.println(findUserBy(f.getOtherUser()).starToString());
				return;
			}
			else if (findFaviByMe(me.getId()) == null){
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("     /)⋈/)");
				System.out.println("      (｡•ㅅ•｡) ♡");
				System.out.println(" ┏━━━━━━∪━∪━━━━━━━━━━━━━┓");
				System.out.println(" ♡   매칭 좀 해봐   *.。♡");
				System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━┛");
			}
		}
		loginMenu();
	}

	private void saveUser() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
			oos.writeObject(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadUser() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"))) {
			users = (List<User>) ois.readObject();
		} catch (Exception e) {
			String[] cd1 = {"성대로1길 18-9","126.93461229808351","37.50123190756427"};
			String[] cd2 = {"내대화길 112-11","126.51572459803326", "35.061870344999036"};
			String[] cd3 = {"기각리 860-6", "126.518478869681", "35.0646657393649"};
			boolean[][] pref1 = {
				{false, true, false, false, false, false},
				{false, true},
				{false, false, true, false, false},
				{true, false},
				{false, false, false, true, false, false},
				{false, false, false, true, false}
				};
			boolean[][] pref2 = {
				{false, false, true, false, false, false},
				{false, true},
				{false, false, true, false, false},
				{false, true},
				{false, true, false, false, false, false},
				{false, false, false, true, false}
				};
			boolean[][] pref3 = {
				{false, false, false, true, false, false},
				{true, false},
				{false, false, false, false, true},
				{false, true},
				{false, true, false, false, false, false},
				{false, true, false, false, false}
				};
			boolean[][] pref4 = {
				{false, false, false, true, false, false},
				{false, true},
				{false, false, false, true, false},
				{false, true},
				{false, false, false, false, false, true},
				{true, false, false, false, false}
				};
			boolean[][] pref5 = {
				{false, true, false, false, false, false},
				{true, false},
				{false, true, false, false, false},
				{true, false},
				{false, false, false, false, true, false},
				{true, false, false, false, false}
				};
			boolean[][] pref6 = {
				{false, false, false, true, false, false},
				{true, false},
				{true, false, false, false, false},
				{false, true},
				{false, true, false, false, false, false},
				{true, false, false, false, false}
				};
			boolean[][] pref7 = {
				{false, false, true, false, false, false},
				{true, false},
				{false, false, false, true, false},
				{true, false},
				{false, false, true, false, false, false},
				{false, true, false, false, false}
				};
			boolean[][] pref8 = {
				{false, false, false, true, false, false},
				{false, true},
				{false, false, false, true, false},
				{false, true},
				{false, false, false, true, false, false},
				{false, true, false, false, false}
				};
			boolean[][] pref9 = {
				{false, true, false, false, false, false},
				{true, false},
				{false, false, false, true, false},
				{true, false},
				{true, false, false, false, false, false},
				{false, false, true, false, false}
				};
			boolean[][] pref10 = {
				{false, false, false, true, false, false},
				{true, false},
				{false, false, false, false, true},
				{true, false},
				{false, false, true, false, false, false},
				{false, false, false, true, false}
				};
			boolean[][] pref11 = {
				{false, false, false, true, false, false},
				{false, true},
				{true, false, false, false, false},
				{true, false},
				{false, false, false, false, false, true},
				{true, false, false, false, false}
				};
			boolean[][] pref12 = {
				{false, false, true, false, false, false},
				{true, false},
				{false, false, false, true, false},
				{true, false},
				{false, false, false, false, false, true},
				{true, false, false, false, false}
				};
			boolean[][] pref13 = {
				{false, false, false, false, false, true},
				{true, false},
				{false, false, true, false, false},
				{false, true},
				{false, false, false, false, true, false},
				{false, false, false, false, true}
				};

			boolean[][] pref14 = {
				{false, false, true, false, false, false},
				{false, true},
				{false, false, false, false, true},
				{true, false},
				{false, false, false, false, true, false},
				{false, false, false, true, false}
				};

			boolean[][] pref15 = {
				{false, true, false, false, false, false},
				{false, true},
				{false, false, false, true, false},
				{true, false},
				{true, false, false, false, false, false},
				{true, false, false, false, false}
				};
			boolean[][] pref16 = {
				{false, true, false, false, false, false},
				{false, true},
				{false, false, true, false, false},
				{false, true},
				{false, false, false, false, false, true},
				{false, false, false, true, false}
				};
			boolean[][] pref17 = {
				{false, true, false, false, false, false},
				{false, true},
				{false, false, true, false, false},
				{false, true},
				{false, false, false, false, false, true},
				{true, false, false, false, false}
				};
			boolean[][] pref18 = {
				{false, false, false, true, false, false},
				{true, false},
				{false, false, true, false, false},
				{true, false},
				{false, false, false, true, false, false},
				{false, false, false, true, false}
				};
			boolean[][] pref19 = {
				{false, false, false, false, false, true},
				{true, false},
				{false, false, false, false, true},
				{false, true},
				{false, false, true, false, false, false},
				{false, true, false, false, false}
				};
			boolean[][] pref20 = {
				{true, false, false, false, false, false},
				{false, true},
				{false, false, true, false, false},
				{true, false},
				{false, false, false, false, true, false},
				{false, false, false, false, true}
				};
			boolean[][] pref21 = {
				{false, false, false, false, false, true},
				{true, false},
				{false, true, false, false, false},
				{false, true},
				{false, false, false, false, false, true},
				{false, true, false, false, false}
				};
			boolean[][] pref22 = {
				{false, false, false, false, false, true},
				{false, true},
				{false, true, false, false, false},
				{false, true},
				{false, false, false, true, false, false},
				{true, false, false, false, false}
				};
			users.add(new User("admin", "1234", true));
			users.add(new User("1111", "1111", "엄준식", 20, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref1));
			users.add(new User("2222", "2222", "하승진", 21, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref2));
			users.add(new User("3333", "3333", "이의지", 22, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref3));
			users.add(new User("4444", "4444", "이상순", 23, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref4));
			users.add(new User("5555", "5555", "김민준", 21, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref5));
			users.add(new User("6666", "6666", "김서연", 22, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref6));
			users.add(new User("7777", "7777", "김서준", 23, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref7));
			users.add(new User("8888", "8888", "이서준", 24, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref8));
			users.add(new User("9999", "9999", "이서윤", 25, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref9));
			users.add(new User("1122", "1122", "박예준", 26, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref10));
			users.add(new User("2233", "2233", "박지우", 27, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref11));
			users.add(new User("3344", "3344", "정도윤", 28, "남자", "sangrn_n@kakao.com", cd3, 0.0, pref12));
			users.add(new User("4455", "4455", "정서현", 29, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref13));
			users.add(new User("5566", "5566", "최시우", 30, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref14));
			users.add(new User("6677", "6677", "최민서", 31, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref15));
			users.add(new User("7788", "7788", "조주원", 32, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref16));
			users.add(new User("8899", "8899", "조하은", 33, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref17));
			users.add(new User("9900", "9900", "강하준", 34, "남자", "sangrn_n@kakao.com", cd3, 0.0, pref18));
			users.add(new User("1010", "1010", "강하윤", 35, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref19));
			users.add(new User("1011", "1011", "윤지호", 36, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref20));
			users.add(new User("1012", "1012", "윤윤서", 37, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref21));
			users.add(new User("1013", "1013", "장지후", 38, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref22));
			users.add(new User("1014", "1014", "장지유", 39, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref1));
			users.add(new User("1015", "1015", "임준서", 20, "남자", "sangrn_n@kakao.com", cd3, 0.0, pref2));
			users.add(new User("1016", "1016", "임지민", 21, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref3));
			users.add(new User("1017", "1017", "신준우", 22, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref4));
			users.add(new User("1018", "1018", "신채원", 23, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref5));
			users.add(new User("1019", "1019", "유현우", 24, "남자", "sangrn_n@kakao.com", cd1, 0.0, pref6));
			users.add(new User("1020", "1020", "유지윤", 25, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref7));
			users.add(new User("1021", "1021", "한도현", 26, "남자", "sangrn_n@kakao.com", cd3, 0.0, pref8));
			users.add(new User("1022", "1022", "한은서", 27, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref9));
			users.add(new User("1023", "1023", "오지훈", 28, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref10));
			users.add(new User("1024", "1024", "오수아", 29, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref11));
			users.add(new User("1025", "1025", "오정아", 29, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref12));
			users.add(new User("1026", "1026", "사비 에르난데스", 41, "남자", "sangrn_n@kakao.com", cd2, 0.0, pref13));
			users.add(new User("1027", "1027", "페르난도 토레스", 37, "남자", "sangrn_n@kakao.com", cd3, 0.0, pref14));
			users.add(new User("1028", "1028", "에바 그린", 41, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref15));
			users.add(new User("1029", "1029", "스칼렛 요한슨", 36, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref16));
			users.add(new User("1030", "1030", "앤 해서웨이", 38, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref17));
			users.add(new User("1031", "1031", "키이라 나이틀리", 36, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref18));
			users.add(new User("1032", "1032", "아만다 사이프리드", 35, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref19));
			users.add(new User("1033", "1033", "제니퍼 로렌스", 30, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref20));
			users.add(new User("1034", "1034", "애나 캔드릭", 35, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref21));
			users.add(new User("1035", "1035", "엘르 패닝", 23, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref22));
			users.add(new User("1036", "1036", "레이첼 맥아담스", 42, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref1));
			users.add(new User("1037", "1037", "마고 로비", 31, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref2));
			users.add(new User("1038", "1038", "고마츠 나나", 25, "여자", "sangrn_n@kakao.com", cd2, 0.0, pref3));
			users.add(new User("1039", "1039", "다코타 존슨", 31, "여자", "sangrn_n@kakao.com", cd3, 0.0, pref4));
			users.add(new User("1040", "1040", "애슐리 그린", 34, "여자", "sangrn_n@kakao.com", cd1, 0.0, pref5));
//			e.printStackTrace();
		}
	}

	private void saveNote() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("note.ser"))) {
			oos.writeObject(notes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadNote() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("note.ser"))) {
			notes = (List<Note>) ois.readObject();
		} catch (Exception e) {
			notes.add(new Note("1111", "2222", "hi", new Date()));
			notes.add(new Note("2222", "3333", "hi", new Date()));
			notes.add(new Note("3333", "1111", "hi", new Date()));
			notes.add(new Note("4444", "2222", "hi", new Date()));
//			e.printStackTrace();
		}
	}

	private void saveFavi() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("favi.ser"))) {
			oos.writeObject(favis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadFavi() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("favi.ser"))) {
			favis = (List<Favorite>) ois.readObject();
		} catch (Exception e) {
			favis.add(new Favorite(true, "1111", "2222", "일길동", 20));
			favis.add(new Favorite(false, "2222", "3333", "이길동", 21));
			favis.add(new Favorite(true, "3333", "4444", "삼길동", 22));
			favis.add(new Favorite(false, "4444", "1111", "사길동", 23));
//			e.printStackTrace();
		}
	}

	private void loadStar() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("star.ser"))) {
			stars = (List<StarRate>) ois.readObject();
		} catch (Exception e) {
			stars.add(new StarRate("1111", "4444", 4));
			stars.add(new StarRate("2222", "3333", 3));
			stars.add(new StarRate("3333", "1111", 1));
			stars.add(new StarRate("4444", "2222", 5));
//			e.printStackTrace();
		}
	}

	/**
	 * @author 이창연
	 * @throws Exception
	 */
	public void starScore() throws RangeException {
		for (User u : users) {
			reflectScore(u);
		}
		meetingList();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		String id = nextLine("▷ 별점을 줄 회원 아이디 : ");
		User user = findUserBy(id);
		if (user == null) {
			System.out.println( "     /＼      ／∧\r\n" + 
					"    / ∧ ＞.―＜/  |\r\n" + 
					"   //／  ´      uヽ\r\n" + 
					"  ｜ u   ○    ○    |\r\n" + 
					"  /         ▼ヽ.    u ヽ\r\n" + 
					"｜  u    ヽ_人_ﾉ         |\r\n" + 
					"  丶             |＿|    u ﾉ\r\n" + 
					"  ./＼u           ／)\r\n" + 
					"  / ＼二二つ⊂二二\"〟");
			System.out.println("없는 사람이잖아");
			return;
		}
		for (StarRate sr : stars) {
			if (sr.getRaterId().equals(me.getId())) {
				System.out.println( "     /＼      ／∧\r\n" + 
						"    / ∧ ＞.―＜/  |\r\n" + 
						"   //／  ´      uヽ\r\n" + 
						"  ｜ u   ○    ○    |\r\n" + 
						"  /         ▼ヽ.    u ヽ\r\n" + 
						"｜  u    ヽ_人_ﾉ         |\r\n" + 
						"  丶             |＿|    u ﾉ\r\n" + 
						"  ./＼u           ／)\r\n" + 
						"  / ＼二二つ⊂二二\"〟");
				System.out.println("이미 별점줬어");
				return;
			}
		}
		double starScore = nextInt("▷ 별점을 입력 : ", 1, 5);
		
		stars.add(new StarRate(me.getId(), id, starScore));
		for (User u : users) {
			reflectScore(u);
		}
		saveScore();
	}

	/**
	 * @author 이창연
	 */
	private void saveScore() {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("star.ser"))) {
			oos.writeObject(stars);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author 이창연
	 */
	public void reflectScore(User user) {
		double totalScore = 0;
		double avgScore = 0;
		int cnt = 0;
		for (StarRate sr : stars) {
			if (sr.getRatedId().equals(user.getId())) {
				cnt++;
				totalScore += sr.getStar();
			}
		}
		avgScore = totalScore / cnt;
		user.setScore(avgScore);
	}

	/**
	 * @author 이창연
	 */
	public void checkScore() {
		System.out.println(me.firstDisplay());
	}

	/**
	 * 좌표로 거리 탐색
	 * 
	 * @author 이창연
	 * @param 경위도 좌표(x:경도,y:위도)
	 * @return 거리(m or km)
	 */
	public String fromTo(User u1, User u2) {

		String[] coord1 = u1.getCoordination();
		String[] coord2 = u2.getCoordination();
//		String[] coord3 = users.get(3).getCoordination();

		double theta = Double.parseDouble(coord1[1]) - Double.parseDouble(coord2[1]);
		double lat1 = Double.parseDouble(coord1[2]);
		double lat2 = Double.parseDouble(coord2[2]);

//		double theta = Double.parseDouble(coord1[1]) - Double.parseDouble(coord3[1]);
//		double lat1 = Double.parseDouble(coord1[2]);
//		double lat2 = Double.parseDouble(coord3[2]);

//		double theta = Double.parseDouble(coord2[1]) - Double.parseDouble(coord3[1]);
//		double lat1 = Double.parseDouble(coord2[2]);
//		double lat2 = Double.parseDouble(coord3[2]);

		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1609.344;

		if (dist >= 1000)
			return " 나와의 거리 : " + (int) (dist / 10) / 100.0 + "km";
		else
			return " 나와의 거리 : " + (int) (dist * 100) / 100.0 + "m";
	}

	/**
	 * 십진수도 >> radian 변환
	 * 
	 * @author 이창연
	 */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * radian >> 십진수도 변환
	 * 
	 * @author 이창연
	 */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
