package com.ciprian12.robotworld.commands;

import com.ciprian12.robotworld.exceptions.InvalidContainer;

/**
 * Created by cipri on 8/4/16.
 */
public interface IContainerCommand {

    public String type();

    public boolean execute() throws InvalidContainer;

}
