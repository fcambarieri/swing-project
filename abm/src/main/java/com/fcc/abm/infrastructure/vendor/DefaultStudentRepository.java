/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.infrastructure.vendor;

import com.fcc.abm.domain.Student;
import com.fcc.abm.domain.StudentRepository;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.ResultSetHandler;
import org.sql2o.Sql2o;

/**
 *
 * @author fernando
 */
public class DefaultStudentRepository implements StudentRepository {

    private Sql2o sql2o;

    public DefaultStudentRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Long createStudent(Student s) {
        final String insertQuery
                = "INSERT INTO alumno (nombre, apellido, dni, estado,fechaNacimiento) "
                + "VALUES (:name, :lastName, :dni, :status, :date)";

        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(insertQuery)
                    .addParameter("name", s.getName())
                    .addParameter("lastName", s.getLastName())
                    .addParameter("dni", s.getDNI())
                    .addParameter("status", s.getStatus())
                    .addParameter("date", s.getBirdDate())
                    .executeUpdate();
            // Remember to call commit() when a transaction is opened,
            // default is to roll back.
            con.commit();
            return ((BigInteger) con.getKey()).longValue();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @Override
    public void updateStudent(Student s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Student> listStudent() {
        final String insertQuery
                = "select * from alumno ";

        try (Connection con = sql2o.open()) {
            return con.createQuery(insertQuery)
                    .executeAndFetch(new ResultSetHandler<Student>() {
                        @Override
                        public Student handle(ResultSet rs) throws SQLException {
                            Student student = new Student();
                            student.setDNI(rs.getString("dni"));
                            student.setId(rs.getLong("idAlumno"));
                            student.setLastName(rs.getString("apellido"));
                            student.setName(rs.getString("nombre"));
                            student.setStatus(rs.getBoolean("estado"));
                            return student;
                        }

                    });
            // Remember to call commit() when a transaction is opened,
            // default is to roll back.

        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
