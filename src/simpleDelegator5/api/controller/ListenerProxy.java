package simpleDelegator5.api.controller;


import java.lang.reflect.InvocationHandler;
import java.util.EventListener;
import java.util.EventObject;

/**
 * This interface allows proxys to give some information about the listeners the proxy is
 * facing.
 * 
 * @author mario
 *
 * @param <EL>
 * @param <EO>
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/api/src/main/java/org/viewaframework/controller/ListenerProxy.java
public interface ListenerProxy<EL extends EventListener,EO extends EventObject> extends InvocationHandler {

	/**
	 * Returns the listener the proxy is carrying.
	 * 
	 * @return
	 */
	public ViewController<EL, EO> getTargetController();
	
}