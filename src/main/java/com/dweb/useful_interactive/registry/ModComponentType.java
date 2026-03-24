package com.dweb.useful_interactive.registry;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.mojang.serialization.Codec;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType; 



public class ModComponentType {
    @SuppressWarnings("null")
    public static final DataComponentType<String> KEY_F_CLOSE = Registry.register( // Registry.register
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        Identifier.fromNamespaceAndPath(UsefulDecorMod.MOD_ID, "key_f_close"), //of
        DataComponentType.<String>builder()
            .persistent(Codec.STRING)  //codec
            .networkSynchronized(ByteBufCodecs.STRING_UTF8) //packetCodec(PacketCodecs) networkCodec
            .build()
    );

    

    // Метод для инициализации (вызовите его в OnInitialize)
    public static void register() {}
}