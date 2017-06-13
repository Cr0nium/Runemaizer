package swrunes;

import java.awt.event.ActionEvent;


public class Rune {
 
    private int id;
    private String set;
    private String slot;
    private String mainStat;
    private int mainStatValue;
    private String prefixStat;
    private int prefixStatValue;
    private int attackInPercentages;
    private int attackInNumbers;
    private int criticalRate;
    private int criticalDamage;
    private int speed;
    private int hitPointsInPercentages;
    private int hitPointsInNambers;
    private int defenseInPercentages;
    private int defenseInNumbers;
    private int accuracy;
    private int resistance; 
  
    public Rune(int Id,
            String Set,
            String Slot, 
            String MainStat,
            int MainStatValue,
            String PrefixStat, 
            int PrefixStatValue,
            int AttackInPercentages, 
            int AttackInNumbers, 
            int CriticalRate,
            int CriticalDamage,
            int Speed,
            int HitPointsInPercentages,
            int HitPointsInNambers,
            int DefenseInPercentages, 
            int DefenseInNumbers,
            int Accuracy,
            int Resistance) 
 { 
         this.id = Id;
         this.set = Set;
         this.slot = Slot;
         this.mainStat = MainStat;
         this.mainStatValue = MainStatValue;
         this.prefixStat = PrefixStat;
         this.prefixStatValue = PrefixStatValue;
         this.attackInPercentages = AttackInPercentages;
         this.attackInNumbers = AttackInNumbers;
         this.criticalRate = CriticalRate;
         this.criticalDamage = CriticalDamage;
         this.speed = Speed;
         this.hitPointsInPercentages = HitPointsInPercentages;
         this.hitPointsInNambers = HitPointsInNambers;
         this.defenseInPercentages = DefenseInPercentages;
         this.defenseInNumbers = DefenseInNumbers;
         this.accuracy = Accuracy;
         this.resistance = Resistance;
  }
 
 public int getId()
 {
     return id;
 }
 
 public int getAttackInPercentages()
 {
     return attackInPercentages;
 }
 
 public int getAttackInNumbers()
 {
     return attackInNumbers;
 }
 
 public String getSet()
 {
     return set;
 }
 
 public String getSlot()
 {
     return slot;
 }
 
 public String getMainStat()
 {
     return mainStat;
 }
 
 public int getMainStatValue()
 {
     return mainStatValue;
 }
 
 public String getPrefixStat()
 {
     return prefixStat;
 }
 
 public int getPrefixStatValue()
 {
     return prefixStatValue;
 }
 
 public int getCriticalRate()
 {
     return criticalRate;
 }
 
 public int getCriticalDamage()
 {
     return criticalDamage;
 }
 
 public int getSpeed()
 {
     return speed;
 }
 
 public int getHitPointsInPercentages()
 {
     return hitPointsInPercentages;
 }
 
 public int getHitPointsInNambers()
 {
     return hitPointsInNambers;
 }
 
 public int getDefenseInPercentages()
 {
     return defenseInPercentages;
 }
 
 public int getDefenseInNumbers()
 {
     return defenseInNumbers;
 }
 
 public int getAccuracy()
 {
     return accuracy;
 }
 
 public int getResistance()
 {
     return resistance;
 }
 
}
