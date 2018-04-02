package com.nightingale.util.web;

public class Pagination {

	// must be an odd number
	private int pages = 5;
	private int previousJumpPage = 1;
	private int nextJumpPage = 1;

	private int startingPage = 1;
	private int endingPage = 1;
	private int currentPage = 1;
	private int totalPages = 0;

	public Pagination(int currentPage, int totalCount, int pageSize) {

		this.currentPage = currentPage;

		totalPages = (int) Math.ceil((double) totalCount / pageSize);

		if (totalPages < pages) {
			startingPage = 1;
			endingPage = totalPages;
		} else {

			int offset = pages / 2;

			startingPage = currentPage - offset;
			endingPage = currentPage + offset;

			int excess = 0;

			if (startingPage < 1) {
				excess = 1 - startingPage;
				startingPage = 1;
				endingPage += excess;
			}

			else if (endingPage > totalPages) {
				excess = totalPages - endingPage;
				endingPage = totalPages;
				startingPage += excess;
			}
		}

		previousJumpPage = currentPage - pages;
		nextJumpPage = currentPage + pages;

		if (previousJumpPage < 1)
			previousJumpPage = 1;

		if (nextJumpPage > totalPages)
			nextJumpPage = totalPages;

	}

	public int getStartingPage() {
		return startingPage;
	}

	public void setStartingPage(int startingPage) {
		this.startingPage = startingPage;
	}

	public int getEndingPage() {
		return endingPage;
	}

	public void setEndingPage(int endingPage) {
		this.endingPage = endingPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPages() {
		return pages;
	}

	public int getPreviousJumpPage() {
		return previousJumpPage;
	}

	public int getNextJumpPage() {
		return nextJumpPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

}
