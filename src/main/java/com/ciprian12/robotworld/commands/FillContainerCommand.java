package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by cipri on 8/4/16.
 */
public class FillContainerCommand implements IContainerCommand {

    private static final Logger logger = Logger.getLogger(FillContainerCommand.class);

    private IWareHouse wareHouse;
    private String containerName;
    private int amount;

    public FillContainerCommand(IWareHouse wareHouse, String container, int amount){
        this.containerName = container;
        this.amount = amount;
        this.wareHouse = wareHouse;
    }

    @Override
    public String type() {
        return "fill";
    }

    @Override
    public boolean execute() {
        logger.debug("execute: " + toString());
        IContainer container = wareHouse.peekContainer(containerName);
        boolean status = container.fillWith(amount);
        logger.debug(toString() + "\n" + wareHouse.stackString());
        return status;
    }

    @Override
    public boolean revert() {

        throw new NotImplementedException();
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
