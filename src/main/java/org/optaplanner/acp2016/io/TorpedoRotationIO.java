package org.optaplanner.acp2016.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.optaplanner.acp2016.domain.BlastFurnaceDelivery;
import org.optaplanner.acp2016.domain.ConverterDelivery;
import org.optaplanner.acp2016.domain.TorpedoRotationParametrization;
import org.optaplanner.acp2016.domain.TorpedoRotationSolution;
import org.optaplanner.acp2016.domain.TorpedoTripAssignment;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;

public class TorpedoRotationIO implements SolutionFileIO {

   private static final String INPUT_FILE_EXTENSION = "ins";
   private static final String OUTPUT_FILE_EXTENSION = "sol";

   @Override
   public String getInputFileExtension() {
      return INPUT_FILE_EXTENSION;
   }

   @Override
   public String getOutputFileExtension() {
      return OUTPUT_FILE_EXTENSION;
   }

   @Override
   public Solution read(File file) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
         TorpedoRotationParametrization torpedoRotationParametrization = new TorpedoRotationParametrization();
         torpedoRotationParametrization.setBlastFurnaceStopDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setDesulfurizationDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setConverterStopDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setBufferZoneCapacity(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setDesulfurizationCapacity(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setConverterCapacity(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setBlastFurnaceToBufferZoneDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setBufferZoneToDesulfurizationDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setDesulfurizationToConverterDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setConverterToEmptyBufferDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setEmptyBufferToBlastFurnaceDuration(Integer.parseInt(reader.readLine().split("=")[1]));
         torpedoRotationParametrization.setBlastFurnaceToEmptyBufferViaEmergencyPitDuration(Integer.parseInt(reader.readLine().split("=")[1]));

         List<BlastFurnaceDelivery> blastFurnaceDeliveryList = new ArrayList<>();
         List<ConverterDelivery> converterDeliveryList = new ArrayList<>();

         for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] parsedLine = line.split(" ");
            if ("BF".equals(parsedLine[0])) {
               BlastFurnaceDelivery blastFurnaceDelivery = new BlastFurnaceDelivery();
               blastFurnaceDelivery.setId(Integer.parseInt(parsedLine[1]));
               blastFurnaceDelivery.setStartTime(Integer.parseInt(parsedLine[2]));
               blastFurnaceDelivery.setProducedSulfurizationLevel(Integer.parseInt(parsedLine[3]));
               blastFurnaceDeliveryList.add(blastFurnaceDelivery);
            } else if ("C".equals(parsedLine[0])) {
               ConverterDelivery converterDelivery = new ConverterDelivery();
               converterDelivery.setId(Integer.parseInt(parsedLine[1]));
               converterDelivery.setStartTime(Integer.parseInt(parsedLine[2]));
               converterDelivery.setRequiredSulfurizationLevel(Integer.parseInt(parsedLine[3]));
               converterDeliveryList.add(converterDelivery);
            } else {
               throw new IllegalArgumentException("Unexpected line " + line);
            }
         }

         ConverterDelivery emergencyPitConverterDelivery = new ConverterDelivery();
         emergencyPitConverterDelivery.setId(converterDeliveryList.get(converterDeliveryList.size() - 1).getId() + 1);
         emergencyPitConverterDelivery.setEmergencyPit(true);

         converterDeliveryList.add(emergencyPitConverterDelivery);

         TorpedoRotationSolution solution = new TorpedoRotationSolution();
         solution.setBlastFurnaceDeliveryList(blastFurnaceDeliveryList);
         solution.setConverterDeliveryList(converterDeliveryList);
         solution.setTorpedoRotationParametrization(torpedoRotationParametrization);
         solution.setMinimumTime(0);

         int converterDeliveryPathMaxPossibleTime = converterDeliveryList.get(converterDeliveryList.size() - 1).getStartTime() + torpedoRotationParametrization.getConverterStopDuration() + torpedoRotationParametrization.getConverterToEmptyBufferDuration() + 1;
         int emergencyPitConverterDeliveryMaxPossibleTime = blastFurnaceDeliveryList.get(blastFurnaceDeliveryList.size() - 1).getStartTime() + torpedoRotationParametrization.getBlastFurnaceStopDuration() + torpedoRotationParametrization.getBlastFurnaceToEmptyBufferViaEmergencyPitDuration() + 1;
         // FIXME
         solution.setMaximumTime(Math.max(converterDeliveryPathMaxPossibleTime, emergencyPitConverterDeliveryMaxPossibleTime));

         List<TorpedoTripAssignment> torpedoTripAssignmentList = new ArrayList<TorpedoTripAssignment>();
         for (BlastFurnaceDelivery blastFurnaceDelivery : blastFurnaceDeliveryList) {
            TorpedoTripAssignment torpedoTripAssignment = new TorpedoTripAssignment();
            torpedoTripAssignment.setId(blastFurnaceDelivery.getId() + 1);
            torpedoTripAssignment.setTorpedoRotationParametrization(torpedoRotationParametrization);
            torpedoTripAssignment.setBlastFurnaceDelivery(blastFurnaceDelivery);
            torpedoTripAssignmentList.add(torpedoTripAssignment);
         }
         solution.setTorpedoTripAssignmentList(torpedoTripAssignmentList);
         return solution;
      } catch (FileNotFoundException e) {
         throw new IllegalStateException(e);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }


   }

   @Override
   public void write(Solution solution, File file) {
      TorpedoRotationSolution torpedoRotationSolution = (TorpedoRotationSolution) solution;
      List<TorpedoTripAssignment> torpedoTripAssignmentList = torpedoRotationSolution.getTorpedoTripAssignmentList();
      Collections.sort(torpedoTripAssignmentList, new Comparator<TorpedoTripAssignment>() {
         @Override
         public int compare(TorpedoTripAssignment o1, TorpedoTripAssignment o2) {
            return o1.getBlastFurnaceArrival() - o2.getBlastFurnaceArrival();
         }
      });
      List<List<TorpedoTripAssignment>> torpedoTripAssignmentIdMappingList = new ArrayList<>();
      for (TorpedoTripAssignment torpedoTripAssignment : torpedoTripAssignmentList) {
         if (torpedoTripAssignmentIdMappingList.isEmpty()) {
            List<TorpedoTripAssignment> listPerTorpedo = new ArrayList<>();
            listPerTorpedo.add(torpedoTripAssignment);
            torpedoTripAssignmentIdMappingList.add(listPerTorpedo);
         } else {
            boolean added = false;
            for (List<TorpedoTripAssignment> torpedoTripAssignmentIdMapping : torpedoTripAssignmentIdMappingList) {
               if (torpedoTripAssignmentIdMapping.get(torpedoTripAssignmentIdMapping.size() - 1).getEmptyBufferArrival() <= torpedoTripAssignment.getEmptyBufferDeparture()) {
                  torpedoTripAssignmentIdMapping.add(torpedoTripAssignment);
                  added = true;
                  break;
               }
            }
            if (!added) {
               List<TorpedoTripAssignment> listPerTorpedo = new ArrayList<>();
               listPerTorpedo.add(torpedoTripAssignment);
               torpedoTripAssignmentIdMappingList.add(listPerTorpedo);
            }
         }
      }

      StringBuilder outputBuilder = new StringBuilder();
      // header
      outputBuilder
         .append(file.getName()).append(System.lineSeparator())
         .append("TeamsID=TODO_TEAM_NAME").append(System.lineSeparator())
         .append("nbTorpedoes=").append(torpedoTripAssignmentIdMappingList.size()).append(System.lineSeparator());

      // torpedoes
      for (int i = 0; i < torpedoTripAssignmentIdMappingList.size(); i++) {
         List<TorpedoTripAssignment> torpedoTripAssignmentIdMapping = torpedoTripAssignmentIdMappingList.get(i);
         for (int j = 0; j < torpedoTripAssignmentIdMapping.size(); j++) {
            TorpedoTripAssignment torpedoTripAssignment = torpedoTripAssignmentIdMapping.get(j);
            outputBuilder
               .append(System.lineSeparator())
               .append("idTorpedo=").append(i).append(System.lineSeparator())
               .append("idBF=").append(torpedoTripAssignment.getBlastFurnaceDelivery().getId()).append(System.lineSeparator())
               .append("idConverter=").append(torpedoTripAssignment.getConverterDelivery().isEmergencyPit() ? -1 : torpedoTripAssignment.getConverterDelivery().getId()).append(System.lineSeparator())
               .append("startBF=").append(torpedoTripAssignment.getBlastFurnaceArrival()).append(System.lineSeparator())
               .append("endBF=").append(torpedoTripAssignment.getBlastFurnaceDeparture()).append(System.lineSeparator());
            if (!torpedoTripAssignment.getConverterDelivery().isEmergencyPit()) {
               outputBuilder
                  .append("startFullBuffer=").append(torpedoTripAssignment.getBufferZoneArrival()).append(System.lineSeparator())
                  .append("endFullBuffer=").append(torpedoTripAssignment.getBufferZoneDeparture()).append(System.lineSeparator())
                  .append("startDesulf=").append(torpedoTripAssignment.getDesulfurizationArrival()).append(System.lineSeparator())
                  .append("endDesulf=").append(torpedoTripAssignment.getDesulfurizationDeparture()).append(System.lineSeparator())
                  .append("startConverter=").append(torpedoTripAssignment.getConverterArrival()).append(System.lineSeparator())
                  .append("endConverter=").append(torpedoTripAssignment.getConverterDeparture()).append(System.lineSeparator());
            }
            outputBuilder.append("startEmptyBuffer=").append(torpedoTripAssignment.getEmptyBufferArrival()).append(System.lineSeparator());
            long emptyBufferDeparture;
            if (j == torpedoTripAssignmentIdMapping.size() - 1) {
               emptyBufferDeparture = torpedoTripAssignment.getEmptyBufferArrival();
            } else {
               TorpedoTripAssignment nextTorpedoTripAssignment = torpedoTripAssignmentIdMapping.get(j + 1);
               emptyBufferDeparture = nextTorpedoTripAssignment.getEmptyBufferDeparture();
            }
            outputBuilder
               .append("endEmptyBuffer=").append(emptyBufferDeparture).append(System.lineSeparator());
         }
      }

      try (FileWriter writer = new FileWriter(file)) {
         writer.write(outputBuilder.toString());
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }
}
