package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;


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

    public AddContainerCommand(IWareHouse wareHouse, IContainer container){
        this.container = container;
        this.stackId = -1;
        this.wareHouse = wareHouse;
    }

    @Override
    public String type() {
        return "add";
    }

    @Override
    public boolean execute() throws InvalidContainerException {
        if(stackId == -1){
            //find the first empty stack
            for(int stack=0; stack<wareHouse.getStackNumber(); stack++){
                if(wareHouse.freePlacesOnStack(stack) > 0){
                    stackId = stack;
                    break;
                }
            }
        }
        //addition might still fail if no empty stack found
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

    @Override
    public String toString(){
        String result = String.format(" %s %s %s", type(), container, stackId);
        return result;
    }
}
