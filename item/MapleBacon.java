package com.mapleman.maplemod.item;


import com.mapleman.maplemod.MapleMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MapleBacon extends Item
{
    public MapleBacon()
    {
        super(new Properties()
                .group(MapleMod.MAPLEMOD_TAB)
                .food(new Food.Builder()
                        .hunger(6)
                        .saturation(5.5f)
                        .effect( () -> new EffectInstance(Effects.HASTE, 5000, 3),1)
                        .build()));
    }
}