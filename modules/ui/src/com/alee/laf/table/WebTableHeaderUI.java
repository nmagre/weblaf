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

package com.alee.laf.table;

import com.alee.extended.painter.Painter;
import com.alee.extended.painter.PainterSupport;
import com.alee.laf.table.renderers.WebTableHeaderCellRenderer;
import com.alee.managers.style.StyleManager;
import com.alee.utils.CompareUtils;
import com.alee.utils.SwingUtils;
import com.alee.utils.laf.ShapeProvider;
import com.alee.utils.laf.Styleable;
import com.alee.utils.swing.DataRunnable;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import java.awt.*;

/**
 * User: mgarin Date: 17.08.11 Time: 23:08
 */

public class WebTableHeaderUI extends BasicTableHeaderUI implements Styleable, ShapeProvider
{
    /**
     * Component painter.
     */
    protected TableHeaderPainter painter;

    /**
     * Runtime variables.
     */
    protected String styleId = null;

    /**
     * Returns an instance of the WebTableHeaderUI for the specified component.
     * This tricky method is used by UIManager to create component UIs when needed.
     *
     * @param c component that will use UI instance
     * @return instance of the WebTableHeaderUI
     */
    @SuppressWarnings ("UnusedParameters")
    public static ComponentUI createUI ( final JComponent c )
    {
        return new WebTableHeaderUI ();
    }

    @Override
    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        // Applying skin
        StyleManager.applySkin ( header );

        // Default renderer
        header.setDefaultRenderer ( new WebTableHeaderCellRenderer ()
        {
            @Override
            public Component getTableCellRendererComponent ( final JTable table, final Object value, final boolean isSelected,
                                                             final boolean hasFocus, final int row, final int column )
            {
                final JLabel renderer = ( JLabel ) super.getTableCellRendererComponent ( table, value, isSelected, hasFocus, row, column );
                renderer.setHorizontalAlignment ( JLabel.CENTER );
                return renderer;
            }
        } );
    }

    /**
     * Uninstalls UI from the specified component.
     *
     * @param c component with this UI
     */
    @Override
    public void uninstallUI ( final JComponent c )
    {
        // Uninstalling applied skin
        StyleManager.removeSkin ( header );

        super.uninstallUI ( c );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStyleId ()
    {
        return styleId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStyleId ( final String id )
    {
        if ( !CompareUtils.equals ( this.styleId, id ) )
        {
            this.styleId = id;
            StyleManager.applySkin ( header );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape provideShape ()
    {
        return PainterSupport.getShape ( header, painter );
    }

    /**
     * Returns table header painter.
     *
     * @return table header painter
     */
    public Painter getPainter ()
    {
        return PainterSupport.getAdaptedPainter ( painter );
    }

    /**
     * Sets table header painter.
     * Pass null to remove table header painter.
     *
     * @param painter new table header painter
     */
    public void setPainter ( final Painter painter )
    {
        PainterSupport.setPainter ( header, new DataRunnable<TableHeaderPainter> ()
        {
            @Override
            public void run ( final TableHeaderPainter newPainter )
            {
                WebTableHeaderUI.this.painter = newPainter;
            }
        }, this.painter, painter, TableHeaderPainter.class, AdaptiveTableHeaderPainter.class );
    }

    /**
     * Paints table header.
     *
     * @param g graphics
     * @param c component
     */
    @Override
    public void paint ( final Graphics g, final JComponent c )
    {
        if ( painter != null )
        {
            painter.prepareToPaint ( rendererPane );
            painter.paint ( ( Graphics2D ) g, SwingUtils.size ( c ), c, this );
        }
    }

    @Override
    public Dimension getPreferredSize ( final JComponent c )
    {
        return PainterSupport.getPreferredSize ( c, super.getPreferredSize ( c ), painter );
    }
}