/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fcc.abm.infrastructure.vendor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sql2o.Sql2o;

/**
 *
 * @author fernando
 */
public class DatabaseConfiguration {
    
    public static Sql2o build() {
        return new Sql2o(buildPool());
    } 
    
    public static HikariDataSource buildPool() {
        Properties props = loadDBProperties();
        HikariConfig config = props != null ? new HikariConfig(props) : buildDefault();
        return new HikariDataSource(config);
    }
    
    private static Properties loadDBProperties() {
        if (Files.exists(Paths.get("/config/connection.properties"))) {
            try {
                Properties props = new Properties();
                props.load(new FileInputStream(Paths.get("config/connection.properties").toFile()));
            } catch (IOException ex) {
                Logger.getLogger(DatabaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }       
        }
        return null;
    }
    
    private static HikariConfig buildDefault() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost/universidad_ulp?useLegacyDatetimeCode=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("my-secret-pw");
        return config;
    }
}
