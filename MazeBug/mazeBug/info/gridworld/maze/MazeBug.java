package info.gridworld.maze;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;
import info.gridworld.actor.Rock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug 
{
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<Location> locationStack = new Stack<Location>();
	public Stack<ArrayList<Integer>> choiceStack = new Stack<ArrayList<Integer>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		isEnd=toEnd();
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int [] directions={Location.SOUTH,Location.NORTH,Location.EAST,Location.WEST};
		Location locat= getLocation();
		for(int i=0;i<4;i++)
		{
			Location next = locat.getAdjacentLocation(directions[i]);
			if(gr.isValid(next)&&gr.get(next)==null)
			{
				valid.add(next);
			}
		}
		return valid;
	}
	public boolean toEnd()
	{
		Grid<Actor> gr = getGrid();
		int [] directions={Location.SOUTH,Location.NORTH,Location.EAST,Location.WEST};
		Location locat= getLocation();
		for(int i=0;i<4;i++)
			{
				Location next = locat.getAdjacentLocation(directions[i]);
				if(gr.isValid(next))
				{
					if(gr.get(next) instanceof Rock)
					{
						if(((Rock)gr.get(next)).getColor()==Color.RED)
						{
							return true;
						}
					}
				}
			}
		return false;
	}
	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */

	public boolean canMove() 
	{
		return true;
	}
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		ArrayList<Location> locs = getValid(getLocation());
		if(locs.size()!=0)
		{
			ArrayList<Integer> choiceList = choiceStack.peek();
			int flag=0;
			int index=0;
			for(int i=0;i<choiceList.size();i++)
			{
				if(choiceList.get(i)==0&&locs.get(i).equals(last)!=true)
				{
					index=i;
					flag=1;
					choiceList.set(i,1);
					break;
				}
			}
			if(flag==0)
			{
				moveBack();
			}
			else
			{
				next=locs.get(index);
				locationStack.push(getLocation());
				moveTo(next);
				ArrayList<Integer> tempList = new ArrayList<Integer>();
				ArrayList<Location> forSize = getValid(getLocation());
				for(int i=0;i<forSize.size();i++)
				{
					tempList.add(0);
				}
				choiceStack.push(tempList);
			}
		}
		else
		{
			moveBack();
		}
	}
	public void moveBack()
	{
		moveTo(last);
		locationStack.pop();
		choiceStack.pop();
		last = locationStack.peek();
	}
}
