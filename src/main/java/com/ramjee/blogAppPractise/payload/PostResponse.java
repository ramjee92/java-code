package com.ramjee.blogAppPractise.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostResponse {

    private List<PostDto> postDtos;
    private int pageNo;
    private int PageSize;
    private int totalElements;
    private int totalPages;
    private boolean isLast;


}
