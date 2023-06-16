/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swing.core.table;

import com.swing.core.ListProperty;
import com.swing.core.ListTableModel;
import com.swing.core.Property;
import com.swing.core.SwingController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;


/**
 *
 * @author fernando
 */
public abstract class SwingTableController<T> extends SwingController {

    private SwingTableForm form = new SwingTableForm();
    private final ListProperty<T> tableList = new ListProperty<T>("tableList");
    private final Property<Integer> index = new Property<Integer>("tableIndex");
    private PropertyChangeListener swingControllerChangeEvent;
    private ListTableModel<T> listTableModel;
    private SwingTableFilter swingTableFilter;
    
    @Override
    protected JPanel getForm() {
        return form;
    }

    @Override
    protected void onPresentationModelChange(PropertyChangeEvent evt) {
        if (evt.getSource() == tableList) {
            boolean enabled = tableList.getList().size() > 0;
            setEnabledEditar(enabled);
            setEnabledQuitar(enabled);
        }
        
        if (getSwingControllerChangeEvent() != null)
            getSwingControllerChangeEvent().propertyChange(evt);

    }

    public ListProperty<T> getTableList() {
        return tableList;
    }

    public Property<Integer> getIndex() {
        return index;
    }

    public void setEnabledQuitar(boolean enabled) {
        form.btnQuitar.setEnabled(enabled);
    }

    public void setEnabledEditar(boolean enabled) {
        form.btnEditar.setEnabled(enabled);
    }

    public void setEnabledAgregar(boolean enabled) {
        form.btnAgregar.setEnabled(enabled);
    }

    @Override
    public void initController() {
        super.initController();
        configureView();
        configureBindings();
        installActions();
    }

    private void configureView() {
        this.listTableModel = configureTable();
        form.xTable.setModel(listTableModel);

        configureTaskPane();
    }

    private void configureBindings() {
        getSwingBinder().bindSingleSelectionTable(form.xTable, tableList, index);
    }
    
    private void installActions() {
        form.btnAgregar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                T newValue = agregarAction();
                if (newValue != null) {
                    tableList.add(newValue);
                }
            }
        });

        form.btnEditar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if (index.get() != null && index.get().intValue() >= 0) {
                    T newValue = editarAction(index.get().intValue());
                    if (newValue != null) {
                        form.xTable.repaint();
                    }
                }
            }
        });

        form.btnQuitar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                quitarAction();
            }
        });

        
        
    }
    
    protected void quitarAction() {
        if (index.get() != null && index.get().intValue() >= 0) {
            quitarAction(tableList.get(index.get().intValue()));
            tableList.remove(index.get().intValue());
        }
    }

    protected JButton getAgregarButton() {
        return form.btnAgregar;
    }

    protected JButton getEditarButton() {
        return form.btnEditar;
    }

    protected JButton getQuitarButton() {
        return form.btnQuitar;
    }

    protected JXTable getJXTable() {
        return form.xTable;
    }

    private void configureTaskPane() {

    }

    public void setSearchPanelVisible(boolean visible) {
        if (swingTableFilter != null)
            swingTableFilter.setVisible(visible);
    }

    public JPanel getPanel() {
        return getForm();
    }

    protected abstract ListTableModel<T> configureTable();

    protected abstract T agregarAction();

    protected abstract T editarAction(int index);
    
    protected abstract void quitarAction(T value);





    public PropertyChangeListener getSwingControllerChangeEvent() {
        return swingControllerChangeEvent;
    }

    public void setSwingControllerChangeEvent(PropertyChangeListener swingControllerChangeEvent) {
        this.swingControllerChangeEvent = swingControllerChangeEvent;
    }
    
}