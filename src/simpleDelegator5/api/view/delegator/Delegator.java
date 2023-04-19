package simpleDelegator5.api.view.delegator;

import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.api.view.ViewException;

public interface Delegator {
	
	public void inject(ViewContainer viewContainer) throws ViewException;
	public void clean(ViewContainer viewContainer)throws ViewException;

}