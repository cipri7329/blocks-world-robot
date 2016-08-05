package com.ciprian12.robotworld.warehouse;

import com.ciprian12.robotworld.exceptions.InsufficientSpace;
import com.ciprian12.robotworld.exceptions.InvalidContainer;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by cipri on 8/3/16.
 */
public class WareHouse implements IWareHouse{

    private int stackNumber;
    private int stackHeight;

    private ArrayList<Stack<IContainer>> stacks;
    private HashSet<IContainer> containers;

    public WareHouse(int _stackNumber, int _stackHeight){
        this.stackHeight = _stackHeight;
        this.stackNumber = _stackNumber;

        stacks = new ArrayList<>();
        containers = new HashSet<>();
        for(int i=0; i<stackNumber; i++)
            stacks.add(new Stack<>());

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
        try {
            Stack<IContainer> stack = stacks.get(stackId);
            IContainer container = stack.pop();
            containers.remove(container);
            container.setHorizontalPosition(-1);
            container.setVerticalPosition(-1);
            return container;
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
        catch (EmptyStackException e){
            return null;
        }
    }

    @Override
    public boolean putContainer(IContainer container, int stackId) throws InvalidContainer {
        if(container == null)
            return false;
        try {
            if(containers.contains(container)){
                throw new InvalidContainer("Container id already exists!");
            }

            Stack<IContainer> stack = stacks.get(stackId);
            if(stack.size() < stackHeight){
                //free space available
                container.setHorizontalPosition(stackId);
                container.setVerticalPosition(stack.size());
                stack.push(container);
                containers.add(container);
                return true;
            }
            else {
                //stack already full
                return false;
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public int freePlacesOnStack(int stackId) {
        try {
            Stack<IContainer> stack = stacks.get(stackId);
            return stackHeight - stack.size();
        }
        catch (IndexOutOfBoundsException e){
            return -1;
        }
    }

    @Override
    public void addStack() {
        stacks.add(new Stack<>());
    }

    @Override
    public boolean removeStack(int stackId) throws InsufficientSpace {
        try {
            Stack<IContainer> stack = stacks.get(stackId);
            if(stack.empty()){
                stacks.remove(stackId);
                return true;
            }
            else {
                throw new InsufficientSpace("Stack is not empty! Only empty stacks can be removed!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
    }
}
