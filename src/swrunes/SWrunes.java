/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swrunes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Cr0n
 */
public class SWrunes extends JFrame {
    
private static String searchq;
    
    public SWrunes() {
        // frame and panels adjustment 
        super("RUNEMAIZER");
        initComponents();
        Show_Runes_In_JTable();
        setLocation(300, 200);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("ADD RUNES");
        title.setTitleJustification(TitledBorder.CENTER);
        jPanel1.setBorder(title);
        TitledBorder titles;
        titles = BorderFactory.createTitledBorder("SEARCH RUNES");
        titles.setTitleJustification(TitledBorder.CENTER);
        jPanel2.setBorder(titles);
   
    }
     // method for connecting  
     public Connection getConnection()
   {
       Connection con;

       try {
           con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/swrunes?useSSL=false","root","Nbveh13");
           return con;
       } 
      catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
    // Getting a list of runes from the database sql for add runes + sql request
     public ArrayList<Rune> getRunesList()
   {
       ArrayList<Rune> runesList = new ArrayList<Rune>();
       com.mysql.jdbc.Connection connection = (com.mysql.jdbc.Connection) getConnection();
       
       String query = "SELECT * FROM  `runes` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);

           Rune runes;

           while(rs.next())
           {
               runes = new Rune(rs.getInt("rune_id"),rs.getString("cet"),rs.getString("slot"),rs.getString("ms"),rs.getInt("msv"),rs.getString("ps"),rs.getInt("psv"),rs.getInt("atkp"),rs.getInt("atks"),rs.getInt("cri_r"),rs.getInt("cri_d"),rs.getInt("spd"),rs.getInt("hpp"),rs.getInt("hps"),rs.getInt("defp"),rs.getInt("defs"),rs.getInt("accp"),rs.getInt("resp"));
               runesList.add(runes);
           }

       } 
      catch (Exception e) {
           e.printStackTrace();
       }
       return runesList;
   }
     
