package com.bewitchment.common.core.capability.resize;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitySize {
	
	@CapabilityInject(CapabilitySize.class)
	public static final Capability<CapabilitySize> CAPABILITY = null;
	
	public static void init() {
		CapabilityManager.INSTANCE.register(CapabilitySize.class, new CapabilitySizeStorage(), CapabilitySize::new);
	}
	
	private float scale = 1f;
	private boolean dirty = false;

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		if (this.scale != scale) {
			dirty = true;
			this.scale = scale;
		}
	}
	
	public void setClean() {
		dirty = false;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	

}
