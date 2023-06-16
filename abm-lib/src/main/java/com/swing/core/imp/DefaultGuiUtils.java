/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core.imp;

import com.swing.core.ButtonListener;
import com.swing.core.GuiUtils;
import com.swing.core.WindowManager;
import com.swing.core.utils.ErrorForm;
import com.swing.core.utils.NotificacionForm;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author fernando
 */
public class DefaultGuiUtils implements GuiUtils {

    @Override
    public JDialog panelDialog(JComponent form, String title, JButton defaultButton) {
        JDialog dialog = new JDialog(WindowManager.getDefault().getMainWindow());
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.add(form);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.pack();
        dialog.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        if (defaultButton != null) {
            dialog.getRootPane().setDefaultButton(defaultButton);
        }
        return dialog;
    }

    @Override
    public JDialog createNoModalDialog(JComponent form, String title, JButton defaultButton) {
        JDialog dialog = new JDialog(WindowManager.getDefault().getMainWindow());
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.add(form);
        dialog.setModal(false);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.pack();
        dialog.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        if (defaultButton != null) {
            dialog.getRootPane().setDefaultButton(defaultButton);
        }
        return dialog;
    }

    @Override
    public void notification(String message) {
        NotificacionForm form = new NotificacionForm();
        final JDialog dialog = panelDialog(form, "Notificación", form.btnAceptar);
        form.txtArea.setText(message);
        form.setListener(() -> {
            dialog.setVisible(false);
        });

        dialog.setVisible(true);
    }

    @Override
    public boolean notificationError(String message, Throwable ex) {
        final ErrorForm form = new ErrorForm();
        final JDialog dialog = panelDialog(form, "Advertencia", form.getDefaultButton());

        form.initForm(message, ex);

        form.setListener(() -> {
            dialog.setVisible(false);
        });
        dialog.setVisible(true);
        return false;
    }

}
