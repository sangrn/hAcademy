package mini.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable{

	private String id; //
	private String pw; //
	private boolean admin; // 관리자
	private String name; // 이름
	private int age; // 나이
	private String gender; // 성별
	private String email; // 전화번호 => 이메일로 수정 완료.,
	private String[] coordination = new String[3]; // 주소, 경위도 배열
	private double score; // 별점
	public static boolean[][] prefs; // 매칭 성향
	private boolean[][] myPrefs; // 내 성향
	public static final List<List<String>> prefsDetails = new ArrayList<List<String>>();
	
	{
		prefsDetails.add(new ArrayList<String>(
				Arrays.asList(new String[] { "150미만", "150대", "160대", "170대", "180대", "190이상" })));
		prefsDetails.add(new ArrayList<String>(
				Arrays.asList(new String[] { "야외 데이트(여행, 공원산책, 운동 등)", "실내 데이트(집, 카페, PC방 등)" })));
		prefsDetails.add(new ArrayList<String>(Arrays.asList(new String[] { "RPG게임", "FPS게임", "전략게임", "스포츠게임", "기타" })));
		prefsDetails.add(new ArrayList<String>(Arrays.asList(new String[] { "흡연", "비흡연" })));
		prefsDetails.add(new ArrayList<String>(
				Arrays.asList(new String[] { "소주파", "맥주파", "막걸리파", "고급파(양주, 와인 등)", "분위기파(술X)", "안먹어(분위기도 싫음)" })));
		prefsDetails.add(new ArrayList<String>(Arrays.asList(new String[] { "기독교", "천주교", "불교", "무교", "기타" })));
		
		myPrefs = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false},
			{false, false, false, false, false },
			{false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false},
		};
		
		prefs = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false},
			{false, false, false, false, false },
			{false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false},
		};
	}
	
	public User() {
	}
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public User(String id, String pw, boolean admin) {
		this(id, pw);
		this.admin = admin;
	}
	
	public User(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public User(String id, String pw, String name, int age, String gender, String email, String[] coordination) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.coordination = coordination;
	}
	
	public User(String id, String pw, String name, int age, String gender, String email, String[] coordination, boolean[][] myPrefs) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.coordination = coordination;
		this.myPrefs = myPrefs;
	}
	
	public User(String id, String pw, String name, int age, String gender, String email, String[] coordination, double score, boolean[][] myPrefs) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.coordination = coordination;
		this.score = score;
		this.myPrefs = myPrefs;
	}

	public User(String id, String pw, String name, int age, String gender, String email, String address) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		coordination[0] = address;
	}
	
	public User(String id, String pw, String name, int age, String gender, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}
	
	public User(String id, String pw, String name, int age, String gender, String email, String address, String longitude,
			String latitude, double score) {
		this(id, pw, name, age, gender, email);
		coordination[0] = address;
		coordination[1] = longitude;
		coordination[2] = latitude;
		this.score = score;
	}
	
	public User(double score) {
		this.score = score;
	}

	public String firstDisplay() {
		return " id   : " + id + "\n" + 
				" 이름 : " + name + "\n" +
				" 주소 : " + getAddress() + "\n" + starToString();
	}
	
	public String matchDisplay() {
		return " id   : " + id + "\n" + 
				" 이름 : " + name + "\n" + starToString();
	}

	@Override
	public String toString() {
		String str = "▶ id     = " + id + "\n" + 
				"▶ pw     = " + pw + "\n" + 
				"▶ 이름   = " + name + "\n" + 
				"▶ 나이   = " + age + "\n" + 
				"▶ 성별   = " + gender + "\n" + 
				"▶ 이메일 = " + email + "\n" + 
				"▶ 주소   = " + getAddress();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < myPrefs.length; i++) {
			for(int j = 0; j < myPrefs[i].length; j++) {
				if(myPrefs[i][j]) {
					list.add(User.prefsDetails.get(i).get(j));
				}
			}
		}
		return str + "\n▶ 성향   = " + list;
	}
	
	public String adminToString() {
		String str = id + ", " + pw + ", " + name + ", " + age + ", " + 
				gender + ", " + email + ", " + getAddress() ;
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < myPrefs.length; i++) {
			for(int j = 0; j < myPrefs[i].length; j++) {
				if(myPrefs[i][j]) {
					list.add(User.prefsDetails.get(i).get(j));
				}
			}
		}
		return str + "\n 성향=" + list;
	}
	public String starToString() {
		int starNum = (int)score;
		String star = "";
		for (int i = 1; i <= 5; i++) {
			if (starNum-- > 0) {
				star += "★";
			}
			else {
				star += "☆";
			}
		}
		return " 별점 : " + Math.round(score * 100) / 100.0 + "[" + star + "]";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getAddress() {
		return coordination[0];
	}

	public void setAddress(String address) {
		coordination[0] = address;
	}

	public String getLongitude() {
		return coordination[1];
	}

	public void setLongitude(String longitude) {
		coordination[1] = longitude;
	}

	public String getLatitude() {
		return coordination[2];
	}

	public void setLatitude(String latitude) {
		coordination[2] = latitude;
	}
	
	public String[] getCoordination() {
		return coordination;
	}
	
	public void setCoordination(String[] coordination) {
		this.coordination = coordination;
	}
	
	public boolean[][] getMyPrefs() {
		return myPrefs;
	}

	public void setPrefs(boolean[][] myPrefs) {
		this.myPrefs = myPrefs;
	}

	public List<List<String>> getPrefsDetails() {
		return prefsDetails;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
