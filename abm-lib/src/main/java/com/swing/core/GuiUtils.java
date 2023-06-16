/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.swing.core;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 *
 * @author fernando
 */
public interface GuiUtils {
    JDialog panelDialog(JComponent form, String title, JButton defaultButton);
    
    JDialog createNoModalDialog(JComponent form, String title, JButton defaultButton);
   
    void notification(String message);
    
    boolean notificationError(String message, Throwable ex);
}
