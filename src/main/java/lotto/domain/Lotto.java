package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    static final String LOTTO_START_ERROR_MESSAGE = "[ERROR] ";
    static final String LOTTO_NUMBER_LENGTH_INVALID_MESSAGE = "발행한 로또 번호는 6자리 숫자여야 합니다.";
    static final String LOTTO_NUMBER_RANGE_INVALID_MESSAGE = "발행한 로또 번호는 1~45의 숫자여야 합니다.";
    static final String LOTTO_NUMBER_DUPLICATE_INVALID_MESSAGE = "발행한 로또 번호는 중복이 있으면 안됩니다.";

    static final int PRICE_PER_LOTTO = 1000;

    private static final int LOTTO_SIX_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortAscending(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateLengthNumber(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    public static List<List<Integer>> generateLottosByAmount(int amount) {
        List<List<Integer>> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(RandomNumber.generateLottoNumbers());
        }

        return lottos;
    }

    public List<Integer> getLottoNumbers() {
        return this.numbers;
    }

    private static void validateLengthNumber(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIX_SIZE) {
            throw new IllegalArgumentException(LOTTO_START_ERROR_MESSAGE + LOTTO_NUMBER_LENGTH_INVALID_MESSAGE);
        }
    }

    private static void validateRange(List<Integer> numbers) {
        if (isOverRange(numbers)) {
            throw new IllegalArgumentException(LOTTO_START_ERROR_MESSAGE + LOTTO_NUMBER_RANGE_INVALID_MESSAGE);
        }
    }

    private static boolean isOverRange(List<Integer> numbers) {
        long numberCount = numbers.stream()
                .filter(number -> number < 1 || number > 45)
                .count();
        return numberCount != 0;
    }

    private static void validateDuplication(List<Integer> numbers) {
        if (isDuplication(numbers)) {
            throw new IllegalArgumentException(LOTTO_START_ERROR_MESSAGE + LOTTO_NUMBER_DUPLICATE_INVALID_MESSAGE);
        }
        ;
    }

    private static boolean isDuplication(List<Integer> numbers) {
        long duplication = numbers.stream()
                .distinct()
                .count();
        return duplication != LOTTO_SIX_SIZE;
    }

    private List<Integer> sortAscending(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
