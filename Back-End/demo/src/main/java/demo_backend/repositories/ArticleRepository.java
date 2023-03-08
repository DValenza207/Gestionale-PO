package demo_backend.repositories;

import demo_backend.entities.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer>, QuerydslPredicateExecutor<Article> {

    @Query(value = "select max(a.id) from Article a")
    Integer findMaxAId();
    List<Article> findAllById(Integer Id);

    List<Article> findAllByName(String name,Pageable pageable);

    List<Article> findAllByDescription(String description, Pageable pageable);

    List<Article> findAllByIdAndName(Integer Id,String Name, Pageable pageable);

    List<Article> findAllByNameAndDescription(String name,String description, Pageable pageable);

    List<Article> findAllByIdAndDescription(Integer Id, String description, Pageable pageable);

    List<Article> findAllByIdAndNameAndDescription(Integer Id, String name, String description, Pageable pageable);
}
