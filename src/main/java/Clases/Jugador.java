package Clases;

public class Jugador extends Objeto2DDinamico {
    private SistemaControl botones;
    private double impulso = 5;
    private int HPmax=100;
    private int HPactual=HPmax;

    private boolean HitState;
    private boolean InicioTimer;

    private long timerStart;
    private long currentTimer;

    public Jugador(String imagen, double posX, double posY){
        super(imagen, posX, posY);
        this.HitState = false;
        this.InicioTimer = false;
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

    public int getHPactual() {
        return HPactual;
    }

    public void setHPactual(int HPactual) {
        this.HPactual = HPactual;
    }

    public void Danio(int dmg){
        if(!HitState){
            this.HPactual -=dmg;
            if(this.HPactual <=0)
                this.HPactual=0;
            this.HitState = true;
        }
    }

    public void Update()
    {
        if(HitState && !InicioTimer){
            timerStart = System.currentTimeMillis();
            InicioTimer = true;
        }
        currentTimer = System.currentTimeMillis() - timerStart;

        if((currentTimer/1000) ==2){
            HitState = false;
            InicioTimer = false;
        }
    }

    public SistemaControl getBotones() {
        return botones;
    }
}
