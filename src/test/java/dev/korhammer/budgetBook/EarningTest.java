package dev.korhammer.budgetBook;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class EarningTest {
  Faker faker = new Faker();

  @Test
  void should_create_Earning() {
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 5000));
    User user = new User();
    String note = faker.cat().name();

    Earning earning = Earning.of(date, amount, user, note);
    assertThat(earning.getAmount()).isEqualTo(amount);
    assertThat(earning.getDate()).isEqualTo(date);
    assertThat(earning.getUser()).isEqualTo(user);
    assertThat(earning.getNote()).isEqualTo(note);
  }

  @Test
  void should_fail_with_invalid_data() {
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 5000));
    User user = new User();
    String note = faker.cat().name();

    assertThatThrownBy(() -> Earning.of(null, amount, user, note))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Earning.of(date, null, user, note))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Earning.of(date, amount, null, note))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Earning.of(date, amount, user, null))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Earning.of(date, BigDecimal.ZERO, user, note))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
            () ->
                Earning.of(
                    date,
                    BigDecimal.valueOf(faker.number().randomDouble(2, -5000, -1)),
                    user,
                    note))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
