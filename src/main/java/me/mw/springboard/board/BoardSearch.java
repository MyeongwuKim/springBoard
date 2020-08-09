package me.mw.springboard.board;


import org.springframework.stereotype.Component;

@Component
public class BoardSearch {

    private String searchTarget;
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchTarget() {
        return searchTarget;
    }

    public void setSearchTarget(String searchTarget) {
        this.searchTarget = searchTarget;
    }

}
