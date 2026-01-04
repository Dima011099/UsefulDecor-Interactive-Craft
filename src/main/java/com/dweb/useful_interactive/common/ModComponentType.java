package com.dweb.useful_interactive.common;

import com.dweb.useful_interactive.UsefulDecorMod;
import com.mojang.serialization.Codec;

import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponentType {

    public static final ComponentType<String> KEY_F_CLOSE = Registry.register(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(UsefulDecorMod.MOD_ID, "key_f_close"),
        ComponentType.<String>builder()
            .codec(Codec.STRING) 
            .packetCodec(PacketCodecs.STRING) 
            .build()
    );

    

    // Метод для инициализации (вызовите его в OnInitialize)
    public static void register() {}
}