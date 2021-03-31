package pl.skiresort.Logic;

import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPassRepository;
import pl.skiresort.Model.Projection.CardPassWriteModel;

@Service
public class CardPassService {

    public final CardPassRepository cardPassRepository;

    CardPassService(final CardPassRepository cardPassRepository) {
        this.cardPassRepository = cardPassRepository;
    }

    public boolean addCardPass(CardPassWriteModel cardPassWriteModel){
        cardPassRepository.save(cardPassWriteModel.toCardPass());
        return true;
    }
}
