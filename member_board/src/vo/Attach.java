package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
	// UUID
	// Origin
	// bno
	private String uuid;
	private String origin;
	private Long bno;
	private String path;
}