package Clases;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Superficie {
    /** Imagen, Sprites, Etc.*/
    private BufferedImage imagen;

    public Superficie(){
        this.imagen = null;
    }

    public Superficie(String archivo){
        try{
            this.imagen = ImageIO.read(getClass().getResource(archivo));
        }catch (IOException e){
            System.out.println("Error al cargar imagen");
        }
    }

    public void cargarImagen(String archivo){
        try{
            this.imagen = ImageIO.read(getClass().getResource(archivo));
        }catch (IOException e){
            System.out.println("Error al cargar imagen");
        }
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getImagen(){
        return this.imagen;
    }
}
