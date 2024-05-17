/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementDAO;
import java.util.List;
import model.*;
/**
 *
 * @author LENOVO
 */
public interface DataInterface {
    public void insert(MovieData m);
    public void update(MovieData m);
    public void delete(String judul);
    public List<MovieData> getALL();
}
