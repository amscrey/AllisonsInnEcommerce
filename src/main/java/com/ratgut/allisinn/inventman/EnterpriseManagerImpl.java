package com.ratgut.allisinn.inventman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by drew on 8/14/14.
 */
public class EnterpriseManagerImpl implements EnterpriseManager {

	private String alert = "Initial Alert";

	InventoryManager im1 = new InventoryManagerOOImpl();
	StoreFront rhinegeist = new StoreFrontImpl("Rhinegeist", "rhinegeist.com", im1);
	StoreFront samAdams = new StoreFrontImpl("Samuel Adams", "sam_adams.com", im1);
	StoreFront[] stores = {rhinegeist, samAdams};
	ArrayList<StoreFront> storeFronts = new ArrayList<StoreFront>(Arrays.asList(stores));

	public String getAlert() {return this.alert;}
	public void setAlert(String alert) {this.alert = alert;}

	public List<StoreFront> getStoreFronts() {
		return this.storeFronts;
	}

	public void setStoreFronts(List<StoreFront> storeFronts) {
		this.storeFronts = new ArrayList<StoreFront>(storeFronts);
	}
}
