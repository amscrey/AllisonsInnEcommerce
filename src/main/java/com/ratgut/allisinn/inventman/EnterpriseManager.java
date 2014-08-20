package com.ratgut.allisinn.inventman;

import java.util.List;

/**
 * Created by drew on 8/14/14.
 */
public interface EnterpriseManager {
	public String getAlert();
	public void setAlert(String alert);
	public List<StoreFront> getStoreFronts();
	public void setStoreFronts(List<StoreFront> storeFronts);
}
