package org.optaplanner.acp2016.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class TorpedoTripAssignment {

   private TorpedoRotationParametrization torpedoRotationParametrization;

   private int id;

   private BlastFurnaceDelivery blastFurnaceDelivery;
   private ConverterDelivery converterDelivery;

   private Integer emptyBufferToBlastFurnaceDeliveryDuration;
   private Integer blastFurnaceDeliveryToBufferZoneDuration;

   private Integer bufferZoneDuration;
   private Integer bufferZoneToDesulfurizationDuration;

   private Integer desulfurizationDuration;
   private Integer desulfurizationToConverterDeliveryDuration;

   private Integer converterStopDuration;
   private Integer converterToEmptyBufferDuration;

   // optional emergency pit path
   private Integer blastFurnaceDeliveryToEmptyBufferDuration;

   public TorpedoRotationParametrization getTorpedoRotationParametrization() {
      return torpedoRotationParametrization;
   }

   public void setTorpedoRotationParametrization(TorpedoRotationParametrization torpedoRotationParametrization) {
      this.torpedoRotationParametrization = torpedoRotationParametrization;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public BlastFurnaceDelivery getBlastFurnaceDelivery() {
      return blastFurnaceDelivery;
   }

   public void setBlastFurnaceDelivery(BlastFurnaceDelivery blastFurnaceDelivery) {
      this.blastFurnaceDelivery = blastFurnaceDelivery;
   }

   @PlanningVariable(valueRangeProviderRefs = "converterDeliveryRange")
   public ConverterDelivery getConverterDelivery() {
      return converterDelivery;
   }

   public void setConverterDelivery(ConverterDelivery converterDelivery) {
      this.converterDelivery = converterDelivery;
   }

   @PlanningVariable(valueRangeProviderRefs = "emptyBufferToBlastFurnaceDeliveryRange")
   public Integer getEmptyBufferToBlastFurnaceDeliveryDuration() {
      return emptyBufferToBlastFurnaceDeliveryDuration;
   }

   public void setEmptyBufferToBlastFurnaceDeliveryDuration(Integer emptyBufferToBlastFurnaceDeliveryDuration) {
      this.emptyBufferToBlastFurnaceDeliveryDuration = emptyBufferToBlastFurnaceDeliveryDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "blastFurnaceDeliveryToBufferZoneRange")
   public Integer getBlastFurnaceDeliveryToBufferZoneDuration() {
      return blastFurnaceDeliveryToBufferZoneDuration;
   }

   public void setBlastFurnaceDeliveryToBufferZoneDuration(Integer blastFurnaceDeliveryToBufferZoneDuration) {
      this.blastFurnaceDeliveryToBufferZoneDuration = blastFurnaceDeliveryToBufferZoneDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "positiveRange")
   public Integer getBufferZoneDuration() {
      return bufferZoneDuration;
   }

   public void setBufferZoneDuration(Integer bufferZoneDuration) {
      this.bufferZoneDuration = bufferZoneDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "bufferZoneToDesulfurizationRange")
   public Integer getBufferZoneToDesulfurizationDuration() {
      return bufferZoneToDesulfurizationDuration;
   }

   public void setBufferZoneToDesulfurizationDuration(Integer bufferZoneToDesulfurizationDuration) {
      this.bufferZoneToDesulfurizationDuration = bufferZoneToDesulfurizationDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "positiveRange")
   public Integer getDesulfurizationDuration() {
      return desulfurizationDuration;
   }

   public void setDesulfurizationDuration(Integer desulfurizationDuration) {
      this.desulfurizationDuration = desulfurizationDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "desulfurizationToConverterRange")
   public Integer getDesulfurizationToConverterDeliveryDuration() {
      return desulfurizationToConverterDeliveryDuration;
   }

   public void setDesulfurizationToConverterDeliveryDuration(Integer desulfurizationToConverterDeliveryDuration) {
      this.desulfurizationToConverterDeliveryDuration = desulfurizationToConverterDeliveryDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "converterStopDurationRange")
   public Integer getConverterStopDuration() {
      return converterStopDuration;
   }

   public void setConverterStopDuration(Integer converterStopDuration) {
      this.converterStopDuration = converterStopDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "converterToEmptyBufferRange")
   public Integer getConverterToEmptyBufferDuration() {
      return converterToEmptyBufferDuration;
   }

   public void setConverterToEmptyBufferDuration(Integer converterToEmptyBufferDuration) {
      this.converterToEmptyBufferDuration = converterToEmptyBufferDuration;
   }

   @PlanningVariable(valueRangeProviderRefs = "blastFurnaceDeliveryToEmptyBufferRange")
   public Integer getBlastFurnaceDeliveryToEmptyBufferDuration() {
      return blastFurnaceDeliveryToEmptyBufferDuration;
   }

   public void setBlastFurnaceDeliveryToEmptyBufferDuration(Integer blastFurnaceDeliveryToEmptyBufferDuration) {
      this.blastFurnaceDeliveryToEmptyBufferDuration = blastFurnaceDeliveryToEmptyBufferDuration;
   }

   public int getBlastFurnaceArrival() {
      return blastFurnaceDelivery.getStartTime();
   }

   public int getBlastFurnaceDeparture() {
      return getBlastFurnaceArrival() + torpedoRotationParametrization.getBlastFurnaceStopDuration();
   }

   public int getBufferZoneArrival() {
      if (blastFurnaceDeliveryToBufferZoneDuration == null) {
         return getBlastFurnaceDeparture();
      }
      return getBlastFurnaceDeparture() + blastFurnaceDeliveryToBufferZoneDuration;
   }

   public int getBufferZoneDeparture() {
      if (bufferZoneDuration == null) {
         return getBufferZoneArrival();
      }
      return getBufferZoneArrival() + bufferZoneDuration;
   }

   public int getDesulfurizationArrival() {
      if (bufferZoneToDesulfurizationDuration == null) {
         return getBufferZoneDeparture();
      }
      return getBufferZoneDeparture() + desulfurizationDuration;
   }

   public int getDesulfurizationDeparture() {
      if (desulfurizationDuration == null) {
         return getDesulfurizationArrival();
      }
      return getDesulfurizationArrival() + desulfurizationDuration;
   }

   public int getConverterArrival() {
      if (desulfurizationToConverterDeliveryDuration == null) {
         return getDesulfurizationDeparture();
      }
      return getDesulfurizationDeparture() + desulfurizationToConverterDeliveryDuration;
   }

   public int getConverterDeparture() {
      if (converterStopDuration == null) {
         return getConverterArrival();
      }
      return getConverterArrival() + converterStopDuration;
   }

   public int getEmptyBufferArrival() {
      if (converterDelivery == null || converterDelivery.isEmergencyPit()) {
         if (blastFurnaceDeliveryToEmptyBufferDuration == null) {
            return getBlastFurnaceDeparture();
         }
         return getBlastFurnaceDeparture() + blastFurnaceDeliveryToEmptyBufferDuration;
      } else {
         if (converterToEmptyBufferDuration == null) {
            return getConverterDeparture();
         }
         return getConverterDeparture() + converterToEmptyBufferDuration;
      }
   }

   public int getEmptyBufferDeparture() {
      if (emptyBufferToBlastFurnaceDeliveryDuration == null) {
         return getBlastFurnaceArrival();
      }
      return getBlastFurnaceArrival() - emptyBufferToBlastFurnaceDeliveryDuration;
   }

   public int getMinimumDesulfurizationDuration() {
      if (converterDelivery != null && !converterDelivery.isEmergencyPit()) {
         int requiredDesulfurizationDuration = blastFurnaceDelivery.getProducedSulfurizationLevel() - converterDelivery.getRequiredSulfurizationLevel();
         if (requiredDesulfurizationDuration > 0) {
            return requiredDesulfurizationDuration * torpedoRotationParametrization.getDesulfurizationDuration();
         }
      }
      return 0;
   }
}
