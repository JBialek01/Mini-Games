package app.guessnumber;

public class WinChecker {

    int userNumber;
    UserNumberProvider userNumberProvider = new UserNumberProvider();

    public void checkWin(int winningNumber){
        do {
            userNumber = userNumberProvider.returnUserNumber();
            if (userNumber != 0) {
                if (userNumber == winningNumber) {
                    System.out.println("Gratulacje zgadłeś!");
                } else if (userNumber > winningNumber) {
                    System.out.println("Podana liczba jest za wysoka, spróbuj ponownie");
                } else if (userNumber < winningNumber) {
                    System.out.println("Podana liczba jest za niska, spróbuj ponownie");
                }
            }
        } while (userNumber != winningNumber);
    }
}
