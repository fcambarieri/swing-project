/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.core.imp;

import com.swing.core.DelegatingComboBoxModel;
import com.swing.core.ListProperty;
import com.swing.core.ListTableModel;
import com.swing.core.NumericUtils;
import com.swing.core.Property;
import com.swing.core.SwingBinder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author fernando
 */
public class DefaultSwingBinder implements SwingBinder{

    private static final int NO_SELECTION_INDEX = -1;
    private final PropertyChangeSupport propertyChangeSupport;
    private final NumericUtils numericUtils;
    
    public DefaultSwingBinder(NumericUtils numericUtils) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.numericUtils = numericUtils;
    }

    private void notifyBindingListener(PropertyChangeEvent evt) {
        propertyChangeSupport.firePropertyChange(evt);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    public void bindCheckBoxToBoolean(final JCheckBox checkBox, final Property<Boolean> booleanProperty) {
        checkBox.addActionListener((ActionEvent evt) -> {
            if (booleanProperty.get() == null || (booleanProperty.get().booleanValue() != checkBox.isSelected())) {
                booleanProperty.set(Boolean.valueOf(checkBox.isSelected()));
            }
        });

        booleanProperty.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (booleanProperty.get() != null && (checkBox.isSelected() != booleanProperty.get().booleanValue())) {
                checkBox.setSelected(booleanProperty.get().booleanValue());
            }
            notifyBindingListener(evt);
        });
    }


    public void bindTextFieldToInteger(final JTextField textField, final Property<Integer> integerProperty) {
        textField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }

            private void textChanged() {
                Integer newValue;
                boolean mustSet;
                try {
                    newValue = new Integer(textField.getText());
                    mustSet = !newValue.equals(integerProperty.get());
                } catch (Throwable t) {
                    newValue = null;
                    mustSet = integerProperty.get() != null;
                }
                if (mustSet) {
                    integerProperty.set(newValue);
                //else
                //    validatorHandler.notifyValidation(textField,integerProperty);
                }
            }
        });

        integerProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (integerProperty.get() != null) {
                    Integer textFieldValue;
                    Integer propertyValue = integerProperty.get();
                    try {
                        textFieldValue = new Integer(textField.getText());
                    } catch (Throwable t) {
                        textFieldValue = null;
                    }

                    if (!propertyValue.equals(textFieldValue)) {
                        textField.setText(propertyValue.toString());
                    }
                }

                //validatorHandler.notifyValidation(textField,integerProperty);
                notifyBindingListener(evt);
            }
        });
    }
    
    public void bindRadioButtonToBoolean(final JRadioButton radioButton, final Property<Boolean> booleanProperty) {
        radioButton.addChangeListener((ChangeEvent e) -> {
            if (booleanProperty.get() == null || (booleanProperty.get().booleanValue() != radioButton.isSelected())) {
                booleanProperty.set(Boolean.valueOf(radioButton.isSelected()));
            }
        });

        booleanProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (booleanProperty.get() != null && (radioButton.isSelected() != booleanProperty.get().booleanValue())) {
                    radioButton.setSelected(booleanProperty.get().booleanValue());
                }
                notifyBindingListener(evt);
            }
        });
    }
    
     public <T> void bindTextComponentToString(final JTextComponent textComponent, final Property<String> stringProperty) {
        textComponent.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                onTextChanged();
            }

            private void onTextChanged() {
                if (stringProperty.get() == null || !stringProperty.get().equals(textComponent.getText())) {
                    stringProperty.set(textComponent.getText());
                }
            }
        });

        stringProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (stringProperty.get() != null && !stringProperty.get().equals(textComponent.getText())) {
                    textComponent.setText(stringProperty.get());
                }
                //validatorHandler.notifyValidation(textComponent,stringProperty);
                notifyBindingListener(evt);
            }
        });
    }

    public void bindSpinnerToInteger(final JSpinner spinner, final Property<Integer> integerProperty) {
        final JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        spinner.addChangeListener((ChangeEvent e) -> {
            if (integerProperty.get() == null || (integerProperty.get().intValue() != (Integer) spinner.getValue())) {
                integerProperty.set((Integer) spinner.getValue());
            }
        });

        integerProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if ((integerProperty.get() != null) && ((Integer) spinner.getValue() != integerProperty.get().intValue())) {
                    spinner.setValue(integerProperty.get().intValue());
                }
                //validatorHandler.notifyValidation(spinner,integerProperty);
                notifyBindingListener(evt);
            }
        });
    }
    
    
    public <T> void bindComboBoxToObject(final JComboBox comboBox, final Property<T> objectProperty, final ListProperty<T> listProperty) {
        listProperty.addListener(new ListProperty.Listener() {

            public void onDataChange() {
                DelegatingComboBoxModel<T> model = (DelegatingComboBoxModel<T>) comboBox.getModel();
                model.fireContentsChanged(model, 0, model.getSize() - 1);
            }

            public void onAddItem(int index) {
                DelegatingComboBoxModel<T> model = (DelegatingComboBoxModel<T>) comboBox.getModel();
                model.fireIntervalAdded(model, index, index);
            }

            public void onRemoveItem(int index) {
                DelegatingComboBoxModel<T> model = (DelegatingComboBoxModel<T>) comboBox.getModel();
                model.fireIntervalRemoved(model, index, index);
            }
        });

        comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                T viewValue = (T) comboBox.getSelectedItem();
                T modelValue = objectProperty.get();

                if ((viewValue != null && !viewValue.equals(modelValue)) || (viewValue == null && modelValue != null)) {
                    objectProperty.set(viewValue);
                }
            }
        });

        objectProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                T viewValue = (T) comboBox.getSelectedItem();
                T modelValue = objectProperty.get();

                if (modelValue == null || (modelValue != null && !modelValue.equals(viewValue))) {
                    comboBox.setSelectedItem(modelValue);                //validatorHandler.notifyValidation(comboBox,objectProperty);
                }
                notifyBindingListener(evt);
            }
        });

    }
    
    public void bindTextFieldToLong(final JTextField textField, final Property<Long> longProperty) {
        textField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                onTextChange();
            }

            public void insertUpdate(DocumentEvent e) {
                onTextChange();
            }

            public void removeUpdate(DocumentEvent e) {
                onTextChange();
            }

            private void onTextChange() {
                Long newValue;
                boolean mustSet;
                try {
                    newValue = new Long(textField.getText());
                    mustSet = !newValue.equals(longProperty.get());
                } catch (Throwable t) {
                    newValue = null;
                    mustSet = longProperty.get() != null;
                }
                if (mustSet) {
                    longProperty.set(newValue);
                //else
                //    validatorHandler.notifyValidation(textField,longProperty);
                }
            }
        });

        longProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (longProperty.get() != null) {
                    Long textFieldValue;
                    Long propertyValue = longProperty.get();
                    try {
                        textFieldValue = new Long(textField.getText());
                    } catch (Throwable t) {
                        textFieldValue = null;
                    }

                    if (!propertyValue.equals(textFieldValue)) {
                        textField.setText(propertyValue.toString());
                    }
                }
                //validatorHandler.notifyValidation(textField,longProperty);
                notifyBindingListener(evt);
            }
        });

    }

    @Override
    public <T> void bindSingleSelectionTable(JXTable table, ListProperty<T> listProperty, Property<Integer> indexProperty) {
     indexProperty.set(NO_SELECTION_INDEX);

        final ListTableModel<T> listTableModel = (ListTableModel<T>) table.getModel();
        listTableModel.setList(listProperty.getList());

        //validatorHandler.notifyValidation(table,listProperty);

        listProperty.addListener(new ListProperty.Listener() {

            public void onDataChange() {
                listTableModel.fireTableDataChanged();
                changeColor();
                notifyBindingListener(new PropertyChangeEvent(listProperty, listProperty.getName(), null, null));
            }

            public void onAddItem(int index) {
                listTableModel.fireTableRowsInserted(index, index);
                changeColor();
                notifyBindingListener(new PropertyChangeEvent(listProperty, listProperty.getName(), null, null));
            }

            public void onRemoveItem(int index) {
                listTableModel.fireTableRowsDeleted(index, index);
                changeColor();
                notifyBindingListener(new PropertyChangeEvent(listProperty, listProperty.getName(), null, null));
            }

            public void changeColor() {
                //validatorHandler.notifyValidation(table,listProperty);
            }
        });


        final ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int tableIndex = table.getSelectedRow();
                    if (tableIndex >= 0) {
                        int tableModelIndex = table.convertRowIndexToModel(tableIndex);
                        if (tableModelIndex != indexProperty.get().intValue()) {
                            indexProperty.set(new Integer(tableModelIndex));
                        }
                    } else {
                        indexProperty.set(NO_SELECTION_INDEX);
                    }
                }
            }
        });



        indexProperty.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                int modelValue = indexProperty.get().intValue();
                if (modelValue != NO_SELECTION_INDEX) {
                    int viewValue = table.convertRowIndexToView(modelValue);
                    int selectedViewIndex = table.getSelectedRow();
                    if (viewValue != selectedViewIndex) {
                        selectionModel.setSelectionInterval(viewValue, viewValue);
                    }
                } else {
                    if (selectionModel.getMinSelectionIndex() != -1) {
                        selectionModel.clearSelection();
                    }
                }

                notifyBindingListener(evt);
            }
        });
    }
}