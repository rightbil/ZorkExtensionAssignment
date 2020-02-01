package com.CodeSea;

import java.lang.reflect.Method;
import java.util.*;

public class ZorkExtension {

    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String quitMessage = ANSI_RED + "Sorry to see you leave, have a nice day" + ANSI_RESET;
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static StringBuilder roomContents = new StringBuilder(" room.\nContents in this castle room : ");
    public static String introPhrase = "Now, you are in the ";
    public static Set<String> nextDirections = new LinkedHashSet<>(); // Order is important
    public static Map<String, Set<String>> rooms = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    public static String userInput = null;
    public static String quit = "C";
    public static Set<String> visitedRooms = new LinkedHashSet<>();
    public static String visitorName;
    public static Date visitDate;
    public static Map<String, Double> roomRating = new HashMap<>();
    public static boolean isSecretRoomVisited = false;

    public static double totalCashAccumulated = 0.0;

    public static String charactorPerson = null;

    public static void print(Object msgToUser) {
        System.out.println(msgToUser);
    }

    public static void managePrize(double currentPrize, String methodName) {

        if (!charactorPerson.equalsIgnoreCase(methodName)) {
            totalCashAccumulated += generatePrize();
        } else {
            totalCashAccumulated = 0.0;
        }

    }

    //TODO: Foyer #1
    public static void foyerRoom() {
        managePrize(generatePrize(), "foyerRoom");
        print(introPhrase + "Foyer" + roomContents + " dead scorpion");
        print(availableDirections("Foyer"));
        addToVisitedBucket("Foyer");
        while (!quit.equalsIgnoreCase("Q")) {

            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;
            }// end of if
            else {
                switch (userInput.toUpperCase()) {
                    case "N":
                        System.out.printf("would you rate the room ? ");
                        setRating("Foyer", sc.nextDouble());
                        frontRoom();
                        quit = "Q";
                        break;
                    default:
                        //TODO: System.out.println(ANSI_CLS + ANSI_HOME);
                        //TODO: System.out.flush();
                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        foyerRoom();

                }
            }
        }

    }

    //TODO: FrontRoom #2
    public static void frontRoom() {
        print(introPhrase + "Front" + roomContents + " dead scorpion");
        print(availableDirections("Front"));
        addToVisitedBucket("Front Room");
        while (!quit.equalsIgnoreCase("q")) {

            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {

                if (!isSecretRoomVisited) {
                    print(quitMessage);//"Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            } else {
                switch (userInput.toUpperCase()) {
                    case "S":
                        System.out.printf("would you rate the room ? ");
                        setRating("Front", sc.nextDouble());
                        foyerRoom();
                        quit = "Q";
                        break;
                    case "E":
                        System.out.printf("would you rate the room ? ");
                        setRating("Front", sc.nextDouble());
                        kitchenRoom();
                        quit = "Q";
                        break;
                    case "W":
                        System.out.printf("would you rate the room ? ");
                        setRating("Front", sc.nextDouble());
                        libraryRoom();
                        quit = "Q";
                        break;
                    default:
                        //TODO: System.out.println(ANSI_CLS + ANSI_HOME);
                        //TODO: System.out.flush();
                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        frontRoom();

                }
            }
        }
    }

    //TODO: Library #3
    public static void libraryRoom() {
        print(introPhrase + "Library" + roomContents + " dead scorpion");
        print(availableDirections("LibraryRoom"));
        while (!quit.equalsIgnoreCase("q")) {

            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            } else {
                switch (userInput.toUpperCase()) {
                    case "E":
                        System.out.printf("would you rate the room ? ");
                        setRating("Library", sc.nextDouble());
                        frontRoom();
                        quit = "Q";
                        break;
                    case "N":
                        System.out.printf("would you rate the room ? ");
                        setRating("Library", sc.nextDouble());
                        diningRoom();
                        quit = "Q";
                        break;
                    default:
                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        libraryRoom();

                }
            }
        }
    }

    //TODO: Kitchen #4
    public static void kitchenRoom() {

        print(introPhrase + "Kitchen" + roomContents + " bats");
        print(availableDirections("KitchenRoom"));
        while (!quit.equalsIgnoreCase("q")) {

            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            }
            else {
                switch (userInput.toUpperCase()) {
                    case "N":
                        System.out.printf("would you rate the room ? ");
                        setRating("Kitchen", sc.nextDouble());
                        parlorRoom();
                        quit = "Q";
                        break;
                    case "E":
                        System.out.printf("would you rate the room ? ");
                        setRating("Kitchen", sc.nextDouble());
                        frontRoom();
                        quit = "Q";
                        break;
                    default:
                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        kitchenRoom();

                }
            }
        }
    }

    //TODO: Dining Room #5
    public static void diningRoom() {
        print(introPhrase + "Dining" + roomContents + " dust empty box");
        print(availableDirections("Dining"));
        while (!quit.equalsIgnoreCase("q")) {
            //System.out.println(ANSI_CLS);
            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            }// end of if
            else {
                switch (userInput.toUpperCase()) {
                    case "S":
                        System.out.printf("would you rate the room ? ");
                        setRating("Dining", sc.nextDouble());
                        libraryRoom();
                        quit = "Q";
                        break;
                    default:

                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        diningRoom();

                }
            }
        }
    }

    //TODO: Valute #6
    public static void valuteRoom() {
        print(introPhrase + "Valute" + roomContents + " 3 walking skeletorn");
        print(availableDirections("Valute"));
        while (!quit.equalsIgnoreCase("q")) {
            //System.out.println(ANSI_CLS);
            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            }// end of if
            else {
                switch (userInput.toUpperCase()) {

                    case "E":
                        if (isSecretRoomVisited == false) {
                            if (randomKeyForSecret() == 1) { // 25 percent chance
                                System.out.printf("would you rate the room ? ");
                                setRating("Valute", sc.nextDouble());
                                secretRoom();
                            } else { // 75 percent chance
                                System.out.printf("would you rate the room ? ");
                                setRating("Valute", sc.nextDouble());
                                parlorRoom();
                            }
                        } else {

                            if (randomKeyForSecret() % 2 == 0) // 50 / 50 % chance for both
                            {
                                System.out.printf("would you rate the room ? ");
                                setRating("Valute", sc.nextDouble());
                                secretRoom();
                            } else {
                                System.out.printf("would you rate the room ? ");
                                setRating("Valute", sc.nextDouble());

                                parlorRoom();
                            }
                        }
                        quit = "Q";
                        break;
                    default:

                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        valuteRoom();

                }
            }
        }
    }

    //TODO: Parlor #7
    public static void parlorRoom() {

        print(introPhrase + "Parlor" + roomContents + " treasure chest");
        print(availableDirections("Parlor"));
        while (!quit.equalsIgnoreCase("q")) {
            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {

                if (!isSecretRoomVisited) {
                    print("Sorry to see you leave, have a nice day");
                }
                listVisitedBucket();
                quit = "Q";
                break;

            }
            else {
                switch (userInput.toUpperCase()) {
                    case "W":
                        System.out.printf("would you rate the room ? ");
                        setRating("Parlor", sc.nextDouble());
                        valuteRoom();
                        quit = "Q";
                        break;
                    case "S":
                        System.out.printf("would you rate the room ? ");
                        setRating("Parlor", sc.nextDouble());
                        kitchenRoom();
                        quit = "Q";
                        break;
                    default:

                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        parlorRoom();

                }
            }
        }
    }

    //TODO: SecretRoom #8
    public static void secretRoom() {
        isSecretRoomVisited = true;
        print(introPhrase + "Secret" + roomContents + " piles of gold");
        print(availableDirections("Secret"));
        while (!quit.equalsIgnoreCase("q")) {

            print("Enter directions or press Q to quit");
            userInput = sc.next();
            if (userInput.equalsIgnoreCase("Q")) {
                // user wanted to quit
                print("you have finished visiting the rooms.");
                listVisitedBucket();
                quit = "Q";
                break;

            } else {
                switch (userInput.toUpperCase()) {
                    case "W":
                        System.out.printf("would you rate the room ? ");
                        setRating("Secret", sc.nextDouble());
                        valuteRoom();
                        quit = "Q";
                        break;
                    default:

                        print("'" + userInput + "'" + " is not a valid direction , please try again.");
                        secretRoom();

                }
            }
        }
    }

    public static int randomKeyForSecret() {

        return new Random().nextInt(4) + 1;
    }

    public static boolean isFollowedByGhost() {

        return randomKeyForSecret() < 2;
    }

    public static void loadCastle() {
        // position the charactor to random room
        setCharactorPosition();
        // load all the rooms with names and directions
        //room 1
        nextDirections = new LinkedHashSet<>();
        nextDirections.add("N");

        // map contains possible directions for each room
        rooms = new HashMap<>();
        rooms.put("Foyer", nextDirections);

        //room 2
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("S", "W", "E")
        );
        rooms.put("Front", nextDirections);

        //room 3
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("E", "N")
        );
        rooms.put("Library", nextDirections);

        // room 4
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("W", "N")
        );
        rooms.put("Kitchen", nextDirections);

        // room 5
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("S")
        );
        rooms.put("Dining", nextDirections);

        // room 6
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("E")  // E8 has 25 % chance to be find.
        );
        rooms.put("Valute", nextDirections);

        // room 7
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("W", "S")
        );
        rooms.put("Parlor", nextDirections);

        // room 8
        nextDirections = new LinkedHashSet<>(
                Arrays.asList("W")
        );
        rooms.put("Secret", nextDirections);


    }

    public static String availableDirections(String roomName) {
        String result = null;

        for (Map.Entry<String, Set<String>> outer : rooms.entrySet()) {
            if (outer.getKey().equalsIgnoreCase(roomName)) {
                result = outer.getValue().toString();
            }

        }

        return "Next,possible room to visit : " + result;
    }

    public static String addToVisitedBucket(String visited) {
        //visitedRooms = new HashSet<>();
        visitedRooms.add(visited);
        return visitedRooms.toString();
    }

    public static String listVisitedBucket() {
        return visitedRooms.toString();
    }

    public static String visitSummary() {
        String visitSummary = visitDate + visitorName
                + " , you visited " + visitedRooms.size() + " rooms : " + listVisitedBucket()
                + " over all rate was " + yourRating() + " and your total cash prize was $" + totalCashAccumulated;
        if (isFollowedByGhost()) {
            visitSummary += " just reminder a ghost is following you.";
        }

        return visitSummary;
    }

    public static void setRating(String room, double rate) {
        roomRating.put(room, rate);
    }

    public static double yourRating() {
        double sum = 0.0;
        double rate;
        for (Map.Entry<String, Double> str : roomRating.entrySet()) {
            sum = sum + str.getValue();
        }

        rate = sum / roomRating.size();
        return rate;
    }

    public static double generatePrize() {

        return new Random(0).nextInt(1000);

    }

    public static void setCharactorPosition() {

        ArrayList<String> allRooms = new ArrayList<>();
        //allRooms.addAll(rooms.keySet());
        Class c = ZorkExtension.class;
        Method[] m = c.getDeclaredMethods(); // getMethod gives including Object method but this gives only user defined and main.
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().contains("Room")) {
                allRooms.add(m[i].getName());
            }
        }

        charactorPerson = allRooms.get(new Random().nextInt(allRooms.size()));

    }

    public static void main(String[] args) {
//TODO: Requirements
/*Ask   users to enter the direction where they want to go {N,S,E,W}
        Show the content in the room and give them what possible direction the can go from there
        Users can move back and forth
        Your program should allow the user to find the secret room only 25% of the time.
        However, once they find the secret room they can always find it.
        When the user exits the house or quits there is a 25% chance they will be followed by a ghost. Let them know when they are being followed.
        Also let the user know how many rooms they visited after they exit or quit.
       */

        loadCastle();// load rooms of the castle
        System.out.println("Visitor name , please");
        visitorName = "\n Dear " + sc.next();
        visitDate = new Date();
        foyerRoom();
        print(visitSummary());
    }
}

