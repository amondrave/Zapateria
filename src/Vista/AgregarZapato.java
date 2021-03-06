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
import Negocio.Manager;
import Modelo.Color;
import Modelo.Zapato;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class AgregarZapato extends javax.swing.JFrame {

    /**
     * Creates new form AgregarZapato
     */
    Inicio i;
    Manager m;
    ZapatoDAOMySql zd;
    SuelaDAOMySql sd;
    ColorDAOMySql cd;
    Zapato z=new Zapato();
    
    ArrayList<Color> color;
    ArrayList<Suela> suela;
    
    
    public AgregarZapato(Inicio i) throws SQLException {
        
        this.i=i;
        m=new Manager();
        zd=m.getZapatoDAOMySql();
        sd=m.getSuelaDAOMySql();
        cd=m.getColorDAOMySql();
        try {
            obtener();
        } catch (DAOException ex) {
            Logger.getLogger(AgregarZapato.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        cargarComboColor(color);
        cargarComboSuela(suela);
    }
    
    //Cargar Combos
    
    public void obtener() throws DAOException{
        color= (ArrayList<Color>) cd.obtenerTodos();
        suela= (ArrayList<Suela>) sd.obtenerTodos();
    }
    
    public void cargarComboColor(List<Color> n){
        //col.removeAllItems();
   
        col.addItem("Seleccionar");
        for(Color c: n){
         col.addItem(c.getNombre());
        }
        
    }
    
    public void cargarComboSuela(List<Suela> n){
        
        sue.removeAllItems();
        
        sue.addItem("Seleccionar");
        for(Suela s: n){
         sue.addItem(s.getNombre());
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ref = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sue = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        col = new javax.swing.JComboBox<>();
        cerrar = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        er1 = new javax.swing.JLabel();
        er2 = new javax.swing.JLabel();
        er3 = new javax.swing.JLabel();
        er4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Zapatos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Referencia");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Suela");

        sue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sueActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Color");

        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        er1.setText("..");

        er2.setText("..");

        er3.setText("..");

        er4.setText("..");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cerrar)
                                .addGap(18, 18, 18)
                                .addComponent(agregar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ref)
                                    .addComponent(nom)
                                    .addComponent(sue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(col, 0, 225, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(er1)
                            .addComponent(er2)
                            .addComponent(er3)
                            .addComponent(er4))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(er1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(er2))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(er3))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(er4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrar)
                    .addComponent(agregar))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
        er1.setText("");
        er2.setText("");
        er3.setText("");
        er4.setText("");
        
        
        int k=0; boolean vacioSn=false; boolean vacioNo=false; int ced=0; double not=0;
        
        if(ref.getText().isEmpty()){
            er1.setText("!");
            k++;
        }
        if(nom.getText().isEmpty()){
            er2.setText("!");
            k++;
        }
        if(sue.getSelectedIndex()==0){
            er3.setText("!");
            k++;
        }
        if(col.getSelectedIndex()==0){
            er4.setText("!");
            k++;
        }
        
        if(k>0){
           JOptionPane.showMessageDialog(null, "Complete Los Campos Restantes", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            z.setReferencia(ref.getText());
            z.setNombre(nom.getText());
            z.setSuela(sue.getSelectedItem()+"");
            z.setColor(col.getSelectedItem()+"");
            
            
            
            try {
                zd.insertar(z);
            } catch (DAOException ex) {
                Logger.getLogger(AgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JOptionPane.showMessageDialog(null, "Proceso Exitoso", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
            ref.setText("");
            nom.setText("");
            sue.setSelectedIndex(0);
            col.setSelectedIndex(0);
            
        } 
        
        er1.setText("");
        er2.setText("");
        er3.setText("");
        er4.setText("");
        
    }//GEN-LAST:event_agregarActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cerrarActionPerformed

    private void sueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sueActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton cerrar;
    private javax.swing.JComboBox<String> col;
    private javax.swing.JLabel er1;
    private javax.swing.JLabel er2;
    private javax.swing.JLabel er3;
    private javax.swing.JLabel er4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField ref;
    private javax.swing.JComboBox<String> sue;
    // End of variables declaration//GEN-END:variables
}
