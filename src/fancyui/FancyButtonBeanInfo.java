package fancyui;

import java.awt.Image;
import java.beans.*;
import java.net.URL;
import java.awt.Toolkit;

public class FancyButtonBeanInfo extends SimpleBeanInfo {


    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            String cat = "Custom Properties";

            PropertyDescriptor cornerRadius =
                new PropertyDescriptor("cornerRadius", FancyButton.class);
            cornerRadius.setValue("category", cat);

            PropertyDescriptor borderEnabled =
                new PropertyDescriptor("borderEnabled", FancyButton.class);
            borderEnabled.setValue("category", cat);

            PropertyDescriptor borderColor =
                new PropertyDescriptor("borderColor", FancyButton.class);
            borderColor.setValue("category", cat);

            PropertyDescriptor borderThickness =
                new PropertyDescriptor("borderThickness", FancyButton.class);
            borderThickness.setValue("category", cat);

            PropertyDescriptor backgroundColor =
                new PropertyDescriptor("backgroundColor", FancyButton.class);
            backgroundColor.setValue("category", cat);

            PropertyDescriptor hoverBackground =
                new PropertyDescriptor("hoverBackground", FancyButton.class);
            hoverBackground.setValue("category", cat);

            PropertyDescriptor foreground =
                new PropertyDescriptor("foreground", FancyButton.class);
            foreground.setValue("category", cat);

            PropertyDescriptor hoverForeground =
                new PropertyDescriptor("hoverForeground", FancyButton.class);
            hoverForeground.setValue("category", cat);

            PropertyDescriptor hoverEnabled =
                new PropertyDescriptor("hoverEnabled", FancyButton.class);
            hoverEnabled.setValue("category", cat);

            return new PropertyDescriptor[] {
                cornerRadius,
                borderEnabled,
                borderColor,
                borderThickness,
                backgroundColor,
                hoverBackground,
                foreground,
                hoverForeground,
                hoverEnabled
            };

        } catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
        // Cached icons
    private Image icon16;
    private Image icon32;

    @Override
    public Image getIcon(int kind) {

        switch (kind) {

            case ICON_COLOR_16x16:
            case ICON_MONO_16x16: {

                if (icon16 == null) {
                    URL url = FancyButton.class.getResource("icons/button_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/button_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/button_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/button_32.png");
                    }
                }
                return icon32;
            }

            default:
                return null;
        }
    }

}
