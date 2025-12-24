# Bacon Number Project

A Java application that calculates “Bacon numbers” for actors: the shortest path connecting each actor to a user-selected root actor through shared movie appearances.

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Input Files
The program currently reads these files from `src/main/java/` (paths are hard-coded in `KevinBacon.java`):
- `test-sample.txt` - List of actor names (one per line)
- `movies.txt` - Movie data in format: `MovieTitle/Actor1/Actor2/Actor3...`

### Usage
1. Run the program
2. Enter an actor's name when prompted (this actor becomes the root node with Bacon number 0)
3. The program will display:
   - Bacon numbers for all actors relative to the selected root actor
   - Bacon number frequency distribution
   - Complete path from each actor to the selected root actor through shared movies

## Assumptions and Design Decisions

### Assumptions
- **User-selected root actor**: Any actor in the database can be selected as the root node (Bacon number 0). Kevin Bacon does not need to exist in the dataset.
- **Data format**: Actor names and movie titles are properly formatted in the input files
- **File location**: Input files must be located at `./src/main/java/test-sample.txt` and `./src/main/java/movies.txt` unless you change the paths in code
- **Case-sensitive names**: Actor name matching is case-sensitive

### Design Decisions

**Graph Representation**:
- Actors are represented as nodes with a list of co-appearances (edges)
- Each `Actor` object maintains a list of other actors they've appeared with in movies
- The `Movie` class links actors who appeared together

**Breadth-First Search (BFS)**:
- Uses BFS algorithm to find shortest paths from any actor to the user-selected root actor
- Guarantees the minimum Bacon number (shortest path)
- Tracks connections to reconstruct the path

**Data Structures**:
- `Actor` class: Stores actor name, co-appearance list, Bacon number, and connection chain
- `Movie` class: Stores movie title and list of actors who appeared in it
- ArrayLists used for dynamic actor and movie lists

**Bacon Number Calculation**:
- Bacon number 0: The selected root actor
- Bacon number 1: Actors who appeared in a movie with the root actor
- Bacon number n: Shortest path requires n steps to reach the root actor
- Bacon number -1 (infinity): Actor has no connection to the root actor

**Error Handling**:
- Validates actor name input with retry mechanism
- Handles file not found exceptions
- Provides user-friendly error messages for invalid input

**Output**:
- Frequency distribution of Bacon numbers across all actors
- Complete path showing movie connections from each actor to the selected root actor
- Clear formatting for readability
