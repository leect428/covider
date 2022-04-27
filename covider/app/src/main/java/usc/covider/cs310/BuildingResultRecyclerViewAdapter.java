package usc.covider.cs310;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cs310.R;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BuildingResultRecyclerViewAdapter extends RecyclerView.Adapter<BuildingResultRecyclerViewAdapter.ViewHolder> {

    private List<BuildingResult> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    // data is passed into the constructor
    BuildingResultRecyclerViewAdapter(Context context, List<BuildingResult> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.building_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BuildingResult result = mData.get(position);
        holder.covidCount.setText(result.hasCovidCount + " person(s) have tested positive for COVID");
        holder.symptomCount.setText(result.hasSymptomCount + " person(s) have symptoms for COVID");
        holder.contactCount.setText(result.hasContactCount + " person(s) have contact for COVID");
        holder.building.setText(result.building + "");
        if(result.hasBuilding){
            holder.layout.setBackgroundColor(mContext.getColor(R.color.grey));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView covidCount;
        TextView symptomCount;
        TextView contactCount;
        TextView building;

        View layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            covidCount = itemView.findViewById(R.id.covidcount);
            symptomCount = itemView.findViewById(R.id.symptomcount);
            contactCount = itemView.findViewById(R.id.contactcount);
            building = itemView.findViewById(R.id.building);
        }
    }

}

