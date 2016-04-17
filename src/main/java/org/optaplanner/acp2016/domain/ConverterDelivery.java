package org.optaplanner.acp2016.domain;

public class ConverterDelivery {

   private int id;

   private int startTime;
   private int requiredSulfurizationLevel;

   // indicates whether torpedo uses emergency pit for this delivery
   private boolean isEmergencyPit;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getStartTime() {
      return startTime;
   }

   public void setStartTime(int startTime) {
      this.startTime = startTime;
   }

   public int getRequiredSulfurizationLevel() {
      return requiredSulfurizationLevel;
   }

   public void setRequiredSulfurizationLevel(int requiredSulfurizationLevel) {
      this.requiredSulfurizationLevel = requiredSulfurizationLevel;
   }

   public boolean isEmergencyPit() {
      return isEmergencyPit;
   }

   public void setEmergencyPit(boolean emergencyPit) {
      isEmergencyPit = emergencyPit;
   }
}
