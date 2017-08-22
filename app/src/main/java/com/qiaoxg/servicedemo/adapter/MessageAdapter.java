package com.qiaoxg.servicedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiaoxg.servicedemo.R;
import com.qiaoxg.servicedemo.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/22.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageVH> {

    public static final int VIEW_TYPE_RECEIVE = 0;
    public static final int VIEW_TYPE_SEND = 1;

    private List<MessageBean> messageBeanList;

    public MessageAdapter() {
        this.messageBeanList = new ArrayList<>();
    }

    public void addMessage(MessageBean bean) {
        if (messageBeanList == null) {
            messageBeanList = new ArrayList<>();
        }
        messageBeanList.add(bean);
        notifyDataSetChanged();
    }

    @Override
    public MessageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SEND) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_send, null, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receive, null, false);
        }
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(MessageVH holder, int position) {
        MessageBean bean = messageBeanList.get(position);
        int headerId = R.drawable.header_01;
        if (bean.getHeaderId() == 1) {
            headerId = R.drawable.header_02;
        } else if (bean.getHeaderId() == 2) {
            headerId = R.drawable.header_03;
        } else if (bean.getHeaderId() == 3) {
            headerId = R.drawable.header_04;
        }
        holder.headerIv.setImageResource(headerId);
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
    }

    @Override
    public int getItemViewType(int position) {
        return messageBeanList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }

    public class MessageVH extends RecyclerView.ViewHolder {

        private ImageView headerIv;
        private TextView titleTv, contentTv;

        public MessageVH(View itemView) {
            super(itemView);
            headerIv = (ImageView) itemView.findViewById(R.id.header_iv);
            titleTv = (TextView) itemView.findViewById(R.id.userName_tv);
            contentTv = (TextView) itemView.findViewById(R.id.phone_tv);
        }
    }
}
