package chatbot.example.com.chatbotapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradeepsakthi on 21/01/18.
 */

public class ResponseModel implements Parcelable{

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("message")
    @Expose
    private Message message;
//    @SerializedName("data")
//    @Expose
//    private List<Object> data = null;
    private boolean isUser;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

//    public List<Object> getData() {
//        return data;
//    }
//
//    public void setData(List<Object> data) {
//        this.data = data;
//    }


    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public ResponseModel(){

    }

    protected ResponseModel(Parcel in) {
        success = in.readByte() == 0x00 ? null : in.readInt();
        errorMessage = in.readString();
        message = (Message) in.readValue(Message.class.getClassLoader());
//        if (in.readByte() == 0x01) {
//            data = new ArrayList<Object>();
//            in.readList(data, Object.class.getClassLoader());
//        } else {
//            data = null;
//        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (success == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(success);
        }
        dest.writeString(errorMessage);
        dest.writeValue(message);
//        if (data == null) {
//            dest.writeByte((byte) (0x00));
//        } else {
//            dest.writeByte((byte) (0x01));
//            dest.writeList(data);
//        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseModel> CREATOR = new Parcelable.Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };
}
