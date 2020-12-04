package com.example.androidsemesterproject.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidsemesterproject.CreateAccountActivity;
import com.example.androidsemesterproject.MainActivity;
import com.example.androidsemesterproject.R;
import com.example.androidsemesterproject.model.email.EMail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private List<EMail> account;
    private AccountViewModel viewModel;

    public AccountAdapter(List<EMail> account,AccountViewModel accountViewModel)
    {
        this.account = account;
        this.viewModel = accountViewModel;
    }
    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.accountsinglerow,parent, false);
        return new AccountAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(account.get(position)!=null) {
            holder.email.setText(account.get(position).fullAddress());
            System.out.println("nnoniodnidioji");
        }
    }

    @Override
    public int getItemCount() {
        if(account!=null)
        {
            return account.size();
        }
        return 0;
    }



//    private MutableLiveData<String> itemText;
//
//
//    public MutableLiveData<String> getItemText() {
//        if(itemText == null)
//        {
//            itemText = new MutableLiveData<>();
//        }
//        return itemText;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView email;
        ImageView delete;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            email =  itemView.findViewById(R.id.email);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {


                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String pass = viewModel.getAccount(email.getText().toString()).getPassword();

                    AuthCredential credential = EmailAuthProvider
                            .getCredential(email.getText().toString(), pass);

                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    user.delete()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        viewModel.removeAccount(email.getText().toString());
                                                        Toast.makeText(view.getContext(), email.getText().toString() + " has been removed", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                }
                            });


                    System.out.println("##############");
                }


            });

        }
    }
}
