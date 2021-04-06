package pl.skiresort.Adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.CardPassRepository;

public interface SQLCardPassRepository extends CardPassRepository, JpaRepository<CardPass, Integer> {

}
