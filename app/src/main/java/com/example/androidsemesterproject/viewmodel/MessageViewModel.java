package com.example.androidsemesterproject.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidsemesterproject.model.message.Message;
import com.example.androidsemesterproject.connection.RetrofitClient;
import com.example.androidsemesterproject.repository.MessageRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class MessageViewModel extends AndroidViewModel {

    private MessageRepository repository;

    public MessageViewModel(Application application){
        super(application);
        repository = MessageRepository.getInstance(application);
    }

    public LiveData<List<Message>> getAllMessages()
    {
        return repository.getAllMessages();
    }

    public void addMessage(Message message)
    {
        repository.insert(message);
    }

    public Message getMessage(int id){
    Message message = new Message();
     List<Message> messageList =  repository.getAllMessages().getValue();
        for (int i = 0; i <messageList.size() ; i++) {
            if(messageList.get(i).getId()==id) {
                message = messageList.get(i);
            }
        }
        return message;
    }

    public void send(String from,String to,String subject, String message)
    {
        RetrofitClient.getInstance()
                .getApi()
                .sendEmail(from, to, subject, message)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == HTTP_OK) {
                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                Toast.makeText(getApplication().getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void deleteMessage(int id)
    {
        repository.deleteMessage(id);
    }
}

