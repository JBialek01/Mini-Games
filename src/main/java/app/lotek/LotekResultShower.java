package app.lotek;

import java.util.Set;

public class LotekResultShower {

    private final LotekWinChecker lotekWinChecker = new LotekWinChecker(new LotekUserNumbersProvider());

    public String showResult(Set<Integer> userNumbers,
                             Set<Integer> winningNumbers,
                             boolean isHtml) {
        if (isHtml) {
            return showResultHtml(userNumbers, winningNumbers);
        } else {
            return showResultConsole(userNumbers, winningNumbers);
        }
    }

    private String showResultConsole(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Twoje liczby:\n");
        for (int userNumber : userNumbers) {
            sb.append("[").append(userNumber).append("] ");
        }
        sb.append("\nZwycięskie liczby:\n");
        for (int winningNumber : winningNumbers) {
            sb.append("[").append(winningNumber).append("] ");
        }
        String checkResult = lotekWinChecker.checkResults(userNumbers, winningNumbers);
        sb.append("\n").append(checkResult);
        return sb.toString();
    }

    private String showResultHtml(Set<Integer> userNumbers, Set<Integer> winningNumbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Twoje liczby:</h2><p>");
        for (int userNumber : userNumbers) {
            sb.append("[").append(userNumber).append("] ");
        }
        sb.append("</p>");
        sb.append("<h2>Zwycięskie liczby:</h2><p>");
        for (int winningNumber : winningNumbers) {
            sb.append("[").append(winningNumber).append("] ");
        }
        sb.append("</p>");
        String checkResult = lotekWinChecker.checkResults(userNumbers, winningNumbers);
        checkResult = checkResult.replace("\n", "<br>");
        sb.append("<p>").append(checkResult).append("</p>");
        return sb.toString();
    }
}
