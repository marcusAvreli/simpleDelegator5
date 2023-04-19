package simpleDelegator5.common;

import static simpleDelegator5.core.util.ComponentFinder.find;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.api.view.ViewContainer;
import simpleDelegator5.api.view.ViewException;
import simpleDelegator5.core.controller.AbstractViewController;


/**
 * @author Mario Garcia
 * @since 1.0.2
 * 
 */
public class TestController extends
		AbstractViewController<ActionListener, ActionEvent> {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.controller.ViewController#getSupportedClass()
	 */
	public Class<ActionListener> getSupportedClass() {
		return ActionListener.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.viewaframework.controller.AbstractViewController#postHandlingView
	 * (org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, ActionEvent eventObject)
			throws ViewException {
		logger.info("hello_controller");
		JTextField field = find(JTextField.class).in(view).named("text");
		field.setText("Hey it worked");
	}
}