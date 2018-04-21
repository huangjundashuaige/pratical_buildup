/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import info.gridworld.grid.Location;
/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class ChangableUnboundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<ArrayList<E>> occupantArray; // the array storing the grid elements
    private int length;
    public ChangableUnboundedGrid()
    {
        this.length = 16;
        occupantArray = new ArrayList<ArrayList<E>>();
        for(int i=0;i<16;i++)
        {
            ArrayList<E> tempList = new ArrayList<E>();
            for(int j=0;j<16;j++)
                tempList.add(null);
            occupantArray.add(tempList);
        }
    }
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && 0 <= loc.getCol();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < this.length; r++)
        {
            for (int c = 0; c < this.length; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        //System.out.println(theLocations.size());
        return theLocations;
    }

    public E get(Location loc)
    {
        for(;loc.getRow()>=this.length||loc.getCol()>=this.length;)
            this.expansion();
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantArray.get(loc.getRow()).get(loc.getCol()); // unavoidable warning
    }
    public void expansion()
    {
        this.length = this.length*2;
        ArrayList<ArrayList<E>> substitution = new ArrayList<ArrayList<E>>();
        for(int i=0;i<this.length;i++)
        {
            ArrayList<E> tempList = new ArrayList<E>();
            for(int j=0;j<this.length;j++)
                tempList.add(null);
            substitution.add(tempList);
        }
        for(int i=0;i<this.length/2;i++)
        {
            for(int j=0;j<this.length/2;j++)
            {
                substitution.get(i).set(j,this.occupantArray.get(i).get(j));
            }
        }
        this.occupantArray=substitution;
    }
    public E put(Location loc, E obj)
    {
        if (obj == null)
            throw new NullPointerException("obj == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        for(;loc.getRow()>=this.length||loc.getCol()>=this.length;)
            this.expansion();
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).set(loc.getCol(),obj);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        for(;loc.getRow()>=this.length||loc.getCol()>=this.length;)
            this.expansion();
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray.get(loc.getRow()).set(loc.getCol(),null);
        return r;
    }
}
