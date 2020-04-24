package Clases;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Objeto2D extends Objeto {
    private Superficie DisplayImage;
    private BufferedImage imagen_inicial;

    public Objeto2D() {
        super();
        this.DisplayImage = new Superficie();
    }

    public Objeto2D(String imagen) {
        super();
        this.DisplayImage = new Superficie(imagen);
        this.imagen_inicial = this.getSuperficie().getImagen();
    }

    public Objeto2D(String imagen, double posx, double posy) {
        super(posx, posy);
        this.DisplayImage = new Superficie(imagen);
        this.imagen_inicial = this.getSuperficie().getImagen();
    }

    public void LoadImagen(String archivo) {
        this.DisplayImage.cargarImagen(archivo);
    }

    public Superficie getSuperficie() {
        return this.DisplayImage;
    }

    public void setEscalar(double factorX, double factorY){
        BufferedImage antes = this.getSuperficie().getImagen();
        int w = antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage((int)(w*factorX),(int)(h*factorY), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(factorX, factorY);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes, despues);
        this.getSuperficie().setImagen(despues);
    }

    public void resetDimensiones(){
        this.getSuperficie().setImagen(this.imagen_inicial);
    }

    public void flipVertical(){
        BufferedImage antes = this.getSuperficie().getImagen();
        int w = antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1,-1));
        at.concatenate(AffineTransform.getTranslateInstance(0,-h));
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes, despues);
        this.getSuperficie().setImagen(despues);
    }

    public void flipHorizontal(){
        BufferedImage antes = this.getSuperficie().getImagen();
        int w = antes.getWidth();
        int h = antes.getHeight();
        BufferedImage despues = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1,1));
        at.concatenate(AffineTransform.getTranslateInstance(-w,0));
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes, despues);

        this.getSuperficie().setImagen(despues);
    }

    public void rotarImagen(double angulo){
        double rads = Math.toRadians(angulo);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));

        BufferedImage antes = this.getSuperficie().getImagen();
        int w = antes.getWidth();
        int h = antes.getHeight();

        int newWidth = (int) Math.floor(w*cos+ h*sin);
        int newHeight = (int) Math.floor(h*cos+ w*sin);

        BufferedImage despues = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.translate((newWidth-w)/2,(newHeight-h)/2);
        int x = w/2;
        int y = h/2;
        at.rotate(rads,x,y);

        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        despues = scaleOp.filter(antes, despues);
        this.getSuperficie().setImagen(despues);
    }
}
//Todo Bien con esta wea