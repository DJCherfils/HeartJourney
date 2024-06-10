import java.io.IOException;
import javax.imageio.ImageIO;
public class OBJ_Heart extends SuperObject{
    public OBJ_Heart(){
        name = "heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/heart-removebg-preview.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
