package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three", "yoon", "su"})
    void 로또_굳입_금액이_숫자가_아니면_IllegalArgumentException_발생_검증(String userAmount) {
        assertThatThrownBy(() ->UserAmount.from(userAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구입 금액은 숫자여야 합니다.");
    }

    @Test
    void 로또_구입_금액이_공백을_포함하면_IllegalArgumentException_예외처리() {
        //given
        String emptyValue = "  6 ";

        //when & then
        Assertions.assertThatThrownBy(() -> UserAmount.from(emptyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구입 금액은 공백이 포함될 수 없습니다.");
    }
}
