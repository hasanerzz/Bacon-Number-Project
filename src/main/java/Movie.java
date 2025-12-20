import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    private String movieTitle;
    private List<Actor> actors = new ArrayList<>();

    public Movie(){

    }
    public Movie(String movieTitle, List<Actor> actors){
        this.actors = actors;
        this.movieTitle = movieTitle;
    }
    public void addActor(Actor actor){
        this.actors.add(actor);
    }
    public List<Actor> getActors() {
        return actors;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieName(String movieName) {
        this.movieTitle = movieName;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
    public void setMovieTitleAndActors(String str, List<Actor> actorList){
        int index = str.indexOf("/");
        this.movieTitle = str.substring(0, index);
        String[] actorsInMovie = str.substring(index).split("/");
        List<String> actorNames = Arrays.stream(actorsInMovie).toList();
        List<String> existingActorNames = new ArrayList<>();
        for (Actor actor : actorList) {
            existingActorNames.add(actor.getFirstName());
        }
        for(String name: actorNames) {
            if (existingActorNames.contains(name)) {
                this.actors.add(actorList.stream().
                        filter(actor -> actor.getFirstName().equals(name)).
                        findFirst().orElse(null));
            } else {
                if(!(name.isEmpty())){
                    this.actors.add(new Actor(name, new ArrayList<>()));
                }
            }
        }
    }
    public void setCoappearances(List<Actor> actorList){
        for(Actor actor: actorList){
            if(this.actors.contains(actor)){
                actor.addCoappearenceActorList(this.actors);
            }
        }
    }
}
