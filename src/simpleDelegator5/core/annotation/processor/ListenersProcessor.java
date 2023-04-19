package simpleDelegator5.core.annotation.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.core.ApplicationContext;
import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.api.view.event.ViewContainerEventController;
import simpleDelegator5.core.annotation.Listener;
import simpleDelegator5.core.annotation.Listeners;



public class ListenersProcessor implements AnnotationProcessor<List<ViewContainerEventController>>{

	private static final Logger logger = LoggerFactory.getLogger(ListenersProcessor.class);
	private ViewContainer view;
	private List<ViewContainerEventController> listeners;
	
	public ListenersProcessor(ViewContainer view){
		this.view = view;
		this.listeners = new ArrayList<ViewContainerEventController>();
	}
	
	@Override
	public void process() throws Exception {
		logger.info("processing_view_started: "+view.getClass().getSimpleName());
		Listeners listeners = view.getClass().getAnnotation(Listeners.class);
		if(null != listeners) {
			logger.info("listeners_is_not_null");
		}else {
			logger.info("listeners_is_null");
		}
		ApplicationContext ctx = this.view.getApplication().getApplicationContext();
		logger.info("checkPost_2");
		//IOCContext ioc = (IOCContext)ctx.getAttribute(IOCContext.ID);
		for (Listener l : listeners.value()){
			logger.info("checkPost_3");
			ViewContainerEventController listener =	l.type().newInstance();
			
			this.listeners.add(listener);
		}
		logger.info("process_finished");
	}

	@Override
	public List<ViewContainerEventController> getResult() {
		try {
			this.process();
		} catch (Exception e) {
			logger.error(
				new StringBuilder("Cant process listeners from: ").
					append(this.view.getClass().getSimpleName()).
					append(" [").append(e.getMessage()).append("]").toString());
		}
		return this.listeners;
	}

}
