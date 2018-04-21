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



import java.util.ArrayList;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import org.omg.CORBA.Object;
/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends BoundedGrid<E>
{
    private ArrayList<SparseGridNode> occupantArray; // the array storing the grid elements
    private int length;
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows,int cols)
    {
        super(rows,cols);
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new ArrayList<SparseGridNode>();
        for(int i=0;i<rows;i++)
            occupantArray.add(null);
        this.length = cols;
    }
    public E get(Location loc)
    {
        //return super.get(loc);
        
        SparseGridNode obj = findByCol(occupantArray.get(loc.getRow()),loc.getCol());
        if(obj == null)
            return null;
        return (E) obj.node;
        
    }

    public E put(Location loc, E obj)
    {
        System.out.println(super.getOccupiedLocations().size());
        System.out.println(loc.getRow());
        System.out.println(loc.getCol());
        
        //super.put(loc,(E) obj);
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        if(occupantArray.get(loc.getRow()) == null)
        {
            occupantArray.set(loc.getRow(),new SparseGridNode(obj,loc.getCol())) ;
            return null;
        }
        // Add the object to the grid.
        SparseGridNode oldOccupant = findByCol(occupantArray.get(loc.getRow()),loc.getCol());
        if(oldOccupant == null)
        {
            if(occupantArray.get(loc.getRow()) == null)
            {
                occupantArray.set(loc.getRow(),new SparseGridNode(obj,loc.getCol()));
            }
            else
            {
                SparseGridNode tempSgn = findLastOne(occupantArray.get(loc.getRow()));
                tempSgn.nextNode = new SparseGridNode(obj,loc.getCol());
            }
            return null;
        }
        else
        {
            SparseGridNode tempSgnBefore = findBefore(occupantArray.get(loc.getRow()),loc.getCol());
            SparseGridNode tempSgn = findBefore(occupantArray.get(loc.getRow()),loc.getCol());
            tempSgnBefore.nextNode = new SparseGridNode(obj,loc.getCol());
            tempSgnBefore.nextNode.nextNode = tempSgn.nextNode;
            return (E) tempSgn.node;
        }
        
    }
    private SparseGridNode findByCol(SparseGridNode sgn,int col)
    {
        SparseGridNode tempSgn = sgn;
        if(sgn == null)
            return null;
        for(int i=0;;i++)
        {
            if(tempSgn.col == col)
                return tempSgn;
            else if(tempSgn.nextNode == null)
                return null;
            else
                tempSgn=tempSgn.nextNode;
        }
        
    }
    private SparseGridNode findLastOne(SparseGridNode sgn)
    {
        SparseGridNode tempSgn = sgn;
        for(int i=0;;i++)
        {
            if(tempSgn.nextNode == null)
                return tempSgn;
            else
                tempSgn = tempSgn.nextNode; 
        }
    }
    private SparseGridNode findBefore(SparseGridNode sgn,int col)
    {
        SparseGridNode tempSgn = sgn;
        if(sgn.col==col&&tempSgn.nextNode==null)
        {
            return null;
        }
        for(int i=0;;i++)
        {
            if(tempSgn.nextNode==null)
                return null;
            if(tempSgn.nextNode.col == col)
                return tempSgn;
            else
                tempSgn = tempSgn.nextNode; 
        }
    }
    public E remove(Location loc)
    {
        //super.remove(loc);
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        SparseGridNode tempSgn = findByCol(occupantArray.get(loc.getRow()),loc.getCol());
        //if(tempSgn == null)
        //    return null;
        SparseGridNode beforeTempSgn = findBefore(occupantArray.get(loc.getRow()),loc.getCol());
        if(beforeTempSgn == null)
        {
            occupantArray.set(loc.getRow(),null);
            return null;
        }
        beforeTempSgn.nextNode = tempSgn.nextNode;
        return (E) tempSgn.node;
    }
}
