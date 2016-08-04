package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;

import java.util.Objects;

/**
 * Created by cipri on 8/4/16.
 */
public class AddContainerCommand implements IContainerCommand {

    private IWareHouse wareHouse;
    private IContainer container;
    private int stackId;

    public AddContainerCommand(IWareHouse wareHouse, IContainer container, int stackId){
        this.container = container;
        this.stackId = stackId;
        this.wareHouse = wareHouse;
    }

    @Override
    public boolean execute() {
        return wareHouse.putContainer(container, stackId);
    }

    @Override
    public int hashCode(){
        return stackId + container.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof AddContainerCommand)){
            return false;
        }
        AddContainerCommand other = (AddContainerCommand) o;
        if(other.stackId != this.stackId)
            return false;
        if(!other.container.equals(this.container))
            return false;
        return true;
    }
}
