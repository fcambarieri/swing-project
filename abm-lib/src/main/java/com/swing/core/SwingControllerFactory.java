/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.swing.core;

/**
 *
 * @author fernando
 */
public interface SwingControllerFactory {
    
    <T extends SwingController> T createController(Class<T> controllerClass);
    
}
