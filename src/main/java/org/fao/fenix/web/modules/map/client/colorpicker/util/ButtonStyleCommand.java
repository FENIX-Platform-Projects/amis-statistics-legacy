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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;


public class ButtonStyleCommand implements StyleCommand
{
    private String width;
    private String height;
    private String padding;
    private String margin;
    private String border;



    public void applyStyle (Element element)
    {
        if (width != null)
            DOM.setStyleAttribute(element, "width", width);
        if (height != null)
            DOM.setStyleAttribute(element, "height", height);
        if (padding != null)
            DOM.setStyleAttribute(element, "padding", padding);
        if (margin != null)
            DOM.setStyleAttribute(element, "margin", margin);
        if (border != null)
            DOM.setStyleAttribute(element, "border", border);
    }

    public String getBorder ()
    {
        return border;
    }

    public void setBorder (String border)
    {
        this.border = border;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getMargin ()
    {
        return margin;
    }

    public void setMargin (String margin)
    {
        this.margin = margin;
    }

    public String getPadding ()
    {
        return padding;
    }

    public void setPadding (String padding)
    {
        this.padding = padding;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }
}
