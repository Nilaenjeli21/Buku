/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nila.controller;

import Nila.view.*;

import Nila.dao.*;
import Nila.model.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;
/**
 *
 * @author Nila Enjeni
 */
public class BukuController {
   /**
     * Creates new form FormBuku
     */
    private FormBuku formBuku;
    private BukuDao bukuDao;
    private Buku buku;
    
    public BukuController(FormBuku formBuku){
        this.formBuku = formBuku;
        bukuDao = new BukuDaoImpl();
    }
    
    public void bersihForm(){
        formBuku.getTxtKodebuku().setText("");
        formBuku.getTxtJudul().setText("");
        formBuku.getTxtPengarang().setText("");
        formBuku.getTxtPenerbit().setText("");
    }
    
    public void save(){
        buku = new Buku();
        buku.setKodebuku(formBuku.getTxtKodebuku().getText());
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setPengarang(formBuku.getTxtPengarang().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        bukuDao.save(buku);
        JOptionPane.showMessageDialog(formBuku,"Entri Data Ok");
    }
    
    
    public void getBuku(){
        int index = formBuku.getTblBuku().getSelectedRow();
        buku = bukuDao.getBuku(index);
        if (buku != null){
            formBuku.getTxtKodebuku().setText(buku.getKodebuku());
            formBuku.getTxtJudul().setText(buku.getJudul());
            formBuku.getTxtPengarang().setText(buku.getPengarang());
            formBuku.getTxtPenerbit().setText(buku.getPenerbit());
        }
    }
    
    public void updateBuku(){
        int index = formBuku.getTblBuku().getSelectedRow();
        buku.setKodebuku(formBuku.getTxtKodebuku().getText());
        buku.setJudul(formBuku.getTxtJudul().getText());
        buku.setPengarang(formBuku.getTxtPengarang().getText());
        buku.setPenerbit(formBuku.getTxtPenerbit().getText());
        bukuDao.update(index,buku);
        javax.swing.JOptionPane.showMessageDialog(formBuku,"Update Ok");
    }
    
     public void deleteBuku(){
        int index = formBuku.getTblBuku().getSelectedRow();
        bukuDao.delete(index);
        javax.swing.JOptionPane.showMessageDialog(formBuku,"Delete Ok");
    }
    public void tampilData(){
        DefaultTableModel tabelModel = ( DefaultTableModel) formBuku.getTblBuku().
        getModel();
        tabelModel.setRowCount(0);
        java.util.List <Buku> listBuku = bukuDao.getAllBuku();
        for(Buku buku : listBuku){
            Object[] data = {
                buku.getKodebuku(),
                buku.getJudul(),
                buku.getPengarang(),
                buku.getPenerbit(),
            };
            tabelModel.addRow(data);
        }
        
    }
}
