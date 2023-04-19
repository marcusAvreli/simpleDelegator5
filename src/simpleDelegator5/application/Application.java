package simpleDelegator5.application;

import simpleDelegator5.api.controller.ViewControllerDispatcherAware;
import simpleDelegator5.api.core.ApplicationContextAware;
import simpleDelegator5.api.core.ApplicationException;
import simpleDelegator5.api.view.ViewContainerFrame;
import simpleDelegator5.api.view.ViewManager;

public interface Application extends ApplicationContextAware,ViewControllerDispatcherAware{
	public void close();
	public void setVisible(boolean visible);
	public abstract void setRootView(ViewContainerFrame rootView);
	public ViewManager getViewManager();
	public void prepare() throws ApplicationException;
	public void prepareUI() ;
	public void initUI() ;
}
