package simpleDelegator5.core.view.delegator;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.api.view.ViewException;
import simpleDelegator5.api.view.delegator.Delegator;
import simpleDelegator5.core.core.AbstractApplicationLauncher;

public class NamedComponentsDelegator implements Delegator{
	private static final Logger logger = LoggerFactory.getLogger(NamedComponentsDelegator.class);
	/* (non-Javadoc)
	 * @see org.viewaframework.view.delegator.Delegator#clean(org.viewaframework.view.ViewContainer)
	 */
	public void clean(ViewContainer view) throws ViewException {
		view.setNamedComponents(null);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.delegator.Delegator#inject(org.viewaframework.view.ViewContainer)
	 */
	public void inject(ViewContainer view) throws ViewException {
		view.setNamedComponents(extractComponents(view.getRootPane(),new HashMap<String,List<Component>>()));
	}
	
	/**
	 * @param con
	 * @param namedComponents
	 * @return
	 */
	private Map<String,List<Component>> extractComponents(Component con,Map<String,List<Component>> namedComponents){
		logger.info("extract_components_started");
		String componentName = con.getName();
		logger.info("component_name:"+componentName);
		if (componentName!=null && !componentName.equalsIgnoreCase("")){
			List<Component> components = namedComponents.get(componentName);
			if (components==null){
				namedComponents.put(componentName, new ArrayList<Component>());
			}
			namedComponents.get(componentName).add(con);
		}			
		if (con instanceof Container){
			if (con instanceof JMenu){
				for (Component c : ((JMenu)con).getMenuComponents()){
					extractComponents(c,namedComponents);
				}
			} else {
				for (Component c : ((Container)con).getComponents()){
					extractComponents(c,namedComponents);
				}
			}
		}	
		logger.info("extract_components_finished");
		return namedComponents;
	}
	
}