package com.ciprian12.robotworld.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by cipri on 8/4/16.
 */
public class Robot {

    private static final Logger logger = Logger.getLogger(Robot.class);

    private LinkedList<IContainerCommand> intermmediateCommandsMemory;
    private LinkedList<IContainerCommand> toExecuteCommands;

    private LinkedList<IContainerCommand> executedCommands;
    private LinkedList<IContainerCommand> executedItermmediateCommands;

    private IWareHouse wareHouse;

    public Robot(IWareHouse wareHouse){
        this.wareHouse = wareHouse;

        intermmediateCommandsMemory = new LinkedList<>();
        toExecuteCommands = new LinkedList<>();

        executedCommands = new LinkedList<>();
        executedItermmediateCommands = new LinkedList<>();
    }

    public void storeCommand(IContainerCommand command){
        if(command == null)
            return;
        toExecuteCommands.add(command);
    }

    public void storeCommands(List<IContainerCommand> commandList){
        if(commandList == null)
            return;
        for(IContainerCommand cmd : commandList){
            storeCommand(cmd);
        }
    }

    public boolean executeCommands() throws InvalidContainerException, InsufficientSpaceException {
        boolean status = true;
        while(hasNextCommand())
            status = status && executeNextCommand();
        return status;
    }

    public boolean executeNextCommand() throws InvalidContainerException, InsufficientSpaceException {

        IContainerCommand nextCmd = toExecuteCommands.pop();
        logger.debug("cmd: " + nextCmd.toString());
        boolean status = nextCmd.execute();
        if(!status){
            //TODO: implement revert
            boolean revertStatus = nextCmd.revert();
            logger.info(String.format("cmd failed: %s reverted status: %s", nextCmd, revertStatus));
            if(revertStatus){
                //TODO:
            }
        }
        else {
            executedCommands.add(nextCmd);
        }

        return status;
    }

    public void undoCommand(){

    }

    public boolean hasNextCommand(){
        return toExecuteCommands.size() != 0;
    }
}
