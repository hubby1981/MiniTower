package tower.games.app.biitworx.minitower.Backgrounds;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by mweissgerber on 02.08.2017.
 */

public class Bracket {

    private RectF rect;

    public Bracket(RectF rect){
        this.rect = rect;
    }


    public void draw(Canvas canvas){
        canvas.drawRect(rect,PainterConst.BACK_PAINT_BRACKET);
        canvas.drawRect(rect,PainterConst.LINE_PAINT_BRACKET);

    }

    public float bottom(){
        return rect.bottom;
    }
}
