package simpleDelegator5.application.test;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleDelegator5.application.test.component.AbstractButtonTrapper;


/**
 * @author Mario Garcia
 *
 */
public class ButtonTrapper 
	extends AbstractButtonTrapper<JButton>{

	private static final Logger logger = LoggerFactory.getLogger(ButtonTrapper.class);
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public ButtonTrapper(ApplicationTrapper applicationTrapper,String componentName) {
		super(applicationTrapper, componentName);
	}

	/**
	 * @param applicationTrapper
	 * @param viewId
	 * @param componentName
	 */
	public ButtonTrapper(ApplicationTrapper applicationTrapper, String viewId,String componentName) {
		super(applicationTrapper, viewId, componentName);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#click()
	 */
	@Override
	public AbstractButtonTrapper<JButton> click() {
		return ButtonTrapper.class.cast(super.click());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#click(long)
	 */
	@Override
	public ButtonTrapper click(long millis) {
		return  ButtonTrapper.class.cast(super.click(millis));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<JButton> getType() {
		return JButton.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public ButtonTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireEnabled()
	 */
	@Override
	public ButtonTrapper requireEnabled() {
		return ButtonTrapper.class.cast(super.requireEnabled());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireEnabled(boolean)
	 */
	@Override
	public ButtonTrapper requireEnabled(boolean enabled) {
		return ButtonTrapper.class.cast(super.requireEnabled(enabled));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireText(java.lang.String)
	 */
	@Override
	public ButtonTrapper requireText(String text) {
		return ButtonTrapper.class.cast(super.requireText(text));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireToolTipText(java.lang.String)
	 */
	@Override
	public ButtonTrapper requireToolTipText(String text) {
		return ButtonTrapper.class.cast(super.requireToolTipText(text));
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractComponentTrapper#requireVisible()
	 */
	@Override
	public ButtonTrapper requireVisible() {
		return ButtonTrapper.class.cast(super.requireVisible());
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.component.AbstractButtonTrapper#requireVisible(boolean)
	 */
	@Override
	public ButtonTrapper requireVisible(boolean visible) {
		return ButtonTrapper.class.cast(super.requireVisible(visible));
	}
}
