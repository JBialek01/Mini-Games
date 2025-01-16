package app.guessnumber;

public class WinChecker {

    String message;
//    UserNumberProviderInterface userNumberProvider;
//
//    public WinChecker(UserNumberProviderInterface userNumberProvider) {
//        this.userNumberProvider = userNumberProvider;
//    }
//
//    public UserNumberProviderInterface getUserNumberProvider() {
//        return userNumberProvider;
//    }

    public String checkWin(int winningNumber, int userNumber){
            if (userNumber != 0 && userNumber != -1) {
                if (userNumber == winningNumber) {
                    message = "Gratulacje zgadłeś!";
                    System.out.println(message);
                    //return message;
                } else if (userNumber > winningNumber) {
                    message = "Podana liczba jest za wysoka";
                    System.out.println(message);
                    //return message;
                } else if (userNumber < winningNumber) {
                    message = "Podana liczba jest za niska";
                    System.out.println(message);
                    //return message;
                }
            }
        return message;
    }
}
