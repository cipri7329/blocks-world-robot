package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;

/**
 * Created by cipri on 8/4/16.
 */
public class FillContainerCommand implements IContainerCommand {

    private IContainer container;
    private int amount;

    public FillContainerCommand(IContainer container, int amount){
        this.container = container;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        return container.fillWith(amount);
    }

    @Override
    public int hashCode(){
        return (container.hashCode() +"#" + amount).hashCode();
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
        if(!other.container.equals(this.container))
            return false;
        return true;
    }
}
