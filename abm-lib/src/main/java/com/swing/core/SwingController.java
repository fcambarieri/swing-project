/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core;

import com.swing.core.validator.SwingValidatorHandler;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author fernando
 */
public abstract class SwingController implements PropertyChangeListener {
    
    private SwingBinder swingBinder;
    private DatetimeUtils datetimeUtils;
    private String title;
    private GuiUtils guiUtils;
    private SwingValidatorHandler swingValidatorHandler;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setGuiUtils(GuiUtils utils) {
        this.guiUtils = utils;
    }
    
    public GuiUtils getGuiUtils() {
        return guiUtils;
    }
    
    public void initController() {
        if (getFocusedComponent() != null)
            getFocusedComponent().requestFocusInWindow();
    }
     public void setSwingBinder(SwingBinder binder) {
        this.swingBinder = binder;
        
        if (this.swingBinder != null)
            this.swingBinder.removePropertyChangeListener(this);
        
        this.swingBinder = binder;
        
        this.swingBinder.addPropertyChangeListener(this);
        
    }
     
    public SwingBinder getSwingBinder() {
        return swingBinder;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        onPresentationModelChange(evt);
    }
    
    protected abstract JPanel getForm();
    
    /**
     * Se llama automóticamente cada vez que una bound property
     * cambia de valor.
     */
    protected abstract void onPresentationModelChange(PropertyChangeEvent evt);
    
    /*
     *  Hay que llamar a este método dentro del onPresentationModelChange
     *  de la clase que herede, así, des está forma se llama cunado quiere.
     */    
    protected boolean canAcceptDialog() {
       return true;
    }
    
    protected JComponent getFocusedComponent() {
        return null;
    }

    public void setDatetimeUtils(DatetimeUtils datetimeUtils) {
        this.datetimeUtils = datetimeUtils;
    }

    public DatetimeUtils getDatetimeUtils() {
        return datetimeUtils;
    }

    public void setSwingValidatorHandler(SwingValidatorHandler swingValidatorHandler) {
        this.swingValidatorHandler = swingValidatorHandler;
    }

    public SwingValidatorHandler getSwingValidatorHandler() {
        return swingValidatorHandler;
    }
    
    
    
}
