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
public class MoveContainerCommand implements IContainerCommand {

    private static final Logger logger = Logger.getLogger(MoveContainerCommand.class);

    private LinkedList<IContainerCommand> executedIntermmediateSteps;

    private IWareHouse wareHouse;
    private int fromStackId;
    private int toStackId;

    private String container1Name;
    private String container2Name;

    private IContainer container1;
    private IContainer container2;

    private boolean isByStackId;

    public MoveContainerCommand(IWareHouse wareHouse, int fromStackId, int toStackId){
        this.wareHouse = wareHouse;
        this.fromStackId = fromStackId;
        this.toStackId = toStackId;

        this.isByStackId = true;
        this.executedIntermmediateSteps = new LinkedList<>();

        try {
            this.container1Name = wareHouse.peekContainer(fromStackId).getName();
            this.container2Name = wareHouse.peekContainer(toStackId).getName();
        }
        catch (Exception e){

        }
    }

    public MoveContainerCommand(IWareHouse wareHouse, String container1, String container2){
        this.wareHouse = wareHouse;
        this.container1Name = container1;
        this.container2Name = container2;

        this.isByStackId = false;
        this.executedIntermmediateSteps = new LinkedList<>();

        this.fromStackId=-1;
        this.toStackId=-1;
    }

    @Override
    public String type() {
        return "move";
    }

    @Override
    public boolean execute() throws InvalidContainerException, InsufficientSpaceException {
        logger.debug("execute: " + toString());
        boolean status;
        if(isByStackId){
            status = moveByStackId();
        }
        else{
            status = moveByContainerName();
        }
        logger.debug(toString() + "\n" + wareHouse.stackString());
        return status;
    }

    private boolean moveByContainerName() throws InvalidContainerException, InsufficientSpaceException {
        this.container1 = wareHouse.peekContainer(this.container1Name);
        this.container2 = wareHouse.peekContainer(this.container2Name);


        IContainerCommand topCmd = new TopContainerCommand(wareHouse, this.container1Name, this.container2Name);
        boolean status = topCmd.execute();
        if(status){
            executedIntermmediateSteps.add(topCmd);
        }

        return status;
    }

    private boolean moveByStackId() throws InvalidContainerException, InsufficientSpaceException {
        IContainer container = wareHouse.getContainer(fromStackId);
        if(container == null)
            return false;

        boolean status = wareHouse.putContainer(container, toStackId);
        if(!status){
            //put the container back
            status = wareHouse.putContainer(container, fromStackId);
        }
        if(!status){
            throw new InsufficientSpaceException("move back container - revert failed!");
        }

        return status;
    }

    @Override
    public boolean revert() throws InvalidContainerException, InsufficientSpaceException {
        logger.debug("revert: " + toString());
        if(isByStackId){
            IContainerCommand cmd = new MoveContainerCommand(wareHouse, toStackId, fromStackId);
            boolean status = cmd.execute();
            logger.debug("reverted: " + toString() + "\n" + wareHouse.stackString());
            return status;
        }
        else{
            boolean revertStatus = true;
            while(executedIntermmediateSteps.size() > 0){
                IContainerCommand cmd = executedIntermmediateSteps.removeLast();
                revertStatus = cmd.revert();
                if(!revertStatus){
                    throw new InsufficientSpaceException("revert failed");
                }
            }
            logger.debug("reverted: " + toString() + "\n" + wareHouse.stackString());
            return revertStatus;
        }

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

    @Override
    public String toString(){
        String result = "";
        result += String.format(" [%s %s %s (%s:%s)]", type(), container1Name, container2Name, fromStackId, toStackId);
        return result;
    }
}
