package fancyui;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.*;
import static java.beans.BeanInfo.ICON_COLOR_16x16;
import static java.beans.BeanInfo.ICON_COLOR_32x32;
import static java.beans.BeanInfo.ICON_MONO_16x16;
import static java.beans.BeanInfo.ICON_MONO_32x32;
import java.net.URL;

public class FancyLabelBeanInfo extends SimpleBeanInfo {

      // Cached icons
    private Image icon16;
    private Image icon32;

    @Override
    public Image getIcon(int kind) {

        switch (kind) {

            case ICON_COLOR_16x16:
            case ICON_MONO_16x16: {

                if (icon16 == null) {
                    URL url = FancyButton.class.getResource("icons/label_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/label_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/label_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/label_32.png");
                    }
                }
                return icon32;
            }

            default:
                return null;
        }
    }
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            String category = "Custom Properties";

            // ---- Hover Enabled ----
            PropertyDescriptor hoverEnabled =
                new PropertyDescriptor("hoverEnabled", FancyLabel.class);
            hoverEnabled.setDisplayName("Hover Enabled");
            hoverEnabled.setShortDescription("Enables hover effects for the label.");
            hoverEnabled.setValue("category", category);

            // ---- Hover Foreground ----
            PropertyDescriptor hoverForeground =
                new PropertyDescriptor("hoverForeground", FancyLabel.class);
            hoverForeground.setDisplayName("Hover Foreground");
            hoverForeground.setShortDescription("Text color when the label is hovered.");
            hoverForeground.setValue("category", category);

            // ---- Hover Background ----
            PropertyDescriptor hoverBackground =
                new PropertyDescriptor("hoverBackground", FancyLabel.class);
            hoverBackground.setDisplayName("Hover Background");
            hoverBackground.setShortDescription("Background color when hovered.");
            hoverBackground.setValue("category", category);

            // ---- Corner Radius (optional) ----
            PropertyDescriptor cornerRadius =
                new PropertyDescriptor("cornerRadius", FancyLabel.class);
            cornerRadius.setDisplayName("Corner Radius");
            cornerRadius.setShortDescription("Rounded background corners for the label.");
            cornerRadius.setValue("category", category);

            // ---- Background Color ----
            PropertyDescriptor backgroundColor =
                new PropertyDescriptor("backgroundColor", FancyLabel.class);
            backgroundColor.setDisplayName("Background Color");
            backgroundColor.setShortDescription("Custom background color for the label.");
            backgroundColor.setValue("category", category);

            // ---- Border Enabled ----
            PropertyDescriptor borderEnabled =
                new PropertyDescriptor("borderEnabled", FancyLabel.class);
            borderEnabled.setDisplayName("Border Enabled");
            borderEnabled.setShortDescription("Enable or disable border around the label.");
            borderEnabled.setValue("category", category);

            // ---- Border Color ----
            PropertyDescriptor borderColor =
                new PropertyDescriptor("borderColor", FancyLabel.class);
            borderColor.setDisplayName("Border Color");
            borderColor.setShortDescription("Color of the label border.");
            borderColor.setValue("category", category);

            // ---- Border Thickness ----
            PropertyDescriptor borderThickness =
                new PropertyDescriptor("borderThickness", FancyLabel.class);
            borderThickness.setDisplayName("Border Thickness");
            borderThickness.setShortDescription("Thickness of the label border.");
            borderThickness.setValue("category", category);

            return new PropertyDescriptor[] {
                hoverEnabled,
                hoverForeground,
                hoverBackground,
                cornerRadius,
                backgroundColor,
                borderEnabled,
                borderColor,
                borderThickness
            };

        } catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
