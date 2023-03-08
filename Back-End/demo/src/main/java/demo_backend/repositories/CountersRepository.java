package demo_backend.repositories;

import demo_backend.entities.Counters;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;


public interface CountersRepository extends CrudRepository<Counters, Integer> {

    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select c.counter from Counters c where c.object='id_po'")
    Integer findPOCounter();

    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Modifying
    @Query(value = "update Counters c set c.counter=c.counter+1 where c.object='id_po'")
    void  updatePOCounter();

    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select c.counter from Counters c where c.object='id_article'")
    Integer findACounter();

    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @Modifying
    @Query(value = "update Counters c set c.counter=c.counter+1 where c.object='id_article'")
    void  updateACounter();
}
