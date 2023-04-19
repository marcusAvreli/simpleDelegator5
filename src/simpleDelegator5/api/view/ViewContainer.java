package simpleDelegator5.api.view;
import javax.swing.RootPaneContainer;

import simpleDelegator5.api.controller.ViewControllerAware;
import simpleDelegator5.api.core.ApplicationAware;
import simpleDelegator5.api.view.delegator.DelegatorAware;
import simpleDelegator5.api.view.event.ViewContainerEventControllerAware;

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/api/src/main/java/org/viewaframework/view/ViewContainer.java
public interface ViewContainer extends RootPaneContainer, ApplicationAware,ComponentsAware,ViewControllerAware,ViewContainerEventControllerAware,DelegatorAware{
	public abstract String getId();
	/**
	 * Sets the name view.
	 * 
	 * @param name the id to set
	 */
	public abstract void setId(String name);
	
	/**
	 * Initializes the view. It should be implemented by an
	 * abstract class and it should call sequentially to the
	 * following methods.<br/><br/>
	 * 
	 * viewInitUIState();<br/>
	 * viewInitBackActions();<br/>
	 * viewFinalUIState();<br/>
	 * 
	 */
	public abstract void viewInit() throws ViewException;
}
