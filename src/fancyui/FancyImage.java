package fancyui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.net.URL;

public class FancyImage extends JLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum ScaleType { FIT, FILL, STRETCH, CENTER }

    private ScaleType scaleType = ScaleType.FILL;

    // Placeholder icon if no icon is set
    private Icon placeholder;

    // Appearance
    private int cornerRadius = 16;
    private Color backgroundColor = new Color(0, 0, 0, 0);

    private boolean borderEnabled = false;
    private Color borderColor = Color.GRAY;
    private float borderThickness = 1f;

    // Shadow
    private boolean shadowEnabled = false;
    private Color shadowColor = new Color(0, 0, 0, 80);
    private int shadowOffsetX = 3;
    private int shadowOffsetY = 3;

    public FancyImage() {
        setOpaque(false);
        loadPlaceholder();
    }

    private void loadPlaceholder() {
        try {
            URL url = FancyImage.class.getResource("/fancyui/images/default_image.jpg");
            if (url != null) {
                placeholder = new ImageIcon(url);
            } else {
                System.err.println("FancyImage: placeholder not found at /fancyui/images/default_image.jpg");
            }
        } catch (Exception ex) {
            System.err.println("FancyImage: error loading placeholder: " + ex.getMessage());
        }
    }

    // ================= Bean properties =================

    public ScaleType getScaleType() { return scaleType; }
    public void setScaleType(ScaleType type) {
        ScaleType old = this.scaleType;
        this.scaleType = (type != null ? type : ScaleType.FIT);
        firePropertyChange("scaleType", old, this.scaleType);
        repaint();
    }

    public Icon getPlaceholder() { return placeholder; }
    public void setPlaceholder(Icon ph) {
        Icon old = this.placeholder;
        this.placeholder = ph;
        firePropertyChange("placeholder", old, ph);
        repaint();
    }

    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int v) {
        int old = this.cornerRadius;
        this.cornerRadius = Math.max(0, v);
        firePropertyChange("cornerRadius", old, this.cornerRadius);
        repaint();
    }

    public Color getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(Color c) {
        Color old = this.backgroundColor;
        this.backgroundColor = c;
        firePropertyChange("backgroundColor", old, c);
        repaint();
    }

    public boolean isBorderEnabled() { return borderEnabled; }
    public void setBorderEnabled(boolean v) {
        boolean old = this.borderEnabled;
        this.borderEnabled = v;
        firePropertyChange("borderEnabled", old, v);
        repaint();
    }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color c) {
        Color old = this.borderColor;
        this.borderColor = c;
        firePropertyChange("borderColor", old, c);
        repaint();
    }

    public float getBorderThickness() { return borderThickness; }
    public void setBorderThickness(float v) {
        float old = this.borderThickness;
        this.borderThickness = Math.max(0f, v);
        firePropertyChange("borderThickness", old, this.borderThickness);
        repaint();
    }

    public boolean isShadowEnabled() { return shadowEnabled; }
    public void setShadowEnabled(boolean v) {
        boolean old = this.shadowEnabled;
        this.shadowEnabled = v;
        firePropertyChange("shadowEnabled", old, v);
        repaint();
    }

    public Color getShadowColor() { return shadowColor; }
    public void setShadowColor(Color c) {
        Color old = this.shadowColor;
        this.shadowColor = c;
        firePropertyChange("shadowColor", old, c);
        repaint();
    }

    public int getShadowOffsetX() { return shadowOffsetX; }
    public void setShadowOffsetX(int v) {
        int old = this.shadowOffsetX;
        this.shadowOffsetX = v;
        firePropertyChange("shadowOffsetX", old, v);
        repaint();
    }

    public int getShadowOffsetY() { return shadowOffsetY; }
    public void setShadowOffsetY(int v) {
        int old = this.shadowOffsetY;
        this.shadowOffsetY = v;
        firePropertyChange("shadowOffsetY", old, v);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        // Fixed “card” size unless user overrides in GUI
        return new Dimension(150, 150);
    }

    // ================= Painting =================

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int w = getWidth();
        int h = getHeight();
        int arc = cornerRadius * 2;

        // Shadow
        if (shadowEnabled && shadowColor != null) {
            g2.setColor(shadowColor);
            g2.fillRoundRect(
                    shadowOffsetX,
                    shadowOffsetY,
                    w - 1,
                    h - 1,
                    arc,
                    arc
            );
        }

        Shape mainShape = new RoundRectangle2D.Double(
                0, 0, w, h, arc, arc
        );

        // Background
        if (backgroundColor != null) {
            g2.setColor(backgroundColor);
            g2.fill(mainShape);
        }

        // Effective icon (icon or placeholder)
        Icon icon = super.getIcon();
        if (icon == null) icon = placeholder;

        if (icon != null) {
            int iw = icon.getIconWidth();
            int ih = icon.getIconHeight();

            Rectangle target = calcImageArea(iw, ih, w, h);

            // If we can get an Image, scale it; otherwise fall back to unscaled icon
            Image img = (icon instanceof ImageIcon)
                    ? ((ImageIcon) icon).getImage()
                    : null;

            g2.setClip(mainShape);

            if (img != null) {
                g2.drawImage(
                        img,
                        target.x, target.y,
                        target.width, target.height,
                        this
                );
            } else {
                // fallback: no scaling, just centered-ish
                int x = target.x;
                int y = target.y;
                icon.paintIcon(this, g2, x, y);
            }

            g2.setClip(null);
        }

        // Border
        if (borderEnabled && borderThickness > 0f && borderColor != null) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.draw(new RoundRectangle2D.Double(
                    borderThickness / 2,
                    borderThickness / 2,
                    w - borderThickness,
                    h - borderThickness,
                    arc, arc
            ));
        }

        g2.dispose();
    }

    private Rectangle calcImageArea(int imgW, int imgH, int boxW, int boxH) {
        if (imgW <= 0 || imgH <= 0) {
            return new Rectangle(0, 0, boxW, boxH);
        }

        switch (scaleType) {
            case CENTER:
                return new Rectangle(
                        (boxW - imgW) / 2,
                        (boxH - imgH) / 2,
                        imgW,
                        imgH
                );
            case STRETCH:
                return new Rectangle(0, 0, boxW, boxH);
            case FIT: {
                double s = Math.min(
                        (double) boxW / imgW,
                        (double) boxH / imgH
                );
                int w = (int) (imgW * s);
                int h = (int) (imgH * s);
                return new Rectangle(
                        (boxW - w) / 2,
                        (boxH - h) / 2,
                        w, h
                );
            }
            case FILL: {
                double s = Math.max(
                        (double) boxW / imgW,
                        (double) boxH / imgH
                );
                int w = (int) (imgW * s);
                int h = (int) (imgH * s);
                return new Rectangle(
                        (boxW - w) / 2,
                        (boxH - h) / 2,
                        w, h
                );
            }
            default:
                return new Rectangle(0, 0, boxW, boxH);
        }
    }
}
