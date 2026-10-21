package com.github.alexthe666.iceandfire.entity.tile;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class IafTileEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IceAndFire.MODID);

    //@formatter:off
    public static final RegistryObject<BlockEntityType<TileEntityLectern>> IAF_LECTERN = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityLectern::new, IafBlockRegistry.LECTERN.get()), "lectern");
    public static final RegistryObject<BlockEntityType<TileEntityPodium>> PODIUM = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityPodium::new, IafBlockRegistry.PODIUM_OAK.get(), IafBlockRegistry.PODIUM_BIRCH.get(), IafBlockRegistry.PODIUM_SPRUCE.get(), IafBlockRegistry.PODIUM_JUNGLE.get(), IafBlockRegistry.PODIUM_DARK_OAK.get(), IafBlockRegistry.PODIUM_ACACIA.get(), IafBlockRegistry.PODIUM_CRIMSON.get(), IafBlockRegistry.PODIUM_WARPED.get(), IafBlockRegistry.PODIUM_MANGROVE.get(), IafBlockRegistry.PODIUM_CHERRY.get()), "podium");
    public static final RegistryObject<BlockEntityType<TileEntityEggInIce>> EGG_IN_ICE = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityEggInIce::new, IafBlockRegistry.EGG_IN_ICE.get()), "egginice");
    public static final RegistryObject<BlockEntityType<TileEntityDragonforge>> DRAGONFORGE_CORE = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityDragonforge::new, IafBlockRegistry.DRAGONFORGE_FIRE_CORE.get(), IafBlockRegistry.DRAGONFORGE_ICE_CORE.get(), IafBlockRegistry.DRAGONFORGE_FIRE_CORE_DISABLED.get(), IafBlockRegistry.DRAGONFORGE_ICE_CORE_DISABLED.get(), IafBlockRegistry.DRAGONFORGE_LIGHTNING_CORE.get(), IafBlockRegistry.DRAGONFORGE_LIGHTNING_CORE_DISABLED.get()), "dragonforge_core");
    public static final RegistryObject<BlockEntityType<TileEntityDragonforgeBrick>> DRAGONFORGE_BRICK = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityDragonforgeBrick::new, IafBlockRegistry.DRAGONFORGE_FIRE_BRICK.get(), IafBlockRegistry.DRAGONFORGE_ICE_BRICK.get(), IafBlockRegistry.DRAGONFORGE_LIGHTNING_BRICK.get()), "dragonforge_brick");
    public static final RegistryObject<BlockEntityType<TileEntityDragonforgeInput>> DRAGONFORGE_INPUT = registerTileEntity(() -> BlockEntityType.Builder.of(TileEntityDragonforgeInput::new, IafBlockRegistry.DRAGONFORGE_FIRE_INPUT.get(), IafBlockRegistry.DRAGONFORGE_ICE_INPUT.get(), IafBlockRegistry.DRAGONFORGE_LIGHTNING_INPUT.get()), "dragonforge_input");


    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerTileEntity(
            Supplier<BlockEntityType.Builder<T>> supplier, String entityName) {
        return TYPES.register(entityName, () -> supplier.get().build(null));
    }
}
