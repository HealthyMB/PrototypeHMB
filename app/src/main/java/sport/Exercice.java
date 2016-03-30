package sport;

/**
 * Created by PAUL on 29/03/2016.
 */
public class Exercice {

    private int id;
    private String nom;
    private String description;
    private String urlVideo;
    private int repetition;
    private boolean isRepetitionADuration;
    private int pause;


    public Exercice(int id, String nom, String description, String urlVideo, int repetition, boolean isRepetitionADuration, int pause) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.urlVideo = urlVideo;
        this.repetition = repetition;
        this.isRepetitionADuration = isRepetitionADuration;
        this.pause = pause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public boolean isRepetitionADuration() {
        return isRepetitionADuration;
    }

    public void setIsRepetitionADuration(boolean isRepetitionADuration) {
        this.isRepetitionADuration = isRepetitionADuration;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
}
