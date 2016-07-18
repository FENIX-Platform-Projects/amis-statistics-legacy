/*
 * Copyright 2007 Robert Hanson <iamroberthanson AT gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fao.fenix.web.modules.map.client.colorpicker.ui;

import java.util.EventObject;


public class ColorSelectedEvent extends EventObject
{
    private static final long serialVersionUID = 9154054520914299238L;
    private int red;
    private int green;
    private int blue;
    private String hexValue;
    private int position;
    private int positionX;
    private int positionY;
    private int maxPositionX;
    private int maxPositionY;


    public ColorSelectedEvent (Object source)
    {
        super(source);
    }

    void setBlue (int blue)
    {
        this.blue = blue;
    }

    void setGreen (int green)
    {
        this.green = green;
    }

    void setHexValue (String hexValue)
    {
        this.hexValue = hexValue;
    }

    void setPositionX (int positionX)
    {
        this.positionX = positionX;
    }

    void setPositionY (int positionY)
    {
        this.positionY = positionY;
    }

    void setPosition (int position)
    {
        this.position = position;
    }

    void setRed (int red)
    {
        this.red = red;
    }

    void setMaxPositionX (int maxPositionX)
    {
        this.maxPositionX = maxPositionX;
    }

    void setMaxPositionY (int maxPositionY)
    {
        this.maxPositionY = maxPositionY;
    }

    
    public int getBlue ()
    {
        return blue;
    }

    public int getGreen ()
    {
        return green;
    }

    public String getHexValue ()
    {
        return hexValue;
    }

    /**
     * Cols from left, starting at 1
     */
    public int getPositionX ()
    {
        return positionX;
    }

    /**
     * Rows from top, starting at 1
     */
    public int getPositionY ()
    {
        return positionY;
    }

    public int getRed ()
    {
        return red;
    }

    /**
     * Cell number that was clicked, starting with
     * (1) in the top-left corner, then incrementing
     * accross and down.
     */
    public int getPosition ()
    {
        return position;
    }

    public int getMaxPositionX ()
    {
        return maxPositionX;
    }

    public int getMaxPositionY ()
    {
        return maxPositionY;
    }

}
