package Clases;


import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SistemaControl{

    public boolean pause = false;
    public boolean continuar = false;
    public boolean salir = false;
    public double vx, vy;
    private double rate = 1;

    public SistemaControl(){
        this.vx = rate;
        this.vy = rate;
    }

    public void Manejador(InputEvent evento){
        KeyEvent presiono;
        if(evento.getEventType() == KeyEvent.KEY_PRESSED){
            presiono = (KeyEvent)evento;
            if(presiono.getCode() == KeyCode.SPACE) {
                this.vy = -rate;
            }
        }
        if(evento.getEventType() == KeyEvent.KEY_RELEASED){
            presiono = (KeyEvent)evento;
            if(presiono.getCode() == KeyCode.SPACE) {
                this.vy = rate;
            }
            if(presiono.getCode() == KeyCode.P) {
                this.pause = (!this.pause);
                System.out.println("Pausa");
            }
            if(presiono.getCode() == KeyCode.ESCAPE) {
                this.salir = (!this.salir);
                System.out.println("Salir");
            }
            if(presiono.getCode() == KeyCode.ENTER) {
                this.continuar = (!this.continuar);
                System.out.println("Enter  ");
            }
        }
    }

    public boolean getPause(){
        return this.pause;
    }

    public boolean getContinue(){
        return this.continuar;
    }

    public boolean getSalir(){
        return this.salir;
    }

    public void resetContinue(){
        this.continuar = false;
    }

    public void resetSalir(){
        this.salir = false;
    }

    public void resetPause(){
        this.pause = salir;
    }
}