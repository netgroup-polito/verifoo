/*******************************************************************************
 * Copyright (c) 2017 Politecnico di Torino and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, Version 2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package it.polito.verigraph.mcnet.components;

import java.util.HashMap;

import com.microsoft.z3.Context;
import com.microsoft.z3.Optimize;
import com.microsoft.z3.Solver;

import it.polito.verifoo.rest.common.AllocationNode;

/**Core component for everything that matters
 *
 *
 */
public abstract class Core{

    public final int MAX_PORT = 65535;

    /**
     * Base class for all objects in the modeling framework
     * @param ctx
     * @param allNodes 
     * @param args
     */
    public Core(Context ctx, HashMap<String, AllocationNode> allNodes, Object[]... args){ // Object[]... -> The nearest way to implement variable length argument lists
        //in Java, in the most generic way.
        init(ctx,allNodes, args);
    }
    /**
     * Override _init for any constructor initialization. Avoids having to explicitly call super.__init__ every Time.class
     * @param ctx
     * @param allNodes 
     * @param args
     */
    abstract protected void init(Context ctx,HashMap<String, AllocationNode> allNodes, Object[]... args);

    /**
     * Add constraints to solver
     * @param solver
     */
    abstract protected void addConstraints(Optimize solver);
}