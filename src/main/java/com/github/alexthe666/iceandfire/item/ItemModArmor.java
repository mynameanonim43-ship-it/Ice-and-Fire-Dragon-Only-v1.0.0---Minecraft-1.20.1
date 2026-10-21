package com.github.alexthe666.iceandfire.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public class ItemModArmor extends ArmorItem {

    public ItemModArmor(ArmorMaterial material, ArmorItem.Type slot) {
        super(material, slot, new Item.Properties());
    }
}
