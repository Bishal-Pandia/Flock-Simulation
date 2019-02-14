package flockbase;
import java.util.ArrayList;
import flockbase.Bird;
import flockbase.Flock;
import flockbase.FlockY;
import flockbase.Position;
import java.io.PrintStream;
import java.lang.Math;

public class BirdX
extends Bird {
    private int speed = 10;
    private boolean amLeader;

    public BirdX()
    {
        super();
    }
    public String getName() {
        if(!amLeader){
            return "Bishal";
        }
        else{
            return "Bishal(L)";
        }
    }
    // int count=0;
    protected void updatePos() {
        // count+=1;
        double dx;
        double dy;
        Position currPos = this.getPos();
        int x = currPos.getX();
        int y = currPos.getY();
        if (!this.amLeader) {
            Position leaderpos = this.getFlock().getLeader().getPos();
            this.setTarget(leaderpos.getX(), leaderpos.getY());
        }
        int xt = this.getTarget().getX();
        int yt = this.getTarget().getY();
        if (xt == x && yt == y) {
            dx = 0.0;
            dy = 0.0;
        } else if (xt == x) {
            dy = yt > y ? 1.0 : -1.0;
            dx = 0.0;
        } else if (yt == y) {
            dx = xt > x ? 1.0 : -1.0;
            dy = 0.0;
        } else {
            double modulus =((xt-x)*(xt-x)) + ((yt-y)*(yt-y));
            double unitm = ((float)(yt - y) / (float)(xt - x))/(Math.sqrt(modulus));
            dx = xt > x ? 1.0:-1.0;
            dy = unitm*(dx *= (double)this.speed);
        }
        System.out.println("dx dy" + dx + "-" + dy);
        ArrayList<Bird> temp =getFlock().getBirds();
        for(Bird bird: temp){
            System.out.println(bird.getPos());
            if(bird==this){
                continue;
            }
            // if(this== this.getFlock().getLeader()){
            //     this.setPos(x +(int)dx, y+ (int)dy);
            // }
            else if((bird.getPos().getX() >= x-5+ (int)dx) && (bird.getPos().getY() >= y-5+(int)dy) && (bird.getPos().getX() <= x+5 + (int)dx) && (bird.getPos().getY() <= y+5+(int)dy) ){
                this.setPos(x , y);
            }
            else{
                this.setPos(x + (int)dx, y + (int)dy);
            }
        }
            
    
        // this.setPos(x + (int)dx, y + (int)dy);
    }

    public void becomeLeader() {
        this.amLeader = true;
    }
    
    public void retireLead() {
        this.amLeader = false;
    }
}
