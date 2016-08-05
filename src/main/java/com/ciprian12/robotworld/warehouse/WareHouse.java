package com.ciprian12.robotworld.warehouse;

import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by cipri on 8/3/16.
 */
public class WareHouse implements IWareHouse{

    private static final Logger logger = Logger.getLogger(WareHouse.class);

    private int stackNumber;
    private int stackHeight;

    private ArrayList<Stack<IContainer>> stacks;
    private HashMap<String, IContainer> containers;

    public WareHouse(int _stackNumber, int _stackHeight){
        this.stackHeight = _stackHeight;
        this.stackNumber = _stackNumber;

        stacks = new ArrayList<>();
        containers = new HashMap<>();
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
            containers.remove(container.getName());
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
    public IContainer peekContainer(String containerId) {
        return containers.get(containerId);
    }

    @Override
    public IContainer peekContainer(int stackId) {
        try{
            return stacks.get(stackId).peek();
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
        catch (EmptyStackException e){
            return null;
        }
    }

    @Override
    public boolean putContainer(IContainer container, int stackId) throws InvalidContainerException {
        if(container == null)
            return false;
        try {
            if(containers.containsKey(container.getName())){
                throw new InvalidContainerException("Container id already exists!");
            }

            Stack<IContainer> stack = stacks.get(stackId);
            if(stack.size() < stackHeight){
                //free space available
                container.setHorizontalPosition(stackId);
                container.setVerticalPosition(stack.size());
                stack.push(container);
                containers.put(container.getName(), container);
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
    public boolean removeStack(int stackId) throws InsufficientSpaceException {
        try {
            Stack<IContainer> stack = stacks.get(stackId);
            if(stack.empty()){
                stacks.remove(stackId);
                return true;
            }
            else {
                throw new InsufficientSpaceException("Stack is not empty! Only empty stacks can be removed!");
            }
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public String toString(){
        return "";
    }

    public String stackString(){
        String[][] matrix = new String[stackHeight][stackNumber];
        for(int i=stackHeight-1; i>=0; i--){
            for(int j=0; j<stackNumber; j++){
                matrix[i][j] = "";
            }
        }

        for(IContainer container : containers.values()){
            int x = container.getHorizontalPosition();
            int y = container.getVerticalPosition();
            matrix[y][x] = container.getName();
        }

        StringBuilder result = new StringBuilder();
        for(int i=stackHeight-1; i>=0; i--){
            for(int j=0; j<stackNumber; j++){
                result.append(String.format("%5s|", matrix[i][j]));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
