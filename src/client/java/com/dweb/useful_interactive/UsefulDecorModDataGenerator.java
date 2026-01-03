package com.dweb.useful_interactive;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class UsefulDecorModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		//FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // ВОТ ЭТА СТРОКА ДОЛЖНА БЫТЬ:
        //pack.addProvider(MyRecipeProvider::new); 
	}
}
