package com.github.alexthe666.iceandfire.loot;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

public class IafLootRegistry {

    public static LootItemFunctionType CUSTOMIZE_TO_DRAGON;

    private static LootItemFunctionType register(String name, Serializer<? extends LootItemFunction> serializer) {
        return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, new ResourceLocation(name), new LootItemFunctionType(serializer));
    }

    public static void init() {
        CUSTOMIZE_TO_DRAGON = register("iceandfire:customize_to_dragon", new CustomizeToDragon.Serializer());
    }

}
