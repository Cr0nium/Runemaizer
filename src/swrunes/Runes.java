package swrunes;

import java.awt.event.ActionEvent;


public class Runes {
 
    private int id;
    private String set;
    private String slot;
    private String ms;
    private int msv;
    private String ps;
    private int psv;
    private int atkp;
    private int atks;
    private int cri_r;
    private int cri_d;
    private int spd;
    private int hpp;
    private int hps;
    private int defp;
    private int defs;
    private int accp;
    private int resp; 
  
    public Runes(int Id, String Set, String Slot, String Ms, int Msv, String Ps, int Psv, int Atkp, int Atks, int CriR, int CriD, int Spd, int Hpp, int Hps, int Defp, int Defs, int Accp, int Resp) 
 { 
         this.id = Id;
         this.set = Set;
         this.slot = Slot;
         this.ms = Ms;
         this.msv = Msv;
         this.ps = Ps;
         this.psv = Psv;
         this.atkp = Atkp;
         this.atks = Atks;
         this.cri_r = CriR;
         this.cri_d = CriD;
         this.spd = Spd;
         this.hpp = Hpp;
         this.hps = Hps;
         this.defp = Defp;
         this.defs = Defs;
         this.accp = Accp;
         this.resp = Resp;
  }
 
 public int getId()
 {
     return id;
 }
 
 public int getAtkp()
 {
     return atkp;
 }
 
 public int getAtks()
 {
     return atks;
 }
 
 public String getSet()
 {
     return set;
 }
 
 public String getSlot()
 {
     return slot;
 }
 
 public String getMs()
 {
     return ms;
 }
 
 public int getMsv()
 {
     return msv;
 }
 
 public String getPs()
 {
     return ps;
 }
 
 public int getPsv()
 {
     return psv;
 }
 
 public int getCriR()
 {
     return cri_r;
 }
 
 public int getCriD()
 {
     return cri_d;
 }
 
 public int getSpd()
 {
     return spd;
 }
 
 public int getHpp()
 {
     return hpp;
 }
 
 public int getHps()
 {
     return hps;
 }
 
 public int getDefp()
 {
     return defp;
 }
 
 public int getDefs()
 {
     return defs;
 }
 
 public int getAccp()
 {
     return accp;
 }
 
 public int getResp()
 {
     return resp;
 }

    void jComboBox_setActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    void jTextField_psvActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
 
 
}
