/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.*;
import DTO.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Krysthian
 */
public class Movimientos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Movimientos.class.getName());
    private ClienteDAO daoCliente = new ClienteDAO();
    private int id_Cliente = -1;
    private ClienteDTO cliente = new ClienteDTO();
    
    public Movimientos(int id_Cliente) throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        this.id_Cliente = id_Cliente;
        cliente = daoCliente.buscarPorId(id_Cliente);
        System.out.printf("""
                          %d | %s | %s
                          """, cliente.getId(), cliente.getNombre(), cliente.getDni());
        lblClienteDNI.setText(cliente.getDni());
        lblClienteNombre.setText(cliente.getNombre());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jpMovimientos = new javax.swing.JPanel();
        btnDepositar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRetirar = new javax.swing.JButton();
        btntransferir = new javax.swing.JButton();
        btnconsultarSaldo = new javax.swing.JButton();
        btncambiarPin = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnbloquearTarjeta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblClienteNombre = new javax.swing.JLabel();
        lblClienteDNI = new javax.swing.JLabel();

        jButton4.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpMovimientos.setBackground(new java.awt.Color(51, 102, 255));
        jpMovimientos.setForeground(new java.awt.Color(0, 102, 255));

        btnDepositar.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnDepositar.setForeground(new java.awt.Color(255, 51, 51));
        btnDepositar.setText("DEPOSITAR");
        btnDepositar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDepositar.addActionListener(this::btnDepositarActionPerformed);

        jLabel1.setText("MOVIMIENTOS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnRetirar.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnRetirar.setForeground(new java.awt.Color(255, 51, 51));
        btnRetirar.setText("RETIRAR");
        btnRetirar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRetirar.addActionListener(this::btnRetirarActionPerformed);

        btntransferir.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btntransferir.setForeground(new java.awt.Color(255, 51, 51));
        btntransferir.setText("TRANSFERIR");
        btntransferir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btntransferir.addActionListener(this::btntransferirActionPerformed);

        btnconsultarSaldo.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnconsultarSaldo.setForeground(new java.awt.Color(255, 51, 51));
        btnconsultarSaldo.setText("CONSULTAR SALDO");
        btnconsultarSaldo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnconsultarSaldo.addActionListener(this::btnconsultarSaldoActionPerformed);

        btncambiarPin.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btncambiarPin.setForeground(new java.awt.Color(255, 51, 51));
        btncambiarPin.setText("CAMBIAR PIN");
        btncambiarPin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btncambiarPin.addActionListener(this::btncambiarPinActionPerformed);

        btnConfiguracion.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 51, 51));
        btnConfiguracion.setText("CONFIGURACIÓN");
        btnConfiguracion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConfiguracion.addActionListener(this::btnConfiguracionActionPerformed);

        btnbloquearTarjeta.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnbloquearTarjeta.setForeground(new java.awt.Color(255, 51, 51));
        btnbloquearTarjeta.setText("BLOQUEAR TARJETA");
        btnbloquearTarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnbloquearTarjeta.addActionListener(this::btnbloquearTarjetaActionPerformed);

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Elija un opcion para continuar:");

        btnSalir.setBackground(new java.awt.Color(51, 102, 255));
        btnSalir.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.setBorder(null);

        jLabel3.setText("Cliente:");

        jLabel4.setText("DNI:");

        lblClienteNombre.setText("?");

        lblClienteDNI.setText("?");

        javax.swing.GroupLayout jpMovimientosLayout = new javax.swing.GroupLayout(jpMovimientos);
        jpMovimientos.setLayout(jpMovimientosLayout);
        jpMovimientosLayout.setHorizontalGroup(
            jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMovimientosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMovimientosLayout.createSequentialGroup()
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btncambiarPin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btntransferir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbloquearTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMovimientosLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnRetirar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnconsultarSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientosLayout.createSequentialGroup()
                                .addComponent(btnSalir)
                                .addGap(38, 38, 38))))
                    .addGroup(jpMovimientosLayout.createSequentialGroup()
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClienteNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(lblClienteDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        jpMovimientosLayout.setVerticalGroup(
            jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMovimientosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMovimientosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnconsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncambiarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblClienteNombre))
                        .addGap(21, 21, 21)
                        .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnbloquearTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpMovimientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblClienteDNI)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addGap(31, 31, 31))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpMovimientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpMovimientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
    Deposito deposito = new Deposito();
    deposito.setVisible(true);
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btncambiarPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarPinActionPerformed
        CambioPin cambio = new CambioPin();
        cambio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btncambiarPinActionPerformed

    private void btntransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransferirActionPerformed
        Transferencia transfer = new Transferencia();
        transfer.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btntransferirActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
       Retiro retiro = new Retiro();
       retiro.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnconsultarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarSaldoActionPerformed

        ConsultaSaldo consulta = new ConsultaSaldo();
        consulta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnconsultarSaldoActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        ConfiguracionTarjeta confi = new ConfiguracionTarjeta();
        confi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnbloquearTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbloquearTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbloquearTarjetaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnbloquearTarjeta;
    private javax.swing.JButton btncambiarPin;
    private javax.swing.JButton btnconsultarSaldo;
    private javax.swing.JButton btntransferir;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jpMovimientos;
    private javax.swing.JLabel lblClienteDNI;
    private javax.swing.JLabel lblClienteNombre;
    // End of variables declaration//GEN-END:variables
}
