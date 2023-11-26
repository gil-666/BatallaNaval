
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gil
 */
public class Punto extends JLabel implements Serializable{
    private ImageIcon img;

    public Punto(int x, int y) {
        setBounds(x, y, 38, 48);
        img = new ImageIcon(getClass().getResource("marca.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }
}
