/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.controller;

import com.fcc.abm.action.CreateStudentAction;
import com.fcc.abm.domain.Student;
import com.swing.core.ListTableModel;
import com.swing.core.table.SwingTableController;
import java.util.List;

/**
 *
 * @author fernando
 */
public class StudentsController extends SwingTableController<Student> {

    private CreateStudentAction agregarAction;

    public void setAgregarAction(CreateStudentAction agregarAction) {
        this.agregarAction = agregarAction;
    }

    
    @Override
    protected ListTableModel<Student> configureTable() {
        ListTableModel<Student> table = new ListTableModel<>();
        table.setColumnClasses(new Class[]{Long.class, String.class, String.class, String.class, Boolean.class});
        table.setColumnTitles(new String[]{"id", "Name", "LastName", "DNI", "Status"});
        table.setColumnMethodNames(new String[]{"getId", "getName", "getLastName","getDNI", "getStatus"});
        return table;
    }

    @Override
    protected Student agregarAction() {
        return agregarAction.createStudent();
    }

    @Override
    protected Student editarAction(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void quitarAction(Student value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public void assignStudents(List<Student> students) {
        getTableList().assignData(students);
    }
    
}
