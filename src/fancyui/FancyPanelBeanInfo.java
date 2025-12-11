package fancyui;

import java.awt.Image;
import java.awt.Toolkit;
import static java.beans.BeanInfo.ICON_COLOR_16x16;
import static java.beans.BeanInfo.ICON_COLOR_32x32;
import static java.beans.BeanInfo.ICON_MONO_16x16;
import static java.beans.BeanInfo.ICON_MONO_32x32;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.net.URL;

public class FancyPanelBeanInfo extends SimpleBeanInfo {
        // Cached icons
    private Image icon16;
    private Image icon32;

    @Override
    public Image getIcon(int kind) {

        switch (kind) {

            case ICON_COLOR_16x16:
            case ICON_MONO_16x16: {

                if (icon16 == null) {
                    URL url = FancyButton.class.getResource("icons/panel_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/panel_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/panel_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/panel_32.png");
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
            // All custom properties go under: "Custom Properties"

            PropertyDescriptor gradientEnabled =
                    new PropertyDescriptor("gradientEnabled", FancyPanel.class);
            gradientEnabled.setDisplayName("Gradient Enabled");
            gradientEnabled.setValue("category", "Custom Properties");

            PropertyDescriptor gradientStart =
                    new PropertyDescriptor("gradientStart", FancyPanel.class);
            gradientStart.setDisplayName("Gradient Start");
            gradientStart.setValue("category", "Custom Properties");

            PropertyDescriptor gradientEnd =
                    new PropertyDescriptor("gradientEnd", FancyPanel.class);
            gradientEnd.setDisplayName("Gradient End");
            gradientEnd.setValue("category", "Custom Properties");

            PropertyDescriptor gradientAngle =
                    new PropertyDescriptor("gradientAngle", FancyPanel.class);
            gradientAngle.setDisplayName("Gradient Angle");
            gradientAngle.setValue("category", "Custom Properties");

            PropertyDescriptor solidBackgroundEnabled =
                    new PropertyDescriptor("solidBackgroundEnabled", FancyPanel.class);
            solidBackgroundEnabled.setDisplayName("Solid Background Enabled");
            solidBackgroundEnabled.setValue("category", "Custom Properties");

            PropertyDescriptor solidBackgroundColor =
                    new PropertyDescriptor("solidBackgroundColor", FancyPanel.class);
            solidBackgroundColor.setDisplayName("Solid Background Color");
            solidBackgroundColor.setValue("category", "Custom Properties");

            PropertyDescriptor cornerRadius =
                    new PropertyDescriptor("cornerRadius", FancyPanel.class);
            cornerRadius.setDisplayName("Corner Radius");
            cornerRadius.setValue("category", "Custom Properties");

            PropertyDescriptor borderEnabled =
                    new PropertyDescriptor("borderEnabled", FancyPanel.class);
            borderEnabled.setDisplayName("Border Enabled");
            borderEnabled.setValue("category", "Custom Properties");

            PropertyDescriptor borderColor =
                    new PropertyDescriptor("borderColor", FancyPanel.class);
            borderColor.setDisplayName("Border Color");
            borderColor.setValue("category", "Custom Properties");

            PropertyDescriptor borderThickness =
                    new PropertyDescriptor("borderThickness", FancyPanel.class);
            borderThickness.setDisplayName("Border Thickness");
            borderThickness.setValue("category", "Custom Properties");

            // If your property is named getOpacity()/setOpacity()
            PropertyDescriptor opacity =
                    new PropertyDescriptor("opacity", FancyPanel.class);
            opacity.setDisplayName("Opacity");
            opacity.setValue("category", "Custom Properties");

            // If instead you used getOpacityValue()/setOpacityValue(),
            // comment the above and uncomment this:
            // PropertyDescriptor opacity =
            //         new PropertyDescriptor("opacityValue", FancyPanel.class);
            // opacity.setDisplayName("Opacity");
            // opacity.setValue("category", "Custom Properties");

            return new PropertyDescriptor[] {
                    gradientEnabled,
                    gradientStart,
                    gradientEnd,
                    gradientAngle,
                    solidBackgroundEnabled,
                    solidBackgroundColor,
                    cornerRadius,
                    borderEnabled,
                    borderColor,
                    borderThickness,
                    opacity
            };

        } catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
