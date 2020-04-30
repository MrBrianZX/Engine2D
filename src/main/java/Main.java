import Clases.Canvas;
import Clases.SistemaControl;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        SistemaControl Micontrol = new SistemaControl();
        int WIDTH = 300;
        int HEIGHT = 400;

        System.out.println("MAIN");
        Canvas mi_canvas = new Canvas(Micontrol);

        JPanel contenedor = new JPanel();
        contenedor.add(mi_canvas.vidaLabel);
        JMenuBar barra = new JMenuBar();

        mi_canvas.setSize(WIDTH,HEIGHT);
         mi_canvas.init();

        JFrame marco = new JFrame("Canvas");
        marco.getContentPane().add(mi_canvas);
        marco.getContentPane().add(BorderLayout.NORTH,contenedor);
        marco.setSize(WIDTH+50,HEIGHT+50);

        marco.addKeyListener(Micontrol);
        marco.setVisible(true);
        marco.setResizable(false);

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
