package co.edu.udec.command.commands;

import co.edu.udec.Main;
import co.edu.udec.Service;
import co.edu.udec.Utils;
import co.edu.udec.command.Arguments;
import co.edu.udec.command.Command;
import co.edu.udec.model.*;
import co.edu.udec.storage.ExecuteSQL;

import java.util.List;
import java.util.UUID;

public class AddCommand extends Command {
    public AddCommand(){
        super("add", "Añades un registro de algo");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "equipment" -> {
                if (arg.length() >= 4){
                    ExecuteSQL.addRow(new Equipment(UUID.fromString(arg.get(1)), arg.get(2), arg.get(3), Long.parseLong(arg.get(4))));
                }else {
                    Service.sendMessage("Tienes que añadir [ID] [Modelo] [Nombre] [Precio]");
                }
            }
            case "finishedproducts" -> {
                if (arg.length() >= 1){
                    ExecuteSQL.addRow(new FinishedProducts(UUID.fromString(arg.get(1)), arg.get(2), arg.get(3), Long.parseLong(arg.get(4)), Long.parseLong(arg.get(5)), System.currentTimeMillis()));
                }else {
                    Service.sendMessage("Tienes que añadir [ID] [serial]");
                }
            }
            case "operator" -> {
                if (arg.length() >= 4){
                    ExecuteSQL.addRow(new Operator(UUID.fromString(arg.get(1)), arg.get(2), arg.get(3), Integer.parseInt(arg.get(4))));
                }else {
                    Service.sendMessage("Tienes que añadir [ID] [nombre] [dirección] [teléfono]");
                }
            }
            case "productionprocesses" -> {
                if (arg.length() >= 4){
                    ExecuteSQL.addRow(new ProductionProcesses(UUID.fromString(arg.get(1)), arg.get(2), arg.get(3), System.currentTimeMillis(), List.of()));
                }else {
                    Service.sendMessage("Tienes que añadir [ID] [nombre] [descripción] [teléfono]");
                }
            }
            case "rawmaterial" -> {
                if (arg.length() >= 4){
                    ExecuteSQL.addRow(new RawMaterial(UUID.fromString(arg.get(1)), arg.get(2), Integer.parseInt(arg.get(3)), Utils.Unit.valueOf(arg.get(4)), arg.get(4), Long.parseLong(arg.get(6))));
                }else {
                    Service.sendMessage("Tienes que añadir [ID] [Nombre] [Cantidad] [Unidades] [Proveedor] [precio]");
                }
            }
        }
    }
}
