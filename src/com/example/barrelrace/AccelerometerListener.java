package com.example.barrelrace;

public interface AccelerometerListener {
	 
	public void onAccelerationChanged(float x, float y, float z, long ts);
 
	public void onShake(float force);
 
}