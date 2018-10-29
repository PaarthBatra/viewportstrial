package com.versionpb.viewports;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class VPBSplashScreen implements Screen {
    private Viewportstrial game;
    private Sprite splashBGImage;
    private OrthographicCamera cam;
    private Viewport viewport;

    private float Screen_Width,Screen_Height;

    private String str;
    private float w, h , pixelsToScreen_Width_Ratio,pixelsToScreen_Height_Ratio;
    private float widthofLine;
    private GlyphLayout glyphLayout;
    private ShapeRenderer srend;

    private static final float TIMEPERCHAR = 0.1f; // play with this for dif speeds
    private float ctimeperchar = 0; // time per character for draw
    private int numchars = 0; // for parsing all chars in string float delta; // get delta time

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public VPBSplashScreen(final Viewportstrial game){
        this.game = game;

        srend = new ShapeRenderer();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("AlexBrush-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.FIREBRICK;
        parameter.borderStraight= true;
        parameter.shadowOffsetX= 10;
        parameter.shadowOffsetY= 10;
        parameter.shadowColor= new Color(0, 0, 0.1f, 0.75f);
        parameter.flip = false;
        font = generator.generateFont(parameter);

        splashBGImage = new Sprite(new Texture(Gdx.files.internal("NiceGameBg.png")));


        Screen_Width = GameInfo.GAME_WIDTH;
        Screen_Height = GameInfo.GAME_HEIGHT;

        pixelsToScreen_Width_Ratio = (float) Gdx.graphics.getWidth() / Screen_Width ;
        pixelsToScreen_Height_Ratio = (float) Gdx.graphics.getHeight() / Screen_Height ;



        cam = new OrthographicCamera();

        cam.setToOrtho(false, Screen_Width/2 , Screen_Height/2 );
        cam.position.set(Screen_Width/2 , Screen_Height/2,0);

        float aspectRaio = (float)Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth() ;
        //viewport = new FillViewport(Screen_Width  , Screen_Height,cam);
        viewport = new FitViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new StretchViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new ExtendViewport(Screen_Width  , Screen_Height,cam);
        //viewport = new ScreenViewport(cam);
        viewport.apply();




        str = GameInfo.str;
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, str);
        w = glyphLayout.width;
        h = glyphLayout.height;

        //widthofLine = cam.viewportWidth / 2 - w / 2;
        widthofLine = cam.viewportWidth / 2 - w / 2;

        Gdx.app.log("LOG","Screen_Width " + Screen_Width);
        Gdx.app.log("LOG","Screen_Height: " + Screen_Height);
        Gdx.app.log("LOG","camera.viewportWidth : " + cam.viewportWidth);
        Gdx.app.log("LOG","camera.viewportHeight : " + cam.viewportHeight);
        Gdx.app.log("LOG","glyphLayout.width i.e. w " + w);
        Gdx.app.log("LOG","glyphLayout.height i.e. h : " + h);
        Gdx.app.log("LOG","GameInfo.WIDTH: " + GameInfo.GAME_WIDTH);
        Gdx.app.log("LOG","GameInfo.HEIGHT: " + GameInfo.GAME_HEIGHT);
        Gdx.app.log("LOG","Gdx.graphics.getWidth(): " + Gdx.graphics.getWidth());
        Gdx.app.log("LOG","Gdx.graphics.getHeight(): " + Gdx.graphics.getHeight());
        Gdx.app.log("LOG","viewport.getWorldWidth(): " + viewport.getWorldWidth());
        Gdx.app.log("LOG","viewport.getWorldHeight(): " + viewport.getWorldHeight());
        Gdx.app.log("LOG","viewport.getScreenWidth()): " + viewport.getScreenWidth());
        Gdx.app.log("LOG","viewport.getScreenHeight(): " + viewport.getScreenHeight());
        Gdx.app.log("LOG","cam.position.x " + cam.position.x);
        Gdx.app.log("LOG","cam.position.y : " + cam.position.y);
        Gdx.app.log("LOG","Aspect Ratio: " + aspectRaio);
        Gdx.app.log("LOG","pixelsToScreen_Width_Ratio: " + pixelsToScreen_Width_Ratio);
        Gdx.app.log("LOG","pixelsToScreen_Height_Ratio: " + pixelsToScreen_Height_Ratio);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        cam.update();
        game.getBatch().setProjectionMatrix(cam.combined);
        game.getBatch().begin();
        game.getBatch().draw(splashBGImage,0,0,cam.viewportWidth,cam.viewportHeight);
        //splashBGImage.draw(game.getBatch(),0,0,GameInfo.GAME_WIDTH,GameInfo.GAME_HEIGHT);

        str=GameInfo.str;
        if (numchars < str.length()) { // if num of chars are lesser than string length , if all chars are not parsed
            ctimeperchar += delta; // character time per char to be added with delta
            if (ctimeperchar >= TIMEPERCHAR) { // if c time ie greater than time // for 1 char
                ctimeperchar = 0; // make ctimeper char again 0
                numchars++; // go to next character , to be printed
            }
        }
        str = str.substring(0, numchars); // get string to be printed


        font.draw(game.getBatch(), str, cam.viewportWidth / 2 - w / 2, cam.viewportHeight / 2 + h / 2);
        game.getBatch().end();

        if(str.equalsIgnoreCase(GameInfo.str)) {
            srend.begin(ShapeRenderer.ShapeType.Filled);
            srend.setColor(GameInfo.SplashScreenUnderLineColor);
            srend.rectLine( (cam.viewportWidth / 2 - w / 2 + GameInfo.LineOffsetX) * pixelsToScreen_Width_Ratio,
                    (cam.viewportHeight/ 2 - h) * pixelsToScreen_Height_Ratio,
                    (widthofLine + GameInfo.LineOffsetX + GameInfo.LineAdjustmentOffsetX) * pixelsToScreen_Width_Ratio,
                    (cam.viewportHeight/ 2 - h) * pixelsToScreen_Height_Ratio,
                    GameInfo.Rectangle_Width);

            srend.end();
            update();
        }

    }

    private void update(){
        if(widthofLine <  w  + GameInfo.LineAdjustmentOffsetX)
            widthofLine += 10;

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        cam.position.set(Screen_Width/2 , Screen_Height/2,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        generator.dispose();
        srend.dispose();
        font.dispose();

    }
}
