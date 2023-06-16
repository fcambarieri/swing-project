/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core.imp;

import com.swing.core.DatetimeUtils;
import com.swing.core.GuiUtils;
import com.swing.core.NumericUtils;
import com.swing.core.SwingController;
import com.swing.core.SwingControllerFactory;
import com.swing.core.validator.DefaultSwingValidatorHandler;

/**
 *
 * @author fernando
 */
public class DefaultSwingControllerFactory implements SwingControllerFactory{

    private final DatetimeUtils datetimeUtils = new DefaultDatetimeUtils();
    private final GuiUtils guiUtils = new DefaultGuiUtils();
   
    
    @Override
    public <T extends SwingController> T createController(Class<T> controllerClass) {
       try {
            T controller = controllerClass.getDeclaredConstructor().newInstance();
            controller.setDatetimeUtils(datetimeUtils);
            controller.setGuiUtils(guiUtils);
            //controller.setSwingValidator(new DefaultSwingValidatorHandler());
            controller.setSwingBinder(new DefaultSwingBinder(null));
            controller.setSwingValidatorHandler(new DefaultSwingValidatorHandler());
            controller.initController();
            return controller;
        } catch(Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
}
