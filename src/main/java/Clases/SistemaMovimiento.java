package Clases;

public class SistemaMovimiento {
    private static SistemaMovimiento instancia = null;

    private int maxx, maxy;

    public static SistemaMovimiento getInstancia(){
        if(instancia == null)
           instancia = new SistemaMovimiento();
        return instancia;
    }

    public void SetCanvasLimit(int width, int height){
        this.maxx = width;
        this.maxy = height;
        System.out.println("w:"+maxx+"h:"+maxy);
    }

    public void Update(Objeto entidad){
        if(entidad.EsMovible()){
            if(entidad.getClass() == (Jugador.class)){
                Jugador player = (Jugador)entidad;
                Vector2D velocidad = new Vector2D(player.getVelocidad());
                velocidad = velocidad.Por(new Vector2D(player.GetControl().vx,player.GetControl().vy));
                entidad.Mover(velocidad);
            }
            else {
                entidad.Mover(entidad.getVelocidad());
            }
        }

        if(entidad.getPosicion().y > maxy){
            entidad.getPosicion().y = maxy;
            Vector2D velocidad = new Vector2D(entidad.getVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }

        if(entidad.getPosicion().y <= 0){
            entidad.getPosicion().y = 0;
            Vector2D velocidad = new Vector2D(entidad.getVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }

        if(entidad.getPosicion().x > maxx + 100){
            entidad.getPosicion().x = maxx;
            Vector2D velocidad = new Vector2D(entidad.getVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }

        if(entidad.getPosicion().x <= 0){
            entidad.getPosicion().x = 0;
            Vector2D velocidad = new Vector2D(entidad.getVelocidad());
            velocidad = velocidad.Por(new Vector2D(0));
            entidad.Mover(velocidad);
        }
        entidad.setPosicion(entidad.getPosicion());
    }
}
//Al millon vato