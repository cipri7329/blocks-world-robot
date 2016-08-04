package com.ciprian12.robotworld.robot;

import com.ciprian12.robotworld.commands.IContainerCommand;

import java.util.Stack;

/**
 * Created by cipri on 8/4/16.
 */
public class Robot {

    private Stack<IContainerCommand> commandsMemory;

    public Robot(){
        commandsMemory = new Stack<>();
    }

    public void storeCommand(IContainerCommand command){
        commandsMemory.push(command);
    }

    public void executeCommands(){

    }

}
