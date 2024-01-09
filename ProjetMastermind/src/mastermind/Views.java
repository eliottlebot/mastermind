package mastermind;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public abstract class Views extends JFrame {
    Font customFont;
    public Views(String nom)
    {
        super(nom);
    }

    public Font loadCustomFont(int style, int size) {
        String path = "assets/fonts/Capuche.otf";
        customFont = null;
        try {
            File fontFile = new File(path);
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style, size);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return customFont;
    }

    public void setCustomFontForComponents(Container container)
    {
        Component[] components = container.getComponents();
        for (Component component : components)
        {
            if (component instanceof JLabel)
            {
                component.setFont(customFont);
            }
            else if (component instanceof JButton)
            {
                component.setFont(customFont.deriveFont(customFont.getSize() + 15));
            }
            else if (component instanceof JTextField)
            {
                String text = ((JTextField) component).getText();
                if (!text.matches(".*\\d.*"))
                {
                    component.setFont(customFont.deriveFont(customFont.getSize() + 10));
                }
                else
                {
                    component.setFont(new Font("Arial", Font.BOLD, 30));
                }
            }
            else if (component instanceof JRadioButton)
            {
                component.setFont(customFont.deriveFont(customFont.getSize() + 5));
            }
            else if (component instanceof Container)
            {
                setCustomFontForComponents((Container) component);
            }
        }
    }

    public void setCustomFontForComponents(Container container, Font Xfont)
    {
        Component[] components = container.getComponents();
        for (Component component : components)
        {
            if (component instanceof JLabel)
            {
                component.setFont(Xfont);
            }
            else if (component instanceof JButton)
            {
                component.setFont(Xfont.deriveFont(Xfont.getSize() + 15));
            }
            else if (component instanceof JTextField)
            {
                String text = ((JTextField) component).getText();
                if (!text.matches(".*\\d.*"))
                {
                    component.setFont(Xfont.deriveFont(Xfont.getSize() + 10));
                }
                else
                {
                    component.setFont(new Font("Arial", Font.BOLD, 30));
                }
            }
            else if (component instanceof JRadioButton)
            {
                component.setFont(customFont.deriveFont(customFont.getSize() + 5));
            }
            else if (component instanceof Container)
            {
                setCustomFontForComponents((Container) component, Xfont);
            }
        }
    }

    public void setCustomFontForComponents(Component[] components)
    {
        for (Component component : components)
        {
            if (component instanceof JLabel)
            {
                component.setFont(customFont);
            }
            else if (component instanceof JButton)
            {
                component.setFont(customFont.deriveFont(customFont.getSize() + 15));
            }
            else if (component instanceof JTextField)
            {
                String text = ((JTextField) component).getText();
                if (!text.matches(".*\\d.*"))
                {
                    component.setFont(customFont.deriveFont(customFont.getSize() + 10));
                }
                else
                {
                    component.setFont(new Font("Arial", Font.BOLD, 30));
                }
            }
            else if (component instanceof JRadioButton)
            {
                component.setFont(customFont.deriveFont(customFont.getSize() + 5));
            }
            else if (component instanceof Container)
            {
                setCustomFontForComponents((Container) component);
            }
        }
    }
}
