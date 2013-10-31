package com.readytalk.swt.widgets.notifications;

import com.readytalk.swt.helpers.AncestryHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

public class Bubble extends Widget {
  private static final RGB BACKGROUND_COLOR = new RGB(74, 74, 74);
  private static final int TRIANGLE_WIDTH = 3; //pixels
  private static final int TRIANGLE_HEIGHT = 3; //pixels

  private Listener listener;
  private String tooltipText;
  private Control parent;
  private Shell parentShell, tooltip;

  public Bubble(Control parent, String tooltipText) {
    super(parent, SWT.NONE);

    this.parent = parent;
    this.tooltipText = tooltipText;
    parentShell = AncestryHelper.getShellFromControl(parent);
    tooltip = new Shell(parentShell, SWT.ON_TOP | SWT.NO_TRIM);
    tooltip.setBackground(new Color(parent.getDisplay(), BACKGROUND_COLOR)); // TODO: we need to manage our colors onDispose

    listener = new Listener() {
      public void handleEvent(Event event) {
        switch (event.type) {
          case SWT.Dispose:
            onDispose(event);
            break;
          case SWT.Paint:
            onPaint(event);
            break;
          case SWT.MouseDown:
            onMouseDown(event);
            break;
        }
      }
    };
    addListener(SWT.Dispose, listener);
    tooltip.addListener(SWT.Paint, listener);
    tooltip.addListener(SWT.MouseDown, listener);


    Region tooltipRegion = calculateTooltipRegion();
    tooltip.open(); //TODO: this needs to be triggered somehow
  }

  private Region calculateTooltipRegion() {
    // Find bottom centered point
    // TODO: This won't always work, we might need to draw above the control sometimes
    Rectangle controlBounds = parent.getBounds();
    int centerPointX = (controlBounds.x + controlBounds.width) / 2;
    int centerPointY = (controlBounds.y + controlBounds.height) / 2;

    // Find the center point, then just create the polygon without Point objects for performance
    int[] polygon = {
      centerPointX, centerPointY,
      centerPointX + TRIANGLE_WIDTH, centerPointY + TRIANGLE_HEIGHT,

    };

    return null;
  }

  public void checkSubclass() {
    //no-op
  }

  private void onDispose(Event event) {
    // TODO: dispose all the things here
  }

  private void onPaint(Event event) {
    // TODO: paint things
    GC gc = event.gc;

    setShellSizeForText(gc, tooltipText);

    gc.drawText(tooltipText, 0, 0);
  }

  private void onMouseDown(Event event) {
    // TODO: dismiss the tooltip if they click it
  }

  private void setShellSizeForText(GC gc, String text) {
    Point textExtent = gc.textExtent(text);
    parentShell.setSize(textExtent);
    parentShell.layout();
  }
}