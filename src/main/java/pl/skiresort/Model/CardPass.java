package pl.skiresort.Model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="card_pass")
public class CardPass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date start;

    private Date end;

    private int level;

    @OneToOne(mappedBy = "cardPass")
    private User user;

    public CardPass(final Date start, final Date end, final int level, final User user) {
        this.start = start;
        this.end = end;
        this.level = level;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    User getUser() {
        return user;
    }

    void setUser(final User user) {
        this.user = user;
    }
}
