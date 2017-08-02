package tower.games.app.biitworx.minitower.Backgrounds;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;

/**
 * Created by mweissgerber on 02.08.2017.
 */

public class PainterConst {

    public static Paint BACK_PAINT_BRACKET;
    public static Paint LINE_PAINT_BRACKET;


    public static Paint BACK_UPPER_TOWER;
    public static Paint BACK_LOWER_TOWER;


    public static Paint BACK_GROUND;




    static{

        BACK_PAINT_BRACKET = new Paint();
        BACK_PAINT_BRACKET.setStyle(Paint.Style.FILL);
        BACK_PAINT_BRACKET.setColor(Color.argb(255,200,0,0));

        BACK_GROUND = new Paint();
        BACK_GROUND.setStyle(Paint.Style.FILL);
        BACK_GROUND.setColor(Color.argb(255,50,50,50));

        LINE_PAINT_BRACKET = new Paint();
        LINE_PAINT_BRACKET.setStyle(Paint.Style.STROKE);
        LINE_PAINT_BRACKET.setStrokeWidth(3);
        LINE_PAINT_BRACKET.setColor(Color.argb(255,255,255,255));

        BACK_UPPER_TOWER = new Paint();
        BACK_UPPER_TOWER.setStyle(Paint.Style.FILL);
        BACK_UPPER_TOWER.setColor(Color.argb(255,190,190,190));

        BACK_LOWER_TOWER = new Paint();
        BACK_LOWER_TOWER.setStyle(Paint.Style.FILL);
        BACK_LOWER_TOWER.setColor(Color.argb(255,90,90,90));
    }
}
