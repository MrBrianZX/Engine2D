package Clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;


public class Canvas extends JPanel{

    private int delay;
    private final Timer miTimer;
    private int fps = 60;

    private Mundo primernivel;

    private SistemaMovimiento movimiento = SistemaMovimiento.getInstancia();
    private  SistemaControl controles;

    public Canvas(SistemaControl evento)
    {
        super();
        this.delay = 1000/fps;
        miTimer = new Timer(this.delay,gameTimer);
        miTimer.start();
        controles = evento;

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
    }

    public void init(){
        movimiento.SetCanvasLimit(this.getWidth(),this.getHeight());
        primernivel = new Mundo();
        primernivel.init(controles, this.getWidth(), this.getHeight());
    }

}
//Esta bien dude