package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.Rank;

public class LottoResult {

    private final List<Lotto> lottos;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoResult(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public void printResult(int purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        Map<Rank, Integer> rankCounts = countRanks();

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) continue;
            System.out.println(rank.getMessage() + " - " + rankCounts.getOrDefault(rank, 0) + "개");
        }

        printProfitRate(rankCounts, purchaseAmount);
    }

    private Map<Rank, Integer> countRanks() {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto);
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
        }
        return rankCounts;
    }

    private Rank determineRank(Lotto lotto) {
        int matchCount = countMatches(lotto);
        boolean hasBonus = lotto.getNumbers().contains(bonusNumber);
        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatches(Lotto lotto) {
        int count = 0;
        for (int num : lotto.getNumbers()) {
            if (winningNumbers.contains(num)) count++;
        }
        return count;
    }

    private void printProfitRate(Map<Rank, Integer> rankCounts, int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : rankCounts.keySet()) {
            totalPrize += (long) rank.getPrize() * rankCounts.get(rank);
        }

        double profitRate = ((double) totalPrize / purchaseAmount) * 100;
        double rounded = Math.round(profitRate * 10) / 10.0;

        System.out.println("총 수익률은 " + rounded + "%입니다.");
    }
}
