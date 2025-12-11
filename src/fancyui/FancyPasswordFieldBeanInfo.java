package fancyui;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.*;
import java.net.URL;

public class FancyPasswordFieldBeanInfo extends SimpleBeanInfo {

     // Cached icons
    private Image icon16;
    private Image icon32;

    @Override
    public Image getIcon(int kind) {

        switch (kind) {

            case ICON_COLOR_16x16:
            case ICON_MONO_16x16: {

                if (icon16 == null) {
                    URL url = FancyButton.class.getResource("icons/password_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/password_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/password_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/password_32.png");
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

            // --- Custom Properties Category ---
            String category = "Custom Properties";

            PropertyDescriptor cornerRadius =
                    new PropertyDescriptor("cornerRadius", FancyPasswordField.class);
            cornerRadius.setValue("category", category);
            cornerRadius.setDisplayName("Corner Radius");

            PropertyDescriptor borderEnabled =
                    new PropertyDescriptor("borderEnabled", FancyPasswordField.class);
            borderEnabled.setValue("category", category);
            borderEnabled.setDisplayName("Border Enabled");

            PropertyDescriptor borderColor =
                    new PropertyDescriptor("borderColor", FancyPasswordField.class);
            borderColor.setValue("category", category);
            borderColor.setDisplayName("Border Color");

            PropertyDescriptor focusBorderColor =
                    new PropertyDescriptor("focusBorderColor", FancyPasswordField.class);
            focusBorderColor.setValue("category", category);
            focusBorderColor.setDisplayName("Focus Border Color");

            PropertyDescriptor borderThickness =
                    new PropertyDescriptor("borderThickness", FancyPasswordField.class);
            borderThickness.setValue("category", category);
            borderThickness.setDisplayName("Border Thickness");

            PropertyDescriptor backgroundColor =
                    new PropertyDescriptor("backgroundColor", FancyPasswordField.class);
            backgroundColor.setValue("category", category);
            backgroundColor.setDisplayName("Background Color");

            PropertyDescriptor leftIcon =
                    new PropertyDescriptor("leftIcon", FancyPasswordField.class);
            leftIcon.setValue("category", category);
            leftIcon.setDisplayName("Left Icon");

            PropertyDescriptor iconTextGap =
                    new PropertyDescriptor("iconTextGap", FancyPasswordField.class);
            iconTextGap.setValue("category", category);
            iconTextGap.setDisplayName("Icon Text Gap");

            PropertyDescriptor paddingTop =
                    new PropertyDescriptor("paddingTop", FancyPasswordField.class);
            paddingTop.setValue("category", category);
            paddingTop.setDisplayName("Padding Top");

            PropertyDescriptor paddingLeft =
                    new PropertyDescriptor("paddingLeft", FancyPasswordField.class);
            paddingLeft.setValue("category", category);
            paddingLeft.setDisplayName("Padding Left");

            PropertyDescriptor paddingBottom =
                    new PropertyDescriptor("paddingBottom", FancyPasswordField.class);
            paddingBottom.setValue("category", category);
            paddingBottom.setDisplayName("Padding Bottom");

            PropertyDescriptor paddingRight =
                    new PropertyDescriptor("paddingRight", FancyPasswordField.class);
            paddingRight.setValue("category", category);
            paddingRight.setDisplayName("Padding Right");

            PropertyDescriptor placeholder =
                    new PropertyDescriptor("placeholder", FancyPasswordField.class);
            placeholder.setValue("category", category);
            placeholder.setDisplayName("Placeholder");

            PropertyDescriptor placeholderColor =
                    new PropertyDescriptor("placeholderColor", FancyPasswordField.class);
            placeholderColor.setValue("category", category);
            placeholderColor.setDisplayName("Placeholder Color");

            PropertyDescriptor showPasswordToggleEnabled =
                    new PropertyDescriptor("showPasswordToggleEnabled", FancyPasswordField.class);
            showPasswordToggleEnabled.setValue("category", category);
            showPasswordToggleEnabled.setDisplayName("Show Password Toggle");

            PropertyDescriptor showPassword =
                    new PropertyDescriptor("showPassword", FancyPasswordField.class);
            showPassword.setValue("category", category);
            showPassword.setDisplayName("Show Password (Preview)");

            return new PropertyDescriptor[] {
                    cornerRadius,
                    borderEnabled,
                    borderColor,
                    focusBorderColor,
                    borderThickness,
                    backgroundColor,
                    leftIcon,
                    iconTextGap,
                    paddingTop,
                    paddingLeft,
                    paddingBottom,
                    paddingRight,
                    placeholder,
                    placeholderColor,
                    showPasswordToggleEnabled,
                    showPassword
            };

        } catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
