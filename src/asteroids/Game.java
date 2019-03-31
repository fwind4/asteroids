
package asteroids;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.Global;
import processing.core.*;
/**
 *
 * @author Wind
 */
public class Game extends PApplet
{
    
    Ship ship;
    ArrayList<Asteroid> asteroids = new ArrayList<>();
    ArrayList<Projectile> projectiles = new ArrayList<>();
    PFont f;
    int l = 3;
    
    public static void main(String[] args)
    {
	String className = Thread.currentThread().getStackTrace()[1].getClassName();
	PApplet.main(className, args);
    }
    public void settings()
    {
	size(1280, 720);
    }

    public void setup()
    {
	f = createFont("Arial",16,true);
	ship = new Ship(this, new PVector(width/2, height/2),
		new PVector(0,0),new PVector(0,0), l);
	for (int i = 0; i < 8; i++)
	{
	    asteroids.add(new Asteroid(this, new PVector(random(width), random(height))));
	}
    }

    public void draw()
    {
	background(0);
	pushMatrix();
	textFont(f,24);
	fill(255);
	text("Lives: " + l,10,20);
	popMatrix();
	
	Asteroid r_ast = null;
	Asteroid r_a = null;
	Projectile r_prj = null;
	
	for(Asteroid ast : asteroids)
	{
	    if (ast.r < 9f)
		r_a = ast;
	    ast.Render();
	    ast.Update();
	    
	    if (ship.Hits(ast))
		ship = new Ship(this, new PVector(width/2, height/2),
		new PVector(0,0),new PVector(0,0), --l);
//	    for(Asteroid a : asteroids)
//	    {
//		if(a.Hits(ast))
//		    r_ast = a;
//	    }
	}
	
	for(Projectile prj : projectiles)
	{
	    if (prj.die)
	    {
		r_prj = prj;
		break;
	    }
	    prj.Render();
	    prj.Update();
	    for(Asteroid ast : asteroids)
	    {
		if(prj.Hits(ast))
		{
		    r_ast = ast;
		    r_prj = prj;
		    break;
		}		    
	    }
	}
	if(r_a != null)
	    asteroids.remove(r_a);
	if(r_ast != null)
	{
	    asteroids.addAll(r_ast.Breakup());
	    asteroids.remove(r_ast);
	}
	if(r_prj != null)
	    projectiles.remove(r_prj);
	
	
	ship.Render();
	ship.Update();
	ship.Move();
	
	if (l == 0)
	{
	    background(0);
	    textFont(f,36);
	    fill(255);
	    text("Game Over",width/2,height/2);
	    noLoop();
	}
	
	if(asteroids.isEmpty())
	{
	    background(0);
	    textFont(f,36);
	    fill(255);
	    text("You Win!",width/2,height/2);
	    noLoop();
	}
    }
    
    public void keyPressed()
    {
	if (key == ' ')
	    projectiles.add(new Projectile(this, ship.pos, ship.heading));
	else if (keyCode == LEFT)
	    ship.setRotation(-0.1f);
	else if (keyCode == RIGHT)
	    ship.setRotation(0.1f);
	else if (keyCode == UP)
	    ship.setMoving(true);
	    
	
    }
    
    public void keyReleased()
    {
	ship.setRotation(0);
	ship.setMoving(false);
	ship.acc = new PVector(0,0);
    }
}