package by.nepravsky.sm.evereactioncalculator.screens.main.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import by.nepravsky.sm.domain.entity.presentation.ItemPres;
import by.nepravsky.sm.evereactioncalculator.screens.base.recycler.BaseAdapter;
import by.nepravsky.sm.evereactioncalculator.screens.base.recycler.BaseClickedModel;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ReactionsAdapter  extends BaseAdapter<ItemPres, ReactionsViewModel> {

    private PublishSubject<BaseClickedModel<ItemPres>> positionCLick = PublishSubject.create();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ReactionsViewHolder.init(parent, new ReactionsViewModel(positionCLick));
    }

    public Observable<BaseClickedModel<ItemPres>> observItemClick(){
        return positionCLick;
    }

}
