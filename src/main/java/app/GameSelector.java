package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class GameSelector {

    String message;
    List<Game> games;
    Scanner scanner;

    public GameSelector(List<Game> games, Scanner scanner) {
        this.games = games;
        this.scanner = scanner;
    }

    public int selectGame(){
        if(games.isEmpty()){
            return -1;
        }

//    Set<Game> game = new HashSet<>();
//    game.add(new LotekGame(new LotekUserNumbersProvider(), new LotekRandomNumbersProvider()));
//    game.add(new GuessNumber(new WinningNumberProvider(), new WinChecker(new UserNumberProvider())));
    int gamePicker = 99;
        while (gamePicker != 0) {
            System.out.println("\nWybierz grę:");
            AtomicInteger i = new AtomicInteger(1);
            games.forEach(game -> System.out.println(i.getAndIncrement() + ". - " + game.getName()));
//            System.out.println("1. - Lotek");
//            System.out.println("2. - GuessNumber");
            System.out.println("0. - Zakończ program");
            try {
                gamePicker = scanner.nextInt();
            } catch (InputMismatchException e) {
                message = "Podana wartość nie jest liczbą całkowitą";
                System.err.println(message);
                scanner.nextLine();
            }
            if(gamePicker == 0){
                return gamePicker;
            }
//            if(gamePicker > games.size()){
//                return gamePicker;
//            }
            Game game = games.get(gamePicker - 1);
            System.out.println("wybrales gre: " + game.getName());
            game.startGame();
//            switch (gamePicker) {
//                case 0: {
//                    break;
//                }
//                case 1: {
////                    message = "Wybrałeś grę Lotek";
//                    Game game = games.get(gamePicker);
//
//                    System.out.println("wybrales gre: " + game.getName());
//                    game.startGame();
//                    break;
//                }
//                case 2: {
////                    message = "Wybrałeś grę GuessNumber";
////                    System.out.println(message);
//                    Game game = games.get(gamePicker);
//                    System.out.println("wybrales gre: " + game.getName());
////                    guessNumber.startGame();
//                    break;
//                }
//                default: {
//                    message = "Wprowadź poprawny numer gry";
//                    System.out.println(message);
//                }
//            }
        }
        return gamePicker;
    }
}
