package Clases;

public class Jugador extends Objeto2DDinamico {
    private SistemaControl botones;
    private double impulso = 5;

    public Jugador(String imagen, double posX, double posY){
        super(imagen, posX, posY);
    }

    public void Addcontrol(SistemaControl controller){
        this.botones = controller;
    }

    public SistemaControl GetControl(){
        return this.botones;
    }

    @Override
    public void Mover(Vector2D vel){
        vel = vel.Por(new Vector2D(this.impulso, this.impulso));
        super.Mover(vel);
    }
    //Ta gueno
}
