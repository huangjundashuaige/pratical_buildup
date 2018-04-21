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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;
import java.util.ArrayList;
//import java.util.ArrayList;
//import 
/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private boolean stopFlag;
    private Integer process;
    private  ArrayList<Integer> rule=new ArrayList<Integer>()
    {
		{
            add(3);
            add(5);
            add(0);
        }
    };
    
    private void howMantTurn(Integer times)
    {
        for(int i=0;i < times;i++)
        {
            turn();
        }
    }
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        stopFlag = false;
        process = 0;
        howMantTurn(2);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        /*
        if (steps < sideLength && canMove())
        {
            move();
            steps++;

        }
        else
        {
            turn();
            turn();
            steps = 0;
        }*/
        if(stopFlag == false)
        {
            if(canMove()==false)
            {
                stopFlag = true;
            }
            else
            {
                if(steps <= sideLength)
                {
                    steps++;
                    move();
                }
                else
                {
                    if(process < 2)
                    {
                        steps=0;
                        howMantTurn(rule.get(process++));
                    }
                    else
                    {
                        stopFlag = true;
                    }
                }
            }
        }
    }
}
