/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.action;

import com.fcc.abm.infrastructure.vendor.Repositories;

/**
 *
 * @author fernando
 */
public final class Actions {
    
    public static final CreateStudentAction studentAction = new CreateStudentAction(Repositories.studentRepository);
    public static final StudentsAction studentsAction = new StudentsAction(Repositories.studentRepository, studentAction);
}
