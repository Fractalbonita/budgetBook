package dev.korhammer.budgetBook;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ExpenseTest {
  Faker faker = new Faker();

  @Test
  void should_create_Expense() {
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = BigDecimal.valueOf(faker.number().randomDouble(2, -5000, -1));
    Category category = Category.GROCERY;
    boolean fixed = faker.random().nextBoolean();
    Place place = new Place();

    Expense expense = Expense.of(date, amount, category, fixed, place);
    assertThat(expense.getDate()).isEqualTo(date);
    assertThat(expense.getAmount()).isEqualTo(amount);
    assertThat(expense.getCategory()).isEqualTo(category);
    assertThat(expense.isFixed()).isEqualTo(fixed);
    assertThat(expense.getPlace()).isEqualTo(place);
  }

  @Test
  void should_fail_with_invalid_data() {
    LocalDateTime date = LocalDateTime.now();
    BigDecimal amount = BigDecimal.valueOf(faker.number().randomDouble(2, -5000, -1));
    Category category = Category.GROCERY;
    boolean fixed = faker.random().nextBoolean();
    Place place = new Place();

    assertThatThrownBy(() -> Expense.of(null, amount, category, fixed, place))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Expense.of(date, null, category, fixed, place))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Expense.of(date, amount, null, fixed, place))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Expense.of(date, amount, category, fixed, null))
        .isInstanceOf(NullPointerException.class);
    assertThatThrownBy(() -> Expense.of(date, BigDecimal.ZERO, category, fixed, place))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(
            () ->
                Expense.of(
                    date,
                    BigDecimal.valueOf(faker.number().randomDouble(2, 1, 5000)),
                    category,
                    fixed,
                    place))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
