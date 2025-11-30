package co.edu.udec.model;

import co.edu.udec.Utils;

import java.util.UUID;

public record RawMaterial(UUID uuid, String name, int amount, Utils.Unit unit, String supplier, long price) {
}
