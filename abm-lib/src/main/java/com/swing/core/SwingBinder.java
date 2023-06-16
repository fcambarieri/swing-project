/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.swing.core;

import java.beans.PropertyChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author fernando
 */
public interface SwingBinder {

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    void bindCheckBoxToBoolean(final JCheckBox checkBox, final Property<Boolean> booleanProperty);

    void bindTextFieldToInteger(final JTextField textField, final Property<Integer> integerProperty);

    void bindRadioButtonToBoolean(final JRadioButton radioButton, final Property<Boolean> booleanProperty);

    void bindSpinnerToInteger(final JSpinner spinner, final Property<Integer> integerProperty);

    <T> void bindComboBoxToObject(final JComboBox comboBox, final Property<T> objectProperty, final ListProperty<T> listProperty);

    void bindTextFieldToLong(final JTextField textField, final Property<Long> longProperty);

    <T> void bindTextComponentToString(final JTextComponent textComponent, final Property<String> stringProperty);
    
    <T> void bindSingleSelectionTable(final JXTable table, final ListProperty<T> listProperty, final Property<Integer> indexProperty);

}
