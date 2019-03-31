/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Wind
 */
public class Projectile extends GameObject
{
    boolean die = false;
    public Projectile(PApplet p, PVector pos, float angle)
    {
	super(p, pos.copy(), new PVector(0, 0), new PVector(0, 0), 0, 0, 1);
	vel = PVector.fromAngle(angle);
	vel.mult(10);
    }
    
    public void Render()
    {
	p.pushMatrix();
	p.stroke(255);
	p.strokeWeight(2f);
	p.point(pos.x, pos.y);
	p.popMatrix();
    }
    
    public void Edges()
    {
	if (pos.x > p.width + r || pos.x < -r ||
		pos.y > p.height + r || pos.y < -r)
	    this.die = true;
    }
    
}
