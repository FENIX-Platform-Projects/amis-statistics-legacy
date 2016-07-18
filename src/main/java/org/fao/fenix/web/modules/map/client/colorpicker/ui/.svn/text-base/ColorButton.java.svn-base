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

import org.fao.fenix.web.modules.map.client.colorpicker.util.ButtonStyleCommand;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;


public class ColorButton extends Button implements ClickListener
{
    private int red, green, blue;
    private ColorPanel parentPanel;
    private int positionX;
    private int positionY;


    public ColorButton ()
    {
        super();
    }

    ColorButton (ButtonStyleCommand buttonStyle, ColorPanel parentPanel, int positionX,
            int positionY)
    {
        super();
        this.parentPanel = parentPanel;
        this.positionX = positionX;
        this.positionY = positionY;
        buttonStyle.applyStyle(getElement());
        addClickListener(this);
    }

    public void setColor (int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        
        DOM.setStyleAttribute(this.getElement(), "backgroundColor", "#" + getHexValue());
    }

    public void setColor (String hex)
    {
        long longVal = Long.parseLong(hex, 16);
        
        blue = (int) (longVal % 256);
        int rawGreen = (int) (longVal % (256*256) - blue);
        green = rawGreen / 256;
        red = (int) ((longVal - blue - rawGreen) / (256*256));
        
        GWT.log(hex, null);
        DOM.setStyleAttribute(this.getElement(), "backgroundColor", "#" + hex);
    }

    public int getRed ()
    {
        return red;
    }

    public int getGreen ()
    {
        return green;
    }

    public int getBlue ()
    {
        return blue;
    }

    public String getHexValue ()
    {
        return (
                pad(Integer.toHexString(red))
                + pad(Integer.toHexString(green))
                + pad(Integer.toHexString(blue))
        ).toUpperCase();
    }
    
    private String pad (String in)
    {
        if (in.length() == 0) {
            return "00";
        }
        if (in.length() == 1) {
            return "0" + in;
        }
        return in;
    }

    public int getPositionX ()
    {
        return positionX;
    }

    public int getPositionY ()
    {
        return positionY;
    }

    public void onClick (Widget sender)
    {
        parentPanel.triggerClickEvent(this);
    }
}
