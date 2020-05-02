package Clases;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class Canvas extends JPanel{

    private int delay;
    private final Timer miTimer;
    private int fps = 60;
    private JLabel textoVida; // = new JLabel("Vida:");
    private Rectangle barraHP;
    private Mundo primernivel;
    private SistemaMovimiento movimiento = SistemaMovimiento.getInstancia();
    private SistemaControl controles;

    public Canvas(SistemaControl evento, Rectangle vida, JLabel vidaTexto)
    {
        super();
        this.delay = 1000/fps;
        miTimer = new Timer(this.delay,gameTimer);
        miTimer.start();
        controles = evento;
        textoVida = vidaTexto;
        barraHP = vida;

    }

    public void init(){
        movimiento.SetCanvasLimit(this.getWidth(),this.getHeight());
        primernivel = new Mundo();
        primernivel.init(controles, this.getWidth(), this.getHeight());
    }

    public void UpdateFrames(){
        this.repaint();
        //System.out.println("Repintando los frames");
    }

    public ActionListener gameTimer = actionEvent -> UpdateFrames();

    private void DibujarImagenes(Objeto entidad, Graphics2D g){
        Objeto2D miEntidad = (Objeto2D) entidad;
        AffineTransform tx = AffineTransform.getScaleInstance(1,1);

        BufferedImageOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage template = op.filter(miEntidad.getSuperficie().getImagen(),null);

        g.drawImage(template,op,(int)miEntidad.getPosicion().x,(int)miEntidad.getPosicion().y);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        primernivel.update();
        for(Objeto objs: primernivel.ObjetosMundo){
            movimiento.Update(objs);
            objs.MostrarColision((Graphics2D) g);
            DibujarImagenes(objs, (Graphics2D) g);
        }
        if(barraHP != null){
            int vida;
            vida = this.primernivel.getPlayer().getHPactual();
            barraHP.setHeight(20);
            barraHP.setFill(ObtenerColor(vida));
            barraHP.setWidth(this.primernivel.getPlayer().getHPactual()*1.5);
            textoVida.setText(String.valueOf(vida));
        }
    }

    private Paint ObtenerColor(int vida){
        if(vida >74)
            return Paint.valueOf("#52ad25"); //Verde
        else if(vida>59 && vida<75)
            return Paint.valueOf("#ce7d2c"); //Naranja
        else if(vida>29 && vida<60)
            return Paint.valueOf("#ce342c"); //Rojo
        else
            return Paint.valueOf("#5532ae"); //Morado

    }

    public Mundo getMundo() {
        return primernivel;
    }
}
