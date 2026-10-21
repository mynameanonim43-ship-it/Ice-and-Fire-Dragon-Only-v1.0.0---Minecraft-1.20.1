package com.github.alexthe666.iceandfire.config;

import com.github.alexthe666.citadel.config.biome.SpawnBiomeConfig;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;
import com.github.alexthe666.iceandfire.IceAndFire;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {
    public static final Map.Entry<String, SpawnBiomeData> sapphireBiomes = Map.entry("iceandfire:sapphire_gen_biomes", DefaultBiomes.VERY_SNOWY);
    public static final Map.Entry<String, SpawnBiomeData> fireLilyBiomes = Map.entry("iceandfire:fire_lily_biomes", DefaultBiomes.VERY_HOT);
    public static final Map.Entry<String, SpawnBiomeData> frostLilyBiomes = Map.entry("iceandfire:frost_lily_biomes", DefaultBiomes.VERY_SNOWY);
    public static final Map.Entry<String, SpawnBiomeData> lightningLilyBiomes = Map.entry("iceandfire:lightning_lily_biomes", DefaultBiomes.SAVANNAS);
    public static final Map.Entry<String, SpawnBiomeData> fireDragonBiomes = Map.entry("iceandfire:fire_dragon_biomes", DefaultBiomes.FIREDRAGON_ROOST);
    public static final Map.Entry<String, SpawnBiomeData> fireDragonCaveBiomes = Map.entry("iceandfire:fire_dragon_cave_biomes", DefaultBiomes.FIREDRAGON_CAVE);
    public static final Map.Entry<String, SpawnBiomeData> iceDragonBiomes = Map.entry("iceandfire:ice_dragon_biomes", DefaultBiomes.ICEDRAGON_ROOST);
    public static final Map.Entry<String, SpawnBiomeData> iceDragonCaveBiomes = Map.entry("iceandfire:ice_dragon_cave_biomes", DefaultBiomes.ICEDRAGON_CAVE);
    public static final Map.Entry<String, SpawnBiomeData> lightningDragonBiomes = Map.entry("iceandfire:lightning_dragon_biomes", DefaultBiomes.LIGHTNING_ROOST);
    public static final Map.Entry<String, SpawnBiomeData> lightningDragonCaveBiomes = Map.entry("iceandfire:lightning_dragon_cave_biomes", DefaultBiomes.LIGHTNING_CAVE);

    public static final Map.Entry<String, SpawnBiomeData> lightningDragonSkeletonBiomes = Map.entry("iceandfire:lightning_dragon_skeleton_biomes", DefaultBiomes.SAVANNAS);
    public static final Map.Entry<String, SpawnBiomeData> fireDragonSkeletonBiomes = Map.entry("iceandfire:fire_dragon_skeleton_biomes", DefaultBiomes.DESERT);
    public static final Map.Entry<String, SpawnBiomeData> iceDragonSkeletonBiomes = Map.entry("iceandfire:ice_dragon_skeleton_biomes", DefaultBiomes.VERY_SNOWY);

    private static boolean init = false;
    private static final Map<String, SpawnBiomeData> biomeConfigValues = new HashMap<>();
    public static void init() {
        try {
            for (Field f : BiomeConfig.class.getFields()) {
                Object obj = f.get(null);
                if (obj instanceof Map.Entry<?,?> mapObj) {
                    String id = (String) mapObj.getKey();
                    SpawnBiomeData data = (SpawnBiomeData) mapObj.getValue();
                    biomeConfigValues.put(id, SpawnBiomeConfig.create(new ResourceLocation(id), data));
                }
            }
                }catch (Exception e){
                    IceAndFire.LOGGER.warn("Encountered error building iceandfire biome config .json files");
                    e.printStackTrace();
                }
        init = true;
    }

    private static Map<String, SpawnBiomeData> getBiomeConfigValues() {
        final class LazyInit {
            private LazyInit() {}
            private static final Map<String, SpawnBiomeData> BIOME_CONFIG_VALUES;
            static {
                var entries = new ArrayList<Map.Entry<String, SpawnBiomeData>>();
                try {
                    for (Field f : BiomeConfig.class.getFields()) {
                        Object obj = f.get(null);
                        if (obj instanceof Map.Entry entry) {
                            String id = (String) entry.getKey();
                            SpawnBiomeData data = (SpawnBiomeData) entry.getValue();
                            entries.add(Map.entry(id, SpawnBiomeConfig.create(new ResourceLocation(id), data)));
                        }
                    }
                }catch (Exception e){
                    IceAndFire.LOGGER.warn("Encountered error building iceandfire biome config .json files");
                    e.printStackTrace();
                }
                BIOME_CONFIG_VALUES = Map.ofEntries(entries.toArray(new Map.Entry[0]));
            }
        }

        return LazyInit.BIOME_CONFIG_VALUES;
    }

    private static ResourceLocation getBiomeName(Holder<Biome> biome) {
        return biome.unwrap().map(ResourceKey::location, (noKey) -> null);
    }

    public static boolean test(Map.Entry<String, SpawnBiomeData> entry, Holder<Biome> biome, ResourceLocation name) {
        if (!init) {
            init();
        }
        return biomeConfigValues.get(entry.getKey()).matches(biome, name);
    }

    public static boolean test(Map.Entry<String, SpawnBiomeData> entry, Holder<Biome> biome) {
        return BiomeConfig.test(entry, biome, getBiomeName(biome));
    }
    public static boolean test(Map.Entry<String, SpawnBiomeData> entry, Holder.Reference<Biome> biome) {
        return test(entry, biome, biome.key().location());
    }

}
