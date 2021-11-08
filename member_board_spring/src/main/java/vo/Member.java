package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {
	@NonNull
	private String id;
	@NonNull
	private String pwd;
	private String email;
	private String name;

	// 생성자, getter, setter, toString
}