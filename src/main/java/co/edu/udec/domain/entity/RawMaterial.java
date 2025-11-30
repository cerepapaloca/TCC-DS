package co.edu.udec.domain.entity;

import co.edu.udec.domain.Utils;

import java.util.UUID;

public record RawMaterial(UUID uuid, String name, int amount, Utils.Unit unit, String supplier, long price) {
}
