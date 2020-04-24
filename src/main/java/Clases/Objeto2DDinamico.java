package Clases;

import java.awt.*;

public class Objeto2DDinamico extends Objeto2D{
    private CuadroColision mi_cuadro_colision;

    public Objeto2DDinamico(){
        super();
        this.es_movible=true;
    }

    public Objeto2DDinamico(String imagen){
        super(imagen);
        this.es_movible=true;
    }

    public Objeto2DDinamico(String imagen, double posx, double posy){
        super(imagen,posx,posy);
        this.es_movible=true;

        double x1 = this.getPosicion().x;
        double y1 = this.getPosicion().y;
        double x2 = x1 + this.getSuperficie().getImagen().getWidth();
        double y2 = y1 + this.getSuperficie().getImagen().getHeight();
        mi_cuadro_colision = new CuadroColision(x1,y1,x2,y2);
    }

    public CuadroColision getColision() {
        return mi_cuadro_colision;
    }

    public void setColisionVisible(boolean visible) {
        this.mi_cuadro_colision.mostrar = visible;
    }

    @Override
    public void MostrarColision(Graphics2D render) {
        if(this.mi_cuadro_colision!=null){
            if(this.mi_cuadro_colision.mostrar){
                for(int i=0; i<this.mi_cuadro_colision.getVertices().length-1; i++){
                    int x1= (int) mi_cuadro_colision.getVertices()[i].x;
                    int x2= (int) mi_cuadro_colision.getVertices()[i+1].x;
                    int y1= (int) mi_cuadro_colision.getVertices()[i].y;
                    int y2= (int) mi_cuadro_colision.getVertices()[i+1].y;
                    render.drawLine(x1,y1,x2,y2);
                }
                render.drawLine((int)mi_cuadro_colision.getVertices()[3].x,(int)mi_cuadro_colision.getVertices()[3].y,(int)mi_cuadro_colision.getVertices()[0].x,(int)mi_cuadro_colision.getVertices()[0].y);
            }
        }
    }
}
//TODO Compa Alterado
