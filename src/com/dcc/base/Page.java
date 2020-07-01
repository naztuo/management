package com.dcc.base;

import java.io.Serializable;

public class Page implements Serializable {
    private static final long serialVersionUID = 4594071922809227379L;
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    private int pageNo;
    private int pageItems;
    private int totalRows;
    private String sortname;
    private String sortorder;

    public Page() {
    }

    public void setPageNo(int pageNo) {
        this.pageNo = Math.max(pageNo, 1);
    }

    public void setPageItems(int pageItems) {
        this.pageItems = Math.max(pageItems, 1);
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = Math.max(totalRows, 0);
        int realPageCount = totalRows % this.pageItems == 0 ? totalRows / this.pageItems : totalRows / this.pageItems + 1;
        if (this.pageNo > realPageCount) {
            if (this.pageNo - 1 > this.pageNo - realPageCount) {
                this.pageNo = realPageCount;
            } else {
                this.pageNo = 1;
            }
        }

    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageItems() {
        return this.pageItems;
    }

    public int getTotalRows() {
        return this.totalRows;
    }

    public String getSortname() {
        return this.sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return this.sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }
}
