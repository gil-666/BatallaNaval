
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
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
    public void enviarmarcas(List<String> marcaPositions) {
        cnx.enviar(marcaPositions); // Send the marks
    }

    @Override
    public void cerrar() {
        cnx.cerrarConexiones();
    }

    @Override
    public List<String> obtenerMarcaOponente() {
        List<String> recibido = cnx.recibir();
        return recibido;
    }

    @Override
    public int obtenerVidas() {
        return vidas;
    }

    @Override
    public void quitarVida() {
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
        System.out.println("ROUND START");
        marcaOponente = obtenerMarcaOponente();

        if (marcaOponente == null) {
            return 0; // No data received
        }

        boolean hit = checkHits();

        if (hit) {
            handleHit();
            return 2; // Hit sent
        } else {
            handleMiss();
            return 3; // Miss sent
        }
    }

    private boolean checkHits() {
        boolean hit = false;

        for (String opponentPosition : marcaOponente) {
            for (Barco boat : getListaDeBarcos()) {
                if (boat.getPosition().equals(opponentPosition) && !boat.isExplotado()) {
                    boat.explotar();
                    boat.setExplotado(true);
                    hit = true;
                    if (vidas != 0) {
                        quitarVida();
                        getLVidas1().setText("" + obtenerVidas());
                    } else {
                        JOptionPane.showMessageDialog(this, "Perdiste! :(");
                    }
                    break;
                }
            }
        }
        return hit;
    }

    private void handleHit() {
        ReiniciarJuego();
    }

    private void handleMiss() {
        ReiniciarJuego();
    }

    private void ReiniciarJuego() {
        getBEnviarUbi().setEnabled(true);
        
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

    public static void main(String[] args) {
        new FCliente("").setVisible(true);

    }
}
