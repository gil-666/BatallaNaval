
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gil
 */
public class FCliente extends FTablero implements Serializable {

    Conexion cnx;
    Usuario user;
    List<String> posOponente;
    List<String> marcaOponente;
    List<String> aciertos;
    int vidas = 5;
    //boolean hit = false;

    public FCliente(String ip) {
        super("Cliente");
        try {
            cnx = new Conexion(new Socket(ip, 6798), this);
            getLVidas1().setText("" + obtenerVidas());
//            String name = JOptionPane.showInputDialog(this, this.getTitle() + ": Ingresa un nombre de usuario");
//            user = new Usuario(name);
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
                // No data received yet from the opponent
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
                                return 1; // Indicate a hit
                            } else {
                                cnx.enviarVictoria(1);
                                JOptionPane.showMessageDialog(this, "Has perdido!");
                                return 1; // Indicate a loss
                            }
                        }
                    }
                }

                // Separate hit detection from message display
                if (hit) {
                    return 2; // Indicate a hit
                } else {
                    return 3; // Indicate a miss
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

    public static void main(String[] args) {
        new FCliente("").setVisible(true);

    }
}
