package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.InputValidator;

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
            return readPurchaseAmount(); // 재입력
        } catch (Exception e) {
            System.out.println("[ERROR] 올바른 숫자를 입력해야 합니다.");
            return readPurchaseAmount(); // 재입력
        }
    }
}
