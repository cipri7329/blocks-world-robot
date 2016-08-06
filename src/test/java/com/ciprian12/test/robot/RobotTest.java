package com.ciprian12.test.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.robot.Robot;
import com.ciprian12.robotworld.utils.CommandsReader;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cipri on 8/6/16.
 */
public class RobotTest {

    @Test
    public void simpleMove() throws InvalidContainerException, InsufficientSpaceException {

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
        robot.executeCommands();
    }

    @Test
    public void top1Simple() throws InvalidContainerException, InsufficientSpaceException {

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");

            add("top a1");
        }};
        IWareHouse wareHouse = new WareHouse(5, 4);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);

        assertTrue(robot.executeCommands());
    }

    @Test
    public void top1OneFreePlace() throws InvalidContainerException, InsufficientSpaceException {

        int stackNumber = 3;
        int stackHeight = 2;

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");
            add("add b2 20 1");
            add("add c1 20 2");

            add("top a1");
        }};
        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);

        assertTrue(robot.executeCommands());
    }

    @Test (expected = InsufficientSpaceException.class)
    public void top1InsufficientSpace() throws InvalidContainerException, InsufficientSpaceException {

        int stackNumber = 2;
        int stackHeight = 2;

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");
            add("add b2 20");

            add("top a1");
        }};
        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);

        robot.executeCommands();
    }

    @Test
    public void top2Simple() throws InvalidContainerException, InsufficientSpaceException {

        int stackNumber = 3;
        int stackHeight = 2;

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");
            add("add b2 20 1");

            add("top a1 b1");
        }};
        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);

        assertTrue(robot.executeCommands());
    }

    @Test
    public void top2SameStack() throws InvalidContainerException, InsufficientSpaceException {

        int stackNumber = 3;
        int stackHeight = 4;

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add a3 10");

            add("add b1 20 1");
            add("add b2 20 1");
            add("add b3 20 1");
            add("add b4 20 1");

            add("add c1 20 2");
            add("add c2 20 2");

            add("top b1 b3");
        }};
        IWareHouse wareHouse = new WareHouse(stackNumber, stackHeight);
        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        System.out.print(wareHouse.stackString());

        Robot robot = new Robot(wareHouse);

        robot.storeCommands(commands);

        assertTrue(robot.executeCommands());
    }

}
