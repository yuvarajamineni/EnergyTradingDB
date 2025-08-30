import java.math.BigDecimal;
import java.time.LocalDate;

public class Trade {
    public int id;
    public LocalDate date;
    public String counterparty;
    public String commodity;
    public BigDecimal volume;
    public BigDecimal price;
    public String type;

    public Trade(int id, LocalDate date, String cp, String com, BigDecimal vol, BigDecimal price, String type) {
        this.id = id;
        this.date = date;
        this.counterparty = cp;
        this.commodity = com;
        this.volume = vol;
        this.price = price;
        this.type = type;
    }

    public Trade(LocalDate date, String cp, String com, BigDecimal vol, BigDecimal price, String type) {
        this(0, date, cp, com, vol, price, type);
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s | %s | Vol=%s | Price=%s | %s",
            id, date, counterparty, commodity, volume, price, type);
    }
}
