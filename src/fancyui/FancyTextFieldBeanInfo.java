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

public class FancyTextFieldBeanInfo extends SimpleBeanInfo {
  // Cached icons
    private Image icon16;
    private Image icon32;

    @Override
    public Image getIcon(int kind) {

        switch (kind) {

            case ICON_COLOR_16x16:
            case ICON_MONO_16x16: {

                if (icon16 == null) {
                    URL url = FancyButton.class.getResource("icons/textfield_16.png");
                    if (url != null) {
                        icon16 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/textfield_16.png");
                    }
                }
                return icon16;
            }

            case ICON_COLOR_32x32:
            case ICON_MONO_32x32: {

                if (icon32 == null) {
                    URL url = FancyButton.class.getResource("icons/textfield_32.png");
                    if (url != null) {
                        icon32 = Toolkit.getDefaultToolkit().getImage(url);
                    } else {
                        System.err.println("Missing icon: fancyui/icons/textfield_32.png");
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
            // All custom properties will appear under: "Custom Properties"

            PropertyDescriptor cornerRadius =
                    new PropertyDescriptor("cornerRadius", FancyTextField.class);
            cornerRadius.setDisplayName("Corner Radius");
            cornerRadius.setShortDescription("Rounded corner radius of the text field.");
            cornerRadius.setValue("category", "Custom Properties");

            PropertyDescriptor borderEnabled =
                    new PropertyDescriptor("borderEnabled", FancyTextField.class);
            borderEnabled.setDisplayName("Border Enabled");
            borderEnabled.setShortDescription("Enable or disable the custom border.");
            borderEnabled.setValue("category", "Custom Properties");

            PropertyDescriptor borderColor =
                    new PropertyDescriptor("borderColor", FancyTextField.class);
            borderColor.setDisplayName("Border Color");
            borderColor.setShortDescription("Color of the text field border.");
            borderColor.setValue("category", "Custom Properties");

            PropertyDescriptor focusBorderColor =
                    new PropertyDescriptor("focusBorderColor", FancyTextField.class);
            focusBorderColor.setDisplayName("Focus Border Color");
            focusBorderColor.setShortDescription("Border color when the field has focus.");
            focusBorderColor.setValue("category", "Custom Properties");

            PropertyDescriptor borderThickness =
                    new PropertyDescriptor("borderThickness", FancyTextField.class);
            borderThickness.setDisplayName("Border Thickness");
            borderThickness.setShortDescription("Thickness of the border line.");
            borderThickness.setValue("category", "Custom Properties");

            PropertyDescriptor backgroundColor =
                    new PropertyDescriptor("backgroundColor", FancyTextField.class);
            backgroundColor.setDisplayName("Background Color");
            backgroundColor.setShortDescription("Background fill color inside the rounded area.");
            backgroundColor.setValue("category", "Custom Properties");

            PropertyDescriptor leftIcon =
                    new PropertyDescriptor("leftIcon", FancyTextField.class);
            leftIcon.setDisplayName("Left Icon");
            leftIcon.setShortDescription("Icon displayed on the left inside the field.");
            leftIcon.setValue("category", "Custom Properties");

            PropertyDescriptor rightIcon =
                    new PropertyDescriptor("rightIcon", FancyTextField.class);
            rightIcon.setDisplayName("Right Icon");
            rightIcon.setShortDescription("Icon displayed on the right inside the field.");
            rightIcon.setValue("category", "Custom Properties");

            PropertyDescriptor iconTextGap =
                    new PropertyDescriptor("iconTextGap", FancyTextField.class);
            iconTextGap.setDisplayName("Icon Text Gap");
            iconTextGap.setShortDescription("Gap between icon and text.");
            iconTextGap.setValue("category", "Custom Properties");

            PropertyDescriptor paddingTop =
                    new PropertyDescriptor("paddingTop", FancyTextField.class);
            paddingTop.setDisplayName("Padding Top");
            paddingTop.setShortDescription("Inner padding at the top.");
            paddingTop.setValue("category", "Custom Properties");

            PropertyDescriptor paddingLeft =
                    new PropertyDescriptor("paddingLeft", FancyTextField.class);
            paddingLeft.setDisplayName("Padding Left");
            paddingLeft.setShortDescription("Inner padding on the left side.");
            paddingLeft.setValue("category", "Custom Properties");

            PropertyDescriptor paddingBottom =
                    new PropertyDescriptor("paddingBottom", FancyTextField.class);
            paddingBottom.setDisplayName("Padding Bottom");
            paddingBottom.setShortDescription("Inner padding at the bottom.");
            paddingBottom.setValue("category", "Custom Properties");

            PropertyDescriptor paddingRight =
                    new PropertyDescriptor("paddingRight", FancyTextField.class);
            paddingRight.setDisplayName("Padding Right");
            paddingRight.setShortDescription("Inner padding on the right side.");
            paddingRight.setValue("category", "Custom Properties");

            PropertyDescriptor placeholder =
                    new PropertyDescriptor("placeholder", FancyTextField.class);
            placeholder.setDisplayName("Placeholder");
            placeholder.setShortDescription("Hint text shown when the field is empty.");
            placeholder.setValue("category", "Custom Properties");

            PropertyDescriptor placeholderColor =
                    new PropertyDescriptor("placeholderColor", FancyTextField.class);
            placeholderColor.setDisplayName("Placeholder Color");
            placeholderColor.setShortDescription("Color used for the placeholder text.");
            placeholderColor.setValue("category", "Custom Properties");

            return new PropertyDescriptor[] {
                    cornerRadius,
                    borderEnabled,
                    borderColor,
                    focusBorderColor,
                    borderThickness,
                    backgroundColor,
                    leftIcon,
                    rightIcon,
                    iconTextGap,
                    paddingTop,
                    paddingLeft,
                    paddingBottom,
                    paddingRight,
                    placeholder,
                    placeholderColor
            };

        } catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
