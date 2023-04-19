package simpleDelegator5.application.test.util;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.application.AbstractApplication;
import simpleDelegator5.application.test.Trapper;

public class TrapperUtils {

	private static final Logger logger = LoggerFactory.getLogger(TrapperUtils.class);
	
	@SuppressWarnings("unchecked")
	public static Trapper processRunnableAndWait(Trapper trapper,long millis,Runnable runnable){
		try{
			SwingUtilities.invokeAndWait(runnable);			
			Thread.sleep(millis);
		} catch (Exception ex){
			logger.error("-------------------------------------- ERROR --------------------------------------");
			
		}		
		return trapper;
	}
}
