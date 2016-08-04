package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;

/**
 * Created by cipri on 8/4/16.
 */
public class MoveContainerCommand implements IContainerCommand {

    private IWareHouse wareHouse;
    private int fromStackId;
    private int toStackId;

    public MoveContainerCommand(IWareHouse wareHouse, int fromStackId, int toStackId){
        this.wareHouse = wareHouse;
        this.fromStackId = fromStackId;
        this.toStackId = toStackId;
    }

    @Override
    public boolean execute() {
        IContainer container = wareHouse.getContainer(fromStackId);
        boolean status = wareHouse.putContainer(container, toStackId);
        if(!status){
            //put the container back
            wareHouse.putContainer(container, fromStackId);
        }
        return status;
    }


    @Override
    public int hashCode(){
        return (fromStackId +"#" + toStackId).hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof MoveContainerCommand)){
            return false;
        }
        MoveContainerCommand other = (MoveContainerCommand) o;
        if(other.fromStackId != this.fromStackId)
            return false;
        if(other.toStackId != this.toStackId)
            return false;
        return true;
    }
}
