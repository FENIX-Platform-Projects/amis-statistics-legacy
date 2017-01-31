package org.fao.fenix.web.modules.qb.client.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class Counter
{
    int counter;

    HashMap<String, HorizontalPanel> conditionsMap = new HashMap<String, HorizontalPanel>(); 
    ArrayList<HorizontalPanel> conditionsList = new ArrayList<HorizontalPanel>(); 
    
    /**
     * Construct a counter whose value is zero.
     */
    public Counter()
    {
	counter = 1;
    }

    /**
     * Construct a counter with given initial value.
     * @param init is the initial value of the counter
     */

    public Counter(int init)
    {
	counter = init;
    }

    /**
     * Returns the value of the counter.
     * @return the value of the counter
     */
    public int getValue()
    {
	return counter;
    }

    /**
     * Zeros the counter so getValue() == 0.
     */
    public void clear()
    {
	counter = 1;
	}
    
    /**
     * Increase the value of the counter by one.
     */
    public void increment(HorizontalPanel panel)
    {	
	counter++;
	conditionsList.add(panel);
    }

    /**
     * Decrease the value of the counter by one.
     */
    public void decrement(HorizontalPanel panel)
    {
	counter--;
	conditionsList.remove(panel);
    }
    
    /**
     * Return a string representing the value of this counter.
     * @return a string representation of the value
     */
    
    public String toString()
    {
	return ""+counter;
    }

    
}
