package com.github.alexthe666.iceandfire.datagen;

import com.github.alexthe666.iceandfire.IceAndFire;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

import java.util.Optional;

import static com.github.alexthe666.iceandfire.client.IafClientSetup.*;

public class AtlasGenerator extends SpriteSourceProvider {

    public AtlasGenerator(PackOutput output, ExistingFileHelper helper) {
        super(output, helper, IceAndFire.MODID);
    }
    @Override
    protected void addSources() {
    }
}
