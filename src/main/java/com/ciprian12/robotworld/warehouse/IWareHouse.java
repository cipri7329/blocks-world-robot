package com.ciprian12.robotworld.warehouse;

import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;

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


    /**
     * remove container from stack
     * @param stackId
     * @return remove container from stack or null if empty stack
     */
    public IContainer getContainer(int stackId);

    /**
     * get a reference to the container with the given id
     * @param containerId
     * @return
     */
    public IContainer peekContainer(String containerId);

    /**
     *
     * @param stackId
     * @return a reference to the top container on stack with id=stackId
     */
    public IContainer peekContainer(int stackId);

    public boolean putContainer(IContainer container, int stackId) throws InvalidContainerException;

    /**
     *
     * @param stackId
     * @return the number of free places on that give stack
     */
    public int freePlacesOnStack(int stackId);

    /**
     *
     * @return return the total number of free places in the warehouse
     */
    public int totalFreePlaces();


    /**
     * add one stack to the warehouse
     */
    public void addStack();

    public boolean removeStack(int stackId) throws InsufficientSpaceException;

    /**
     *
     * @return a matrix representation of the warehouse container allocation
     */
    public String stackString();
}
