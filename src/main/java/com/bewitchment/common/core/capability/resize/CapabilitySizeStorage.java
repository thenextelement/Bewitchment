package com.bewitchment.common.core.capability.resize;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilitySizeStorage implements IStorage<CapabilitySize> {

	@Override
	public NBTBase writeNBT(Capability<CapabilitySize> capability, CapabilitySize instance, EnumFacing side) {
		return new NBTTagFloat(instance.getScale());
	}

	@Override
	public void readNBT(Capability<CapabilitySize> capability, CapabilitySize instance, EnumFacing side, NBTBase nbt) {
		instance.setScale(((NBTTagFloat)nbt).getFloat());
	}

}
