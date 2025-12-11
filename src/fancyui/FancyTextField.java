package fancyui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

/**
 * FancyTextField — robust text field with left/right icons, placeholder,
 * rounded corners, border, and correct painting order so icons are always visible.
 */
public class FancyTextField extends JTextField implements Serializable {

    private static final long serialVersionUID = 1L;

    // Appearance
    private int cornerRadius = 12;
    private boolean borderEnabled = true;
    private Color borderColor = UIManager.getColor("controlShadow");
    private Color focusBorderColor = UIManager.getColor("TextField.focusedBorderColor");
    private float borderThickness = 1.2f;
    private Color backgroundColor = UIManager.getColor("TextField.background");

    // Padding
    private int paddingTop = 4;
    private int paddingLeft = 8;
    private int paddingBottom = 4;
    private int paddingRight = 8;

    // Icons (left / right)
    private Icon leftIcon;
    private Icon rightIcon;
    private int iconTextGap = 4;

    // Placeholder
    private String placeholder = "";
    private Color placeholderColor = new Color(150, 150, 150);

    public FancyTextField() {
        setOpaque(false);
        updateInnerBorder();

        // repaint on focus so border color updates
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override public void focusGained(java.awt.event.FocusEvent e) { repaint(); }
            @Override public void focusLost(java.awt.event.FocusEvent e) { repaint(); }
        });
    }

    // ========= Bean properties =========

    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int cornerRadius) {
        int old = this.cornerRadius;
        this.cornerRadius = Math.max(0, cornerRadius);
        firePropertyChange("cornerRadius", old, this.cornerRadius);
        repaint();
    }

    public boolean isBorderEnabled() { return borderEnabled; }
    public void setBorderEnabled(boolean borderEnabled) {
        boolean old = this.borderEnabled;
        this.borderEnabled = borderEnabled;
        firePropertyChange("borderEnabled", old, borderEnabled);
        repaint();
    }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color borderColor) {
        Color old = this.borderColor;
        this.borderColor = borderColor;
        firePropertyChange("borderColor", old, borderColor);
        repaint();
    }

    public Color getFocusBorderColor() { return focusBorderColor; }
    public void setFocusBorderColor(Color focusBorderColor) {
        Color old = this.focusBorderColor;
        this.focusBorderColor = focusBorderColor;
        firePropertyChange("focusBorderColor", old, focusBorderColor);
        repaint();
    }

    public float getBorderThickness() { return borderThickness; }
    public void setBorderThickness(float borderThickness) {
        float old = this.borderThickness;
        this.borderThickness = Math.max(0f, borderThickness);
        firePropertyChange("borderThickness", old, this.borderThickness);
        repaint();
    }

    public Color getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(Color backgroundColor) {
        Color old = this.backgroundColor;
        this.backgroundColor = backgroundColor;
        firePropertyChange("backgroundColor", old, backgroundColor);
        repaint();
    }

    public int getPaddingTop() { return paddingTop; }
    public void setPaddingTop(int paddingTop) {
        int old = this.paddingTop;
        this.paddingTop = Math.max(0, paddingTop);
        firePropertyChange("paddingTop", old, this.paddingTop);
        updateInnerBorder();
    }

    public int getPaddingLeft() { return paddingLeft; }
    public void setPaddingLeft(int paddingLeft) {
        int old = this.paddingLeft;
        this.paddingLeft = Math.max(0, paddingLeft);
        firePropertyChange("paddingLeft", old, this.paddingLeft);
        updateInnerBorder();
    }

    public int getPaddingBottom() { return paddingBottom; }
    public void setPaddingBottom(int paddingBottom) {
        int old = this.paddingBottom;
        this.paddingBottom = Math.max(0, paddingBottom);
        firePropertyChange("paddingBottom", old, this.paddingBottom);
        updateInnerBorder();
    }

    public int getPaddingRight() { return paddingRight; }
    public void setPaddingRight(int paddingRight) {
        int old = this.paddingRight;
        this.paddingRight = Math.max(0, paddingRight);
        firePropertyChange("paddingRight", old, this.paddingRight);
        updateInnerBorder();
    }

    public Icon getLeftIcon() { return leftIcon; }
    public void setLeftIcon(Icon leftIcon) {
        Icon old = this.leftIcon;
        this.leftIcon = leftIcon;
        firePropertyChange("leftIcon", old, leftIcon);
        updateInnerBorder();
        repaint();
    }

    public Icon getRightIcon() { return rightIcon; }
    public void setRightIcon(Icon rightIcon) {
        Icon old = this.rightIcon;
        this.rightIcon = rightIcon;
        firePropertyChange("rightIcon", old, rightIcon);
        updateInnerBorder();
        repaint();
    }

    public int getIconTextGap() { return iconTextGap; }
    public void setIconTextGap(int iconTextGap) {
        int old = this.iconTextGap;
        this.iconTextGap = Math.max(0, iconTextGap);
        firePropertyChange("iconTextGap", old, this.iconTextGap);
        updateInnerBorder();
        repaint();
    }

    public String getPlaceholder() { return placeholder; }
    public void setPlaceholder(String placeholder) {
        String old = this.placeholder;
        this.placeholder = placeholder;
        firePropertyChange("placeholder", old, placeholder);
        repaint();
    }

    public Color getPlaceholderColor() { return placeholderColor; }
    public void setPlaceholderColor(Color placeholderColor) {
        Color old = this.placeholderColor;
        this.placeholderColor = placeholderColor;
        firePropertyChange("placeholderColor", old, placeholderColor);
        repaint();
    }

    // ========= Insets for icons/text =========
    private void updateInnerBorder() {
        int left = paddingLeft;
        int right = paddingRight;

        if (leftIcon != null) left += leftIcon.getIconWidth() + iconTextGap;
        if (rightIcon != null) right += rightIcon.getIconWidth() + iconTextGap;

        // set an EmptyBorder so text caret/selection doesn't overlap icons
        setBorder(new EmptyBorder(paddingTop, left, paddingBottom, right));
        revalidate();
        repaint();
    }

    // ========= Painting =========

    @Override
    protected void paintComponent(Graphics g) {
        // Create copy
        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();
        int arc = cornerRadius * 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape round = new RoundRectangle2D.Double(0.5, 0.5, w - 1, h - 1, arc, arc);

        // Fill background first
        g2.setColor(backgroundColor != null ? backgroundColor : getBackground());
        g2.fill(round);

        // Draw border background if desired (we don't stroke here - stroke in paintBorder)
        g2.dispose();

        // Let Swing paint text/caret inside the same component
        // Important: component must be non-opaque so super won't overpaint our rounded background
        boolean wasOpaque = isOpaque();
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(wasOpaque);

        // Draw icons on top so they are always visible
        Graphics2D gTop = (Graphics2D) g.create();
        gTop.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintIcons(gTop);
        gTop.dispose();

        // Draw placeholder on top if necessary
        paintPlaceholder((Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (!borderEnabled || borderThickness <= 0f) return;

        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();
        int arc = cornerRadius * 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0.5, 0.5, w - 1, h - 1, arc, arc);

        Color drawColor = isFocusOwner() && focusBorderColor != null ? focusBorderColor : borderColor;
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(drawColor);
        g2.draw(shape);
        g2.dispose();
    }

    private void paintIcons(Graphics2D g2) {
        int w = getWidth(), h = getHeight();

        if (leftIcon != null) {
            int y = (h - leftIcon.getIconHeight()) / 2;
            leftIcon.paintIcon(this, g2, paddingLeft, y);
        }
        if (rightIcon != null) {
            int y = (h - rightIcon.getIconHeight()) / 2;
            int x = w - paddingRight - rightIcon.getIconWidth();
            rightIcon.paintIcon(this, g2, x, y);
        }
    }

    private void paintPlaceholder(Graphics2D g2) {
        if (placeholder == null || placeholder.isEmpty()) return;
        if (getText() != null && !getText().isEmpty()) return;
        if (isFocusOwner()) return;

        Insets in = getInsets();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = in.left;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(placeholderColor);
        g2.drawString(placeholder, x, y);
    }
}
