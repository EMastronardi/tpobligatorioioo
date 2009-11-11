package ar.edu.uade.ioo.grupo5.tpo.view;


/**
 *
 * @author emolinero
 */

public class AbrirMesa extends javax.swing.JFrame {

    /** Creates new form AbrirMesa */
    public AbrirMesa() {
        initComponents();
    }

  
    private void initComponents() {

        lblMesa = new javax.swing.JLabel();
        txtMesa = new javax.swing.JTextField();
        btnAbrir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("AbrirMesa");

        lblMesa.setText("Mesa"); 

        txtMesa.setText(""); 

        btnAbrir.setText("Abrir"); 
        
        btnCancelar.setText("Cancelar"); 
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMesa)
                        .addGap(40, 40, 40)
                        .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMesa)
                    .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnCancelar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>



    // Variables declaration - do not modify
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JTextField txtMesa;
    // End of variables declaration

}