     // Getting a list of runes from the database sql for search runes
      public ArrayList<Rune> ListRunes()
    {
        ArrayList<Rune> runesList = new ArrayList<Rune>();
        
        Statement st;
        ResultSet rs;
        
        
        try{
            Connection con = getConnection();
            st = con.createStatement();
            // create diferent sql request based on the request fields
            if ((jComboBox_sset.getSelectedIndex() == 0) && (jComboBox_sslot.getSelectedIndex() != 0))
            {
            searchq = "SELECT * FROM runes WHERE ("+jComboBox_ssub1.getSelectedItem()+" "+jComboBox_br1.getSelectedItem()+" "+jTextField_ssub1.getText()+") and (slot = '"+jComboBox_sslot.getSelectedItem()+"')";
            }
            else if ((jComboBox_sset.getSelectedIndex() != 0) && (jComboBox_sslot.getSelectedIndex() == 0) ) {
            searchq = "SELECT * FROM runes WHERE ("+jComboBox_ssub1.getSelectedItem()+" "+jComboBox_br1.getSelectedItem()+" "+jTextField_ssub1.getText()+") and (cet = '"+jComboBox_sset.getSelectedItem()+"')";
            }
            else if ((jComboBox_sset.getSelectedIndex() != 0) && (jComboBox_sslot.getSelectedIndex() != 0) ) {
            searchq = "SELECT * FROM runes WHERE ("+jComboBox_ssub1.getSelectedItem()+" "+jComboBox_br1.getSelectedItem()+" "+jTextField_ssub1.getText()+") and (cet = '"+jComboBox_sset.getSelectedItem()+"') and (slot = '"+jComboBox_sslot.getSelectedItem()+"') ";
            }
            else {
            searchq = "SELECT * FROM runes WHERE "+jComboBox_ssub1.getSelectedItem()+" "+jComboBox_br1.getSelectedItem()+" "+jTextField_ssub1.getText()+"";
            }
            
            
            String searchQuery = searchq;
            rs = st.executeQuery(searchQuery);
            Rune runes;
            
            while(rs.next())
            {
                runes = new Rune(rs.getInt("rune_id"),rs.getString("cet"),rs.getString("slot"),rs.getString("ms"),rs.getInt("msv"),rs.getString("ps"),rs.getInt("psv"),rs.getInt("atkp"),rs.getInt("atks"),rs.getInt("cri_r"),rs.getInt("cri_d"),rs.getInt("spd"),rs.getInt("hpp"),rs.getInt("hps"),rs.getInt("defp"),rs.getInt("defs"),rs.getInt("accp"),rs.getInt("resp"));
                runesList.add(runes);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return runesList;
    }
     // Show the received list for add runes
     public void Show_Runes_In_JTable()
   {
       ArrayList<Rune> list = getRunesList();
       DefaultTableModel model = (DefaultTableModel)jTable_runes.getModel();
       Object[] row = new Object[18];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getSet();
           row[2] = list.get(i).getSlot();
           row[3] = list.get(i).getMainStat();
           row[4] = list.get(i).getMainStatValue();
           row[5] = list.get(i).getPrefixStat();
           row[6] = list.get(i).getPrefixStatValue();
           row[7] = list.get(i).getAttackInPercentages();
           row[8] = list.get(i).getAttackInNumbers();
           row[9] = list.get(i).getCriticalRate();
           row[10] = list.get(i).getCriticalDamage();
           row[11] = list.get(i).getSpeed();
           row[12] = list.get(i).getHitPointsInPercentages();
           row[13] = list.get(i).getHitPointsInNambers();
           row[14] = list.get(i).getDefenseInPercentages();
           row[15] = list.get(i).getDefenseInNumbers();
           row[16] = list.get(i).getAccuracy();
           row[17] = list.get(i).getResistance();
           
           model.addRow(row);
       }
    }
     // Show the received list for search runes
     public void findRunes()
   {
       ArrayList<Rune> list = ListRunes();
       DefaultTableModel model = (DefaultTableModel)jTable_srunes.getModel();
       model.setRowCount(0);
       model.setColumnIdentifiers(new Object[]{"Id", "Set", "Slot", "Ms", "Msv", "Ps", "Psv", "Atkp", "Atks", "CriR", "CriD", "Spd", "Hpp", "Hps", "Defp", "Defs", "Accp", "Resp"});
       Object[] row = new Object[18];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getSet();
           row[2] = list.get(i).getSlot();
           row[3] = list.get(i).getMainStat();
           row[4] = list.get(i).getMainStatValue();
           row[5] = list.get(i).getPrefixStat();
           row[6] = list.get(i).getPrefixStatValue();
           row[7] = list.get(i).getAttackInPercentages();
           row[8] = list.get(i).getAttackInNumbers();
           row[9] = list.get(i).getCriticalRate();
           row[10] = list.get(i).getCriticalDamage();
           row[11] = list.get(i).getSpeed();
           row[12] = list.get(i).getHitPointsInPercentages();
           row[13] = list.get(i).getHitPointsInNambers();
           row[14] = list.get(i).getDefenseInPercentages();
           row[15] = list.get(i).getDefenseInNumbers();
           row[16] = list.get(i).getAccuracy();
           row[17] = list.get(i).getResistance();
           model.addRow(row);
       }
       jTable_srunes.setModel(model);
    }
     
                 
     // sql request organization
     public void executeSQlQuery(String query, String message)
   {
       com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable_runes.getModel();
               model.setRowCount(0);
               Show_Runes_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Data "+message+" Succefully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
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

        jFrame_search = new javax.swing.JFrame();
        jPanel_search = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_srunes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_msv = new javax.swing.JTextField();
        jTextField_psv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_atkp = new javax.swing.JTextField();
        jTextField_atks = new javax.swing.JTextField();
        jTextField_crir = new javax.swing.JTextField();
        jTextField_crid = new javax.swing.JTextField();
        jTextField_spd = new javax.swing.JTextField();
        jTextField_hpp = new javax.swing.JTextField();
        jTextField_hps = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField_defp = new javax.swing.JTextField();
        jTextField_defs = new javax.swing.JTextField();
        jTextField_accp = new javax.swing.JTextField();
        jTextField_resp = new javax.swing.JTextField();
        jComboBox_set = new javax.swing.JComboBox<>();
        jComboBox_slot = new javax.swing.JComboBox<>();
        jComboBox_ms = new javax.swing.JComboBox<>();
        jComboBox_ps = new javax.swing.JComboBox<>();
        jButton_add = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox_sset = new javax.swing.JComboBox<>();
        jComboBox_sslot = new javax.swing.JComboBox<>();
        jComboBox_ssub1 = new javax.swing.JComboBox<>();
        jComboBox_ssub2 = new javax.swing.JComboBox<>();
        jComboBox_br1 = new javax.swing.JComboBox<>();
        jComboBox_br2 = new javax.swing.JComboBox<>();
        jTextField_ssub1 = new javax.swing.JTextField();
        jTextField_ssub2 = new javax.swing.JTextField();
        jButton_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_runes = new javax.swing.JTable();

        jTable_srunes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Set", "Slot", "Ms", "Msv", "Ps", "Psv", "Atkp", "Atks", "CriR", "CriD", "Spd", "Hpp", "Hps", "Defp", "Defs", "Accp", "Resp"
            }
        ));
        jScrollPane4.setViewportView(jTable_srunes);

        javax.swing.GroupLayout jPanel_searchLayout = new javax.swing.GroupLayout(jPanel_search);
        jPanel_search.setLayout(jPanel_searchLayout);
        jPanel_searchLayout.setHorizontalGroup(
            jPanel_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        jPanel_searchLayout.setVerticalGroup(
            jPanel_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame_searchLayout = new javax.swing.GroupLayout(jFrame_search.getContentPane());
        jFrame_search.getContentPane().setLayout(jFrame_searchLayout);
        jFrame_searchLayout.setHorizontalGroup(
            jFrame_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame_searchLayout.setVerticalGroup(
            jFrame_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SET");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SLOT");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Main Stat");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MS Value");
        jLabel5.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(60, 16));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Prefix Stat");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PS Value");

        jTextField_id.setText("  ");

        jTextField_msv.setText("0");

        jTextField_psv.setText("0");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ATK%");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("ATK+");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CRI.rate");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CRI.dmg");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("HP%");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("SPD");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("HP+");

        jTextField_atkp.setText("0");

        jTextField_atks.setText("0");

        jTextField_crir.setText("0");

        jTextField_crid.setText("0");

        jTextField_spd.setText("0");

        jTextField_hpp.setText("0");

        jTextField_hps.setText("0");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("DEF%");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("DEF+");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ACC%");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("RES%");

        jTextField_defp.setText("0");

        jTextField_defs.setText("0");

        jTextField_accp.setText("0");

        jTextField_resp.setText("0");

        jComboBox_set.setMaximumRowCount(21);
        jComboBox_set.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "energy", "fatal", "blade", "swift", "despair", "focus", "guard", "endure", "shield", "violent", "revenge", "will", "nemesis", "vampire", "destroy", "rage", "fight", "determin.", "enhance", "accuracy", "tolerance" }));
        jComboBox_set.setToolTipText("");

        jComboBox_slot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        jComboBox_slot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jComboBox_ms.setMaximumRowCount(11);
        jComboBox_ms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "atkp", "atks", "cri_r", "cri_d", "spd", "hpp", "hps", "defp", "defs", "accp", "resp" }));

        jComboBox_ps.setMaximumRowCount(12);
        jComboBox_ps.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "atkp", "atks", "cri_r", "cri_d", "spd", "hpp", "hps", "defp", "defs", "accp", "resp" }));

        jButton_add.setText("ADD");
        jButton_add.setFocusPainted(false);
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_update.setText("UPDATE");
        jButton_update.setFocusPainted(false);
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setText("DELETE");
        jButton_delete.setFocusPainted(false);
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_atkp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_defp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_set, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_atks, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_slot, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_crir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_accp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_defs, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_ps, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_psv, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_hpp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_hps, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_msv, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_spd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_ms, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_crid, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_resp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_atkp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField_defp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(jTextField_atks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField_defs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_set, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_crir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField_accp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_slot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(jTextField_crid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField_resp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_ms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_msv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField_spd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel12)
                        .addComponent(jTextField_hpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_ps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_update, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_psv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField_hps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("SET");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("SLOT");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("SUB Stat 1");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("SUB Stat 2");

        jComboBox_sset.setMaximumRowCount(22);
        jComboBox_sset.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "any", "energy", "fatal", "blade", "swift", "despair", "focus", "guard", "endure", "shield", "violent", "revenge", "will", "nemesis", "vampire", "destroy", "rage", "fight", "determin.", "enhance", "accuracy", "tolerance" }));

        jComboBox_sslot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "any", "1", "2", "3", "4", "5", "6" }));

        jComboBox_ssub1.setMaximumRowCount(13);
        jComboBox_ssub1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "atkp", "atks", "cri_r", "cri_d", "spd", "hpp", "hps", "defp", "defs", "accp", "resp" }));

        jComboBox_ssub2.setMaximumRowCount(13);
        jComboBox_ssub2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "atkp", "atks", "cri_r", "cri_d", "spd", "hpp", "hps", "defp", "defs", "accp", "resp" }));
        jComboBox_ssub2.setSelectedIndex(-1);
        jComboBox_ssub2.setToolTipText("");

        jComboBox_br1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">", "=" }));

        jComboBox_br2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">", "=" }));
        jComboBox_br2.setSelectedIndex(-1);

        jButton_search.setText("SEARCH");
        jButton_search.setFocusPainted(false);
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox_sset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_sslot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox_ssub2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_br2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_ssub2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox_ssub1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_br1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_ssub1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox_sset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jComboBox_sslot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jComboBox_ssub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_br1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ssub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jComboBox_ssub2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_br2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_ssub2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable_runes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Set", "Slot", "Ms", "Msv", "Ps", "Psv", "Atkp", "Atks", "CriR", "CriD", "Spd", "Hpp", "Hps", "Defp", "Defs", "Accp", "Resp"
            }
        ));
        jTable_runes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_runesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_runes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
