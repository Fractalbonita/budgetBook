
package dev.korhammer.budgetBook;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningRepository extends JpaRepository<Earning, UUID> {
}
