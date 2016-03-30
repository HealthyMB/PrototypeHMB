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
 * Created by PAUL on 29/03/2016.
 */
public class ExerciceAdapter extends BaseAdapter {

    private List<Exercice> exos = new ArrayList<Exercice>();
    private LayoutInflater mInflater;

    public ExerciceAdapter(List<Exercice> exos, Context context) {
        this.exos = exos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return exos.size();
    }


    @Override
    public Object getItem(int position) {
        return exos.get(position);
    }


    @Override
    public long getItemId(int position) {
        return exos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView  = mInflater.inflate(R.layout.exo, null);
            holder = new ViewHolder();


            holder.nom = (TextView) convertView.findViewById(R.id.nomExo);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.nbRepetition = (TextView) convertView.findViewById(R.id.nbRepetitionValue);
            holder.nbRepetitionT = (TextView) convertView.findViewById(R.id.nbRepetition);
            holder.pause = (TextView) convertView.findViewById(R.id.pause);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Exercice exo = (Exercice) getItem(position);

        if (exo != null) {
            holder.nom.setText(exo.getNom());
            holder.description.setText(exo.getDescription());
            if(exo.isRepetitionADuration()){
                holder.nbRepetitionT.setText("Durée : ");
                holder.nbRepetition.setText(String.valueOf(exo.getRepetition()) + " s");
            } else {
                holder.nbRepetitionT.setText("Nombre de répétitions : ");
                holder.nbRepetition.setText(String.valueOf(exo.getRepetition()));
            }
            holder.pause.setText(String.valueOf(exo.getPause()) + " s");
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView nom;
        public TextView description;
        public TextView nbRepetition;
        public TextView nbRepetitionT;
        public TextView pause;
    }


    public List<Exercice> getExos() {
        return exos;
    }

    public void setExos(List<Exercice> exos) {
        this.exos = exos;
    }
}
