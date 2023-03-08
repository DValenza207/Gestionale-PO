package demo_backend.repositories;

import demo_backend.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {

   // @Query("SELECT u.name FROM User u WHERE u.name =:name")
    public User findUserByName(String name);

    //@Query("SELECT u.password FROM User u WHERE u.password =:password")
    //public String findPassword(@Param("password") String password);
}
