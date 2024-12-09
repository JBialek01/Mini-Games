package app.guessnumber;

public class WinChecker {

    String message;
    int userNumber;
    UserNumberProvider userNumberProvider;

    public WinChecker(UserNumberProvider userNumberProvider) {
        this.userNumberProvider = userNumberProvider;
    }

    public String checkWin(int winningNumber){
        do {
            userNumber = userNumberProvider.returnUserNumber();
            if (userNumber != 0 && userNumber != -1) {
                if (userNumber == winningNumber) {
                    message = "Gratulacje zgadłeś!";
                    System.out.println(message);
                    return message;
                } else if (userNumber > winningNumber) {
                    message = "Podana liczba jest za wysoka";
                    System.out.println(message);
                } else if (userNumber < winningNumber) {
                    message = "Podana liczba jest za niska";
                    System.out.println(message);
                }
            }
        } while (userNumber != winningNumber && userNumber != -1);
        message += ", wyszedłeś z gry";
        return message;
    }
}
