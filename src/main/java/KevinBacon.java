import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KevinBacon {
    public static void main(String[] args) {
        Map<Actor, List<Actor>> coappearancesMapping = new HashMap<>();
        List<Actor> actorList = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>();


        try{
            Scanner scan = new Scanner(new File("./src/main/resources/test-sample.txt"));
            while(scan.hasNextLine()) {
                Actor actor = new Actor(scan.nextLine(), new ArrayList<>());
                actorList.add(actor);
            }
            scan = new Scanner(new File("./src/main/resources/movies.txt"));
            while(scan.hasNextLine()){
                String movieTitleAndActors = scan.nextLine();
                Movie movie = new Movie();
                movie.setMovieTitleAndActors(movieTitleAndActors, actorList);
                movieList.add(movie);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Movie movie : movieList) {
            movie.setCoappearances(actorList);
        }
        coappearancesMapping = setMappings(actorList);
        findBaconNumber(actorList.get(0), actorList, coappearancesMapping);
        System.out.println(actorList.get(0).getBaconNumber() + " " + actorList.get(1).getBaconNumber());


    }
    public static void findBaconNumber(Actor selectedActor, List<Actor> actorList, Map<Actor, List<Actor>> coappMapping){

    }
    public static void findBaconNumberHelper(List<Actor> coActorList, List<Actor> allActorList, int baconNumber){
        for(Actor coActor: coActorList){
            if(!coActor.hasBaconNumber()){
                coActor.setBaconNumber(baconNumber);
                coActor.setHasBaconNumber(true);
         }
        }
        baconNumber++;
        for(Actor coActor: coActorList) {
            findBaconNumberHelper(coActor.getCoappearenceList(), allActorList, baconNumber);
        }
    }
    public static Map<Actor, List<Actor>> setMappings(List<Actor> actorList){
        Map<Actor, List<Actor>> coappearanceMap = new HashMap<>();
        for(Actor actor: actorList){
            coappearanceMap.put(actor, actor.getCoappearenceList());
        }
        return coappearanceMap;
    }
    }

