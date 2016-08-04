package com.ciprian12.robotworld.utils;

import com.ciprian12.robotworld.warehouse.IWareHouse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * Created by cipri on 8/4/16.
 */
public class ConfigurationRobotWorld {

    static final Logger logger = Logger.getLogger(ConfigurationRobotWorld.class);

    private Properties wareHouseProperties;

    private static ConfigurationRobotWorld _instance = null;

    public static ConfigurationRobotWorld instance(){
        if(_instance != null)
            return _instance;

        _instance = new ConfigurationRobotWorld();
        _instance.initWarehouseConfig();


        return _instance;
    }

    public int wareHouseConfigInt(String propertyName){
        if(wareHouseProperties == null)
            return -1;
        String numberString = wareHouseProperties.getProperty(propertyName);
        try {
            int number = Integer.valueOf(numberString);
            return number;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    public String wareHouseConfigString(String propertyName){
        if(wareHouseProperties == null)
            return null;
        return wareHouseProperties.getProperty(propertyName);
    }

    private void initWarehouseConfig(){
        InputStream inputStream;
        try {
            wareHouseProperties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(IWareHouse.WAREHOUSE_PROPERTIES);
            if (inputStream != null) {
                wareHouseProperties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + IWareHouse.WAREHOUSE_PROPERTIES + "' not found in the classpath");
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }


    private void readConfiguration(){

    }
}
