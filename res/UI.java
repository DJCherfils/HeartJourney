
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font saturno, purisaB;
    BufferedImage heart_full,heart_half,heart_blank,crystal_full,crystal_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0: first screen, 1: second screen, ...
    public int slotCol = 0;
    public int slotRow = 0;


    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/saturno.ttf");
            saturno = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(purisaB);
        g2.setColor(Color.WHITE);

//TITLE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
//PLAY
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
//PAUSE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
//DIALOGUE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;


        x = gp.tileSize/2-5;
        y = (int) (gp.tileSize*1.5);
        i = 0;

        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank,x,y,null);
            i++;
            x += 35;
        }
        x = gp.tileSize/2-5;
        y = (int) (gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full,x,y,null);
            i++;
            x += 35;
        }

    }
    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null) {

                g2.setColor(Color.RED);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);

                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState == 0) {
            g2.setColor(new Color(18, 27, 70));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64));
            String text = "The Trials of Love";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 5 / 2;
            //SHADOW TEXT
            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 5, y + 5);
            //MAIN TEXT
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            x = gp.screenWidth / 2 - gp.tileSize * 3 / 2;
            y += gp.tileSize;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 5 / 2, gp.tileSize * 5 / 2, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "New Game";
            x = getXForCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Load Game";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3 / 2;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Quit";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3 / 2;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }

        else if(titleScreenState == 1){
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "What are you?";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text,x,y);

            text = "Fighter";
            x = getXForCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Farmer";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Sorcerer";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text,x,y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }
    public void drawPauseScreen(){
        g2.setFont(saturno);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 65));
        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen(){
        int x = gp.tileSize;
        int y = gp.tileSize/2;
        int width = gp.screenWidth-(gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }
    public void drawCharacterScreen(){
        //CREATE FRAME FOR STAT SCREEN
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*6;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //DRAW TEXT ON STAT SCREEN
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(25F));

        int textX = frameX + 10;
        int textY = frameY + gp.tileSize;
        final int lineHeight = (int) frameHeight/13;
        //NAMES
        g2.drawString("Level",textX,textY);
        textY += lineHeight;
        g2.drawString("Life",textX,textY);
        textY += lineHeight;
        g2.drawString("Mana",textX,textY);
        textY += lineHeight;
        g2.drawString("Strength",textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY += lineHeight;
        g2.drawString("Attack",textX,textY);
        textY += lineHeight;
        g2.drawString("Defense",textX,textY);
        textY += lineHeight;
        g2.drawString("EXP",textX,textY);
        textY += lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY += lineHeight;
        g2.drawString("Coin",textX,textY);
        textY += lineHeight;
        g2.drawString("Weapon",textX,textY);
        textY += lineHeight;
        g2.drawString("Shield",textX,textY);

    }
    public void drawInventory() {

        //INVENTORY FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //INVENTORY SLOT FRAME
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //FIND CURSOR LOCATION
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        ;
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x,int y,int width,int height){
        Color c = new Color(11, 21, 77, 255);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, gp.tileSize/2,gp.tileSize/2);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, gp.tileSize/2-10,gp.tileSize/2-10);
    }
    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length;
        return x;
    }
}