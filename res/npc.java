import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class npc extends Entity{

    public npc(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
    }

    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman_right_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
