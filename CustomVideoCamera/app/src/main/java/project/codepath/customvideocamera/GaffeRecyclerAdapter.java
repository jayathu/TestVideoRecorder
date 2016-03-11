package project.codepath.customvideocamera;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jnagaraj on 3/6/16.
 */
public class GaffeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private RecordActionListener listener;

    private List<QuestionCard> cards;

    public void setRecordActionListener(RecordActionListener listner) {
        this.listener = listner;
    }

    public GaffeRecyclerAdapter(List<QuestionCard> cards) {
        this.cards = cards;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        GaffeItemHolder gaffeItemHolder;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_question, parent, false);
        gaffeItemHolder = new GaffeItemHolder(view);

        return gaffeItemHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        GaffeItemHolder gaffeHolder = (GaffeItemHolder) holder;
        gaffeHolder.loadDataIntoView(cards.get(position));

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class GaffeItemHolder extends RecyclerView.ViewHolder {

        private TextView mQuestionTitle;
        private TextView mUsername;
        private Button mOpenCamera;

        public GaffeItemHolder(final View itemView) {
            super(itemView);

            mQuestionTitle = (TextView)itemView.findViewById(R.id.tvBody);
            mUsername = (TextView)itemView.findViewById(R.id.tvUsername);
            mOpenCamera = (Button)itemView.findViewById(R.id.openCamera);

            mOpenCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onRecordButtonClick(itemView, getLayoutPosition());
                    }
                }
            });
        }

        public void loadDataIntoView(QuestionCard card) {

            mQuestionTitle.setText(card.title);
            mUsername.setText(card.username);
        }


    }
}
