package com.bewitchment.common.core.capability.resize;

import static com.bewitchment.common.core.capability.resize.CapabilitySize.CAPABILITY;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilitySizeProvider implements ICapabilitySerializable<NBTBase> {

	private CapabilitySize personalInstance = new CapabilitySize();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilitySize.CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilitySize.CAPABILITY) {
			return CapabilitySize.CAPABILITY.cast(personalInstance);
		}
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CAPABILITY.getStorage().writeNBT(CAPABILITY, personalInstance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CAPABILITY.getStorage().readNBT(CAPABILITY, personalInstance, null, nbt);
	}

}
