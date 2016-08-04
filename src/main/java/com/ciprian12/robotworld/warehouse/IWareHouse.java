package com.ciprian12.robotworld.warehouse;

/**
 * Created by cipri on 8/3/16.
 *
 *
 *
 */
public interface IWareHouse {

    public static final String WAREHOUSE_PROPERTIES = "warehouse.properties";

    public static final String STACK_NUMBER = "stack.number";
    public static final String STACK_HEIGHT = "stack.height";

    /**
     * avoided the stackSize term because it's confusing. it hints to stackSize as in height
     * here stackNumber means the number of columns
     * @return how many stacks the warehouse supports
     */
    public int getStackNumber();

    /**
     *
     * @return the maximum height of a container stack
     */
    public int getStackHeight();


    public IContainer getContainer(int stackId);

    public boolean putContainer(IContainer container, int stackId);

    public int freePlacesOnStack(int stackId);

    /**
     * add one stack to the warehouse
     */
    public void addStack();

    public boolean removeStack(int stackId) throws InsufficientSpace;
}
