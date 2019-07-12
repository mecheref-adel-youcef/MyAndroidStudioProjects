package joseph.youcef.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserViewHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView description;
    public ImageView photo;

    public UserViewHolder(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View view){
        this.name = view.findViewById(R.id.name);
        this.description = view.findViewById(R.id.description);
        this.photo =view.findViewById(R.id.imageView);
    }

    public void setItem(User user){
        name.setText(user.getName());
        description.setText(user.getDescription());
        photo.setImageResource(user.getPhoto());
    }

}
