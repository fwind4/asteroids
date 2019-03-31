package asteroids;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Wind
 */
public abstract class GameObject
{
    protected PApplet p;
    protected PVector pos;
    protected PVector vel;
    protected PVector acc;
    protected float heading;
    protected float rot;
    protected float r;

    public GameObject( PApplet p, PVector pos, PVector vel, PVector acc, 
	    float heading, float rot, float r)
    {
	this.pos = pos;
	this.vel = vel;
	this.acc = acc;
	this.p = p;
	this.heading = heading;
	this.rot = rot;
	this.r = r;
    }

    public void Update()
    {
	this.pos.add(this.vel);
	this.vel.add(this.acc);
	heading += rot;
	Edges();
    }
    
    public void Edges()
    {
	if (pos.x > p.width + r)
	    pos.x = -r;
	else if (pos.x < -r)
	    pos.x = p.width + r;
	else if (pos.y > p.height + r)
	    pos.y = -r;
	else if (pos.y < -r)
	    pos.y = p.height + r;
    }
    
    public void Render()
    {
	
    }
 
    public boolean Hits(Asteroid ast)
    {
	float d = PApplet.dist(this.pos.x,this.pos.y,ast.pos.x,ast.pos.y);
	return (d < ast.r && this != ast);
    }
}
