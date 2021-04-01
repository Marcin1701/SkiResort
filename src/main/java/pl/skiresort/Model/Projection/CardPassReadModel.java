package pl.skiresort.Model.Projection;

import java.util.Date;

public class CardPassReadModel {

    private int id;

    private Date start;

    private Date end;

    private int level;

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
