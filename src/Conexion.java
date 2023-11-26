
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Conexion extends Thread{
    
    Socket conector;
    ObjectOutputStream salida;
    ObjectInputStream entrada;
    int puerto;
    FTablero ventana;
    
    public Conexion(Socket conector, FTablero ventana){
        this.conector = conector;
        this.ventana = ventana;
        try {
            salida = new ObjectOutputStream(conector.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(conector.getInputStream());
            start();
            System.out.println("Conexion existosa");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enviar(List<String> barcoPosiciones){
        try {
            salida.flush();
            salida.writeObject(barcoPosiciones);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void cerrarConexiones(){
        try {
            entrada.close();
            salida.close();
            conector.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public List<String> recibir() {
        List<String> Posiciones = null;
        try {
            Object mensaje = entrada.readObject();
            
            if (mensaje instanceof ArrayList) {
                Posiciones = (List<String>) mensaje;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return Posiciones;
    }
//    @Override
//    public void run(){
//        while (true){
//            try {
//                Object mensaje = entrada.readObject();
//                
//                if (mensaje instanceof String){
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } 
//        }
//    }
}
