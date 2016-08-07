package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;

import java.util.LinkedList;

/**
 * Created by cipri on 8/4/16.
 */
public class FillContainerCommand implements IContainerCommand {

    private static final Logger logger = Logger.getLogger(FillContainerCommand.class);

    private LinkedList<IContainerCommand> executedIntermmediateSteps;

    private IWareHouse wareHouse;
    private String containerName;
    private int amount;

    public FillContainerCommand(IWareHouse wareHouse, String container, int amount){
        this.containerName = container;
        this.amount = amount;
        this.wareHouse = wareHouse;

        executedIntermmediateSteps = new LinkedList<>();
    }

    @Override
    public String type() {
        return "fill";
    }

    @Override
    public boolean execute() throws InvalidContainerException, InsufficientSpaceException {
        logger.debug("execute: " + toString());

        IContainerCommand cmd = new TopContainerCommand(wareHouse, containerName);
        boolean topStatus = cmd.execute();
        if(topStatus){
            executedIntermmediateSteps.add(cmd);
        }
        else{
            throw new InsufficientSpaceException("insufficient space!");
        }

        IContainer container = wareHouse.peekContainer(containerName);
        boolean status = container.fillWith(amount);
        if(!status){
            while(executedIntermmediateSteps.size() > 0){
                IContainerCommand revertCmd = executedIntermmediateSteps.removeLast();
                if(!revertCmd.revert()){
                    throw new InsufficientSpaceException("fill revert failed!");
                }
            }
            throw new InsufficientSpaceException("insufficient filling space!");
        }

        logger.debug(toString() + "\n" + wareHouse.stackString());
        return status;
    }

    @Override
    public boolean revert() throws InvalidContainerException, InsufficientSpaceException {
        logger.debug("revert: " + toString());

        while(executedIntermmediateSteps.size() > 0){
            IContainerCommand revertCmd = executedIntermmediateSteps.removeLast();
            if(!revertCmd.revert()){
                throw new InsufficientSpaceException("fill revert failed!");
            }
        }

        IContainer container = wareHouse.peekContainer(containerName);
        boolean status = container.fillWith(-amount);

        logger.debug("revert: " + toString() + "\n" + wareHouse.stackString());
        return status;
    }

    @Override
    public int hashCode(){
        return (containerName.hashCode() +"#" + amount).hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof FillContainerCommand)){
            return false;
        }
        FillContainerCommand other = (FillContainerCommand) o;
        if(other.amount != this.amount)
            return false;
        if(!other.containerName.equals(this.containerName))
            return false;
        return true;
    }

    @Override
    public String toString(){
        String result = String.format(" %s %s %s", type(), containerName, amount);
        return result;
    }
}
