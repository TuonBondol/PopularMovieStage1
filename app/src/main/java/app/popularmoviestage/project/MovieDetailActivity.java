package app.popularmoviestage.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidmvp.bondol.project1.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private RecyclerView mTrailerRecyclerView;
    private MovieTrailerRecyclerViewAdapter mMovieTrailerRecyclerViewAdapter;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        TextView mMovieTitleTextView = (TextView) findViewById(R.id.tv_movie_title);
        TextView mReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        ImageView mMovieImageView = (ImageView) findViewById(R.id.iv_movie_image);
        TextView mUserRateTextView = (TextView) findViewById(R.id.tv_user_rate);
        TextView mOverViewTextView = (TextView) findViewById(R.id.tv_overview);
        Button mMarkAsFavoriteButton = (Button) findViewById(R.id.mark_as_favorite);

        mTrailerRecyclerView = (RecyclerView) findViewById(R.id.rv_trailer);

        Intent intent = getIntent();
        if (intent.hasExtra(Constant.INTENT_OBJECT)) {
            Result mResultModel = (Result) intent.getSerializableExtra(Constant.INTENT_OBJECT);
            mMovieTitleTextView.setText(mResultModel.getOriginalTitle());
            mReleaseDateTextView.setText(mResultModel.getReleaseDate());
            Picasso
                    .with(this)
                    .load(PopularMovieStageAdapter.BASE_HOST_IMAGE + PopularMovieStageAdapter.IMAGE_WIDTH + mResultModel.getPosterPath())
                    .into(mMovieImageView);
            mUserRateTextView.setText(mResultModel.getVoteAverage() + getResources().getString(R.string.rate_ten));
            mOverViewTextView.setText(mResultModel.getOverview());
        }

        mMarkAsFavoriteButton.setOnClickListener(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mTrailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTrailerRecyclerView.setHasFixedSize(true);
        mTrailerRecyclerView.setNestedScrollingEnabled(false);

        mMovieTrailerRecyclerViewAdapter = new MovieTrailerRecyclerViewAdapter(this);
        mTrailerRecyclerView.setAdapter(mMovieTrailerRecyclerViewAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mark_as_favorite: {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(this, getResources().getString(R.string.favorite_clicked), Toast.LENGTH_LONG);
                mToast.show();
                break;
            }
        }
    }

    @Override
    public void onItemClickCallback(Object data) {
        int indexClick = (int) data;
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, getResources().getString(R.string.trailer_click) + indexClick, Toast.LENGTH_LONG);
        mToast.show();
    }
}
