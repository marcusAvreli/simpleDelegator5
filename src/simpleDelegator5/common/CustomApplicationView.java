package simpleDelegator5.common;

import javax.swing.JLabel;

import simpleDelegator5.core.annotation.Controller;
import simpleDelegator5.core.annotation.Controllers;
import simpleDelegator5.core.annotation.Listener;
import simpleDelegator5.core.annotation.Listeners;
import simpleDelegator5.core.annotation.processor.ListenersProcessor;
import simpleDelegator5.core.view.DefaultViewContainer;
import simpleDelegator5.core.view.event.DefaultViewContainerEventController;

@Controllers({
	@Controller(type=TestController.class,pattern="testButton")
	
})
@Listeners({
	@Listener(type=DefaultViewContainerEventController.class,id="testButton")
})
public class CustomApplicationView  extends DefaultViewContainer{

	private JLabel lblCa;
	public static final String ID = "ControllerTestViewId";	

	public CustomApplicationView() {
		super(ID,new TestPanel());
		
	}
	

}
