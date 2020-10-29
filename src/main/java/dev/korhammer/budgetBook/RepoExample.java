package dev.korhammer.budgetBook;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RepoExample {
  private final EarningRepository repository;

  public RepoExample(EarningRepository repository) {
    this.repository = repository;
  }

  List<Earning> loadAllEarnings() {
    return repository.findAll();
  }

  List<Earning> loadAllEarningsBetween(LocalDateTime begin, LocalDateTime end) {
    return repository.findAllByDateBetween(begin, end);
  }
}
