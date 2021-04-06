package pl.skiresort.Model;

import pl.skiresort.Model.Projection.CardPassReadModel;

import java.util.List;
import java.util.Optional;

public interface CardPassRepository {
    List<CardPass> findAll();

    Optional<CardPass> findById(Integer i);

    CardPass save(CardPass entity);
}
