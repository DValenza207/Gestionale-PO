package demo_backend.repositories;

import demo_backend.entities.PurchaseOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer>, QuerydslPredicateExecutor<PurchaseOrder> {
    @Query(value = "select max(po.id) from PurchaseOrder po")
    Integer findMaxId();

    List<PurchaseOrder> findAllByCreationDateBetween(Timestamp creationDate,Timestamp creationDate2, Pageable pageable);

    List<PurchaseOrder> findAllByType(String type, Pageable pageable);

    List<PurchaseOrder> findAllByDescription(String description, Pageable pageable);

    List<PurchaseOrder> findAllByCreationDateBetweenAndType(Timestamp creationDate, Timestamp creationDate2, String type, Pageable pageable);

    List<PurchaseOrder> findAllByCreationDateBetweenAndDescription(Timestamp creationDate, Timestamp creationDate2, String description, Pageable pageable);

    List<PurchaseOrder> findAllByTypeAndDescription(String type, String description, Pageable pageable);

    List<PurchaseOrder> findAllByCreationDateBetweenAndTypeAndDescription(Timestamp creationDate, Timestamp creationDate2, String type, String description, Pageable pageable);

}
