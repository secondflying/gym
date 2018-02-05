package com.gym.common.dto;

import java.util.List;

public class BaseResultsResponse extends BaseResponse {

	private static final long serialVersionUID = -6719321696302335511L;

	private long totalCount = 0;

	private List<? extends Object> results;

	public BaseResultsResponse(List<? extends Object> results) {
		this.results = results;
	}

	public BaseResultsResponse(long totalCount, List<? extends Object> results) {
		this.totalCount = totalCount;
		this.results = results;
	}

	public BaseResultsResponse() {

	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<? extends Object> getResults() {
		return results;
	}

	public void setResults(List<? extends Object> results) {
		this.results = results;
	}

}
