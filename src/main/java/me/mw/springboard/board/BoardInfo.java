package me.mw.springboard.board;

import org.springframework.stereotype.Component;

@Component
public class BoardInfo {

    private Integer maxPage;
    private Integer currentPage;

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
