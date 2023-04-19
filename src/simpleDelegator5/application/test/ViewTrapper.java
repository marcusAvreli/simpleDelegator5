package simpleDelegator5.application.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.application.AbstractApplication;
import simpleDelegator5.application.PropertyTrapper;
import simpleDelegator5.application.test.application.ApplicationTrapperAware;
import simpleDelegator5.application.test.application.PropertyAware;


/**
 * @author Mario Garcia
 *
 */
public class ViewTrapper implements 
	Trapper<ViewContainer>,ApplicationTrapperAware,PropertyAware{

	private static final Logger logger = LoggerFactory.getLogger(ViewTrapper.class);
	private ApplicationTrapper applicationTraper;
	private ViewContainer target;
	
	/**
	 * @param applicationTrapper
	 * @param viewId
	 */
	public ViewTrapper(ApplicationTrapper applicationTrapper,String viewId){
		logger.info("ViewTrapper_started_applicationTrapper: "+applicationTrapper);
		logger.info("ViewTrapper_started_applicationTrapper: "+viewId);
		this.applicationTraper = applicationTrapper;
		this.target = this.applicationTraper.
			getTarget().getViewManager().getViews().get(viewId);
		if(null != target) {
			logger.info("target_is_not_null");
		}else {
			logger.info("target_is_null");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ApplicationTrapperAware#applicationTrapper()
	 */
	public ApplicationTrapper applicationTrapper() {
		return this.applicationTraper;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#button(java.lang.String)
	 */
	public ButtonTrapper button(String name) {
		return new ButtonTrapper(this.applicationTrapper(),name);
	}


	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public ViewContainer getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<ViewContainer> getType() {
		return ViewContainer.class;
	}

	
	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public ViewTrapper log(String message) {
		logger.info(message);
		return this;
	}

	

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.PropertyAware#property(java.lang.String)
	 */
	public PropertyTrapper property(String name) {
		return new PropertyTrapper(this.applicationTrapper(),name);
	}
	
	/**
	 * @return
	 */
	public ViewTrapper requireOpened(){
		return this.requireOpened(true);
	}

	/**
	 * @param openedOrClosed
	 * @return
	 */
	public ViewTrapper requireOpened(boolean openedOrClosed){
	
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(ViewContainer target) {
		this.target = target;
	}


}