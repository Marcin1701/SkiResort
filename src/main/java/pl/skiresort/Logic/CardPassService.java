package pl.skiresort.Logic;

import org.springframework.stereotype.Service;
import pl.skiresort.Model.CardPassRepository;
import pl.skiresort.Model.Projection.CardPassReadModel;
import pl.skiresort.Model.Projection.CardPassWriteModel;
import pl.skiresort.Model.User;

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

    public User getCardPassUser(Integer id){
        if (cardPassRepository.findById(id).isPresent()){
            return cardPassRepository.findById(id).get().getUser();
        } else {
            return null;
        }
    }
}
