import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;

public class ChangableUnboundedGridRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("ChangableUnboundedGrid");
        //world.addGridClass("SparseBoundedGrid");
        //world.addGridClass("SparseBoundedGrid3");
        //world.addGridClass("UnboundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}