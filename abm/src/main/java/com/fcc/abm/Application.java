/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fcc.abm;

import com.fcc.abm.action.Actions;
import com.fcc.abm.action.CreateStudentAction;
import com.swing.core.WindowManager;

/**
 *
 * @author fernando
 */
public class Application {

    public static void main(String[] args) {
        
        
        
        MainAppFrame main = new MainAppFrame();
        main.Create.addActionListener(Actions.studentAction);
        main.menuListStudents.addActionListener(Actions.studentsAction);
        WindowManager.getDefault().setMainWindow(main);
        WindowManager.getDefault().startMainWindow();
    }
}
