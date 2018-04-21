import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;

public class MapBoundedGridRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("MapBoundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}