import java.sql.*;             
import java.util.*;            
import java.math.BigDecimal;
import java.time.LocalDate;

public class TradeDAO {

    public int addTrade(Trade t) throws SQLException {
        String sql = "INSERT INTO Trades (TradeDate,Counterparty,Commodity,Volume,Price,TradeType) VALUES (?,?,?,?,?,?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, java.sql.Date.valueOf(t.date));
            ps.setString(2, t.counterparty);
            ps.setString(3, t.commodity);
            ps.setBigDecimal(4, t.volume);
            ps.setBigDecimal(5, t.price);
            ps.setString(6, t.type.toUpperCase());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<Trade> getAllTrades() throws SQLException {
        List<Trade> list = new ArrayList<>();
        String sql = "SELECT * FROM Trades";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public int updateTrade(int id, BigDecimal newPrice, BigDecimal newVol) throws SQLException {
        String sql = "UPDATE Trades SET Price=?, Volume=? WHERE TradeID=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1, newPrice);
            ps.setBigDecimal(2, newVol);
            ps.setInt(3, id);
            return ps.executeUpdate();
        }
    }

    public int deleteTrade(int id) throws SQLException {
        String sql = "DELETE FROM Trades WHERE TradeID=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public List<Trade> search(String cpLike, String comLike) throws SQLException {
        String sql = "SELECT * FROM Trades WHERE 1=1";
        List<Object> params = new ArrayList<>();
        if (cpLike != null && !cpLike.isBlank()) {
            sql += " AND Counterparty LIKE ?";
            params.add("%" + cpLike + "%");
        }
        if (comLike != null && !comLike.isBlank()) {
            sql += " AND Commodity LIKE ?";
            params.add("%" + comLike + "%");
        }

        List<Trade> list = new ArrayList<>();
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i=0; i<params.size(); i++) ps.setObject(i+1, params.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private Trade map(ResultSet rs) throws SQLException {
        return new Trade(
            rs.getInt("TradeID"),
            rs.getDate("TradeDate").toLocalDate(),
            rs.getString("Counterparty"),
            rs.getString("Commodity"),
            rs.getBigDecimal("Volume"),
            rs.getBigDecimal("Price"),
            rs.getString("TradeType")
        );
    }
}
