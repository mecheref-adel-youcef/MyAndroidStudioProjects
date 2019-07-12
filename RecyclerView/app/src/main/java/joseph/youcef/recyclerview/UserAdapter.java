package joseph.youcef.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {


    private List<User> userList;

    public UserAdapter( List<User> users) {

        this.userList = users;
    }


    @NonNull
    @Override //relier item xml
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(itemView);

    }

    // bind view model every item
    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
            holder.setItem(userList.get(position));
            setAnimation(holder.itemView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Hi "+holder.name.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });


    }

    // total nb
    @Override
    public int getItemCount() {
        return userList.size();
    }


    //set animation
    private void setAnimation(View view){
        ScaleAnimation anim = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);


    }
}