// sql request for addition
    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
      String query = "INSERT INTO `runes`(`cet`, `slot`, `ms`, `msv`, `ps`, `psv`, `atkp`, `atks`, `cri_r`, `cri_d`, `spd`, `hpp`, `hps`, `defp`, `defs`, `accp`, `resp`) VALUES ('"+jComboBox_set.getSelectedItem()+"','"+jComboBox_slot.getSelectedItem()+"','"+jComboBox_ms.getSelectedItem()+"',"+jTextField_msv.getText()+",'"+jComboBox_ps.getSelectedItem()+"',"+jTextField_psv.getText()+","+jTextField_atkp.getText()+","+jTextField_atks.getText()+","+jTextField_crir.getText()+","+jTextField_crid.getText()+","+jTextField_spd.getText()+","+jTextField_hpp.getText()+","+jTextField_hps.getText()+","+jTextField_defp.getText()+","+jTextField_defs.getText()+","+jTextField_accp.getText()+","+jTextField_resp.getText()+")";
      executeSQlQuery(query, "ADDED");
    }//GEN-LAST:event_jButton_addActionPerformed
// sqlrequest for delete
    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        String query = "DELETE FROM `runes` WHERE rune_id = "+jTextField_id.getText();
         executeSQlQuery(query, "Deleted");
    }//GEN-LAST:event_jButton_deleteActionPerformed
