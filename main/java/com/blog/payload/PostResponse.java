package com.blog.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse <T>{

	private List<T> content;
	private int pageNum;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
