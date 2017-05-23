package app.popularmoviestage.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidmvp.bondol.project1.R;

/***
 * @author TUON BONDOL on 5/23/17.
 *
 */

public class MovieTrailerRecyclerViewAdapter extends RecyclerView.Adapter<MovieTrailerRecyclerViewAdapter.MovieTrailerViewHolder>{

    String[] mListTrailer = {"Trailer 1", "Trailer 2", "Trailer 3", "Trailer 4", "Trailer 5"};

    private ItemClickListener mItemClickListener;

    public MovieTrailerRecyclerViewAdapter(ItemClickListener listener){
        mItemClickListener = listener;
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieTrailerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {
        holder.mTopTitleTextView.setVisibility(View.VISIBLE);
        if(position != 0){
            holder.mTopTitleTextView.setVisibility(View.GONE);
        }
        holder.mTrailerTextView.setText(mListTrailer[position]);
    }

    @Override
    public int getItemCount() {
        return mListTrailer.length;
    }

    class MovieTrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTopTitleTextView;
        private ImageView mPlayImageView;
        private TextView mTrailerTextView;

        MovieTrailerViewHolder(View itemView) {
            super(itemView);

            mTopTitleTextView = (TextView) itemView.findViewById(R.id.tv_trailer_top_title);
            mPlayImageView = (ImageView) itemView.findViewById(R.id.iv_play);
            mTrailerTextView = (TextView) itemView.findViewById(R.id.tv_trailer_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener !=null){
                mItemClickListener.onItemClickCallback(getAdapterPosition());
            }
        }
    }
}
