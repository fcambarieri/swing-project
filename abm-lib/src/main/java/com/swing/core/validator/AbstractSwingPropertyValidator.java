/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swing.core.validator;

import com.swing.core.Property;


/**
 *
 * @author fernando
 */
public abstract class AbstractSwingPropertyValidator extends  AbstractSwingValidator {

    
    protected <T> AbstractSwingPropertyValidator(Property<T> property, String message) {
        super(message);
    }

    public abstract <T> Property<T> getProperty() ;
}
