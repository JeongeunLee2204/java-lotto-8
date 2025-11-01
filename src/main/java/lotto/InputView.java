package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.InputValidator;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        return parsePurchaseAmount(input);
    }

    private static int parsePurchaseAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            InputValidator.validatePurchaseAmount(amount);
            return amount;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPurchaseAmount();
        } catch (Exception e) {
            System.out.println("[ERROR] 숫자 형식이 올바르지 않습니다.");
            return readPurchaseAmount();
        }
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        try {
            return parseWinningNumbers(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readWinningNumbers();
        }
    }

    public static int readBonusNumber(List<Integer> winningNumbers) {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        try {
            int number = Integer.parseInt(input);
            if (winningNumbers.contains(number)) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            return number;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBonusNumber(winningNumbers);
        } catch (Exception e) {
            System.out.println("[ERROR] 숫자 형식이 올바르지 않습니다.");
            return readBonusNumber(winningNumbers);
        }
    }

    private static List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(",");
        if (tokens.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        List<Integer> result = new ArrayList<>();
        for (String token : tokens) {
            int num = Integer.parseInt(token.trim());
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (result.contains(num)) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
            }
            result.add(num);
        }
        return result;
    }
}
