package nozero.apps1;

/**
 * Created by Ramadhanario on 26/04/2015.
 */
public class RelieverProvider {
    private String nama;
    private String isi;

    public RelieverProvider(String nama, String isi){
        this.setNama(nama);
        this.setIsi(isi);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
