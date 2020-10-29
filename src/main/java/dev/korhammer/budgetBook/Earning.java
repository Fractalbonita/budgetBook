package dev.korhammer.budgetBook;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public class Earning {
  @Id private UUID id;
  private LocalDateTime date;
  private BigDecimal amount;
  @ManyToOne private User user;
  private String note;

  @SuppressWarnings("unused")
  protected Earning() {}

  private Earning(UUID id, LocalDateTime date, BigDecimal amount, User user, String note) {
    this.id = id;
    this.date = date;
    this.amount = amount;
    this.user = user;
    this.note = note;
  }

  public static Earning of(LocalDateTime date, BigDecimal amount, User user, String note) {
    checkNotNull(date);
    checkNotNull(amount);
    checkNotNull(user);
    checkNotNull(note);
    checkArgument(amount.compareTo(BigDecimal.ZERO) > 0);
    return new Earning(UUID.randomUUID(), date, amount, user, note);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Earning earning = (Earning) o;
    return id.equals(earning.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("date", date)
        .add("amount", amount)
        .add("user", user)
        .add("note", note)
        .toString();
  }

  public UUID getId() {
    return id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public User getUser() {
    return user;
  }

  public String getNote() {
    return note;
  }
}