// sql request for update
    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
       String query = "UPDATE `runes` SET `cet`='"+jComboBox_set.getSelectedItem()+"',`slot`='"+jComboBox_slot.getSelectedItem()+"',`ms`='"+jComboBox_ms.getSelectedItem()+"',`msv`="+jTextField_msv.getText()+",`ps`='"+jComboBox_ps.getSelectedItem()+"',`psv`="+jTextField_psv.getText()+",`atkp`="+jTextField_atkp.getText()+",`atks`="+jTextField_atks.getText()+",`cri_r`="+jTextField_crir.getText()+",`cri_d`="+jTextField_crid.getText()+",`spd`="+jTextField_spd.getText()+",`hpp`="+jTextField_hpp.getText()+",`hps`="+jTextField_hps.getText()+",`defp`="+jTextField_defp.getText()+",`defs`="+jTextField_defs.getText()+",`accp`="+jTextField_accp.getText()+",`resp`="+jTextField_resp.getText()+" WHERE `rune_id` = "+jTextField_id.getText();
       executeSQlQuery(query, "Updated");
    }//GEN-LAST:event_jButton_updateActionPerformed
//Filling fields when you click on a jtable_runes
    private void jTable_runesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_runesMouseClicked
        int i = jTable_runes.getSelectedRow();

        TableModel model = jTable_runes.getModel();
        
         // Display Slected Row In JTexteFields
        jTextField_id.setText(model.getValueAt(i,0).toString());

        jComboBox_set.setSelectedItem(model.getValueAt(i,1));

        jComboBox_slot.setSelectedItem(model.getValueAt(i,2));

        jComboBox_ms.setSelectedItem(model.getValueAt(i,3));
        
        jTextField_msv.setText(model.getValueAt(i,4).toString());
        
        jComboBox_ps.setSelectedItem(model.getValueAt(i,5));
        
        jTextField_psv.setText(model.getValueAt(i,6).toString());
        
        jTextField_atkp.setText(model.getValueAt(i,7).toString());
        
        jTextField_atks.setText(model.getValueAt(i,8).toString());
        
        jTextField_crir.setText(model.getValueAt(i,9).toString());
        
        jTextField_crid.setText(model.getValueAt(i,10).toString());
        
        jTextField_spd.setText(model.getValueAt(i,11).toString());
        
        jTextField_hpp.setText(model.getValueAt(i,12).toString());
        
        jTextField_hps.setText(model.getValueAt(i,13).toString());
        
        jTextField_defp.setText(model.getValueAt(i,14).toString());
        
        jTextField_defs.setText(model.getValueAt(i,15).toString());
        
        jTextField_accp.setText(model.getValueAt(i,16).toString());
        
        jTextField_resp.setText(model.getValueAt(i,17).toString());
    }//GEN-LAST:event_jTable_runesMouseClicked
