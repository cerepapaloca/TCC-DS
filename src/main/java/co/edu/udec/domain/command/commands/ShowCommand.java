package co.edu.udec.domain.command.commands;

import co.edu.udec.domain.Service;
import co.edu.udec.domain.command.Arguments;
import co.edu.udec.domain.command.Command;
import co.edu.udec.domain.entity.*;
import co.edu.udec.model.*;
import co.edu.udec.domain.storage.ExecuteSQL;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "muestras todos los elementos de una categoría");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "equipment" -> Service.sendMessage("Equipamiento: \n" + String.join("\n" + ExecuteSQL.selectAllRow(Equipment.class).stream().map(Record::toString).toList()));
            case "finishedproducts" -> Service.sendMessage("Productos terminados: \n" + String.join("\n" + ExecuteSQL.selectAllRow(FinishedProducts.class).stream().map(Record::toString).toList()));
            case "operator" -> Service.sendMessage("Operadores: \n" + String.join("\n" + ExecuteSQL.selectAllRow(Operator.class).stream().map(Operator::toString).toList()));
            case "productionprocesses" -> Service.sendMessage("Proceso de Producción: \n" + String.join("\n" + ExecuteSQL.selectAllRow(ProductionProcesses.class).stream().map(Record::toString).toList()));
            case "rawmaterial" -> Service.sendMessage("Materias primas: \n" + String.join("\n" + ExecuteSQL.selectAllRow(RawMaterial.class).stream().map(Record::toString).toList()));
        }
    }
}
