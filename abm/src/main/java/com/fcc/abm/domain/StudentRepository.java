/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fcc.abm.domain;

import java.util.List;

/**
 *
 * @author fernando
 */
public interface StudentRepository {
    Long createStudent(Student s);
    void updateStudent(Student s);
    List<Student> listStudent();
}
