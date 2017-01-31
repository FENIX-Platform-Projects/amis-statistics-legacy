package org.fao.fenix.web.modules.re.client.view.util.Pager;

public class FenixPager {
	int listSize;
	int elementsPerPage;
	int actualPage;

	public FenixPager(int listSize, int elementsPerPage) {
		if (elementsPerPage < 0)
			System.out.println("Error, elementsPerPage must be greater than 0");
		if (listSize < 0)
			System.out.println("Error, listSize must be greater than 0");
		this.elementsPerPage = elementsPerPage;
		this.listSize = listSize;
		if (listSize == 0)
			this.actualPage = 0;
		else
			this.actualPage = 1;
	}

	public int calculateNumberOfPages() {
		int pages = 0;
		if (listSize > 0) {
			if (elementsPerPage > 0) {
				pages = (int) (listSize / elementsPerPage);
				if (listSize % elementsPerPage != 0)
					pages++;
			} else
				System.out.println("Error, elementsPerPage must be greater than 0");
		} else
			pages = 0;
		return pages;
	}

	public int calculateIndexTo() {
		int indexTo = 0;
		if (validatePageNumber(this.actualPage)) {
			indexTo = this.actualPage * elementsPerPage;
			if (indexTo > listSize)
				indexTo = listSize;
		}
		return indexTo;
	}

	public int calculateIndexFrom() {
		int indexFrom = 0;
		if (validatePageNumber(this.actualPage) && listSize > 0) {
			//indexFrom = (this.actualPage - 1) * elementsPerPage;
			indexFrom = (this.actualPage - 1) * elementsPerPage;
		}
		return indexFrom;
	}

	private boolean validatePageNumber(int pageNumber) {
		boolean correct = true;
		if (pageNumber > calculateNumberOfPages()) {
			System.out.println("Error, pageNumber greater than number of pages");
			correct = false;
		}
		if (pageNumber < 0) {
			System.out.println("Error, pageNumber cannot be smaller than 1 ");
			correct = false;
		}
		return correct;
	}

	public int getActualPage() {
		return actualPage;
	}

	public boolean goToNextPage() {
		boolean pageNumberChanged = false;
		if (actualPage < calculateNumberOfPages()) {
			actualPage++;
			pageNumberChanged = true;
		}
		return pageNumberChanged;
	}

	public boolean goToPreviousPage() {
		boolean pageNumberChanged = false;
		if (actualPage > 1) {
			actualPage--;
			pageNumberChanged = true;
		}
		return pageNumberChanged;
	}

	public boolean setActualPage(int actualPage) {
		boolean pageNumberChanged = false;
		if (actualPage < 0)
			System.out.println("Actual page number smaller than 0, is " + actualPage);
		if (actualPage < 0 || actualPage > calculateNumberOfPages())
			System.out.println("Actual page number (" + actualPage
					+ ") cannot be greater than the possible max number of pages(" + calculateNumberOfPages() + ")");
		if (actualPage != this.actualPage) {
			pageNumberChanged = true;
			this.actualPage = actualPage;
		}
		return pageNumberChanged;
	}

	public int getListSize() {
		return listSize;
	}

}
