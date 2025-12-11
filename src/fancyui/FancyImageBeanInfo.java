package fancyui;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.*;
import static java.beans.BeanInfo.ICON_COLOR_16x16;
import static java.beans.BeanInfo.ICON_COLOR_32x32;
import static java.beans.BeanInfo.ICON_MONO_16x16;
import static java.beans.BeanInfo.ICON_MONO_32x32;
import java.net.URL;

public class FancyImageBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {

        try {

            String cat = "Custom Properties";

            PropertyDescriptor icon =
                new PropertyDescriptor("icon", FancyImage.class);
            icon.setDisplayName("Image Icon");
            icon.setShortDescription("Displays an image in the FancyImage component.");
            icon.setValue("category", cat);

            PropertyDescriptor scaleType =
                new PropertyDescriptor("scaleType", FancyImage.class);
            scaleType.setValue("category", cat);

            PropertyDescriptor cornerRadius =
                new PropertyDescriptor("cornerRadius", FancyImage.class);
            cornerRadius.setValue("category", cat);

            PropertyDescriptor backgroundColor =
                new PropertyDescriptor("backgroundColor", FancyImage.class);
            backgroundColor.setValue("category", cat);

            PropertyDescriptor borderEnabled =
                new PropertyDescriptor("borderEnabled", FancyImage.class);
            borderEnabled.setValue("category", cat);

            PropertyDescriptor borderColor =
                new PropertyDescriptor("borderColor", FancyImage.class);
            borderColor.setValue("category", cat);

            PropertyDescriptor borderThickness =
                new PropertyDescriptor("borderThickness", FancyImage.class);
            borderThickness.setValue("category", cat);

            PropertyDescriptor shadowEnabled =
                new PropertyDescriptor("shadowEnabled", FancyImage.class);
            shadowEnabled.setValue("category", cat);

            PropertyDescriptor shadowColor =
                new PropertyDescriptor("shadowColor", FancyImage.class);
            shadowColor.setValue("category", cat);

            PropertyDescriptor shadowOffsetX =
                new PropertyDescriptor("shadowOffsetX", FancyImage.class);
            shadowOffsetX.setValue("category", cat);

            PropertyDescriptor shadowOffsetY =
                new PropertyDescriptor("shadowOffsetY", FancyImage.class);
            shadowOffsetY.setValue("category", cat);

            return new PropertyDescriptor[] {
                icon,
                scaleType,
                cornerRadius,
                backgroundColor,
                borderEnabled,
                borderColor,
                borderThickness,
                shadowEnabled,
                shadowColor,
                shadowOffsetX,
                shadowOffsetY
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
                    URL url = FancyButton.class.getResource("icons/image_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/image_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/image_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/image_32.png");
                    }
                }
                return icon32;
            }

            default:
                return null;
        }
    }
}
