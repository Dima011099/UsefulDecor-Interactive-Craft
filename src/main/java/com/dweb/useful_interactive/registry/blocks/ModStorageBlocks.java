package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.cabinet.BoxCabinetBlock;
import com.dweb.useful_interactive.block.chest.BoxBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class ModStorageBlocks {
    // Registry Keys for storage blocks
    public static final ResourceKey<Block> BOX_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "box")
    );

    public static final ResourceKey<Block> OAK_BOX_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "oak_box")
    );

    public static final ResourceKey<Block> BIRCH_BOX_KEY = ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_box")
    );

    public static final ResourceKey<Block> BIRCH_BOX_CABINET_KEY = ResourceKey.create(
        Registries.BLOCK,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "birch_box_cabinet")
    );

    //Регистрируем сам блок
@SuppressWarnings("null")
    public static final Block BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(BOX_KEY).strength(2.0f, 50.0f)), 
            BOX_KEY
    );
   
    @SuppressWarnings("null")
    public static final Block OAK_BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(BlockBehaviour.Properties.of().setId(OAK_BOX_KEY).strength(4.0f, 50.0f)), 
            OAK_BOX_KEY
    );

    @SuppressWarnings("null")
    public static final Block  BIRCH_BOX_BLOCK = ModBlocks.registerBlock(
        new BoxBlock(BlockBehaviour.Properties.of().setId(BIRCH_BOX_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_KEY
    );

    @SuppressWarnings("null")
     public static final Block BIRCH_BOX_CABINET_BLOCK = ModBlocks.registerBlock(
        new BoxCabinetBlock(BlockBehaviour.Properties.of().setId(BIRCH_BOX_CABINET_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_CABINET_KEY
    );


     public static void register() {}
}
