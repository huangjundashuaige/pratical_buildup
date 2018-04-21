

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Flower;
//import sun.security.action.GetLongAction;
import info.gridworld.actor.Rock;
public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            Location newLocation=a.getLocation().getAdjacentLocation(getDirection());
            if(getGrid().isValid(newLocation))
            {
                a.moveTo(newLocation);
            }
            else
            {
                a.removeSelfFromGrid();
            }
        }
    }
    
}
