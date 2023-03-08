package demo_backend.repositories;

import demo_backend.entities.POArticle;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface POArticleRepository extends CrudRepository<POArticle,Integer> {

    void deleteAllByIdPo(Integer id);

    List<POArticle> findAllById(Integer Id);

    List<POArticle> findAllByIdArticolo(Integer id);

    List<POArticle> findAllByIdPo(Integer id);

    List<POArticle> findAllByQuantityBefore(Integer quantity);

    List<POArticle> findAllByIdAndIdPoAndIdArticoloAndQuantityBefore(Integer id, Integer idpo, Integer idA, Integer quantity);

    List<POArticle> findAllByIdAndIdPoAndIdArticolo(Integer id, Integer idpo, Integer idA);

    List<POArticle> findAllByIdPoAndIdArticoloAndQuantityBefore(Integer idpo, Integer idA, Integer quantity);

    List<POArticle> findAllByIdAndIdArticoloAndQuantityBefore(Integer id, Integer idA, Integer quantity);

    List<POArticle> findAllByIdAndIdPoAndQuantityBefore(Integer id, Integer idpo, Integer quantity);

    List<POArticle> findAllByIdAndIdPo(Integer id, Integer idpo);

    List<POArticle> findAllByIdPoAndIdArticolo(Integer idpo, Integer idA);

    List<POArticle> findAllByIdArticoloAndQuantityBefore(Integer idA, Integer quantity);

    List<POArticle> findAllByIdAndIdArticolo(Integer id, Integer idA);

    List<POArticle> findAllByIdAndQuantityBefore(Integer id, Integer quantity);

    List<POArticle> findAllByIdPoAndQuantityBefore(Integer idpo, Integer quantity);

}

//Le modifiche su PurchaseOrder potrebbero riflettersi in questo file
//Immagino che la colonna inerente all'idPo andrà aggiornata
