package com.dweb.useful_interactive.registry.blockentites;

import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;


import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.chest.BoxBlockEntity;
import com.dweb.useful_interactive.block.clock.WallClockBlockEntity;
import com.dweb.useful_interactive.block.door.DoorDecorEntity;
import com.dweb.useful_interactive.block.keybox.KeyBoxBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("null")
public class ModBlockEntites {
    public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY_TYPE = Registry.register(
    BuiltInRegistries.BLOCK_ENTITY_TYPE,
    Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "box_be"),
    FabricBlockEntityTypeBuilder.create(
        BoxBlockEntity::new, 
        ModStorageBlocks.BOX_BLOCK, 
        ModStorageBlocks.OAK_BOX_BLOCK, 
        ModStorageBlocks.BIRCH_BOX_BLOCK,
        ModStorageBlocks.BIRCH_BOX_CABINET_BLOCK/*, 
    ModUtilityBlocks.BATANIC_TABLE_BLOCK*/).build()
);

public static final BlockEntityType<DoorDecorEntity> DOOR_BLOCK_ENTITY_TYPE = Registry.register(
    BuiltInRegistries.BLOCK_ENTITY_TYPE,
    Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "door_be"), //of
    FabricBlockEntityTypeBuilder.create(
        DoorDecorEntity::new, 
        ModArchitecturalBlocks.OAK_DECOR_DOOR,
        ModArchitecturalBlocks.OAK_GLASS_DECOR_DOOR, 
        ModArchitecturalBlocks.METAL_DECOR_DOOR, 
        ModArchitecturalBlocks.BIRCH_DECOR_DOOR,
        ModArchitecturalBlocks.GLASS_DECOR_DOOR).build()
);

public static final  BlockEntityType<KeyBoxBlockEntity> KEY_BOX_ENTITY_TYPE = Registry.register(
    BuiltInRegistries.BLOCK_ENTITY_TYPE,
    Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_box_be"),
    FabricBlockEntityTypeBuilder.create(
        KeyBoxBlockEntity::new,
        ModUtilityBlocks.KEYBOX_BLOCK,
        ModUtilityBlocks.BATANIC_TABLE_BLOCK
    ).build()
);

public static final  BlockEntityType<WallClockBlockEntity> WALL_CLOCK = Registry.register(
    BuiltInRegistries.BLOCK_ENTITY_TYPE,
    Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "wall_clock"),
    FabricBlockEntityTypeBuilder.create(
        WallClockBlockEntity::new,
        ModUtilityBlocks.CRIMSON_WALL_CLOCK_BLOCK,
        ModUtilityBlocks.OCEANIC_WALL_CLOCK_BLOCK,
        ModUtilityBlocks.WOODEN_WALL_CLOCK_BLOCK,
        ModUtilityBlocks.OBSIDIAN_WALL_CLOCK_BLOCK

    ).build()
);

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Registering block entities for " + UsefulDecorMod.MOD_ID);
    }
}
