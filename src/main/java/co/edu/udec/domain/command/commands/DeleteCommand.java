package co.edu.udec.domain.command.commands;

import co.edu.udec.domain.Service;
import co.edu.udec.domain.command.Arguments;
import co.edu.udec.domain.command.Command;
import co.edu.udec.domain.entity.*;
import co.edu.udec.domain.storage.ExecuteSQL;

import java.util.UUID;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super("delete", "borras una fila en la base de datos.");
    }

    @Override
    public void execute(Arguments arg) {
        if (arg.length() < 2) {
            Service.sendMessage("Tienes que añadir la id");
            return;
        }
        switch (arg.get(0).toLowerCase()){
            case "equipment" -> ExecuteSQL.removeRow(UUID.fromString(arg.get(1)), Equipment.class);
            case "finishedproducts" -> ExecuteSQL.removeRow(UUID.fromString(arg.get(1)), FinishedProducts.class);
            case "operator" -> ExecuteSQL.removeRow(UUID.fromString(arg.get(1)), Operator.class);
            case "productionprocesses" -> ExecuteSQL.removeRow(UUID.fromString(arg.get(1)), ProductionProcesses.class);
            case "rawmaterial" -> ExecuteSQL.removeRow(UUID.fromString(arg.get(1)), RawMaterial.class);
        }
    }
}
