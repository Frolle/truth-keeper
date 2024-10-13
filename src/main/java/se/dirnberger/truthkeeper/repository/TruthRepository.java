package se.dirnberger.truthkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.dirnberger.truthkeeper.model.Truth;

@Repository
public interface TruthRepository extends JpaRepository<Truth, Long> {
}
