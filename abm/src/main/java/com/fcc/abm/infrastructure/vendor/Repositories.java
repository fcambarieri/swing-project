/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.infrastructure.vendor;

import com.fcc.abm.domain.StudentRepository;
import org.sql2o.Sql2o;

/**
 *
 * @author fernando
 */
public final class Repositories {
    private final static Sql2o sql2o = DatabaseConfiguration.build();
    
    public final static StudentRepository studentRepository = new DefaultStudentRepository(sql2o);
}
