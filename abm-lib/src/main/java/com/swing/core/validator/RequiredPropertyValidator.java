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
public class RequiredPropertyValidator extends AbstractSwingPropertyValidator {

    private Property<?> property;
    
    public RequiredPropertyValidator(Property<?> property, String message) {
        super(property, message);
        this.property = property;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Property<?> getProperty() {
        return property;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean validate() {
        return property.get() != null;
    }

}
