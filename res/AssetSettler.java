
public class AssetSettler {

    GamePanel gp;

    public AssetSettler(GamePanel gp){
        this.gp = gp;
    }

    public void setObjects(){
        gp.obj[0] = new OBJ_Heart();
    }

    public void setNPC(){
        gp.npc[0] = new npc(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

}
