package pl.skiresort.Model;


import javax.persistence.*;
import java.sql.Date;

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
}
