package mini.vo;

import java.io.Serializable;

public class Favorite extends User implements Serializable{

	/**즐겨찾기 클래스 <br>
	 * 
	 * @author 최보원
	 */
	private boolean favi;
	private String otherUser; // 즐겨찾기 상대
	
	public Favorite() {}

	public Favorite(boolean favi, String id, String otherUser, String name, int age) {
		super(id, name, age);
		this.otherUser = otherUser;
		this.favi = favi;
	}
	
	public boolean isFavi() {
		return favi;
	}

	public void setFavi(boolean favi) {
		this.favi = favi;
	}

	public String getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(String otherUser) {
		this.otherUser = otherUser;
	}

	@Override
	public String toString() {
		return (favi == true ? '♥' : '♡') + 
				" id : " + getId() +
				", 상대 id : " + otherUser +
				", 이름 : " + getName() + 
				", 나이 : " + getAge();
	}
}