// sql request for searche
    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        
        findRunes();
        jFrame_search.setTitle("SEARCHING RESULT");
        jFrame_search.setDefaultCloseOperation(jFrame_search.DISPOSE_ON_CLOSE);
        jFrame_search.setLocation(800, 450);
        jFrame_search.setSize(800,600);
        jFrame_search.setVisible(true);
    }//GEN-LAST:event_jButton_searchActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SWrunes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SWrunes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SWrunes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SWrunes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
 
         
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SWrunes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_search;
    private javax.swing.JButton jButton_update;
    private javax.swing.JComboBox<String> jComboBox_br1;
    private javax.swing.JComboBox<String> jComboBox_br2;
    private javax.swing.JComboBox<String> jComboBox_ms;
    private javax.swing.JComboBox<String> jComboBox_ps;
    private javax.swing.JComboBox<String> jComboBox_set;
    private javax.swing.JComboBox<String> jComboBox_slot;
    private javax.swing.JComboBox<String> jComboBox_sset;
    private javax.swing.JComboBox<String> jComboBox_sslot;
    private javax.swing.JComboBox<String> jComboBox_ssub1;
    private javax.swing.JComboBox<String> jComboBox_ssub2;
    private javax.swing.JFrame jFrame_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_search;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_runes;
    private javax.swing.JTable jTable_srunes;
    private javax.swing.JTextField jTextField_accp;
    private javax.swing.JTextField jTextField_atkp;
    private javax.swing.JTextField jTextField_atks;
    private javax.swing.JTextField jTextField_crid;
    private javax.swing.JTextField jTextField_crir;
    private javax.swing.JTextField jTextField_defp;
    private javax.swing.JTextField jTextField_defs;
    private javax.swing.JTextField jTextField_hpp;
    private javax.swing.JTextField jTextField_hps;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_msv;
    private javax.swing.JTextField jTextField_psv;
    private javax.swing.JTextField jTextField_resp;
    private javax.swing.JTextField jTextField_spd;
    private javax.swing.JTextField jTextField_ssub1;
    private javax.swing.JTextField jTextField_ssub2;
    // End of variables declaration//GEN-END:variables
}
