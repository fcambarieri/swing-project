/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.swing.core;

import java.util.Date;

/**
 *
 * @author fernando
 */
public interface DatetimeUtils {
    String formatDate(Date date);
    String formatTime(Date date);
    String formatDateTime(Date date);
    
    Date parseDate(String value);
    Date parseDateTime(String value);
    
    Date ultimaHora(Date date);
    Date primeraHora(Date date);
    
    int countDayDiff(Date desde, Date hasta);
}
