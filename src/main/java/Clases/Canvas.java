package Clases;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Canvas extends JPanel {

    private int delay;
    private final Timer miTimer;
    private boolean prueba = true;
    private int fps = 60;
    private JLabel textoVida;
    private Rectangle barraHP;
    private Mundo primernivel = new Mundo();
    ;
    private SistemaMovimiento movimiento = SistemaMovimiento.getInstancia();
    private SistemaControl controles;
    private JLabel textoDerrota;


    public Canvas(SistemaControl evento, Rectangle vida, JLabel vidaTexto) {
        super();
        this.delay = 1000 / fps;
        miTimer = new Timer(this.delay, gameTimer);
        miTimer.start();
        controles = evento;
        textoVida = vidaTexto;
        barraHP = vida;

    }

    public void init() {
        movimiento.SetCanvasLimit(this.getWidth(), this.getHeight());
        primernivel.init(controles, this.getWidth(), this.getHeight());
        this.primernivel.getPlayer().setHPactual(10);
    }

    public void UpdateFrames() {
        this.repaint();
    }

    public ActionListener gameTimer = actionEvent -> UpdateFrames();

    private void DibujarImagenes(Objeto entidad, Graphics2D g) {
        Objeto2D miEntidad = (Objeto2D) entidad;
        AffineTransform tx = AffineTransform.getScaleInstance(1, 1);

        BufferedImageOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage template = op.filter(miEntidad.getSuperficie().getImagen(), null);

        g.drawImage(template, op, (int) miEntidad.getPosicion().x, (int) miEntidad.getPosicion().y);
    }

    @Override
    public void paintComponent(Graphics g) {
        int vida = this.primernivel.getPlayer().getHPactual();
        if (!primernivel.personaje.getBotones().getPause() && vida > 0) {
            super.paintComponent(g);
            primernivel.update();
            for (Objeto objs : primernivel.ObjetosMundo) {
                movimiento.Update(objs);
                objs.MostrarColision((Graphics2D) g);
                DibujarImagenes(objs, (Graphics2D) g);
            }
            if (barraHP != null) {
                barraHP.setHeight(20);
                barraHP.setFill(ObtenerColor(vida));
                barraHP.setWidth(this.primernivel.getPlayer().getHPactual() * 1.5);
                textoVida.setText(String.valueOf(vida));
            }
        } else if (vida < 1) {
            if (primernivel.personaje.getBotones().getContinue()) {
                this.init();
                primernivel.personaje.getBotones().resetContinue();
                primernivel.personaje.getBotones().resetPause();
            }
        }
        if (primernivel.personaje.getBotones().getSalir()) {
            System.exit(0);
        }


    }

    private Paint ObtenerColor(int vida) {
        if (vida > 74)
            return Paint.valueOf("#52ad25"); //Verde
        else if (vida > 59 && vida < 75)
            return Paint.valueOf("#ce7d2c"); //Naranja
        else if (vida > 29 && vida < 60)
            return Paint.valueOf("#ce342c"); //Rojo
        else if (vida > 0)
            return Paint.valueOf("#5532ae"); //Morado
        else {
            return Paint.valueOf(" #000000");
        }
    }

    public Mundo getMundo() {
        return primernivel;
    }
}
