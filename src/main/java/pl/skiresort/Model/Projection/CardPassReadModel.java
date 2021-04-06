package pl.skiresort.Model.Projection;

import pl.skiresort.Model.CardPass;

import java.time.LocalDate;
import java.util.Date;

public class CardPassReadModel {

    private int id;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final int level;

    public CardPassReadModel(final CardPass cardPass) {
        this.endDate = cardPass.getEnd();
        this.startDate = cardPass.getStart();
        this.level = cardPass.getLevel();

    }

    public CardPassReadModel(final LocalDate startDate, final LocalDate endDate, final int level) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
