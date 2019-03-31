/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

import java.util.ArrayList;
import java.util.Collection;
import processing.core.*;
/**
 *
 * @author Wind
 */
public class Asteroid extends GameObject
{
    
    private int total;
    private float[] offset;
    
    public Asteroid(PApplet p, PVector pos)
    {
	super(p, pos, PVector.random2D(), new PVector(0,0), 0, 0, p.random(15,50));
	total = PApplet.floor(p.random(7,15));
	offset = new float[total];
	for (int i = 0; i < total; i++)
	    offset[i] = p.random(-10,20);
    }
    
    public Asteroid(PApplet p, PVector pos, float r)
    {
	super(p, pos, PVector.random2D(), new PVector(0,0), 0, 0, r);
	total = PApplet.floor(p.random(7,15));
	offset = new float[total];
	for (int i = 0; i < total; i++)
	    offset[i] = p.random(-r*0.8f,r*0.8f);
    }
    
    public void Render()
    {
	p.pushMatrix();
	p.translate(pos.x, pos.y);
	p.noFill();
	p.stroke(255);
	p.strokeWeight(0.5f);
	
	p.beginShape();
	for (int i = 0; i < total; i++)
	{
	    float angle = PApplet.map(i,0,total,0,PConstants.TWO_PI);
	    float r = this.r + offset[i];
	    float x = r * PApplet.cos(angle);
	    float y = r * PApplet.sin(angle);
	    p.vertex(x,y);
	}
	p.endShape(PConstants.CLOSE);
	
	p.popMatrix();
    }
    
    public ArrayList<Asteroid> Breakup()
    {
	ArrayList<Asteroid> newast = new ArrayList<>();
	newast.add(new Asteroid(p, pos.copy(), r/2));
	newast.add(new Asteroid(p, pos.copy(), r/2));
	return newast;
    }
}
