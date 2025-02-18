package pl.games.app.console;

import java.util.Set;

class ConsoleResultShower {

    String showResultConsole(Set<Integer> userNumbers, Set<Integer> winningNumbers, String checkResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("Twoje liczby:\n");
        for (int userNumber : userNumbers) {
            sb.append("[").append(userNumber).append("] ");
        }
        sb.append("\nZwycięskie liczby:\n");
        for (int winningNumber : winningNumbers) {
            sb.append("[").append(winningNumber).append("] ");
        }
        sb.append("\n").append(checkResult);
        return sb.toString();
    }
}
