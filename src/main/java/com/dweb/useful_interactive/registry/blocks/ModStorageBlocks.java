package com.dweb.useful_interactive.registry.blocks;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.dweb.useful_interactive.block.cabinet.BoxCabinetBlock;
import com.dweb.useful_interactive.block.chest.BoxBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModStorageBlocks {
    // Registry Keys for storage blocks
    public static final RegistryKey<Block> BOX_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "box")
    );

    public static final RegistryKey<Block> OAK_BOX_KEY = RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(UsefulDecorMod.MOD_ID, "oak_box")
    );

    public static final RegistryKey<Block> BIRCH_BOX_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_box")
    );

    public static final RegistryKey<Block> BIRCH_BOX_CABINET_KEY = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(UsefulDecorMod.MOD_ID, "birch_box_cabinet")
    );

    //Регистрируем сам блок

    public static final Block BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(BOX_KEY).strength(2.0f, 50.0f)), 
            BOX_KEY
    );
   
    public static final Block OAK_BOX_BLOCK = ModBlocks.registerBlock(
            new BoxBlock(AbstractBlock.Settings.create().registryKey(OAK_BOX_KEY).strength(4.0f, 50.0f)), 
            OAK_BOX_KEY
    );

    public static final Block  BIRCH_BOX_BLOCK = ModBlocks.registerBlock(
        new BoxBlock(AbstractBlock.Settings.create().registryKey(BIRCH_BOX_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_KEY
    );

     public static final Block BIRCH_BOX_CABINET_BLOCK = ModBlocks.registerBlock(
        new BoxCabinetBlock(AbstractBlock.Settings.create().registryKey(BIRCH_BOX_CABINET_KEY).strength(3.0f, 50.0f)), 
        BIRCH_BOX_CABINET_KEY
    );


     public static void register() {}
}
