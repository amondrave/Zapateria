/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.ColorDAOMySql;
import DAO.SuelaDAOMySql;
import DAO.ZapatoDAOMySql;
import DAOInterfaces.DAOException;
import Modelo.Suela;
import Modelo.Zapato;
import Modelo.*;
import Negocio.Manager;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class BuscarZapato extends javax.swing.JFrame {

    /**
     * Creates new form BuscarZapato
     */
    Inicio i;
    ArrayList<Zapato> zapatos;
    Manager m;
    ZapatoDAOMySql zd;
    SuelaDAOMySql sd;
    ColorDAOMySql cd;
    Zapato za ;
    boolean carga = false;
    Venta v;
    public BuscarZapato(Inicio i) throws SQLException, DAOException {
        initComponents();
        this.getContentPane().setBackground(Color.decode("#FFFFCC"));
        this.i=i;
        this.setResizable(false);
        m = new Manager();
        zd = m.getZapatoDAOMySql();
        sd = m.getSuelaDAOMySql();
        cd = m.getColorDAOMySql();
        zapatos = (ArrayList<Zapato>) zd.obtenerTodos();
        cargarCombo(zapatos);
    }
    
    public BuscarZapato(Venta v) throws SQLException, DAOException{
    initComponents();
        this.getContentPane().setBackground(Color.decode("#FFFFCC"));
        this.v=v;
        this.setResizable(false);
        m = new Manager();
        zd = m.getZapatoDAOMySql();
        zapatos = (ArrayList<Zapato>) zd.obtenerTodos();
        cargarCombo(zapatos);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        referencias = new javax.swing.JComboBox<>();
        cargar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelRef = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        suela = new javax.swing.JLabel();
        color = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        cerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        referencias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cargar.setText("Cargar");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        jLabel1.setText("Referencia:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Ref suela:");

        jLabel4.setText("Ref color:");

        labelRef.setText("..");

        labelNombre.setText("..");

        suela.setText("..");

        color.setText("..");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(referencias, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(cargar)
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(suela, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(labelRef, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregar)
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargar)
                    .addComponent(referencias, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelRef))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(suela))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(color))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(agregar)
                            .addComponent(cerrar))
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        if(carga==true){
          v.setRef(za.getReferencia());
          Suela sue = null;
            try {
                v.setColor(za.getColor());
            } catch (DAOException ex) {
                Logger.getLogger(BuscarZapato.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                v.setSuela(za.getSuela());
            } catch (DAOException ex) {
                Logger.getLogger(BuscarZapato.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Cargue primero un zapato");
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        // TODO add your handling code here:
        za = buscar(zapatos,referencias.getSelectedItem().toString());
        labelRef.setText(za.getReferencia());
        labelNombre.setText(za.getNombre());
        suela.setText(za.getSuela());
        color.setText(za.getColor());
        carga = true;
    }//GEN-LAST:event_cargarActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        dispose();
    }//GEN-LAST:event_cerrarActionPerformed

    public void cargarCombo(ArrayList<Zapato> z){
     referencias.removeAllItems();
     referencias.addItem("Seleccione una referencia");
     for(Zapato za : z){
       referencias.addItem(za.getReferencia());
     }
    
    }
    
    private Zapato buscar(ArrayList<Zapato> z,String referencia){
     Zapato aux = null;
     for(Zapato ze : z){
       if(ze.getReferencia().equalsIgnoreCase(referencia))
           aux = ze;
     }
     return aux;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton cargar;
    private javax.swing.JButton cerrar;
    private javax.swing.JLabel color;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelRef;
    private javax.swing.JComboBox<String> referencias;
    private javax.swing.JLabel suela;
    // End of variables declaration//GEN-END:variables
}
