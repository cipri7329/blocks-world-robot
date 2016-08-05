package com.ciprian12.test.utils;

import com.ciprian12.robotworld.commands.IContainerCommand;
import com.ciprian12.robotworld.utils.CommandsReader;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import com.ciprian12.robotworld.warehouse.WareHouse;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cipri on 8/5/16.
 */
public class CommandsReaderTest {

    @Test
    public void testAdd(){

        List<String> commandsInput = new ArrayList<String>(){{
            add("add a1 10");
            add("add a2 10");
            add("add b1 20 1");
            add("b2 20 1");
        }};

        IWareHouse wareHouse = new WareHouse(10, 10);

        List<IContainerCommand> commands = CommandsReader.readCommands(commandsInput.stream(), wareHouse);

        Assert.assertEquals(commands.size(), 3);
    }

}
