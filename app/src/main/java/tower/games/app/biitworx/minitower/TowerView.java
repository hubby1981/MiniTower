package tower.games.app.biitworx.minitower;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tower.games.app.biitworx.minitower.Backgrounds.Bracket;
import tower.games.app.biitworx.minitower.Backgrounds.PainterConst;

/**
 * Created by mweissgerber on 26.07.2017.
 */

public class TowerView extends View {

    RectF towerBounds;
    RectF lowerTomer;
    RectF upperTower;
    RectF ground;
    RectF lobby;
RectF flat;

    int bracketW =0;
    int bracketH =0;

    float scale=1;
    float tx=-0;
    float ty=0;

    List<Bracket> brackets=new ArrayList<>();

    public TowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void onDraw(Canvas canvas) {
        Rect clip=new Rect(0,0,0,0);
        canvas.getClipBounds(clip);
        canvas.scale(scale,scale);
canvas.translate(tx,ty);
        Paint sceneBack = new Paint();
        LinearGradient gradient = new LinearGradient(clip.left,clip.top,clip.left,clip.bottom,
                Color.argb(255,70,230,255),Color.argb(255,20,200,140), Shader.TileMode.CLAMP);
        sceneBack.setStyle(Paint.Style.FILL);
        sceneBack.setShader(gradient);



        canvas.drawRect(clip,sceneBack);

        float margin = clip.width() / 10;
        towerBounds = new RectF(clip.left + margin, clip.top + margin/2, clip.right - margin, clip.bottom );

        lowerTomer = new RectF(towerBounds.left,towerBounds.bottom-towerBounds.height()/8,towerBounds.right,towerBounds.bottom);
        upperTower = new RectF(towerBounds.left,towerBounds.top,towerBounds.right,lowerTomer.top);
        
        ground = new RectF(clip.left,lowerTomer.top,clip.right,clip.bottom);

        canvas.drawRect(ground, PainterConst.BACK_GROUND);
        canvas.drawRect(upperTower, PainterConst.BACK_UPPER_TOWER);
        canvas.drawRect(lowerTomer, PainterConst.BACK_LOWER_TOWER);
        bracketH = (int)towerBounds.height()/80;
        bracketW = (int)towerBounds.width()/30;

        float bracker = bracketW+(bracketW/1.5f);

        makeBracketsLine(new RectF(towerBounds.left+bracker,towerBounds.bottom,towerBounds.right-bracker,towerBounds.bottom),0);
        makeBracketsLine(new RectF(towerBounds.left+bracker,upperTower.bottom,towerBounds.right-bracker,upperTower.bottom),0);

        lobby = new RectF(upperTower.left,upperTower.bottom-(upperTower.height()/10)*1.5f,upperTower.right,upperTower.bottom);
        makeBracketsLine(new RectF(towerBounds.left+bracker,lobby.top,towerBounds.right-bracker,lobby.top),0);

        flat = new RectF(upperTower.left,upperTower.top,upperTower.right,lobby.top);
        int flats = (int)flat.height()/8;
        int hh = (int)flat.bottom;
        for(int index=0;index<8;index++){
            hh-=flats;
            makeBracketsToTop(new RectF(towerBounds.left+bracker*3,hh,towerBounds.left+bracker*3,hh+flats),-1);
            makeBracketsToTop(new RectF(towerBounds.left+bracker*6,hh,towerBounds.left+bracker*6,hh+flats),-1);
            makeBracketsToTop(new RectF(towerBounds.left+bracker*10,hh,towerBounds.left+bracker*10,hh+flats),-1);
            makeBracketsToTop(new RectF(towerBounds.left+bracker*13,hh,towerBounds.left+bracker*13,hh+flats),-1);
            makeBracketsToTop(new RectF(towerBounds.left+bracker*15,hh,towerBounds.left+bracker*15,hh+flats),-1);


            RectF elev = new RectF(towerBounds.left+bracker*15.75f,hh+flats*0.25f,towerBounds.left+bracker*17f,hh+flats);
            Paint elevBack = new Paint();

            RadialGradient elevGradient = new RadialGradient(elev.centerX(),elev.top,elev.width()/4,
                    Color.argb(255,150,150,150),Color.argb(255,100,100,100), Shader.TileMode.CLAMP);
            elevBack.setStyle(Paint.Style.FILL);
            elevBack.setShader(elevGradient);
            canvas.drawRect(elev,elevBack);

        }

     flats = (int)flat.height()/8;
     hh = (int)flat.bottom;
        for(int index=0;index<7;index++){
            hh-=flats;


            makeBracketsLine(new RectF(towerBounds.left+bracker,hh,towerBounds.right-bracker,hh),0);
            makeBracketsLine(new RectF(towerBounds.left+bracker,hh+bracketH,towerBounds.right-bracker,hh+bracketH),1);

        }

        makeBracketsToTop(towerBounds,0);
        makeBracketsToTop(new RectF(towerBounds.right-(bracker),towerBounds.top,towerBounds.right,towerBounds.bottom),0);


        makeBracketsLine(new RectF(towerBounds.left+bracker,brackets.get(brackets.size()-1).bottom(),towerBounds.right-bracker,brackets.get(brackets.size()-1).bottom()),0);


        for(Bracket b :brackets){
            b.draw(canvas);
        }



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN &&scale>1)
        {
            tx=0-event.getX();
            ty=0-event.getY();
            invalidate();
            return true;
        }else if(event.getAction()==MotionEvent.ACTION_DOWN &&scale==1){
            scale=5;
            invalidate();
            return true;
        }

        return false;
    }


        private void makeBracketsToTop(RectF rc,int m){



        int y = (int)rc.bottom;
        int x = (int)rc.left;

        while(y>(int)rc.top){
            if(m==0){
                brackets.add(new Bracket(new RectF(x,y-bracketH,x+bracketW,y)));
                x+=bracketW;
                brackets.add(new Bracket(new RectF(x,y-bracketH,x+bracketW/1.5f,y)));
                x=(int)rc.left;
                m=1;

            }else if(m==-1){
                brackets.add(new Bracket(new RectF(x,y-bracketH,x+bracketW/2f,y)));
            }else
            {
                brackets.add(new Bracket(new RectF(x,y-bracketH,x+bracketW/1.5f,y)));
                x+=bracketW/1.5f;
                x+=1;
                brackets.add(new Bracket(new RectF(x,y-bracketH,x+bracketW,y)));
                x=(int)rc.left;
                m=0;
            }
            y-=bracketH;
        }
    }

    private void makeBracketsLine(RectF rc,int m){

        int y = (int)rc.bottom;
        int x = (int)rc.left;

        while(x<rc.right){
            float plus=0;
            if(x+bracketW*2>rc.right){
                plus=rc.right-x;
            }
            plus = plus==0?(m==0?bracketW:bracketW/1.5f):plus;
            brackets.add(new Bracket(new RectF(x,y-bracketH,x+plus,y)));
            x+=m==0?bracketW:bracketW/1.5f;

            m=m==0?1:0;
        }
    }
}
