package mini.util;

import static mini.vo.User.prefsDetails;
import static mini.util.UserCommon.nextLine;
import static mini.vo.User.prefs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import mini.exception.*;
import mini.vo.User;

public class UserCommon {
	static Scanner scanner = new Scanner(System.in);
	public static final String SERVICE_KEY = "b7b01563bc519b04b71c588c0cc6f9a7";
	public static final String MAIN_API = "https://dapi.kakao.com//v2/local/search/address.json";

	public static int nextInt(String text) {
		return Integer.parseInt(nextLine(text));
	}

	public static int nextInt(String text, int from, int to) throws RangeException {
		int val = Integer.parseInt(nextLine(text));
		if (val < from || val > to) {
			throw new RangeException(from, to);
		}
//		for (int i = 0; i < text.length(); i++) {
//			if (text.charAt(i) < '0' || text.charAt(i) > '9')
//				throw new NumberFormatException("숫자로 입력하세요.");
//		}
		return val;
	}

	public static String nextLine(String text) {
		System.out.print(text);
		return scanner.nextLine();
	}

	/**
	 * 주소 - 좌표값 검색
	 * 
	 * @author 이창연
	 * @param 주소 - scanner(도로명주소)
	 * @return 경위도 좌표(x:경도,y:위도) user 데이터에 좌표값 대입
	 */
	public static String[] nextAddr(String text) throws Exception {

		Map<String, String> address = null;
		String addrName = "";
		while (true) {
			try {
				String addr = nextLine("▷ 주소 입력 : ");
				addrName = addr;
				String query = URLEncoder.encode(addr, "utf-8");
				String api = MAIN_API + "?query=" + query;
				URL url = new URL(api);
				URLConnection conn = url.openConnection();
				conn.setRequestProperty("Authorization", "KakaoAK " + SERVICE_KEY);

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = null;
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					sb.append(line);
				}
				@SuppressWarnings("unchecked")
				Map<String, ArrayList<Map<String, String>>> map = new Gson().fromJson(sb.toString(), Map.class);
				System.out.println(map);
				System.out.println(map.get("documents"));
				System.out.println(map.get("meta"));

				ArrayList<Map<String, String>> arr = map.get("documents");
				System.out.println("=========================");
				for (Map<String, String> m : arr) {
					System.out.println(m.get("address_name"));
					System.out.println(m.get("x"));
					System.out.println(m.get("y"));
					address = m;
					System.out.println();
				}
				if (arr.isEmpty()) {
					throw new NullPointerException();
				} else if (arr.size() > 1) {
					throw new NotOnlyOneException();
				} else
					break;
			} catch (NullPointerException npe) {
				System.out.println("주소 잘못됐다. 다시 입력해라.");
				continue;
			} catch (NotOnlyOneException nooe) {
				System.out.println("주소 여러 개 나오잖아 구체적으로 좀 써라.");
				continue;
			}
		}
		String[] coordxy = { addrName, (address.get("x")), (address.get("y")) };
		return coordxy;
	}
	/**
	 * 성향 - 가입 / 매칭 <br>
	 * 메시지, 기능 구분(0 : 가입, 1 : 매칭)
	 * 
	 * @author 이창연
	 * @return 논리형 2차원 가변 배열 (성향 정보) 
	 */
	public static boolean[][] preference(String msg, int function) {
		System.out.println(msg);
		boolean[][] myPref = prefs;
		String[] number = "①②③④⑤⑥⑦⑧⑨⑩".split("");
		for (int i = 0; i < prefs.length; i++) {
			for (int j = 0; j < prefs[i].length; j++) {
				System.out.print(number[j]);
				System.out.println(prefsDetails.get(i).get(j));
			}
			if (function == 1) {
				System.out.print(number[prefs[i].length]);
				System.out.println("상관 없음");
			}
			
			int no = nextInt("골라봐. >", 1, prefs[i].length + 1);
			if (function == 1 && no == prefs[i].length + 1) {
				for (int k = 0; k < prefs[i].length; k++) {
					myPref[i][k] = true;
				}
			}
			else {
				myPref[i][no-1] = true;
			}
		}
		return myPref;
	} 

	public static String nextLine(String text, boolean flag) throws KoreanOnlyException {
		System.out.print(text);
		String str = "";
		if (flag) {
			str = scanner.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) < '가' || str.charAt(i) > '힣')
					throw new KoreanOnlyException("한글로 입력해");
			}
		}
		return str;
	}

	public static String nextLine(String text, char male, char female) throws GenderException {
		System.out.print(text);
		String str = "";
		str = scanner.nextLine();
		if (str.charAt(0) != male && str.charAt(0) != female)
			throw new GenderException("성별 잘 좀 입력해봐 (남/여)");
		return str;
	}

	public static boolean isValueEmail(String email) {
		return email.matches("^[0-9a-zA-Z]*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{1,3}$");
	}
	public static boolean isValueId(String id) {
		return id.matches("^[0-9a-zA-Z]*");
	}
	public static boolean isValuePw(String pw) {
		return pw.matches("^[0-9a-zA-Z]*");
	}
}
