package pl.skiresort.Adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.skiresort.Model.UserRepository;
import pl.skiresort.Model.User;

import java.util.Optional;

@Repository
interface SQLUserRepository extends UserRepository, JpaRepository<User, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select * from clients where id=:id")
    Optional<User> findById(@Param("id") Integer i);

    @Override
    @Query(nativeQuery = true, value = "select * from clients c where email=:email and password=:password")
    Optional<User> findByEmailAndPassword(@Param("email") String e, @Param("password") String p);

    @Override
    boolean existsByEmail(String email);

    @Override
    Optional<User> findByEmail(String email);
}
