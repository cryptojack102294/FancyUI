package fancyui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class FancyLabel extends JLabel {

    private Color hoverForeground = null;
    private Color hoverBackground = null;
    private boolean hoverEnabled = false;

    private Color normalForeground;
    private Color normalBackground;

    private int cornerRadius = 8;
    private boolean borderEnabled = false;
    private Color borderColor = Color.GRAY;
    private float borderThickness = 1f;

    private boolean hovered = false;

    public FancyLabel() {
        setOpaque(false); // IMPORTANT FIX — prevents default gray fill

        normalForeground = super.getForeground();
        normalBackground = new Color(0,0,0,0); // transparent by default

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                hovered = true;
                updateState();
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                hovered = false;
                updateState();
            }
        });
    }

    private void updateState() {
        if (hoverEnabled && hovered) {
            if (hoverForeground != null) setForeground(hoverForeground);
        } else {
            setForeground(normalForeground);
        }
        repaint();
    }

    // ===== Properties =====

    public boolean isHoverEnabled() { return hoverEnabled; }
    public void setHoverEnabled(boolean v) {
        hoverEnabled = v;
        repaint();
    }

    public Color getHoverForeground() { return hoverForeground; }
    public void setHoverForeground(Color c) {
        hoverForeground = c;
        repaint();
    }

    public Color getHoverBackground() { return hoverBackground; }
    public void setHoverBackground(Color c) {
        hoverBackground = c;
        repaint();
    }

    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int v) {
        cornerRadius = Math.max(0, v);
        repaint();
    }

    public boolean isBorderEnabled() { return borderEnabled; }
    public void setBorderEnabled(boolean v) {
        borderEnabled = v;
        repaint();
    }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color c) {
        borderColor = c;
        repaint();
    }

    public float getBorderThickness() { return borderThickness; }
    public void setBorderThickness(float v) {
        borderThickness = Math.max(0f, v);
        repaint();
    }

    // ===== Painting =====

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int arc = cornerRadius * 2;

        Color bg = null;

        if (hoverEnabled && hovered && hoverBackground != null) {
            bg = hoverBackground;
        } else {
            bg = normalBackground; // transparent background
        }

        if (bg != null && bg.getAlpha() > 0) {
            g2.setColor(bg);
            g2.fill(new RoundRectangle2D.Double(
                0, 0, w - 1, h - 1, arc, arc
            ));
        }

        g2.dispose();

        // Draw text on top
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (!borderEnabled || borderThickness <= 0f) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int arc = cornerRadius * 2;

        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Double(
            0.5, 0.5, w - 1, h - 1, arc, arc
        ));

        g2.dispose();
    }
}
