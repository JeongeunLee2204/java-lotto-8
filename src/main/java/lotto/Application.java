package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int purchaseAmount = lotto.view.InputView.readPurchaseAmount();
        System.out.println(purchaseAmount + "원 입력됨 (테스트용)");
    }
}
