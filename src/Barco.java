
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Barco extends JLabel implements Serializable {

    private int x;
    private int y;
    private String position;
    private ImageIcon img;
    private boolean explotado;

    public Barco(int x, int y, String position) {
        this.x = x;
        this.y = y;
        this.position = position;
        setBounds(x, y, 38, 48);
        img = new ImageIcon(getClass().getResource("barco.png"));
    }

    public int getX() {
        return x;
    }

    public String getPosition() {
        return position;
    }

    public void explotar() {
        new Thread(() -> {
            try {
                URL sonido = getClass().getResource("explosion.wav");
                AudioInputStream in = AudioSystem.getAudioInputStream(sonido);
                Clip clip = AudioSystem.getClip();
                clip.open(in);
                clip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
        setImg(new ImageIcon(getClass().getResource("explosion.png")));
        repaint();
        JOptionPane.showMessageDialog(this, "Tu barco en " + getPosition() + " recibió un misil!");
        }).start();
        
        

    }

    public boolean isExplotado() {
        return explotado;
    }

    public void setExplotado(boolean explotado) {
        this.explotado = explotado;
    }

    public void setPosition(String position) {
        this.position = position;
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

    @Override
    public String toString() {
        return "Barco{" + "x=" + x + ", y=" + y + ", position=" + position + ", img=" + img + '}';
    }

    boolean isExploded() {
        return explotado;
    }

}
