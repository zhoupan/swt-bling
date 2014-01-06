package com.readytalk.examples.swt.widgets.buttons;

import com.readytalk.examples.swt.RunnableExample;
import com.readytalk.examples.swt.SwtBlingExample;
import com.readytalk.swt.util.ColorFactory;
import com.readytalk.swt.widgets.buttons.SquareButton;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SquareButtonExample implements SwtBlingExample {
  @RunnableExample(name="SquareButton")
  public SquareButtonExample() { }

  public void run(Display display, Shell shell) {
    /* Colors */
    final Color offBlack = ColorFactory.getColor(display, 74, 74, 74);
    final Color lightGray = ColorFactory.getColor(display, 204, 204, 204);
    final Color lighterGray = ColorFactory.getColor(display, 232, 232, 232);
    final Color lightestGray = ColorFactory.getColor(display, 239, 239, 239);
    final Color white = ColorFactory.getColor(display, 255, 255, 255);

    /* SquareButton */
    String buttonText = "SquareButton";
    Image buttonImage = display.getSystemImage(SWT.ICON_INFORMATION);
    SquareButton.ImagePosition BUTTON_IMAGE_POSITION = SquareButton.ImagePosition.ABOVE_TEXT;
    int CORNER_RADIUS = 3;
    SquareButton.SquareButtonColorGroup BUTTON_HOVER_COLOR_GROUP =
            new SquareButton.SquareButtonColorGroup(white, white, white, offBlack);
    SquareButton.SquareButtonColorGroup BUTTON_DEFAULT_COLOR_GROUP =
            new SquareButton.SquareButtonColorGroup(lighterGray, lightestGray, lightGray, offBlack);

    shell.setLayout(new FillLayout());
    Composite composite = new Composite(shell, SWT.NONE);
    composite.setLayout(new GridLayout());

    SquareButton.SquareButtonBuilder builder = new SquareButton.SquareButtonBuilder();
    builder .setParent(composite)
            .setText(buttonText)
            .setImage(buttonImage)
            .setImagePosition(BUTTON_IMAGE_POSITION)
            .setCornerRadius(CORNER_RADIUS)
            .setHoverColors(BUTTON_HOVER_COLOR_GROUP)
            .setDefaultColors(BUTTON_DEFAULT_COLOR_GROUP);
    SquareButton button = builder.build();

    button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

    shell.setSize(200, 200);
    shell.open();

    // This is needed remove focus from the Button by default.
    Composite composite2 = new Composite(shell, SWT.NONE);
    composite2.setFocus();
  }
}