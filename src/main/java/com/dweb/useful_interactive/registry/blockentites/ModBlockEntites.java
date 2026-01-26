package com.dweb.useful_interactive.registry.blockentites;

import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;
import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.chest.BoxBlockEntity;
import com.dweb.useful_interactive.block.door.DoorDecorEntity;
import com.dweb.useful_interactive.block.keybox.KeyBoxBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntites {
    public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "box_be"),
    FabricBlockEntityTypeBuilder.create(
        BoxBlockEntity::new, 
        ModStorageBlocks.BOX_BLOCK, 
        ModStorageBlocks.OAK_BOX_BLOCK, 
        ModStorageBlocks.BIRCH_BOX_BLOCK,
        ModStorageBlocks.BIRCH_BOX_CABINET_BLOCK/*, 
    ModUtilityBlocks.BATANIC_TABLE_BLOCK*/).build()
);

public static final BlockEntityType<DoorDecorEntity> DOOR_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "door_be"),
    FabricBlockEntityTypeBuilder.create(
        DoorDecorEntity::new, 
        ModArchitecturalBlocks.OAK_DECOR_DOOR, 
        ModArchitecturalBlocks.METAL_DECOR_DOOR, 
        ModArchitecturalBlocks.BIRCH_DECOR_DOOR).build()
);

public static final  BlockEntityType<KeyBoxBlockEntity> KEY_BOX_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "key_box_be"),
    FabricBlockEntityTypeBuilder.create(
        KeyBoxBlockEntity::new,
        ModUtilityBlocks.KEYBOX_BLOCK  
    ).build()
);

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Registering block entities for " + UsefulDecorMod.MOD_ID);
    }
}
