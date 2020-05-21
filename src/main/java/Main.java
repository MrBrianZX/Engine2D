import Clases.Canvas;
import Clases.SistemaControl;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.*;
public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    Stage MIVentana;
    @Override
    public void start(Stage ventana) throws Exception {
        SistemaControl Micontrol = new SistemaControl();
        int WIDTH = 320;
        int HEIGHT = 400;

        //JavaFX
        MIVentana = ventana;
        MIVentana.setTitle("MiniJuego con Engine JavaFX");
        MIVentana.setAlwaysOnTop(true);
        System.out.println("MAIN");
        //Labels
        JLabel vida = new JLabel("Vida:");
        JLabel vidavalor = new JLabel("Loading");
        SwingNode nodoVidaTexto = new SwingNode();
        nodoVidaTexto.setContent(vida);
        SwingNode nodoVidaValor = new SwingNode();
        nodoVidaValor.setContent(vidavalor);

        Rectangle hpbarra = new Rectangle();

        //Canvas
        Canvas mi_canvas = new Canvas(Micontrol,hpbarra,vidavalor);
        mi_canvas.setSize(WIDTH,HEIGHT);
        mi_canvas.init();
        SwingNode nodoCanvas = new SwingNode();
        nodoCanvas.setContent(mi_canvas);

        Separator linea = new Separator(Orientation.VERTICAL);
        Separator linea2 = new Separator(Orientation.VERTICAL);

        HBox toppanel = new HBox(nodoVidaTexto,linea, hpbarra,linea2,nodoVidaValor);
        toppanel.setPrefWidth(WIDTH);
        toppanel.setPrefHeight(25);

        BorderPane layout = new BorderPane();
        layout.setTop(toppanel);
        layout.setCenter(nodoCanvas);

        //Evento al precionar tecla
        EventHandler<InputEvent> teclas = inputEvent ->{
            Micontrol.Manejador(inputEvent);
            inputEvent.consume();
        };

        //Agregar el evento a la ventana
        MIVentana.addEventHandler(KeyEvent.KEY_PRESSED,teclas);
        MIVentana.addEventHandler(KeyEvent.KEY_RELEASED,teclas);

        Scene Area = new Scene(layout, WIDTH+10, HEIGHT+10);

        MIVentana.setScene(Area);
        MIVentana.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(1);
    }
}
