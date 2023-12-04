
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
    Conexion vic;
    ServerSocket ss;
    ServerSocket vv;
    Usuario user;
    List<String> posOponente;
    List<String> marcaOponente;
    List<String> aciertos;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    int vidas;
    //boolean hit = false;

    public FServidor() {
        super("Servidor");
        try {
            ss = new ServerSocket(6798);
            vv = new ServerSocket(6898);

            JDialog esperando = new JDialog();
//            esperando.setUndecorated(false);
            JLabel mensaje = new JLabel("Esperando a que un jugador se una...");// DIALOGO QUE ESPERA A QUE SE CONECTE EL CLIENTE
            mensaje.setHorizontalAlignment(SwingConstants.CENTER);
            esperando.add(mensaje);
            esperando.setSize(300, 100);
            esperando.setLocationRelativeTo(null);
            esperando.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            esperando.setVisible(true);
            vidas = 5;
            cnx = new Conexion(ss.accept(), this);
            vic = new Conexion(vv.accept(), this);

//            String name = JOptionPane.showInputDialog(this, this.getTitle() + ": Ingresa un nombre de usuario");
//            user = new Usuario(name);
            esperando.dispose();
            getLVidas1().setText("" + obtenerVidas());
            checkVidas();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkVidas() {
        new Thread(() -> {
            while (true) {
                if (vidas <= 0) {
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Perdiste!! :(");
                    this.dispose();
                    break;
                }
                try {
                    Thread.sleep(1000); // Adjust the duration between checks
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void enviarvictoria() {
        cnx.enviarEstadoDeVictoria(true);
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
    public void quitarVida() {
        vidas--;
    }

    @Override
    public List<String> obtenerMarcaOponente() {
        List<String> recibido = cnx.recibir();
        return recibido;
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

                return -1;
            }
        } catch (Exception e) {
            // Handle specific exceptions
            e.printStackTrace(); // Consider logging the exception for debugging
            JOptionPane.showMessageDialog(this, "Error receiving data from opponent!");
            return -1; // Return 0 as an indication of an error or no data received
        }
    }

    @Override
    public int locationMarca() {
        System.out.println("ROUND START");
        marcaOponente = obtenerMarcaOponente();

        if (marcaOponente == null) {
            return -1; // No data received
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
                    vidas--;
                    getLVidas1().setText("" + vidas);
                    System.out.println("Remaining lives: " + vidas);

                    break;
                }
            }
        }
        return hit;

    }

    private void handleHit() {
        List<String> hitPositionsToSend = new ArrayList<>();

        // Collect hit positions that need to be sent
        for (String hitPosition : marcaOponente) {
            for (Barco boat : getListaDeBarcos()) {
                if (boat.getPosition().equals(hitPosition) && boat.isExplotado()) {
                    hitPositionsToSend.add(hitPosition);
                    break;
                }
            }
        }

        // Send the collected hit positions to the opponent for marking the explosions
        for (String hitPositionToSend : hitPositionsToSend) {
            cnx.enviarPosicionDeImpacto(hitPositionToSend);
        }

        ReiniciarJuego();
    }

    private void MarcarExplosion(String position) {
        char letter = position.charAt(0);
        int number = Integer.parseInt(position.substring(1));

        int row = letter - 'A'; // Convert the letter to a row index (assuming 'A' is 0)
        int column = number - 1; // Subtract 1 to convert number to a zero-based index

        int cellWidth = getpMapa1().getWidth() / getpMapa1().getGRID_SIZE();
        int cellHeight = getpMapa1().getHeight() / getpMapa1().getGRID_SIZE();

        int x = column * cellWidth + (cellWidth / 2); // Calculate x coordinate
        int y = row * cellHeight + (cellHeight / 2);
        double xPercentage = 0.5;
        double yPercentage = 0.5;

        int xOffset = (int) (cellWidth * xPercentage - cellWidth);
        int yOffset = (int) (cellHeight * yPercentage - cellHeight);

        x += xOffset;
        y += yOffset;

        getpMapa1().dibujarPuntoen(x, y);
        System.out.println("x: " + x + "y: " + y);

    }

    private void handleMiss() {
        processHitPosition();
        ReiniciarJuego();
    }

    private void processHitPosition() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> cnx.recibirPosicionDeImpacto());

        try {
            String hitPosition = future.get(1000, TimeUnit.MILLISECONDS);
            System.out.println("processhitPosition: " + hitPosition);

            if (hitPosition != null) {
                MarcarExplosion(hitPosition); // Implement this method to draw explosion at the specified position
            } else {
                ReiniciarJuego();
            }
        } catch (TimeoutException e) {
            // Timeout occurred, handle it accordingly
            System.out.println("Timeout while waiting for hit position");
            // Perform actions for timeout, e.g., restart game or handle the situation
            ReiniciarJuego();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Handle exceptions
        } finally {
            executor.shutdownNow(); // Shutdown the executor
        }
    }

    private void ReiniciarJuego() {
        getBEnviarUbi().setEnabled(true);
        getpMapa1().setLimit(1);
        repaint();
    }

    public static void main(String args[]) {

        new FServidor().setVisible(true);
    }

}
