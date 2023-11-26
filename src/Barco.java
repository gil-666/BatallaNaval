
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Barco extends JLabel implements Serializable{
    private int x;
    private int y;
    
    private ImageIcon img;

    public Barco(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, 38, 48);
        img = new ImageIcon(getClass().getResource("barco.png"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
