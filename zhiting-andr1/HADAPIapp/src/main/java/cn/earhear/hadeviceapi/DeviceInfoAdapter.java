package cn.earhear.hadeviceapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.earhear.hadevicelib.DeviceInfo;

/**
 * Created by ice88 on 2018-5-14.
 */

public class DeviceInfoAdapter extends BaseAdapter {
    public static final int INVALID_POSITION = -1;

    private Context context;
    private List<DeviceInfo> deviceInfoList;
    private OnDeviceClickListener mListener;
    private int connectPosition;

    public DeviceInfoAdapter(Context context) {
        this.context = context;
    }

    public void addDeviceInfo(DeviceInfo deviceInfo) {
        if (deviceInfoList == null)
            deviceInfoList = new ArrayList<>();
        deviceInfoList.add(deviceInfo);
        notifyDataSetChanged();
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
        notifyDataSetChanged();
    }

    public void setConnectPosition(int connectPosition) {
        this.connectPosition = connectPosition;
        if (connectPosition != INVALID_POSITION)
            notifyDataSetChanged();
    }

    public int getConnectPosition() {
        return connectPosition;
    }

    public void deviceListClear() {
        if (deviceInfoList != null) {
            this.deviceInfoList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (deviceInfoList != null)
            return deviceInfoList.size();
        return 0;
    }

    @Override
    public DeviceInfo getItem(int position) {
        if (deviceInfoList == null || position > deviceInfoList.size())
            return null;
        return deviceInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_deviceinfo, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.img_bt = convertView.findViewById(R.id.img_bt);
            holder.txt_name = convertView.findViewById(R.id.txt_name);
            holder.txt_mac = convertView.findViewById(R.id.txt_mac);
            holder.txt_rssi = convertView.findViewById(R.id.txt_rssi);
            holder.btn_connect = convertView.findViewById(R.id.btn_connect);
        }

        final DeviceInfo deviceInfo = getItem(position);
        if (deviceInfo != null) {
            holder.img_bt.setBackgroundResource(R.mipmap.ic_bt_remote);
            holder.txt_name.setText(deviceInfo.getDeviceName());
            holder.txt_mac.setText(deviceInfo.getDeviceMac());
            holder.txt_rssi.setText(String.valueOf(deviceInfo.getRssi()));

            if (position == this.connectPosition)
                holder.btn_connect.setText(R.string.connected);
            else
                holder.btn_connect.setText(R.string.wait_connect);

            holder.btn_connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onChoice(position);
                    }
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img_bt;
        TextView txt_name;
        TextView txt_mac;
        TextView txt_rssi;
        Button btn_connect;
    }

    public interface OnDeviceClickListener {
        void onChoice(int position);
    }

    public void setOnDeviceClickListener(OnDeviceClickListener listener) {
        this.mListener = listener;
    }
}