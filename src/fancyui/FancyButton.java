package fancyui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.UIManager;

public class FancyButton extends JButton {

    // ====== STYLE PROPERTIES ======

    // Radius
    private int cornerRadius = 4;

    // Background
    private Color backgroundColor = Color.WHITE;
      // normal
    private Color hoverBackgroundColor = new Color(0x1E88E5); // on hover

    // Border
    private boolean borderEnabled = true;
    private Color borderColor = UIManager.getColor("controlShadow");
    private float borderThickness = 1f;

    // Internal hover state
    private boolean hovered = false;

    public FancyButton() {
        // We will paint everything ourselves
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);

        setForeground(Color.BLACK); // default text color
        setFont(getFont().deriveFont(Font.BOLD));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Track hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    // ====== BEAN PROPERTIES (for NetBeans) ======

    // Corner radius
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        int old = this.cornerRadius;
        this.cornerRadius = Math.max(0, cornerRadius);
        firePropertyChange("cornerRadius", old, this.cornerRadius);
        repaint();
    }

    // Normal background
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        Color old = this.backgroundColor;
        this.backgroundColor = backgroundColor;
        firePropertyChange("backgroundColor", old, backgroundColor);
        repaint();
    }

    // Hover background
    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        Color old = this.hoverBackgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        firePropertyChange("hoverBackgroundColor", old, hoverBackgroundColor);
        repaint();
    }

    // Border enabled
    public boolean isBorderEnabled() {
        return borderEnabled;
    }

    public void setBorderEnabled(boolean borderEnabled) {
        boolean old = this.borderEnabled;
        this.borderEnabled = borderEnabled;
        firePropertyChange("borderEnabled", old, borderEnabled);
        repaint();
    }

    // Border color
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        Color old = this.borderColor;
        this.borderColor = borderColor;
        firePropertyChange("borderColor", old, borderColor);
        repaint();
    }

    // Border thickness
    public float getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(float borderThickness) {
        float old = this.borderThickness;
        this.borderThickness = Math.max(0f, borderThickness);
        firePropertyChange("borderThickness", old, this.borderThickness);
        repaint();
    }

    // ====== PAINTING ======

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int width  = getWidth();
        int height = getHeight();
        int arc = cornerRadius * 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        Shape shape = new RoundRectangle2D.Double(
                0.5, 0.5,
                width - 1, height - 1,
                arc, arc
        );

        // Background color: normal vs hover
        Color fillColor = backgroundColor;
        if (hovered && hoverBackgroundColor != null) {
            fillColor = hoverBackgroundColor;
        }
        if (fillColor == null) {
            fillColor = getBackground(); // fallback
        }

        g2.setColor(fillColor);
        g2.fill(shape);

        // Border (optional)
        if (borderEnabled && borderThickness > 0f && borderColor != null) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.draw(shape);
        }

        g2.dispose();

        // Let JButton draw the text (and icon) on top
        super.paintComponent(g);
    }

    @Override
    public boolean isOpaque() {
        // We handle opacity ourselves
        return false;
    }
}
