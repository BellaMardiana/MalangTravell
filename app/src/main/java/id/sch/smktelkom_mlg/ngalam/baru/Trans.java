package id.sch.smktelkom_mlg.ngalam.baru;

import java.io.Serializable;

/**
 * Created by Mokleters on 5/7/2017.
 */

public class Trans implements Serializable{
    public String judul;
    public String deskripsi;
    public String pict;

    public Trans(String judul, String deskripsi, String pict) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.pict=pict;

    }


}
