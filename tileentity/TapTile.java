
package com.mapleman.maplemod.tileentity;

import com.mapleman.maplemod.MapleMod;
import com.mapleman.maplemod.block.ModBlocks;
import com.mapleman.maplemod.block.SapSpout;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TapTile extends TileEntity implements ITickableTileEntity
{
    public static final int MB_PER_TICK = 12;
    public static final int PACK_SIZE = 144;

    public static String TAG_STOP = "stop";
    public static String TAG_STATE = "state";

    private TapState tapState = TapState.OFF;

    private boolean stopPouring = false;

    private enum TapState
    {
        OFF,
        POURING;
    }

    public boolean isPouring()
    {
        return tapState != TapState.OFF;
    }

    public void reset()
    {
        stopPouring = false;
        if (tapState != TapState.OFF)
        {
            tapState = TapState.OFF;
        }
    }



    public static TapState fromIndex(int index)
    {
        switch(index)
        {
            case 1: return TapState.POURING;
        }
        return TapState.OFF;
    }

    public TapTile(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public TapTile()
    {
        this(ModTileEntities.TAP_TILE_ENTITY.get());
    }

    public void activate()
    {
        if (world == null || world.isRemote)
        {
            return;
        }

        switch (tapState)
        {
            case OFF:
                stopPouring = false;
                break;
            case POURING:
                stopPouring = true;
                break;
        }
    }

    @Override
    public void tick()
    {
        if (world == null || world.isRemote)
        {
            return;
        }

        if (tapState == TapState.OFF)
        {
            return;
        } else if (stopPouring) {
            reset();
        } else {
            tapState = TapState.POURING;
            return;
        }
    }

    public void onActivationPacket(boolean isPouring)
    {
        this.tapState = isPouring ? TapState.POURING : TapState.OFF;
    }

}
