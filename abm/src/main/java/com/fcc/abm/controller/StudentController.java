/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.controller;

import com.fcc.abm.domain.Student;
import com.fcc.abm.infrastructure.view.StudentForm;
import com.swing.core.Property;
import com.swing.core.form.SwingModalController;
import com.swing.core.validator.StringPropertyValidator;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author fernando
 */
public class StudentController extends SwingModalController {

    private final StudentForm form = new StudentForm();
    private final Property<Long> id = new Property<Long>("id");
    private final Property<String> name = new Property<>("name");
    private final Property<String> lastName = new Property<>("lastName");
    private final Property<String> dni = new Property<>("dni");
    private final Property<String> date = new Property<>("date");
    private final Property<Boolean> status = new Property<>("status");

    @Override
    public void initController() {
        super.initController(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        //Init Bindinds
        getSwingBinder().bindTextComponentToString(form.txtLastName, lastName);
        getSwingBinder().bindTextComponentToString(form.txtName, name);
        getSwingBinder().bindTextComponentToString(form.txtDNI, dni);
        getSwingBinder().bindCheckBoxToBoolean(form.chkStatus, status);

        getSwingBinder().bindTextComponentToString(form.txtDate, date);
        
        
        getSwingBinder().bindTextFieldToLong(form.txtID, id);
        form.txtID.setEditable(false);
        
        //validations
        getSwingValidatorHandler().setJLabel(form.lblValidation);
        getSwingValidatorHandler().addSwingValidator(new StringPropertyValidator(name, "name is required"));
        getSwingValidatorHandler().addSwingValidator(
                new StringPropertyValidator(lastName, "lastName has to be between 4 and 10 characters", 4, 10));

        status.set(Boolean.FALSE);
    }

    @Override
    protected JButton getAcceptButton() {
        return form.btAccept;
    }

    @Override
    protected JButton getCancelButton() {
        return form.btnCancel;
    }

    @Override
    protected JPanel getForm() {
        return form;
    }

    @Override
    protected boolean canAcceptDialog() {
        return getSwingValidatorHandler().validate();
    }
    
    public Student buildStudent(){
        Student student = new Student();
        student.setLastName(lastName.get());
        student.setName(name.get());
        student.setDNI(dni.get());
        student.setStatus(status.get());
        student.setBirdDate(getDatetimeUtils().parseDate(date.get()));
        return student;
    } 

}
