package simpleDelegator5.api.core;

import simpleDelegator5.application.Application;

/**
 * This interface should be implemented for those classes that want to
 * be connected to the lifecycle of the application.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public interface ApplicationAware
{
	/**
	 * Sets the application instance
	 * 
	 * @param application
	 */
	public abstract void setApplication(Application application);
	
	/**
	 * Returns the instance of the current application
	 * 
	 * @return
	 */
	public abstract Application getApplication();

}