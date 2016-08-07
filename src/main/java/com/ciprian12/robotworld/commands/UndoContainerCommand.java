package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.exceptions.InsufficientSpaceException;
import com.ciprian12.robotworld.exceptions.InvalidContainerException;

/**
 * Created by cipri on 8/7/16.
 */
public class UndoContainerCommand implements IContainerCommand {
    @Override
    public String type() {
        return "undo";
    }

    @Override
    public boolean execute() throws InvalidContainerException, InsufficientSpaceException {
        return false;
    }

    @Override
    public boolean revert() throws InvalidContainerException, InsufficientSpaceException {
        return false;
    }
}
