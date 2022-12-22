import java.util.Scanner;

import javax.tools.DocumentationTool.Location;

import java.awt.Point;
  
public class EscapeRoom {
  //attributes
  private Boolean exit;
  public String name;
  Scanner user_input = new Scanner(System.in);

  private Player user;
  private static Point userPoint;
  private Point northPoint;
  private Point southPoint;
  private Point eastPoint;
  private Point westPoint;
  private Point centerPoint;


  private Furniture rug;
  private Furniture door;
  private Furniture desk;
  private Furniture window;
  private Furniture bookcase;
  private Furniture computer;
  private Furniture trashCan;
  private Furniture lamp;

  boolean stillPlaying = true;
  String user_response = "";
  private int north, south, east, west;


  /**
  * Constructor of EscapeRoom
  */
  public EscapeRoom(){
    this.name = null;
    this.userPoint = new Point(0, 0);
    this.westPoint = new Point(-3, 0);

    //Creating instance of player in EscapeRoom
    this.user = EscapeRoom.addPlayer(new Player (this.name, this.userPoint)); 

    //Creating instance of all furniture in EscapeRoom
    this.rug = EscapeRoom.addFurniture(new Furniture("rug", new Point(0,0))); 
    this.door = EscapeRoom.addFurniture(new Furniture("door", new Point(0,3)));
    this.desk = EscapeRoom.addFurniture( new Furniture("desk", new Point(-3,0)));
    this.window = EscapeRoom.addFurniture( new Furniture("window", new Point(3,0)));
    this.bookcase = EscapeRoom.addFurniture( new Furniture("bookcase", new Point(0,-3)));
    this.computer = EscapeRoom.addFurniture( new Furniture("computer", new Point(-3,0)));
    this.trashCan = EscapeRoom.addFurniture( new Furniture("trashcan", new Point(-3,0)));
    this.lamp = EscapeRoom.addFurniture( new Furniture("lamp", this.westPoint));

    //Dimensions of the room 
    this.north = 3;
    this.south = -3;
    this.east = 3;
    this.west = -3;
  }


  
  //Manipulator 
  /**
   * Sets the location,used for Point
   * @return sets the users point as the new point
  */
  public static Point setLocation(Point newPoint){
    return userPoint = newPoint;
  }



  /**
   * Creates/prints out the banner displayed at the beginning of the game. Asks for the players name.
   * Greets the player then gives them a description of the escape room, calls gameloop after.
  */
  public void startGame() {
    System.out.println("-----------------------------------");
    System.out.println("         Java Escape Room");
    System.out.println("-----------------------------------");
    System.out.println(" ");
    System.out.println("Welcome to our Escape Room! What is you name?");
    this.name = user_input.nextLine(); 
    System.out.println("Hello, " + name);  
    System.out.println(" ");  
    System.out.println("You are standing in the center of a room above a rug. There is a door to the north. A bookcase to the south. A window to the east. And a desk to the west."); 
    gameLoop();
  }

