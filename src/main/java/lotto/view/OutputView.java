package lotto.view;

import lotto.dto.RevenueDto;
import lotto.dto.PurchaseLottoDto;
import lotto.dto.WinningResponseDto;
import lotto.dto.WinningTierResponseDto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class OutputView {


    private static final String OUTPUT_ISSUE_ABLE_COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String COUNT_UNIT = "개";
    private static final String OUTPUT_RATE_OF_REVENUE_MESSAGE = "총 수익률은 %,.1f%%입니다.";
    private static final String OUTPUT_WINNING_STATICS_MESSAGE = "\n당첨 통계\n---";
    private static final int DEFAULT_VALUE = 0;
    private static final String WINNING_STATICS_FIFTH = "3개 일치 (5,000원) - ";
    private static final String WINNING_STATICS_FOUR = "4개 일치 (50,000원) - ";
    private static final String WINNING_STATICS_THIRD = "5개 일치 (1,500,000원) - ";
    private static final String WINNING_STATICS_SECOND = "5개 일치, 보너스 볼 일치 (30,000,000원) - ";
    private static final String WINNING_STATICS_FIRST = "6개 일치 (2,000,000,000원) - ";
    private static final int FIRST_RANK = 1;
    private static final int SECOND_RANK = 2;
    private static final int THIRD_RANK = 3;
    private static final int FOUR_RANK = 4;
    private static final int FIFTH_RANK = 5;
    private static final String WINNING_PER_CORRECT_MESSAGE = "개 일치";
    private static final String WINNING_COMMA_BONUS_CORRECT_MESSAGE = ", 보너스 볼 일치";
    private static final String SPACE_BAR_ENTER_RIGHT_BRACHET_MESSAGE = " (";
    private static final String RANK_PRICE_PER_WON_MESSAGE = "%,d원";
    private static final String CLOSE_BRACKET_MESSAGE = ") - ";
    private static final String PER_COUNT_MESSAGE = "개";
    private static final int ZERO_POSITION = 0;

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printLottosByAmount(PurchaseLottoDto lottos) {
        for (List<Integer> lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        printEnter();
    }

    public static void printIssueAbleCount(int issueAbleCount) {
        printEnter();
        System.out.printf(OUTPUT_ISSUE_ABLE_COUNT_MESSAGE, issueAbleCount);
    }

    public static void printWinningStatics(WinningTierResponseDto winningTierResponseDto) {
        Map<Integer, WinningResponseDto> winningResponseMap = winningTierResponseDto.getWinningResponseMap();

        winningResponseMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(v -> toWinningMessage(v.getValue()));
    }

    public static void toWinningMessage(WinningResponseDto winningResponseDto) {
        System.out.print(winningResponseDto.getTier().get(ZERO_POSITION) + WINNING_PER_CORRECT_MESSAGE);
        if (winningResponseDto.getIsBonus()) {
            System.out.print(WINNING_COMMA_BONUS_CORRECT_MESSAGE);
        }
        System.out.print(SPACE_BAR_ENTER_RIGHT_BRACHET_MESSAGE);
        System.out.printf(RANK_PRICE_PER_WON_MESSAGE, winningResponseDto.getRankPrice());
        System.out.print(CLOSE_BRACKET_MESSAGE);
        System.out.print(winningResponseDto.getCorrectCount() + PER_COUNT_MESSAGE);
        printEnter();
    }


    public static void printWinningRevenue(RevenueDto revenueDto) {
        double userRateOfRevenue = revenueDto.getUserRateOfRevenue();
        System.out.printf(OUTPUT_RATE_OF_REVENUE_MESSAGE, userRateOfRevenue);
    }

    public static void printWinningStaticsInput() {
        System.out.println(OUTPUT_WINNING_STATICS_MESSAGE);
    }

    public static void printEnter() {
        System.out.println();
    }

}
