package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;

/**
 * Created by cipri on 8/5/16.
 */
public class TopContainerCommand implements IContainerCommand {

    private IWareHouse wareHouse;
    private IContainer container1;
    private IContainer container2;

    public TopContainerCommand(IWareHouse wareHouse, IContainer container){
        this.container1 = container;
        this.wareHouse = wareHouse;
        this.container2 = null;
    }

    public TopContainerCommand(IWareHouse wareHouse, IContainer container1, IContainer container2){
        this.container1 = container1;
        this.container2 = container2;
        this.wareHouse = wareHouse;
    }

    @Override
    public String type() {
        return "top";
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public int hashCode(){
        int hc1 = container1.getName().hashCode() + container1.hashCode();
        int hc2 = container2 == null ? 0 : container2.hashCode();
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
        if(!other.container1.equals(this.container1))
            return false;

        if(this.container2 == null && other.container2!=null)
            return false;
        if(this.container2 != null && other.container2==null)
            return false;

        if(this.container2 != null)
            if(!other.container2.equals(this.container2))
                return false;

        return true;
    }

    @Override
    public String toString(){
        String result = String.format(" %s %s %s", type(), container1, container2);
        return result;
    }

}
