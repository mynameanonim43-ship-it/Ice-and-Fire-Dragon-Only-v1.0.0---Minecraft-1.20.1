package com.github.alexthe666.iceandfire.compat.jade;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityDragonPart;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.List;
import java.util.UUID;

public class DragonJadeProvider implements IEntityComponentProvider {

    public static final DragonJadeProvider INSTANCE = new DragonJadeProvider();
    public static final ResourceLocation UID = new ResourceLocation(IceAndFire.MODID, "dragon_info");

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        Entity entity = accessor.getEntity();
        EntityDragonBase dragon = null;

        if (entity instanceof EntityDragonBase d) {
            dragon = d;
        } else if (entity instanceof EntityDragonPart part) {
            dragon = getParentDragon(part);
        }

        if (dragon == null) {
            return;
        }

        // 1. Gender
        boolean isMale = dragon.isMale();
        MutableComponent genderLabel = Component.translatable("dragon.gender");
        MutableComponent genderValue = Component.translatable(isMale ? "dragon.gender.male" : "dragon.gender.female")
                .withStyle(isMale ? ChatFormatting.AQUA : ChatFormatting.LIGHT_PURPLE);
        tooltip.add(Component.empty().append(genderLabel).append(" ").append(genderValue));

        // 2. Stage & Age in Days
        int stage = dragon.getDragonStage();
        int days = dragon.getAgeInDays();
        MutableComponent stageLabel = Component.translatable("dragon.stage");
        MutableComponent stageValue = Component.literal(" " + stage + " ")
                .append(Component.translatable("dragon.days.front"))
                .append(String.valueOf(days))
                .append(" ")
                .append(Component.translatable("dragon.days.back"));
        tooltip.add(Component.empty().append(stageLabel).append(stageValue).withStyle(ChatFormatting.GRAY));

        // 3. Owner / Tame Status
        if (dragon.isTame()) {
            MutableComponent ownerLabel = Component.translatable("dragon.owner");
            Component ownerName = dragon.getOwner() != null ? dragon.getOwner().getName() : Component.literal("Unknown");
            tooltip.add(Component.empty().append(ownerLabel).append(" ").append(ownerName).withStyle(ChatFormatting.GREEN));
        } else {
            tooltip.add(Component.translatable("dragon.untamed").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    public static EntityDragonBase getParentDragon(EntityDragonPart part) {
        UUID id = part.getParentId();
        if (id == null) {
            return null;
        }
        Level level = part.level();
        if (level instanceof ServerLevel serverLevel) {
            Entity parent = serverLevel.getEntity(id);
            if (parent instanceof EntityDragonBase dragon) {
                return dragon;
            }
        } else {
            List<EntityDragonBase> dragons = level.getEntitiesOfClass(
                    EntityDragonBase.class,
                    part.getBoundingBox().inflate(32.0D),
                    d -> id.equals(d.getUUID())
            );
            if (!dragons.isEmpty()) {
                return dragons.get(0);
            }
        }
        return null;
    }
}
