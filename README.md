# 🎨 FancyUI – Modern Custom Swing Components for Java

FancyUI is a modern, customizable Java Swing component library designed to bring **beautiful UI features** to traditional desktop applications.  
It includes components with **rounded corners, gradients, shadows, icons, password toggles, background images, custom borders, placeholders**, and more.

Built for **NetBeans GUI Builder compatibility**, FancyUI works perfectly with drag-and-drop development and exposes all custom properties inside the Property Sheet through BeanInfo.

---

## ✨ Features

### 🔸 Panels  
✔ Gradient background  
✔ Solid background  
✔ Corner radius  
✔ Border styling  
✔ Opacity  
✔ **Background image**  
✔ Scale modes (FIT, FILL, STRETCH, CENTER)

### 🔸 Text Fields  
✔ Placeholder text  
✔ Custom padding  
✔ Border radius  
✔ Custom border  
✔ Left/Right icons  
✔ Focus border color  
✔ Icon insets handled automatically

### 🔸 Password Fields  
✔ Show/Hide toggle eye icon  
✔ Customizable colors  
✔ Rounded corners + border  
✔ Default included icons  
✔ Optional toggle enabling

### 🔸 Buttons  
✔ Hover effects  
✔ Rounded corners  
✔ Custom border  
✔ Custom background + hover color  
✔ Custom foreground + hover color

### 🔸 Labels  
✔ Hover effects (foreground + background)  
✔ Rounded corners  
✔ Border styling  

### 🔸 Image Component (`FancyImage`)  
✔ Extends `JLabel` (NetBeans-friendly)  
✔ Background image or direct icon  
✔ Scale modes: FIT, FILL, STRETCH, CENTER  
✔ Placeholder support  
✔ Rounded corners  
✔ Border + Shadow

---

## 📦 Installation

### **1. Build the Library**
1. Open the FancyUI project in NetBeans.  
2. Go to **Run → Clean and Build**.  
3. The JAR will be created in the `dist/` folder.

### **2. Add to NetBeans Palette**
1. Open any Swing GUI form (`.java` with design view).  
2. Right-click Palette → **Palette Manager**.  
3. Add from JAR → select the FancyUI `.jar`.  
4. Choose all FancyUI components.  
5. Finish.

Your custom components will now appear in the GUI Builder palette.

---

## 🧩 Components Included

| Component | Description |
|----------|-------------|
| `FancyPanel` | Panel with gradient, image background, shadow, radius |
| `FancyTextField` | Text field with icons, rounded corners, placeholder |
| `FancyPasswordField` | Password field with show/hide eye icon |
| `FancyButton` | Button with hover styles and rounded design |
| `FancyLabel` | Label with hover effects and border/radius |
| `FancyImage` | Image display component with scaling + placeholder |

---

## 🛠 Usage Example

### **FancyTextField**
```java
FancyTextField txt = new FancyTextField();
txt.setPlaceholder("Enter your name");
txt.setLeftIcon(new ImageIcon(getClass().getResource("/icons/user.png")));

FancyPanel panel = new FancyPanel();
panel.setBackgroundImage(new ImageIcon("background.jpg"));
panel.setImageScaleType(FancyPanel.ImageScaleType.FILL);


FancyPasswordField pass = new FancyPasswordField();
pass.setShowToggleEnabled(true);


🎨 Custom Property Support (BeanInfo)

Each FancyUI component includes a BeanInfo class, so:

Custom properties appear in NetBeans Property Editor

Properties are grouped under Custom Properties

Editable fields include color pickers, booleans, numeric values, icons, enums, etc.

🧱 Requirements

Java 8+ (recommended: Java 17+)

NetBeans 12+ (GUI Builder support)

Swing application
