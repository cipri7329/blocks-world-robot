package com.ciprian12.test.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.robot.Robot;
import com.ciprian12.robotworld.utils.CommandsReader;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cipri on 8/6/16.
 */
public class RobotTest {

    @Test
    public void simpleMove(){

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");

            add("move a1 b1");
        }};
        IWareHouse wareHouse = new WareHouse(5, 4);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);


        robot.executeNextCommand();
        robot.executeNextCommand();
        robot.executeNextCommand();

        robot.executeNextCommand();
    }



}
