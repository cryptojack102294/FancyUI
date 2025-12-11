package fancyui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.AlphaComposite;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class FancyPanel extends JPanel {

    // ====== STYLE PROPERTIES ======

    // Gradient
    private boolean gradientEnabled = true;
    private Color gradientStart = Color.MAGENTA;
    private Color gradientEnd   = Color.PINK;
    /** Angle in degrees: 0 = left→right, 90 = top→bottom, etc. */
    private int gradientAngle = 90;

    // Solid background (optional)
    private boolean solidBackgroundEnabled = false;
    private Color solidBackgroundColor = new Color(0xFAFAFA);

    // Corner radius
    private int cornerRadius = 8;

    // Border
    private boolean borderEnabled = false;
    private Color borderColor = new Color(0xB0BEC5);
    private float borderThickness = 1f;

    // Opacity (alpha)
    // 1.0 = fully opaque, 0.0 = fully transparent
    private float opacity = 1.0f;

    public FancyPanel() {
        // We do our own background painting
        setOpaque(false);
    }

    // ====== BEAN PROPERTIES (for NetBeans) ======

    // --- Gradient enabled ---
    public boolean isGradientEnabled() {
        return gradientEnabled;
    }

    public void setGradientEnabled(boolean gradientEnabled) {
        boolean old = this.gradientEnabled;
        this.gradientEnabled = gradientEnabled;
        firePropertyChange("gradientEnabled", old, gradientEnabled);
        repaint();
    }

    // --- Gradient start color ---
    public Color getGradientStart() {
        return gradientStart;
    }

    public void setGradientStart(Color gradientStart) {
        Color old = this.gradientStart;
        this.gradientStart = gradientStart;
        firePropertyChange("gradientStart", old, gradientStart);
        repaint();
    }

    // --- Gradient end color ---
    public Color getGradientEnd() {
        return gradientEnd;
    }

    public void setGradientEnd(Color gradientEnd) {
        Color old = this.gradientEnd;
        this.gradientEnd = gradientEnd;
        firePropertyChange("gradientEnd", old, gradientEnd);
        repaint();
    }

    // --- Gradient angle (degrees) ---
    public int getGradientAngle() {
        return gradientAngle;
    }

    public void setGradientAngle(int gradientAngle) {
        int old = this.gradientAngle;
        // Normalize to 0–359
        this.gradientAngle = ((gradientAngle % 360) + 360) % 360;
        firePropertyChange("gradientAngle", old, this.gradientAngle);
        repaint();
    }

    // --- Solid background enabled ---
    public boolean isSolidBackgroundEnabled() {
        return solidBackgroundEnabled;
    }

    public void setSolidBackgroundEnabled(boolean solidBackgroundEnabled) {
        boolean old = this.solidBackgroundEnabled;
        this.solidBackgroundEnabled = solidBackgroundEnabled;
        firePropertyChange("solidBackgroundEnabled", old, solidBackgroundEnabled);
        repaint();
    }

    // --- Solid background color ---
    public Color getSolidBackgroundColor() {
        return solidBackgroundColor;
    }

    public void setSolidBackgroundColor(Color solidBackgroundColor) {
        Color old = this.solidBackgroundColor;
        this.solidBackgroundColor = solidBackgroundColor;
        firePropertyChange("solidBackgroundColor", old, solidBackgroundColor);
        repaint();
    }

    // --- Corner radius ---
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        int old = this.cornerRadius;
        this.cornerRadius = Math.max(0, cornerRadius);
        firePropertyChange("cornerRadius", old, this.cornerRadius);
        revalidate();
        repaint();
    }

    // --- Border enabled ---
    public boolean isBorderEnabled() {
        return borderEnabled;
    }

    public void setBorderEnabled(boolean borderEnabled) {
        boolean old = this.borderEnabled;
        this.borderEnabled = borderEnabled;
        firePropertyChange("borderEnabled", old, borderEnabled);
        repaint();
    }

    // --- Border color ---
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        Color old = this.borderColor;
        this.borderColor = borderColor;
        firePropertyChange("borderColor", old, borderColor);
        repaint();
    }

    // --- Border thickness ---
    public float getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(float borderThickness) {
        float old = this.borderThickness;
        this.borderThickness = Math.max(0f, borderThickness);
        firePropertyChange("borderThickness", old, this.borderThickness);
        repaint();
    }

    // --- Opacity (0.0 – 1.0) ---
    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        float old = this.opacity;
        // Clamp to [0, 1]
        this.opacity = Math.max(0f, Math.min(1f, opacity));
        firePropertyChange("opacity", old, this.opacity);
        repaint();
    }

    // ====== PAINTING ======

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Antialias for smooth corners
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = cornerRadius * 2;
        Shape shape = new RoundRectangle2D.Double(
                0, 0,
                width - 1,
                height - 1,
                arc, arc
        );

        // ===== APPLY OPACITY =====
        if (opacity < 1f) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        }

        // ===== DECIDE BACKGROUND TYPE =====
        Paint paint;

        if (solidBackgroundEnabled && solidBackgroundColor != null) {
            paint = solidBackgroundColor;
        } else if (gradientEnabled && gradientStart != null && gradientEnd != null) {
            paint = createAngleGradientPaint(width, height);
        } else {
            // Fallback: normal background color
            paint = getBackground();
        }

        g2.setPaint(paint);
        g2.fill(shape);

        // ===== BORDER =====
        if (borderEnabled && borderColor != null && borderThickness > 0f) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.draw(shape);
        }

        g2.dispose();

        // Paint children, text, etc.
        super.paintComponent(g);
    }

    /**
     * Create a LinearGradientPaint based on the gradientAngle.
     * 0° = left→right, 90° = top→bottom, 180° = right→left, 270° = bottom→top.
     */
    private Paint createAngleGradientPaint(int width, int height) {
        double theta = Math.toRadians(gradientAngle);
        double cx = width / 2.0;
        double cy = height / 2.0;

        // Use half the diagonal to ensure the gradient covers the whole panel
        double radius = Math.hypot(width, height) / 2.0;

        double dx = Math.cos(theta) * radius;
        double dy = Math.sin(theta) * radius;

        float x1 = (float) (cx - dx);
        float y1 = (float) (cy - dy);
        float x2 = (float) (cx + dx);
        float y2 = (float) (cy + dy);

        return new LinearGradientPaint(
                x1, y1, x2, y2,
                new float[]{0f, 1f},
                new Color[]{gradientStart, gradientEnd}
        );
    }
}
