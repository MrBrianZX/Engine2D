package Clases;

import java.awt.*;

public class Objeto {
    /** Coordenadas*/
    protected Vector2D posicion;
    protected Vector2D velocidad;
    protected Vector2D velocidad_rotacion;
    protected boolean es_movible = false;

    public Objeto(double posx, double posy){
        this.posicion = new Vector2D(posx,posy);
        this.velocidad = new Vector2D(0,0);
        this.velocidad_rotacion = new Vector2D(0,0);
    }

    public Objeto(){
        this.posicion = new Vector2D();
        this.velocidad = new Vector2D(0,0);
        this.velocidad_rotacion = new Vector2D(0,0);
    }

    /** Sistema de movimiento*/
    public Vector2D getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2D posicion) {
        this.posicion = posicion;
    }

    public void Mover(Vector2D velocidad){
        posicion = posicion.Suma(velocidad);
    }

    public Vector2D getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Vector2D velocidad) {
        this.velocidad.x = velocidad.x;
        this.velocidad.y = velocidad.y;
    }

    public Vector2D getVelocidad_rotacion(){
        return velocidad_rotacion;
    }

    public boolean EsMovible(){
        return es_movible;
    }

    public void MostrarColision(Graphics2D render){
        //sobreescribir en la clase que se use. cascaron para el renderer
    }
}
//Todo Bien con esta wea