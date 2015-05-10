package nozero.apps1;

/**
 * Created by Ramadhanario on 06/05/2015.
 */
public class PostProvider {
    private String title;
    private String isi;

    public PostProvider(String title, String isi){
        this.setTitle(title);
        this.setIsi(isi);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
