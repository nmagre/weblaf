/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.managers.style.skin.web;

import com.alee.laf.list.IListItemPainter;
import com.alee.laf.list.WebListUI;
import com.alee.managers.style.skin.web.data.decoration.IDecoration;

import javax.swing.*;

/**
 * @author Mikle Garin
 */

public class WebListItemPainter<E extends JList, U extends WebListUI, D extends IDecoration<E, D>>
        extends AbstractSectionDecorationPainter<E, U, D> implements IListItemPainter<E, U>
{
    /**
     * Painted list item index.
     */
    protected int index;

    @Override
    public void prepareToPaint ( final int index )
    {
        this.index = index;
    }

    @Override
    protected boolean isFocused ()
    {
        return false;
    }
}