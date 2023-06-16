/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.action;

import com.fcc.abm.controller.StudentController;
import com.fcc.abm.domain.Student;
import com.fcc.abm.domain.StudentRepository;
import com.swing.core.Facade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author fernando
 */
public class CreateStudentAction implements ActionListener {
    
    private final StudentRepository repository;

    public CreateStudentAction(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createStudent();
    }
    
    public Student createStudent() {
        StudentController controller = Facade.getSwingControllerFactory().createController(StudentController.class);
        if (controller.showModal()) {
            //Do something
            Student s = controller.buildStudent();
            try {
                Long id = repository.createStudent(s);
                s.setId(id);
                return s;
            } catch (Throwable t) {
                controller.getGuiUtils().notificationError("Error creating user", t);
            }
        }
        return null;
    }
    
}
