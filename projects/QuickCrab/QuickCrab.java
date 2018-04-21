

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Flower;
//import sun.security.action.GetLongAction;

import projects.QuickCrab.CrabCritter;

public class QuickCrab extends CrabCritter
{
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

        return locs;
    }
    public ArrayList<Location> getMoveLocationsDest()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirsLeft =
            { Location.LEFT};
        int[] dirsRight = 
            { Location.RIGHT };
        /*for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);*/
        ArrayList<Location> leftNeighbor = getLocationsInDirections(dirsLeft);
        if(leftNeighbor.size() != 0 && getGrid().isValid(leftNeighbor.get(0).getAdjacentLocation(dirsLeft[0]))==true)
        {
            Location leftNeighborLeft = leftNeighbor.get(0).getAdjacentLocation(dirsLeft[0]);
            if(getGrid().get(leftNeighborLeft) == null || getGrid().get(leftNeighborLeft) instanceof Flower)
            {
                if(leftNeighborLeft.getRow()<0||leftNeighborLeft.getCol()<0)
                {

                }
                else
                    locs.add(leftNeighborLeft);
            }
        }
        ArrayList<Location>  rightNeighbor = getLocationsInDirections(dirsRight);
        if(rightNeighbor.size() != 0 && getGrid().isValid(rightNeighbor.get(0).getAdjacentLocation(dirsRight[0]))==true)
        {
            Location rightNeighborRight = rightNeighbor.get(0).getAdjacentLocation(dirsRight[0]);
            if(getGrid().get(rightNeighborRight) == null || getGrid().get(rightNeighborRight) instanceof Flower)
            {
                if(rightNeighborRight.getRow()<0||rightNeighborRight.getCol()<0)
                    {
    
                    }
                else
                locs.add(rightNeighborRight);
            }
        }
        return locs;
    }
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        ArrayList<Location> li = getMoveLocationsDest();
        if (li.size() == 0)
        {
            if(n != 0)
            {
                int r=(int) (Math.random() * n);
                return locs.get(r);
            }
            else
            {
                return getLocation();
            }
        }
        else
        {
            int r = (int) (Math.random()*li.size());
            return li.get(r);
        }
    }
    
}
