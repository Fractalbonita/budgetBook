package dev.korhammer.budgetBook;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Expense {
  private LocalDateTime date;
  private BigDecimal amount;
  private Category category;
  private boolean fixed;
  @ManyToOne private Place place;

  private Expense(
      LocalDateTime date, BigDecimal amount, Category category, boolean fixed, Place place) {
    this.date = date;
    this.amount = amount;
    this.category = category;
    this.fixed = fixed;
    this.place = place;
  }

  public static Expense of(
      LocalDateTime date, BigDecimal amount, Category category, boolean fixed, Place place) {
    checkNotNull(date);
    checkNotNull(amount);
    checkNotNull(category);
    checkNotNull(place);
    checkArgument(amount.compareTo(BigDecimal.ZERO) < 0);
    return new Expense(date, amount, category, fixed, place);
  }

  public LocalDateTime getDate() {
    return date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Category getCategory() {
    return category;
  }

  public boolean isFixed() {
    return fixed;
  }

  public Place getPlace() {
    return place;
  }
}
