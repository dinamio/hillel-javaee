package edu.karazin.shop.web;

public class BookSearchForm {

    String searchText;

    public BookSearchForm(){}

    public BookSearchForm(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
