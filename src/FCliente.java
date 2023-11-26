
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
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
    ArrayList<Point> posOponente;
    ArrayList<Point> marcaOponente;

    public FCliente() {
        super("Cliente");
        try {
            cnx = new Conexion(new Socket("localhost", 7000), this);
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

    @Override
    public int locationOponente() {
        try {
            posOponente = cnx.recibir();
            if (posOponente != null) {
                for (Point point : posOponente) {
                    System.out.println("x=" + point.x + ", y=" + point.y);
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
            if (marcaOponente != null) {
                for (Point point : marcaOponente) {
                    System.out.println("x=" + point.x + ", y=" + point.y);
                }
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

    public int siIntersectan(ArrayList<Point> boatPositions) {
        if (boatPositions != null && posOponente != null) {
            for (Point boat : boatPositions) {
                for (Point pos : posOponente) {
                    if (boat.equals(pos)) {
                        JOptionPane.showMessageDialog(this, "La marca coincide con un barco en la posición: \n" + pos.toString());
                        return 1; // Found intersection
                    }
                }
            }
        }
        return 0; // No intersection found
    }

    public static void main(String[] args) {
        new FCliente().setVisible(true);

    }
}
