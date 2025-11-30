package co.edu.udec.model;

import java.util.List;
import java.util.UUID;


public record ProductionProcesses(UUID id, String name, String Description, long date, List<Operator> operators) {
}
