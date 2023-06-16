/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author fernando
 */
public class Property<T> {
    private String name;
    private T value;
    private PropertyChangeSupport propertyChangeSupport;
    
    public Property(String name) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public T get() {
        return value;
    }
    
    public void set(T newValue) {
        T oldValue = get();
        value = newValue;
        propertyChangeSupport.firePropertyChange(name, oldValue, newValue);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
