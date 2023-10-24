package baseball;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class BaseballGame {
    private List<Integer> computerNumberList;
    private List<Integer> userNumberList;

    public void start() {
        showStart();
        boolean playAgain = true;
        while (playAgain) {
            Computer computer = new Computer();
            computerNumberList = computer.generateComputerNumber();
            showInputUserNumber();
            userNumberList = getUsetNumberList();
            CompareNumber compareNumber = new CompareNumber();
            int[] compareResult = compareNumber.getCompareResult(userNumberList, computerNumberList);
            showCount(compareResult);
            if (compareNumber.isStrikeThree()) {
                isMatchShowFinish(compareResult);
                playAgain = retry();
            }
        }
    }


    private static void showStart() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    private static void showInputUserNumber() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    private static void showAskRetry() {
        System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    private static boolean retry() {
        showAskRetry();
        int inputNum = Integer.parseInt(Console.readLine());
        if (inputNum == 1) {
            return true;
        }
        return false;
    }

    private static List<Integer> getUsetNumberList() {
        List<Integer> userNumberList = new ArrayList<>();
        User user = new User();
        String userNumberStr = new String();
        user.inputUserNumber(userNumberStr);
        userNumberList = user.convertInputToList();

        return userNumberList;
    }

    private static void showCount(int[] compareResult) {
        showBallCount(compareResult);
        showStrikeCount(compareResult);
        showNothing(compareResult);
    }

    private static void showBallCount(int[] compareResult) {
        if (compareResult[0] != 0) {
            if (compareResult[1] != 0) {
                System.out.println(compareResult[0] + "볼 ");
            } else {
                System.out.println(compareResult[0] + "볼");
            }
        }
    }

    private static void showStrikeCount(int[] compareResult) {
        if (compareResult[1] != 0) {
            System.out.println(compareResult[1] + "스트라이크");
        }
    }

    private static void showNothing(int[] compareResult) {
        if (compareResult[0] == 0 && compareResult[1] == 0) {
            System.out.println("낫싱");
        }
    }

    private static void isMatchShowFinish(int[] compareResult) {
        if (compareResult[1] == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        }
    }
}
