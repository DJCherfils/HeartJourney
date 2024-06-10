import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 12;
        worldY = 240;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 7.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 8.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 2.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 5.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Basic Charakter Spritesheet copy 6.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
                worldY -= speed;
            }
            else if(keyH.downPressed){
                direction = "down";
                worldY += speed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                worldX -= speed;
            }
            else if(keyH.rightPressed){
                direction = "right";
                worldX += speed;
            }

            boolean collisionOn = false;
            gp.cChecker.checkTile(this);

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch(direction){
            case"up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
