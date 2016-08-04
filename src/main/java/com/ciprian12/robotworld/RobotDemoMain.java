package com.ciprian12.robotworld;

import com.ciprian12.robotworld.utils.ConfigurationRobotWorld;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cipri on 8/3/16.
 */
public class RobotDemoMain {

    public static void main(String[] args){

        if(args.length != 1){
            System.out.println("Please provide an input file with commands: <commands-file>");
            return;
        }

        startDemo();

    }


    private static void startDemo(){

        ConfigurationRobotWorld configurationRobotWorld = ConfigurationRobotWorld.instance();

        int stackHeight = configurationRobotWorld.wareHouseConfigInt(IWareHouse.STACK_HEIGHT);
        int stackNumber = configurationRobotWorld.wareHouseConfigInt(IWareHouse.STACK_NUMBER);

        System.out.println(String.format("stackHeight=%d stackNumber=%d", stackHeight, stackNumber));

//        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);


    }



}
