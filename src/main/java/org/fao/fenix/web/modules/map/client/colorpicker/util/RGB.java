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

package org.fao.fenix.web.modules.map.client.colorpicker.util;

public class RGB
{
    int red;
    int green;
    int blue;



    public RGB ()
    {
    }

    public RGB (int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getBlue ()
    {
        return blue;
    }

    public void setBlue (int blue)
    {
        this.blue = blue;
    }

    public int getGreen ()
    {
        return green;
    }

    public void setGreen (int green)
    {
        this.green = green;
    }

    public int getRed ()
    {
        return red;
    }

    public void setRed (int red)
    {
        this.red = red;
    }
}
