package lotto;

import lotto.view.InputView;
import java.util.List;
import lotto.Rank;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int purchaseAmount = lotto.view.InputView.readPurchaseAmount();
        System.out.println(purchaseAmount + "원 입력됨 (테스트용)");

        int count = purchaseAmount / 1000;
        System.out.println(count + "개를 구매했습니다.");

        List<Lotto> lottos = LottoMachine.issueLottos(count);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }

        List<Integer> winningNumbers = lotto.view.InputView.readWinningNumbers();
        int bonusNumber = lotto.view.InputView.readBonusNumber(winningNumbers);

        LottoResult result = new LottoResult(lottos, winningNumbers, bonusNumber);
        result.printResult();
    }
}
