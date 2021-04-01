package pl.skiresort.Model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="card_pass")
public class CardPass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate start;

    private LocalDate end;

    private int level;

    @OneToOne(mappedBy = "cardPass")
    private User user;

    public CardPass(final LocalDate start, final LocalDate end, final int level, final User user) {
        this.start = start;
        this.end = end;
        this.level = level;
        this.user = user;
    }

    public CardPass() {

    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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

    User getUser() {
        return user;
    }

    void setUser(final User user) {
        this.user = user;
    }
}
