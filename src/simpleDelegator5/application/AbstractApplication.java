package simpleDelegator5.application;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.controller.ViewControllerDispatcher;
import simpleDelegator5.api.controller.ViewControllerDispatcherException;
import simpleDelegator5.api.core.ApplicationContext;
import simpleDelegator5.api.core.ApplicationContextException;
import simpleDelegator5.api.core.ApplicationException;
import simpleDelegator5.api.view.ViewContainerFrame;
import simpleDelegator5.api.view.ViewException;
import simpleDelegator5.api.view.ViewManager;
import simpleDelegator5.api.view.ViewManagerException;
import simpleDelegator5.api.view.perspective.Perspective;
import simpleDelegator5.common.CustomApplicationView;
import simpleDelegator5.core.annotation.processor.AnnotationProcessor;
import simpleDelegator5.core.annotation.processor.ViewsPerspectiveProcessor;
import simpleDelegator5.core.annotation.processor.ViewsProcessor;
import simpleDelegator5.core.annotation.processor.ViewsProcessorWrapper;
import simpleDelegator5.core.controller.DefaultViewControllerDispatcher;
import simpleDelegator5.core.core.DefaultApplicationContext;
import simpleDelegator5.core.view.DefaultViewManager;
import simpleDelegator5.core.view.perspective.DefaultPerspective;

///https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/core/AbstractApplication.java
public abstract class AbstractApplication implements Application{
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplication.class);
	private ApplicationContext			applicationContext;
	private ViewContainerFrame			rootView;
	private ViewControllerDispatcher 	dispatcher;
	//private AbstractViewManager 				viewManager;
	private ViewManager 				viewManager;
	private List<ViewsProcessorWrapper> initViews;
	//Application
	@Override
	public void close() {
		logger.info("close called");
		this.fireClose();
	}
	
	public void fireClose() {
		ViewContainerFrame viewContainerFrame = this.getViewManager().getRootView();
		viewContainerFrame.getFrame().dispose();
	}
	//Application
	@Override
	public void setVisible(boolean visible) {
		
		JFrame frame = this.getRootView().getFrame();
		if (frame != null){
			if (visible){
				frame.setVisible(visible);
				frame.repaint();
			} else {
				frame.setVisible(visible);
			}
		}
	}
	
	//Application
	@Override
	/**
	 * @param rootView
	 */
	public void setRootView(ViewContainerFrame rootView) {
		logger.info("Setting Root View");
		
			if (this.viewManager!=null){
				this.viewManager.setRootView(rootView);
				this.rootView = rootView;
			}
		
	}
	
	public ViewContainerFrame  getRootView() {	
		logger.info("get_root_view");
		return rootView;
	}
	public AbstractApplication(){
	//	this.viewManager 			= new DefaultViewManager(this);
		this.dispatcher 			= new DefaultViewControllerDispatcher();
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
	}
	public ViewControllerDispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(ViewControllerDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public AbstractApplication(ViewContainerFrame mainView){
		this();
		//this.setName(name);
		this.setRootView(mainView);
	}
	/* (non-Javadoc)
	 * @see org.viewa.core.Application#getViewManager()
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}
	public void prepare() throws ApplicationException {
		logger.info("prepare_started");
		try {
			logger.info("Application preparing!");
			/* If there's no custom applicationContext then a default implementation is provided (v1.0.2) */
			if (this.applicationContext == null){
				this.applicationContext = new DefaultApplicationContext();
			}
			logger.info("checkPost_1");
		 /* Now any application annotation is going to be processed (v1.0.2) */
			AnnotationProcessor viewsProcessor = new ViewsProcessor(this);
			logger.info("checkPost_2");
			AnnotationProcessor viewsPerspectiveProcessor = new ViewsPerspectiveProcessor(this);
			logger.info("checkPost_3");
		 /* Executing processors */
			viewsProcessor.process();
			logger.info("checkPost_4");
			viewsPerspectiveProcessor.process();
			logger.info("checkPost_5");
		 /* Getting process results */
			Perspective annotationPerspective  = Perspective.class.cast(viewsPerspectiveProcessor.getResult());
			logger.info("checkPost_6");
		 /* If there's a valid annotation perspective it's set as default */
			if (annotationPerspective != null){
				logger.info("checkPost_7");
				this.getViewManager().setPerspective(annotationPerspective);
				logger.info("checkPost_8");
			}
			logger.info("checkPost_9");
			initViews = List.class.cast(viewsProcessor.getResult());
			if(null != initViews) {
				logger.info("initViews_is_not_null");
			}else {
				logger.info("initViews_is_null");
			}
			logger.info("checkPost_10");
		} catch (Exception e) {		 
			throw new ApplicationException(e);
		}
		/*if(null == initViews) {
			 
		
			initViews = new ArrayList<ViewsProcessorWrapper>();
			ViewsProcessor processor = new ViewsProcessor(this,new CustomApplicationView());
			try {
				processor.process();
			
			initViews = List.class.cast(processor.getResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}*/
		logger.info("prepare_finished");
		
	}
	public void prepareUI(){
		logger.info("Application preparing UI!");
		
		if (initViews!=null){
			logger.info("initViews:"+initViews.size());
			for (ViewsProcessorWrapper w : initViews){
				if (w.isRootView()){
					this.setRootView(ViewContainerFrame.class.cast(w.getView()));
				} else {
					logger.info("adding_just_view");
					try {
						this.getViewManager().addView(w.getView(),w.getConstraint());
					} catch (ViewException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					
					
				
			}
		}else {
			logger.info("initViews_is_null");
		}
	}
	public void initUI() {
		
			logger.info("Application UI initializing!");
			ViewManager viewManager = this.getViewManager();
			logger.info("after_view_manager");
			Component view;
			try {
				view = viewManager.arrangeViews();
			
			logger.info("after_arrange_views");
			if(null != view) {
				logger.info("view_is_not_null");
			}else {
				logger.info("view_is_null");
			}
			
			view.setVisible(true);
			} catch (ViewManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationContextAware#setApplicationContext(org.viewaframework.core.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws ApplicationContextException {
		if (this.applicationContext != null) {
			throw new ApplicationContextException();
		}
		this.applicationContext = applicationContext;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationContextAware#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setControllerDispatcher(ViewControllerDispatcher dispatcher) throws ViewControllerDispatcherException {
		this.dispatcher = dispatcher;
	}
	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#getControllerDispatcher()
	 */
	public ViewControllerDispatcher getControllerDispatcher() {
		return this.dispatcher;
	}

}
