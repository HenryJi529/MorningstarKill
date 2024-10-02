package com.morningstar.kill.domain.player;

import com.morningstar.kill.domain.card.Card;
import lombok.Data;

/**
 * NOTE: 要注意set时装备的类型
 */
@Data
public class EquipmentArea {
    private Card weaponCard;
    private Card armorCard;
    private Card defensiveHorseCard;
    private Card offensiveHorseCard;
    private Card treasureCard;
}