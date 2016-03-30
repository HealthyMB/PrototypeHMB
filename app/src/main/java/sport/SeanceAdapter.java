package sport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import efrei.healthymb.R;

/**
 * Created by PAUL on 28/03/2016.
 */
public class SeanceAdapter extends BaseAdapter{

    private List<Seance> seances = new ArrayList<Seance>();
    private LayoutInflater mInflater;

    public SeanceAdapter(List<Seance> seances, Context context) {
        this.seances = seances;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return seances.size();
    }


    @Override
    public Object getItem(int position) {
        return seances.get(position);
    }


    @Override
    public long getItemId(int position) {
        return seances.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView  = mInflater.inflate(R.layout.seance, null);
            holder = new ViewHolder();

            holder.nom = (TextView) convertView.findViewById(R.id.nomSeance);
            holder.duree = (TextView) convertView.findViewById(R.id.dureeSeance);
            holder.difficulte = (TextView) convertView.findViewById(R.id.difficulteSeance);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }


        Seance seance = (Seance) getItem(position);

        if(seance != null) {
            holder.nom.setText(seance.getNom());
            holder.duree.setText(seance.getDuree() + " min");
            holder.difficulte.setText(seance.getDifficulte() + "/5");
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView nom;
        public TextView duree;
        public TextView difficulte;
    }


    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
