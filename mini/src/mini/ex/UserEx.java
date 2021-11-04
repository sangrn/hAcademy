package mini.ex;

import static mini.util.UserCommon.*;

import mini.exception.GenderException;
import mini.exception.KoreanOnlyException;
import mini.exception.RangeException;
import mini.service.*;
import mini.vo.*;
import mini.service.*;

public class UserEx {
	public static void main(String[] args) {
		UserService us = new UserServiceImpl();
		while(true) {
			try {
				execute(us);
				
			}catch(NumberFormatException e) {
				System.out.println("정확한 숫자를 입력해주세요.");
			}
			catch(KoreanOnlyException | RangeException | GenderException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println(e + "예외 발생");
//				e.printStackTrace();
			}
		}
	}
	static void execute(UserService us) throws Exception{
		System.out.println(	"      ♡  ♡  ᕬ ᕬ   ♡  ♡     \r\n" + 
							"      + ♡  ( ⌯′-′⌯) ♡ +    \r\n" + 
							"    ┏━━━♡━━━ U U ━━━♡━━━┓ \r\n" + 
							"    ♡    휴먼 소개팅    ♡ \r\n" + 
							"    ┗━━━━♡━━━━━━━━━♡━━━━┛ ");
		int input = nextInt(" ① 회원가입   ② 로그인   ③ 종료", 1, 3);
		switch (input) {
		case 1:
			us.register();
			break;
		case 2:
			us.login();
			break;
		case 3:
			System.out.println("ㅂㅂ");
			System.exit(0);
		}
	}
	
}
