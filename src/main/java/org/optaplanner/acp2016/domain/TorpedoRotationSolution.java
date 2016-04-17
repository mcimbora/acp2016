package org.optaplanner.acp2016.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.CountableValueRange;
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class TorpedoRotationSolution implements Solution<HardSoftScore> {

   private TorpedoRotationParametrization torpedoRotationParametrization;

   private List<TorpedoTripAssignment> torpedoTripAssignmentList;
   private List<BlastFurnaceDelivery> blastFurnaceDeliveryList;
   private List<ConverterDelivery> converterDeliveryList;

   private int minimumTime;
   private int maximumTime;

   private HardSoftScore score;

   public TorpedoRotationParametrization getTorpedoRotationParametrization() {
      return torpedoRotationParametrization;
   }

   public void setTorpedoRotationParametrization(TorpedoRotationParametrization torpedoRotationParametrization) {
      this.torpedoRotationParametrization = torpedoRotationParametrization;
   }

   @PlanningEntityCollectionProperty
   public List<TorpedoTripAssignment> getTorpedoTripAssignmentList() {
      return torpedoTripAssignmentList;
   }

   public void setTorpedoTripAssignmentList(List<TorpedoTripAssignment> torpedoTripAssignmentList) {
      this.torpedoTripAssignmentList = torpedoTripAssignmentList;
   }

   public List<BlastFurnaceDelivery> getBlastFurnaceDeliveryList() {
      return blastFurnaceDeliveryList;
   }

   public void setBlastFurnaceDeliveryList(List<BlastFurnaceDelivery> blastFurnaceDeliveryList) {
      this.blastFurnaceDeliveryList = blastFurnaceDeliveryList;
   }

   @ValueRangeProvider(id= "converterDeliveryRange")
   public List<ConverterDelivery> getConverterDeliveryList() {
      return converterDeliveryList;
   }

   public void setConverterDeliveryList(List<ConverterDelivery> converterDeliveryList) {
      this.converterDeliveryList = converterDeliveryList;
   }

   public int getMinimumTime() {
      return minimumTime;
   }

   public void setMinimumTime(int minimumTime) {
      this.minimumTime = minimumTime;
   }

   public int getMaximumTime() {
      return maximumTime;
   }

   public void setMaximumTime(int maximumTime) {
      this.maximumTime = maximumTime;
   }


   @ValueRangeProvider(id = "positiveRange")
   public CountableValueRange<Integer> getPositiveRange() {
      return ValueRangeFactory.createIntValueRange(0, maximumTime);
   }

   @ValueRangeProvider(id = "emptyBufferToBlastFurnaceDeliveryRange")
   public CountableValueRange<Integer> getEmptyBufferToBlastFurnaceDelivery() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getEmptyBufferToBlastFurnaceDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "blastFurnaceDeliveryToBufferZoneRange")
   public CountableValueRange<Integer> getBlastFurnaceDeliveryToBufferZoneRange() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getBlastFurnaceToBufferZoneDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "bufferZoneToDesulfurizationRange")
   public CountableValueRange<Integer> getBufferZoneToDesulfurizationRange() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getBufferZoneToDesulfurizationDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "desulfurizationToConverterRange")
   public CountableValueRange<Integer> getDesulfurizationToConverterRange() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getDesulfurizationToConverterDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "converterStopDurationRange")
   public CountableValueRange<Integer> getConverterStopDurationRange() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getConverterStopDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "converterToEmptyBufferRange")
   public CountableValueRange<Integer> getConverterToEmptyBuffer() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getConverterToEmptyBufferDuration(), maximumTime);
   }

   @ValueRangeProvider(id = "blastFurnaceDeliveryToEmptyBufferRange")
   public CountableValueRange<Integer> getBlastFurnaceDeliveryToEmptyBuffer() {
      return ValueRangeFactory.createIntValueRange(torpedoRotationParametrization.getBlastFurnaceToEmptyBufferViaEmergencyPitDuration(), maximumTime);
   }

   @Override
   public HardSoftScore getScore() {
      return score;
   }

   @Override
   public void setScore(HardSoftScore score) {
      this.score = score;
   }

   @Override
   public Collection<?> getProblemFacts() {
      List<Object> facts = new ArrayList<Object>();
      facts.add(torpedoRotationParametrization);
      facts.addAll(blastFurnaceDeliveryList);
      facts.addAll(converterDeliveryList);
      return facts;
   }
}
