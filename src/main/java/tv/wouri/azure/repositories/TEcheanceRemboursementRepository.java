package tv.wouri.azure.repositories;

import tv.wouri.azure.models.TEcheanceRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TEcheanceRemboursementRepository extends JpaRepository<TEcheanceRemboursement, Integer> {

    @Query(value = "select * from t_echeanceremboursement where PRNUMERO = ?1", nativeQuery = true)
    List<TEcheanceRemboursement> findByPRet (int personne);
}
