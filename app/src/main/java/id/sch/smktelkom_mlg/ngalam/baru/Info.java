package id.sch.smktelkom_mlg.ngalam.baru;

import android.graphics.drawable.Drawable;

/**
 * Created by Mokleters on 04/30/2017.
 */

public class Info {
    public String judul;
    public String deskripsi;
    public String detail;
    public Drawable foto;
    // public Drawable panggil;


    public Info(String judul, String deskripsi, String detail, Drawable foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.detail = detail;
        this.foto = foto;
        // this.panggil = panggil;
    }
}
