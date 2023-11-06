package lotto;

import lotto.domain.WinningTier;
import lotto.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OutputViewTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    OutputView outputView = new OutputView();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
        System.setOut(System.out);
    }

    @Test
    void 당첨_내역을_출력하는_기능_검증() {
        List<Long> userCorrectWinningsCount = new ArrayList<>();
        List<Boolean> userCorrectBonuses = new ArrayList<>();

        userCorrectWinningsCount.add(6L);
        userCorrectBonuses.add(false);

        userCorrectWinningsCount.add(5L);
        userCorrectBonuses.add(true);

        userCorrectWinningsCount.add(6L);
        userCorrectBonuses.add(false);

        WinningTier winningTier = new WinningTier();
        winningTier.estimate(userCorrectWinningsCount, userCorrectBonuses);

        outputView.printWinningStatics(winningTier.getWinningTier());
        String winningTierResultMessage =
                "3개 일치 (5,000원) - 0개\n" +
                "4개 일치 (50,000원) - 0개\n" +
                "5개 일치 (1,500,000원) - 0개\n" +
                "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개\n" +
                "6개 일치 (2,000,000,000원) - 2개\n";

        Assertions.assertThat(output.toString()).isEqualTo(winningTierResultMessage);
    }
}