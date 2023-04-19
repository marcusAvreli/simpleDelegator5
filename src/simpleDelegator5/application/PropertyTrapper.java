package simpleDelegator5.application;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import simpleDelegator5.application.test.ApplicationTrapper;
import simpleDelegator5.application.test.Trapper;
import simpleDelegator5.application.test.ViewTrapper;
import simpleDelegator5.application.test.application.ApplicationTrapperAware;
import simpleDelegator5.application.test.application.ViewTrapperAware;



public class PropertyTrapper 
	implements Trapper<Object>, ApplicationTrapperAware, 
	ViewTrapperAware{

	private static final Logger logger = LoggerFactory.getLogger(PropertyTrapper.class);
	private ApplicationTrapper applicationTrapper;	
	private Object target;
	
	/**
	 * @param applicationTrapper
	 * @param componentName
	 */
	public PropertyTrapper(ApplicationTrapper applicationTrapper,String targetName) {
		this.applicationTrapper = applicationTrapper;
		this.target = applicationTrapper.view().getTarget();
		
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ApplicationTrapperAware#applicationTrapper()
	 */
	public ApplicationTrapper applicationTrapper() {
		return this.applicationTrapper;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asByte()
	 */
	public PropertyTrapper asByte() {
		
		return this;
	}
	
	
	/**
	 * @return
	 */
	public PropertyTrapper asCollection(){
		this.target = Collection.class.cast(target);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asDouble()
	 */
	public PropertyTrapper asDouble() {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asFloat()
	 */
	public PropertyTrapper asFloat() {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asInteger()
	 */
	public PropertyTrapper asInteger() {
		
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asLong()
	 */
	public PropertyTrapper asLong() {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asNumber()
	 */
	public PropertyTrapper asNumber() {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#asShort()
	 */
	public PropertyTrapper asShort() {
		
		return this;
	}

	/**
	 * @return
	 */
	public Integer collectionSize(){
		Integer result = -1;
		if (this.target.getClass().isAssignableFrom(Collection.class)){
			result = Collection.class.cast(this.target).size();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getTarget()
	 */
	public Object getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#getType()
	 */
	public Class<Object> getType() {
		return Object.class;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.test.Trapper#log(java.lang.String)
	 */
	public PropertyTrapper log(String message) {
		logger.info(message);
		return this;
	}

	/**
	 * @param propertyName
	 * @return
	 */
	public PropertyTrapper property(String propertyName){
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireByte(java.lang.Byte)
	 */
	public PropertyTrapper requireByte(Byte number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireDouble(java.lang.Double)
	 */
	public PropertyTrapper requireDouble(Double number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireFloat(java.lang.Float)
	 */
	public PropertyTrapper requireFloat(Float number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireInteger(java.lang.Integer)
	 */
	public PropertyTrapper requireInteger(Integer number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireLong(java.lang.Long)
	 */
	public PropertyTrapper requireLong(Long number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireNumber(java.lang.Number)
	 */
	public PropertyTrapper requireNumber(Number number) {
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.NumberAware#requireShort(java.lang.Short)
	 */
	public PropertyTrapper requireShort(Short number) {
		
		return this;
	}

	/**
	 * @param expected
	 * @return
	 */
	public PropertyTrapper requireSize(Integer expected){
		
		return this;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.Trapper#setTarget(java.lang.Object)
	 */
	public void setTarget(Object target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view()
	 */
	public ViewTrapper view() {
		return this.applicationTrapper().view();
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.widget.test.ViewTrapperAware#view(java.lang.String)
	 */
	public ViewTrapper view(String viewId) {
		return this.applicationTrapper().view(viewId);
	}

}
