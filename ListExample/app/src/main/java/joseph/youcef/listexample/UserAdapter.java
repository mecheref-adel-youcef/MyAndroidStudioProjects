package joseph.youcef.listexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {


    private Context mContext;
    private List<User> userList ;

    public UserAdapter(@NonNull  Context context, List<User> users) {
        super(context, 0, users);
        this.mContext = context;
        this.userList = users;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);



        // optimisation affichage des vues mini controller
        UserViewHolder userViewHolder = (UserViewHolder) listItem.getTag();
        if(userViewHolder == null){
            userViewHolder = new UserViewHolder();
            userViewHolder.name = listItem.findViewById(R.id.name);
            userViewHolder.description = listItem.findViewById(R.id.description);
            userViewHolder.photo = listItem.findViewById(R.id.imageView);
            listItem.setTag(userViewHolder);
        }

        User currentUser = userList.get(position);

        ImageView imageView  = listItem.findViewById(R.id.imageView);
        imageView.setImageResource(currentUser.getPhoto());

        TextView name = listItem.findViewById(R.id.name);
        name.setText(currentUser.getName());

        TextView description = listItem.findViewById(R.id.description);
        description.setText(currentUser.getDescription());

        return listItem;

    }
}
