/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/**
 *
 * @author mauri
 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class tablero extends javax.swing.JFrame {

    /**
     * Creates new form tablero
     */
    public tablero() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
    
    public void CartasIniciales(JPanel panel, JLabel label){
        int color = 1+r.nextInt(4);
        switch (color){
            case 1:
                panel.setBackground(Color.RED);
                label.setText(r.nextInt(10)+"");
                break;
            case 2:
                panel.setBackground(Color.BLUE);
                label.setText(r.nextInt(10)+"");
                break;
            case 3:
                panel.setBackground(Color.GREEN);
                label.setText(r.nextInt(10)+"");
                break;
            case 4:
                panel.setBackground(Color.YELLOW);
                label.setText(r.nextInt(10)+"");
                break;
        }
    }
    
    public void actualizarCarta(JPanel panel, JLabel label, int x, int y){
        panel.setBackground(jugadores.get(x).getCartas().get(y).getColor());
        switch (jugadores.get(x).getCartas().get(y).getNumero()){
            case 10:
                label.setIcon(null);
                label.setText("WILD");
                break;
            case 11:
                label.setIcon(null);
                label.setText("+2");
                break;
            case 12:
                label.setIcon(null);
                label.setText("+4");
                break;
            case 13:
                IconReversa(label);
                label.setText("");
                break;
            case 14:
                IconSkip(label);
                label.setText("");
                break;
            default:
                label.setIcon(null);
                label.setText(jugadores.get(x).getCartas().get(y).getNumero()+"");
                break;
        }
    }
    
    public void IconReversa(JLabel label){
        Image imagen = new ImageIcon(this.getClass().getResource("./Reversa.png")).getImage();
        label.setIcon(new ImageIcon(imagen));
    }
    
    public void IconSkip(JLabel label){
        Image imagen = new ImageIcon(this.getClass().getResource("./Skip.png")).getImage();
        label.setIcon(new ImageIcon(imagen));
    }
    
    public void crearjugador(){
        int c = jugadores.size()-1;
        for (int i = 0; i < 7; i++) {
            int color = 1+r.nextInt(4);
            switch (color){
                case 1:
                    jugadores.get(c).getCartas().add(new Cards(Color.RED, r.nextInt(15)));
                    break;
                case 2:
                    jugadores.get(c).getCartas().add(new Cards(Color.BLUE, r.nextInt(15)));
                    break;
                case 3:
                    jugadores.get(c).getCartas().add(new Cards(Color.GREEN, r.nextInt(15)));
                    break;
                case 4:
                    jugadores.get(c).getCartas().add(new Cards(Color.YELLOW, r.nextInt(15)));
                    break;
            }
            if (jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).getNumero() == 10 || jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).getNumero() == 12){
                jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).setColor(Color.BLACK);
            }
        }
    }
    
    public void agregarCarta (int c){
        int color = 1+r.nextInt(4);
        switch (color){
            case 1:
                jugadores.get(c).getCartas().add(new Cards(Color.RED, r.nextInt(15)));
                break;
            case 2:
                jugadores.get(c).getCartas().add(new Cards(Color.BLUE, r.nextInt(15)));
                break;
            case 3:
                jugadores.get(c).getCartas().add(new Cards(Color.GREEN, r.nextInt(15)));
                break;
            case 4:
                jugadores.get(c).getCartas().add(new Cards(Color.YELLOW, r.nextInt(15)));
                break;
        }
        if (jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).getNumero() == 10 || jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).getNumero() == 12){
            jugadores.get(c).getCartas().get(jugadores.get(c).getCartas().size()-1).setColor(Color.BLACK);
        }
    }
    
    public void reiniciarTablaJugador(JTable tabla) {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Color", "Valor"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }});
    }
    
    public void reiniciarTablaCpu(JTable tabla){
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public void llenarBarajas(){
        if (jugadores.size()==2){
            reiniciarTablaJugador(jt_cpu1_jugador);
            reiniciarTablaCpu(jt_cpu1_cpu);
            
            //llenar barajas
            DefaultTableModel modelo = (DefaultTableModel) jt_cpu1_jugador.getModel();
            for (int i = 0; i < jugadores.get(0).getCartas().size(); i++) {
                if (jugadores.get(0).getCartas().get(i).getNumero()<10){
                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                        modelo.addRow(new Object[]{"Rojo", jugadores.get(0).getCartas().get(i).getNumero()});
                    }
                    else{
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                            modelo.addRow(new Object[]{"Azul", jugadores.get(0).getCartas().get(i).getNumero()});
                        }
                        else{
                            if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                modelo.addRow(new Object[]{"Verde", jugadores.get(0).getCartas().get(i).getNumero()});
                            }
                            else{
                                if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                    modelo.addRow(new Object[]{"Amarillo", jugadores.get(0).getCartas().get(i).getNumero()});
                                }
                            }
                        }
                    }
                }
                else{
                    if (jugadores.get(0).getCartas().get(i).getNumero()==10){
                        modelo.addRow(new Object[]{"Wild", "Wild Card"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==11){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "+2"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "+2"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "+2"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "+2"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==12){
                        modelo.addRow(new Object[]{"Wild", "+4"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==13){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Reversa"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Reversa"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Reversa"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Reversa"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==14){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Skip"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Skip"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Skip"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Skip"});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            DefaultTableModel modelo2 = (DefaultTableModel) jt_cpu1_cpu.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo2.addRow(new Object[]{"UNO"});
            }
        }
        
        if (jugadores.size()==3){
            reiniciarTablaJugador(jt_cpu2_jugador);
            reiniciarTablaCpu(jt_cpu2_cpu1);
            reiniciarTablaCpu(jt_cpu2_cpu2);
            DefaultTableModel modelo = (DefaultTableModel) jt_cpu2_jugador.getModel();
            for (int i = 0; i < jugadores.get(0).getCartas().size(); i++) {
                if (jugadores.get(0).getCartas().get(i).getNumero()<10){
                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                        modelo.addRow(new Object[]{"Rojo", jugadores.get(0).getCartas().get(i).getNumero()});
                    }
                    else{
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                            modelo.addRow(new Object[]{"Azul", jugadores.get(0).getCartas().get(i).getNumero()});
                        }
                        else{
                            if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                modelo.addRow(new Object[]{"Verde", jugadores.get(0).getCartas().get(i).getNumero()});
                            }
                            else{
                                if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                    modelo.addRow(new Object[]{"Amarillo", jugadores.get(0).getCartas().get(i).getNumero()});
                                }
                            }
                        }
                    }
                }
                else{
                    if (jugadores.get(0).getCartas().get(i).getNumero()==10){
                        modelo.addRow(new Object[]{"Wild", "Wild Card"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==11){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "+2"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "+2"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "+2"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "+2"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==12){
                        modelo.addRow(new Object[]{"Wild", "+4"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==13){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Reversa"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Reversa"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Reversa"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Reversa"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==14){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Skip"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Skip"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Skip"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Skip"});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            DefaultTableModel modelo2 = (DefaultTableModel) jt_cpu2_cpu1.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo2.addRow(new Object[]{"UNO"});
            }
            DefaultTableModel modelo3 = (DefaultTableModel) jt_cpu2_cpu2.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo3.addRow(new Object[]{"UNO"});
            }
        }
        if (jugadores.size()==4){
            reiniciarTablaJugador(jt_cpu3_jugador);
            reiniciarTablaCpu(jt_cpu3_cpu1);
            reiniciarTablaCpu(jt_cpu3_cpu2);
            reiniciarTablaCpu(jt_cpu3_cpu3);
            DefaultTableModel modelo = (DefaultTableModel) jt_cpu3_jugador.getModel();
            for (int i = 0; i < jugadores.get(0).getCartas().size(); i++) {
                if (jugadores.get(0).getCartas().get(i).getNumero()<10){
                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                        modelo.addRow(new Object[]{"Rojo", jugadores.get(0).getCartas().get(i).getNumero()});
                    }
                    else{
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                            modelo.addRow(new Object[]{"Azul", jugadores.get(0).getCartas().get(i).getNumero()});
                        }
                        else{
                            if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                modelo.addRow(new Object[]{"Verde", jugadores.get(0).getCartas().get(i).getNumero()});
                            }
                            else{
                                if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                    modelo.addRow(new Object[]{"Amarillo", jugadores.get(0).getCartas().get(i).getNumero()});
                                }
                            }
                        }
                    }
                }
                else{
                    if (jugadores.get(0).getCartas().get(i).getNumero()==10){
                        modelo.addRow(new Object[]{"Wild", "Wild Card"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==11){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "+2"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "+2"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "+2"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "+2"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==12){
                        modelo.addRow(new Object[]{"Wild", "+4"});
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==13){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Reversa"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Reversa"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Reversa"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Reversa"});
                                    }
                                }
                            }
                        }
                    }
                    if (jugadores.get(0).getCartas().get(i).getNumero()==14){
                        if (jugadores.get(0).getCartas().get(i).getColor() == Color.RED){
                            modelo.addRow(new Object[]{"Rojo", "Skip"});
                        }
                        else{
                            if (jugadores.get(0).getCartas().get(i).getColor() == Color.BLUE){
                                modelo.addRow(new Object[]{"Azul", "Skip"});
                            }
                            else{
                                if(jugadores.get(0).getCartas().get(i).getColor() == Color.GREEN){
                                    modelo.addRow(new Object[]{"Verde", "Skip"});
                                }
                                else{
                                    if (jugadores.get(0).getCartas().get(i).getColor() == Color.YELLOW){
                                        modelo.addRow(new Object[]{"Amarillo", "Skip"});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            DefaultTableModel modelo2 = (DefaultTableModel) jt_cpu3_cpu1.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo2.addRow(new Object[]{"UNO"});
            }
            DefaultTableModel modelo3 = (DefaultTableModel) jt_cpu3_cpu2.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo3.addRow(new Object[]{"UNO"});
            }
            DefaultTableModel modelo4 = (DefaultTableModel) jt_cpu3_cpu3.getModel();
            for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
                modelo4.addRow(new Object[]{"UNO"});
            }
        }
    }
    
    public void CambioColor(JPanel panel, int x){
        switch (x){
            case 0:
                panel.setBackground(Color.RED);
                break;
            case 1:
                panel.setBackground(Color.BLUE);
                break;
            case 2:
                panel.setBackground(Color.GREEN);
                break;
            case 3:
                panel.setBackground(Color.YELLOW);
                break;
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

        jd_cpu1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jp_cpu1 = new javax.swing.JPanel();
        jl_cpu1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_cpu1_cpu = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_cpu1_jugador = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jl_nombre = new javax.swing.JLabel();
        jl_nombre1 = new javax.swing.JLabel();
        jd_cpu2 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jp_cpu2 = new javax.swing.JPanel();
        jl_cpu2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jt_cpu2_cpu1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jt_cpu2_jugador = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jt_cpu2_cpu2 = new javax.swing.JTable();
        jd_cpu3 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jp_cpu3 = new javax.swing.JPanel();
        jl_cpu3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jt_cpu3_cpu1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jt_cpu3_jugador = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jt_cpu3_cpu2 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jt_cpu3_cpu3 = new javax.swing.JTable();
        jd_Wilds = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        cb_nuevocolor = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sp_cpu = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        cb_dificultad = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(184, 46, 12));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jp_cpu1.setBackground(new java.awt.Color(204, 204, 204));
        jp_cpu1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jl_cpu1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jl_cpu1.setForeground(new java.awt.Color(255, 255, 255));
        jl_cpu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_cpu1.setText("0");

        javax.swing.GroupLayout jp_cpu1Layout = new javax.swing.GroupLayout(jp_cpu1);
        jp_cpu1.setLayout(jp_cpu1Layout);
        jp_cpu1Layout.setHorizontalGroup(
            jp_cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jl_cpu1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );
        jp_cpu1Layout.setVerticalGroup(
            jp_cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jl_cpu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );

        jt_cpu1_cpu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_cpu1_cpu);
        if (jt_cpu1_cpu.getColumnModel().getColumnCount() > 0) {
            jt_cpu1_cpu.getColumnModel().getColumn(0).setResizable(false);
        }

        jt_cpu1_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Color", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jt_cpu1_jugador);
        if (jt_cpu1_jugador.getColumnModel().getColumnCount() > 0) {
            jt_cpu1_jugador.getColumnModel().getColumn(0).setResizable(false);
            jt_cpu1_jugador.getColumnModel().getColumn(1).setHeaderValue("Valor");
        }

        jButton2.setText("Jugar Carta");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("UNO!");

        jButton9.setText("Draw");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        jl_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre.setForeground(new java.awt.Color(255, 255, 255));

        jl_nombre1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_nombre1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jp_cpu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(503, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(298, 298, 298))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jl_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jp_cpu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jl_nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(21, 21, 21)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jd_cpu1Layout = new javax.swing.GroupLayout(jd_cpu1.getContentPane());
        jd_cpu1.getContentPane().setLayout(jd_cpu1Layout);
        jd_cpu1Layout.setHorizontalGroup(
            jd_cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_cpu1Layout.setVerticalGroup(
            jd_cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(184, 46, 12));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jp_cpu2.setBackground(new java.awt.Color(204, 204, 204));
        jp_cpu2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jl_cpu2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jl_cpu2.setForeground(new java.awt.Color(255, 255, 255));
        jl_cpu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_cpu2.setText("0");

        javax.swing.GroupLayout jp_cpu2Layout = new javax.swing.GroupLayout(jp_cpu2);
        jp_cpu2.setLayout(jp_cpu2Layout);
        jp_cpu2Layout.setHorizontalGroup(
            jp_cpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jl_cpu2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );
        jp_cpu2Layout.setVerticalGroup(
            jp_cpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_cpu2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jl_cpu2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jt_cpu2_cpu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jt_cpu2_cpu1);
        if (jt_cpu2_cpu1.getColumnModel().getColumnCount() > 0) {
            jt_cpu2_cpu1.getColumnModel().getColumn(0).setResizable(false);
        }

        jt_cpu2_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Color", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jt_cpu2_jugador);
        if (jt_cpu2_jugador.getColumnModel().getColumnCount() > 0) {
            jt_cpu2_jugador.getColumnModel().getColumn(0).setResizable(false);
            jt_cpu2_jugador.getColumnModel().getColumn(1).setHeaderValue("Valor");
        }

        jButton4.setText("Jugar Carta");

        jButton5.setText("UNO!");

        jt_cpu2_cpu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jt_cpu2_cpu2);
        if (jt_cpu2_cpu2.getColumnModel().getColumnCount() > 0) {
            jt_cpu2_cpu2.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(431, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(298, 298, 298))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(jp_cpu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jp_cpu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(34, 34, 34))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jd_cpu2Layout = new javax.swing.GroupLayout(jd_cpu2.getContentPane());
        jd_cpu2.getContentPane().setLayout(jd_cpu2Layout);
        jd_cpu2Layout.setHorizontalGroup(
            jd_cpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_cpu2Layout.setVerticalGroup(
            jd_cpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(184, 46, 12));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jp_cpu3.setBackground(new java.awt.Color(204, 204, 204));
        jp_cpu3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jl_cpu3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jl_cpu3.setForeground(new java.awt.Color(255, 255, 255));
        jl_cpu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_cpu3.setText("0");

        javax.swing.GroupLayout jp_cpu3Layout = new javax.swing.GroupLayout(jp_cpu3);
        jp_cpu3.setLayout(jp_cpu3Layout);
        jp_cpu3Layout.setHorizontalGroup(
            jp_cpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jl_cpu3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );
        jp_cpu3Layout.setVerticalGroup(
            jp_cpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_cpu3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jl_cpu3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jt_cpu3_cpu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jt_cpu3_cpu1);
        if (jt_cpu3_cpu1.getColumnModel().getColumnCount() > 0) {
            jt_cpu3_cpu1.getColumnModel().getColumn(0).setResizable(false);
        }

        jt_cpu3_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Color", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jt_cpu3_jugador);
        if (jt_cpu3_jugador.getColumnModel().getColumnCount() > 0) {
            jt_cpu3_jugador.getColumnModel().getColumn(0).setResizable(false);
            jt_cpu3_jugador.getColumnModel().getColumn(1).setHeaderValue("Valor");
        }

        jButton6.setText("Jugar Carta");

        jButton7.setText("UNO!");

        jt_cpu3_cpu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jt_cpu3_cpu2);
        if (jt_cpu3_cpu2.getColumnModel().getColumnCount() > 0) {
            jt_cpu3_cpu2.getColumnModel().getColumn(0).setResizable(false);
        }

        jt_cpu3_cpu3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jt_cpu3_cpu3);
        if (jt_cpu3_cpu3.getColumnModel().getColumnCount() > 0) {
            jt_cpu3_cpu3.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(431, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jButton6))
                .addGap(298, 298, 298))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp_cpu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jp_cpu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(34, 34, 34))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jd_cpu3Layout = new javax.swing.GroupLayout(jd_cpu3.getContentPane());
        jd_cpu3.getContentPane().setLayout(jd_cpu3Layout);
        jd_cpu3Layout.setHorizontalGroup(
            jd_cpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jd_cpu3Layout.setVerticalGroup(
            jd_cpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setText("Escoja el nuevo color");

        cb_nuevocolor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rojo", "Azul", "Verde", "Amarillo" }));

        jButton8.setText("Conitnuar");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jd_WildsLayout = new javax.swing.GroupLayout(jd_Wilds.getContentPane());
        jd_Wilds.getContentPane().setLayout(jd_WildsLayout);
        jd_WildsLayout.setHorizontalGroup(
            jd_WildsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_WildsLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jd_WildsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jd_WildsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(cb_nuevocolor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jd_WildsLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton8)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jd_WildsLayout.setVerticalGroup(
            jd_WildsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jd_WildsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cb_nuevocolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNO");

        jLabel1.setText("Ingrese la cantidad de cpus:");

        sp_cpu.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));

        jLabel2.setText("Elija la dificultad");

        cb_dificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Medio", "Dificil", "Prepare To Die" }));

        jButton1.setText("Start Game");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel4.setText("Nombre");

        jLabel5.setText("Solo funciona con un cpu, mas o menos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_nombre)
                            .addComponent(sp_cpu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_dificultad, 0, 105, Short.MAX_VALUE))
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sp_cpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_dificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String nombre = tf_nombre.getText();
        switch (cb_dificultad.getSelectedIndex()){
            case 0:
                probabilidad = 20;
                break;
            case 1:
                probabilidad = 50;
                break;
            case 2:
                probabilidad = 80;
                break;
            case 3:
                probabilidad = 100;
                break;
        }
        jugadores.clear();
        
        jugadores.add(new Players(nombre));
        crearjugador();
        
        
        switch ((Integer) sp_cpu.getValue()){
            case 1:
                jugadores.add(new Players("CPU 1"));
                crearjugador();
                llenarBarajas();
                CartasIniciales(jp_cpu1, jl_cpu1);
                jl_nombre.setText(jugadores.get(0).getNombre());
                jl_nombre1.setText(jugadores.get(1).getNombre());
                jd_cpu1.setModal(true);
                jd_cpu1.pack();
                jd_cpu1.setLocationRelativeTo(this);
                jd_cpu1.setVisible(true);
                break;
            case 2:
                jugadores.add(new Players("CPU 1"));
                crearjugador();
                jugadores.add(new Players("CPU 2"));
                crearjugador();
                llenarBarajas();
                CartasIniciales(jp_cpu2, jl_cpu2);
                jd_cpu2.setModal(true);
                jd_cpu2.pack();
                jd_cpu2.setLocationRelativeTo(this);
                jd_cpu2.setVisible(true);
                break;
            case 3:
                jugadores.add(new Players("CPU 1"));
                crearjugador();
                jugadores.add(new Players("CPU 3"));
                crearjugador();
                jugadores.add(new Players("CPU 4"));
                crearjugador();
                llenarBarajas();
                CartasIniciales(jp_cpu3, jl_cpu3);
                jd_cpu3.setModal(true);
                jd_cpu3.pack();
                jd_cpu3.setLocationRelativeTo(this);
                jd_cpu3.setVisible(true);
                break;
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        int c= jt_cpu1_jugador.getSelectedRow();
        String n =jugadores.get(0).getCartas().get(c).getNumero()+"";
        if (jugadores.get(0).getCartas().get(c).getNumero() == 11){
            n = "+2";
        }
        if (jugadores.get(0).getCartas().get(c).getColor().equals(jp_cpu1.getBackground()) || n.equals(jl_cpu1.getText()) || jugadores.get(0).getCartas().get(c).getColor().equals(Color.BLACK)){
            actualizarCarta(jp_cpu1, jl_cpu1, 0, c);
            jugadores.get(0).getCartas().remove(c);
            llenarBarajas();
        }
        else{
            JOptionPane.showMessageDialog(jd_cpu1, "La carta es invalida, intentelo con otra");
        }
        if (jp_cpu1.getBackground().equals(Color.BLACK)){
            jd_Wilds.setModal(true);
            jd_Wilds.pack();
            jd_Wilds.setLocationRelativeTo(this);
            jd_Wilds.setVisible(true);
        }
        if (jugadores.size()<1){
            JOptionPane.showMessageDialog(jd_cpu1, "Felicidades, ha ganado");
            jd_cpu1.setVisible(false);
            jd_cpu1.setModal(false);
        }
        boolean val=false;
        for (int i = 0; i < jugadores.get(1).getCartas().size(); i++) {
            if (val==false){
                n =jugadores.get(1).getCartas().get(i).getNumero()+"";
                if (jugadores.get(1).getCartas().get(i).getNumero() == 11){
                    n = "+2";
                }
                if (jugadores.get(1).getCartas().get(i).getColor().equals(jp_cpu1.getBackground()) || n.equals(jl_cpu1.getText()) || jugadores.get(1).getCartas().get(i).getColor().equals(Color.BLACK)){
                    actualizarCarta(jp_cpu1, jl_cpu1, 1, i);
                    jugadores.get(1).getCartas().remove(i);
                    llenarBarajas();
                    val=true;
                }
                if (jp_cpu1.getBackground().equals(Color.BLACK)){
                    CambioColor(jp_cpu1, r.nextInt(4));
                }
            }
        }
        if (val==false){
            agregarCarta(1);
            llenarBarajas();
        }
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        CambioColor(jp_cpu1, cb_nuevocolor.getSelectedIndex());
        jd_Wilds.setVisible(false);
        jd_Wilds.setModal(false);
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        agregarCarta(0);
        llenarBarajas();
    }//GEN-LAST:event_jButton9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_dificultad;
    private javax.swing.JComboBox<String> cb_nuevocolor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JDialog jd_Wilds;
    private javax.swing.JDialog jd_cpu1;
    private javax.swing.JDialog jd_cpu2;
    private javax.swing.JDialog jd_cpu3;
    private javax.swing.JLabel jl_cpu1;
    private javax.swing.JLabel jl_cpu2;
    private javax.swing.JLabel jl_cpu3;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_nombre1;
    private javax.swing.JPanel jp_cpu1;
    private javax.swing.JPanel jp_cpu2;
    private javax.swing.JPanel jp_cpu3;
    private javax.swing.JTable jt_cpu1_cpu;
    private javax.swing.JTable jt_cpu1_jugador;
    private javax.swing.JTable jt_cpu2_cpu1;
    private javax.swing.JTable jt_cpu2_cpu2;
    private javax.swing.JTable jt_cpu2_jugador;
    private javax.swing.JTable jt_cpu3_cpu1;
    private javax.swing.JTable jt_cpu3_cpu2;
    private javax.swing.JTable jt_cpu3_cpu3;
    private javax.swing.JTable jt_cpu3_jugador;
    private javax.swing.JSpinner sp_cpu;
    private javax.swing.JTextField tf_nombre;
    // End of variables declaration//GEN-END:variables
int probabilidad=0;
Random r = new Random();
int ultcarta;
ArrayList <Players> jugadores = new ArrayList();
}
