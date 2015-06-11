package com.yingluo.Appraiser.im;


import com.lidroid.xutils.util.LogUtils;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.OnSendMessageListener;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.LocationInputProvider;
import io.rong.imkit.widget.provider.VoIPInputProvider;
import io.rong.imlib.RongIMClient.ConnectionStatusListener;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.RongIMClient.ConnectionStatusListener.ConnectionStatus;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ContactNotificationMessage;
import io.rong.message.ImageMessage;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 接手融云聊天信息回调
 * @author xy418
 *
 */
public class RongCloudEvent implements UserInfoProvider,OnReceiveMessageListener,ConnectionStatusListener,OnSendMessageListener{
	
	private static RongCloudEvent mRongCloudInstance;
    private Context mContext;
	 /**
     * 初始化 RongCloud.
     *
     * @param context 上下文。
     */
    public static void init(Context context) {

        if (mRongCloudInstance == null) {

            synchronized (RongCloudEvent.class) {

                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new RongCloudEvent(context);
                }
            }
        }
    }
    
    /**
     * 获取RongCloud 实例。
     *
     * @return RongCloud。
     */
    public static RongCloudEvent getInstance() {
        return mRongCloudInstance;
    }
    
    /**
     * 构造方法。
     *
     * @param context 上下文。
     */
    private RongCloudEvent(Context context) {
        mContext = context;
      initDefaultListener();
    }
    
    /**
     * RongIM.init(this) 后直接可注册的Listener。
     */
    private void initDefaultListener() {
          RongIM.setUserInfoProvider(this, true);//设置用户信息提供者。
//        RongIM.setGroupInfoProvider(this,true);//设置群组信息提供者。
//        RongIM.setConversationBehaviorListener(this);//设置会话界面操作的监听器。
//        RongIM.setLocationProvider(this);//设置地理位置提供者,不用位置的同学可以注掉此行代码
//        RongIM.setPushMessageBehaviorListener(this);//自定义 push 通知。
    }
    
    /**
     * 连接成功注册。
     * <p/>
     * 在RongIM-connect-onSuccess后调用。
     */
    public void setOtherListener() {
        RongIM.getInstance().getRongIMClient().setOnReceiveMessageListener(this);//设置消息接收监听器。
        RongIM.getInstance().setSendMessageListener(this);//设置发出消息接收监听器.
        RongIM.getInstance().getRongIMClient().setConnectionStatusListener(this);//设置连接状态监听器。

        //扩展功能自定义
        InputProvider.ExtendProvider[] provider = {
                new ImageInputProvider(RongContext.getInstance()),
                new CameraInputProvider(RongContext.getInstance()),
                new LocationInputProvider(RongContext.getInstance()),
                new VoIPInputProvider(RongContext.getInstance()),
        };
        RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, provider);
    }

    /**
     * 通过user_id获取融云中的用户信息
     */
	@Override
	public UserInfo getUserInfo(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}



	@Override
	public Message onSend(Message arg0) {
		// TODO 自动生成的方法存根
		return arg0;
	}
	  /**
     * 消息在UI展示后执行/自己的消息发出后执行,无论成功或失败。
     *
     * @param arg0 消息。
     */
	@Override
	public void onSent(Message arg0) {
		// TODO 自动生成的方法存根
		MessageContent messageContent = arg0.getContent();

        if (messageContent instanceof TextMessage) {//文本消息
            TextMessage textMessage = (TextMessage) messageContent;
            LogUtils.d( "onSent-TextMessage:" + textMessage.getContent());
        } else if (messageContent instanceof ImageMessage) {//图片消息
            ImageMessage imageMessage = (ImageMessage) messageContent;
            LogUtils.d("onSent-ImageMessage:" + imageMessage.getRemoteUri());
        } else if (messageContent instanceof VoiceMessage) {//语音消息
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
            LogUtils.d( "onSent-voiceMessage:" + voiceMessage.getUri().toString());
        } else if (messageContent instanceof RichContentMessage) {//图文消息
            RichContentMessage richContentMessage = (RichContentMessage) messageContent;
            LogUtils.d( "onSent-RichContentMessage:" + richContentMessage.getContent());
        } else {
        	LogUtils.d( "onSent-其他消息，自己来判断处理");
        }
	}

	
	 /**
     * 连接状态监听器，以获取连接相关状态:ConnectionStatusListener 的回调方法，网络状态变化时执行。
     *
     * @param status 网络状态。
     */
	@Override
	public void onChanged(ConnectionStatus arg0) {
		// TODO 自动生成的方法存根
		  LogUtils.d( "onChanged:" + arg0);
	        if (arg0.getMessage().equals(ConnectionStatus.DISCONNECTED.getMessage())) {
	        
	       }
	}


    /**
     * 接收消息的监听器：OnReceiveMessageListener 的回调方法，接收到消息后执行。
     *
     * @param message 接收到的消息的实体信息。
     * @param left    剩余未拉取消息数目。
     */
	@Override
	public boolean onReceived(Message arg0, int arg1) {
		// TODO 自动生成的方法存根
		  MessageContent messageContent = arg0.getContent();
	        if (messageContent instanceof TextMessage) {//文本消息
	            TextMessage textMessage = (TextMessage) messageContent;
	            LogUtils.d("onReceived-TextMessage:" + textMessage.getContent());
	        } else if (messageContent instanceof ImageMessage) {//图片消息
	            ImageMessage imageMessage = (ImageMessage) messageContent;
	            LogUtils.d("onReceived-ImageMessage:" + imageMessage.getRemoteUri());
	        } else if (messageContent instanceof VoiceMessage) {//语音消息
	            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
	            LogUtils.d( "onReceived-voiceMessage:" + voiceMessage.getUri().toString());
	        } else if (messageContent instanceof RichContentMessage) {//图文消息
	            RichContentMessage richContentMessage = (RichContentMessage) messageContent;
	            LogUtils.d( "onReceived-RichContentMessage:" + richContentMessage.getContent());
	        } else if (messageContent instanceof InformationNotificationMessage) {//小灰条消息
	            InformationNotificationMessage informationNotificationMessage = (InformationNotificationMessage) messageContent;
	            LogUtils.d( "onReceived-informationNotificationMessage:" + informationNotificationMessage.getMessage());
	        }  else {
	        	LogUtils.d( "onReceived-其他消息，自己来判断处理");
	        }

	        return false;
	}

    
}
