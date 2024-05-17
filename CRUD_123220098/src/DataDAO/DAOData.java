/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataDAO;
import java.sql.*;
import java.util.*;
import Koneksi.Connector;
import model.*;
import ImplementDAO.DataInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class DAOData implements DataInterface {

    Connection connection;

    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?)";
    final String update = "UPDATE movie set alur=?, penokohan=?, akting=?, nilai=? WHERE judul=?;";
    final String delete = "delete from movie WHERE judul=?";

    public DAOData() {
        connection = Connector.connection();
    }

    @Override
    public void insert(MovieData m) {
        PreparedStatement statement = null;
        try {
            if (m.getAlur() < 0 || m.getAlur() > 5 || m.getAkting() < 0 || m.getAkting() > 5 || m.getPenokohan() < 0 || m.getPenokohan() > 5) {
                throw new SQLException("Nilai-nilai harus berada di antara 0 dan 5");
            }
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, m.getJudul());
            statement.setDouble(2, m.getAlur());
            statement.setDouble(3, m.getPenokohan());
            statement.setDouble(4, m.getAkting());
            statement.setDouble(5, m.getNilai());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            throw new SQLException("Data Movie Berhasil Ditambahkan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(MovieData m) {
        PreparedStatement statement = null;
        try {
            if (m.getAlur() < 0 || m.getAlur() > 5 || m.getAkting() < 0 || m.getAkting() > 5 || m.getPenokohan() < 0 || m.getPenokohan() > 5) {
                throw new SQLException("Nilai-nilai harus berada di antara 0 dan 5");
            }
            statement = connection.prepareStatement(update);
            statement.setDouble(1, m.getAlur());
            statement.setDouble(2, m.getPenokohan());
            statement.setDouble(3, m.getAkting());
            statement.setDouble(4, m.getNilai());
            statement.setString(5, m.getJudul());
            statement.executeUpdate();
            throw new SQLException("Data Movie Berhasil Diupdate");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);

            statement.setString(1, judul);
            statement.executeUpdate();
            throw new SQLException("Data Movie Berhasil Dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<MovieData> getALL() {
        List<MovieData> dm = null;
        try {
            dm = new ArrayList<MovieData>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                MovieData movie = new MovieData();
                movie.setJudul(rs.getString("judul"));
                movie.setAlur(rs.getDouble("alur"));
                movie.setPenokohan(rs.getDouble("penokohan"));
                movie.setAkting(rs.getDouble("akting"));
                movie.setNilai(rs.getDouble("nilai"));
                dm.add(movie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dm;
    }
}
