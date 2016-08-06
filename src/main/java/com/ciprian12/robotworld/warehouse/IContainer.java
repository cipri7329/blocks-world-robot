package com.ciprian12.robotworld.warehouse;

/**
 * Created by cipri on 8/3/16.
 *
 * Define a "smart" container which can provide information about itself and its content
 */
public interface IContainer {

    /**
     * @return the name of the Container
     */
    public String getName();

    /**
     *
     * @return the total capacity of the Container in liters
     */
    public int getTotalCapacity();

    /**
     *
     * @return the filled size of the Container in liters
     */
    public int getFilledCapacity();

    public boolean fillWith(int amount);

    /**
     *
     * @return the free capacity of the Container in liters
     */
    public int getFreeCapacity();


    /**
     *
     * @return the horizontal position of the container
     */
    public int getStackId();

    public void setStackId(int horizontalPosition);

    public int getStackHeightPosition();

    public void setStackHeightPosition(int verticalPosition);
}
