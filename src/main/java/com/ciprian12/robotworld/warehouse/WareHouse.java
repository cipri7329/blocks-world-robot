package com.ciprian12.robotworld.warehouse;

/**
 * Created by cipri on 8/3/16.
 */
public class WareHouse implements IWareHouse{

    private int stackNumber;
    private int stackHeight;



    public WareHouse(int _stackNumber, int _stackHeight){
        this.stackHeight = _stackHeight;
        this.stackNumber = _stackNumber;
    }


    @Override
    public int getStackNumber() {
        return stackNumber;
    }

    @Override
    public int getStackHeight() {
        return stackHeight;
    }

    @Override
    public IContainer getContainer(int stackId) {
        return null;
    }

    @Override
    public void putContainer(IContainer container, int stackId) {

    }

    @Override
    public int freePlacesOnStack(int stackId) {
        return 0;
    }
}
