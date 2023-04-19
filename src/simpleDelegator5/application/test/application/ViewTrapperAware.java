package simpleDelegator5.application.test.application;

import simpleDelegator5.application.test.ViewTrapper;

public interface ViewTrapperAware {

	/**
	 * @param viewId
	 * @return
	 */
	public ViewTrapper view(String viewId);
	
	/**
	 * @return
	 */
	public ViewTrapper view();
}