package com.winter.app.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pager {
    private Long page;
    private Long perPage;
    private Long startIndex;

    private Long startNum;
    private Long lastNum;
    private Long totalPage;

    private Boolean start;
    private Boolean last;

    private String search;
    private String kind;

    private String sortBy;

    public void makeBlock(Long totalRow){
        totalPage = (long) Math.ceil((double) totalRow /getPerPage());
        Long perBlock = 5L;

        Long lastBlock = (long) Math.ceil((double) totalPage /perBlock);
        lastBlock = Math.max(1L, lastBlock);
        Long currentBlock = (long) Math.floor((double) (getPage() - 1) /perBlock) + 1;

        startNum = 1 + (currentBlock - 1)*perBlock;
        lastNum = currentBlock * perBlock;

        start = (currentBlock == 1L);
        if(currentBlock>=lastBlock){
            last = true;
            lastNum = Math.max(totalPage,1L);
        }
    }

    public void makeIndex(){
        startIndex = (this.getPage() - 1) * this.getPerPage();
    }

    public Long getPage(){
        if(page == null || page < 1){
            page = 1L;
        }
        return page;
    }

    public Long getPerPage(){
        if(perPage == null || perPage < 1){
            perPage = 10L;
        }
        return perPage;
    }

    public String getSearch(){
        return search == null ? "" : search;
    }

}
