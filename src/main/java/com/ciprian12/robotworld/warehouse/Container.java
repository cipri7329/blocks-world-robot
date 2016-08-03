package com.ciprian12.robotworld.warehouse;

/**
 * Created by cipri on 8/3/16.
 */
public class Container implements IContainer {

    private String name;
    private int size;
    private int content;

    private int verticalPosition;
    private int horizontalPosition;

    public Container(String _name, int _size){
        this.name = _name;
        this.size = _size;

        this.content = 0;
        this.verticalPosition = -1;
        this.horizontalPosition = -1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTotalCapacity() {
        return size;
    }

    @Override
    public int getFilledCapacity() {
        return content;
    }

    @Override
    public int getFreeCapacity() {
        return size - content;
    }

    @Override
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    @Override
    public int getVerticalPosition() {
        return verticalPosition;
    }

    @Override
    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }
}
