import java.util.List;

public class Actor {
    private String firstName;
    private List<Actor> coappearenceList;
    private int baconNumber;
    private boolean hasBaconNumber;

    public Actor(){

    }
    public Actor(String firstName, List<Actor> coappearenceList){
        this.firstName = firstName;
        this.coappearenceList = coappearenceList;
    }

    public int getBaconNumber() {
        return baconNumber;
    }

    public void setBaconNumber(int baconNumber) {
        if(!hasBaconNumber) {
            this.baconNumber = baconNumber;
        }
    }

    public List<Actor> getCoappearenceList() {
        return coappearenceList;
    }

    public boolean hasBaconNumber() {
        return hasBaconNumber;
    }

    public void setHasBaconNumber(boolean hasBaconNumber) {
        this.hasBaconNumber = hasBaconNumber;
    }

    public void setCoappearenceList(List<Actor> coappearenceList) {
        this.coappearenceList = coappearenceList;
    }
    public void addCoappearenceActor(Actor actor){
        this.coappearenceList.add(actor);
    }
    public void addCoappearenceActorList(List<Actor> actorList){
        for(int i = 0 ; i < actorList.size(); i++){
            if(this == actorList.get(i)){
                continue;
            }
            this.coappearenceList.add(actorList.get(i));
        }

    }
    public String getFirstName() {
        return firstName;
    }
}
