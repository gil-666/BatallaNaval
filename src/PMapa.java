
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author gil
 */
public class PMapa extends javax.swing.JPanel {

    private static final int GRID_SIZE = 10;
    int limit = 1;
    Punto marca;
    List<Punto> placedPoints = new ArrayList<>();

    public PMapa() {
        initComponents();
    }

    public static int getGRID_SIZE() {
        return GRID_SIZE;
    }

    public Punto getMarca() {
        return marca;
    }

    public void setMarca(Punto marca) {
        this.marca = marca;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate the cell size to fit the grid within the panel
        int cellWidth = panelWidth / GRID_SIZE;
        int cellHeight = panelHeight / GRID_SIZE;

        // Draw vertical lines
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        //dibujar numeros horizontal arriba
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = i * cellWidth + cellWidth / 2;
            int y = 10;
            g.setColor(Color.black);
            g.drawString("" + (i + 1), x, y);
        }

        //dibujar letras en y
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = 1;
            int y = i * cellHeight + cellHeight - 10;
            char letra = alphabet[i];
            g.setColor(Color.black);
            g.drawString("" + letra, x, y);
        }

        // Draw horizontal lines
        for (int i = 0; i <= GRID_SIZE; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 255, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        if (limit > 0 && isEnabled()) {
            int cellWidth = getWidth() / GRID_SIZE;
            int cellHeight = getHeight() / GRID_SIZE;

            int x = evt.getX() / cellWidth * cellWidth;
            int y = evt.getY() / cellHeight * cellHeight;

            Punto bomba = new Punto(x, y);
            if (!pointAlreadyPlaced(bomba)) {
                placedPoints.add(bomba); // Add the new point to the list
                setMarca(bomba);
                this.add(bomba);
                bomba.setVisible(true);
                repaint();
                limit--;
                System.out.println("pmapa punto: x:"+bomba.getX()+"y: "+bomba.getY());
            } else {
                JOptionPane.showMessageDialog(this, "Ya pusiste ahí!");
            }
        }
    }//GEN-LAST:event_formMouseClicked
    public void dibujarPuntoen(int x, int y){
        Punto bomba = new Punto(x, y);
        bomba.explotar();
        this.add(bomba);
        bomba.setVisible(true);
        repaint();
    }
    private boolean pointAlreadyPlaced(Punto newPoint) {
        for (Punto existingPoint : placedPoints) {
            if (existingPoint.contains(newPoint.getX(), newPoint.getY())) {
                return true; // The grid location is already occupied by a point
            }
        }
        return false; // The grid location is not occupied
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
