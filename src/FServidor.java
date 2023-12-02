
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author gil
 */
public class FServidor extends FTablero implements Serializable {

    Conexion cnx;
    ServerSocket ss;
    Usuario user;
    List<String> posOponente;
    List<String> marcaOponente;
    List<String> aciertos;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    int vidas = 5;
    //boolean hit = false;

    public FServidor() {
        super("Servidor");
        try {
            ss = new ServerSocket(6798);

            JDialog esperando = new JDialog();
//            esperando.setUndecorated(false);
            JLabel mensaje = new JLabel("Esperando a que un jugador se una...");// DIALOGO QUE ESPERA A QUE SE CONECTE EL CLIENTE
            mensaje.setHorizontalAlignment(SwingConstants.CENTER);
            esperando.add(mensaje);
            esperando.setSize(300, 100);
            esperando.setLocationRelativeTo(null);
            esperando.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            esperando.setVisible(true);

            cnx = new Conexion(ss.accept(), this);

//            String name = JOptionPane.showInputDialog(this, this.getTitle() + ": Ingresa un nombre de usuario");
//            user = new Usuario(name);
            esperando.dispose();
            getLVidas1().setText("" + obtenerVidas());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void enviarbarcos() {
        cnx.enviar(getBoatPositions());
    }

    @Override
    public void enviarmarcas() {
        cnx.enviar(getMarcaPositions());
    }

    @Override
    public void cerrar() {
        cnx.cerrarConexiones();
    }

    @Override
    public int obtenerVidas() {
        return vidas;
    }

    @Override
    public void quitarVida(Barco boat) {
        getListaDeBarcos().remove(boat);
        vidas--;
    }

    public int locationOponente() {

        try {
            posOponente = cnx.recibir();
            System.out.println(getTitle() + " oponente posiciones:");
            if (posOponente != null) {
                for (String point : posOponente) {
                    System.out.println(point);
                }
                return 1; // Data received from the opponent
            } else {

                return 0;
            }
        } catch (Exception e) {
            // Handle specific exceptions
            e.printStackTrace(); // Consider logging the exception for debugging
            JOptionPane.showMessageDialog(this, "Error receiving data from opponent!");
            return 0; // Return 0 as an indication of an error or no data received
        }
    }

    @Override
    public int locationMarca() {
        try {
            marcaOponente = cnx.recibir();
            System.out.println(getTitle() + " oponente posiciones:");

            if (marcaOponente != null) {
                boolean hit = false; // Flag to determine if any boat got hit

                for (String opponentPosition : marcaOponente) {
                    for (Barco boat : getListaDeBarcos()) {
                        String boatPosition = boat.getPosition();
                        if (opponentPosition.equals(boatPosition)) {
                            boat.explotar();
                            hit = true; // Flag it as a hit
                            if (vidas != 0) { // If there are lives remaining after the explosion
                                quitarVida(boat);
                                getLVidas1().setText("" + obtenerVidas());
                                if (cnx.recibirVictoria() == 1) {
                                    JOptionPane.showMessageDialog(this, "Ganaste!");
                                    return 1; // Indicate a hit
                                }
                                ReiniciarJuego();
                                return 1; // Indicate a hit
                            } else {
                                cnx.enviarVictoria(1);
                                JOptionPane.showMessageDialog(this, "Has perdido!");
                                return 1; // Indicate a loss
                            }
                            
                        }
                        break;
                    }
                }

                // Separate hit detection from message display
//                if (hit) {
//                    return 2; // Indicate a hit
//                } else {
//                    return 3; // Indicate a miss
//                }
            } else {
                return 0; // Indicate no data received
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging the exception for debugging
            JOptionPane.showMessageDialog(this, "Error receiving data from opponent!");
            return -1; // Indicate an error state
        }
        return 1;
    }

    public void ReiniciarJuego() {
        getBEnviarUbi().setEnabled(true);
        getMarcaPositions().clear();
        repaint();
    }

    public List<String> obtenerAciertos(List<String> marcaOponente, List<String> posBarcosjugador) {
        List<String> aciertos = new ArrayList<>();

        for (String str1 : marcaOponente) {
            for (String str2 : posBarcosjugador) {
                if (str1.equals(str2)) {
                    aciertos.add(str1); // Add the intersecting position to the list
                }
            }
        }

        return aciertos; // Return the list of intersecting positions
    }

    public static void main(String args[]) {

        new FServidor().setVisible(true);
    }

}
