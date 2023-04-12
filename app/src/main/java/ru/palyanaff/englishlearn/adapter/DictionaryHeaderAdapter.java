package ru.palyanaff.englishlearn.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.palyanaff.englishlearn.R;

public class DictionaryHeaderAdapter extends RecyclerView.Adapter<DictionaryHeaderAdapter.ItemViewHolder>{
    private static final String TAG = "DictionaryHeaderAdapter";
    private final List<String> list;

    public DictionaryHeaderAdapter (List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_header_item,parent,false);
        return new DictionaryHeaderAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.dictionaryHeader.setText(list.get(position));
        /*holder.cardView.setOnClickListener(v -> {
            try {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                @NonNull DictionaryFragmentDirections.ActionDictionaryFragmentToWordListFragment action
                        = DictionaryFragmentDirections.actionDictionaryFragmentToWordListFragment(list.get(position));
                navController.navigate(action);
            } catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView dictionaryHeader;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.dictionary_card_view);
            dictionaryHeader = itemView.findViewById(R.id.dictionary_header_text);
        }
    }
}