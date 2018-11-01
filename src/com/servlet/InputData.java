package com.servlet;

public class InputData {
	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public String toString() {
		return "InputData [searchText=" + searchText + "]";
	}

}
