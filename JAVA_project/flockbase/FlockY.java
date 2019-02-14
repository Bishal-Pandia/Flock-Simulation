package flockbase;

import flockbase.Bird;
import flockbase.Flock;
import java.util.ArrayList;

public class FlockY
extends Flock {
    private ArrayList<Bird> birds = new ArrayList<Bird>();
    Bird lead = null;
    public FlockY(){
        super();
    }

    public void addBird(Bird b) {
        this.birds.add(b);
        b.setFlock((Flock)this);
    }

    public void setLeader(Bird leader) {
        if (this.lead != null) {
            this.lead.retireLead();
        }
        this.lead = leader;
        this.lead.becomeLeader();
    }

    public ArrayList<Bird> getBirds() {
        return this.birds;
    }

    public Bird getLeader() {
        return this.lead;
    }

    public Flock split(int pos) {
        Bird birdAtPos=birds.get(pos);
        Flock newFlock=new FlockY();
        newFlock.setLeader(birdAtPos);
        for(int i=pos;i<birds.size();i++){
            newFlock.addBird(birds.get(i));
        }
        for(int i=pos;i<birds.size();i++){
            birds.remove(i);
        }

        return newFlock;
    }

    public void joinFlock(Flock f) {
        this.getLeader().retireLead();
        for(Bird bird:getBirds()){
            f.addBird(bird);
        }
    }
}
