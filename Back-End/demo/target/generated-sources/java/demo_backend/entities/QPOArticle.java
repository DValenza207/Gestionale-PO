package demo_backend.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPOArticle is a Querydsl query type for POArticle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPOArticle extends EntityPathBase<POArticle> {

    private static final long serialVersionUID = -985339520L;

    public static final QPOArticle pOArticle = new QPOArticle("pOArticle");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idArticolo = createNumber("idArticolo", Integer.class);

    public final NumberPath<Integer> idPo = createNumber("idPo", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QPOArticle(String variable) {
        super(POArticle.class, forVariable(variable));
    }

    public QPOArticle(Path<? extends POArticle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPOArticle(PathMetadata metadata) {
        super(POArticle.class, metadata);
    }

}

