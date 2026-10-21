package com.github.alexthe666.iceandfire.compat.jade;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityDragonPart;
import net.minecraft.world.entity.Entity;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(IceAndFire.MODID)
public class IceAndFireJadePlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(DragonJadeProvider.INSTANCE, EntityDragonBase.class);
        registration.registerEntityComponent(DragonJadeProvider.INSTANCE, EntityDragonPart.class);

        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof EntityAccessor entityAccessor) {
                Entity entity = entityAccessor.getEntity();
                if (entity instanceof EntityDragonPart part) {
                    EntityDragonBase dragon = DragonJadeProvider.getParentDragon(part);
                    if (dragon != null) {
                        return registration.entityAccessor()
                                .from(entityAccessor)
                                .entity(dragon)
                                .build();
                    }
                }
            }
            return accessor;
        });
    }
}
