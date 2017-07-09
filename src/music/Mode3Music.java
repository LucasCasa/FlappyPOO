package music;

import component.worldComponent.Types;

/**
 * Created by Lucas on 09/07/2017.
 */
public class Mode3Music extends Music{
    private static Mode3Music ourInstance = new Mode3Music();

    public static Mode3Music getInstance() {
        return ourInstance;
    }

    private Mode3Music() {
        s = Types.MODE_3_THEME;
    }
}
