package simpleDelegator5.core.annotation.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.api.view.perspective.PerspectiveConstraint;
import simpleDelegator5.application.Application;
import simpleDelegator5.common.CustomApplicationView;
import simpleDelegator5.core.annotation.View;
import simpleDelegator5.core.annotation.Views;
import simpleDelegator5.core.view.AbstractViewContainer;

/**
 * 
 * This processor takes the annotation information of the Application and generates the needed classes at runtime.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * This processor takes the annotation information of the Application and generates the needed classes at runtime.
 * 
 * @author Mario Garcia
 * @since 1.0.2
 *
 */
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/annotation/processor/ViewsProcessor.java
public class ViewsProcessor implements AnnotationProcessor {

	private Application app;
	private List<ViewsProcessorWrapper> wrappers;
	
	/**
	 * @param app
	 */
	public ViewsProcessor(Application app){
		this.app = app;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.annotation.processor.AnnotationProcessor#process()
	 */
	public void process() throws Exception {
		Views viewsAnnotations = app.getClass().getAnnotation(Views.class);
		if (viewsAnnotations != null){
			View[] views = viewsAnnotations.value();
			wrappers  = new ArrayList<ViewsProcessorWrapper>();
			for (View view : views){
			 /* ------------------- VIEW INFO ----------------- */
				Class<? extends ViewContainer> viewType = view.type();
				String viewId = view.viewId();
				String iocId = view.id();
				PerspectiveConstraint constraint = view.position();
				boolean isRootView = view.isRoot();
				boolean isTrayView = view.isTray();
			 /* ----------------- VIEW PROCESS ---------------- */
				ViewContainer viewContainer = null;
				/*if (!iocId.equalsIgnoreCase("")){
					IOCContext context = IOCContext.class.cast(this.app.getApplicationContext().getAttribute(IOCContext.ID));
					if (context!= null){
						viewContainer = ViewContainer.class.cast(context.getBean(iocId));
					}
				}*/
				if (viewContainer == null){
					viewContainer = viewType.newInstance();
					viewContainer.setId(!viewId.equalsIgnoreCase("") ? viewId : viewContainer.getId());
				}
				wrappers.add(new ViewsProcessorWrapper(viewContainer, constraint,isRootView,isTrayView));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.annotation.processor.AnnotationProcessor#getResult()
	 */
	public Object getResult(){
		return this.wrappers;
	}
	
}