package com.ciprian12.test.warehouse;

import com.ciprian12.robotworld.exceptions.InvalidContainerException;
import com.ciprian12.robotworld.warehouse.Container;
import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;
import org.junit.Test;

/**
 * Created by cipri on 8/5/16.
 */
public class WareHouseTest {

    @Test
    public void matrixDisplay() throws InvalidContainerException {

        IWareHouse wareHouse = new WareHouse(5, 4);

        wareHouse.putContainer(getContainer("a0"), 0);

        wareHouse.putContainer(getContainer("b1"), 1);
        wareHouse.putContainer(getContainer("b2"), 1);

        wareHouse.putContainer(getContainer("c1"), 2);
        wareHouse.putContainer(getContainer("c2"), 2);
        wareHouse.putContainer(getContainer("c3"), 2);

        wareHouse.putContainer(getContainer("e1"), 4);

        System.out.println(wareHouse.stackString());

    }

    private static IContainer getContainer(String name){
        IContainer container = new Container(name);
        return container;
    }

}
