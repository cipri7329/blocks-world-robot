package com.ciprian12.robotworld.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by cipri on 8/4/16.
 */
public class Robot {

    private static final Logger logger = Logger.getLogger(Robot.class);

    private Stack<IContainerCommand> intermmediateCommandsMemory;
    private Stack<IContainerCommand> toExecuteCommands;

    private Stack<IContainerCommand> executedCommands;
    private Stack<IContainerCommand> executedItermmediateCommands;

    private IWareHouse wareHouse;

    public Robot(IWareHouse wareHouse){
        this.wareHouse = wareHouse;

        intermmediateCommandsMemory = new Stack<>();
        toExecuteCommands = new Stack<>();

        executedCommands = new Stack<>();
        executedItermmediateCommands = new Stack<>();
    }

    public void storeCommand(IContainerCommand command){
        toExecuteCommands.push(command);
    }

    public void executeCommands(){

    }

    public void executeNextCommand(){

        try {
            IContainerCommand nextCmd = toExecuteCommands.pop();
            nextCmd.execute();

            executedCommands.push(nextCmd);
        }
        catch (EmptyStackException e){
            logger.info("all commands have been executed");
        } catch (InvalidContainerException e) {
            logger.warn(e.getMessage());
        }

        logger.debug(wareHouse.stackString());
    }

    public void undoCommand(){

    }

    public boolean hasNextCommand(){
        return !toExecuteCommands.empty();
    }
}
