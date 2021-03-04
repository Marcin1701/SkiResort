package pl.skiresort.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SQLUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
