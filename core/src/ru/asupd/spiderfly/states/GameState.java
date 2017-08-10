package ru.asupd.spiderfly.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import ru.asupd.spiderfly.sprites.Bee;
import ru.asupd.spiderfly.sprites.Bomb;
import ru.asupd.spiderfly.sprites.Coin;
import ru.asupd.spiderfly.sprites.Fly;
import ru.asupd.spiderfly.sprites.Frosty;
import ru.asupd.spiderfly.sprites.Green_leaf;
import ru.asupd.spiderfly.sprites.Snow;
import ru.asupd.spiderfly.sprites.Spider;

/**
 * Created by Asup.D on 09.12.2016.
 */

public class GameState extends State implements InputProcessor {
    private Spider spider;
    private Array<Bomb> bombs;
    private Coin coin;
    private Bee bee;
    private Fly fly;
    private Frosty frosty;
    private Snow snow;
    private Green_leaf green_leaf;

  //   private Texture test ;

    private boolean available;
    private BitmapFont font,font2;
    public static int score,score2;
    public int respawn_score;
    final Random random;


    public int getScore() {
        return score;
    }

    boolean changemus;
    private Music music,music2;

    public GameState(GameStateManager gsm) {
        super(gsm);
        spider = new Spider(800/2,320+160/2);

        score =0;score2=0;
        respawn_score=0;

        changemus=true;
        random = new Random();
        //int i,j;
        //i=random.nextInt(800);
        //j=random.nextInt(480);
        coin = new Coin(random.nextInt(400),random.nextInt(320));
        bee = new Bee(-70,100,spider.getPosition().x,spider.getPosition().y);
        bee.stop();
       // test = new Texture("snow.png");
        fly = new Fly(-50,random.nextInt(310)+10,coin.getPosition().x,coin.getPosition().y);

        green_leaf=new Green_leaf(300,50);

        frosty = new Frosty(727,468);
        snow = new Snow(random.nextInt(400),random.nextInt(320));


        camera.setToOrtho(false,800,480);
        System.out.println("GameState Created");
        Gdx.input.setInputProcessor(this);
        bombs = new Array<Bomb>();

        available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

        font = new BitmapFont();
        font2 = new BitmapFont();

        music = Gdx.audio.newMusic(Gdx.files.internal("svin-spider.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);

        music2 = Gdx.audio.newMusic(Gdx.files.internal("homer-svin-spider.mp3"));
        music2.setLooping(true);
        music2.setVolume(0.3f);

        music.play();

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            //System.out.println("Just toched");
            // spider.trycatchfly();
            System.out.println("game justTouched");
        }


    }

    @Override
    public void update(float dt) {
      //  handleInput();
        if (available)
        {
            float accelY = Gdx.input.getAccelerometerY();
            spider.move_acc(accelY);
        }
        spider.update(dt);
        bee.update(dt);
        coin.update(dt);
        fly.update(dt);
        frosty.update(dt);
        green_leaf.update(dt);
        int index;
        index =0;
        for (Bomb bomb:bombs)
        {
            bomb.update(dt);
            if (bomb.isDetonation())
            {
                bombs.removeIndex(0);
                bomb.dispose();
            }
            if (green_leaf.collision(bomb.getBound_bomb()))
            {

                bomb.bounced();
                green_leaf.dispose();
                green_leaf = new Green_leaf(random.nextInt(400),random.nextInt(320));
            }
            if (snow.collision(bomb.getBound_bomb()))
            {
                spider.setSnow(true);
            }

            if (coin.collision(bomb.getBound_bomb()))
            {
                score+=1;
                coin.dispose();
                bombs.removeIndex(index);
                bomb.dispose();//Бомба в памяти? Какая ирония)
                coin = new Coin(random.nextInt(400),random.nextInt(320));

                respawn_score+=1;
                if (respawn_score>=(random.nextInt(3)+2)){
                    bee = new Bee(-70,100,spider.getPosition().x,spider.getPosition().y);
                    bee.move();
                    respawn_score=0;
                }
            }
            index+=1;
        }
        //оса за экраном
        if ((bee.getPosition().x>=800)||(bee.getPosition().y>480)||(bee.getPosition().y<-64))
        {

            bee.dispose();
            bee = new Bee(-70,100,spider.getPosition().x,spider.getPosition().y);
            bee.stop();

        }
        //
        if ((fly.getPosition().x>=800)||(fly.getPosition().y>480)||(fly.getPosition().y<-64))
        {

            fly.dispose();
            fly = new Fly(-50,random.nextInt(310)+10,coin.getPosition().x,coin.getPosition().y);

        }
        if (coin.collision(fly.getBound_fly()))
        {
            coin.setVelosity(fly.getVelosity());
        }
        if (snow.collision(fly.getBound_fly()))
        {
            fly.setSnow(true);
            snow.dispose();
            snow = new Snow(random.nextInt(736), random.nextInt(320));
        }
        /*
        if (snow.collision(spider.getBound_spider()))
        {
            spider.setSnow(true);
            snow.dispose();
            snow = new Snow(random.nextInt(736), random.nextInt(320));
        }*/

        /* Коллизия монеты и осы
        if (coin.collision(bee.getBound_bee()))
        {
            coin.setVelosity(bee.getVelosity());
        }*/

        //респаун монеты
        if (coin.getPosition().x>=800||coin.getPosition().x<=-64||coin.getPosition().y>=480||
                coin.getPosition().y<=-64)
        {
            coin.dispose();
            coin = new Coin(random.nextInt(736), random.nextInt(320));
            score2+=1;
        }



        if ((score>=25)&&(changemus))
        {
            music.stop();
            changemus=false;
            music2.play();
        }
        if (spider.collision(bee.getBound_bee()))
        {
            gsm.set(new GameoverState(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(spider.getSpider(),spider.getPosition().x,spider.getPosition().y);
        sb.draw(green_leaf.getTexture_green_leaf(),green_leaf.getPosition().x,green_leaf.getPosition().y);
        sb.draw(frosty.getFrosty(),frosty.getPosition().x,frosty.getPosition().y);
        sb.draw(snow.getTexture_snow(),snow.getPosition().x,snow.getPosition().y);
        sb.draw(coin.getCoin(),coin.getPosition().x,coin.getPosition().y);
        sb.draw(bee.getBee(),bee.getPosition().x,bee.getPosition().y);
        sb.draw(fly.getTexture_fly(),fly.getPosition().x,fly.getPosition().y);
        for (Bomb bomb:bombs)
        {
            sb.draw(bomb.getBomb(),bomb.getPosition().x,bomb.getPosition().y);

        }
        font.draw(sb,"SCORE: "+score,0,480);



        //font2.draw(sb,"BEE SCORE: "+score2,690,480);

      //  sb.draw(test,800/2,120);
        sb.end();
    }

    @Override
    public void dispose() {
        if (music.isPlaying()) {
            music.stop();
        }
        music.dispose();
        if ( music2.isPlaying()) {
            music2.stop();
        }
        music2.dispose();
        System.out.println("Game State Disposed");
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
        {
            spider.move_left();
            System.out.println("Input.Keys.LEFT");
        }
        if(keycode == Input.Keys.RIGHT)
        {
            spider.move_right();
            System.out.println("Input.Keys.RIGHT");
        }
        if (keycode == Input.Keys.SPACE)
        {
            spider.trycatchfly();
            bombs.add(new Bomb(spider.getPosition().x,spider.getPosition().y));
            System.out.println("Input.Keys.SPACE");
        }
        if (keycode == Input.Keys.Q)
        {
            spider.trycatchfly();
            //bombs.removeRange(0,1);
            bombs.removeIndex(0);
            System.out.println("Input.Keys.Q");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("left click");
        bombs.add(new Bomb(spider.getPosition().x,spider.getPosition().y));
        System.out.println("Alpha test");
        spider.trycatchfly();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
