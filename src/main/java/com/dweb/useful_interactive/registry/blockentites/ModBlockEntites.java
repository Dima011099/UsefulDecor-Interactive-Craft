package com.dweb.useful_interactive.registry.blockentites;

import com.dweb.useful_interactive.registry.blocks.ModArchitecturalBlocks;
import com.dweb.useful_interactive.registry.blocks.ModStorageBlocks;
import com.dweb.useful_interactive.registry.blocks.ModUtilityBlocks;
import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.common.BoxBlockEntity;
import com.dweb.useful_interactive.common.DoorDecorEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntites {
    public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "box_be"),//AOK_BOX_BLOCK
    // Это актуальный способ для последних версий Fabric
    FabricBlockEntityTypeBuilder.create(
        BoxBlockEntity::new, 
        ModStorageBlocks.BOX_BLOCK, 
        ModStorageBlocks.OAK_BOX_BLOCK, 
        ModStorageBlocks.BIRCH_BOX_BLOCK/*, 
        ModUtilityBlocks.HOUSEKEEPER_BLOCK, 
    ModUtilityBlocks.BATANIC_TABLE_BLOCK*/).build()
);

public static final BlockEntityType<DoorDecorEntity> DOOR_BLOCK_ENTITY_TYPE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE,
    Identifier.of(UsefulDecorMod.MOD_ID, "door_be"),//AOK_BOX_BLOCK
    // Это актуальный способ для последних версий Fabric
    FabricBlockEntityTypeBuilder.create(
        DoorDecorEntity::new, 
        ModArchitecturalBlocks.OAK_DECOR_DOOR, 
        ModArchitecturalBlocks.METAL_DECOR_DOOR, 
        ModArchitecturalBlocks.BIRCH_DECOR_DOOR).build()
);

    public static void register() {
        UsefulDecorMod.LOGGER.debug("Регистрация блочных сущностей для " + UsefulDecorMod.MOD_ID);
    }
}
