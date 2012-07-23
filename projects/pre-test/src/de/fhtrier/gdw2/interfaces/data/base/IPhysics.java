package de.fhtrier.gdw2.interfaces.data.base;

import org.newdawn.slick.geom.Vector2f;

import de.fhtrier.gdw2.interfaces.base.IUpdateable;

public interface IPhysics extends IUpdateable{
	Vector2f getPosition();
	Vector2f getSpeed();
}
