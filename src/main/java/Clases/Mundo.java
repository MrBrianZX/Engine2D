package Clases;

import java.util.ArrayList;
import java.util.Random;

public class Mundo {
    private Jugador personaje;
    private Pilares pilarUpper;
    private Pilares pilarBelow;
    private Objeto2D bg;

    private boolean fin = false;
    private double xlim, ylim;

    public ArrayList<Objeto> ObjetosMundo = new ArrayList<>();

    public void init(SistemaControl evento, int widht, int height){
        xlim = widht;
        ylim = height;

        bg = new Objeto2D("/bg2.png");
        bg.setEscalar(3,3);

        personaje = new Jugador("/wormvolador.png", 0,ylim/2);
        personaje.Addcontrol(evento);
        personaje.setVelocidad(new Vector2D(0,1));
        personaje.flipHorizontal();
        personaje.setColisionVisible(true);

        pilarUpper = new Pilares("/muro1.png", xlim/2, 0);
        pilarUpper.rotarImagen(90);
        pilarUpper.setVelocidad(new Vector2D(-3,0));
        pilarUpper.setEscalar(0.5,1);
        pilarUpper.setColisionVisible(true);

        pilarBelow = new Pilares("/muro2.png", xlim/1.25, -20);
        pilarBelow.setEscalar(0.5,1);
        pilarBelow.setVelocidad(new Vector2D(-3,0));
        pilarBelow.setColisionVisible(true);

        ObjetosMundo.add(bg);
        ObjetosMundo.add(personaje);
        ObjetosMundo.add(pilarUpper);
        ObjetosMundo.add(pilarBelow);
    }

    public void Reglas() {
        for(Objeto actores : ObjetosMundo){
            if(actores instanceof Objeto2DDinamico){
                Objeto2DDinamico actoresDynamicos = (Objeto2DDinamico)actores;

                double x1 = actoresDynamicos.getPosicion().x;
                double y1 = actoresDynamicos.getPosicion().y;

                double x2 = x1 + actoresDynamicos.getSuperficie().getImagen().getWidth();
                double y2 = y1 + actoresDynamicos.getSuperficie().getImagen().getHeight();
                actoresDynamicos.getColision().UpdatePosicion(x1,y1,x2,y2);
            }
        }
        if(personaje.getPosicion().y >= ylim || pilarUpper.getColision().EnColision(personaje.getColision()) || pilarBelow.getColision().EnColision(personaje.getColision())){
            SetFin(true);
            System.out.println(fin);
        }

        int escalaminima = 1;
        int escalamaxima = 3;

        if(pilarUpper.getPosicion().x <= 0){
            pilarUpper.resetDimensiones();
            pilarUpper.rotarImagen(90);

            Random random = new Random();
            int r = random.nextInt((escalamaxima - escalaminima) + escalaminima) + 1;
            pilarUpper.setEscalar(0.5,r);
            pilarUpper.setPosicion(new Vector2D(xlim+100,0));
        }

        if(pilarBelow.getPosicion().x <= 0){
            pilarBelow.resetDimensiones();

            Random random = new Random();
            int r = random.nextInt((escalamaxima - escalaminima) + escalaminima) + 1;
            pilarBelow.setEscalar(0.5,r);
            pilarBelow.setPosicion(new Vector2D(xlim+100,ylim-pilarBelow.getSuperficie().getImagen().getHeight()+10));
        }
    }

    public void update(){
        Reglas();
    }

    public void SetFin(boolean valor){
        fin = valor;
    }

    public boolean IsOver(){
        return fin;
    }
}
// Good Job Darling