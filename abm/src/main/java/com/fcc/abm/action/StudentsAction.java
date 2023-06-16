/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.action;

import com.fcc.abm.controller.StudentController;
import com.fcc.abm.controller.StudentsController;
import com.fcc.abm.domain.StudentRepository;
import com.swing.core.Facade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author fernando
 */
public class StudentsAction implements ActionListener{

    private StudentRepository repository;
    private CreateStudentAction createAction;

    public StudentsAction(StudentRepository repository, CreateStudentAction createAction) {
        this.repository = repository;
        this.createAction = createAction;
    }

   
    
    @Override
    public void actionPerformed(ActionEvent e) {
       StudentsController controller = Facade.getSwingControllerFactory().createController(StudentsController.class);
       controller.setAgregarAction(createAction);
       controller.assignStudents(repository.listStudent());
       JDialog dialog = Facade.getGuiUtils().createNoModalDialog(controller.getPanel(), "Students", null);
       dialog.setVisible(true);
    }
    
}
