package com.manish.spring.config;


import com.manish.spring.domain.DBDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource(value = {"classpath:my-db-config.properties", "classpath:my-ip-config.properties"})
public class MyConfig {

    @Value("${db.name}")
    private String databaseName;

    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String databaseUsername;

    @Value("${db.password}")
    private String databasePassword;

    @Value("${db.port:3306}")
    private int databasePort;

    @Value("${db.mySqlDB:false}")
    private boolean mySqlDB;

    @Value("${myserver.ip}")
    private String serverIp;


    @Autowired
    private Environment env;

    @Bean
    public DBDetails dbDetailsUsingValue(){
        DBDetails dbDetails = new DBDetails();
        dbDetails.setX(databaseName);
        dbDetails.setY(databaseUrl);
        dbDetails.setZ(databaseUsername);
        dbDetails.setA(databasePassword);
        dbDetails.setB(databasePort);
        dbDetails.setC(mySqlDB);
        System.out.println("With @Value "+serverIp);
        return dbDetails;
    }

    @Bean("dbDetailsUsingEnvironment")
    public DBDetails dbDetailsUsingEnvironment(){
        DBDetails dbDetails = new DBDetails();
        dbDetails.setX(env.getProperty("db.name"));
        dbDetails.setY(env.getProperty("db.url"));
        dbDetails.setZ(env.getProperty("db.username"));
        dbDetails.setA(env.getProperty("db.password"));
        dbDetails.setB(Integer.parseInt(env.getProperty("db.port", "3306")));
        dbDetails.setC(Boolean.parseBoolean(env.getProperty("db.mySqlDB")));

        System.out.println("With Env "+env.getProperty("myserver.ip"));
        return dbDetails;
    }


}
