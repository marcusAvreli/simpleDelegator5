package simpleDelegator5.api.view.event;

import simpleDelegator5.api.view.ViewContainer;

/**
 * @author Mario García
 * @since 1.0
 *
 */
public class ViewContainerEvent {
	
	private ViewContainer source;

	/**
	 * @param view
	 */
	public ViewContainerEvent(ViewContainer view){
		this.source = view;;
	}
	
	/**
	 * @return
	 */
	public ViewContainer getSource() {
		return source;
	}

	/**
	 * @param source
	 */
	public void setSource(ViewContainer source) {
		this.source = source;
	}

}
