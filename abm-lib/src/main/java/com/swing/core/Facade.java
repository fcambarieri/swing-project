/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core;

import com.swing.core.imp.DefaultGuiUtils;
import com.swing.core.imp.DefaultSwingControllerFactory;

/**
 *
 * @author fernando
 */
public final class Facade {
    private static GuiUtils guiUtils = new DefaultGuiUtils();
    private static SwingControllerFactory factory = new DefaultSwingControllerFactory();
    
    public static SwingControllerFactory getSwingControllerFactory() {
        return factory;
    }

    public static GuiUtils getGuiUtils() {
        return guiUtils;
    }
    
}
