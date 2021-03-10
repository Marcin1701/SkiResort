package pl.skiresort.Adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skiresort.Model.UserRepository;
import pl.skiresort.Model.User;

@Repository
interface SQLUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
