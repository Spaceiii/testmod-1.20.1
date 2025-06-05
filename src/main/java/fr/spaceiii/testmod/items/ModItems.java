package fr.spaceiii.testmod.items;

import fr.spaceiii.testmod.TestMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item LEGENDARY_BOW = registerItem("legendary_bow", new LegendaryBow(new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item STELLAR_ESSENCE = registerItem("stellar_essence", new Item(new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item STAR_FRAGMENT = registerItem("star_fragment", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));

    private static void addItemsToCombatTab(FabricItemGroupEntries entries) {
        entries.add(LEGENDARY_BOW);
    }

    private static void addItemsToIngredientTab(FabricItemGroupEntries entries) {
        entries.add(STELLAR_ESSENCE);
        entries.add(STAR_FRAGMENT);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TestMod.MOD_ID, name), item);
    }

    public static void register() {
        TestMod.LOGGER.info("Registering mod items for " + TestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
    }
}
