package co.edu.udec.domain.command.commands;

import co.edu.udec.domain.Service;
import co.edu.udec.domain.command.Arguments;
import co.edu.udec.domain.command.Command;
import co.edu.udec.domain.entity.*;
import co.edu.udec.domain.storage.ExecuteSQL;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "muestras todos los elementos de una categoría");
    }

    @Override
    public void execute(Arguments arg) {
        if (arg.length() == 0){
            Service.sendMessage("Falta Argumentos");
            return;
        }
        switch (arg.get(0).toLowerCase()){
            case "equipment" -> Service.sendMessage("Equipamiento: \n" + String.join("\n", ExecuteSQL.selectAllRow(Equipment.class)));
            case "finishedproducts" -> Service.sendMessage("Productos terminados: \n" + String.join("\n", ExecuteSQL.selectAllRow(FinishedProducts.class)));
            case "operator" -> Service.sendMessage("Operadores: \n" + String.join("\n", ExecuteSQL.selectAllRow(Operator.class)));
            case "productionprocesses" -> Service.sendMessage("Proceso de Producción: \n", String.join("\n" + ExecuteSQL.selectAllRow(ProductionProcesses.class).stream()));
            case "rawmaterial" -> Service.sendMessage("Materias primas: \n" + String.join("\n", ExecuteSQL.selectAllRow(RawMaterial.class)));
        }
    }
}
