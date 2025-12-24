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
        System.out.println("Enter an actor to find his/her Bacon number: ");

        Scanner inputScan = new Scanner(System.in);
        String actorName = inputScan.nextLine();
        Actor selectedActor = findActorByName(actorName, actorList);
        findBaconNumber(selectedActor);
        findInfBaconNumber(selectedActor, actorList);
        printBaconNumFreq(actorList);
        System.out.println();
        for(Actor actor: actorList) {
            System.out.println(printBaconPath(actor, movieList));
        }



    }
    public static void printBaconNumFreq(List<Actor> actorList){
        System.out.println("Bacon Number\t\t\tFrequency\n-----------------------");
        int count[] = new int[10];
        for(Actor actor: actorList) {
            if (actor.getBaconNumber() == -1) {
                count[9]++;
            } else {
                count[actor.getBaconNumber()]++;
            }
        }
        for(int i = 0; i < count.length ; i++) {
            System.out.println(i + "\t\t\t" + count[i]);
            if(i == 9){
                System.out.println("infinity\t\t\t" + count[i]);
            }
        }


    }
    public static String printBaconPath(Actor selectedActor, List<Movie> movieList){
        Actor current = selectedActor;
        StringBuilder sb = new StringBuilder(selectedActor.getFirstName() + " has a Bacon number of "+ selectedActor.getBaconNumber() + "\n");

        while(current != null){

            String commonMovie = findCommonMovieTitle(current, current.getBaconConnection(), movieList);
            if(current.getBaconConnection() != null){
                sb.append(current.getFirstName()).append(" was in " + commonMovie + " with ").append(current.getBaconConnection().getFirstName()).append("\n");
            }

            current = current.getBaconConnection();
        }
        return sb.toString();
    }
    public static Actor findActorByName(String actorName, List<Actor> actorList){
        for(Actor actor: actorList){
            if(actorName.equals(actor.getFirstName())){
                return actor;
            }
        }
        return null;
    }
    public static String findCommonMovieTitle(Actor actor1, Actor actor2, List<Movie> movieList){
        for(Movie movie: movieList){
            if(movie.getActors().contains(actor1) && movie.getActors().contains(actor2))
                return movie.getMovieTitle();
        }
        return null;
    }
    public static void findBaconNumber(Actor selectedActor){
        Queue<Actor> actorQueue = new LinkedList<>();

        selectedActor.setBaconNumber(0);
        selectedActor.setHasBaconNumber(true);
        actorQueue.add(selectedActor);

        System.out.println("Traversing the nodes are started.");

        while(!actorQueue.isEmpty()){
            Actor currentActor = actorQueue.poll();
            for(Actor actor: currentActor.getCoappearenceList()){
                if(!actor.hasBaconNumber()){
                    actor.setBaconNumber(currentActor.getBaconNumber()+1);
                    actor.setHasBaconNumber(true);
                    actorQueue.add(actor);
                    actor.setBaconConnection(currentActor);
                }
            }
        }


    }
    public static void findInfBaconNumber(Actor selectedActor, List<Actor> actorList){
        for(Actor actor: actorList){
            if(actor.getBaconNumber() == 0 && !actor.getFirstName().equals(selectedActor.getFirstName())){
                actor.setBaconNumber(-1);
                actor.setHasBaconNumber(true);
            }

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

