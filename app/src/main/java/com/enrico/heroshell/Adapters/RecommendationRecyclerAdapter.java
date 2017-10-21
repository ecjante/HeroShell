package com.enrico.heroshell.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.enrico.heroshell.Activities.ContainerActivity;
import com.enrico.heroshell.Fragments.UserProfileFragment;
import com.enrico.heroshell.Models.Recommendation;
import com.enrico.heroshell.Models.User;
import com.enrico.heroshell.R;

import java.util.ArrayList;

/**
 * Created by enrico on 10/21/17.
 */

public class RecommendationRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<Recommendation> recommendations;

    public RecommendationRecyclerAdapter() {
        recommendations = new ArrayList<>();
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
        recommendations.add(new Recommendation());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recommendation_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View v = holder.itemView;
        Recommendation recommendation = recommendations.get(position);

        ImageView albumArt = (ImageView) v.findViewById(R.id.image_view);
        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCorners(10)
        );
        Glide.with(ContainerActivity.getContext())
                .load(recommendation.getStream().getAlbumArt())
                .apply(RequestOptions.bitmapTransform(multi))
                .into(albumArt);

        TextView primaryText = (TextView) v.findViewById(R.id.primary_text);
        primaryText.setText(recommendation.getStream().getTitle());

        TextView secondaryText = (TextView) v.findViewById(R.id.secondary_text);
        secondaryText.setText(recommendation.getStream().getArtist());

        hideUserImages(v);
        if (!recommendation.getRecommendedBy().isEmpty()) {
            int userShownCount = 0;
            for (int i = 0; i < recommendation.getRecommendedBy().size(); i++) {
                ImageView userImage;
                if (i == 0) {
                    userImage = v.findViewById(R.id.user0);
                } else if (i == 1) {
                    userImage = v.findViewById(R.id.user1);
                } else {
                    break;
                }
                if (userImage != null) {
                    userImage.setVisibility(View.VISIBLE);
                    final User user = recommendation.getRecommendedBy().get(i);
                    Glide.with(ContainerActivity.getContext())
                            .load(user.getProfileImageUrl())
                            .apply(RequestOptions.circleCropTransform())
                            .into(userImage);
                    userImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UserProfileFragment userProfileFragment = new UserProfileFragment();
                            userProfileFragment.setUser(user);
                            ContainerActivity.pushFragment(userProfileFragment);
                        }
                    });
                    userShownCount++;
                } else {
                    break;
                }
            }

            if (recommendation.getRecommendedBy().size() > userShownCount) {
                TextView userCountText = (TextView) v.findViewById(R.id.user_count);
                int leftOverCount = recommendation.getRecommendedBy().size() - userShownCount;
                String pluralizedUser = (leftOverCount == 1) ? "user" : "users";
                String userCountString = "and " + leftOverCount + " other " + pluralizedUser;
                userCountText.setText(userCountString);
            }
        }
    }

    private void hideUserImages(View v) {
        ImageView user0 = v.findViewById(R.id.user0);
        user0.setVisibility(View.GONE);
        ImageView user1 = v.findViewById(R.id.user1);
        user1.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }
}
