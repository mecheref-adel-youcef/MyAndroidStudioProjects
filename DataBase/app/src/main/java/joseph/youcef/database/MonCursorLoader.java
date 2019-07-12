package joseph.youcef.database;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;

public class MonCursorLoader extends CursorLoader {

    MaBaseManager maBaseManager;
    public MonCursorLoader(Context context) {
        super(context);
        maBaseManager = new MaBaseManager(context);
    }

    @Override
    protected Cursor onLoadInBackground() {
        return maBaseManager.getAllWords();
    }


}
