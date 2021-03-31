package pl.skiresort.Model.Projection;

import pl.skiresort.Model.CardPass;
import pl.skiresort.Model.User;

import java.util.Date;

public class CardPassWriteModel {

    private Date start;

    private Date end;

    private int level;

    private User user;

    public CardPassWriteModel() {
    }

    public CardPassWriteModel(final Date start, final Date end, final int level) {
        this.start = start;
        this.end = end;
        this.level = level;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(final Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
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
