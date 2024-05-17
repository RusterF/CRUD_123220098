/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerDAO;
import java.util.List;
import DataDAO.DAOData;
import ImplementDAO.DataInterface;
import javax.swing.JOptionPane;
import model.*;
import Tampilan.MainView;
/**
 *
 * @author LENOVO
 */
public class DataController {
    
    MainView frame;
    DataInterface ImplDataMovie;
    List<MovieData> dm;

    public DataController(MainView frame) {
        this.frame = frame;
        ImplDataMovie = new DAOData();
        dm = ImplDataMovie.getALL();
    }

    public void isiTabel() {
        dm = ImplDataMovie.getALL();
        ModelTabel mm = new ModelTabel(dm);
        frame.getTabelDataMovie().setModel(mm);
    }

    public void insert() {
        MovieData dm = new MovieData();
        dm.setJudul(frame.getJTxtjudul().getText());
        dm.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
        dm.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
        dm.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
        dm.setNilai(dm.getNilai());
        ImplDataMovie.insert(dm);
    }

    public void update() {
        MovieData dm = new MovieData();
        dm.setJudul(frame.getJTxtjudul().getText());
        dm.setAlur(Double.parseDouble(frame.getJTxtalur().getText()));
        dm.setPenokohan(Double.parseDouble(frame.getJTxtpenokohan().getText()));
        dm.setAkting(Double.parseDouble(frame.getJTxtakting().getText()));
        dm.setNilai(dm.getNilai());
        ImplDataMovie.update(dm);
    }

    public void delete() {
        String judul = frame.getJTxtjudul().getText();
        ImplDataMovie.delete(judul);
    }

}
