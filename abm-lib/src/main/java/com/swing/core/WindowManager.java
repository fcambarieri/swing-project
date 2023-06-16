/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JFrame;

/**
 *
 * @author fernando
 */
public class WindowManager {

    private static WindowManager wm = null;

    private JFrame mainWindow;
    private final AtomicBoolean started = new AtomicBoolean(false);

    private WindowManager() {
    }

    public static WindowManager getDefault() {
        if (wm == null) {
            wm = new WindowManager();
        }
        return wm;
    }

    public void setMainWindow(JFrame window) {
        assert window == null;
        this.mainWindow = window;

    }

    public JFrame getMainWindow() {
        if (mainWindow == null) {
            mainWindow = new JFrame();
        }
        return mainWindow;
    }

    public void startMainWindow() {
        if (!started.get()) {
            java.awt.EventQueue.invokeLater(() -> {
                getMainWindow().setVisible(true);
            });

            started.set(true);

        }
    }

}
