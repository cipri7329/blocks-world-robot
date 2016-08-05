package com.ciprian12.robotworld.utils;

import com.ciprian12.robotworld.commands.*;
import com.ciprian12.robotworld.warehouse.Container;
import com.ciprian12.robotworld.warehouse.IContainer;
import com.ciprian12.robotworld.warehouse.IWareHouse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cipri on 8/4/16.
 */
public class CommandsReader {

    private static final Logger logger = Logger.getLogger(CommandsReader.class);

    public static List<IContainerCommand> readCommands(String inputFile, final IWareHouse wareHouse){
        List<IContainerCommand> commands = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {

            readCommands(stream, wareHouse);

        } catch (IOException e) {
            logger.error(e);
        }
        return commands;
    }

    public static List<IContainerCommand> readCommands(Stream<String> stream, final IWareHouse wareHouse){

        List<IContainerCommand> commands = new ArrayList<>();
        if(stream == null)
            return commands;

        commands = stream
                .filter(line -> line.length() > 0 && !line.startsWith("*") && !line.startsWith("#"))
                .map(x -> commandMapper(x, wareHouse))
                .filter(x -> x!=null)
                .collect(Collectors.<IContainerCommand> toList());

        logger.debug(String.format("parsed cmd: %s",commands));
        return commands;
    }

    private static IContainerCommand commandMapper(String commandString, IWareHouse wareHouse){
        String[] elements = commandString.split("\\s+");
        logger.debug(String.format("input cmd: %s", Arrays.asList(elements)));

        IContainerCommand cmd = null;
        String container1Name;
        String container2Name;
        try {
            switch (elements[0]) {

                case "add":
                    if(elements.length < 3)
                        throw new NumberFormatException();
                    int size = Integer.valueOf(elements[2]);
                    IContainer container = new Container(elements[1], size);
                    if(elements.length == 4) {
                        int stackId = Integer.valueOf(elements[3]);
                        cmd = new AddContainerCommand(wareHouse, container, stackId);
                    }
                    else
                        cmd = new AddContainerCommand(wareHouse, container);
                    break;

                case "move":
                    if(elements.length != 3)
                        throw new NumberFormatException();
                    container1Name = elements[1];
                    container2Name = elements[2];
                    cmd = new MoveContainerCommand(wareHouse, container1Name, container2Name);
                    break;

                case "top":
                    if(elements.length < 2)
                        throw new NumberFormatException();
                    container1Name = elements[1];
                    if(elements.length == 2){
                        cmd = new TopContainerCommand(wareHouse, container1Name);
                    }
                    else{
                        container2Name = elements[2];
                        cmd = new TopContainerCommand(wareHouse, container1Name, container2Name);
                    }
                    break;
                case "redo":
                    break;
                case "undo":
                    break;

                case "fill":
                    if(elements.length != 2)
                        throw new NumberFormatException();
                    container1Name = elements[1];
                    int amount = Integer.valueOf(elements[2]);
                    if(amount < 0){
                        throw new NumberFormatException("negative amount not allowed");
                    }
                    cmd = new FillContainerCommand(wareHouse, container1Name, amount);
                    break;
                default:
                    logger.warn(String.format("command %s not recognized!", Arrays.asList(elements)));
            }
        }
        catch (NumberFormatException e){
            logger.warn(String.format("invalid command: %s", Arrays.asList(elements)));
        }

        return  cmd;
    }



}
