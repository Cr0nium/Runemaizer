package swrunes;


public class Runes {
 
    private int id;
//    private String set;
//    private int slot;
//    private String ms;
//    private int msv;
//    private String ps;
//    private int psv;
    private int atkp;
    private int atks;
//    private int cri_r;
//    private int cri_d;
//    private int spd;
//    private int hpp;
//    private int hps;
//    private int defp;
//    private int defs;
//    private int accp;
//    private int resp; 
  
    public Runes(int Id, int Atkp, int Atks) 
 { 
         this.id = Id;
  //       this.set = Set;
  //       this.slot = Slot;
  //       this.ms = Ms;
  //       this.msv = Msv;
  //       this.ps = Ps;
  //       this.psv = Psv;
         this.atkp = Atkp;
         this.atks = Atks;
  //       this.cri_r = CriR;
  //       this.cri_d = CriD;
  //       this.spd = Spd;
  //       this.hpp = Hpp;
  //       this.hps = Hps;
  //       this.defp = Defp;
  //       this.defs = Defs;
  //       this.accp = Accp;
  //       this.resp = Resp;
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
 
}
