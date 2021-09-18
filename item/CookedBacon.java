package com.mapleman.maplemod.item;


import com.mapleman.maplemod.MapleMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CookedBacon extends Item
{
    public CookedBacon()
    {
        super(new Properties()
                .group(MapleMod.MAPLEMOD_TAB)
                .food(new Food.Builder()
                        .hunger(5)
                        .saturation(4.5f)
                       /* .effect( () -> new EffectInstance(Effects.HASTE, 500, 3), 0.5f) */
                        .build()));
    }
}
