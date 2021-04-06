package pl.skiresort.Model.Projection;

import org.springframework.format.annotation.DateTimeFormat;
import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.User;

import java.time.LocalDate;

public class CardPassWriteModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate end;

    private int level;

    private User user;

    public CardPassWriteModel() {
    }

    public CardPassWriteModel(final LocalDate start, final LocalDate end, final int level, final User user) {
        this.start = start;
        this.end = end;
        this.level = level;
        this.user = user;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(final LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(final LocalDate end) {
        this.end = end;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public CardPass toCardPass() {
        return new CardPass(this.start, this.end, this.level, this.user);
    }
}
