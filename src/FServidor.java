
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
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
    public void enviarmarcas(List<String> marcaPositions) {
            cnx.enviar(marcaPositions); // Send the marks
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
        vidas--;
    }

    @Override
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
                boolean hit = false; // Reset hit status for this round

                for (String opponentPosition : marcaOponente) {
                    for (Barco boat : getListaDeBarcos()) {
                        String boatPosition = boat.getPosition();
                        System.out.println("checando barco exploded= " + boat.isExplotado() + "en posicion " + boatPosition + " con marca: " + opponentPosition);
                        if (boatPosition.equals(opponentPosition) && !boat.isExplotado()) {
                            boat.explotar();
                            boat.setExplotado(true);
                            hit = true; // Flag it as a hit for this round
                            if (vidas != 0) { // If there are lives remaining after the explosion
                                quitarVida(boat);
                                getLVidas1().setText("" + obtenerVidas());
                                // Any other necessary game logic here...
                            } else {
                                JOptionPane.showMessageDialog(this, "Perdiste! :(");
                            }
                            break; // Exit the loop for this opponent's position
                        }
                    }
                }
                if (hit) {
                    ReiniciarJuego();
                    return 2; // enviar golpe
                } else {
                    ReiniciarJuego();
                    return 3; // fallo
                }
            } else {
                return 0; // Indicate no data received
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging the exception for debugging
            JOptionPane.showMessageDialog(this, "Error receiving data from opponent!");
            return -1; // Indicate an error state
        }
    }

    public void ReiniciarJuego() {
        getBEnviarUbi().setEnabled(true);
        marcaOponente = new ArrayList<>();
        getpMapa1().setLimit(2);
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
