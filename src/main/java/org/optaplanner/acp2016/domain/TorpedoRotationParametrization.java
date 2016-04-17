package org.optaplanner.acp2016.domain;

public class TorpedoRotationParametrization {

   private int emptyBufferToBlastFurnaceDuration;
   private int blastFurnaceToBufferZoneDuration;
   private int bufferZoneToDesulfurizationDuration;
   private int desulfurizationToConverterDuration;
   private int converterToEmptyBufferDuration;

   private int blastFurnaceToEmptyBufferViaEmergencyPitDuration;

   private int blastFurnaceStopDuration;
   private int desulfurizationDuration;
   private int converterStopDuration;

   private int bufferZoneCapacity;
   private int desulfurizationCapacity;
   private int converterCapacity;

   public int getEmptyBufferToBlastFurnaceDuration() {
      return emptyBufferToBlastFurnaceDuration;
   }

   public void setEmptyBufferToBlastFurnaceDuration(int emptyBufferToBlastFurnaceDuration) {
      this.emptyBufferToBlastFurnaceDuration = emptyBufferToBlastFurnaceDuration;
   }

   public int getBlastFurnaceToBufferZoneDuration() {
      return blastFurnaceToBufferZoneDuration;
   }

   public void setBlastFurnaceToBufferZoneDuration(int blastFurnaceToBufferZoneDuration) {
      this.blastFurnaceToBufferZoneDuration = blastFurnaceToBufferZoneDuration;
   }

   public int getBufferZoneToDesulfurizationDuration() {
      return bufferZoneToDesulfurizationDuration;
   }

   public void setBufferZoneToDesulfurizationDuration(int bufferZoneToDesulfurizationDuration) {
      this.bufferZoneToDesulfurizationDuration = bufferZoneToDesulfurizationDuration;
   }

   public int getDesulfurizationToConverterDuration() {
      return desulfurizationToConverterDuration;
   }

   public void setDesulfurizationToConverterDuration(int desulfurizationToConverterDuration) {
      this.desulfurizationToConverterDuration = desulfurizationToConverterDuration;
   }

   public int getConverterToEmptyBufferDuration() {
      return converterToEmptyBufferDuration;
   }

   public void setConverterToEmptyBufferDuration(int converterToEmptyBufferDuration) {
      this.converterToEmptyBufferDuration = converterToEmptyBufferDuration;
   }

   public int getBlastFurnaceToEmptyBufferViaEmergencyPitDuration() {
      return blastFurnaceToEmptyBufferViaEmergencyPitDuration;
   }

   public void setBlastFurnaceToEmptyBufferViaEmergencyPitDuration(int blastFurnaceToEmptyBufferViaEmergencyPitDuration) {
      this.blastFurnaceToEmptyBufferViaEmergencyPitDuration = blastFurnaceToEmptyBufferViaEmergencyPitDuration;
   }

   public int getBlastFurnaceStopDuration() {
      return blastFurnaceStopDuration;
   }

   public void setBlastFurnaceStopDuration(int blastFurnaceStopDuration) {
      this.blastFurnaceStopDuration = blastFurnaceStopDuration;
   }

   public int getDesulfurizationDuration() {
      return desulfurizationDuration;
   }

   public void setDesulfurizationDuration(int desulfurizationDuration) {
      this.desulfurizationDuration = desulfurizationDuration;
   }

   public int getConverterStopDuration() {
      return converterStopDuration;
   }

   public void setConverterStopDuration(int converterStopDuration) {
      this.converterStopDuration = converterStopDuration;
   }

   public int getBufferZoneCapacity() {
      return bufferZoneCapacity;
   }

   public void setBufferZoneCapacity(int bufferZoneCapacity) {
      this.bufferZoneCapacity = bufferZoneCapacity;
   }

   public int getDesulfurizationCapacity() {
      return desulfurizationCapacity;
   }

   public void setDesulfurizationCapacity(int desulfurizationCapacity) {
      this.desulfurizationCapacity = desulfurizationCapacity;
   }

   public int getConverterCapacity() {
      return converterCapacity;
   }

   public void setConverterCapacity(int converterCapacity) {
      this.converterCapacity = converterCapacity;
   }
}
