package mini.vo;

import java.io.Serializable;

/**
 * 별점 매기기
 * 
 * @author 이창연
 */
public class StarRate extends User implements Serializable{

	String ratedId; // 별점 받은 유저
	String raterId; // 별점 준 유저
	double star;
	
	
	public StarRate(String raterId, String ratedId, double star) {
		this.raterId = raterId;
		this.ratedId = ratedId;
		this.star = star;
	}
	public String getRatedId() {
		return ratedId;
	}
	public void setRatedId(String ratedId) {
		this.ratedId = ratedId;
	}
	public String getRaterId() {
		return raterId;
	}
	public void setRaterId(String raterId) {
		this.raterId = raterId;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	
//	@Override
//	public String toString() {
//		int starNum = (int)getScore();
//		String star = "";
//		for (int i = 1; i <= 5; i++) {
//			if (starNum-- > 0) {
//				star += "★";
//			}
//			else {
//				star += "☆";
//			}
//		}
//		return "별점 준 유저 : " + raterId + ", 별점 받은 유저 : " + ratedId + 
//				" => 별점 : " + (Math.round(getScore() * 100) / 100.0) + "[" + star + "]";
//	}
	
	
}
