
import java.awt.BorderLayout;
import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author gil
 */
public class FTablero extends javax.swing.JFrame {

    private List<String> boatPositions;
    private List<String> marcaPositions;
    int limite;
    int limitemarcas;
    int borde;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    public List<String> getBoatPositions() {
        return boatPositions;
    }

    public List<String> getMarcaPositions() {
        return marcaPositions;
    }

    public FTablero(String titulo) {
        setTitle(titulo);
        initComponents();
        boatPositions = new ArrayList<>();
        marcaPositions = new ArrayList<>();
        limite = pUniverso1.getLimit();
        borde = pUniverso1.getLimit();
        limitemarcas = pUniverso1.getLimit();
    }

    public void enviarbarcos() {

    }
    
    

    public void enviarmarcas() {

    }

    public void cerrar() {

    }

    public int locationOponente() {
        return 0;
    }

    public int locationMarca() {
        return 0;
    }

    public int siIntersectan() {
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pUniverso1 = new PUniverso();
        pMapa1 = new PMapa();
        jPanel2 = new javax.swing.JPanel();
        BEnviarUbi = new javax.swing.JButton();
        BObtenerMarcas = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        BEnviarbarco = new javax.swing.JButton();
        BObtenerBarcos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pUniverso1.setPreferredSize(new java.awt.Dimension(380, 480));
        pUniverso1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pUniverso1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pUniverso1Layout = new javax.swing.GroupLayout(pUniverso1);
        pUniverso1.setLayout(pUniverso1Layout);
        pUniverso1Layout.setHorizontalGroup(
            pUniverso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        pUniverso1Layout.setVerticalGroup(
            pUniverso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        pMapa1.setPreferredSize(new java.awt.Dimension(380, 480));
        pMapa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMapa1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMapa1Layout = new javax.swing.GroupLayout(pMapa1);
        pMapa1.setLayout(pMapa1Layout);
        pMapa1Layout.setHorizontalGroup(
            pMapa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMapa1Layout.setVerticalGroup(
            pMapa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicación del oponente"));

        BEnviarUbi.setText("Listo");
        BEnviarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEnviarUbiActionPerformed(evt);
            }
        });

        BObtenerMarcas.setText("obtener marcas");
        BObtenerMarcas.setEnabled(false);
        BObtenerMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BObtenerMarcasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(BEnviarUbi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(BObtenerMarcas)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BEnviarUbi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BObtenerMarcas)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Poner barco"));

        BEnviarbarco.setText("Listo");
        BEnviarbarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEnviarbarcoActionPerformed(evt);
            }
        });

        BObtenerBarcos.setText("obtener barcos");
        BObtenerBarcos.setEnabled(false);
        BObtenerBarcos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BObtenerBarcosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(BEnviarbarco))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(BObtenerBarcos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(BEnviarbarco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BObtenerBarcos)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pUniverso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pMapa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pMapa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pUniverso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BEnviarbarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEnviarbarcoActionPerformed
        if (!boatPositions.isEmpty() && boatPositions != null && boatPositions.size() == borde) {
            enviarbarcos();
            
            BEnviarbarco.setEnabled(false);
            JDialog waitingDialog = new JDialog();
            waitingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            waitingDialog.setLayout(new BorderLayout());
            waitingDialog.add(new JLabel("Esperando al oponente..."), BorderLayout.CENTER);
            waitingDialog.pack();
            waitingDialog.setLocationRelativeTo(null); // Center the dialog
            waitingDialog.setVisible(true);
            new Thread(() -> {
                while (locationOponente() == 0) {
                    try {
                        Thread.sleep(1000); // Wait for 1 second before checking again
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                waitingDialog.dispose();
                System.out.println(getTitle()+": Ubicacion barco enviada al oponente");
            }).start();
        } else {
            JOptionPane.showMessageDialog(this, "Envia todos los barcos!\nTe faltan (" + limite + ")");

        }


    }//GEN-LAST:event_BEnviarbarcoActionPerformed

    private void pUniverso1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pUniverso1MouseClicked

        if (limite > 0) {
            int cellWidth = pUniverso1.getWidth() / pUniverso1.getGRID_SIZE();
            int cellHeight = pUniverso1.getHeight() / pUniverso1.getGRID_SIZE();

            int column = evt.getX() / cellWidth;
            int row = evt.getY() / cellHeight;

            char letter = (char) (alphabet[row]);
            int number = column + 1;

            String position = "" + letter + number;

            if (!boatPositions.contains(position)) {
                boatPositions.add(position);
                System.out.println("Barco agregado en posición: " + position);
            }
            limite--;
        }

    }//GEN-LAST:event_pUniverso1MouseClicked

    private void BObtenerBarcosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BObtenerBarcosActionPerformed
        locationOponente();
    }//GEN-LAST:event_BObtenerBarcosActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed

    private void pMapa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMapa1MouseClicked
//        if (limitemarcas > 0) {
//            Point clickedPoint = new Point(evt.getX(), evt.getY());
//
//            if (!marcaPositions.contains(clickedPoint)) {
//                marcaPositions.add(clickedPoint);
//                System.out.println("marca agregada: " + clickedPoint);
//            }
//            limitemarcas--;
//        }
            if (limitemarcas > 0) {
            int cellWidth = pUniverso1.getWidth() / pUniverso1.getGRID_SIZE();
            int cellHeight = pUniverso1.getHeight() / pUniverso1.getGRID_SIZE();

            int column = evt.getX() / cellWidth;
            int row = evt.getY() / cellHeight;

            char letter = (char) (alphabet[row]);
            int number = column + 1;

            String position = "" + letter + number;

            if (!marcaPositions.contains(position)) {
                marcaPositions.add(position);
                System.out.println("Bomba agregada en posición: " + position);
            }
            limitemarcas--;
        }
    }//GEN-LAST:event_pMapa1MouseClicked

    private void BObtenerMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BObtenerMarcasActionPerformed
        locationMarca();
    }//GEN-LAST:event_BObtenerMarcasActionPerformed

    private void BEnviarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEnviarUbiActionPerformed
        if (!marcaPositions.isEmpty() && marcaPositions != null && marcaPositions.size() == borde ) {
            enviarmarcas();
            BEnviarUbi.setEnabled(false);
            JDialog waitingDialog = new JDialog();
            waitingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            waitingDialog.setLayout(new BorderLayout());
            waitingDialog.add(new JLabel("Esperando al oponente..."), BorderLayout.CENTER);
            waitingDialog.pack();
            waitingDialog.setLocationRelativeTo(null); // Center the dialog
            waitingDialog.setVisible(true);
//            int status = locationMarca();
            new Thread(() -> {

                while (locationMarca() == 0) {
                    try {
                        Thread.sleep(1000); // Wait for 1 second before checking again
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                waitingDialog.dispose();
                

            }).start();
        } else {
            JOptionPane.showMessageDialog(this, "Envia todos los tiros hacia el oponente!\nTe faltan (" + limitemarcas + ")");
            System.out.println("restante " + limitemarcas);
            System.out.println("size " + marcaPositions.size());
        }

    }//GEN-LAST:event_BEnviarUbiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BEnviarUbi;
    private javax.swing.JButton BEnviarbarco;
    private javax.swing.JButton BObtenerBarcos;
    private javax.swing.JButton BObtenerMarcas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private PMapa pMapa1;
    private PUniverso pUniverso1;
    // End of variables declaration//GEN-END:variables
}
