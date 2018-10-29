package com.versionpb.viewports;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Viewportstrial extends Game {
	Texture img;
	private SpriteBatch batch;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        setScreen(new VPBSplashScreen(this));
	}

	@Override
    public void render () {
        super.render();
    }

    public Batch getBatch(){
        return this.batch;
    }
}
