package com.ciprian12.robotworld.playground;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.robot.Robot;
import com.ciprian12.robotworld.utils.CommandsReader;
import com.ciprian12.robotworld.utils.ConfigurationRobotWorld;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by cipri on 8/3/16.
 */
public class PlaygroundDemoMain {

    public static void main(String[] args){

        String commandsFile = "robot-commands.txt";
        if(args.length != 1){
            System.out.println("Working with the default input file: " + commandsFile);
        }
        else {
            commandsFile = args[0];
        }

        startDemo(commandsFile);

    }


    private static void startDemo(String commandsFile){

        ConfigurationRobotWorld configurationRobotWorld = ConfigurationRobotWorld.instance();

        int stackHeight = configurationRobotWorld.wareHouseConfigInt(IWareHouse.STACK_HEIGHT);
        int stackNumber = configurationRobotWorld.wareHouseConfigInt(IWareHouse.STACK_NUMBER);

        System.out.println(String.format("stackHeight=%d stackNumber=%d", stackHeight, stackNumber));
        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);

        List<IContainerCommand> commands = CommandsReader.readCommands(commandsFile, wareHouse);

        Robot robot = new Robot(wareHouse);

        for(IContainerCommand cmd : commands){
            robot.storeCommand(cmd);
        }


    }



}
