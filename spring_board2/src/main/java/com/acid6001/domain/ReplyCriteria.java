package com.acid6001.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyCriteria extends Criteria {
	private long lastRno;

	public ReplyCriteria() {
		this(10);
	}

	public ReplyCriteria(int amount) {
		setAmount(amount);
	}

	public ReplyCriteria(Long lastBno, int amount) {
		this(lastBno);
		setAmount(amount);

	}

}
