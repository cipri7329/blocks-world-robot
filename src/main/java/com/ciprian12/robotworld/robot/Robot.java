package com.ciprian12.robotworld.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.warehouse.IWareHouse;

import java.util.Stack;

/**
 * Created by cipri on 8/4/16.
 */
public class Robot {

    private Stack<IContainerCommand> intermmediateCommandsMemory;
    private Stack<IContainerCommand> originalCommandsMemory;

    private IWareHouse wareHouse;

    public Robot(IWareHouse wareHouse){
        this.wareHouse = wareHouse;

        intermmediateCommandsMemory = new Stack<>();
        originalCommandsMemory = new Stack<>();
    }

    public void storeCommand(IContainerCommand command){
        originalCommandsMemory.push(command);
    }

    public void executeCommands(){

    }

    public void executeNextCommand(){

    }

    public void undoCommand(){

    }

}
