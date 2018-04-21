/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;
/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private int courageLevel;
    private static final double COLOR_CHANGE_RATE = 0.05;
    public BlusterCritter(int courageLevel)
    {
        this.courageLevel = courageLevel;
    }
    public BlusterCritter()
    {
        this.courageLevel = 3;
    }
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */

    /**
     * Turns towards the new location as it moves.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        if(actors.size()<courageLevel)
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 + COLOR_CHANGE_RATE));
            int green = (int) (c.getGreen() * (1 + COLOR_CHANGE_RATE));
            int blue = (int) (c.getBlue() * (1 + COLOR_CHANGE_RATE));
            if(red<255&&green<255&&blue<255)
                setColor(new Color(red, green, blue));
        }
        else
        {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - COLOR_CHANGE_RATE));
            int green = (int) (c.getGreen() * (1 - COLOR_CHANGE_RATE));
            int blue = (int) (c.getBlue() * (1 - COLOR_CHANGE_RATE));
            setColor(new Color(red, green, blue));
        }
    }
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> allNeighbors = new ArrayList<Actor>();
        ArrayList<Actor> realNeighbors = getGrid().getNeighbors(getLocation());
        for(Actor a:realNeighbors)
        {
            allNeighbors.add(a);
            ArrayList<Actor> temp = getGrid().getNeighbors(a.getLocation());
            for(Actor b:temp)
            {
                if(temp.indexOf(b)!=-1)
                {
                    allNeighbors.add(b);
                }
            }
        }
        return  allNeighbors;
    }
}