  /**
   * The game loop for the escape room game. Allows the player to walk, reset the game,
   * inspect items and furniture. Allows the user to grab and use clues. 
   * Sets the stopping conditions for the game
  */
  public void gameLoop() {
    System.out.println(" ");  

    do {
      // System.out.println("The player is at " + this.userPoint);
      String response = user_input.nextLine();  
      if(response.toLowerCase().contains("walk") || response.toLowerCase().contains("go")){
        EscapeRoom.setLocation(user.walk(response));
        // this.userPoint = location;
      }

      if (response.toLowerCase().contains("reset")){
        resetGame(response);
      }

      if(response.toLowerCase().contains("inspect")){
        if (response.toLowerCase().contains("inspect lamp")){
          if (userPoint.equals(westPoint)){
            System.out.println("There is a paper under the lamp");
          }
          else{
            System.out.println("You are not close enough to inspect the lamp");
          }
        } 
        if (response.toLowerCase().contains("inspect computer")){
          if (userPoint.equals(westPoint)){
            System.out.println("There is a login screen");
            System.out.println("Input the username:");
            do{
              if(response.toLowerCase().contains("helloworld")){
                System.out.println("Input the password:");
              }
              if (response.toLowerCase().contains("iguessyoufoundme")){
                System.out.println("You are on the computer.");
              }
              if (response.toLowerCase().contains("insert thumbdrive")){
                System.out.println("The pin is 1202");
              }
            }
            while(response.toLowerCase().contains("helloworld"));
          }
          else{
            System.out.println("You are not close enough to inspect the computer.");
          }
        } 
        if (response.toLowerCase().contains("inspect trashcan")){
          if (userPoint.equals(westPoint)){
            System.out.println("There is a thumbdrive inside the trashcan.");
          }
          else{
            System.out.println("You are not close enough to inspect the trashcan.");
          }
        } 
        if (response.toLowerCase().contains("inspect rug")){
          if (userPoint.equals(centerPoint)){
            System.out.println("There seems to be a loose board under it is a box.");
            if(response.toLowerCase().contains("inspect box")){
              System.out.println("In the box is a key.");
            }
          }
          else{
            System.out.println("You are not close enough to inspect the rug.");
          }
        } 
        if (response.toLowerCase().contains("inspect bookcase")){
          if(userPoint.equals(southPoint)){
            System.out.println("There is a safe on one of the shelves of the bookcase. You need a key to open the safe.");
          }
          else{
            System.out.println("You are not close enough to inspect the bookcase.");
          }
        }
        if (response.toLowerCase().contains("inspect desk")){
          if(userPoint.equals(westPoint)){
            System.out.println("There is a lamp and a computer on the desk. Next to the desk there is a trashcan.");
          }
          else{
            System.out.println("You are not close enough to inspect the desk.");
          }
        }
        if (response.toLowerCase().contains("inspect pin-pad")){
          if(userPoint.equals(northPoint)){
            System.out.println("Insert code:");
          }
          else{
            System.out.println("You are not close enough to inspect the pin-pad.");
          }
        }
        if (response.toLowerCase().contains("inspect window")){
          if(userPoint.equals(eastPoint)){
            System.out.println("You have fallen out of the window!");
            endGame(false);          
          }
          else{
            System.out.println("You are not close enough to inspect the window. Get closer :)");
          }
        }
      }

      if(response.toLowerCase().contains("grab")){
        if (response.toLowerCase().contains("grab paper")){
          user.grab("Paper that has a phrase written on it. The phrase says username: helloworld");
        }
        if (response.toLowerCase().contains("grab thumbdrive")){
          user.grab("Thumb drive has a file on it.");
        }
        if (response.toLowerCase().contains("grab key")){
          user.grab("Key for safe.");
        }
        if (response.toLowerCase().contains("grab post-it")){
          user.grab("Post-it that has a phrase written on it. The phrase says password: iguessyoufoundme");
        }
      }

      if(response.toLowerCase().contains("use")){
        if (response.toLowerCase().contains("use thumbdrive")){
          user.use("Thumb drive has a file on it. On the file is a pin ");
        }
        if (response.toLowerCase().contains("use key")){
          user.use("Key for safe.");
          System.out.println("There is a post-it in the safe.");
  
        }
        if (response.toLowerCase().contains("use pin-pad")){
          if(userPoint.equals(northPoint)){
            System.out.println("Insert code:");
            if(response.toLowerCase().contains("1202")){
              endGame(true);
            }
            else{
              System.out.println("INCORRECT");
            }
          }   
        }
        else{
          System.out.println("You are not close enough to use the pin-pad.");
        }
      }

      //Stopping condition for game loop 
      if (response.toLowerCase().contains("END GAME") || response.toLowerCase().contains("LOSE")){
        stillPlaying = false;
        endGame(stillPlaying);
      }
    } while (stillPlaying);
  }


  /**
   * Adds player into the room. Contructs a player (calls on the constructor for player).
   * @param Player  new instance of player 
   * @return the user
  */
  private static Player addPlayer(Player p) {
    System.out.println("Adding player...");
    Player user = p;
    p.getName();
    return user; 
  }


  /**
   * Adds furniture to the room. Constructs furniture (calls on the constructor for furniture).
   * @param Furniture new instance of furniture 
   * @return the furniture 
  */
  private static Furniture addFurniture(Furniture f) {
    System.out.println("Adding furniture...");
    Furniture rug = f;
    f.getName();
    return f; 
  }


  /**
   * Ends the game. Tells the player whether they have escaped successfully or if they failed.
   * Closes the scanner.
   * @param boolean Tells whether the player has exited successfully  
  */
  public void endGame(boolean exit) {
    if (exit = true){
      user_input.close();
      System.out.println("You have successfully escaped!"); 
      System.out.println("GAME OVER");
    }
    if (exit = false){
      user_input.close();
      System.out.println("You have failed to escape :("); 
      System.out.println("GAME OVER");
    }
  }


 /**
   * Resets the name when the user inputs "reset"
   * @param String the user input/response
  */ 
  public void resetGame(String user_input) {
    if (user_input.contains("reset")) {
      EscapeRoom room = new EscapeRoom();
      room.startGame();
      user.undo();
    }
  }

  
  /* main method (for testing) */
  public static void main(String[] args) {
    EscapeRoom room = new EscapeRoom();

    
    room.startGame();

  }
}

