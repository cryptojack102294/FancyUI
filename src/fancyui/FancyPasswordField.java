package fancyui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FancyPasswordField extends JPasswordField implements Serializable {

    private static final long serialVersionUID = 1L;

    // ====== Appearance ======
    private int cornerRadius = 6;
    private boolean borderEnabled = true;
    private Color borderColor = new Color(0xB0BEC5);
    private Color focusBorderColor = new Color(0x42A5F5);
    private float borderThickness = 1.2f;
    private Color backgroundColor = Color.WHITE;

    // ====== Padding ======
    private int paddingTop = 4;
    private int paddingLeft = 8;
    private int paddingBottom = 4;
    private int paddingRight = 8;

    // ====== Left icon (user-configurable) ======
    private Icon leftIcon;
    private int iconTextGap = 4;

    // ====== Show/hide password behavior ======
    private boolean showPassword = false;
    private boolean showPasswordToggleEnabled = true;
    private char defaultEchoChar;

    // Eye as vector size (fallback)
    private int eyeWidth = 18;
    private int eyeHeight = 12;

    // Eye image icons (internal, not changeable from GUI)
    private Icon eyeShowIcon; // when password is visible
    private Icon eyeHideIcon; // when password is hidden

    // ====== Placeholder ======
    private String placeholder = "Password";
    private Color placeholderColor = new Color(150, 150, 150);

    public FancyPasswordField() {
        setOpaque(false);
        defaultEchoChar = getEchoChar();

        // Try to load icons from /fancyui/icons/...
        eyeShowIcon = loadIcon("/fancyui/icons/show_password.png");
        eyeHideIcon = loadIcon("/fancyui/icons/hide_password.png");

        updateInnerBorder();

        addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { repaint(); }
            @Override public void focusLost (FocusEvent e) { repaint(); }
        });

        // Click on eye area to toggle
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!showPasswordToggleEnabled || !isEnabled()) return;

                Rectangle eyeBounds = getEyeBounds();
                if (eyeBounds != null && eyeBounds.contains(e.getPoint())) {
                    setShowPassword(!showPassword);
                }
            }
        });

        // NEW: change cursor when hovering over the eye
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!showPasswordToggleEnabled || !isEnabled()) {
                    setCursor(Cursor.getDefaultCursor());
                    return;
                }

                Rectangle eyeBounds = getEyeBounds();
                if (eyeBounds != null && eyeBounds.contains(e.getPoint())) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        });
    }

    // =========================================================
    // Icon loader
    // =========================================================
    private Icon loadIcon(String absolutePath) {
        try {
            java.net.URL url = getClass().getResource(absolutePath);
            if (url != null) {
                return new ImageIcon(url);
            }
        } catch (Exception ignored) {}
        return null;
    }

    private Icon getActiveEyeIcon() {
        if (!showPasswordToggleEnabled) return null;
        if (showPassword && eyeShowIcon != null)  return eyeShowIcon;   // visible → show icon
        if (!showPassword && eyeHideIcon != null) return eyeHideIcon;  // hidden → hide icon
        return null;
    }

    // =========================================================
    // Bean properties
    // =========================================================

    public boolean isShowPasswordToggleEnabled() {
        return showPasswordToggleEnabled;
    }

    public void setShowPasswordToggleEnabled(boolean enabled) {
        boolean old = this.showPasswordToggleEnabled;
        this.showPasswordToggleEnabled = enabled;
        firePropertyChange("showPasswordToggleEnabled", old, enabled);
        updateInnerBorder();
        repaint();
    }

    public boolean isShowPassword() {
        return showPassword;
    }

    public void setShowPassword(boolean showPassword) {
        boolean old = this.showPassword;
        this.showPassword = showPassword;

        setEchoChar(showPassword ? (char) 0 : defaultEchoChar);

        firePropertyChange("showPassword", old, showPassword);
        repaint();
    }

    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int v) {
        int old = cornerRadius;
        cornerRadius = Math.max(0, v);
        firePropertyChange("cornerRadius", old, v);
        repaint();
    }

    public boolean isBorderEnabled() { return borderEnabled; }
    public void setBorderEnabled(boolean v) {
        boolean old = borderEnabled;
        borderEnabled = v;
        firePropertyChange("borderEnabled", old, v);
        repaint();
    }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color c) {
        Color old = borderColor;
        borderColor = c;
        firePropertyChange("borderColor", old, c);
        repaint();
    }

    public Color getFocusBorderColor() { return focusBorderColor; }
    public void setFocusBorderColor(Color c) {
        Color old = focusBorderColor;
        focusBorderColor = c;
        firePropertyChange("focusBorderColor", old, c);
        repaint();
    }

    public float getBorderThickness() { return borderThickness; }
    public void setBorderThickness(float v) {
        float old = borderThickness;
        borderThickness = Math.max(0f, v);
        firePropertyChange("borderThickness", old, v);
        repaint();
    }

    public Color getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(Color bg) {
        Color old = backgroundColor;
        backgroundColor = bg;
        firePropertyChange("backgroundColor", old, bg);
        repaint();
    }

    public Icon getLeftIcon() { return leftIcon; }
    public void setLeftIcon(Icon icon) {
        Icon old = leftIcon;
        leftIcon = icon;
        firePropertyChange("leftIcon", old, icon);
        updateInnerBorder();
    }

    public int getIconTextGap() { return iconTextGap; }
    public void setIconTextGap(int g) {
        int old = iconTextGap;
        iconTextGap = Math.max(0, g);
        firePropertyChange("iconTextGap", old, g);
        updateInnerBorder();
    }

    public int getPaddingTop() { return paddingTop; }
    public void setPaddingTop(int v) {
        int old = paddingTop;
        paddingTop = Math.max(0, v);
        firePropertyChange("paddingTop", old, v);
        updateInnerBorder();
    }

    public int getPaddingLeft() { return paddingLeft; }
    public void setPaddingLeft(int v) {
        int old = paddingLeft;
        paddingLeft = Math.max(0, v);
        firePropertyChange("paddingLeft", old, v);
        updateInnerBorder();
    }

    public int getPaddingBottom() { return paddingBottom; }
    public void setPaddingBottom(int v) {
        int old = paddingBottom;
        paddingBottom = Math.max(0, v);
        firePropertyChange("paddingBottom", old, v);
        updateInnerBorder();
    }

    public int getPaddingRight() { return paddingRight; }
    public void setPaddingRight(int v) {
        int old = paddingRight;
        paddingRight = Math.max(0, v);
        firePropertyChange("paddingRight", old, v);
        updateInnerBorder();
    }

    public String getPlaceholder() { return placeholder; }
    public void setPlaceholder(String t) {
        String old = placeholder;
        placeholder = t;
        firePropertyChange("placeholder", old, t);
        repaint();
    }

    public Color getPlaceholderColor() { return placeholderColor; }
    public void setPlaceholderColor(Color c) {
        Color old = placeholderColor;
        placeholderColor = c;
        firePropertyChange("placeholderColor", old, c);
        repaint();
    }

    // =========================================================
    // Layout / Insets
    // =========================================================

    private void updateInnerBorder() {
        int left = paddingLeft;
        int right = paddingRight;

        if (leftIcon != null)
            left += leftIcon.getIconWidth() + iconTextGap;

        if (showPasswordToggleEnabled) {
            Icon eye = getActiveEyeIcon();
            if (eye != null) {
                right += eye.getIconWidth() + iconTextGap;
            } else {
                right += eyeWidth + iconTextGap; // fallback space for vector eye
            }
        }

        setBorder(new EmptyBorder(paddingTop, left, paddingBottom, right));
        revalidate();
        repaint();
    }

    private Rectangle getEyeBounds() {
        if (!showPasswordToggleEnabled) return null;
        int w = getWidth();
        int h = getHeight();

        Icon eye = getActiveEyeIcon();
        int ew = eye != null ? eye.getIconWidth() : eyeWidth;
        int eh = eye != null ? eye.getIconHeight() : eyeHeight;

        int x = w - paddingRight - ew;
        int y = (h - eh) / 2;
        return new Rectangle(x, y, ew, eh);
    }

    // =========================================================
    // Painting
    // =========================================================

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        Shape box = new RoundRectangle2D.Double(
                0.5, 0.5, w - 1, h - 1,
                cornerRadius * 2, cornerRadius * 2
        );

        // Clip to rounded rect so background + text are rounded
        g2.setClip(box);

        // Fill background
        g2.setColor(backgroundColor);
        g2.fill(box);

        // Let Swing paint text & caret INSIDE the clip
        boolean wasOpaque = isOpaque();
        setOpaque(false);              // prevent super from filling rect background
        super.paintComponent(g2);
        setOpaque(wasOpaque);

        // Remove clip so icons can overflow if needed
        g2.setClip(null);

        // Draw left icon + eye on top of everything
        paintIcons(g2);

        g2.dispose();

        // Placeholder text on very top
        paintPlaceholder((Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (!borderEnabled || borderThickness <= 0f) return;

        Graphics2D g2 = (Graphics2D) g.create();
        int w = getWidth(), h = getHeight();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        Shape box = new RoundRectangle2D.Double(
                0.5, 0.5, w - 1, h - 1,
                cornerRadius * 2, cornerRadius * 2
        );

        Color draw = isFocusOwner() && focusBorderColor != null
                ? focusBorderColor
                : borderColor;

        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(draw);
        g2.draw(box);

        g2.dispose();
    }

    private void paintIcons(Graphics2D g2) {
        int w = getWidth(), h = getHeight();

        // left icon
        if (leftIcon != null) {
            int y = (h - leftIcon.getIconHeight()) / 2;
            leftIcon.paintIcon(this, g2, paddingLeft, y);
        }

        // right eye (image if available, else vector)
        Rectangle eyeBounds = getEyeBounds();
        if (eyeBounds == null) return;

        Icon eye = getActiveEyeIcon();
        if (eye != null) {
            int x = eyeBounds.x;
            int y = eyeBounds.y;
            eye.paintIcon(this, g2, x, y);
        } else {
            paintEyeVector(g2, eyeBounds.x, eyeBounds.y, eyeBounds.width, eyeBounds.height, showPassword);
        }
    }

    private void paintPlaceholder(Graphics2D g2) {
        if (placeholder == null || placeholder.isEmpty()) return;
        if (getPassword().length > 0) return;
        if (isFocusOwner()) return;

        Insets in = getInsets();
        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = in.left;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        g2.setColor(placeholderColor);
        g2.drawString(placeholder, x, y);
    }

    // =========================================================
    // Vector eye fallback
    // =========================================================
    private void paintEyeVector(Graphics2D g2, int x, int y, int w, int h, boolean open) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = x + w / 2;
        int cy = y + h / 2;
        int rx = w / 2;
        int ry = h / 2;

        // eye outline
        g2.setStroke(new BasicStroke(1.4f));
        g2.setColor(new Color(120, 120, 120));

        g2.drawArc(x, y, w, h, 45, 90);
        g2.drawArc(x, y, w, h, 225, 90);

        if (open) {
            int ir = Math.min(rx, ry) / 2 + 1;
            g2.setColor(new Color(80, 80, 80));
            g2.fillOval(cx - ir, cy - ir, ir * 2, ir * 2);
        } else {
            g2.setColor(new Color(150, 80, 80));
            g2.drawLine(x + 2, y + h - 2, x + w - 2, y + 2);
        }
    }
}
