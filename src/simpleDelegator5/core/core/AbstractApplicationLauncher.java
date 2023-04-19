package simpleDelegator5.core.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.LookAndFeel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.core.ApplicationException;
import simpleDelegator5.api.core.ApplicationLauncher;
import simpleDelegator5.application.Application;


/**
 * This is the launcher of the <code>Application</code>. It launches the
 * application lifecycle in a new Thread.
 * 
 * @author Mario Garcia
 * @since 1.0
 * 
 */
public abstract class AbstractApplicationLauncher extends
		SwingWorker<Application, Void> implements ApplicationLauncher {

	private static final String APPLICATION_INIT_FAILED_PREFIX = "Application init failed: ";
	private static final String BLANK_SPACE = " ";
	private static final String EMPTY_STRING = "";
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplicationLauncher.class);
	private static final String P_X = "p_x";
	private static final String P_Y = "p_y";
	private static final String PROGRESS_HEIGHT = "progress_height";
	private static final String PROGRESS_WIDTH = "progress_width";
	private Map<String,Object> configuration;
	private Map<String, Integer> coordinates;
	private Properties currentLocaleBundle;
	private Dimension dim;
	private Graphics2D graphics;
	private ImageIcon imageIcon;
	private SplashScreen splashScreen;
	private Properties viewaProperties;

	/**
	 * @throws Exception
	 * 
	 */
	public AbstractApplicationLauncher() {
		try {
			this.coordinates = new HashMap<String, Integer>();
			this.configuration = new HashMap<String,Object>();
			initialization();
		} catch (Exception e) {
			logger.error(APPLICATION_INIT_FAILED_PREFIX + e.getMessage());
		}
	}

	/**
	 * @param message
	 */
	private void debugJustInCase(String message) {
		/*if (logger.isDebugEnabled()) {
			logger.debug(message);
		}*/
		logger.info(message);
	}

	private void doConfigurationProperties() {
		
	}

	private boolean doesSplashScreenExists() {
		return this.splashScreen!= null && this.configuration.get(APPLICATION_SPLASH)!=null && 
				!this.configuration.get(APPLICATION_SPLASH).toString().equals("") &&
				Boolean.valueOf(this.configuration.get(APPLICATION_SPLASH).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Application doInBackground() throws Exception {
		if (this.getLookAndFeel() != null){
			UIManager.setLookAndFeel(getLookAndFeel());
		}
		this.debugJustInCase("Init: Starting application");
		/* Init */
		//publish(new SplashStatus(currentLocaleBundle.getProperty(SPLASH_KEY_MESSAGE_STARTING), 0));
		
		Application application = this.getApplication();
		application.prepare();
		//String locale = viewaProperties.getProperty(APPLICATION_LOCALE);
	
		/* Preparing background tasks */
		//publish(new SplashStatus(currentLocaleBundle.getProperty(SPLASH_KEY_MESSAGE_PREPARE), 1));
	 /* ---------------------------------- PREPARE (1) ------------------------------------ */	
		//application.prepare();
		debugJustInCase("Init: Back actions finished");
		return application;
	}
	
	/**
	 * This method initializes the splash screen
	 */
	private void doInitSplash() {
		this.splashScreen = SplashScreen.getSplashScreen();
	}

	private void doLoadProperties() throws IOException {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done()  {
		try {
			
			Application application = get();
			application.prepareUI();
			updateSplashForPrepareUI();
			
		 /* -------------------------------- PREPARE UI -- (2) --------------------------------- */
			//application.prepareUI();
			updateSplashForInitUI();
			application.initUI();
		 /* --------------------------------- INIT UI ---- (3) --------------------------------- */
			//application.initUI();
			updateSplashForOpening();
			
			notifyInit();
			
		} catch (InterruptedException e1) {
			logger.error(APPLICATION_INIT_FAILED_PREFIX + e1.getMessage());
		} catch (ExecutionException e1) {
			logger.error(APPLICATION_INIT_FAILED_PREFIX + e1.getMessage());
		}
	}

	private synchronized void notifyInit() {
		notifyAll();
	}

	private void doSplashCoordinates() {		
		if (doesSplashScreenExists()){			
			this.graphics = splashScreen.createGraphics();
			this.imageIcon = new ImageIcon(this.splashScreen.getImageURL());
			this.dim = splashScreen.getSize();
			this.coordinates.put(PROGRESS_WIDTH, Double.valueOf(dim.getWidth() / 2).intValue());
			this.coordinates.put(PROGRESS_HEIGHT, 10);
			this.coordinates.put(P_X, Double.valueOf(dim.getWidth() / 4).intValue());
			this.coordinates.put(P_Y, Double.valueOf((dim.getHeight() / 2) - this.coordinates.get(PROGRESS_HEIGHT)).intValue());
		} else if (this.splashScreen != null){
			this.splashScreen.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.core.ApplicationLauncher#execute(java.lang.Class)
	 */
	public synchronized Application execute(Class<? extends Application> app) throws Exception {
		this.execute();
		wait();
		return this.getApplication();
	}

	
	/**
	 * @return
	 */
	public abstract Application getApplication() throws ApplicationException;



	

	/**
	 * If anyone wants to execute an application with a certain look and feel then it has
	 * to be set in this method.
	 * 
	 * @return
	 */
	public LookAndFeel getLookAndFeel(){
		return null;
	}

	/**
	 * Inits splash screen
	 */
	private void initialization() {
		try {
			doInitSplash();
			doLoadProperties();	
			doConfigurationProperties();		
			doSplashCoordinates();
		} catch (IOException e) {
			logger.error(APPLICATION_INIT_FAILED_PREFIX + e.getMessage());
		}	
	}

	/**
	 * @return
	 */
	private boolean isSplashScreenStillAvailable() {
		return splashScreen != null && splashScreen.isVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */


	/**
	 * This method paints messages and progress bar in the splash screen
	 * 
	 * @param g
	 * @param message
	 * @param frame
	 * @param coor
	 */
	public void renderSplashFrame(Graphics2D g, String message, int frame,Map<String, Integer> coor) {
		/* ----------------------- PROGRESS -------------------- */
		
	}
	
	private void updateSplashForInitUI() {
		if (doesSplashScreenExists()){
		renderSplashFrame(graphics, currentLocaleBundle.getProperty(SPLASH_KEY_MESSAGE_OPENING), 3, coordinates);
		if (isSplashScreenStillAvailable()) {
			splashScreen.update();
		}
		}
		debugJustInCase("Init: Executing initUI");
	}

	private void updateSplashForOpening() {
		if (doesSplashScreenExists()){
			if (isSplashScreenStillAvailable()) {
				splashScreen.close();
			}
		}
	}

	private void updateSplashForPrepareUI() {
		if (doesSplashScreenExists()){
		renderSplashFrame(graphics, currentLocaleBundle.getProperty(SPLASH_KEY_MESSAGE_PREPAREUI), 2, coordinates);
		}
		debugJustInCase("Init: Executing PrepareUI");
	}
}