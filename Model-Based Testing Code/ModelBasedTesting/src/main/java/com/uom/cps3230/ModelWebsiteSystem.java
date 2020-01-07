package com.uom.cps3230;

public class ModelWebsiteSystem {
	private boolean logOn = true, logOff = false, search = false, addProduct = false, removeProduct = false, checkout = false;


	boolean isLogOn() {
		return logOn;
	}

	boolean isLogOff() {
		return logOff;
	}

	boolean isSearch() {
		return search;
	}
	boolean isAddProduct() {
		return addProduct;
	}
	boolean isRemoveProduct() {
		return removeProduct;
	}
	boolean isCheckout() {
		return checkout;
	}

	void userLogsOff() {
		if (logOn && !logOff && !search && !addProduct && !removeProduct && !checkout) {
			logOn = false;
			logOff = true;
		}
	}

	void userLogsOn() {
		if (!logOn && logOff && !search && !addProduct && !removeProduct && !checkout) {
			logOn = true;
			logOff = false;
		}
	}

	void userSearches() {
		if (logOn && !logOff && !search && !addProduct && !removeProduct && !checkout) {
			search = true;
		}
	}

	void userSelectsProduct() {
		if (logOn && !logOff && !search && !addProduct && !removeProduct && !checkout) {
			addProduct = true;

		}
	}

	void userSearchingAfterAddingAProduct() {
		if (logOn && !logOff && !search && addProduct && !removeProduct && !checkout) {
			addProduct = false;
			search = true;
		}
	}

	void userRemovesProducts() {
		if (logOn && !logOff && !search && addProduct && !removeProduct && !checkout) {
			addProduct = false;
			removeProduct = true;
		}
	}

	void userSearchesAfterRemovingAProduct() {
		if (logOn && !logOff && !search && !addProduct && removeProduct && !checkout) {
			search = true;
			removeProduct = false;
		}
	}

	void userChecksOut() {
		if (logOn && !logOff && !search && !addProduct && removeProduct && !checkout) {
			removeProduct = false;
			checkout = true;
		}
	}

	void userChecksOutAfterAddingAProduct() {
		if (logOn && !logOff && !search && addProduct && !removeProduct && !checkout) {
			addProduct = false;
			checkout = true;
		}
	}

}
