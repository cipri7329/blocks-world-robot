package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

/**
 * Created by cipri on 8/5/16.
 * Moves one / two containers on the top of any stack
 */
public class TopContainerCommand implements IContainerCommand {

    private static final Logger logger = Logger.getLogger(TopContainerCommand.class);

    private IWareHouse wareHouse;
    private String container1Name;
    private String container2Name;

    private LinkedList<IContainerCommand> executedIntermmediateSteps;

    public TopContainerCommand(IWareHouse wareHouse, String container){
        this.container1Name = container;
        this.wareHouse = wareHouse;
        this.container2Name = null;
        this.executedIntermmediateSteps = new LinkedList<>();
    }

    public TopContainerCommand(IWareHouse wareHouse, String container1, String container2){
        this.container1Name = container1;
        this.container2Name = container2;
        this.wareHouse = wareHouse;
        this.executedIntermmediateSteps = new LinkedList<>();
    }

    @Override
    public String type() {
        return "top";
    }

    @Override
    public boolean execute() throws InvalidContainerException, InsufficientSpaceException {
        logger.debug("execute: " + toString());

        boolean status;
        if(container1Name == null)
            return false;
        if(container2Name == null){
            status = topOneContainer();
        }
        else{
            status = topTwoContainers();
        }
        logger.debug(toString() + "\n" + wareHouse.stackString());
        return status;
    }

    @Override
    public boolean revert() {
        //TODO: implement revert
        throw new NotImplementedException();
    }


    private boolean topOneContainer() throws InvalidContainerException, InsufficientSpaceException {
        IContainer container = wareHouse.peekContainer(container1Name);
        IContainer topContainer = wareHouse.peekContainer(container.getStackId());
        if(container.equals(topContainer))
            return true;

        int occupiedStack = container.getStackId();
        for(int stackId = 0; stackId < wareHouse.getStackNumber(); stackId++){
            if(stackId == occupiedStack)
                continue;

            if(wareHouse.freePlacesOnStack(stackId) > 0){
                IContainerCommand moveContainer = new MoveContainerCommand(wareHouse, occupiedStack, stackId);
                boolean status = moveContainer.execute();
                executedIntermmediateSteps.add(moveContainer);
                if(!status){
                    //TODO: implement revert
                }
                //reuse the stack
                stackId --;
            }

            topContainer = wareHouse.peekContainer(container.getStackId());
            if(container.equals(topContainer))
                return true;
        }

        topContainer = wareHouse.peekContainer(container.getStackId());
        if(!container.equals(topContainer)) {
            //TODO: implement revert
            while (this.executedIntermmediateSteps.size() > 0){
                IContainerCommand cmd = this.executedIntermmediateSteps.removeLast();
                cmd.revert();
            }

            throw new InsufficientSpaceException("not enough free space on stacks!");
        }

        return true;
    }

    private boolean topTwoContainers() throws InvalidContainerException, InsufficientSpaceException {

        boolean onTop1 = false;
        IContainer container1 = wareHouse.peekContainer(container1Name);
        int occupiedStack1 = container1.getStackId();
        boolean onTop2 = false;
        IContainer container2 = wareHouse.peekContainer(container2Name);
        int occupiedStack2 = container2.getStackId();

        if(occupiedStack1 == occupiedStack2){
            //containers are on the same stack
            String topName;
            if(container1.getStackHeightPosition() > container2.getStackHeightPosition()){
                topName = container1Name;
            }
            else{
                topName = container2Name;
            }
            IContainerCommand topCmd = new TopContainerCommand(wareHouse, topName);
            boolean status = topCmd.execute();
            executedIntermmediateSteps.add(topCmd);
            if(!status){
                //TODO: implement revert
            }
            if(topName.equals(container1Name)){
                occupiedStack1 = container1.getStackId();
            }
            else{
                occupiedStack2 = container2.getStackId();
            }
        }

        IContainer topContainer1 = wareHouse.peekContainer(container1.getStackId());
        if(container1.equals(topContainer1))
            onTop1 = true;

        IContainer topContainer2 = wareHouse.peekContainer(container2.getStackId());
        if(container2.equals(topContainer2))
            onTop2 = true;

        if(onTop1  && onTop2){
            return true;
        }

        for(int stackId = 0; stackId < wareHouse.getStackNumber(); stackId++){
            if(stackId == occupiedStack1 || stackId == occupiedStack2)
                continue;

            if(wareHouse.freePlacesOnStack(stackId) > 0){
                if(!onTop1) {
                    IContainerCommand moveContainer = new MoveContainerCommand(wareHouse, occupiedStack1, stackId);
                    moveContainer.execute();
                    occupiedStack2 = container2.getStackId();
                    boolean status = executedIntermmediateSteps.add(moveContainer);

                    if(!status){
                        //TODO: implement revert
                    }
                    //reuse the stack
                    stackId--;
                }
                else{
                    if(!onTop2) {
                        IContainerCommand moveContainer = new MoveContainerCommand(wareHouse, occupiedStack2, stackId);
                        moveContainer.execute();
                        occupiedStack1 = container1.getStackId();
                        boolean status = executedIntermmediateSteps.add(moveContainer);

                        if(!status){
                            //TODO: implement revert
                        }
                        //reuse the stack
                        stackId--;
                    }
                }
            }

            topContainer1 = wareHouse.peekContainer(container1.getStackId());
            if(container1.equals(topContainer1))
                onTop1 = true;

            topContainer2 = wareHouse.peekContainer(container2.getStackId());
            if(container2.equals(topContainer2))
                onTop2 = true;

            if(onTop1 && onTop2)
                break;
        }


        if(!(onTop1 && onTop2)) {
            //TODO: implement revert
            while (this.executedIntermmediateSteps.size() > 0){
                IContainerCommand cmd = this.executedIntermmediateSteps.removeLast();
                cmd.revert();
            }

            throw new InsufficientSpaceException("not enough free space on stacks!");
        }

        return true;
    }



    @Override
    public int hashCode(){
        int hc1 = container1Name.hashCode();
        int hc2 = container2Name == null ? 0 : container2Name.hashCode();
        return ("top" + hc1 + hc2).hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof TopContainerCommand)){
            return false;
        }
        TopContainerCommand other = (TopContainerCommand) o;
        if(!other.container1Name.equals(this.container1Name))
            return false;

        if(this.container2Name == null && other.container2Name !=null)
            return false;
        if(this.container2Name != null && other.container2Name ==null)
            return false;

        if(this.container2Name != null)
            if(!other.container2Name.equals(this.container2Name))
                return false;

        return true;
    }

    @Override
    public String toString(){
        String result = String.format(" %s %s %s", type(), container1Name, container2Name);
        return result;
    }

}
