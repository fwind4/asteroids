package asteroids;

import processing.core.*;

/**
 *
 * @author Wind
 */
public class Ship extends GameObject
{
    private boolean isMoving = false;
    private int life = 3;

    public Ship(PApplet p, PVector pos, PVector vel, PVector acc, int life)
    {
	super(p, pos, vel, acc, 0, 0, 20);
	this.life = life;
    }

    public void Render()
    {
	p.pushMatrix();
	p.translate(pos.x, pos.y);
	p.rotate(heading + PConstants.HALF_PI);
	p.fill(0);
	p.stroke(255);
	p.strokeWeight(0.5f);
	p.triangle(-r, r, r, r, 0, -r);
	p.popMatrix();
    }

    public void setRotation(float a)
    {
	rot = a;
    }
    
    public void setMoving(boolean b)
    {
	isMoving = b;
    }
    
    public void Move()
    {
	if(isMoving)
	{
	    acc = PVector.fromAngle(heading);
	    acc.mult(0.1f);
	}
    }

}
