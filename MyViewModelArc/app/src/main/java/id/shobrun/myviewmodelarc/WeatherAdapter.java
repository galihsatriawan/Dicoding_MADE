package id.shobrun.myviewmodelarc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private ArrayList<WeatherItems> mData = new ArrayList<>();
    public void setData(ArrayList<WeatherItems> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_items,viewGroup,false);
        return new WeatherViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.bind(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNamaKota;
        TextView textViewTemperature;
        TextView textViewDescription;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaKota = itemView.findViewById(R.id.textKota);
            textViewTemperature = itemView.findViewById(R.id.textTemp);
            textViewDescription = itemView.findViewById(R.id.textDesc);
        }
        void bind(WeatherItems weatherItems) {
            textViewNamaKota.setText(weatherItems.getName());
            textViewTemperature.setText(weatherItems.getTemperature());
            textViewDescription.setText(weatherItems.getDescription());
        }
    }
}
