import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class GamePanel extends JPanel implements Runnable{

    //Screen setting
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 25;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSettler asettler = new AssetSettler(this);
    public Player player = new Player(this, keyH);
    public Entity[] npc = new Entity[10];
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGamethread(){
        gameThread  = new Thread(this);
        gameThread.start();
    }
    public void setupGame(){
        asettler.setNPC();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;



        while(gameThread != null){

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime +=  drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);

        player.draw(g2);


        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }

        g2.dispose();
    }
}
