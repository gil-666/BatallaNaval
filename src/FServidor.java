
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public FServidor() {
        super("Servidor");
        try {
            ss = new ServerSocket(7000);
            cnx = new Conexion(ss.accept(), this);
            String name = JOptionPane.showInputDialog(this, this.getTitle() + ": Ingresa un nombre de usuario");
            user = new Usuario(name);
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
                for (String point : marcaOponente) {
                    System.out.println(point);
                }
                aciertos = obtenerAciertos(marcaOponente, getBoatPositions());
                System.out.println("barcos acertados por "+getTitle()+": "+aciertos.toString());
                return 1;
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
