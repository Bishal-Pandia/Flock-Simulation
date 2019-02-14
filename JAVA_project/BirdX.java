package sample;

import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;
import java.io.PrintStream;

public class BirdX
extends Bird {
    private int speed = 10;
    private boolean amLeader;

    public BirdX()
    {super();
    }
    public String getName() {
        return "BirdIMT2017010";
    }

    protected void updatePos() {
        double dx;
        double dy;
        Position currPos = this.getPos();
        int x = currPos.getX();
        int y = currPos.getY();
        if (!this.amLeader) {
            Position lpos = this.getFlock().getLeader().getPos();
            this.setTarget(lpos.getX(), lpos.getY());
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
            double m = (float)(yt - y) / (float)(xt - x);
            System.out.println(String.valueOf(xt) + "," + yt + "  " + (Object)currPos + " m = " + m);
            dx = xt > x ? 1.0 : -1.0;
            dy = m * (dx *= (double)this.speed);
        }
        System.out.println("dx dy" + dx + "-" + dy);
        this.setPos(x + (int)dx, y + (int)dy);
    }

    public void becomeLeader() {
        this.amLeader = true;
    }
    
    public void retireLead() {
        this.amLeader = false;
    }
}
