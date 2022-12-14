package com.endava.petclinic.services;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.util.EnvReader;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

// This is not recommended for a real project.
// It's better to interogate the db through an API, instead of doing so directly;
public class DBService {

    public Owner getOwnerById(Long id){

        try (Connection conn = DriverManager.getConnection(EnvReader.getDBUrl(),EnvReader.getDBUsername(), EnvReader.getDBPassword())){
            var mapColumnsToProperties = new HashMap<String,String>();
            //mapping your database here
            mapColumnsToProperties.put("first_name", "firstName");
            mapColumnsToProperties.put("last_name", "lastName");
            BeanProcessor beanProcessor = new BeanProcessor(mapColumnsToProperties);
            RowProcessor rowProcessor = new BasicRowProcessor(beanProcessor);
            ResultSetHandler<Owner> h = new BeanHandler<Owner>(Owner.class, rowProcessor);

            QueryRunner runner = new QueryRunner();
            Owner owner = runner.query(conn, "SELECT * FROM owners WHERE id = ?", h, id); // ? is replaced with id variable
            return owner;
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to DB", e);
        }
    }
}
