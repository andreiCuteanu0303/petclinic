package com.endava.petclinic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

    private static final Properties properties = new Properties();

    public static void getEnv(){ //se va executa o singura data
        if(properties.isEmpty()){
            String env = System.getProperty("env");
            InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream("env/"+env+".properties");
            try {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                throw new RuntimeException("Can't read properties file!", e); // e = cause of runtime exception
            }
        }
    }

    public static String getBaseUri(){
        EnvReader.getEnv();
        return properties.getProperty("baseUri");
    }

    //Integer.parseInt -> converts String to Integer
    public static Integer getPort(){
        EnvReader.getEnv();
        return Integer.parseInt(properties.getProperty("port"));
    }

    public static String getBasePath(){
        EnvReader.getEnv();
        return properties.getProperty("basePath");
    }

    public static String getAdminUsername(){
        EnvReader.getEnv();
        return properties.getProperty("admin.username");
    }

    public static String getAdminPassword(){
        EnvReader.getEnv();
        return properties.getProperty("admin.password");
    }

    public static String getDBUrl(){
        EnvReader.getEnv();
        return properties.getProperty("db.url");
    }

    public static String getDBUsername(){
        EnvReader.getEnv();
        return properties.getProperty("db.username");
    }

    public static String getDBPassword(){
        EnvReader.getEnv();
        return properties.getProperty("db.password");
    }
}
