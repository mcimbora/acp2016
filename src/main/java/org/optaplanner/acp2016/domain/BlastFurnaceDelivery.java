package org.optaplanner.acp2016.domain;

public class BlastFurnaceDelivery {

   private int id;

   private int startTime;
   private int producedSulfurizationLevel;

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

   public int getProducedSulfurizationLevel() {
      return producedSulfurizationLevel;
   }

   public void setProducedSulfurizationLevel(int producedSulfurizationLevel) {
      this.producedSulfurizationLevel = producedSulfurizationLevel;
   }
}
