package music;

import component.worldComponent.Types;

/**
 * Created by Lucas on 09/07/2017.
 */
public class Mode2Music extends Music {
    private static Mode2Music instance = new Mode2Music();

    private Mode2Music(){
        s = Types.MODE_2_THEME;
    }
    public static Mode2Music getInstance(){
        return instance;
    }
}
