package simpleDelegator5.application.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.view.ViewManager;
import simpleDelegator5.application.AbstractApplication;
import simpleDelegator5.application.Application;
import simpleDelegator5.application.PropertyTrapper;
import simpleDelegator5.application.test.application.PropertyAware;
import simpleDelegator5.application.test.application.ViewTrapperAware;


/**
 * @author Mario Garcia
 *
 */
public class ApplicationTrapper implements 
	Trapper<Application>,ViewTrapperAware,PropertyAware{

	private static final Logger logger = LoggerFactory.getLogger(ApplicationTrapper.class);
	//private TrapperSettings settings;
	private Application target;
	private ViewTrapper viewTrapper;
	
	
	
	/**
	 * 
	 */
	public ApplicationTrapper(){
		super();
		//this.settings = new TrapperSettings();
	}
	
	/**
	 * @param application
	 */
	public ApplicationTrapper(Application application){
		this();
		this.target = application;
		this.viewTrapper = new ViewTrapper(this, "FRAME");
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ComponentAware#button(java.lang.String)
	 */
	public ButtonTrapper button(String name) {
		return this.viewTrapper.button(name);
	}
	
	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public ButtonTrapper button(String viewID,String name){
		this.viewTrapper = new ViewTrapper(this, viewID);
		return this.viewTrapper.button(name);
	}
	
	

	/**
	 * 
	 */
	public void close(){
		this.target.close();
	}


	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public Application getTarget() {
		return target;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<Application> getType() {
		return Application.class;
	}
	
	

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public ApplicationTrapper log(String message) {
		logger.info(message);
		return this;
	}
	
	

	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.PropertyAware#property(java.lang.String)
	 */
	public PropertyTrapper property(String name) {
		return this.viewTrapper.property(name);
	}

	/**
	 * @param viewID
	 * @param name
	 * @return
	 */
	public PropertyTrapper property(String viewID,String name) {
		this.viewTrapper = new ViewTrapper(this,viewID);
		return this.viewTrapper.property(name);
	}
	
	/**
	 * Checks that the application is visible
	 * 
	 * @return
	 */
	public ApplicationTrapper requireVisible(){
		return this.requireVisible(true);
	}
	
	/**
	 * Checks whether the application is visible or not
	 * 
	 * @param visible
	 * @return
	 */
	public ApplicationTrapper requireVisible(boolean visible){
		
		return this;		
	}
	
	

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(Application target) {
		this.target = target;
	}

	

	

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view()
	 */
	public ViewTrapper view() {
	 /* The view musn't be cached. The viewTrapper always must have a fresh reference of the view */
		logger.info("view_called:"+this.viewTrapper.getTarget());
		this.viewTrapper = new ViewTrapper(this,this.viewTrapper.getTarget() != null ? 
				this.viewTrapper.getTarget().getId() : null);
		return this.viewTrapper;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#viewTrapper(java.lang.String)
	 */
	public ViewTrapper view(String viewId) {
		logger.info("view_called:"+viewId);
		this.viewTrapper =  new ViewTrapper(this, viewId);
		if(null == this.viewTrapper) {
			logger.info("view_trapper_is_null");
		}else {
			logger.info("view_trapper_is_not_null");
		}
		return this.viewTrapper;
	}
}